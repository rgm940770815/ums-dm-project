/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.userinfo;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.service.UmsUserInfoService;
import cn.net.withub.ums.util.SimpleDecodeParameter;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/userinfo/password")
@Results({
    @Result(name = "json", type = "json", params = {"root", "data"})
})
public class UserInfoPasswordChangeAction {

    @Autowired
    private UmsUserInfoService userInfoService;

    private String oldPassword;
    private String newPassword;

    private Object data;

    @Action("change")
    public String changePassword() {
        StringBuilder msg = new StringBuilder();
        Map<String, Object> map = new HashMap<>();
        data = map;
        try {
            UmsUserInfoView uuiv = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
            //userCode

            //解码
            byte[] decode1 = SimpleDecodeParameter.decode(oldPassword);
            oldPassword = new String(decode1,"utf-8");

            byte[] decode2 = SimpleDecodeParameter.decode(newPassword);
            newPassword = new String(decode2,"utf-8");


    //        Base64 base64 = new Base64();
    //        oldPassword = new String(base64.decode(oldPassword));
    //        newPassword = new String(base64.decode(newPassword));

            if(uuiv.getUsername().equals("admin") || uuiv.getUsername().equals("superuser")){
                map.put("success", userInfoService.changePasswordForAdmin(uuiv.getId(), oldPassword, newPassword, msg));
            }else{
                map.put("success", userInfoService.changePassword(uuiv.getId(), oldPassword, newPassword, msg));
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success",false);
        }
        map.put("msg", msg.toString());

        return "json";
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
