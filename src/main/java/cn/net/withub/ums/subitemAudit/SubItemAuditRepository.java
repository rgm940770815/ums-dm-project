package cn.net.withub.ums.subitemAudit;


import cn.net.withub.ums.action.userinfo.UserInfoAttachedTableAction;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.UmsSubitemAuditMapper;
import cn.net.withub.ums.dao.UmsSubitemRuleMapper;
import cn.net.withub.ums.dao.UmsUserInfoViewMapper;
import cn.net.withub.ums.dao.attach.experimental.UmsAttachedTableMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsCodeService;
import cn.net.withub.ums.util.StringTools;
import cn.net.withub.ums.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/*
权限操作仓库
 */
@Repository
public class SubItemAuditRepository {


    @Autowired
    UmsSubitemAuditMapper subitemAuditMapper;

    @Autowired
    UmsSubitemRuleMapper subitemRuleMapper;

    @Autowired
    private UmsCodeService codeService;

    @Autowired
    UmsAttachedTableMapper umsAttachedTableMapper;

    @Autowired
    UserInfoAttachedTableAction userInfoAttachedTableAction;

    @Autowired
    UmsUserInfoViewMapper umsUserInfoViewMapper;


    // 获取到配置规则
    public UmsSubitemRule getRuleByKey(String key) {
        UmsSubitemRuleExample example = new UmsSubitemRuleExample();
        example.createCriteria().andTableKeyEqualTo(key).andEnableEqualTo(true);
        List<UmsSubitemRule> umsSubitemRules = subitemRuleMapper.selectByExample(example);
        return umsSubitemRules != null && umsSubitemRules.size() > 0 ? umsSubitemRules.get(0) : null;
    }

    //保存审核新
    public boolean saveAuditInfo(String contentType, String contentTypeText, ExamineEnum examineEnum, Object entity, Map<String, Object> oldDataInfo, String shlx) {

        try {

            ActionContext context = ActionContext.getContext();
            Map session = context.getSession();
            UmsUserInfoView user = (UmsUserInfoView) session.get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

            SerializeConfig config = new SerializeConfig();
            config.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));

            UmsSubitemAuditWithBLOBs audit = new UmsSubitemAuditWithBLOBs();
            audit.setId(UUID.randomUUID().toString());
            audit.setContentType(contentType);
            audit.setContentTypeText(contentTypeText);
            audit.setExamineType(examineEnum.getKey());
            audit.setExamineTypeText(examineEnum.getValue());
            audit.setSerializeContent(JSON.toJSONString(entity, config));
            audit.setCreateTime(new Date());
            audit.setCreatorId(user.getId());
            audit.setCreatorName(user.getFullname());
            //未审核
            audit.setAuditStatus(AuditStatusEnum.S_1.getStatus());
            //获取到实体类用到的所有编码
            Map<Integer, List<UmsCode>> allCodeByReflect = getAllCodeByReflect(entity);
            //实体类显示描述
            Map<String, String> describeText = getDescribeText(entity, allCodeByReflect);
            audit.setShowContent(JSON.toJSONString(describeText, config));
            if (examineEnum == ExamineEnum.UPDATE) {
                //还要添加改变的内容
                String id = getValueByReflect(entity, "id");
                if (StringUtils.isEmpty(id)) {
                    throw new Exception("empty id");
                }
                setChangeContent(contentType, id, entity, allCodeByReflect, audit, oldDataInfo);
            }
            String userId = getValueByReflect(entity, "userId");
            if (StringUtils.isEmpty(userId)) {
                //没有userId为不符合条件
                throw new Exception("empty userId");
            }
            //查找被操作人的信息
            UmsUserInfoViewCriteria example = new UmsUserInfoViewCriteria();
            example.createCriteria().andIdEqualTo(userId);
            List<UmsUserInfoView> umsUserInfoViews = umsUserInfoViewMapper.selectByExample(example);
            //只取一条 没有报错
            UmsUserInfoView umsUserInfoView = umsUserInfoViews.get(0);
            audit.setOperatedUserId(umsUserInfoView.getId());
            audit.setOperatedUserName(umsUserInfoView.getFullname());
            audit.setOperatedUserCourtNo(umsUserInfoView.getCourtNo());
            audit.setOperatedUserCourtText(umsUserInfoView.getCourtNoText());
            audit.setShlx(shlx);

