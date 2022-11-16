/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.juror;

import cn.net.withub.ums.action.rmpsSync.RmpsyDataSync;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.*;
import cn.net.withub.ums.util.*;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/juror")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class JurorInfoAction {

    @Autowired
    UmsGbbzlxInfoService umsGbbzlxInfoService;

    private Object data;

    @Autowired
    UmsUserOperationLogService umsUserOperationLogService; // 记录用户操作日志

    @Autowired
    private UmsJurorInfoService jurorInfoService;

    @Autowired
    private UmsUserInfoService userInfoService;
    @Autowired
    private UmsCourtService courtService;
    @Autowired
    RmpsyDataSync rmpsyDataSync;

    @Autowired
    UmsCodeService umsCodeService;

    private UmsJurorInfo jurorInfo;
    private UmsUserInfo userInfo;

    private String id;
    private String codeName;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public UmsJurorInfo getJurorInfo() {
        return jurorInfo;
    }

    public void setJurorInfo(UmsJurorInfo jurorInfo) {
        this.jurorInfo = jurorInfo;
    }

    public UmsUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UmsUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    //</editor-fold>
    @Action("one")
    public String findOne() {
        UmsJurorInfo umsJurorInfo = jurorInfoService.selectById(jurorInfo.getUserId());
        setWorkArea(umsJurorInfo);
        data = umsJurorInfo;
        return "json";
    }

    @Action("oneExtend")
    public String findOneExtend() {
        Map<String, Object> returnMap = new HashMap<>();
        data = returnMap;
        UmsJurorInfo umsJurorInfo = jurorInfoService.selectById(jurorInfo.getUserId());
        returnMap.put("info", umsJurorInfo);
        // 查询工作区域
        if (umsJurorInfo != null) {
            setWorkArea(umsJurorInfo);
            if (StringUtils.hasText(umsJurorInfo.getWorkArea())) {
                UmsCodeCriteria example = new UmsCodeCriteria();
                example.createCriteria().andTypeIdEqualTo(112).andIdEqualTo(umsJurorInfo.getWorkArea());
                List<UmsCode> umsCodes = umsCodeService.selectByExample(example);
                String parentAreaName = getParentAreaName(umsCodes.get(0));
                returnMap.put("gzqy", parentAreaName + "-" + umsCodes.get(0).getCodeName());
            }
        }
        return "json";
    }

    @Action("save")
    public String save() {
        Map<String, Object> map = new HashMap<>();
        data = map;
        // 表ums_user_info
        if (userInfo != null) {

            // 表ums_juror_info
            if (jurorInfo == null) {
                map.put("success", false);
                map.put("msg", "内容验证未通过");
                return "json";
            }

            // 保存前ums_user_info表数据
            UmsUserInfo umsUserInfoBeforeSave = userInfoService.selectById(userInfo.getId());
            // 通过反射获取前端传来的字段
            Field[] fields = userInfo.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                // 设置是否允许访问，不是修改原来的访问权限修饰词。
                fields[i].setAccessible(true);
                try {
                    if (null != fields[i].get(userInfo)) {
                        // 只有ums_user_info表，存在三种类型人员的数据, 只修改这次编辑的数据传来的数据，保留之前的数据
                        Field field = umsUserInfoBeforeSave.getClass().getDeclaredField(fields[i].getName());
                        field.setAccessible(true);
                        field.set(umsUserInfoBeforeSave, fields[i].get(userInfo));
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            // 将原数据与修改后的数据合并赋值给userInfo，这样不用改下面代码
            userInfo = umsUserInfoBeforeSave;

            // 下面的更新的userInfo，是前端传来的数据
            userInfo.setUsername(userInfo.getUsername().trim().replace(" ", "").replace("　", ""));
            userInfo.setFullname(userInfo.getFullname().trim().replace(" ", "").replace("　", ""));
            userInfo.setCourtStdNo(courtService.courtNo2CourtStdNo(userInfo.getCourtNo()));
            userInfo.setCourtCode(courtService.courtNo2CourtCode(userInfo.getCourtNo()));

            // 获取当前用户
            UmsUserInfoView user = SessionUtils.currentUser();
            userInfo.setUpdateUser(user.getFullname() + "@" + user.getCourtNoText());
            userInfo.setUpdateTime(new Date());
            if (StringUtils.isEmpty(userInfo.getDepartment())) {
                userInfo.setDeptOrgCode("998");
                userInfo.setDepartment(999);
            }


            // 表ums_juror_info
            jurorInfo.setUpdateUser(user.getFullname() + "@" + user.getCourtNoText());
            jurorInfo.setUpdateDate(new Date());

            //现在要求给人事系统存储、获取身份证证和手机号的信息对应加密、解密
            EncodeDecodeDataInfo.enCodeDataForUms(userInfo);

            int result = 0;
            try {
                // 新增
                if (StringTools.isNullOrEmpty(userInfo.getId())) {
                    // 测试UserNo统统为负数
                    userInfo.setUserNo((int) (Math.random() * 1000));
                    userInfo.setId(id);

                    // 启用
                    userInfo.setIsValid(1);

                    // 排序
                    if (userInfo.getSortNo() == null) {
                        userInfo.setSortNo(9999);
                    }

                    if (userInfo.getPassword() != null) {
                        Md5PasswordEncoder Md5Checker = new Md5PasswordEncoder();
                        /// salt这个是做什么用的？
                        String salt = null;
                        userInfo.setSalt(salt);
                        String rPassword = Md5Checker.encodePassword(userInfo.getPassword(), salt);
                        userInfo.setPassword(rPassword);
                    }

                    // cPsMzyy：免职原因
                    if (userInfo.getCPsMzyy() != null) {
                        // 停用
                        userInfo.setIsValid(0);
                    }

                    // insert表ums_user_info 和 表xtpt_t_user
                    result = userInfoService.insert(userInfo);

                    // 记录用户操作记录
                    try {
                        UmsUserOperationLog umsUserOperationLog = new UmsUserOperationLog();
                        ActionContext ctx = ActionContext.getContext();
                        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
                        String ip = IpTools.getIpAddress(request);
                        UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                        if (user_ != null) {
                            umsUserOperationLog.setOperationUsername(user_.getFullname());
                        }
                        umsUserOperationLog.setId(UUID.randomUUID().toString());
                        umsUserOperationLog.setModifiedUserid(userInfo.getId());
                        umsUserOperationLog.setOperationTypecode(9);
                        umsUserOperationLog.setOperationTypedetail("新增人民陪审员信息");
                        umsUserOperationLog.setOperationTime(userInfo.getUpdateTime());
                        umsUserOperationLog.setOperationIp(ip);
                        umsUserOperationLog.setOperationContent("新增人民陪审员信息");
                        umsUserOperationLogService.logUserOperation(umsUserOperationLog);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    // 修改
                    // 是否更新ums_gbbzlx_info
                    boolean updateGbbzlxInfo = false;

                    // cPsMzyy：免职原因
                    if (userInfo.getCPsMzyy() != null) {
                        // 停用
                        userInfo.setIsValid(0);
                    } else {
                        // 如果前端没传userType，就用默认的
                        if (null == userInfo.getUserType()) {
                            userInfo.setUserType(3);
                        } else {
                            Integer userType = userInfo.getUserType();
                            // userType大于10，代表是调职或改编
                            if (userType > 10) {
                                userInfo.setIsValid(1);
                                if (userType > 20) {
                                    // 如果是改编，就要更新表
                                    updateGbbzlxInfo = true;
                                }
                                // 该方法就是用于人民陪审员
                                userInfo.setUserType(3);
                            }
                        }
                    }

                    // 这里用来获得哪些字段是改变了的-----------start-------------
                    UmsUserInfo userInfo_original = userInfoService.selectById(userInfo.getId());
                    Map<String, String> map_dif = CompareUtil.compare(userInfo_original, userInfo);
                    // 这里用来获得哪些字段是改变了的------------end--------------

                    // update表ums_user_info 和 表xtpt_t_user
                    result = userInfoService.update(userInfo);

                    // 如果是改编为在编的业务，那么更新表ums_gbbzlx_info为”已完成编辑“
                    if (updateGbbzlxInfo) {
                        String changeUUID = ServletActionContext.getRequest().getParameter("changeUUID");
                        UmsGbbzlxInfo umsGbbzlxInfo = new UmsGbbzlxInfo();
                        umsGbbzlxInfo.setChangeuuid(changeUUID);
                        umsGbbzlxInfo.setState("已完成编辑");
                        umsGbbzlxInfoService.updateByPrimaryKeySelective(umsGbbzlxInfo);
                    }

                    // 记录用户操作记录
                    try {
                        UmsUserOperationLog umsUserOperationLog = new UmsUserOperationLog();
                        ActionContext ctx = ActionContext.getContext();
                        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
                        String ip = IpTools.getIpAddress(request);
                        UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                        if (user_ != null) {
                            umsUserOperationLog.setOperationUsername(user_.getFullname());
                        }
                        umsUserOperationLog.setId(UUID.randomUUID().toString());
                        umsUserOperationLog.setModifiedUserid(userInfo.getId());
                        umsUserOperationLog.setOperationTypecode(10);
                        umsUserOperationLog.setOperationTypedetail("修改人民陪审员信息");
                        umsUserOperationLog.setOperationTime(userInfo.getUpdateTime());
                        umsUserOperationLog.setOperationIp(ip);
                        umsUserOperationLog.setOperationContent("修改的内容:" + map_dif.toString());
                        umsUserOperationLogService.logUserOperation(umsUserOperationLog);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                jurorInfo.setUserId(userInfo.getId());
                // jurorInfo 的工作区域分为四级 但是不清楚是第几级
                String workArea = jurorInfo.getWorkArea();
                if (StringUtils.hasText(workArea)) {
                    String workArea1 = null;
                    String workArea2 = null;
                    String workArea3 = null;
                    String workArea4 = null;
                    switch (workArea.length()) {
                        case 2:
                            workArea1 = workArea;
                            break;
                        case 4:
                            workArea2 = workArea;
                            workArea1 = getParentArea(workArea2);
                            break;
                        case 6:
                            workArea3 = workArea;
                            workArea2 = getParentArea(workArea3);
                            workArea1 = getParentArea(workArea2);
                            break;
                        default:
                            workArea4 = workArea;
                            workArea3 = getParentArea(workArea4);
                            workArea2 = getParentArea(workArea3);
                            workArea1 = getParentArea(workArea2);
                            break;
                    }
                    jurorInfo.setWorkArea1(workArea1);
                    jurorInfo.setWorkArea2(workArea2);
                    jurorInfo.setWorkArea3(workArea3);
                    jurorInfo.setWorkArea4(workArea4);

                } else {
                    jurorInfo.setWorkArea1(null);
                    jurorInfo.setWorkArea2(null);
                    jurorInfo.setWorkArea3(null);
                    jurorInfo.setWorkArea4(null);
                }

                // 如果表ums_juror_info有值：update；无值：insert
                if (jurorInfoService.hasJurorId(userInfo.getId())) {
                    result += jurorInfoService.update(jurorInfo);
                } else {
                    result += jurorInfoService.insert(jurorInfo);
                }

                map.put("success", true);
                map.put("msg", "保存成功！");
            } catch (Exception e) {
                e.printStackTrace();
                map.put("success", false);
                map.put("msg", "内容验证未通过");
            } finally {
                map.put("result", result);
            }
        }
        try {
            // 人民陪审员信息同步
            if ("1".equals(rmpsyDataSync.isSynchronization)) {
                ActionContext context = ActionContext.getContext();
                Map session = context.getSession();
                UmsUserInfoView czr = (UmsUserInfoView) session.get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                rmpsyDataSync.userSync(userInfo.getId(), czr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

    @Action("sayHello")
    public void sayHello() {

    }


    @Action("searchArea")
    public String searchArea() {

        if (!StringUtils.hasText(codeName)) {
            return "json";
        }

        List<Map<String, Object>> list = new ArrayList<>();

        UmsCodeCriteria example = new UmsCodeCriteria();
        UmsCodeCriteria.Criteria criteria = example.createCriteria();
        criteria.andTypeIdEqualTo(112);
        criteria.andCodeNameLike("%" + codeName + "%").andIdRegexp("^[0-9]{9,}$");

        List<UmsCode> umsCodes = umsCodeService.selectByExample(example);

        for (UmsCode umsCode : umsCodes) {
//        UmsCode umsCode = umsCodes.get(0);
            Map<String, Object> m = new HashMap<>();
//            UmsCode p_3 = getParentAreaNode(umsCode);
//            UmsCode p_2 = getParentAreaNode(p_3);
//            UmsCode p_1 = getParentAreaNode(p_2);
            String parentAreaName = getParentAreaName(umsCode);
            m.put("id", umsCode.getId());
            m.put("codeName", umsCode.getCodeName());
            m.put("parentName", parentAreaName);
            m.put("fullName", parentAreaName + "-" + umsCode.getCodeName());
            list.add(m);
        }

        data = list;

        return "json";
    }

    private String getParentArea(String areaCode) {

        if (!StringUtils.hasText(areaCode)) {
            return null;
        }

        UmsCodeCriteria example = new UmsCodeCriteria();
        example.createCriteria().andTypeIdEqualTo(112).andIdEqualTo(areaCode);
        List<UmsCode> umsCodes = umsCodeService.selectByExample(example);

        return umsCodes.size() > 0 ? umsCodes.get(0).getParentId() : null;
    }


    private String getParentAreaName(UmsCode areaCode) {

        if (areaCode == null || !StringUtils.hasText(areaCode.getId())) {
            return null;
        }

        UmsCodeCriteria example = new UmsCodeCriteria();
        example.createCriteria().andTypeIdEqualTo(112).andIdEqualTo(areaCode.getId());
        List<UmsCode> umsCodes = umsCodeService.selectByExample(example);

        StringBuilder str = new StringBuilder();
        if (umsCodes.size() > 0) {

            Integer times = 0;
            List<String> p = new ArrayList<>();
            UmsCode parentNode = umsCodes.get(0);
            do {

                parentNode = getParentNode(parentNode);
                if (parentNode != null) {
                    p.add(parentNode.getCodeName());
                }
                times++;

            } while (parentNode != null && times < 5);

            for (int i = p.size(); i > 0; i--) {
                String s = p.get(i - 1);
                if (i == p.size()) {
                    str.append(s);
                    continue;
                }
                str.append("-").append(s);
            }

        }


        return str.toString();
    }

    private UmsCode getParentNode(UmsCode areaCode) {

        if (areaCode == null || !StringUtils.hasText(areaCode.getParentId())) {
            return null;
        }

        UmsCodeCriteria example = new UmsCodeCriteria();
        example.createCriteria().andTypeIdEqualTo(112).andIdEqualTo(areaCode.getParentId());
        List<UmsCode> umsCodes1 = umsCodeService.selectByExample(example);
        if (umsCodes1.size() > 0) {
            return umsCodes1.get(0);
        }

        return null;
    }


    private void setWorkArea(UmsJurorInfo info) {
        if (info == null) {
            return;
        }
        if (StringUtils.hasText(info.getWorkArea4())) {
            info.setWorkArea(info.getWorkArea4());
            return;
        }
        if (StringUtils.hasText(info.getWorkArea3())) {
            info.setWorkArea(info.getWorkArea3());
            return;
        }
        if (StringUtils.hasText(info.getWorkArea2())) {
            info.setWorkArea(info.getWorkArea2());
            return;
        }
        if (StringUtils.hasText(info.getWorkArea1())) {
            info.setWorkArea(info.getWorkArea1());
        }

    }

}
