/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsExternalJobTypeMapper;
import cn.net.withub.ums.entity.UmsExternalJobType;
import cn.net.withub.ums.entity.UmsExternalJobTypeCriteria;
import cn.net.withub.ums.service.UmsExternalJobTypeService;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmsExternalJobTypeServiceImpl implements UmsExternalJobTypeService {

    @Autowired
    private UmsExternalJobTypeMapper externalJobTypeMapper;

    @Override
    public int insert(UmsExternalJobType entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(UmsExternalJobType entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(UmsExternalJobType entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UmsExternalJobType selectById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UmsExternalJobType> search(UmsExternalJobTypeCriteria criteria, RowBounds rowBounds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count(UmsExternalJobTypeCriteria criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UmsExternalJobType> findAll() {
        UmsExternalJobTypeCriteria criteria = new UmsExternalJobTypeCriteria();
        criteria.createCriteria().getAllCriteria();
        return externalJobTypeMapper.selectByExample(criteria);
    }

    @Override
    public List<UmsExternalJobType> findBySrcId(Integer srcId) {
        UmsExternalJobTypeCriteria criteria = new UmsExternalJobTypeCriteria();
        criteria.createCriteria().andSrcIdEqualTo(srcId);
        return externalJobTypeMapper.selectByExample(criteria);
    }

    @Override
    public List<UmsExternalJobType> findByPid(Integer pid) {
        UmsExternalJobTypeCriteria criteria = new UmsExternalJobTypeCriteria();
        criteria.createCriteria().andPidEqualTo(pid);
        return externalJobTypeMapper.selectByExample(criteria);
    }

    @Override
    public List<UmsExternalJobType> findRootBySrcId(Integer srcId) {
        UmsExternalJobTypeCriteria criteria = new UmsExternalJobTypeCriteria();
        criteria.createCriteria().andSrcIdEqualTo(srcId).andPidEqualTo(0);
        return externalJobTypeMapper.selectByExample(criteria);
    }

}
