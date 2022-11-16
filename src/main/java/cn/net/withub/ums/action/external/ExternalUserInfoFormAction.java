/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.external;

import cn.net.withub.ums.action.LoginAction;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.*;
import cn.net.withub.ums.service.statistics.XtptUserService;
import cn.net.withub.ums.util.*;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.collections.MapUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 编外人员表单
 *
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/external")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"}),
        @Result(name = "form", location = "/board/personnel/info/external_add2.jsp?isQuery=${isQuery}", type = "dispatcher"),
        @Result(name = "form3", location = "/board/personnel/info/externalEditForGbbzlx.jsp?isQuery=${isQuery}", type = "dispatcher")
})
public class ExternalUserInfoFormAction {

    @Autowired
    UmsGbbzlxInfoService umsGbbzlxInfoService;

    @Autowired
    private UmsExtEnterSrcTypeService enterSrcTypeService;

    @Autowired
    private UmsExternalJobTypeService externalJobTypeService;

    @Autowired
    private UmsUserInfoService userInfoService;

    @Autowired
    private UmsCourtService courtService;

    @Autowired
    private XtptUserService xtptUserService;

    @Autowired
    private UmsExternalUserInfoService externalUserInfoService;

    @Autowired
    private UmsExternalCompanyInfoService companyInfoService;

    @Autowired
    private UmsDepartmentService umsDepartmentService;

    @Autowired
    UmsUserOperationLogService umsUserOperationLogService; // 记录用户操作日志

    @Autowired
    LoginAction loginAction;

    private Object data;
    private Integer srcId;
    private Integer id;
    private List<UmsExtEnterSrcType> srcTypes;
    private Map<Integer, List<Node>> jobTypesMap; //树节点
    private Map<Integer, List<String>> jobTypeInputMap; //HTML标签

    private UmsUserInfo userInfo;

    private UmsExternalUserInfo info;

    private UmsExternalCompanyInfo company;

    private List<String> deptText;

    private String newid;
    private String userId;
    private String copyname;

    private Integer isQuery = 0;

    public String getCopyname() {
        return copyname;
    }

    public void setCopyname(String copyname) {
        this.copyname = copyname;
    }

    public Integer getIsQuery() {
        return isQuery;
    }

    public void setIsQuery(Integer isQuery) {
        this.isQuery = isQuery;
    }


    @Action("getEnterSrc")
    public String getEnterSrc() {

        data = enterSrcTypeService.findAll();
        return "json";

    }

