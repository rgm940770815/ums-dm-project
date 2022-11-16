package cn.net.withub.ums.action.institution;


import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsCourtFullService;
import cn.net.withub.ums.service.UmsDepartmentService;
import cn.net.withub.ums.service.UmsPartyOrganizationService;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/*
党组织情况
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/party")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class UmsPartyOrganizationAction {

    @Autowired
    UmsPartyOrganizationService service;

    @Autowired
    private UmsCourtFullService courtFullService;

    @Autowired
    private UmsDepartmentService departmentService;


    private UmsPartyOrganization information;

    private Object data;

    public Integer start;

    public Integer limit;


    @Action("query")
    public String select(){

        try {
            if (information != null){

                UmsPartyOrganizationCriteria example = new UmsPartyOrganizationCriteria();
                UmsPartyOrganizationCriteria.Criteria criteria = example.createCriteria();
                example.setOrderByClause(" update_time asc");
                boolean flag = false;
                if (information.getCourtNo() != null){
                    criteria.andCourtNoEqualTo(information.getCourtNo());
                    flag = true;
                }

                if(information.getDeptNo() != null){
                    criteria.andDeptNoEqualTo(information.getDeptNo());
                    flag = true;
                }else{
                    criteria.andDeptNoIsNull();
                }

                if(!flag){
                    return "json";
                }

                if(start != null){
                    example.setOffset(start);
                }
                if(limit != null){
                    example.setOffset(limit);
                }

                Map<String,Object> returnMap = new HashMap<>();

                returnMap.put("rows" , service.selectByExample(example));
                returnMap.put("results" , service.countByExample(example));

                data = returnMap;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "json";
    }


    @Action("insert")
    public String insert(){
        //所以信息不能都为空
        Map<String,Object> returnMap = new HashMap<>();
        try {
            if (information != null && information.getCourtNo() != null){
                //所有信息点不能全部为空
                boolean flag = false;
                Field[] declaredFields = UmsPartyOrganization.class.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    if(Modifier.isStatic(declaredField.getModifiers())){
                        continue;
                    }
                    declaredField.setAccessible(true);
                    if("institutionId".equals(declaredField.getName()) || "courtCode".equals(declaredField.getName())){
                        continue;
                    }
                    Object o = declaredField.get(information);
                    if(o instanceof String && StringUtils.hasText(o.toString())){
                        flag = true;
                        break;
                    }
                }
                if (flag){
                    UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
                    criteria.createCriteria().andCourtNoEqualTo(information.getCourtNo());
                    List<UmsCourtFull> umsCourtFulls = courtFullService.selectByExample(criteria);
                    if(umsCourtFulls.size() == 1){
                        UmsCourtFull umsCourtFull = umsCourtFulls.get(0);
                        information.setId(UUID.randomUUID().toString());
                        information.setCourtNo(umsCourtFull.getCourtNo());
                        information.setCourtStdNo(umsCourtFull.getCourtStdNo());
                        information.setCourtCode(umsCourtFull.getCourtCode());
                        information.setUpdateTime(new Date());
                        //如果没有部门是法院
                        Integer deptNo = information.getDeptNo();
                        int insert = 0;
                        if(  deptNo == null){
                            //法院
                            information.setInstitutionId(umsCourtFull.getId());
                            insert = service.insert(information);
                        }else {
                            //部门
                            UmsDepartmentCriteria departmentCriteria = new UmsDepartmentCriteria();
                            departmentCriteria.createCriteria().andCourtNoEqualTo(umsCourtFull.getCourtNo()).andDeptNoEqualTo(deptNo);
                            List<UmsDepartment> umsDepartments = departmentService.selectByExample(departmentCriteria);
                            if(umsDepartments.size() > 0){
                                information.setInstitutionId(umsDepartments.get(0).getId());
                                insert = service.insert(information);
                            }
                        }
                        if(insert > 0){
                            returnMap.put("success" , true);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = returnMap;
        return "json";
    }


    @Action("update")
    public String update(){

        Map<String,Object> returnMap = new HashMap<>();

        try {
            if(information != null && StringUtils.hasText(information.getId()) ){
                UmsPartyOrganization umsPartyOrganization = service.selectByPrimaryKey(information.getId());
                if(umsPartyOrganization == null){
                    returnMap.put("success", false);
                }else{
                    information.setInstitutionId(umsPartyOrganization.getInstitutionId());
                    information.setCourtCode(umsPartyOrganization.getCourtCode());
                    information.setCourtStdNo(umsPartyOrganization.getCourtStdNo());
                    information.setUpdateTime(new Date());
                    //如果把int 的值 由1改为0 用updateByPrimaryKeySelective更新不了
                    int i = service.updateByPrimaryKey(information);
//              int i  = service.updateByPrimaryKeySelective(information);
                    if(i > 0){
                        returnMap.put("success", true);
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        data = returnMap;
        return "json";
    }

    @Action("delete")
    public String delete(){

        Map<String,Object> returnMap = new HashMap<>();

        try {
            if(information != null && StringUtils.hasText(information.getId()) ){
                int i = service.deleteByPrimaryKey(information.getId());
                if(i > 0){
                    returnMap.put("success", true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        data = returnMap;
        return "json";
    }


    public UmsPartyOrganization getInformation() {
        return information;
    }

    public void setInformation(UmsPartyOrganization information) {
        this.information = information;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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
}
