package cn.net.withub.ums.action.support4other;


import cn.net.withub.ums.service.UmsUserInfoViewService;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/RyListAction")
@Results({@Result(name = "json", type = "json", params = {"root", "data"})})
public class RyListAction {

    @Autowired
    UmsUserInfoViewService userInfoViewService;

    private Object data;


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private String type;

    private String fydm;

    private String xtpt_id;

    private List<String> zwlsit;


    public String getXtpt_id() {
        return xtpt_id;
    }

    public void setXtpt_id(String xtpt_id) {
        this.xtpt_id = xtpt_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFydm() {
        return fydm;
    }

    public void setFydm(String fydm) {
        this.fydm = fydm;
    }

    public List<String> getZwlsit() {
        return zwlsit;
    }

    public void setZwlsit(List<String> zwlsit) {
        this.zwlsit = zwlsit;
    }

    @Action("getRyList")
    public String getRyList() {
        Map<String,Object> map  = new HashMap<>();
        map.put("fydm",fydm);
        map.put("type",type);
        map.put("zwlist",zwlsit);
        map.put("xtpt_id",xtpt_id);
        List<Map<String,Object>> resList = userInfoViewService.selectRyList(map);

        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("success", true);
        returnMap.put("data", resList);

        data = returnMap;
        return "json";
    }


}
