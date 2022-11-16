/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.code;

import cn.net.withub.ums.auth.AuthorityHelper;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.UmsCourtFullMapper;
import cn.net.withub.ums.dao.extend.UmsCourtExtendMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsAuthorityService;
import cn.net.withub.ums.service.UmsCodeService;
import cn.net.withub.ums.service.UmsCodeTypeService;
import cn.net.withub.ums.service.UmsCourtFullService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编码列表
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
public class CodeListAction {

    @Autowired
    AuthorityHelper authorityHelper;
    @Autowired
    private UmsCodeService codeService;

    @Autowired
    private UmsCourtFullService courtFullService;

    @Autowired
    private UmsAuthorityService authorityService;

    @Autowired
    private UmsCourtExtendMapper courtExtendMapper;

    @Autowired
    UmsCodeTypeService codeTypeService;

    private Integer typeId;
    private Object data;
    private int provinceID;
    private int cityID;
    private String id;
    private String parentId;

    public int getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 通过编码类型ID获得相应编码列表
     * <br>
     * 法院列表仅取本地法院
     *
     * @return
     * @see cn.net.withub.ums.service.impl.UmsCodeServiceImpl#selectCodesByType(java.lang.Integer)
     */
    @Action("codeListByType")
    public String codeListByType() {
        List<UmsCode> list;
        if (typeId == 1) {
            Map map = new HashMap();
            list = new ArrayList<>();
            List<Integer> courtNoList = accessibleCourtNoList("查看");
            List<UmsCourtFull> courtList = null;
            try {
                courtList = courtFullService.selectByList(courtNoList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (UmsCourtFull court : courtList) {
                UmsCode code = new UmsCode();
                code.setId(court.getCourtNo() + "");
                code.setTypeId(1);
                code.setCodeName(court.getCourtStdName());
                code.setCourtCode(court.getCourtCode());
                code.setParentId(court.getParentId());
                list.add(code);
            }
            UmsUserInfoView uuiv = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
            map.put("auth", false);
            map.put("data", list);
            map.put("value", uuiv.getCourtNo());
            //管理员权限
            List<UmsRole> roles = authorityService.userRoles(uuiv.getId());
            if(roles != null){
                for (UmsRole role : roles) {
                    boolean adminRole = authorityService.isAdminRole(role);
                    if(adminRole){
                        map.put("auth", true);
                        break;
                    }
                }
            }
            data = map;
        } else if (typeId == 127) {
            list = codeService.selectCodesByType(typeId, parentId);
            data = list;
        } else {
            list = codeService.selectCodesByType(typeId);
            data = list;
        }
        return "json";
    }


    @Action("codeListByTypeWithNoAspect")
    public String codeListByTypeWithNoAspect() {

        List<UmsCode> list;
        Map map = new HashMap();
        list = new ArrayList<>();

        List<UmsCourtFull> courtList = null;
        try {
            courtList = courtFullService.selectByListAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (UmsCourtFull court : courtList) {
            UmsCode code = new UmsCode();
            code.setId(court.getCourtNo() + "");
            code.setTypeId(1);
            code.setCodeName(court.getCourtStdName());
            code.setCourtCode(court.getCourtCode());
            list.add(code);
        }

        map.put("data", list);
        data = map;
        return "json";
    }


    @Action("getDetailArea")
    public String getDetailArea() {

        List<Map<String, Object>> list = new ArrayList<>();

        UmsCodeCriteria example = new UmsCodeCriteria();
        UmsCodeCriteria.Criteria criteria = example.createCriteria();
        criteria.andTypeIdEqualTo(112);
        if (StringUtils.hasText(id)) {
            criteria.andParentIdEqualTo(id);
        } else {
            criteria.andParentIdIsNull();
        }
        List<UmsCode> umsCodes = codeService.selectByExample(example);

        for (UmsCode umsCode : umsCodes) {
            Map<String, Object> inner = new HashMap<>();
            inner.put("id", umsCode.getId());
            inner.put("text", umsCode.getCodeName());
            //查询是否有子叶
            example.clear();
            UmsCodeCriteria.Criteria f = example.createCriteria();
            f.andTypeIdEqualTo(112);
            f.andParentIdEqualTo(umsCode.getId());
            int count = codeService.count(example);
            inner.put("leaf", count == 0);
            list.add(inner);
        }
        data = list;

        return "json";
    }

    private void authCheck(Object criteria) {
        UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

        List<Integer> courtNoList = accessibleCourtNoList("查看");


    }

    public List<Integer> accessibleCourtNoList(String authName) {
        // 获取查看或编辑权限
        UmsAuth auth = authorityService.getAuthByName(authName);
        return accessibleCourtNoList(auth);
    }


    public List<Integer> accessibleCourtNoList(UmsAuth auth) {
        UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        // 该人员所在法院
        Integer courtNo = u.getCourtNo();
        // 根据用户id获取用户拥有的角色
        List<UmsRole> roles = authorityService.userRoles(u.getId());
        // 查询所有法院
        List<UmsCourtFull> umsCourtFullList = courtFullService.selectByListAll();
        // 拥有权限的法院列表
        List<Integer> courtNoList = new ArrayList<>();
        // 没有角色就是基本角色，而且只能看
        if (roles.isEmpty() && auth.getAuthName().equals("查看")) {
            // 法院列表应只包含自己所在法院
            courtNoList.add(courtNo);
        } else {
            for (UmsRole role : roles) {
                // 总体逻辑是，权限是累加的。
                // 是否有查看或编辑权限
                boolean ckOrBjFlag = authorityService.hasAuth(role.getAuthCode(), auth);
                if (ckOrBjFlag) {
                    // 是否是超级管理员
                    boolean superuserFlag = authorityService.isAdminRole(role);
                    if (superuserFlag) {
                        for (UmsCourtFull umsCourt : umsCourtFullList) {
                            // 超级管理员，添加所有法院
                            courtNoList.add(umsCourt.getCourtNo());
                        }
                    }
                    // 权限表里，法院代码为空、部门代码为空、区域代码为空、法院标准代码为空，就只能看自己
                    boolean flag = authorityService.isSelfRole(role);
                    // 只能看自己
                    if (flag) {
                        courtNoList.add(courtNo);
                    }
                    // 是否有区域限制
                    if (role.getAreaNo() != null) {
                        courtNoList.addAll(courtExtendMapper.areaCourtNoList(role.getAreaNo()));
                    }
                    // 是否有法院权限
                    if (role.getCourtNo() != null) {
                        courtNoList.add(role.getCourtNo());
                    }
                }
            }
        }
        return courtNoList;
    }

    /**
     * 获取省份列表
     *
     * @return
     */
    @Action("getProvince")
    public String getProvince() {
        List<Map<String, Object>> list = null;
        list = codeService.getProvince();
        data = list;
        return "json";
    }

    /**
     * 获取城市列表
     *
     * @return
     */
    @Action("getCity")
    public String getCity() {
        List<Map<String, Object>> list = null;
        if (provinceID != 0) {
            list = codeService.getCity(provinceID);
        }
        data = list;
        return "json";
    }

    /**
     * 获取区县列表
     *
     * @return
     */
    @Action("getArea")
    public String getArea() {
        List<Map<String, Object>> list = null;
        System.out.println(cityID);
        if (cityID != 0) {
            list = codeService.getArea(cityID);
        }
        data = list;
        return "json";
    }

    @Action("getCodeWithNotNull")
    public String getCodeWithNotNull() {
        List<Map> codeList = codeTypeService.getAllCodeType();
        data = codeList;
        return "json";
    }

    @Action("getCodeWithNotNullForBw")
    public String getCodeWithNotNullForBw() {
        List<Map<String, Object>> allCodeTypeForBw = codeTypeService.getAllCodeTypeForBw();
        //添加额外的编外信息
        Map<String, Object> u1 = new HashMap<>();
        u1.put("ID", 800);
        u1.put("type_name", "编外人员来源");
        u1.put("column_name", "enter_src");
        allCodeTypeForBw.add(u1);
        Map<String, Object> u2 = new HashMap<>();
        u2.put("ID", 801);
        u2.put("type_name", "公司名称");
        u2.put("column_name", "company_info_id");
        allCodeTypeForBw.add(u2);
        data = allCodeTypeForBw;
        return "json";
    }

    @Action("getCodeWithNotNullForPsy")
    public String getCodeWithNotNullForPsy() {
        List<Map<String, Object>> psy = codeTypeService.getCodeWithNotNullForPsy();
        data = psy;
        return "json";
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
