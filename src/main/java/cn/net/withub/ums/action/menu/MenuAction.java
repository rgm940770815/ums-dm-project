/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.menu;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.menu.BUIMenuHelper;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/menu")
@Results({
    @Result(name = "json", type = "json", params = {"root", "data"})
})
public class MenuAction {

    @Autowired
    private BUIMenuHelper iMenuHelper;

    private Object data;

    /**
     * 生成当前登录用户菜单
     *
     * @return
     */
    @Action("myMenu")
    public String myMenu() {
        UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        data = iMenuHelper.generateUserMenu(u.getId());
        return "json";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
