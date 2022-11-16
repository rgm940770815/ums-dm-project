/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsExternalCompanyInfoMapper;
import cn.net.withub.ums.entity.UmsExternalCompanyInfo;
import cn.net.withub.ums.entity.UmsExternalCompanyInfoCriteria;
import cn.net.withub.ums.service.UmsExternalCompanyInfoService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsExternalCompanyInfoServiceImpl implements UmsExternalCompanyInfoService {

    @Autowired
    private UmsExternalCompanyInfoMapper externalCompanyInfoMapper;

    @Override
    public List<UmsExternalCompanyInfo> findAll() {
        UmsExternalCompanyInfoCriteria criteria = new UmsExternalCompanyInfoCriteria();
        criteria.createCriteria().getAllCriteria();

        return externalCompanyInfoMapper.selectByExample(criteria);
    }

    @Override
    public List<UmsExternalCompanyInfo> findByName(String companyName) {
        UmsExternalCompanyInfoCriteria criteria = new UmsExternalCompanyInfoCriteria();
        criteria.createCriteria().andCompanyNameEqualTo(companyName);
        return externalCompanyInfoMapper.selectByExample(criteria);
    }

    @Override
    public int insert(UmsExternalCompanyInfo entity) {
        return externalCompanyInfoMapper.insert(entity);
    }
    public int insertSelective(UmsExternalCompanyInfo entity) {
        return externalCompanyInfoMapper.insertSelective(entity);
    }

    @Override
    public int delete(UmsExternalCompanyInfo entity) {
        return externalCompanyInfoMapper.deleteByPrimaryKey(entity.getId());
    }

    @Override
    public int deleteById(Object id) {
        return externalCompanyInfoMapper.deleteByPrimaryKey((Integer) id);
    }

    @Override
    public int update(UmsExternalCompanyInfo entity) {
        return externalCompanyInfoMapper.updateByPrimaryKey(entity);
    }

    @Override
    public UmsExternalCompanyInfo selectById(Object id) {
        return externalCompanyInfoMapper.selectByPrimaryKey((Integer) id);
    }

    @Override
    public int countAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UmsExternalCompanyInfo> search(UmsExternalCompanyInfoCriteria criteria, RowBounds rowBounds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count(UmsExternalCompanyInfoCriteria criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
