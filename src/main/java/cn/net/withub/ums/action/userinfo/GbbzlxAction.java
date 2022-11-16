package cn.net.withub.ums.action.userinfo;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsGbbzlxInfoService;
import cn.net.withub.ums.service.UmsUserInfoService;
import cn.net.withub.ums.service.UmsUserInfoViewService;
import cn.net.withub.ums.service.UmsUserOperationLogService;
import cn.net.withub.ums.util.IpTools;
import cn.net.withub.ums.util.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/gbbzlxAction")
@Results({@Result(name = "json", type = "json", params = {"root", "data"})})
public class GbbzlxAction {

    private Object data;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    UmsUserOperationLogService umsUserOperationLogService; // 记录用户操作日志

    @Autowired
    UmsGbbzlxInfoService umsGbbzlxInfoService;

    @Autowired
    private UmsUserInfoViewService userInfoViewService;

    @Autowired
    private UmsUserInfoService userInfoService;

    // 获取申请信息的列表
    @Action("getList")
    public Object getList() {
        HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
        // 申请人姓名
        String user_name = httpServletRequest.getParameter("user_name");
        String courtNo = httpServletRequest.getParameter("courtNo");
        String start = httpServletRequest.getParameter("start");
        String limit = httpServletRequest.getParameter("limit");
        UmsGbbzlxInfoExample umsGbbzlxInfoExample = new UmsGbbzlxInfoExample();
        if (null != user_name) {
            umsGbbzlxInfoExample.createCriteria().andUserNameEqualTo(user_name);
        }
        if (StringUtils.isNotEmpty(courtNo)) {
            umsGbbzlxInfoExample.createCriteria().andCourtNoEqualTo(Integer.valueOf(courtNo));
        }
        // 分页
        umsGbbzlxInfoExample.setOrderByClause(" sq_time desc limit " + start + ", " + limit);
        // 查询列表
        List<UmsGbbzlxInfo> list = umsGbbzlxInfoService.selectByExample(umsGbbzlxInfoExample);
        // 查询总数
        int list_size = umsGbbzlxInfoService.countByExample(umsGbbzlxInfoExample);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", list);
        map.put("results", list_size);
        data = map;
        return "json";
    }

