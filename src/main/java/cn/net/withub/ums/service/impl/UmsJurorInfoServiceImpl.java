/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsJurorInfoMapper;
import cn.net.withub.ums.entity.UmsJurorInfo;
import cn.net.withub.ums.entity.UmsJurorInfoCriteria;
import cn.net.withub.ums.service.UmsJurorInfoService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsJurorInfoServiceImpl implements UmsJurorInfoService {

    @Autowired
    private UmsJurorInfoMapper jurorInfoMapper;

    @Override
    public int insert(UmsJurorInfo entity) {
        return jurorInfoMapper.insert(entity);
    }

    @Override
    public int delete(UmsJurorInfo entity) {
        return jurorInfoMapper.deleteByPrimaryKey(entity.getUserId());
    }

    @Override
    public int deleteById(Object id) {
        return jurorInfoMapper.deleteByPrimaryKey(id.toString());
    }

    @Override
    public int update(UmsJurorInfo entity) {
        return jurorInfoMapper.updateByPrimaryKey(entity);
    }

    @Override
    public UmsJurorInfo selectById(Object id) {
        return jurorInfoMapper.selectByPrimaryKey(id.toString());
    }

    @Override
    public int countAll() {
        UmsJurorInfoCriteria criteria = new UmsJurorInfoCriteria();
        criteria.createCriteria().getAllCriteria();

        return jurorInfoMapper.countByExample(criteria);
    }

    @Override
    public List<UmsJurorInfo> search(UmsJurorInfoCriteria criteria, RowBounds rowBounds) {
        return jurorInfoMapper.selectByExample(criteria, rowBounds);
    }

    @Override
    public int count(UmsJurorInfoCriteria criteria) {
        return jurorInfoMapper.countByExample(criteria);
    }

    @Override
    public boolean hasJurorId(String userId) {
        UmsJurorInfoCriteria criteria = new UmsJurorInfoCriteria();
        criteria.createCriteria().andUserIdEqualTo(userId);
        return jurorInfoMapper.countByExample(criteria) > 0;
    }

    @Override
    public int updateByPrimaryKeySelective(UmsJurorInfo user) {

        return jurorInfoMapper.updateByPrimaryKeySelective(user);

    }

    @Override
    public int insertNoAspect(UmsJurorInfo entity) {

        return jurorInfoMapper.insert(entity);

    }


}
