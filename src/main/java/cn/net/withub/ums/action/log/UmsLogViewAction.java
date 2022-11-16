/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.log;

import cn.net.withub.ums.entity.UmsLogView;
import cn.net.withub.ums.entity.UmsLogViewCriteria;
import cn.net.withub.ums.service.UmsLogViewService;
import cn.net.withub.ums.util.StringTools;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
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
@Namespace("/log")
@Results({
    @Result(name = "json", type = "json", params = {"root", "data"})
})
public class UmsLogViewAction {

    @Autowired
    private UmsLogViewService logViewService;

    private Integer start;
    private Integer limit;

    private String field;
    private String direction;

    private Boolean isLoginLog;

    private Object data;

    @Action("search")
    public String search() {
        Map<String, Object> map = new HashMap<>();
        data = map;

        if (!StringTools.isNullOrEmpty(field) && field.endsWith("Text")) {
            field = field.substring(0, field.length() - 4);
        }

        UmsLogViewCriteria criteria = new UmsLogViewCriteria();
        UmsLogViewCriteria.Criteria c = criteria.createCriteria();

        if (isLoginLog != null) {
            if (isLoginLog) {
                c.andOpTypeEqualTo(0);
            } else {
                c.andOpTypeNotEqualTo(0);
            }
        }

        RowBounds rowBounds = new RowBounds(start, limit);

        criteria.setOrderByClause(StringTools.camelOrPascalToUnderline(field) + " " + direction);

        List<UmsLogView> list = logViewService.search(criteria, rowBounds);
        int total = logViewService.count(criteria);

        map.put("rows", list);
        map.put("results", total);
        map.put("success", true);

        return "json";
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getIsLoginLog() {
        return isLoginLog;
    }

    public void setIsLoginLog(Boolean isLoginLog) {
        this.isLoginLog = isLoginLog;
    }

}
