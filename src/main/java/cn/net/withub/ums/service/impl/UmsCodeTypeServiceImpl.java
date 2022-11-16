/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsCodeTypeMapper;
import cn.net.withub.ums.entity.UmsCodeType;
import cn.net.withub.ums.entity.UmsCodeTypeCriteria;
import cn.net.withub.ums.service.UmsCodeTypeService;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmsCodeTypeServiceImpl implements UmsCodeTypeService {

    @Autowired
    private UmsCodeTypeMapper codeTypeMapper;

    @Override
    public String codeTypeName(Integer typeId) {
        UmsCodeType type = codeTypeMapper.selectByPrimaryKey(typeId);
        return type != null ? type.getTypeName() : null;
    }

    @Override
    public int insert(UmsCodeType entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(UmsCodeType entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(UmsCodeType entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UmsCodeType selectById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UmsCodeType> search(UmsCodeTypeCriteria criteria, RowBounds rowBounds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count(UmsCodeTypeCriteria criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Map> getAllCodeType()
    {
        return codeTypeMapper.getAllCodeType();
    }

    @Override
    public List<Map<String,Object>> getAllCodeTypeForBw() {
        return codeTypeMapper.getAllCodeTypeForBw();
    }

    @Override
    public List<Map<String,Object>> getCodeWithNotNullForPsy() {
        return codeTypeMapper.getCodeWithNotNullForPsy();
    }

    @Override
    public List<Map<String,Object>> selectCodeTypeByColumn(String column) {
        return codeTypeMapper.selectCodeTypeByColumn(column);
    }

}