    // 将申请信息写入改变编制类型表
    @Action("insertGbbzlx_sq")
    public Object insertGbbzlx_sq() {
        HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
        // 申请人姓名
        String changeUUID = httpServletRequest.getParameter("changeUUID");
        String user_id = httpServletRequest.getParameter("user_id");
        String user_name = httpServletRequest.getParameter("user_name");
        Integer court_no = httpServletRequest.getParameter("court_no") == null ? null : Integer.valueOf(httpServletRequest.getParameter("court_no"));
        String court_text = httpServletRequest.getParameter("court_text");
        Integer dep_no = httpServletRequest.getParameter("dep_no") == null ? null : Integer.valueOf(httpServletRequest.getParameter("dep_no"));
        String dep_text = httpServletRequest.getParameter("dep_text");
        String sq_content = httpServletRequest.getParameter("sq_content");
        String state = httpServletRequest.getParameter("state");
        String sq_time = httpServletRequest.getParameter("sq_time");
        String sq_reason = httpServletRequest.getParameter("sq_reason");
        // 获取操作人账号的信息
        UmsUserInfoView umsUserInfoView = (UmsUserInfoView) httpServletRequest.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        UmsGbbzlxInfo umsGbbzlxInfo = new UmsGbbzlxInfo();
        if (null != changeUUID) {
            umsGbbzlxInfo.setChangeuuid(changeUUID);
        }
        if (null != user_id) {
            umsGbbzlxInfo.setUserId(user_id);
        }
        if (null != user_name) {
            umsGbbzlxInfo.setUserName(user_name);
        }
        if (null != court_no) {
            umsGbbzlxInfo.setCourtNo(court_no);
        }
        if (null != court_text) {
            umsGbbzlxInfo.setCourtText(court_text);
        }
        if (null != dep_no) {
            umsGbbzlxInfo.setDepNo(dep_no);
        }
        if (null != dep_text) {
            umsGbbzlxInfo.setDepText(dep_text);
        }
        if (null != sq_content) {
            umsGbbzlxInfo.setSqContent(sq_content);
        }
        if (null != sq_time) {
            umsGbbzlxInfo.setSqTime(sq_time);
        }
        if (null != state) {
            umsGbbzlxInfo.setState(state);
        }
        if (null != sq_reason) {
            umsGbbzlxInfo.setSq_reason(sq_reason);
        }
        int insertCount = umsGbbzlxInfoService.insertSelective(umsGbbzlxInfo);
        if (insertCount == 1) {
            // 记录用户操作记录
            try {
                String ip = IpTools.getIpAddress(httpServletRequest);
                UmsUserInfoView user_ = (UmsUserInfoView) httpServletRequest.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                UmsUserOperationLog umsUserOperationLog = new UmsUserOperationLog();
                umsUserOperationLog.setId(UUID.randomUUID().toString());
                umsUserOperationLog.setOperationUsername(user_.getFullname());
                umsUserOperationLog.setModifiedUserid(user_id);
                umsUserOperationLog.setOperationTypecode(0);
                umsUserOperationLog.setOperationTypedetail("申请改变编制类型");
                umsUserOperationLog.setOperationTime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
                umsUserOperationLog.setOperationIp(ip);
                umsUserOperationLog.setOperationContent("state");
                umsUserOperationLogService.logUserOperation(umsUserOperationLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("results", insertCount);
        data = map;
        return "json";
    }

    // 管理员批准是否同意改变申请
    @Action("insertGbbzlx_pz")
    public Object insertGbbzlx_pz() {
        HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
        // 申请人姓名
        String changeUUID = httpServletRequest.getParameter("changeUUID");
        String user_id = httpServletRequest.getParameter("user_id");
        String clr_reason = httpServletRequest.getParameter("clr_reason");
        String state = httpServletRequest.getParameter("state");
        String clr_time = httpServletRequest.getParameter("clr_time");
        // 获取操作人账号的信息
        UmsUserInfoView umsUserInfoView = (UmsUserInfoView) httpServletRequest.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        String clr_user_id = umsUserInfoView.getId();
        String clr_user_name = umsUserInfoView.getFullname();
        Integer clr_court_no = umsUserInfoView.getCourtNo();
        String clr_court_text = umsUserInfoView.getCourtNoText();
        // where 条件 主键
        UmsGbbzlxInfoExample umsGbbzlxInfoExample = new UmsGbbzlxInfoExample();
        umsGbbzlxInfoExample.createCriteria().andChangeuuidEqualTo(changeUUID);
        // 需更新的字段
        UmsGbbzlxInfo umsGbbzlxInfo = new UmsGbbzlxInfo();
        if (null != changeUUID) {
            umsGbbzlxInfo.setChangeuuid(changeUUID);
        }
        if (null != clr_user_id) {
            umsGbbzlxInfo.setClrUserId(clr_user_id);
        }
        if (null != clr_user_name) {
            umsGbbzlxInfo.setClrUserName(clr_user_name);
        }
        if (null != clr_time) {
            umsGbbzlxInfo.setClrTime(clr_time);
        }
        if (StringUtils.isEmpty(clr_reason)) {
            umsGbbzlxInfo.setClrReason("无");
        } else {
            umsGbbzlxInfo.setClrReason(clr_reason);
        }
        if (null != clr_court_no) {
            umsGbbzlxInfo.setClrCourtNo(clr_court_no);
        }
        if (null != state) {
            umsGbbzlxInfo.setState(state);
        }
        if (null != clr_court_text) {
            umsGbbzlxInfo.setClrCourtText(clr_court_text);
        }
        int insertCount = umsGbbzlxInfoService.updateByPrimaryKeySelective(umsGbbzlxInfo);
        if (insertCount == 1) {
            // 记录用户操作记录
            try {
                String ip = IpTools.getIpAddress(httpServletRequest);
                UmsUserInfoView user_ = (UmsUserInfoView) httpServletRequest.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                UmsUserOperationLog umsUserOperationLog = new UmsUserOperationLog();
                umsUserOperationLog.setId(UUID.randomUUID().toString());
                umsUserOperationLog.setOperationUsername(user_.getFullname());
                umsUserOperationLog.setModifiedUserid(user_id);
                umsUserOperationLog.setOperationTypecode(0);
                umsUserOperationLog.setOperationTypedetail("同意改变改变编制类型");
                umsUserOperationLog.setOperationTime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
                umsUserOperationLog.setOperationIp(ip);
                umsUserOperationLog.setOperationContent("state");
                umsUserOperationLogService.logUserOperation(umsUserOperationLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 用来判断是否需要更改状态
        if (state.equals("已同意")) {
            Object update_count = gbbzlx(user_id);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("results", insertCount);
        data = map;
        return "json";
    }

    // 判断是否申请过
    @Action("sfysq")
    public Object sfysq() {
        HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
        // 申请人姓名
        String user_id = httpServletRequest.getParameter("user_id");
        String state = httpServletRequest.getParameter("state");
        UmsGbbzlxInfoExample umsGbbzlxInfoExample = new UmsGbbzlxInfoExample();
        umsGbbzlxInfoExample.createCriteria().andUserIdEqualTo(user_id).andStateEqualTo(state);
        int count = umsGbbzlxInfoService.countByExample(umsGbbzlxInfoExample);
        Map<String, Object> map = new HashMap<>();
        map.put("results", count);
        data = map;
        return "json";
    }

    // 改变编制
    public Object gbbzlx(String user_id) {
        // 根据user_id查询，该人员未改变时的人员类型
        UmsUserInfoView umsUserInfoView_select_for_type = userInfoViewService.selectById(user_id);
        Integer user_type = umsUserInfoView_select_for_type.getUserType();
        // 改变编制时：userType在原有基础上+20；调职处理时：userType在原有基础上+10；
        Integer user_type_gbbzlx = user_type + 20;
        UmsUserInfo umsUserInfo = new UmsUserInfo();
        umsUserInfo.setId(user_id);
        umsUserInfo.setUserType(user_type_gbbzlx);
        int update_count = userInfoService.updateByPrimaryKeySelective(umsUserInfo);
        // 具体的转编内容，根据表ums_gbbzlx_info表的sq_content字段区分
        return update_count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
