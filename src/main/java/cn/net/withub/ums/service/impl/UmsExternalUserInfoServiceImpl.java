/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsExternalUserInfoMapper;
import cn.net.withub.ums.entity.UmsExternalUserInfo;
import cn.net.withub.ums.entity.UmsExternalUserInfoCriteria;
import cn.net.withub.ums.service.UmsExternalUserInfoService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsExternalUserInfoServiceImpl implements UmsExternalUserInfoService {

    @Autowired
    private UmsExternalUserInfoMapper externalUserInfoMapper;

    @Override
    public int insert(UmsExternalUserInfo entity) {
        return externalUserInfoMapper.insert(entity);
    }
    public int insertSelective(UmsExternalUserInfo entity) {
        return externalUserInfoMapper.insertSelective(entity);
    }

    @Override
    public int delete(UmsExternalUserInfo entity) {
        return externalUserInfoMapper.deleteByPrimaryKey(entity.getUserId());
    }

    @Override
    public int deleteById(Object id) {
        return externalUserInfoMapper.deleteByPrimaryKey((String) id);
    }

    @Override
    public int update(UmsExternalUserInfo entity) {
        return externalUserInfoMapper.updateByPrimaryKey(entity);
    }
    public int updateByPrimaryKeySelective(UmsExternalUserInfo entity) {
        return externalUserInfoMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public UmsExternalUserInfo selectById(Object id) {
        return externalUserInfoMapper.selectByPrimaryKey((String) id);
    }

    @Override
    public int countAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UmsExternalUserInfo> search(UmsExternalUserInfoCriteria criteria, RowBounds rowBounds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count(UmsExternalUserInfoCriteria criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateEnabled(UmsExternalUserInfo info) {
        return externalUserInfoMapper.updateEnabled(info);
    }
}
