/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.auth;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.extend.UmsCourtExtendMapper;
import cn.net.withub.ums.entity.UmsAuth;
import cn.net.withub.ums.entity.UmsRole;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.service.UmsAuthorityService;
import cn.net.withub.ums.util.ReflectionTools;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限辅助
 * <br>
 * CourtNo集合中，-9999代表超级管理员，-1代表基本权限
 *
 * @author Diluka
 */
@Component
public class AuthorityHelper {

    @Autowired
    private UmsAuthorityService authorityService;

    @Autowired
    private UmsCourtExtendMapper courtExtendMapper;

    /**
     * 实体类检查
     *
     * @param entity
     * @return
     */
    public boolean entityCheck(Object entity) {
        UmsAuth editAuth = authorityService.getAuthByName("编辑");

        List<Integer> courtNoList = accessibleCourtNoList(editAuth);

        if (courtNoList.isEmpty()) {
            return false; //没有权限
        }

        if (courtNoList.contains(-9999)) {
            return true;
        } else if (isSelfOnly(courtNoList)) {
            UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
            if (ReflectionTools.hasField(entity, "userId")) {
                return u.getId().equalsIgnoreCase((String) ReflectionTools.getField(entity, "userId"));
            } else {
                return u.getId().equalsIgnoreCase((String) ReflectionTools.getField(entity, "id"));
            }
        }

        try {
            Field courtNoField = entity.getClass().getDeclaredField("courtNo");
            courtNoField.setAccessible(true);

            Integer courtNo = (Integer) courtNoField.get(entity);

            if (courtNoList.contains(courtNo)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    /**
     * 查询Criteria附加限制条件
     *
     * @param criteria
     */
    public void selectRestriction(Object criteria) {
        UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

        List<Integer> courtNoList = accessibleCourtNoList("查看");

        if (courtNoList.contains(-9999)) {//管理员权限 GOGOGO
            return;
        } else if (isSelfOnly(courtNoList)) {//仅自己
            try {
                //附表
                Method andUserIdEqualTo = criteria.getClass().getDeclaredMethod("andUserIdEqualTo", String.class);
                andUserIdEqualTo.invoke(criteria, u.getId());
            } catch (NoSuchMethodException e) {
                try {
                    //主表
                    Method andIdEqualTo = criteria.getClass().getDeclaredMethod("andIdEqualTo", String.class);
                    andIdEqualTo.invoke(criteria, u.getId());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return;
        } else {//其他
            try {
                Method andCourtNoIn = criteria.getClass().getDeclaredMethod("andCourtNoIn", List.class);
                andCourtNoIn.invoke(criteria, courtNoList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获取当前用户某个权限可以操作的法院编号列表
     *
     * @param authName 某个权限的名称
     * @return
     */
    public List<Integer> accessibleCourtNoList(String authName) {
        UmsAuth auth = authorityService.getAuthByName(authName);
        return accessibleCourtNoList(auth);
    }

    /**
     * 获取当前用户某个权限可以操作的法院编号列表
     *
     * @param auth 某个权限
     * @return
     */
    public List<Integer> accessibleCourtNoList(UmsAuth auth) {
        UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

        List<UmsRole> roles = authorityService.userRoles(u.getId());

        List<Integer> courtNoList = new ArrayList<>();
        if (roles.isEmpty() && auth.getAuthName().equals("查看")) {//没有角色就是基本角色，而且只能看
            courtNoList.add(-1);
        }
        for (UmsRole role : roles) {
            if (authorityService.hasAuth(role.getAuthCode(), auth)) { //有权限

                if (authorityService.isAdminRole(role)) {
                    courtNoList.add(-9999);//无限制
                    return courtNoList;
                } else if (authorityService.isSelfRole(role)) {//加上自身的限制

                    courtNoList.add(-1);
                    //courtNoList.add(u.getCourtNo());

                } else {

                    if (role.getAreaNo() != null) {//有区域权限
                        courtNoList.addAll(courtExtendMapper.areaCourtNoList(role.getAreaNo()));
                    }

                    if (role.getCourtNo() != null) {//法院权限
                        courtNoList.add(role.getCourtNo());
                    }
                }
            }
        }

        return courtNoList;
    }

    /**
     * 是否仅包含基本权限
     *
     * @param courtNoList
     * @return
     */
    public boolean isSelfOnly(List<Integer> courtNoList) {
        return courtNoList.size() == 1 && courtNoList.get(0) == -1;
    }

    /**
     * 权限类型
     *
     * @param userId
     * @return
     */
    public AuthType getAuthType(String userId) {
        // 获取用户拥有的角色
        List<UmsRole> roles = authorityService.userRoles(userId);
        if ((roles.isEmpty()) || (roles.size() == 1 && authorityService.isSelfRole(roles.get(0)))) {
            return AuthType.PERSON;
        }
        for (UmsRole role : roles) {
            // 判断是否是超级管理员角色
            if (authorityService.isAdminRole(role)) {
                return AuthType.ADMIN;
            }
        }
        // 各个法院的管理员
        return AuthType.COURT;
    }
}
