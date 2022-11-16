/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.UmsRoleMapper;
import cn.net.withub.ums.dao.UmsRoleMenuAuthMapper;
import cn.net.withub.ums.dao.extend.UmsAuthorityMapper;
import cn.net.withub.ums.dao.extend.UmsCourtExtendMapper;
import cn.net.withub.ums.entity.UmsAuth;
import cn.net.withub.ums.entity.UmsRole;
import cn.net.withub.ums.entity.UmsRoleMenuAuthCriteria;
import cn.net.withub.ums.entity.UmsRoleMenuAuthKey;
import cn.net.withub.ums.service.UmsAuthorityService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmsAuthorityServiceImpl implements UmsAuthorityService {

    @Autowired
    private UmsAuthorityMapper authorityMapper;

    @Autowired
    private UmsCourtExtendMapper courtExtendMapper;

    @Autowired
    private UmsRoleMenuAuthMapper roleMenuAuthMapper;

    @Autowired
    private UmsRoleMapper roleMapper;

    @Override
    public List<UmsRole> userRoles(String userId) {
        return authorityMapper.userRoles(userId);
    }

    @Override
    public int addUserRoles(Integer roleId, String userId) {
        return authorityMapper.addUserRoles(roleId, userId);
    }

    @Override
    public int updateUserRoles(Integer roleId, String userId) {
        return authorityMapper.updateUserRoles(roleId, userId);
    }

    @Override
    public Map<String, Integer> userAuth(String userId) {
        Map<String, Integer> auths = new HashMap<>();
        List<UmsRole> roles = userRoles(userId);
        if (roles != null) {
            for (UmsRole role : roles) {
                roleAuth(auths, role);
            }
        }

        return auths;
    }

    @Override
    public List<UmsAuth> decodeAuths(Integer authCode) {
        List<UmsAuth> allAuths = authorityMapper.allAuths();

        List<UmsAuth> auths = new ArrayList<>();

        for (UmsAuth auth : allAuths) {
            if ((auth.getAuthCode() & authCode) == auth.getAuthCode()) {//检查权限
                auths.add(auth);
            }
        }

        return auths;
    }

    public void roleAuth(Map<String, Integer> auths, UmsRole role) {

        if (role != null) {

            String authKey = null;

            if (role.getDeptNo() != null) {//部门权限
                authKey = role.getCourtNo() + "," + role.getDeptNo();
                auths.put(authKey, (auths.get(authKey) == null ? 0 : auths.get(authKey)) | role.getAuthCode());
            } else if (role.getCourtNo() != null) {//法院权限
                authKey = role.getCourtNo() + "";
                auths.put(authKey, (auths.get(authKey) == null ? 0 : auths.get(authKey)) | role.getAuthCode());
            } else if (role.getCourtStdNo() != null) {//法院权限
                authKey = role.getCourtStdNo() + "";
                auths.put(authKey, (auths.get(authKey) == null ? 0 : auths.get(authKey)) | role.getAuthCode());
            }

            if (role.getAreaNo() != null) {//区域权限
                loadAreaAuth(auths, role.getAreaNo(), role.getAuthCode());
            }

            if (isSelfRole(role)) {//自己
                authKey = UmsConstant.SELF.toString();
                auths.put(authKey, (auths.get(authKey) == null ? 0 : auths.get(authKey)) | role.getAuthCode());
            }

            if (isAdminRole(role)) {//admin
                authKey = UmsConstant.ADMIN.toString();
                auths.put(authKey, (auths.get(authKey) == null ? 0 : auths.get(authKey)) | role.getAuthCode());
            }

        }
    }

    private void loadAreaAuth(Map<String, Integer> auths, Integer areaNo, Integer authCode) {

        List<Integer> courtNoList = courtExtendMapper.areaCourtNoList(areaNo);

        for (Integer cno : courtNoList) {
            String cno_str = cno.toString();
            auths.put(cno_str,
                    (auths.get(cno_str) == null ? 0 : auths.get(cno_str))
                    | authCode);
        }
    }

    @Override
    public UmsAuth getAuthByName(String authName) {
        return authorityMapper.selectAuthByName(authName);
    }

    @Override
    public boolean hasAuth(Integer authCode, UmsAuth auth) {
        return auth != null && (authCode & auth.getAuthCode()) == auth.getAuthCode();
    }

    @Override
    public boolean isAdminRole(UmsRole role) {
        try {
            return role.getAreaNo() == 9999 && role.getCourtNo() == 9999 && role.getDeptNo() == 9999;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isSelfRole(UmsRole role) {
        return role.getAreaNo() == null && role.getCourtNo() == null && role.getCourtStdNo() == null && role.getDeptNo() == null;
    }

    @Override
    public List<Integer> areaCourts(Integer areaNo) {
        return courtExtendMapper.areaCourtNoList(areaNo);
    }

    @Override
    public List<UmsRoleMenuAuthKey> roleMenuAuthByRoleId(Integer roleId) {
        UmsRoleMenuAuthCriteria criteria = new UmsRoleMenuAuthCriteria();
        criteria.createCriteria().andRoleIdEqualTo(roleId);
        return roleMenuAuthMapper.selectByExample(criteria);
    }

    @Override
    public Map<String, Integer> menuAuth(String userId) {
        List<UmsRole> roles = userRoles(userId);
        Map<String, Integer> menuAuth = new HashMap<>();

        for (UmsRole role : roles) {
            UmsRole r = role;

            while (r != null) {
                List<UmsRoleMenuAuthKey> authList = roleMenuAuthByRoleId(r.getId());

                for (UmsRoleMenuAuthKey ak : authList) {
                    menuAuth.put(ak.getMenuId(), (menuAuth.containsKey(ak.getMenuId()) ? menuAuth.get(ak.getMenuId()) : 0) | ak.getAuthCode());
                }

                r = roleMapper.selectByPrimaryKey(r.getPid());
            }

        }

        return menuAuth;
    }

}
