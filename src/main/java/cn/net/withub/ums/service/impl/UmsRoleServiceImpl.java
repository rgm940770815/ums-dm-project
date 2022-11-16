/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsRoleMapper;
import cn.net.withub.ums.dao.UmsUserRoleMapper;
import cn.net.withub.ums.entity.UmsRole;
import cn.net.withub.ums.entity.UmsRoleCriteria;
import cn.net.withub.ums.entity.UmsUserRoleCriteria;
import cn.net.withub.ums.entity.UmsUserRoleKey;
import cn.net.withub.ums.service.UmsRoleService;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    @Autowired
    private UmsRoleMapper roleMapper;

    @Autowired
    private UmsUserRoleMapper userRoleMapper;

    @Override
    public int insert(UmsRole entity) {
        return roleMapper.insert(entity);
    }

    @Override
    public int delete(UmsRole entity) {
        return roleMapper.deleteByPrimaryKey(entity.getId());
    }

    @Override
    public int deleteById(Object id) {
        return roleMapper.deleteByPrimaryKey(new Integer(id.toString()));
    }

    @Override
    public int update(UmsRole entity) {
        return roleMapper.updateByPrimaryKey(entity);
    }

    @Override
    public UmsRole selectById(Object id) {
        return roleMapper.selectByPrimaryKey(new Integer(id.toString()));
    }

    @Override
    public int countAll() {
        UmsRoleCriteria criteria = new UmsRoleCriteria();
        criteria.createCriteria().getAllCriteria();
        return roleMapper.countByExample(criteria);
    }

    @Override
    public List<UmsRole> search(UmsRoleCriteria criteria, RowBounds rowBounds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count(UmsRoleCriteria criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int replaceUserRoles(final String userId, List<Integer> roleIds) {
        UmsUserRoleCriteria criteria = new UmsUserRoleCriteria();
        criteria.createCriteria().andUserIdEqualTo(userId);
        userRoleMapper.deleteByExample(criteria);

        int result = 0;
        if (roleIds != null) {
            for (final Integer roleId : roleIds) {
                if (roleId == null) {
                    continue;
                }
                result += userRoleMapper.insert(new UmsUserRoleKey() {
                    {
                        setUserId(userId);
                        setRoleId(roleId);
                    }
                });
            }
        }

        return result;
    }

    @Override
    public List<UmsRole> allRoles() {
        UmsRoleCriteria criteria = new UmsRoleCriteria();
        criteria.createCriteria().getAllCriteria();

        return roleMapper.selectByExample(criteria);
    }

}
