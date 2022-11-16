/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.code;

import cn.net.withub.ums.entity.UmsCourt;
import cn.net.withub.ums.entity.UmsCourtFull;
import cn.net.withub.ums.entity.UmsCourtFullCriteria;
import cn.net.withub.ums.service.UmsCourtFullService;
import cn.net.withub.ums.service.UmsCourtService;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 法院
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
public class CourtAction {

    @Autowired
    private UmsCourtService courtService;

    @Autowired
    private UmsCourtFullService courtFullService;

    private UmsCourtFull paramInfo;


    private Object data;

    @Action("courtList")
    public String listAll() {
        List<UmsCourt> list = courtService.listAll();
        for (UmsCourt c : list) {
            if (c.getCourtStdName().startsWith("重庆市")) {
                continue;
            }
            c.setCourtStdName("重庆市" + c.getCourtStdName());
        }
        data = list;
        return "json";
    }

    @Action("courtList2")
    public String listAll2() {
        List<UmsCourtFull> list = courtFullService.listAllCourts();
        data = list;
        return "json";
    }

    @Action("areaList")
    public String areaList() {
        List<UmsCourtFull> list = courtFullService.listArea();
        data = list;
        return "json";
    }

    @Action("courtAndAreaList")
    public String courtAndAreaList() {
        List<UmsCourtFull> list = courtFullService.listAll();
        data = list;
        return "json";
    }

    @Action("getCourtByNo")
    public String getCourtByNo(){

        if(paramInfo != null && paramInfo.getCourtNo() != null){

            UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
            criteria.createCriteria().andCourtNoEqualTo(paramInfo.getCourtNo());
            List<UmsCourtFull> umsCourtFulls = courtFullService.selectByExample(criteria);

            if(umsCourtFulls.size() == 1){
                Map<String,Object> returnMap = new HashMap<>();
                UmsCourtFull umsCourtFull = umsCourtFulls.get(0);
                returnMap.put("info", umsCourtFull);
                //获取父级机构
                if(umsCourtFull.getParentId() == null && umsCourtFull.getCourtCode().contains("00")){
                    returnMap.put("parentName" , "最高人民法院");
                }else{
                    criteria.clear();
                    criteria.createCriteria().andIdEqualTo(umsCourtFull.getParentId());
                    List<UmsCourtFull> umsCourtFulls1 = courtFullService.selectByExample(criteria);
                    if(umsCourtFulls1.size() == 1){
                        returnMap.put("parentName" , umsCourtFulls1.get(0).getCourtStdName());
                    }
                }
                data = returnMap;

            }


        }

        return "json";
    }

    @Action("saveCourtInfo")
    public String saveCourtInfo(){

        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("success" ,false);
        data = returnMap;
        if(paramInfo != null && paramInfo.getCourtNo() != null){
            //先查后写
            UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
            criteria.createCriteria().andCourtNoEqualTo( paramInfo.getCourtNo() );

            List<UmsCourtFull> umsCourtFulls = courtFullService.selectByExample(criteria);

            if(umsCourtFulls.size() == 1){
                UmsCourtFull umsCourtFull = umsCourtFulls.get(0);
                paramInfo.setCourtCode(umsCourtFull.getCourtCode());
                int i = courtFullService.updateByPrimaryKeySelective(paramInfo);
                if(i > 0){
                    returnMap.put("success" ,true);
                }

            }

        }

        return "json";
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public UmsCourtFull getParamInfo() {
        return paramInfo;
    }

    public void setParamInfo(UmsCourtFull paramInfo) {
        this.paramInfo = paramInfo;
    }
}