    /**
     * 请求表单
     *
     * @return
     */
    @Action("form")
    public String form() {
        // 如果id没有值，说明是新增
        if (StringUtils.isEmpty(userId)) {
            newid = UUID.randomUUID().toString();
        } else { //修改
            userInfo = userInfoService.selectById(userId);
            info = externalUserInfoService.selectById(userId);
        }

        jobTypeInputMap = new HashMap<>();
        srcTypes = enterSrcTypeService.findAll();
        for (UmsExtEnterSrcType srcType : srcTypes) {
            List<String> labels = new ArrayList<>();
            List<UmsExternalJobType> root = externalJobTypeService.findRootBySrcId(srcType.getId());
            String type = "";
            boolean leaf = true;
            int textInputCount = 0;

            for (UmsExternalJobType t : root) {
                if (t.getNodeType() == 1) {
                    type = "select";
                } else if (t.getNodeType() == 2) {
                    type = "input";
                    labels.add(String.format("<!--%s-->", type));
                    labels.add(String.format("<div class='row'>"));
                    if (info != null && Objects.equals(info.getEnterSrc(), srcType.getId())) {
                        String dept = "";
                        if (textInputCount == 0) {
                            dept = info.getFormerDept();
                        } else if (textInputCount == 1) {
                            dept = info.getPresentDept();
                        }
                        labels.add(String.format("<div class='control-group span8'>"
                                + "<label class='control-label'><s>*</s>%s：</label>"
                                + "<div class='controls'>"
                                + "<input id='deptText%d-%d' name='deptText[%d]' type='text' placeholder='请输入%s' class='control-text' data-rules='{required:true}' value='%s'>\n"
                                + "</div></div>", t.getJobName(), textInputCount, t.getId(), textInputCount, t.getJobName(), dept));
                    } else {
                        labels.add(String.format("<div class='control-group span8'>"
                                + "<label class='control-label'><s>*</s>%s：</label>"
                                + "<div class='controls'>"
                                + "<input id='deptText%d-%d' name='deptText[%d]' type='text' placeholder='请输入%s' class='control-text' data-rules='{required:true}'>\n"
                                + "</div></div>", t.getJobName(), textInputCount, t.getId(), textInputCount, t.getJobName()));
                    }
                    labels.add(String.format("</div>"));
                    textInputCount++;
                }

                leaf &= t.getLeaf();
            }

            if (type.equals("select")) {
                labels.add(String.format("<!--%s-->", type));
                labels.add(String.format("<div class='row'>"
                        + "<div class='control-group span12'>"
                        + "<label class='control-label'><s>*</s>工作类型：</label>"
                        + "<div id='g%d' class='controls bui-form-group-select' data-type='type-%d'>", srcType.getId(), srcType.getId()));
                if (info != null && Objects.equals(info.getEnterSrc(), srcType.getId())) {
                    labels.add(String.format("<select id='s1-%d' class='input-normal' name='info.jobType1' data-rules='{required:true}' data-messages=\"{required:'请选择第一项'}\" value='%d' onChange='linkageChange(this)'></select>", srcType.getId(), info.getJobType1()));
                } else {
                    labels.add(String.format("<select id='s1-%d' class='input-normal' name='info.jobType1' data-rules='{required:true}' data-messages=\"{required:'请选择第一项'}\" onChange='linkageChange(this)'></select>", srcType.getId()));
                }
                if (!leaf) {
                    if (info != null && Objects.equals(info.getEnterSrc(), srcType.getId())) {
                        labels.add(String.format("<select id='s2-%d' class='input-normal' name='info.jobType2' data-rules='{select2_required:%d}' value='%d'></select>", srcType.getId(), srcType.getId(), info.getJobType2()));
                    } else {
                        labels.add(String.format("<select id='s2-%d' class='input-normal' name='info.jobType2' data-rules='{select2_required:%d}'></select>", srcType.getId(), srcType.getId()));
                    }
                }
                labels.add("</div></div>");

                //row end
                labels.add("</div>");

                if (!leaf) {
                    labels.add("<div class='row'><div class='control-group span12'><label class='control-label'>&nbsp;</label><div class='controls'><span class='auxiliary-text'>如果第二级没有选项，则说明不需要选择</span></div></div></div>");
                    //labels.add("<div class='tips tips-small tips-info'><span class='x-icon x-icon-small x-icon-info'><i class='icon icon-white icon-info'></i></span><div class='tips-content'>如果第二级没有选项，则说明不需要选择</div></div>");
                }

            }

            jobTypeInputMap.put(srcType.getId(), labels);
        }
        //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑工作类型↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

        return "form";
    }

