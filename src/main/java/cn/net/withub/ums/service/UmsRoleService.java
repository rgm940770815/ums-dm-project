/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsRole;
import cn.net.withub.ums.entity.UmsRoleCriteria;
import java.util.List;

/**
 *
 * @author Diluka
 */
public interface UmsRoleService extends BaseService<UmsRole, UmsRoleCriteria> {

    int replaceUserRoles(String userId, List<Integer> roleIds);

    List<UmsRole> allRoles();
}
