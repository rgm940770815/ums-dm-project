/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsExtEnterSrcTypeMapper;
import cn.net.withub.ums.entity.UmsExtEnterSrcType;
import cn.net.withub.ums.entity.UmsExtEnterSrcTypeCriteria;
import cn.net.withub.ums.service.UmsExtEnterSrcTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmsExtEnterSrcTypeServiceImpl implements UmsExtEnterSrcTypeService {

    @Autowired
    private UmsExtEnterSrcTypeMapper enterSrcTypeMapper;

    @Override
    public List<UmsExtEnterSrcType> findAll() {
        UmsExtEnterSrcTypeCriteria criteria = new UmsExtEnterSrcTypeCriteria();
        criteria.createCriteria().getAllCriteria();
        return enterSrcTypeMapper.selectByExample(criteria);
    }

}
