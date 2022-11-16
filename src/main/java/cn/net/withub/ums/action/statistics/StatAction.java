/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.statistics;

import cn.net.withub.ums.entity.UmsDepartment;
import cn.net.withub.ums.entity.UmsDepartmentCriteria;
import cn.net.withub.ums.entity.UmsUserInfoViewCriteria;
import cn.net.withub.ums.service.UmsDepartmentService;
import cn.net.withub.ums.service.UmsUserInfoViewService;
import cn.net.withub.ums.service.statistics.StatisticsService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/stat")
@Results({
    @Result(name = "json", type = "json", params = {"root", "data"})
})
public class StatAction {

    @Autowired
    private UmsUserInfoViewService userInfoViewService;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private UmsDepartmentService umsDepartmentService;



    private Object data;
    private Integer courtNo;

    @Action("newEnter")
    public String newEnteredUsers() {
        data = userInfoViewService.newEnterPersonnelList(10,courtNo);
        return "json";
    }

    @Action("recentAlt")
    public String recentAltUsers() {
        data = statisticsService.recentlyPositionAlteredPersonnelList(10);
        return "json";
    }
    @Action("loadCount")
    public String loadCount(){
        Map resultMap = new HashMap();
        Integer deptCount = umsDepartmentService.countByCourtNo(courtNo);
        resultMap.put("deptCount",deptCount);
        List<Map> userCountMap = userInfoViewService.countByCourtNo(courtNo);
        Long bwCount = 0l;
        for (Map m : userCountMap ) {
            if((Integer) m.get("user_type") == 1){
                if((Integer)m.get("gender")==1){
                    resultMap.put("zbMan",m.get("cot"));
                }else if ((Integer)m.get("gender")==2){
                    resultMap.put("zbWoman",m.get("cot"));
                }
            }else if((Integer) m.get("user_type") == 2){
                bwCount += (Long) m.get("cot");
            }
        }
        resultMap.put("bwCount",bwCount);
        data = resultMap;
        return "json";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }
}
