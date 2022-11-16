/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.code;

import java.util.UUID;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Diluka
 */
@Controller
@ParentPackage("codeBase")
@Scope("prototype")
@Namespace("/code")
@Results({
    @Result(name = "json", type = "json", params = {"root", "data"})
})
public class UUIDAction {

    private Object data;

    @Action("newUUID")
    public String newUUID() {
        data = UUID.randomUUID().toString();
        return "json";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
