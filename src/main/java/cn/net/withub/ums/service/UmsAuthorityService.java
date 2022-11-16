/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsAuth;
import cn.net.withub.ums.entity.UmsRole;
import cn.net.withub.ums.entity.UmsRoleMenuAuthKey;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Diluka
 */
public interface UmsAuthorityService {

    /**
     * 用户角色
     *
     * @param userId
     * @return
     */
    List<UmsRole> userRoles(String userId);

    /**
     * 添加用户角色
     *
     * @param roleId 角色ID
     * @param userId 用户ID
     * @return
     */
    @Deprecated
    int addUserRoles(Integer roleId, String userId);

    /**
     * 修改用户角色
     *
     * @param roleId
     * @param userId
     * @return
     */
    @Deprecated
    int updateUserRoles(Integer roleId, String userId);

    /**
     * 用户权限表
     *
     * @param userId
     * @return
     */
    @Deprecated
    Map<String, Integer> userAuth(String userId);

    /**
     * 解码权限编码
     *
     * @param authCode
     * @return
     */
    List<UmsAuth> decodeAuths(Integer authCode);

    /**
     *
     * @param authName
     * @return
     */
    UmsAuth getAuthByName(String authName);

    /**
     *
     * @param authCode
     * @param auth
     * @return
     */
    boolean hasAuth(Integer authCode, UmsAuth auth);

    boolean isAdminRole(UmsRole role);

    boolean isSelfRole(UmsRole role);

    /**
     *
     * @param areaNo 大于等于7000的法院标准编号为区域编号
     * @return
     */
    List<Integer> areaCourts(Integer areaNo);

    List<UmsRoleMenuAuthKey> roleMenuAuthByRoleId(Integer roleId);

    Map<String, Integer> menuAuth(String userId);
}
