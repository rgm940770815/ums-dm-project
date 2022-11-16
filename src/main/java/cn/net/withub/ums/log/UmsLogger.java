/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.log;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.UmsLog;
import cn.net.withub.ums.entity.UmsUserInfo;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.entity.UmsUserLog;
import cn.net.withub.ums.service.UmsLogService;
import cn.net.withub.ums.util.IpTools;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.UUID;

/**
 * 日志记录器
 *
 * @author Diluka
 */
@Component
public class UmsLogger {

    @Autowired
    private UmsLogService logService;

    /**
     * 日志类型
     */
    public enum LogType {

        Suspicious(-1),
        /**
         * 操作日志
         */
        Insert(1), Update(2), Delete(3),


        /**
         * 登录日志
         */
        Login(0);

        private final int type;

        private LogType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

    }

    /**
     * 一般记录日志
     *
     * @param logType 日志类型
     * @param content 操作内容
     */
    public void log(LogType logType, String content) {
        log(logType, content, null, null,null);
    }

    /**
     * 一般记录日志
     *
     * @param logType 日志类型
     * @param content 操作内容
     */
    public void log(LogType logType, String content, String entityId, String userId, Integer courtNo) {
        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);

        String ip = IpTools.getIpAddress(request);

        UmsUserInfoView user = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        UmsLog log = new UmsLog();

        log.setId(UUID.randomUUID().toString());
        log.setOpTime(Calendar.getInstance().getTime());
        if (user != null) {
            log.setOpUserId(user.getId());
            log.setOpUser(user.getFullname());
            log.setOpCourtNo(user.getCourtNo());
        }
        log.setOpContent(content);
        log.setSortNo(99);
        log.setOpType(logType.getType());
        log.setOpIp(ip);
        log.setEntityId(entityId);
        log.setUserId(userId);
        log.setCourtNo(courtNo);

        logService.insert(log);
    }

    /**
     * 快捷登录日志记录
     */
    public void login() {
        log(LogType.Login, null);
    }

    public void loginFailed(Integer c, String u, String p) {
        log(LogType.Login, String.format("登录失败：c: %d, u: %s, p: %s", c, u, p));
    }

    /**
     * 通用记录
     *
     * @param log
     */
    public void generalLog(UmsLog log) {
        logService.insert(log);
    }

}
