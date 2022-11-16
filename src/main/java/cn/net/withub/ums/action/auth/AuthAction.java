/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.auth;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.UmsAuth;
import cn.net.withub.ums.entity.UmsRole;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.service.UmsAuthService;
import cn.net.withub.ums.service.UmsAuthorityService;
import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/auth")
@Results({
    @Result(name = "json", type = "json", params = {"root", "data"})
})
public class AuthAction {

    @Autowired
    private UmsAuthService authService;

    @Autowired
    private UmsAuthorityService authorityService;

    private String userId;

    private Object data;

    private String rolename;

    @Action("authList")
    public String allAuth() {
        data = authService.listAll();
        return "json";
    }

    @Action("authList2")
    public String allAuth2() {
        List items = new ArrayList();
        for (final UmsAuth a : authService.listAll()) {
            items.add(new HashMap<String, Object>() {
                {
                    put("text", a.getAuthName());
                    put("value", a.getAuthCode());
                }
            });
        }

        data = items;
        return "json";
    }

    @Action("canIEdit")
    public String canIEdit() {
        boolean flag = false;
        UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        if (u != null) {
            UmsAuth editAuth = authorityService.getAuthByName("编辑");
            for (UmsRole role : authorityService.userRoles(u.getId())) {
                if ((role.getAuthCode() & editAuth.getAuthCode()) == editAuth.getAuthCode()) {
                    flag = true;
                }
            }
//            if (!flag) {
//                flag = userId.equalsIgnoreCase(u.getId());
//            }
        }

        data = flag;
        return "json";
    }

    // 是否有包含关键字的角色
    @Action("hasRole")
    public String hasRole() {
        boolean flag = false;
        UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        if (u != null) {
            for (UmsRole role : authorityService.userRoles(u.getId())) {
                if (role.getRoleName().contains(rolename)) {
                    flag = true;
                }
            }
        }
        data = flag;
        return "json";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