    /**
     * 请求表单
     *
     * @return
     */
    @Action("form3")
    public String form3() {
        //如果id没有值，说明是新增
        if (StringUtils.isEmpty(userId)) {
            newid = UUID.randomUUID().toString();
        } else { //修改
            userInfo = userInfoService.selectById(userId);
            info = externalUserInfoService.selectById(userId);
        }

        jobTypeInputMap = new HashMap<>();
        srcTypes = enterSrcTypeService.findAll();
        for (UmsExtEnterSrcType srcType : srcTypes) {
            List<String> labels = new ArrayList<>();
            List<UmsExternalJobType> root = externalJobTypeService.findRootBySrcId(srcType.getId());
            String type = "";
            boolean leaf = true;
            int textInputCount = 0;

            for (UmsExternalJobType t : root) {
                if (t.getNodeType() == 1) {
                    type = "select";
                } else if (t.getNodeType() == 2) {
                    type = "input";
                    labels.add(String.format("<!--%s-->", type));
                    labels.add(String.format("<div class='row'>"));
                    if (info != null && Objects.equals(info.getEnterSrc(), srcType.getId())) {
                        String dept = "";
                        if (textInputCount == 0) {
                            dept = info.getFormerDept();
                        } else if (textInputCount == 1) {
                            dept = info.getPresentDept();
                        }
                        labels.add(String.format("<div class='control-group span8'>"
                                + "<label class='control-label'><s>*</s>%s：</label>"
                                + "<div class='controls'>"
                                + "<input id='deptText%d-%d' name='deptText[%d]' type='text' placeholder='请输入%s' class='control-text' data-rules='{required:true}' value='%s'>\n"
                                + "</div></div>", t.getJobName(), textInputCount, t.getId(), textInputCount, t.getJobName(), dept));
                    } else {
                        labels.add(String.format("<div class='control-group span8'>"
                                + "<label class='control-label'><s>*</s>%s：</label>"
                                + "<div class='controls'>"
                                + "<input id='deptText%d-%d' name='deptText[%d]' type='text' placeholder='请输入%s' class='control-text' data-rules='{required:true}'>\n"
                                + "</div></div>", t.getJobName(), textInputCount, t.getId(), textInputCount, t.getJobName()));
                    }
                    labels.add(String.format("</div>"));
                    textInputCount++;
                }

                leaf &= t.getLeaf();
            }

            if (type.equals("select")) {
                labels.add(String.format("<!--%s-->", type));
                labels.add(String.format("<div class='row'>"
                        + "<div class='control-group span12'>"
                        + "<label class='control-label'><s>*</s>工作类型：</label>"
                        + "<div id='g%d' class='controls bui-form-group-select' data-type='type-%d'>", srcType.getId(), srcType.getId()));
                if (info != null && Objects.equals(info.getEnterSrc(), srcType.getId())) {
                    labels.add(String.format("<select id='s1-%d' class='input-normal' name='info.jobType1' data-rules='{required:true}' data-messages=\"{required:'请选择第一项'}\" value='%d' onChange='linkageChange(this)'></select>", srcType.getId(), info.getJobType1()));
                } else {
                    labels.add(String.format("<select id='s1-%d' class='input-normal' name='info.jobType1' data-rules='{required:true}' data-messages=\"{required:'请选择第一项'}\" onChange='linkageChange(this)'></select>", srcType.getId()));
                }
                if (!leaf) {
                    if (info != null && Objects.equals(info.getEnterSrc(), srcType.getId())) {
                        labels.add(String.format("<select id='s2-%d' class='input-normal' name='info.jobType2' data-rules='{select2_required:%d}' value='%d'></select>", srcType.getId(), srcType.getId(), info.getJobType2()));
                    } else {
                        labels.add(String.format("<select id='s2-%d' class='input-normal' name='info.jobType2' data-rules='{select2_required:%d}'></select>", srcType.getId(), srcType.getId()));
                    }
                }
                labels.add("</div></div>");

                //row end
                labels.add("</div>");

                if (!leaf) {
                    labels.add("<div class='row'><div class='control-group span12'><label class='control-label'>&nbsp;</label><div class='controls'><span class='auxiliary-text'>如果第二级没有选项，则说明不需要选择</span></div></div></div>");
                    //labels.add("<div class='tips tips-small tips-info'><span class='x-icon x-icon-small x-icon-info'><i class='icon icon-white icon-info'></i></span><div class='tips-content'>如果第二级没有选项，则说明不需要选择</div></div>");
                }

            }

            jobTypeInputMap.put(srcType.getId(), labels);
        }
        //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑工作类型↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

        return "form3";
    }

