/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.userinfo;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.UmsSubitemAuditMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsAuthorityService;
import cn.net.withub.ums.subitemAudit.AuditStatusEnum;
import cn.net.withub.ums.subitemAudit.SubItemAuditRepository;
import cn.net.withub.ums.util.StringUtils;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/subitem")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class SubItemAction {

    @Autowired
    SubItemAuditRepository repository;

    @Autowired
    UmsSubitemAuditMapper subitemAuditMapper;

    @Autowired
    private UmsAuthorityService authorityService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Object data;
    //参数实体类
    private UmsSubitemAudit info;
    //分页
    private Integer page;
    private Integer start;
    private Integer limit;


    //获取到基本的信息
    @Action("selectSubItemInfoForLayUI")
    public String selectSubItemInfoForLayUI() {

        //一定要分页
        if (page == null || limit == null) {
            return "json";
        }
        //简单判断是不是管理员
        boolean manager = isManager();
        if (!manager) {
            return "json";
        }

        UmsSubitemAuditExample example = new UmsSubitemAuditExample();
        if (info != null) {
            createExample(info, example);
        }

        example.setPage(page);
        example.setLimit(limit);
        example.setStart((page - 1) * limit);
        example.setOrderByClause(" create_time DESC ");

        // 获取传入的创建时间范围参数
        if (null != info) {
            if (info.getCreateTimeStart() != null) {
                example.getOredCriteria().get(0).andCreateTimeGreaterThan(info.getCreateTimeStart());
            }
            if (info.getCreateTimeEnd() != null) {
                example.getOredCriteria().get(0).andCreateTimeLessThan(info.getCreateTimeEnd());
            }
        }

        //要设置分页参数
        List<UmsSubitemAuditWithBLOBs> datas = subitemAuditMapper.selectByExampleWithBLOBs(example);
        int count = subitemAuditMapper.countByExample(example);

        Map<String, Object> re = new HashMap<>();
        re.put("code", 0);
        re.put("count", count);
        re.put("data", datas);

        data = re;
        return "json";
    }

    @Action("selectSubItemInfoForBUI")
    public String selectSubItemInfoForBUI() {

        //一定要分页
        if (start == null || limit == null) {
            return "json";
        }
        //简单判断是不是管理员
        boolean manager = isManager();
        if (!manager) {
            return "json";
        }

        UmsSubitemAuditExample example = new UmsSubitemAuditExample();
        if (info != null) {
            createExample(info, example);
        }
        example.setLimit(limit);
        example.setStart(start);
        example.setOrderByClause(" create_time DESC ");
        //要设置分页参数
        List<UmsSubitemAuditWithBLOBs> datas = subitemAuditMapper.selectByExampleWithBLOBs(example);
        int count = subitemAuditMapper.countByExample(example);

        Map<String, Object> map = new HashMap<>();


        map.put("rows", datas);
        map.put("results", count);

        data = map;
        return "json";
    }

    @Action("selectSubItemInfoForBUIOnlyForCount")
    public String selectSubItemInfoForBUIOnlyForCount() {

        //一定要分页
        if (start == null || limit == null) {
            return "json";
        }
        //简单判断是不是管理员
        boolean manager = isManager();
        if (!manager) {
            return "json";
        }

        UmsSubitemAuditExample example = new UmsSubitemAuditExample();
        if (info != null) {
            createExample(info, example);
        }
        // 只查询未审核的

        example.setLimit(limit);
        example.setStart(start);
        //要设置分页参数
        int count = subitemAuditMapper.countByExample(example);

        Map<String, Object> map = new HashMap<>();

        map.put("results", count);

        data = map;
        return "json";
    }

    //审核状态
    @Action("auditStatus")
    public String auditStatus() {

        Map<Integer, String> map = new HashMap<>();
        for (AuditStatusEnum value : AuditStatusEnum.values()) {
            map.put(value.getStatus(), value.getDescribe());
        }
        data = map;
        return "json";
    }

    //撤销申请
    @Action("deleteAuditInfo")
    public String deleteAuditInfo() {

        data = false;
        if (info == null || StringUtils.isEmpty(info.getId())) {
            return "json";
        }
        //简单判断是不是管理员
        boolean manager = isManager();
        if (!manager) {
            return "json";
        }
        //删除之前看一下状态
        UmsSubitemAuditWithBLOBs audit = subitemAuditMapper.selectByPrimaryKey(info.getId());
        //只能是为审核状态
        if (audit == null || audit.getAuditStatus() != AuditStatusEnum.S_1.getStatus()) {
            return "json";
        }

        int i = subitemAuditMapper.deleteByPrimaryKey(info.getId());
        data = i > 0;
        return "json";
    }

    //是否是管理员
    @Action("isManager")
    public String isManagerA() {
        data = isManager();
        return "json";
    }

    // 保存审核意见
    @Action("updateAuditInfo")
    public String updateAuditInfo() {
        // 获取当前操作账号的信息
        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();
        UmsUserInfoView user = (UmsUserInfoView) session.get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

        UmsSubitemAuditWithBLOBs umsSubitemAuditWithBLOBs = new UmsSubitemAuditWithBLOBs();
        umsSubitemAuditWithBLOBs.setId(info.getId());
        umsSubitemAuditWithBLOBs.setAuditorId(user.getUserId()); // 审核人
        umsSubitemAuditWithBLOBs.setAuditorName(user.getFullname()); // 审核人名称
        try {
            umsSubitemAuditWithBLOBs.setAuditTime(simpleDateFormat.parse(simpleDateFormat.format(new Date()))); // 审核时间
        } catch (ParseException e) {
            e.printStackTrace();
        }

        umsSubitemAuditWithBLOBs.setAuditStatus(info.getAuditStatus());
        umsSubitemAuditWithBLOBs.setAuditOpinions(info.getAuditOpinions());

        // 区分单个和批量
        String id = info.getId();
        if (id.contains(",")) {
            String[] idArray = id.split(",");
            for (String idA : idArray) {
                umsSubitemAuditWithBLOBs.setId(idA);
                int update = subitemAuditMapper.updateByPrimaryKeySelective(umsSubitemAuditWithBLOBs);
                // 审核通过后更新子表
                if (info.getAuditStatus() == 1) {
                    // 更新子表成功或失败
                    boolean update_cgsb = repository.saveOrUpdate(idA);
                }
            }
        } else {
            int update = subitemAuditMapper.updateByPrimaryKeySelective(umsSubitemAuditWithBLOBs);

            // 审核通过后更新子表
            if (info.getAuditStatus() == 1) {
                // 更新子表成功或失败
                boolean update_cgsb = repository.saveOrUpdate(info.getId());
            }
        }

        Map<String, Object> re = new HashMap<>();
        re.put("code", 0);
        data = re;
        return "json";
    }


    // 反射创建example 只能创建等于情况
    private void createExample(UmsSubitemAudit info, UmsSubitemAuditExample example) {
        example.clear();
        UmsSubitemAuditExample.Criteria criteria = example.createCriteria();
        try {
            Class<? extends UmsSubitemAuditExample.Criteria> aClass = criteria.getClass();
            Field[] fields = info.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                Object o = field.get(info);
                if (o != null) {
                    try {
                        if ("CreateTimeStart".equals(Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1)) || "CreateTimeEnd".equals(Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1))) {
                            // 跳过时间范围的反射
                            continue;
                        }
                        String methodName = "and" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1) + "EqualTo";
                        Method method = aClass.getDeclaredMethod(methodName, o.getClass());
                        method.invoke(criteria, o);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //简单判断一下是不是管理员权限
    public boolean isManager() {
        boolean flag = false;
        UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        for (UmsRole role : authorityService.userRoles(u.getId())) {
            if (role.getRoleName().contains("管理员")) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public UmsSubitemAudit getInfo() {
        return info;
    }

    public void setInfo(UmsSubitemAudit info) {
        this.info = info;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}
