/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.code;

import cn.net.withub.ums.entity.UmsDepartment;
import cn.net.withub.ums.service.UmsDepartmentService;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 部门列表
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
public class DepartmentListAction {

    @Autowired
    private UmsDepartmentService departmentService;

    private Integer courtNo;
    private Boolean flag;
    private Object data;
    private Integer deptNo;
    private Integer orgType;
    private String fydm;

    /**
     * 通过法院编号获得部门列表
     *
     * @return
     */
    @Action("deptByCourtNo")
    public String departmentListByCourtNo() {
        if(courtNo == null && !StringUtils.hasText(fydm)){
            return "json";
        }

        List<UmsDepartment> list = null;
        if(courtNo != null){
            list = departmentService.selectByCourtNo(courtNo);
        }else if(StringUtils.hasText(fydm)){
            list = departmentService.selectByCourtNo(fydm);
        }

        if (list != null && flag != null && flag) {
            list.add(0, new UmsDepartment() {
                {
                    setDeptName("请选择");
                }
            });
        }
        data = list;

        return "json";
    }

    /**
     * 通过法院代码获得部门列表
     *
     * @return
     */
    @Action("deptByFydm")
    public String departmentListByFydm() {
        List<UmsDepartment> list = departmentService.selectByCourtNo(fydm);
        if (flag != null && flag) {
            list.add(0, new UmsDepartment() {
                {
                    setDeptName("请选择");
                }
            });
        }
        data = list;

        return "json";
    }


    @Action("deptDetailInfo")
    public String deptDetailInfo() {
        UmsDepartment dept = null;
        try {
            dept = departmentService.selectByCourtNoAndDeptNo(courtNo, deptNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = dept;
        return "json";
    }


    @Action("updateDeptInfo")
    public String updateDeptInfo() {
        boolean result = false;
        if(courtNo != null && deptNo != null && orgType != null){
            UmsDepartment department = null;
            try {
                department = new UmsDepartment();
                department.setCourtNo(courtNo);
                department.setDeptNo(deptNo);
                department.setOrgType(orgType);
                result = departmentService.updateByPrimaryKeySelective(department) > 0? true:false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        data = result;
        return "json";
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public Integer getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getFydm() {
        return fydm;
    }

    public void setFydm(String fydm) {
        this.fydm = fydm;
    }
}
