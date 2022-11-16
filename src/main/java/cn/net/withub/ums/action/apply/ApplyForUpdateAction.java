package cn.net.withub.ums.action.apply;

import cn.net.withub.ums.action.userinfo.UserInfoAction;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsUserOperationLogService;
import cn.net.withub.ums.service.UmsApplyForUpdateService;
import cn.net.withub.ums.service.UmsUserInfoService;
import cn.net.withub.ums.util.IpTools;
import cn.net.withub.ums.util.SessionUtils;
import cn.net.withub.ums.util.StringTools;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.*;

/**
 * Created by Cypress on 2016/12/7.
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/applyForUpdate")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class ApplyForUpdateAction {

    @Autowired
    UmsApplyForUpdateService umsApplyForUpdateService;

    @Autowired
    private UmsUserInfoService userInfoService;

    @Autowired
    UserInfoAction userInfoAction;

    @Autowired
    UmsUserOperationLogService umsUserOperationLogService; // 记录用户操作日志

    private Object data;
    private Integer start;
    private Integer limit;
    private String field;
    private String direction;
    private Integer court;
    private String userNameF;
    private Integer handleCode;
    private Integer enable;
    private Integer isUser;
    private UmsApplyForUpdate umsApplyForUpdate;


    @Action("insert")
    public String insert() {
        Map<String, Object> result = new HashMap<>();
        boolean flag = false;
        if (umsApplyForUpdate != null && umsApplyForUpdate.getUserId() != null
                && umsApplyForUpdate.getNewValidCode() != null) {

            try {
                //先查找用户信息
                UmsUserInfoCriteria criteria_user = new UmsUserInfoCriteria();
                criteria_user.createCriteria().andIdEqualTo(umsApplyForUpdate.getUserId());
                UmsUserInfo umsUserInfo = userInfoService.search(criteria_user, null).get(0);

                //获取用户信息
                UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

                //插入之前先查找一下是否存在未处理的申请
                UmsApplyForUpdateExample example = new UmsApplyForUpdateExample();
                example.createCriteria().andUserIdEqualTo(umsApplyForUpdate.getUserId()).andHandleEqualTo(0)
                        .andNewValidCodeEqualTo(umsApplyForUpdate.getNewValidCode());
                List<UmsApplyForUpdate> umsApplyForUpdates = umsApplyForUpdateService.selectByExample(example);
                if (umsApplyForUpdates.size() > 0) {
                    result.put("success", false);
                    result.put("errorType", 1);
                    data = result;
                    return "json";
                }

                //为了以后更好的处理申请信息(不用再建表) 定义一个type 目前启用停用的申请设置为1
                umsApplyForUpdate.setType(1);
                umsApplyForUpdate.setId(UUID.randomUUID().toString());
                umsApplyForUpdate.setCreateTime(new Date());
                umsApplyForUpdate.setApplyUserId(u.getId());
                umsApplyForUpdate.setApplyUserName(u.getFullname());
                umsApplyForUpdate.setHandle(0);
                umsApplyForUpdate.setUserName(umsUserInfo.getFullname());
                umsApplyForUpdate.setUserCourtNo(umsUserInfo.getCourtNo());
                umsApplyForUpdate.setOldValidCode(umsUserInfo.getIsValid());
                int insert = umsApplyForUpdateService.insert(umsApplyForUpdate);

                if (umsUserInfo.getIsValid() == 0) {
                    //进行停用操作


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
                        umsUserOperationLog.setModifiedUserid(umsUserInfo.getId());
                        umsUserOperationLog.setOperationTypecode(11);
                        umsUserOperationLog.setOperationTypedetail("申请启用在编人员");
                        umsUserOperationLog.setOperationTime(umsApplyForUpdate.getCreateTime());
                        umsUserOperationLog.setOperationIp(ip);
                        umsUserOperationLog.setOperationContent("申请启用在编人员");
                        umsUserOperationLogService.logUserOperation(umsUserOperationLog);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
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
                        umsUserOperationLog.setModifiedUserid(umsUserInfo.getId());
                        umsUserOperationLog.setOperationTypecode(12);
                        umsUserOperationLog.setOperationTypedetail("申请停用在编人员");
                        umsUserOperationLog.setOperationTime(umsApplyForUpdate.getCreateTime());
                        umsUserOperationLog.setOperationIp(ip);
                        umsUserOperationLog.setOperationContent("申请停用在编人员");
                        umsUserOperationLogService.logUserOperation(umsUserOperationLog);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (insert > 0) {
                    flag = true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            result.put("success", flag);
            data = result;
        }
        return "json";
    }

    @Action("getInfo")
    public String getInfo() {
        UmsApplyForUpdateExample umsApplyForUpdateExample = new UmsApplyForUpdateExample();

        String order = "";
        if (field != null && direction != null) {
            order = " " + StringTools.camelOrPascalToUnderline(field) + " " + direction;
        } else {
            order = "   create_time desc";
        }

        if (start != null && limit != null) {
            order += " limit " + start + "," + limit;
        } else {
            order += " limit 0,20";
        }
        umsApplyForUpdateExample.setOrderByClause(order);
        List<UmsApplyForUpdate> UmsApplyForUpdates = new ArrayList<>();
        int i = 0;
        try {
            UmsApplyForUpdateExample.Criteria criteria = umsApplyForUpdateExample.createCriteria();
            criteria.andTypeEqualTo(1);
            if (handleCode != null) {
                criteria.andHandleEqualTo(handleCode);
            }
            if (StringUtils.hasText(userNameF)) {
                criteria.andUserNameLike("%" + userNameF + "%");
            }
            if (isUser != null && isUser == 1) {
                //获取用户信息
                UmsUserInfoView user = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

                criteria.andApplyUserIdEqualTo(user.getId());
            }
            UmsApplyForUpdates = umsApplyForUpdateService.selectByExample(umsApplyForUpdateExample);
            i = umsApplyForUpdateService.countByExample(umsApplyForUpdateExample);


        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, Object> map = new HashMap<>();
        map.put("rows", UmsApplyForUpdates);
        map.put("results", i);
        data = map;
        return "json";
    }

    @Action("update")
    public String update() {
        Map<String, Object> result = new HashMap<>();
        boolean flag = false;
        int i = 0;
        try {
            if (umsApplyForUpdate != null && umsApplyForUpdate.getId() != null
                    && enable != null) {
                //先查申请
                UmsApplyForUpdateExample example = new UmsApplyForUpdateExample();
                example.createCriteria().andTypeEqualTo(1).andIdEqualTo(umsApplyForUpdate.getId());
//                criteria.and
                List<UmsApplyForUpdate> umsApplyForUpdates = umsApplyForUpdateService.selectByExample(example);
                if (umsApplyForUpdates.size() != 1) {
                    throw new Exception("error");
                }
                UmsApplyForUpdate umsApplyForUpdate = umsApplyForUpdates.get(0);
                //获取用户信息
                UmsUserInfoView user = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());


                if (enable == 1) {
                    //同意
                    UmsUserInfo u = new UmsUserInfo();
                    u.setId(umsApplyForUpdate.getUserId());
                    u.setIsValid(umsApplyForUpdate.getNewValidCode());
                    u.setLeaveReason(umsApplyForUpdate.getLeaveReason());
                    u.setLeaveDate(umsApplyForUpdate.getLeaveDate());
                    u.setLeaveDestination(umsApplyForUpdate.getLeaveDestination());
                    userInfoAction.setUserInfo(u);
                    boolean flag_ = userInfoAction.doEnable();
                    if (flag_) {
                        umsApplyForUpdate.setUpdateTime(new Date());
                        umsApplyForUpdate.setUpdateUserName(user.getFullname());
                        umsApplyForUpdate.setUpdateUserId(user.getId());
                        umsApplyForUpdate.setHandle(1);
                        i = umsApplyForUpdateService.updateByPrimaryKeySelective(umsApplyForUpdate);

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
                            umsUserOperationLog.setModifiedUserid(umsApplyForUpdate.getUserId());
                            umsUserOperationLog.setOperationTime(new Date());
                            umsUserOperationLog.setOperationIp(ip);
                            if (umsApplyForUpdates.get(0).getOldValidCode() == 0 && umsApplyForUpdates.get(0).getNewValidCode() == 1) {
                                umsUserOperationLog.setOperationTypecode(13);
                                umsUserOperationLog.setOperationTypedetail("同意启用在编人员");
                                umsUserOperationLog.setOperationContent("同意启用在编人员");

                            } else {
                                umsUserOperationLog.setOperationTypecode(14);
                                umsUserOperationLog.setOperationTypedetail("同意停用在编人员");
                                umsUserOperationLog.setOperationContent("同意停用在编人员");
                            }
                            umsUserOperationLogService.logUserOperation(umsUserOperationLog);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                } else if (enable == 0) {
                    //不同意
                    umsApplyForUpdate.setUpdateTime(new Date());
                    umsApplyForUpdate.setUpdateUserName(user.getFullname());
                    umsApplyForUpdate.setUpdateUserId(user.getId());
                    umsApplyForUpdate.setHandle(2);
                    i = umsApplyForUpdateService.updateByPrimaryKeySelective(umsApplyForUpdate);


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
                        umsUserOperationLog.setModifiedUserid(umsApplyForUpdate.getUserId());
                        umsUserOperationLog.setOperationTime(new Date());
                        umsUserOperationLog.setOperationIp(ip);
                        if (umsApplyForUpdates.get(0).getOldValidCode() == 0 && umsApplyForUpdates.get(0).getNewValidCode() == 1) {
                            umsUserOperationLog.setOperationTypecode(15);
                            umsUserOperationLog.setOperationTypedetail("不同意启用在编人员");
                            umsUserOperationLog.setOperationContent("不同意启用在编人员");

                        } else {
                            umsUserOperationLog.setOperationTypecode(16);
                            umsUserOperationLog.setOperationTypedetail("不同意停用在编人员");
                            umsUserOperationLog.setOperationContent("不同意停用在编人员");
                        }
                        umsUserOperationLogService.logUserOperation(umsUserOperationLog);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (enable == 3) {
                    //取消
                    umsApplyForUpdate.setHandle(3);
                    i = umsApplyForUpdateService.updateByPrimaryKeySelective(umsApplyForUpdate);

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
                        umsUserOperationLog.setModifiedUserid(umsApplyForUpdate.getUserId());
                        umsUserOperationLog.setOperationTime(new Date());
                        umsUserOperationLog.setOperationIp(ip);
                        if (umsApplyForUpdates.get(0).getOldValidCode() == 0 && umsApplyForUpdates.get(0).getNewValidCode() == 1) {
                            umsUserOperationLog.setOperationTypecode(17);
                            umsUserOperationLog.setOperationTypedetail("取消操作是否启用在编人员");
                            umsUserOperationLog.setOperationContent("取消操作是否启用在编人员");

                        } else {
                            umsUserOperationLog.setOperationTypecode(18);
                            umsUserOperationLog.setOperationTypedetail("取消操作是否停用在编人员");
                            umsUserOperationLog.setOperationContent("取消操作是否停用在编人员");
                        }
                        umsUserOperationLogService.logUserOperation(umsUserOperationLog);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.put("success", i > 0);
        data = result;
        return "json";
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getCourt() {
        return court;
    }

    public void setCourt(Integer court) {
        this.court = court;
    }

    public UmsApplyForUpdate getUmsApplyForUpdate() {
        return umsApplyForUpdate;
    }

    public void setUmsApplyForUpdate(UmsApplyForUpdate umsApplyForUpdate) {
        this.umsApplyForUpdate = umsApplyForUpdate;
    }

    public String getUserNameF() {
        return userNameF;
    }

    public void setUserNameF(String userNameF) {
        this.userNameF = userNameF;
    }

    public Integer getHandleCode() {
        return handleCode;
    }

    public void setHandleCode(Integer handleCode) {
        this.handleCode = handleCode;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getIsUser() {
        return isUser;
    }

    public void setIsUser(Integer isUser) {
        this.isUser = isUser;
    }

}
