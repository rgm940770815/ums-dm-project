/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.util;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.UmsUserInfoView;
import com.opensymphony.xwork2.ActionContext;

/**
 *
 * @author Diluka
 */
public class SessionUtils {

    /**
     * 当前用户
     *
     * @return
     */
    public static UmsUserInfoView currentUser() {
        return (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
    }

}
