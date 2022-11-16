/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.userinfo;

import cn.net.withub.ums.action.rmpsSync.RmpsyDataSync;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.UmsUserStatusMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.*;
import cn.net.withub.ums.service.statistics.XtptUserService;
import cn.net.withub.ums.util.*;
import com.opensymphony.xwork2.ActionContext;
import org.apache.ibatis.session.RowBounds;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/userinfo")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"}),
        @Result(name = "edit", location = "/board/personnel/info/user2_edit.jsp")
})
public class UserInfoAction {
    @Autowired
    UmsGbbzlxInfoService umsGbbzlxInfoService;
    @Autowired
    private UmsUserInfoService userInfoService;
    @Autowired
    private UmsCourtService courtService;
    @Autowired
    private UmsPhotoService photoService;
    @Autowired
    private XtptUserService xtptUserService;
    @Autowired
    private XtptTUserService xtptTUserService;
    @Autowired
    private UmsExternalUserInfoService externalUserInfoService;
    @Autowired
    private UmsDepartmentService umsDepartmentService;
    @Autowired
    private UmsAttachedTableService attachedTableService;
    @Autowired
    RmpsyDataSync rmpsyDataSync;
    @Autowired
    UmsUserOperationLogService umsUserOperationLogService; // 记录用户操作日志
    @Autowired
    UmsUserStatusMapper umsUserStatusMapper;
    private UmsUserInfo userInfo;
    private String id;
    private Integer enableType;
    private Object data;
    private UmsChangeJob umsChangeJob;
    private Integer apply_court_no;
    private Integer start;
    private Integer limit;
    private String fullname;
    private String img;
    private List allId;
    private Map<String, String> fields = new HashMap<>();

    @Action("save")
    public String save() {
        Map<String, Object> map = new HashMap<>();
        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);