            //插入信息
            int insert = subitemAuditMapper.insert(audit);
            return insert > 0;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //审核通过后执行保存或者更新数据
    public boolean saveOrUpdate(String id){

        if(StringUtils.isEmpty(id)){
            return false;
        }
        try {
            UmsSubitemAuditWithBLOBs audit = subitemAuditMapper.selectByPrimaryKey(id);
            //获取到参数
            String serializeContent = audit.getSerializeContent();
            //重新组装参数
            Map<String, String> params = JSON.parseObject(serializeContent, new TypeReference<Map<String, String>>() {
            });

            userInfoAttachedTableAction.setP(params);
            userInfoAttachedTableAction.setT(audit.getContentType().replace("Ums",""));
            if(audit.getExamineType().equals(ExamineEnum.INSERT.getKey())){
                //新增加id
                userInfoAttachedTableAction.setId(params.get("id"));
            }else{
                userInfoAttachedTableAction.setId(null);
            }
            userInfoAttachedTableAction.save();

            Map<String, Object>  data = (Map<String, Object>)userInfoAttachedTableAction.getData();
            if((Boolean) data.get("success")){
                //插入成功
                updateAuditImportStatus(id,AuditStatusEnum.S_2);
                return true;
            }else{
                updateAuditImportStatus(id,AuditStatusEnum.S_3);
            }

        } catch (Exception e) {
            e.printStackTrace();
            updateAuditImportStatus(id,AuditStatusEnum.S_3);
        }
        return  false;
    }

    private int updateAuditImportStatus(String id,AuditStatusEnum status){
        UmsSubitemAuditWithBLOBs item = new UmsSubitemAuditWithBLOBs();
        item.setId(id);
        item.setImportStatus(status.getStatus());
        return subitemAuditMapper.updateByPrimaryKeySelective(item);
    }

