package cn.net.withub.ums.action.institution;


import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsCommunicationInformationService;
import cn.net.withub.ums.service.UmsCourtFullService;
import cn.net.withub.ums.service.UmsDepartmentService;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;


/*
单位(部门)通信信息
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/communication")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class UmsCommunicationInformationAction {

    private Object data;

    @Autowired
    public UmsCommunicationInformationService service;

    @Autowired
    private UmsCourtFullService courtFullService;

    @Autowired
    private UmsDepartmentService departmentService;

    private UmsCommunicationInformation information;

    public Integer start;

    public Integer limit;

    @Action("insert")
    public String insert(){

        //所以信息不能都为空
        Map<String,Object> returnMap = new HashMap<>();
        try {

            if (information != null && information.getCourtNo() != null){

                //所有信息点不能全部为空
                boolean flag = false;
                Field[] declaredFields = UmsCommunicationInformation.class.getDeclaredFields();
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
                        information.setInsertTime(new Date());

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

    @Action("update")
    public String update(){

        Map<String,Object> returnMap = new HashMap<>();

        try {
            if(information != null && StringUtils.hasText(information.getId()) ){
                int i = service.updateByPrimaryKeySelective(information);
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

    @Action("query")
    public String query(){


        try {
            if(information != null ){

                if(StringUtils.hasText(information.getId())){
                    data = service.selectByPrimaryKey(information.getId());
                }else{
                    boolean flag = false;
                    UmsCommunicationInformationCriteria example = new UmsCommunicationInformationCriteria();
                    UmsCommunicationInformationCriteria.Criteria criteria = example.createCriteria();
                    example.setOrderByClause("insert_time asc");

                    if(StringUtils.hasText(information.getInstitutionId())){
                        flag = true;
                        criteria.andInstitutionIdEqualTo(information.getInstitutionId());
                    }
                    if(information.getCourtNo() != null){
                        flag = true;
                        criteria.andCourtNoEqualTo(information.getCourtNo());
                    }
                    if(information.getDeptNo() != null){
                        flag = true;
                        criteria.andDeptNoEqualTo(information.getDeptNo());
                    }else{
                        criteria.andDeptNoIsNull();
                    }

                    if(flag){
                        if(start != null){
                            example.setOffset(start);
                        }
                        if(limit != null){
                            example.setLimit(limit);
                        }
                        Map<String,Object> returnMap = new HashMap<>();
                        returnMap.put("rows",service.selectByExample(example));
                        returnMap.put("results",service.countByExample(example));
                        data = returnMap;
                    }


                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "json";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public UmsCommunicationInformation getInformation() {
        return information;
    }

    public void setInformation(UmsCommunicationInformation information) {
        this.information = information;
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


