/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsRoleViewMapper;
import cn.net.withub.ums.entity.UmsRoleView;
import cn.net.withub.ums.entity.UmsRoleViewCriteria;
import cn.net.withub.ums.service.UmsRoleViewService;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmsRoleViewServiceImpl implements UmsRoleViewService {

    @Autowired
    private UmsRoleViewMapper roleViewMapper;

    @Override
    public List<UmsRoleView> getAll() {
        UmsRoleViewCriteria criteria = new UmsRoleViewCriteria();
        criteria.createCriteria().getAllCriteria();
        return roleViewMapper.selectByExample(criteria);
    }

    @Override
    public int insert(UmsRoleView entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(UmsRoleView entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(UmsRoleView entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UmsRoleView selectById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countAll() {
        UmsRoleViewCriteria criteria = new UmsRoleViewCriteria();
        criteria.createCriteria().getAllCriteria();
        return roleViewMapper.countByExample(criteria);
    }

    @Override
    public List<UmsRoleView> search(UmsRoleViewCriteria criteria, RowBounds rowBounds) {
        return roleViewMapper.selectByExample(criteria, rowBounds);
    }

    @Override
    public int count(UmsRoleViewCriteria criteria) {
        return roleViewMapper.countByExample(criteria);
    }

}