    //获取到实体类描述Map
    private Map<String, String> getDescribeText(Object entity, Map<Integer, List<UmsCode>> allCodeByReflect) {
        Field[] fields = entity.getClass().getDeclaredFields();
        Map<String, String> entityMap = new LinkedHashMap<>();
        try {
            Map<Integer, Map<String, String>> sortedMap = new TreeMap<>((x, y) -> {
                if (x == null && y == null) {
                    return 0;
                } else if (x == null) {
                    return -1;
                } else if (y == null) {
                    return 1;
                }
                return x.compareTo(y);
            });
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int nullSort = 10000;
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                FieldDescribe annotation = field.getAnnotation(FieldDescribe.class);
                if (annotation != null) {
                    Object o = field.get(entity);
                    String text = "";
                    if (o != null) {
                        if (o instanceof Date) {
                            text = sdf.format(o);
                        } else {
                            text = o.toString();
                        }
                    }
                    CodeType codeType = field.getAnnotation(CodeType.class);
                    if (codeType != null) {
                        text = getCodeText(allCodeByReflect, codeType, text);
                    }
                    Map<String, String> m1 = new HashMap<>();
                    m1.put(annotation.value(), text);
                    FieldSort fieldSort = field.getAnnotation(FieldSort.class);
                    Integer sort = null;
                    if (fieldSort != null) {
                        sort = fieldSort.value();
                        sortedMap.put(sort, m1);
                    } else {
                        sortedMap.put(nullSort++, m1);
                    }
                }
            }

            for (Map<String, String> value : sortedMap.values()) {
                for (Map.Entry<String, String> entry : value.entrySet()) {
                    entityMap.put(entry.getKey(), entry.getValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return entityMap;
    }

    //反射获取到信息
    public String getValueByReflect(Object entity, String field) {
        try {
            Field userId = entity.getClass().getDeclaredField(field);
            if (userId != null) {
                userId.setAccessible(true);
                Object o = userId.get(entity);
                return o == null ? null : o.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询更新数据以前的值
    public Map<String, Object> oldDataInfo(String viewName, String id) {
        return umsAttachedTableMapper.selectDataById(viewName, id);
    }

    //查找到子信息集合当前信息为是的信息
    public Map<String, Object> presentDataInfo(String viewName, String userId) {
        return umsAttachedTableMapper.selectPresentDataById(viewName, userId);
    }


    //获取到改变内容及文本描述
    private void setChangeContent(String viewName, String id, Object entity, Map<Integer, List<UmsCode>> allCodeByReflect, UmsSubitemAuditWithBLOBs audit, Map<String, Object> oldDataInfo) {
        Map<String, Object> detailChange = new HashMap<>();
        try {

            if (oldDataInfo == null) {
                oldDataInfo = umsAttachedTableMapper.selectDataById(viewName, id);
            }
            Field[] declaredFields = entity.getClass().getDeclaredFields();
            StringBuilder describe = new StringBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (Field field : declaredFields) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                // 把 null 和 ""算一种
                field.setAccessible(true);
                String name = field.getName();
                Object newN = field.get(entity);
                String newValue = "";
                if (newN != null) {
                    if (newN instanceof Date) {
                        newValue = sdf.format(newN);
                    } else {
                        newValue = newN.toString();
                    }
                }
                Object old = oldDataInfo.get(StringTools.camelOrPascalToUnderline(name));
                String oldValue = "";
                if (old != null) {
                    if (old instanceof Date) {
                        oldValue = sdf.format(old);
                    } else {
                        oldValue = old.toString();
                    }
                }
                if (!newValue.equals(oldValue)) {
                    //不同要记录
                    Map<String, Object> change = new HashMap<>();
                    FieldDescribe annotation = field.getAnnotation(FieldDescribe.class);
                    CodeType codeType = field.getAnnotation(CodeType.class);
                    if (annotation != null) {
                        change.put("describe", annotation.value());
                        describe.append(annotation.value());

                        if (codeType != null) {
                            describe.append("由 ").append(getCodeText(allCodeByReflect, codeType, oldValue)).append(" 改变为 ").append(getCodeText(allCodeByReflect, codeType, newValue)).append(";");
                        } else {
                            describe.append("由 ").append(oldValue).append(" 改变为 ").append(newValue).append(";");
                        }

                    }


                    change.put("old", oldValue);
                    change.put("new", newValue);
                    detailChange.put(name, change);
                }
            }
            audit.setChangeContentDescribe(describe.toString());
            audit.setChangeContent(JSON.toJSONString(detailChange, SerializerFeature.WriteDateUseDateFormat));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    private String getCodeText(Map<Integer, List<UmsCode>> allCodeByReflect, CodeType codeType, String code) {
        String text = "";
        if (codeType != null && allCodeByReflect != null && StringUtils.isNotEmpty(code)) {
            List<UmsCode> umsCodes = allCodeByReflect.get(codeType.value());
            if (umsCodes != null) {
                for (UmsCode umsCode : umsCodes) {
                    if (StringUtils.isNotEmpty(umsCode.getId()) && umsCode.getId().equals(code)) {
                        text = umsCode.getCodeName();
                        break;
                    }
                }
            }
        }
        return text;
    }

    //获取到实体类用到的所有编码
    private Map<Integer, List<UmsCode>> getAllCodeByReflect(Object entity) {

        try {
            Field[] fields = entity.getClass().getDeclaredFields();

            List<Integer> keyList = new ArrayList<>();
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                CodeType codeType = field.getAnnotation(CodeType.class);
                if (codeType != null) {
                    keyList.add(codeType.value());
                }
            }
            List<UmsCode> umsCodes = codeService.selectCodesByType(keyList);

            return umsCodes.stream().collect(Collectors.groupingBy(UmsCode::getTypeId));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
