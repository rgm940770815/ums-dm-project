/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsPhotoMapper;
import cn.net.withub.ums.entity.UmsPhoto;
import cn.net.withub.ums.entity.UmsPhotoCriteria;
import cn.net.withub.ums.service.UmsPhotoService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UmsPhotoServiceImpl implements UmsPhotoService {

    @Autowired
    private UmsPhotoMapper photoMapper;

    @Override
    public int insert(UmsPhoto entity) {
        return photoMapper.insert(entity);
    }

    @Override
    public int delete(UmsPhoto entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(UmsPhoto entity) {
        return photoMapper.updateByPrimaryKeyWithBLOBs(entity);
    }

    @Override
    public int countAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UmsPhoto selectById(Object id) {
        return photoMapper.selectByPrimaryKey((String) id);
    }

    @Override
    public List<UmsPhoto> search(UmsPhotoCriteria criteria, RowBounds rowBounds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count(UmsPhotoCriteria criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteById(Object id) {
        return photoMapper.deleteByPrimaryKey((String) id);
    }

    @Override
    public List<UmsPhoto> selectByExample(UmsPhotoCriteria example,RowBounds rowBound) {
        return photoMapper.selectByExample(example,rowBound);
    }

    @Override
    public List<String> selectIsValidUser( RowBounds rowBounds) {
        return photoMapper.selectIsValidUser(rowBounds);
    }

    @Override
    public UmsPhoto selectByIdWithNOAspect(String id) {
        return photoMapper.selectByPrimaryKey( id);
    }

    @Override
    public List<Map> selectByDept(Map map) {
        return photoMapper.selectByDept(map);
    }
}
