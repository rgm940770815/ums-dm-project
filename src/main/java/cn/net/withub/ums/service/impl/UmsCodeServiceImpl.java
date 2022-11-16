/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsCodeMapper;
import cn.net.withub.ums.entity.UmsCode;
import cn.net.withub.ums.entity.UmsCodeCriteria;
import cn.net.withub.ums.service.UmsCodeService;
import cn.net.withub.ums.util.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UmsCodeServiceImpl implements UmsCodeService {

    @Autowired
    private UmsCodeMapper codeMapper;

    /**
     * 通过ID获得编码表，法院进取本地
     *
     * @param typeId
     * @return
     */
    @Override
    public List<UmsCode> selectCodesByType(Integer typeId) {
        UmsCodeCriteria criteria = new UmsCodeCriteria();

        UmsCodeCriteria.Criteria criteria1 = criteria.createCriteria();
        criteria1.andTypeIdEqualTo(typeId);
        criteria.setOrderByClause("sort_no asc");

        return codeMapper.selectByExample(criteria);
    }

    public List<UmsCode> selectCodesByType(Integer typeId, String parentId) {
        UmsCodeCriteria criteria = new UmsCodeCriteria();
        UmsCodeCriteria.Criteria criteria1 = criteria.createCriteria();
        if (StringUtils.isEmpty(parentId)) {
            criteria1.andTypeIdEqualTo(typeId);
        } else {
            if (parentId.contains(",")) {
                String[] parentIdArray = parentId.split(",");
                criteria1.andTypeIdEqualTo(typeId).andParentIdIsNotNull().andParentIdIn(Arrays.asList(parentIdArray));
            } else {
                criteria1.andTypeIdEqualTo(typeId).andParentIdIsNotNull().andParentIdEqualTo(parentId);
            }
        }
        criteria.setOrderByClause("sort_no asc");
        return codeMapper.selectByExample(criteria);
    }


    /**
     * 通过ID获得编码表，法院进取本地
     *
     * @param ids
     * @return
     */
    @Override
    public List<UmsCode> selectCodesByType(List<Integer> ids) {
        UmsCodeCriteria criteria = new UmsCodeCriteria();

        criteria.createCriteria().andTypeIdIn(ids);
        criteria.setOrderByClause("sort_no asc");

        return codeMapper.selectByExample(criteria);
    }

    @Override
    public List<Map<String,Object>> getProvince() {
        return codeMapper.getProvince();
    }

    @Override
    public List<Map<String,Object>> getCity(int provinceID) {
        return codeMapper.getCity(provinceID);
    }

    @Override
    public List<Map<String,Object>> getArea(int cityId) {
        return codeMapper.getArea(cityId);
    }

    @Override
    public List<UmsCode> selectByExample(UmsCodeCriteria criteria) {
        return codeMapper.selectByExample(criteria);
    }

    @Override
    public int insert(UmsCode entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(UmsCode entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(UmsCode entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countAll() {
        UmsCodeCriteria criteria = new UmsCodeCriteria();
        return codeMapper.countByExample(criteria);
    }

    @Override
    public UmsCode selectById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UmsCode> search(UmsCodeCriteria criteria, RowBounds rowBounds) {
        return codeMapper.selectByExample(criteria);
    }

    @Override
    public int count(UmsCodeCriteria criteria) {
        return codeMapper.countByExample(criteria);
    }

    @Override
    public int deleteById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //  <!--上报字段获取对应字段同名的值-->
    public Map<Integer, List<Map<String, String>>> getReport() {
        int i[] = new int[]{5,11,13,15,16,17};
        String s[] = new String[]{"民族(上报)","文化程度(上报)","政治面貌(上报)","行政职务(上报)","法律职务(上报)","职级(上报)"};
        Map<Integer, List<Map<String, String>>> integerListMap = new HashMap<>();
        for (int i1 : i) {
            List<Map<String, String>> report = codeMapper.getReport(i1);
            integerListMap.put(i1, report);
        }
        return integerListMap;
    }
}
