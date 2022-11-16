/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.auth;

import cn.net.withub.ums.entity.UmsRole;
import cn.net.withub.ums.entity.UmsRoleView;
import cn.net.withub.ums.entity.UmsRoleViewCriteria;
import cn.net.withub.ums.log.UmsLogger;
import cn.net.withub.ums.service.UmsAuthorityService;
import cn.net.withub.ums.service.UmsRoleService;
import cn.net.withub.ums.service.UmsRoleViewService;
import cn.net.withub.ums.util.StringTools;
import org.apache.ibatis.session.RowBounds;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/auth/role")
@Results({
    @Result(name = "json", type = "json", params = {"root", "data"})
})
public class RoleAction {

    @Autowired
    private UmsRoleViewService roleViewService;

    @Autowired
    private UmsRoleService roleService;

    @Autowired
    private UmsAuthorityService authorityService;

    @Autowired
    private UmsLogger umsLogger;

    private UmsRole role;

    private Integer start = 0;
    private Integer limit = 20;

    private String sortField;
    private String sortDirection;
    private Integer id;

    private Integer roleId;
    private String userId;

    private List<Integer> roleIds;

    private Object data;

    @Action("all")
    public String showAllRoles() {
        Map<String, Object> map = new HashMap<>();
        data = map;

        RowBounds rowBounds = new RowBounds(start, limit);
        UmsRoleViewCriteria criteria = new UmsRoleViewCriteria();

        criteria.createCriteria().getAllCriteria();

        if (!StringTools.isNullOrEmpty(sortField) && !StringTools.isNullOrEmpty(sortDirection)) {
            if (sortField.endsWith("Text")) {
                sortField = sortField.substring(0, sortField.length() - 4);
            }
            criteria.setOrderByClause(StringTools.camelOrPascalToUnderline(sortField) + " " + sortDirection);
        }

        map.put("success", true);
        map.put("rows", roleViewService.search(criteria, rowBounds));
        map.put("results", roleViewService.count(criteria));

        return "json";
    }

    @Action("allRolesForEnum")
    public String allRolesForEnum() {

        Map<String, String> enumMap = new TreeMap<>();

        for (final UmsRole r : roleService.allRoles()) {
            enumMap.put(r.getId().toString(), r.getRoleName());
        }

        data = enumMap;

        return "json";
    }

//    @Action("save")
//    public String saveRoles() {
//        Map<String, Object> map = new HashMap<>();
//        data = map;
//
//        if (role != null) {
//            if (StringTools.isNullOrEmpty(role.getRoleName()) || role.getAuthCode() == null) {
//                map.put("success", false);
//                map.put("msg", "内容验证未通过");
//
//                return "json";
//            }
//        }
//
//        int result = 0;
//        try {
//            if (role.getId() == null) {
//                role.setId(id);
//                result = roleService.insert(role);
//            } else {
//                result = roleService.update(role);
//            }
//
//            map.put("success", true);
//            map.put("msg", "保存成功！");
//        } catch (Exception e) {
//            e.printStackTrace();
//            map.put("success", false);
//            map.put("msg", "内容验证未通过");
//        } finally {
//            map.put("result", result);
//        }
//
//        return "json";
//    }
    @Action("delete")
    public String deleteRoles() {
        Map<String, Object> map = new HashMap<>();
        data = map;
        int result = 0;

        if (id != null) {
            System.out.println("----------------->ID:" + id);
            result = roleService.deleteById(id);
        }

        map.put("success", result > 0);
        map.put("result", result);
        return "json";
    }

    @Action("userRoles")
    public String userRoles() {
        data = authorityService.userRoles(userId);
        return "json";
    }

    @Action("replaceUserRoles")
    public String replaceUserRoles() {
        Map<String, Object> map = new HashMap<>();
        data = map;

        try {

            map.put("result", roleService.replaceUserRoles(userId, roleIds));

            map.put("success", true);
            map.put("msg", "赋权成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("msg", "内容验证未通过");
        }

        return "json";
    }

    @Action("listAll")
    public String listAllRole() {
        List<UmsRoleView> list = roleViewService.getAll();
        data = list;
        return "json";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public UmsRole getRole() {
        return role;
    }

    public void setRole(UmsRole role) {
        this.role = role;
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

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

}