        if (userInfo != null) {
            // 必填项不能为空
            if (
                    userInfo.getCourtNo() == null ||
                    userInfo.getDepartment() == null ||
                    StringTools.isNullOrEmpty(userInfo.getFullname()) ||
                    StringTools.isNullOrEmpty(userInfo.getIdcard()) ||
                    userInfo.getGender() == null ||
                    userInfo.getNation() == null ||
                    userInfo.getPhysicalCondition() == null ||
                    userInfo.getMaritalStatus() == null ||
                    userInfo.getBirthday() == null ||
                    StringTools.isNullOrEmpty(userInfo.getHometown()) ||
                    userInfo.getWorkDate() == null ||
                    userInfo.getEnterDate() == null ||
                    userInfo.getExtraSeniority() == null ||
                    userInfo.getDeductionSeniority() == null ||
                    userInfo.getBeforeCourtWorkYear() == null ||
                    userInfo.getEnterSource() == null ||
                    userInfo.getEnterWay() == null
            ) {
                map.put("success", false);
                map.put("msg", "内容验证未通过");
                data = map;
                return "json";
            }

            //将身份证号的字母统一转为大写
            userInfo.setIdcard(userInfo.getIdcard().toUpperCase());
            //判断username是否处理了防止sql注入拦截的问题
            if(userInfo.getUsername() != null && userInfo.getUsername().endsWith("/")){
                userInfo.setUsername(userInfo.getUsername().replace("/",""));
            }
            // ---------------------查询之前数据start
            // 保存前ums_user_info表数据
            UmsUserInfo umsUserInfoBeforeSave = userInfoService.selectById(userInfo.getId());
            // 通过反射获取前端传来的字段
            Field[] fields = userInfo.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                // 设置是否允许访问，不是修改原来的访问权限修饰词。
                fields[i].setAccessible(true);
                try {
                    // 排除前端页面 非必填项且是下拉框的20200107 避免这些字段在修改的时候改为空,后台不能修改的情况发生
                    String fieldName = fields[i].getName();
                    String[] df = {"positionType", "assign", "partyOffice", "proCert", "leaveReason", "leaveDestination"};
                    List dfList = Arrays.asList(df);
                    if ((dfList.contains(fieldName) || null != fields[i].get(userInfo)) && null != umsUserInfoBeforeSave) {
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
            // ---------------------查询之前数据end

            // 下面的更新的userInfo，是前端传来的数据
            userInfo.setUsername(userInfo.getUsername().trim().replace(" ", "").replace("　", ""));
            userInfo.setFullname(userInfo.getFullname().trim().replace(" ", "").replace("　", ""));
            userInfo.setCourtStdNo(courtService.courtNo2CourtStdNo(userInfo.getCourtNo()));
            userInfo.setCourtCode(courtService.courtNo2CourtCode(userInfo.getCourtNo()));

            // 查询标准部门名称
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
                // 新增ums_user_info等两张表
                if (StringTools.isNullOrEmpty(userInfo.getId())) {
                    // 新增
                    // 测试UserNo统统为负数
                    userInfo.setUserNo((int) (Math.random() * -1000));
                    userInfo.setId(id);

                    if (userInfo.getPassword() != null) {
                        Md5PasswordEncoder Md5Checker = new Md5PasswordEncoder();
                        String salt = null;
                        userInfo.setSalt(salt);
                        String rPassword = Md5Checker.encodePassword(userInfo.getPassword(), salt);
                        userInfo.setPassword(rPassword);
                    }

                    // 启用
                    userInfo.setIsValid(1);

                    // 排序号
                    if (userInfo.getSortNo() == null) {
                        userInfo.setSortNo(9999);
                    }

                    // 如果调离原因不为空 启用状态要设为0
//                    if (userInfo.getLeaveReason() != null) {
                        // 停用
//                        userInfo.setIsValid(0);
//                    }

                    // 新增两张表
                    result = userInfoService.insert(userInfo);

                    //-----------start-------------记录日志
                    String ip = IpTools.getIpAddress(request);
                    UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                    UmsUserOperationLog umsUserOperationLog = new UmsUserOperationLog();
                    umsUserOperationLog.setId(UUID.randomUUID().toString());
                    umsUserOperationLog.setOperationUsername(user_.getFullname());
                    umsUserOperationLog.setModifiedUserid(userInfo.getId());
                    umsUserOperationLog.setOperationTypecode(1);
                    umsUserOperationLog.setOperationTypedetail("新增在编人员信息");
                    umsUserOperationLog.setOperationTime(userInfo.getUpdateTime());
                    umsUserOperationLog.setOperationIp(ip);
                    umsUserOperationLog.setOperationContent("新增在编人员信息");
                    umsUserOperationLogService.logUserOperation(umsUserOperationLog);
                    //-----------end-------------记录日志

                } else {
                    // 修改ums_user_info等两张表
                    // 为了封住更改法院时 附件表没有更新法院编码的口子 要先查一次之前的法院编码进行比较
                    UmsUserInfoCriteria criteria_user = new UmsUserInfoCriteria();
                    criteria_user.createCriteria().andIdEqualTo(userInfo.getId());
                    UmsUserInfo umsUserInfo = userInfoService.search(criteria_user, null).get(0);

                    // 是否更新ums_gbbzlx_info
                    boolean updateGbbzlxInfo = false;

                    // 如果调离原因不为空 启用状态要设为0
                    if (userInfo.getLeaveReason() != null && userInfo.getUserType() < 10) {
                        // 停用
//                        userInfo.setIsValid(0);
                        // 特殊处理 如果调离原因是离退休的话 会涉及返聘 这类人员不停用
//                        if (userInfo.getLeaveReason() == 5) {
//                            if (umsUserInfo.getIsValid() != null && umsUserInfo.getIsValid() == 1 && umsUserInfo.getLeaveReason() != null && umsUserInfo.getLeaveReason() == 5) {
//                                userInfo.setIsValid(null);
//                            }
//                        }
                    } else {
                        // 如果前端没传userType，就用默认的
                        if (null == userInfo.getUserType()) {
                            userInfo.setUserType(1);
                        } else {
                            Integer userType = userInfo.getUserType();
                            // userType大于10，代表是调职或改编
                            if (userType > 10) {
                                userInfo.setIsValid(1);
                                if (userType > 20) {
                                    // 如果是改编，就要更新表
                                    updateGbbzlxInfo = true;
                                }
                                // 该方法就是用于在编
                                userInfo.setUserType(1);
                            }
                        }
                    }

                    // 改动之前的数据
                    UmsUserInfo umsUserInfo_original = userInfoService.selectById(userInfo.getId());
                    // 这里用来获得哪些字段是改变了的
                    int isValid_orig = umsUserInfo_original.getIsValid();
                    Map<String, String> map_dif = CompareUtil.compare(umsUserInfo_original, userInfo);

                    // xtpt 必要要有排序号
                    if (userInfo.getSortNo() == null) {
                        userInfo.setSortNo(9999);
                    }
                    // 更新两张表
                    result = userInfoService.update(userInfo);

                    // 如果是改编为在编的业务，那么更新表ums_gbbzlx_info为”已完成编辑“
                    if (updateGbbzlxInfo) {
                        String changeUUID = ServletActionContext.getRequest().getParameter("changeUUID");
                        UmsGbbzlxInfo umsGbbzlxInfo = new UmsGbbzlxInfo();
                        umsGbbzlxInfo.setChangeuuid(changeUUID);
                        umsGbbzlxInfo.setState("已完成编辑");
                        umsGbbzlxInfoService.updateByPrimaryKeySelective(umsGbbzlxInfo);
                    }

                    //-----------start-------------记录日志
                    try {
                        UmsUserOperationLog umsUserOperationLog = new UmsUserOperationLog();
//                        ActionContext ctx = ActionContext.getContext();
//                        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
                        String ip = IpTools.getIpAddress(request);
                        UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                        if (user_ != null) {
                            umsUserOperationLog.setOperationUsername(user_.getFullname());
                        }
                        if (isValid_orig == 0 && userInfo.getIsValid() == 1) {
                            // 代表是申请调职后的编辑启用
                            umsUserOperationLog.setOperationTypecode(17);
                            umsUserOperationLog.setOperationTypedetail("处理调职人员信息时的编辑");
                        } else {
                            umsUserOperationLog.setOperationTypecode(2);
                            umsUserOperationLog.setOperationTypedetail("修改在编人员信息");
                        }
                        umsUserOperationLog.setId(UUID.randomUUID().toString());
                        umsUserOperationLog.setModifiedUserid(userInfo.getId());
                        umsUserOperationLog.setOperationTime(userInfo.getUpdateTime());
                        umsUserOperationLog.setOperationIp(ip);
                        umsUserOperationLog.setOperationContent("修改的内容:" + map_dif.toString());
                        umsUserOperationLogService.logUserOperation(umsUserOperationLog);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //-----------end-------------记录日志

                    // 批量更新该人员涉及法院的表cn/net/withub/ums/action/userinfo/UserInfoAttachedViewsAction.java:202
                    if (result > 0 && umsUserInfo.getCourtNo() != null && userInfo.getCourtNo() != null && !Objects.equals(umsUserInfo.getCourtNo(), userInfo.getCourtNo())) {
                        attachedTableService.AttachmentCourtUpdate(umsUserInfo.getId(), userInfo.getCourtNo());
                    }
                }
                String userStatusCode = request.getParameter("userStatus");
                String[] userStatusCodeList =  request.getParameterMap().get("userStatus[]");
                String userId = userInfo.getId();
                String ip = IpTools.getIpAddress(request);
                UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                //需要录入的身份信息
                List<UmsUserStatus> insertList = new ArrayList<>();
                //原有的身份信息
                List<UmsUserStatus> userstatusList = umsUserStatusMapper.selectByUserId(userId);

                Map<String,UmsUserStatus> umsUserStatusMap = new HashMap<>();
                //将原有的身份信息装为map类型,  便于后面好比较
                for (UmsUserStatus userStatus : userstatusList) {
                    umsUserStatusMap.put(userStatus.getStatusCode(),userStatus);
                }

                if(!StringUtils.isEmpty(userStatusCode)){//当userStatusCode为空时, 表示有可能存在多个身份信息或没有身份信息
                    if(!umsUserStatusMap.containsKey(userStatusCode)){
                        //需要先删除原有的身份信息
                        umsUserStatusMapper.deleteByUserId(userId);
                        UmsUserStatus umsUserStatus = new UmsUserStatus();
                        umsUserStatus.setAddIp(ip);
                        umsUserStatus.setAddUser(user_.getFullname());
                        umsUserStatus.setStatusCode(userStatusCode);
                        umsUserStatus.setTypeId(10007);
                        umsUserStatus.setUserId(userId);
                        insertList.add(umsUserStatus);
                    }
                }else if(userStatusCodeList != null && userStatusCodeList.length>0){//当userStatusCodeList不为空时,表示有多个身份信息
                    //记录需要保留的身份编码
                    List<String> codeList = new ArrayList<>();
                    for (String StatusCode : userStatusCodeList) {
                        if(!umsUserStatusMap.containsKey(StatusCode)){
                            UmsUserStatus umsUserStatus = new UmsUserStatus();
                            umsUserStatus.setAddIp(ip);
                            umsUserStatus.setAddUser(user_.getFullname());
                            umsUserStatus.setStatusCode(StatusCode);
                            umsUserStatus.setTypeId(10007);
                            umsUserStatus.setUserId(userId);
                            insertList.add(umsUserStatus);
                        }else{
                            codeList.add(StatusCode);
                        }
                    }
                    //先删除不需要保留的其他身份信息
                    if(codeList.size()>0){
                        umsUserStatusMapper.deleteByUserIdAndNotCodeList(userId,codeList);
                    }
                }else{//表示没有身份信息
                    umsUserStatusMapper.deleteByUserId(userId);
                }
                if(insertList.size()>0){
                    umsUserStatusMapper.insertList(insertList);
                }


                map.put("success", true);
                map.put("msg", "保存成功！");
            } catch (Exception e) {
                e.printStackTrace();
                map.put("success", false);
                map.put("msg", e.getMessage());
            } finally {
                map.put("result", result);
            }

        }
        data = map;
        return "json";
    }

    @Action("updateTel")
    public String updateTel() {
        Map<String, Object> map = new HashMap<>();
        data = map;
        if (userInfo != null) {
            int i = userInfoService.updateByPrimaryKeySelective(userInfo);
            map.put("success", i > 0);
        }
        return "json";
    }

    @Action("edit")
    public String editUserinfo() {
        //userInfo = userInfoService.selectById(id);
        return "edit";
    }

    @Action("one")
    public String selectById() {
        //现在要求给人事系统存储、获取身份证证和手机号的信息对应加密、解密
        UmsUserInfo umsUserInfo = userInfoService.selectById(id);
        EncodeDecodeDataInfo.deCodeDataForUms(umsUserInfo);
        Map<String, Object> map = entityToMap(umsUserInfo);
        UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        List<UmsUserStatus> userStatuses = umsUserStatusMapper.selectByUserId(id);
        map.put("userStatus",userStatuses);
        if (fields != null && fields.size() > 0) {
            Map<String, Object> map1 = attachedTableService.selectDataSetView(fields, id);
            map.put("otherfield", map1);
        }
        map.put("operator", u);
        data = map;
        return "json";
    }


    @Action("enabled")
    public String enabled() {

        Map<String, Object> map = new HashMap<>();
        data = map;
//        if (userInfo != null && userInfo.getId() != null && userInfo.getIsValid() != null) {
//            try {
//
//                //先查找xtpt 是否存在
//                XtptTUserExample example = new XtptTUserExample();
//                example.createCriteria().andUuidEqualTo(userInfo.getId());
//                List<XtptTUser> list = xtptTUserService.selectByExample(example);
//                if (userInfo.getIsValid() == 0) {
//                    if (list != null && list.size() > 0) {
//                        XtptTUser l = list.get(0);
//                        l.setIsValid(userInfo.getIsValid());
//                        l.setEnabled(false);
//                        xtptTUserService.update(l);
//                    }
//                } else if (userInfo.getIsValid() == 1) {
//                    //离开原因置为空
//                    UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
//                    criteria.createCriteria().andIdEqualTo(userInfo.getId());
//                    UmsUserInfo umsUserInfo = userInfoService.search(criteria, null).get(0);
//                    if (umsUserInfo.getUserType() == 1) {
//                        //如果是进行返聘操作 要把状态置为启用 并且不把调离原因置为空 人员类型要改为编外的人员
//                        if (enableType != null && enableType == 1) {
//                            userInfo.setUserType(2);
//                            userInfo.setIsValid(1);
//                        } else {
//                            userInfo.setLeaveReason(-1);
//                        }
//                    } else if (umsUserInfo.getUserType() == 2) {
//                        UmsExternalUserInfo info = externalUserInfoService.selectById(userInfo.getId());
//                        info.setLeaveType(-1);
//                        externalUserInfoService.updateEnabled(info);
//                    } else if (umsUserInfo.getUserType() == 3) {
//                        userInfo.setC_ps_mzyy(-1);
//                    }
//                    if (list != null && list.size() > 0) {
//                        XtptTUser l = list.get(0);
//                        l.setIsValid(userInfo.getIsValid());
//                        l.setEnabled(true);
//                        //返聘
//                        if (enableType != null && enableType == 1) {
//                            l.setUserType(userInfo.getUserType());
//                        }
//
//                        xtptTUserService.update(l);
//                    } else {
//                        throw new Exception("无对应信息");
//                    }
//                }
//                i = userInfoService.updateEnabled(userInfo);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            //人民陪审员信息同步
//            if ("1".equals(rmpsyDataSync.isSynchronization)) {
//                ActionContext context = ActionContext.getContext();
//                Map session = context.getSession();
//                UmsUserInfoView czr = (UmsUserInfoView) session.get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
//                rmpsyDataSync.userSync(userInfo.getId(), czr);
//            }
//        }
        boolean b = false;
        try {
            b = doEnable();

            if (userInfo.getIsValid() == 1) {
                //离开原因置为空
                UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
                criteria.createCriteria().andIdEqualTo(userInfo.getId());
                UmsUserInfo umsUserInfo = userInfoService.search(criteria, null).get(0);
                if (umsUserInfo.getUserType() == 1) {
                    try {//记录用户操作记录
                        UmsUserOperationLog umsUserOperationLog = new UmsUserOperationLog();// 记录操作日志
                        ActionContext ctx = ActionContext.getContext();
                        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
                        String ip = IpTools.getIpAddress(request);
                        UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                        if (user_ != null) {
                            umsUserOperationLog.setOperationUsername(user_.getFullname());
                        }
                        umsUserOperationLog.setId(UUID.randomUUID().toString());
                        umsUserOperationLog.setModifiedUserid(userInfo.getId());
                        umsUserOperationLog.setOperationTypecode(5);
                        umsUserOperationLog.setOperationTypedetail("启用在编人员");//记录用户操作日志
                        umsUserOperationLog.setOperationContent("启用在编人员");
                        umsUserOperationLog.setOperationTime(new Date());
                        umsUserOperationLog.setOperationIp(ip);
                        umsUserOperationLogService.logUserOperation(umsUserOperationLog);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("success", b);
        return "json";
    }

    public boolean doEnable() throws Exception {
        int i = 0;
        UmsUserOperationLog umsUserOperationLog = new UmsUserOperationLog();// 记录操作日志
        if (userInfo != null && userInfo.getId() != null && userInfo.getIsValid() != null) {
            // 先查找xtpt 是否存在
            XtptTUserExample example = new XtptTUserExample();
            example.createCriteria().andUuidEqualTo(userInfo.getId());
            List<XtptTUser> list = xtptTUserService.selectByExample(example);
            // 查询原来的 ums_user_info 信息
            UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
            criteria.createCriteria().andIdEqualTo(userInfo.getId());
            List<UmsUserInfo> search = userInfoService.search(criteria, null);
            if (search == null || search.size() != 1) {
                return false;
            }
            UmsUserInfo umsUserInfo = search.get(0);
            // 合并数据
            if (userInfo.getIsValid() == 0) {
                // 如果是进行返聘操作  人员类型要改为在编的人员
                if (enableType != null && enableType == 1) {
                    userInfo.setUserType(1);
                }
                if (list != null && list.size() > 0) {
                    XtptTUser l = list.get(0);
                    l.setIsValid(userInfo.getIsValid());
                    l.setEnabled(false);
                    if (enableType != null && enableType == 1) {
                        l.setUserType(userInfo.getUserType());
                    }
                    if (userInfo.getLeaveReason() != null) {
                        l.setLeaveReason(userInfo.getLeaveReason());
                    }
                    if (userInfo.getLeaveDate() != null) {
                        l.setLeaveDate(userInfo.getLeaveDate());
                    }
                    if (userInfo.getLeaveDestination() != null) {
                        l.setLeaveDestination(userInfo.getLeaveDestination());
                    }
                    xtptTUserService.updateAll(l);
                    if (list.get(0).getUserType() == 2) {
                        umsUserOperationLog.setOperationTypecode(3);
                        umsUserOperationLog.setOperationContent("停用编外人员信息");
                        umsUserOperationLog.setOperationTypedetail("停用编外人员信息");
                    }
                    if (list.get(0).getUserType() == 3) {
                        umsUserOperationLog.setOperationTypecode(4);
                        umsUserOperationLog.setOperationContent("停用人民陪审员");
                        umsUserOperationLog.setOperationTypedetail("停用人民陪审员");
                    }
                } else {
                    // 存在一种情况：一个人员在表ums_user_info中有数据，在表xtpt_t_user无数据。
                    // 在这种情况下，原来，必须先更新表xtpt_t_user,再更新表ums_user_info。
                    // 现在改为，如果只在表ums_user_info中有数据，也允许停启用该人员。所以，注释掉了下面代码。
//                    throw new Exception("无对应信息");
                }
            } else if (userInfo.getIsValid() == 1) {
                // 离开原因置为空
                if (umsUserInfo.getUserType() == 1) {
                    // 如果是进行返聘操作 要把状态置为启用 并且不把调离原因置为空 人员类型要改为编外的人员
                    if (enableType != null && enableType == 1) {
                        userInfo.setUserType(2);
                        userInfo.setIsValid(1);
                    } else {
                        userInfo.setLeaveReason(-1);
                        userInfo.setLeaveDate(null);
                        userInfo.setLeaveDestination(-1);
                    }
                } else if (umsUserInfo.getUserType() == 2) {
                    UmsExternalUserInfo info = externalUserInfoService.selectById(userInfo.getId());
                    info.setLeaveType(-1);
                    externalUserInfoService.updateEnabled(info);
                    umsUserOperationLog.setOperationTypecode(6);
                    umsUserOperationLog.setOperationTypedetail("启用编外人员");//记录用户操作日志
                    umsUserOperationLog.setOperationContent("启用编外人员");
                } else if (umsUserInfo.getUserType() == 3) {
                    userInfo.setCPsMzyy(-1);
                    umsUserOperationLog.setOperationTypecode(7);
                    umsUserOperationLog.setOperationTypedetail("启用人民陪审员");//记录用户操作日志
                    umsUserOperationLog.setOperationContent("启用人民陪审员");
                }
                if (list != null && list.size() > 0) {
                    XtptTUser l = list.get(0);
                    l.setIsValid(userInfo.getIsValid());
                    l.setEnabled(true);
                    l.setLeaveDate(null);
                    l.setLeaveDestination(null);
                    l.setLeaveReason(null);
                    //返聘
                    if (enableType != null && enableType == 1) {
                        l.setUserType(userInfo.getUserType());
                    }
                    xtptTUserService.updateAll(l);
                } else {
                    throw new Exception("无对应信息");
                }
            } else if (userInfo.getIsValid() == 3) {
                umsUserOperationLog.setOperationTypecode(91);
                umsUserOperationLog.setOperationTypedetail("保留系统登录");//记录用户操作日志
                umsUserOperationLog.setOperationContent("保留系统登录");
                if (list != null && list.size() > 0) {
                    XtptTUser l = list.get(0);
                    //把xtpt的状态置为可用
                    l.setIsValid(1);
                    l.setEnabled(true);
                    xtptTUserService.updateAll(l);
                } else {
                    throw new Exception("无对应信息");
                }
            }
            userInfo.setCourtNo(umsUserInfo.getCourtNo());
            i = userInfoService.updateEnabled(userInfo);
            if (list.get(0).getUserType() == 11 || list.get(0).getUserType() == 1 || list.get(0).getUserType() == 22 || list.get(0).getUserType() == 12) {
            } else {
                try {//记录用户操作记录
                    ActionContext ctx = ActionContext.getContext();
                    HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
                    String ip = IpTools.getIpAddress(request);
                    UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                    if (user_ != null) {
                        umsUserOperationLog.setOperationUsername(user_.getFullname());
                    }
                    umsUserOperationLog.setId(UUID.randomUUID().toString());
                    umsUserOperationLog.setModifiedUserid(userInfo.getId());
                    umsUserOperationLog.setOperationTime(new Date());
                    umsUserOperationLog.setOperationIp(ip);
                    umsUserOperationLogService.logUserOperation(umsUserOperationLog);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 人民陪审员信息同步
            if ("1".equals(rmpsyDataSync.isSynchronization)) {
                ActionContext context = ActionContext.getContext();
                Map session = context.getSession();
                UmsUserInfoView czr = (UmsUserInfoView) session.get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                rmpsyDataSync.userSync(userInfo.getId(), czr);
            }
        }
        return i > 0;
    }

    @Action("selectChangeJob")
    public String selectChangeJob() {
        List<UmsChangeJob> ma = null;
        int total = 0;
        RowBounds rowBounds = null;
        if (start == null || limit == null) {
            rowBounds = new RowBounds(0, 20);
        } else {
            rowBounds = new RowBounds(start, limit);
        }
        try {
            UmsChangeJob umscj = new UmsChangeJob();
            if (id != null) {
                umscj.setUser_id(id);
            }
            umscj.setOld_court_no(apply_court_no);
            umscj.setUser_name(fullname);
            ma = userInfoService.selectChangeJob(umscj, rowBounds);
            total = userInfoService.countChangeJob(umscj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        if (ma.size() > 0) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        map.put("rows", ma);
        map.put("results", total);
        data = map;
        return "json";
    }

    @Action("insertChangeJob")
    public String insertChangeJob() {

        UmsDepartmentKey umsDepartmentKey = new UmsDepartmentKey();
        umsDepartmentKey.setCourtNo(umsChangeJob.getNew_court_no());
        umsDepartmentKey.setDeptNo(umsChangeJob.getNew_dept_no());

        UmsDepartment umsDepartment = umsDepartmentService.selectByPrimaryKey(umsDepartmentKey);
        umsChangeJob.setNew_court_code(umsDepartment.getCourtCode());
        umsChangeJob.setNew_dept_org_code(umsDepartment.getOrgCode());
        umsChangeJob.setNew_court_std_no(umsDepartment.getCourtStdNo());

        List<UmsDepartment> list = umsDepartmentService.selectByCourtNo(umsChangeJob.getNew_court_no());
        umsChangeJob.setNew_court_text(list.get(0).getCourtShortName());
        umsChangeJob.setState("申请中");

        int i = userInfoService.insertJobById(umsChangeJob);

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
            umsUserOperationLog.setModifiedUserid(umsChangeJob.getUser_id());
            umsUserOperationLog.setOperationTypecode(8);
            if (userInfoService.selectById(umsChangeJob.getUser_id()).getUserType() == 1) {
                umsUserOperationLog.setOperationTypedetail("申请调职在编人员");
            }
            if (userInfoService.selectById(umsChangeJob.getUser_id()).getUserType() == 2) {
                umsUserOperationLog.setOperationTypedetail("申请调职编外人员");
            }
            umsUserOperationLog.setOperationTime(new Date());
            umsUserOperationLog.setOperationIp(ip);
            umsUserOperationLog.setOperationContent("调职的内容:" + umsChangeJob.getOld_court_text() + "->" + umsChangeJob.getNew_court_text() + " || " + umsChangeJob.getOld_dep_text() + "->" + umsChangeJob.getNew_dept_text());
            umsUserOperationLogService.logUserOperation(umsUserOperationLog);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("success", i > 0 ? true : false);
        map.put("msg", "申请成功");
        data = map;
        return "json";
    }

    @Action("updateChangeJob")
    public String updateChangeJob() {

        if (umsChangeJob.getState().equals("已同意")) {
            try {//记录用户操作记录
                UmsUserOperationLog umsUserOperationLog = new UmsUserOperationLog();
                ActionContext ctx = ActionContext.getContext();
                HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
                String ip = IpTools.getIpAddress(request);
                UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                if (user_ != null) {
                    umsUserOperationLog.setOperationUsername(user_.getFullname());
                }

                umsUserOperationLog.setId(UUID.randomUUID().toString());
                umsUserOperationLog.setModifiedUserid(umsChangeJob.getUser_id());
                umsUserOperationLog.setOperationTypecode(9);
                umsUserOperationLog.setOperationTypedetail(umsChangeJob.getState() + "调职");
                umsUserOperationLog.setOperationTime(new Date());
                umsUserOperationLog.setOperationIp(ip);
                umsUserOperationLog.setOperationContent(umsChangeJob.getState() + "该人员调职");
                umsUserOperationLogService.logUserOperation(umsUserOperationLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (umsChangeJob.getState().equals("已拒绝")) {
            try {//记录用户操作记录
                UmsUserOperationLog umsUserOperationLog = new UmsUserOperationLog();
                ActionContext ctx = ActionContext.getContext();
                HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
                String ip = IpTools.getIpAddress(request);
                UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                if (user_ != null) {
                    umsUserOperationLog.setOperationUsername(user_.getFullname());
                }

                umsUserOperationLog.setId(UUID.randomUUID().toString());
                umsUserOperationLog.setModifiedUserid(umsChangeJob.getUser_id());
                umsUserOperationLog.setOperationTypecode(11);
                umsUserOperationLog.setOperationTypedetail(umsChangeJob.getState() + "调职");
                umsUserOperationLog.setOperationTime(new Date());
                umsUserOperationLog.setOperationIp(ip);
                umsUserOperationLog.setOperationContent(umsChangeJob.getState() + "该人员调职" + " || 原因:" + umsChangeJob.getUpdate_reason());
                umsUserOperationLogService.logUserOperation(umsUserOperationLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (umsChangeJob.getState().equals("已取消")) {
            try {//记录用户操作记录
                UmsUserOperationLog umsUserOperationLog = new UmsUserOperationLog();
                ActionContext ctx = ActionContext.getContext();
                HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
                String ip = IpTools.getIpAddress(request);
                UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                if (user_ != null) {
                    umsUserOperationLog.setOperationUsername(user_.getFullname());
                }

                umsUserOperationLog.setId(UUID.randomUUID().toString());
                umsUserOperationLog.setModifiedUserid(umsChangeJob.getUser_id());
                umsUserOperationLog.setOperationTypecode(10);
                umsUserOperationLog.setOperationTypedetail(umsChangeJob.getState() + "该调职操作");
                umsUserOperationLog.setOperationTime(new Date());
                umsUserOperationLog.setOperationIp(ip);
                umsUserOperationLog.setOperationContent(umsChangeJob.getState() + "该调职操作");
                umsUserOperationLogService.logUserOperation(umsUserOperationLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int j = 0, i = 0;
        List<UmsChangeJob> ma = null;
        RowBounds rowBounds = new RowBounds(0, 20);
        if (umsChangeJob.getState().equals("已同意")) {
            ma = userInfoService.selectChangeJob(umsChangeJob, rowBounds);
        }

        i = userInfoService.updateJobById(umsChangeJob);

        if (umsChangeJob.getState().equals("已同意")) {
            try {
                umsChangeJob = ma.get(0);
                j = userInfoService.updateXtptAndInfo(umsChangeJob);
                if (j > 0) {
                    attachedTableService.AttachmentCourtUpdate(umsChangeJob.getUser_id(), umsChangeJob.getNew_court_no());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("job", i > 0 ? true : false);
        map.put("info", j > 0 ? true : false);
        //将同意调职的用户强制设为不可用，以便强制管理员进行信息完善和启用该用户
        if ((boolean) map.get("job") && (boolean) map.get("info")) {
            userInfo = new UmsUserInfo();
            userInfo.setIsValid(0);
            userInfo.setId(umsChangeJob.getUser_id());
            enabled();
        }

        data = map;
        return "json";
    }

    public static Map<String, Object> entityToMap(Object o) {
        Map<String, Object> map = new HashMap<>();

        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {

            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            field.setAccessible(true);

            try {
                map.put(field.getName(), field.get(o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    @Action("legalBatch")
    public String legalBatch() {
        System.out.println(allId);
        return "json";
    }

    private String judgeResumeAndFamilyIsNull(String id) {
        String resumeViewName = "resume_info";
        String familyViewName = "family_info";
        int resumeNum = attachedTableService.countView(resumeViewName, id);
        int familyNum = attachedTableService.countView(familyViewName, id);
        StringBuffer str = new StringBuffer("");
        if (resumeNum < 1)
            str.append("、简历信息");
        if (familyNum < 1)
            str.append("、家庭信息");
        if (!"".equals(str.toString()))
            return "您的" + str.toString().substring(1) + "暂时还没有完善，请尽快完善这些信息，谢谢！";
        return "";
    }

    private UmsUserInfo combineSydwCore(UmsUserInfo sourceBean, UmsUserInfo targetBean) {
        Class sourceBeanClass = sourceBean.getClass();
        Class targetBeanClass = targetBean.getClass();

        Field[] sourceFields = sourceBeanClass.getDeclaredFields();
        Field[] targetFields = targetBeanClass.getDeclaredFields();
        for (int i = 0; i < sourceFields.length; i++) {
            Field sourceField = sourceFields[i];
            if (Modifier.isStatic(sourceField.getModifiers())) {
                continue;
            }
            Field targetField = targetFields[i];
            if (Modifier.isStatic(targetField.getModifiers())) {
                continue;
            }
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            try {
                if (!(sourceField.get(sourceBean) == null) && !"serialVersionUID".equals(sourceField.getName().toString())) {
                    targetField.set(targetBean, sourceField.get(sourceBean));
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return targetBean;
    }

    public Integer getEnableType() {
        return enableType;
    }

    public void setEnableType(Integer enableType) {
        this.enableType = enableType;
    }

    public UmsUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UmsUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UmsChangeJob getUmsChangeJob() {
        return umsChangeJob;
    }

    public void setUmsChangeJob(UmsChangeJob umsChangeJob) {
        this.umsChangeJob = umsChangeJob;
    }

    public Integer getApply_court_no() {
        return apply_court_no;
    }

    public void setApply_court_no(Integer apply_court_no) {
        this.apply_court_no = apply_court_no;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List getAllId() {
        return allId;
    }

    public void setAllId(List allId) {
        this.allId = allId;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

}