    // 获取编外人员的
    @Action("getExternalUserInfo")
    public Object getExternalUserInfo() {
//        jobTypeInputMap = new HashMap<>();
//        srcTypes = enterSrcTypeService.findAll();
//        for (UmsExtEnterSrcType srcType : srcTypes) {
//            List<String> labels = new ArrayList<>();
//            List<UmsExternalJobType> root = externalJobTypeService.findRootBySrcId(srcType.getId());
//            String type = "";
//            boolean leaf = true;
//            int textInputCount = 0;
//
//            for (UmsExternalJobType t : root) {
//                if (t.getNodeType() == 1) {
//                    type = "select";
//                } else if (t.getNodeType() == 2) {
//                    type = "input";
//                    labels.add(String.format("<!--%s-->", type));
//                    labels.add(String.format("<div class='row'>"));
//                    if (info != null && Objects.equals(info.getEnterSrc(), srcType.getId())) {
//                        String dept = "";
//                        if (textInputCount == 0) {
//                            dept = info.getFormerDept();
//                        } else if (textInputCount == 1) {
//                            dept = info.getPresentDept();
//                        }
//                        labels.add(String.format("<div class='control-group span8'>"
//                                + "<label class='control-label'><s>*</s>%s：</label>"
//                                + "<div class='controls'>"
//                                + "<input id='deptText%d-%d' name='deptText[%d]' type='text' placeholder='请输入%s' class='control-text' data-rules='{required:true}' value='%s'>\n"
//                                + "</div></div>", t.getJobName(), textInputCount, t.getId(), textInputCount, t.getJobName(), dept));
//                    } else {
//                        labels.add(String.format("<div class='control-group span8'>"
//                                + "<label class='control-label'><s>*</s>%s：</label>"
//                                + "<div class='controls'>"
//                                + "<input id='deptText%d-%d' name='deptText[%d]' type='text' placeholder='请输入%s' class='control-text' data-rules='{required:true}'>\n"
//                                + "</div></div>", t.getJobName(), textInputCount, t.getId(), textInputCount, t.getJobName()));
//                    }
//                    labels.add(String.format("</div>"));
//                    textInputCount++;
//                }
//
//                leaf &= t.getLeaf();
//            }
//
//            if (type.equals("select")) {
//                labels.add(String.format("<!--%s-->", type));
//                labels.add(String.format("<div class='row'>"
//                        + "<div class='control-group span12'>"
//                        + "<label class='control-label'><s>*</s>工作类型：</label>"
//                        + "<div id='g%d' class='controls bui-form-group-select' data-type='type-%d'>", srcType.getId(), srcType.getId()));
//                if (info != null && Objects.equals(info.getEnterSrc(), srcType.getId())) {
//                    labels.add(String.format("<select id='s1-%d' class='input-normal' name='info.jobType1' data-rules='{required:true}' data-messages=\"{required:'请选择第一项'}\" value='%d' onChange='linkageChange(this)'></select>", srcType.getId(), info.getJobType1()));
//                } else {
//                    labels.add(String.format("<select id='s1-%d' class='input-normal' name='info.jobType1' data-rules='{required:true}' data-messages=\"{required:'请选择第一项'}\" onChange='linkageChange(this)'></select>", srcType.getId()));
//                }
//                if (!leaf) {
//                    if (info != null && Objects.equals(info.getEnterSrc(), srcType.getId())) {
//                        labels.add(String.format("<select id='s2-%d' class='input-normal' name='info.jobType2' data-rules='{select2_required:%d}' value='%d'></select>", srcType.getId(), srcType.getId(), info.getJobType2()));
//                    } else {
//                        labels.add(String.format("<select id='s2-%d' class='input-normal' name='info.jobType2' data-rules='{select2_required:%d}'></select>", srcType.getId(), srcType.getId()));
//                    }
//                }
//                labels.add("</div></div>");
//
//                //row end
//                labels.add("</div>");
//
//                if (!leaf) {
//                    labels.add("<div class='row'><div class='control-group span12'><label class='control-label'>&nbsp;</label><div class='controls'><span class='auxiliary-text'>如果第二级没有选项，则说明不需要选择</span></div></div></div>");
//                    //labels.add("<div class='tips tips-small tips-info'><span class='x-icon x-icon-small x-icon-info'><i class='icon icon-white icon-info'></i></span><div class='tips-content'>如果第二级没有选项，则说明不需要选择</div></div>");
//                }
//
//            }
//
//            jobTypeInputMap.put(srcType.getId(), labels);
//        }
        info = externalUserInfoService.selectById(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("info", info);
//        map.put("srcTypes", jobTypeInputMap);
        data = map;
        return "json";
    }

    /**
     * 树结构
     *
     * @return
     */
    @Action("nodes")
    public String nodes() {
        if (id == null || id == 0) {
            List<UmsExternalJobType> root = externalJobTypeService.findRootBySrcId(srcId);
            List<Node> nodes = new ArrayList<>();
            for (UmsExternalJobType t : root) {
                nodes.add(new Node(t));
            }
            data = nodes;
        } else {
            List<UmsExternalJobType> root = externalJobTypeService.findByPid(id);
            List<Node> nodes = new ArrayList<>();
            for (UmsExternalJobType t : root) {
                nodes.add(new Node(t));
            }
            data = nodes;
        }
        return "json";
    }

    @Action("save")
    public String save() {
        Map<String, Object> map = new HashMap<>();
        if (userInfo != null) {
            // 验证必填项
            if (userInfo.getCourtNo() == null || userInfo.getDepartment() == null || StringTools.isNullOrEmpty(userInfo.getFullname()) || StringTools.isNullOrEmpty(userInfo.getIdcard()) || userInfo.getGender() == null || userInfo.getNation() == null || userInfo.getPhysicalCondition() == null || userInfo.getMaritalStatus() == null || userInfo.getBirthday() == null || StringTools.isNullOrEmpty(userInfo.getHometown())) {
                map.put("success", false);
                map.put("msg", "内容验证未通过");
                data = map;
                return "json";
            }
            //将身份证中的字母统一为大写
            userInfo.setIdcard(userInfo.getIdcard().toUpperCase());
            if (deptText != null) {
                try {
                    info.setFormerDept(deptText.get(0));
                } catch (Exception e) {
                }
                try {
                    info.setPresentDept(deptText.get(1));
                } catch (Exception e) {
                }
            }

            // 保存前ums_user_info表数据
            UmsUserInfo umsUserInfoBeforeSave = userInfoService.selectById(userInfo.getId());
            // 通过反射获取前端传来的字段
            Field[] fields = userInfo.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                // 设置是否允许访问，不是修改原来的访问权限修饰词。
                fields[i].setAccessible(true);
                try {
                    if (null != fields[i].get(userInfo) && null != umsUserInfoBeforeSave) {
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
            if (null == umsUserInfoBeforeSave) {

            } else {
                userInfo = umsUserInfoBeforeSave;
            }

            // 下面的更新的userInfo，是前端传来的数据
            userInfo.setUsername(userInfo.getUsername().trim().replace(" ", "").replace("　", ""));
            userInfo.setFullname(userInfo.getFullname().trim().replace(" ", "").replace("　", ""));
            userInfo.setCourtStdNo(courtService.courtNo2CourtStdNo(userInfo.getCourtNo()));
            userInfo.setCourtCode(courtService.courtNo2CourtCode(userInfo.getCourtNo()));

            //查询标准部门名称
            UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
            criteria.createCriteria().andCourtCodeEqualTo(userInfo.getCourtCode()).andDeptNoEqualTo(userInfo.getDepartment());
            List<UmsDepartment> umsDepartments = umsDepartmentService.selectByExample(criteria);
            UmsDepartment umsDepartment = umsDepartments.get(0);
            userInfo.setDeptOrgCode(umsDepartment.getOrgCode());

            UmsUserInfoView user = SessionUtils.currentUser();
            userInfo.setUpdateUser(user.getFullname() + "@" + user.getCourtNoText());
            userInfo.setUpdateTime(new Date());
            //现在要求给人事系统存储、获取身份证证和手机号的信息对应加密、解密
            EncodeDecodeDataInfo.enCodeDataForUms(userInfo);

            int result = 0;
            try {
                if (info.getCompanyInfoId() == null || info.getCompanyInfoId() == 0) {
                    companyInfoService.insert(company);
                    info.setCompanyInfoId(company.getId());
                }
                // 新增
                if (StringTools.isNullOrEmpty(userInfo.getId())) {
                    // 测试UserNo统统为负数
                    userInfo.setUserNo((int) (Math.random() * -1000));
                    userInfo.setId(newid);
                    info.setUserId(newid);

                    if (userInfo.getPassword() != null) {
                        Md5PasswordEncoder Md5Checker = new Md5PasswordEncoder();
                        String salt = null;
                        userInfo.setSalt(salt);
                        String rPassword = Md5Checker.encodePassword(userInfo.getPassword(), salt);
                        userInfo.setPassword(rPassword);
                    }

                    // 启用
                    userInfo.setIsValid(1);

                    // 排序
                    if (userInfo.getSortNo() == null) {
                        userInfo.setSortNo(9999);
                    }

                    // 如果调离原因不为空 启用状态要设为0
                    if (info.getLeaveType() != null) {
                        // 停用
                        userInfo.setIsValid(0);
                    }

                    result += userInfoService.insert(userInfo);
                    result += externalUserInfoService.insert(info);

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
                        umsUserOperationLog.setOperationTypecode(1);
                        umsUserOperationLog.setOperationTypedetail("新增编外人员信息");
                        umsUserOperationLog.setOperationTime(userInfo.getUpdateTime());
                        umsUserOperationLog.setOperationIp(ip);
                        umsUserOperationLog.setOperationContent("新增编外人员信息");
                        umsUserOperationLogService.logUserOperation(umsUserOperationLog);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    // 是否更新ums_gbbzlx_info
                    boolean updateGbbzlxInfo = false;

                    // 如果调离原因不为空 启用状态要设为0
                    if (info.getLeaveType() != null) {
                        // 停用
                        userInfo.setIsValid(0);
                    } else {
                        // 如果前端没传userType，就用默认的
                        if (null == userInfo.getUserType()) {
                            userInfo.setUserType(2);
                        } else {
                            Integer userType = userInfo.getUserType();
                            // userType大于10，代表是调职或改编
                            if (userType > 10) {
                                userInfo.setIsValid(1);
                                if (userType > 20) {
                                    // 如果是改编，就要更新表
                                    updateGbbzlxInfo = true;
                                }
                                // 该方法就是用于编外
                                userInfo.setUserType(2);
                            }
                        }
                    }

                    info.setUserId(userInfo.getId());

                    // 这里用来获得哪些字段是改变了的
                    UmsUserInfo userInfo_original = userInfoService.selectById(userInfo.getId());
                    Map<String, String> map_dif = CompareUtil.compare(userInfo_original, userInfo);

                    // 更新
                    result += userInfoService.update(userInfo);

                    // 如果是改编为在编的业务，那么更新表ums_gbbzlx_info为”已完成编辑“
                    if (updateGbbzlxInfo) {
                        String changeUUID = ServletActionContext.getRequest().getParameter("changeUUID");
                        UmsGbbzlxInfo umsGbbzlxInfo = new UmsGbbzlxInfo();
                        umsGbbzlxInfo.setChangeuuid(changeUUID);
                        umsGbbzlxInfo.setState("已完成编辑");
                        umsGbbzlxInfoService.updateByPrimaryKeySelective(umsGbbzlxInfo);
                    }

                    UmsExternalUserInfo umsExternalUserInfo = externalUserInfoService.selectById(userInfo.getId());
                    if (umsExternalUserInfo == null) {
                        result += externalUserInfoService.insert(info);
                    } else {
                        result += externalUserInfoService.update(info);
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
                        umsUserOperationLog.setOperationTypecode(2);
                        umsUserOperationLog.setOperationTypedetail("修改编外人员信息");
                        umsUserOperationLog.setOperationTime(userInfo.getUpdateTime());
                        umsUserOperationLog.setOperationIp(ip);
                        umsUserOperationLog.setOperationContent("修改的内容:" + map_dif.toString());
                        umsUserOperationLogService.logUserOperation(umsUserOperationLog);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

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
        data = map;
        return "json";
    }

    @Action("copyInfo")
    public String copyInfo() {
        int result = 0;
        data = false;
        if (StringUtils.isBlankObject(copyname) && StringUtils.isBlankObject(userId)) {
            try {
                UmsUserInfo userInfo = userInfoService.selectById(userId);
                if (userInfo != null) {
                    String userName = copyname;
                    try {
                        //保存之前必须要检测用户名是否重复
                        loginAction.setUsername(userName);
                        loginAction.checkUserName();
                        Map<String,Object> datax = (Map<String,Object>)loginAction.getData();
                        if(org.springframework.util.StringUtils.hasText(MapUtils.getString(datax,"UserNameCheck"))
                         && MapUtils.getString(datax,"UserNameCheck").equalsIgnoreCase("true")){
                            userName = MapUtils.getString(datax,"username");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        return "json";
                    }
                    String newId = UUID.randomUUID().toString();
                    userInfo.setId(newId);
                    userInfo.setFullname(copyname);
                    userInfo.setUsername(userName);
                    userInfo.setUpdateTime(new Date());
                    UserForXtpt user = xtptUserService.searchByUUIDForXtpt(userId);
                    if (user != null) {
                        user.setUuid(newId);
                        user.setFullname(copyname);
                        user.setUsername(userName);
                        user.setId(null);
                        xtptUserService.insertForXtptN(user);
                        result = userInfoService.insertOnly(userInfo);
                        this.data = result > 0 ? true : false;
                    } else {
                        result = userInfoService.insert(userInfo);
                        this.data = result > 0 ? true : false;
                    }
//                    XtptUser xtptUser =  null;
//                    xtptUserService.insert(xtptUser);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            return "json";

        }
        return "json";
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public List<UmsExtEnterSrcType> getSrcTypes() {
        return srcTypes;
    }

    public void setSrcTypes(List<UmsExtEnterSrcType> srcTypes) {
        this.srcTypes = srcTypes;
    }

    public Map<Integer, List<Node>> getJobTypesMap() {
        return jobTypesMap;
    }

    public void setJobTypesMap(Map<Integer, List<Node>> jobTypesMap) {
        this.jobTypesMap = jobTypesMap;
    }

    public Integer getSrcId() {
        return srcId;
    }

    public void setSrcId(Integer srcId) {
        this.srcId = srcId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Integer, List<String>> getJobTypeInputMap() {
        return jobTypeInputMap;
    }

    public void setJobTypeInputMap(Map<Integer, List<String>> jobTypeInputMap) {
        this.jobTypeInputMap = jobTypeInputMap;
    }

    public UmsUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UmsUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UmsExternalUserInfo getInfo() {
        return info;
    }

    public void setInfo(UmsExternalUserInfo info) {
        this.info = info;
    }

    public UmsExternalCompanyInfo getCompany() {
        return company;
    }

    public void setCompany(UmsExternalCompanyInfo company) {
        this.company = company;
    }

    public List<String> getDeptText() {
        return deptText;
    }

    public void setDeptText(List<String> deptText) {
        this.deptText = deptText;
    }

    public String getNewid() {
        return newid;
    }

    public void setNewid(String newid) {
        this.newid = newid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="节点内部类">
    public class Node {

        private String id;
        private String text;
        private UmsExternalJobType item;
        private boolean leaf;
        private List<Node> children;

        public Node(UmsExternalJobType item) {
            this.item = item;
            this.id = item.getId().toString();
            this.text = item.getJobName();
            this.leaf = item.getLeaf();
            //异步加载，不需要递归查询
//            if (!this.leaf) {
//                List<UmsExternalJobType> childs = externalJobTypeService.findByPid(item.getId());
//                children = new ArrayList<>();
//                for (UmsExternalJobType child : childs) {
//                    children.add(new Node(child));
//                }
//            }
        }

        //<editor-fold defaultstate="collapsed" desc="Node属性方法">
        @JSON(serialize = false)
        public UmsExternalJobType getItem() {
            return item;
        }

        public void setItem(UmsExternalJobType item) {
            this.item = item;
        }

        @JSON(serialize = false)
        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }

        public boolean isLeaf() {
            return leaf;
        }

        public void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

//</editor-fold>
    }
    //</editor-fold>
}
