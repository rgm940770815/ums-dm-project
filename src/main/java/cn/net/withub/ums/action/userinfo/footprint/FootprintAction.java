/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.userinfo.footprint;

import cn.net.withub.ums.service.footprint.FootprintService;
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
@Namespace("/userinfo/footprint")
@Results({
    @Result(name = "json", type = "json", params = {"root", "data"}),
    @Result(name = "timeline", location = "/board/personnel/info/footprint/timeline.jsp"),})
public class FootprintAction {

    @Autowired
    private FootprintService footprintHelper;

    private String userId;
    private Object data;

    @Action("footprints")
    public String footprints() {
        data = footprintHelper.userFootprints(userId);
        return "json";
    }

    @Action("timeline")
    public String timeline() {
        return "timeline";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
