/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.auth;

import cn.net.withub.ums.entity.UmsRole;
import cn.net.withub.ums.service.UmsRoleService;
import cn.net.withub.ums.util.StringTools;
import java.util.HashMap;
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
 *
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/auth/role")
@Results({
    @Result(name = "json", type = "json", params = {"root", "data"})
})
public class RoleSaveAction {

    @Autowired
    private UmsRoleService roleService;

    private Object data;

    private Integer id;
    private Integer sortNo;
    private String roleName;
    private Integer courtNo;
    private Integer courtStdNo;
    private Integer deptNo;
    private Integer areaNo;
    private Integer authCode;
    private String authCodeList;

    @Action("save")
    public String save() {
        Map<String, Object> map = new HashMap<>();
        data = map;

        if (StringTools.isNullOrEmpty(roleName)) {
            map.put("success", false);
            map.put("msg", "内容验证未通过");

            return "json";
        }

        authCode = encode(authCodeList);

        UmsRole role = new UmsRole();
        role.setId(id);
        role.setSortNo(sortNo);
        role.setRoleName(roleName);
        role.setCourtNo(courtNo);
        role.setCourtStdNo(courtStdNo);
        role.setDeptNo(deptNo);
        role.setAreaNo(areaNo);
        role.setAuthCode(authCode);

        int result = 0;
        try {
            if (id == null) {
                result = roleService.insert(role);
            } else {
                result = roleService.update(role);
            }

            map.put("success", true);
            map.put("msg", "保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("msg", "内容验证未通过");
        } finally {
            map.put("result", result);
        }

        return "json";
    }

    private Integer encode(String codes) {
        Integer code = 0;

        for (String c : codes.split(",")) {
            code |= new Integer(c);
        }

        return code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    public Integer getCourtStdNo() {
        return courtStdNo;
    }

    public void setCourtStdNo(Integer courtStdNo) {
        this.courtStdNo = courtStdNo;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public Integer getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(Integer areaNo) {
        this.areaNo = areaNo;
    }

    public Integer getAuthCode() {
        return authCode;
    }

    public void setAuthCode(Integer authCode) {
        this.authCode = authCode;
    }

    public String getAuthCodeList() {
        return authCodeList;
    }

    public void setAuthCodeList(String authCodeList) {
        this.authCodeList = authCodeList;
    }

}
