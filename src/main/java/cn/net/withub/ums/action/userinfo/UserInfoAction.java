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
    UmsUserOperationLogService umsUserOperationLogService; // ????????????????????????
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
            // ?????????????????????
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
                map.put("msg", "?????????????????????");
                data = map;
                return "json";
            }

            //??????????????????????????????????????????
            userInfo.setIdcard(userInfo.getIdcard().toUpperCase());
            //??????username?????????????????????sql?????????????????????
            if(userInfo.getUsername() != null && userInfo.getUsername().endsWith("/")){
                userInfo.setUsername(userInfo.getUsername().replace("/",""));
            }
            // ---------------------??????????????????start
            // ?????????ums_user_info?????????
            UmsUserInfo umsUserInfoBeforeSave = userInfoService.selectById(userInfo.getId());
            // ???????????????????????????????????????
            Field[] fields = userInfo.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                // ????????????????????????????????????????????????????????????????????????
                fields[i].setAccessible(true);
                try {
                    // ?????????????????? ??????????????????????????????20200107 ?????????????????????????????????????????????,?????????????????????????????????
                    String fieldName = fields[i].getName();
                    String[] df = {"positionType", "assign", "partyOffice", "proCert", "leaveReason", "leaveDestination"};
                    List dfList = Arrays.asList(df);
                    if ((dfList.contains(fieldName) || null != fields[i].get(userInfo)) && null != umsUserInfoBeforeSave) {
                        // ??????ums_user_info???????????????????????????????????????, ?????????????????????????????????????????????????????????????????????
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

            // ????????????????????????????????????????????????userInfo??????????????????????????????
            if (null == umsUserInfoBeforeSave) {

            } else {
                userInfo = umsUserInfoBeforeSave;
            }
            // ---------------------??????????????????end

            // ??????????????????userInfo???????????????????????????
            userInfo.setUsername(userInfo.getUsername().trim().replace(" ", "").replace("???", ""));
            userInfo.setFullname(userInfo.getFullname().trim().replace(" ", "").replace("???", ""));
            userInfo.setCourtStdNo(courtService.courtNo2CourtStdNo(userInfo.getCourtNo()));
            userInfo.setCourtCode(courtService.courtNo2CourtCode(userInfo.getCourtNo()));

            // ????????????????????????
            UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
            criteria.createCriteria().andCourtCodeEqualTo(userInfo.getCourtCode()).andDeptNoEqualTo(userInfo.getDepartment());
            List<UmsDepartment> umsDepartments = umsDepartmentService.selectByExample(criteria);
            UmsDepartment umsDepartment = umsDepartments.get(0);

            userInfo.setDeptOrgCode(umsDepartment.getOrgCode());
            UmsUserInfoView user = SessionUtils.currentUser();
            userInfo.setUpdateUser(user.getFullname() + "@" + user.getCourtNoText());
            userInfo.setUpdateTime(new Date());
            //????????????????????????????????????????????????????????????????????????????????????????????????
            EncodeDecodeDataInfo.enCodeDataForUms(userInfo);


            int result = 0;
            try {
                // ??????ums_user_info????????????
                if (StringTools.isNullOrEmpty(userInfo.getId())) {
                    // ??????
                    // ??????UserNo???????????????
                    userInfo.setUserNo((int) (Math.random() * -1000));
                    userInfo.setId(id);

                    if (userInfo.getPassword() != null) {
                        Md5PasswordEncoder Md5Checker = new Md5PasswordEncoder();
                        String salt = null;
                        userInfo.setSalt(salt);
                        String rPassword = Md5Checker.encodePassword(userInfo.getPassword(), salt);
                        userInfo.setPassword(rPassword);
                    }

                    // ??????
                    userInfo.setIsValid(1);

                    // ?????????
                    if (userInfo.getSortNo() == null) {
                        userInfo.setSortNo(9999);
                    }

                    // ??????????????????????????? ?????????????????????0
//                    if (userInfo.getLeaveReason() != null) {
                        // ??????
//                        userInfo.setIsValid(0);
//                    }

                    // ???????????????
                    result = userInfoService.insert(userInfo);

                    //-----------start-------------????????????
                    String ip = IpTools.getIpAddress(request);
                    UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                    UmsUserOperationLog umsUserOperationLog = new UmsUserOperationLog();
                    umsUserOperationLog.setId(UUID.randomUUID().toString());
                    umsUserOperationLog.setOperationUsername(user_.getFullname());
                    umsUserOperationLog.setModifiedUserid(userInfo.getId());
                    umsUserOperationLog.setOperationTypecode(1);
                    umsUserOperationLog.setOperationTypedetail("????????????????????????");
                    umsUserOperationLog.setOperationTime(userInfo.getUpdateTime());
                    umsUserOperationLog.setOperationIp(ip);
                    umsUserOperationLog.setOperationContent("????????????????????????");
                    umsUserOperationLogService.logUserOperation(umsUserOperationLog);
                    //-----------end-------------????????????

                } else {
                    // ??????ums_user_info????????????
                    // ??????????????????????????? ?????????????????????????????????????????? ????????????????????????????????????????????????
                    UmsUserInfoCriteria criteria_user = new UmsUserInfoCriteria();
                    criteria_user.createCriteria().andIdEqualTo(userInfo.getId());
                    UmsUserInfo umsUserInfo = userInfoService.search(criteria_user, null).get(0);

                    // ????????????ums_gbbzlx_info
                    boolean updateGbbzlxInfo = false;

                    // ??????????????????????????? ?????????????????????0
                    if (userInfo.getLeaveReason() != null && userInfo.getUserType() < 10) {
                        // ??????
//                        userInfo.setIsValid(0);
                        // ???????????? ???????????????????????????????????? ??????????????? ?????????????????????
//                        if (userInfo.getLeaveReason() == 5) {
//                            if (umsUserInfo.getIsValid() != null && umsUserInfo.getIsValid() == 1 && umsUserInfo.getLeaveReason() != null && umsUserInfo.getLeaveReason() == 5) {
//                                userInfo.setIsValid(null);
//                            }
//                        }
                    } else {
                        // ??????????????????userType??????????????????
                        if (null == userInfo.getUserType()) {
                            userInfo.setUserType(1);
                        } else {
                            Integer userType = userInfo.getUserType();
                            // userType??????10???????????????????????????
                            if (userType > 10) {
                                userInfo.setIsValid(1);
                                if (userType > 20) {
                                    // ?????????????????????????????????
                                    updateGbbzlxInfo = true;
                                }
                                // ???????????????????????????
                                userInfo.setUserType(1);
                            }
                        }
                    }

                    // ?????????????????????
                    UmsUserInfo umsUserInfo_original = userInfoService.selectById(userInfo.getId());
                    // ?????????????????????????????????????????????
                    int isValid_orig = umsUserInfo_original.getIsValid();
                    Map<String, String> map_dif = CompareUtil.compare(umsUserInfo_original, userInfo);

                    // xtpt ?????????????????????
                    if (userInfo.getSortNo() == null) {
                        userInfo.setSortNo(9999);
                    }
                    // ???????????????
                    result = userInfoService.update(userInfo);

                    // ???????????????????????????????????????????????????ums_gbbzlx_info????????????????????????
                    if (updateGbbzlxInfo) {
                        String changeUUID = ServletActionContext.getRequest().getParameter("changeUUID");
                        UmsGbbzlxInfo umsGbbzlxInfo = new UmsGbbzlxInfo();
                        umsGbbzlxInfo.setChangeuuid(changeUUID);
                        umsGbbzlxInfo.setState("???????????????");
                        umsGbbzlxInfoService.updateByPrimaryKeySelective(umsGbbzlxInfo);
                    }

                    //-----------start-------------????????????
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
                            // ???????????????????????????????????????
                            umsUserOperationLog.setOperationTypecode(17);
                            umsUserOperationLog.setOperationTypedetail("????????????????????????????????????");
                        } else {
                            umsUserOperationLog.setOperationTypecode(2);
                            umsUserOperationLog.setOperationTypedetail("????????????????????????");
                        }
                        umsUserOperationLog.setId(UUID.randomUUID().toString());
                        umsUserOperationLog.setModifiedUserid(userInfo.getId());
                        umsUserOperationLog.setOperationTime(userInfo.getUpdateTime());
                        umsUserOperationLog.setOperationIp(ip);
                        umsUserOperationLog.setOperationContent("???????????????:" + map_dif.toString());
                        umsUserOperationLogService.logUserOperation(umsUserOperationLog);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //-----------end-------------????????????

                    // ???????????????????????????????????????cn/net/withub/ums/action/userinfo/UserInfoAttachedViewsAction.java:202
                    if (result > 0 && umsUserInfo.getCourtNo() != null && userInfo.getCourtNo() != null && !Objects.equals(umsUserInfo.getCourtNo(), userInfo.getCourtNo())) {
                        attachedTableService.AttachmentCourtUpdate(umsUserInfo.getId(), userInfo.getCourtNo());
                    }
                }
                String userStatusCode = request.getParameter("userStatus");
                String[] userStatusCodeList =  request.getParameterMap().get("userStatus[]");
                String userId = userInfo.getId();
                String ip = IpTools.getIpAddress(request);
                UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                //???????????????????????????
                List<UmsUserStatus> insertList = new ArrayList<>();
                //?????????????????????
                List<UmsUserStatus> userstatusList = umsUserStatusMapper.selectByUserId(userId);

                Map<String,UmsUserStatus> umsUserStatusMap = new HashMap<>();
                //??????????????????????????????map??????,  ?????????????????????
                for (UmsUserStatus userStatus : userstatusList) {
                    umsUserStatusMap.put(userStatus.getStatusCode(),userStatus);
                }

                if(!StringUtils.isEmpty(userStatusCode)){//???userStatusCode?????????, ????????????????????????????????????????????????????????????
                    if(!umsUserStatusMap.containsKey(userStatusCode)){
                        //????????????????????????????????????
                        umsUserStatusMapper.deleteByUserId(userId);
                        UmsUserStatus umsUserStatus = new UmsUserStatus();
                        umsUserStatus.setAddIp(ip);
                        umsUserStatus.setAddUser(user_.getFullname());
                        umsUserStatus.setStatusCode(userStatusCode);
                        umsUserStatus.setTypeId(10007);
                        umsUserStatus.setUserId(userId);
                        insertList.add(umsUserStatus);
                    }
                }else if(userStatusCodeList != null && userStatusCodeList.length>0){//???userStatusCodeList????????????,???????????????????????????
                    //?????????????????????????????????
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
                    //?????????????????????????????????????????????
                    if(codeList.size()>0){
                        umsUserStatusMapper.deleteByUserIdAndNotCodeList(userId,codeList);
                    }
                }else{//????????????????????????
                    umsUserStatusMapper.deleteByUserId(userId);
                }
                if(insertList.size()>0){
                    umsUserStatusMapper.insertList(insertList);
                }


                map.put("success", true);
                map.put("msg", "???????????????");
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
        //????????????????????????????????????????????????????????????????????????????????????????????????
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
//                //?????????xtpt ????????????
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
//                    //?????????????????????
//                    UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
//                    criteria.createCriteria().andIdEqualTo(userInfo.getId());
//                    UmsUserInfo umsUserInfo = userInfoService.search(criteria, null).get(0);
//                    if (umsUserInfo.getUserType() == 1) {
//                        //??????????????????????????? ???????????????????????? ????????????????????????????????? ????????????????????????????????????
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
//                        //??????
//                        if (enableType != null && enableType == 1) {
//                            l.setUserType(userInfo.getUserType());
//                        }
//
//                        xtptTUserService.update(l);
//                    } else {
//                        throw new Exception("???????????????");
//                    }
//                }
//                i = userInfoService.updateEnabled(userInfo);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            //???????????????????????????
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
                //?????????????????????
                UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
                criteria.createCriteria().andIdEqualTo(userInfo.getId());
                UmsUserInfo umsUserInfo = userInfoService.search(criteria, null).get(0);
                if (umsUserInfo.getUserType() == 1) {
                    try {//????????????????????????
                        UmsUserOperationLog umsUserOperationLog = new UmsUserOperationLog();// ??????????????????
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
                        umsUserOperationLog.setOperationTypedetail("??????????????????");//????????????????????????
                        umsUserOperationLog.setOperationContent("??????????????????");
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
        UmsUserOperationLog umsUserOperationLog = new UmsUserOperationLog();// ??????????????????
        if (userInfo != null && userInfo.getId() != null && userInfo.getIsValid() != null) {
            // ?????????xtpt ????????????
            XtptTUserExample example = new XtptTUserExample();
            example.createCriteria().andUuidEqualTo(userInfo.getId());
            List<XtptTUser> list = xtptTUserService.selectByExample(example);
            // ??????????????? ums_user_info ??????
            UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
            criteria.createCriteria().andIdEqualTo(userInfo.getId());
            List<UmsUserInfo> search = userInfoService.search(criteria, null);
            if (search == null || search.size() != 1) {
                return false;
            }
            UmsUserInfo umsUserInfo = search.get(0);
            // ????????????
            if (userInfo.getIsValid() == 0) {
                // ???????????????????????????  ????????????????????????????????????
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
                        umsUserOperationLog.setOperationContent("????????????????????????");
                        umsUserOperationLog.setOperationTypedetail("????????????????????????");
                    }
                    if (list.get(0).getUserType() == 3) {
                        umsUserOperationLog.setOperationTypecode(4);
                        umsUserOperationLog.setOperationContent("?????????????????????");
                        umsUserOperationLog.setOperationTypedetail("?????????????????????");
                    }
                } else {
                    // ???????????????????????????????????????ums_user_info?????????????????????xtpt_t_user????????????
                    // ????????????????????????????????????????????????xtpt_t_user,????????????ums_user_info???
                    // ??????????????????????????????ums_user_info?????????????????????????????????????????????????????????????????????????????????
//                    throw new Exception("???????????????");
                }
            } else if (userInfo.getIsValid() == 1) {
                // ?????????????????????
                if (umsUserInfo.getUserType() == 1) {
                    // ??????????????????????????? ???????????????????????? ????????????????????????????????? ????????????????????????????????????
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
                    umsUserOperationLog.setOperationTypedetail("??????????????????");//????????????????????????
                    umsUserOperationLog.setOperationContent("??????????????????");
                } else if (umsUserInfo.getUserType() == 3) {
                    userInfo.setCPsMzyy(-1);
                    umsUserOperationLog.setOperationTypecode(7);
                    umsUserOperationLog.setOperationTypedetail("?????????????????????");//????????????????????????
                    umsUserOperationLog.setOperationContent("?????????????????????");
                }
                if (list != null && list.size() > 0) {
                    XtptTUser l = list.get(0);
                    l.setIsValid(userInfo.getIsValid());
                    l.setEnabled(true);
                    l.setLeaveDate(null);
                    l.setLeaveDestination(null);
                    l.setLeaveReason(null);
                    //??????
                    if (enableType != null && enableType == 1) {
                        l.setUserType(userInfo.getUserType());
                    }
                    xtptTUserService.updateAll(l);
                } else {
                    throw new Exception("???????????????");
                }
            } else if (userInfo.getIsValid() == 3) {
                umsUserOperationLog.setOperationTypecode(91);
                umsUserOperationLog.setOperationTypedetail("??????????????????");//????????????????????????
                umsUserOperationLog.setOperationContent("??????????????????");
                if (list != null && list.size() > 0) {
                    XtptTUser l = list.get(0);
                    //???xtpt?????????????????????
                    l.setIsValid(1);
                    l.setEnabled(true);
                    xtptTUserService.updateAll(l);
                } else {
                    throw new Exception("???????????????");
                }
            }
            userInfo.setCourtNo(umsUserInfo.getCourtNo());
            i = userInfoService.updateEnabled(userInfo);
            if (list.get(0).getUserType() == 11 || list.get(0).getUserType() == 1 || list.get(0).getUserType() == 22 || list.get(0).getUserType() == 12) {
            } else {
                try {//????????????????????????
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
            // ???????????????????????????
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
        umsChangeJob.setState("?????????");

        int i = userInfoService.insertJobById(umsChangeJob);

        // ????????????????????????
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
                umsUserOperationLog.setOperationTypedetail("????????????????????????");
            }
            if (userInfoService.selectById(umsChangeJob.getUser_id()).getUserType() == 2) {
                umsUserOperationLog.setOperationTypedetail("????????????????????????");
            }
            umsUserOperationLog.setOperationTime(new Date());
            umsUserOperationLog.setOperationIp(ip);
            umsUserOperationLog.setOperationContent("???????????????:" + umsChangeJob.getOld_court_text() + "->" + umsChangeJob.getNew_court_text() + " || " + umsChangeJob.getOld_dep_text() + "->" + umsChangeJob.getNew_dept_text());
            umsUserOperationLogService.logUserOperation(umsUserOperationLog);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("success", i > 0 ? true : false);
        map.put("msg", "????????????");
        data = map;
        return "json";
    }

    @Action("updateChangeJob")
    public String updateChangeJob() {

        if (umsChangeJob.getState().equals("?????????")) {
            try {//????????????????????????
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
                umsUserOperationLog.setOperationTypedetail(umsChangeJob.getState() + "??????");
                umsUserOperationLog.setOperationTime(new Date());
                umsUserOperationLog.setOperationIp(ip);
                umsUserOperationLog.setOperationContent(umsChangeJob.getState() + "???????????????");
                umsUserOperationLogService.logUserOperation(umsUserOperationLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (umsChangeJob.getState().equals("?????????")) {
            try {//????????????????????????
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
                umsUserOperationLog.setOperationTypedetail(umsChangeJob.getState() + "??????");
                umsUserOperationLog.setOperationTime(new Date());
                umsUserOperationLog.setOperationIp(ip);
                umsUserOperationLog.setOperationContent(umsChangeJob.getState() + "???????????????" + " || ??????:" + umsChangeJob.getUpdate_reason());
                umsUserOperationLogService.logUserOperation(umsUserOperationLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (umsChangeJob.getState().equals("?????????")) {
            try {//????????????????????????
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
                umsUserOperationLog.setOperationTypedetail(umsChangeJob.getState() + "???????????????");
                umsUserOperationLog.setOperationTime(new Date());
                umsUserOperationLog.setOperationIp(ip);
                umsUserOperationLog.setOperationContent(umsChangeJob.getState() + "???????????????");
                umsUserOperationLogService.logUserOperation(umsUserOperationLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int j = 0, i = 0;
        List<UmsChangeJob> ma = null;
        RowBounds rowBounds = new RowBounds(0, 20);
        if (umsChangeJob.getState().equals("?????????")) {
            ma = userInfoService.selectChangeJob(umsChangeJob, rowBounds);
        }

        i = userInfoService.updateJobById(umsChangeJob);

        if (umsChangeJob.getState().equals("?????????")) {
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
        //?????????????????????????????????????????????????????????????????????????????????????????????????????????
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
            str.append("???????????????");
        if (familyNum < 1)
            str.append("???????????????");
        if (!"".equals(str.toString()))
            return "??????" + str.toString().substring(1) + "???????????????????????????????????????????????????????????????";
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
