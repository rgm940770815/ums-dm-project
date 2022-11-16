/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsExternalLabourContractMapper;
import cn.net.withub.ums.entity.UmsExternalLabourContract;
import cn.net.withub.ums.entity.UmsExternalLabourContractCriteria;
import cn.net.withub.ums.service.UmsExternalLabourContractService;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmsExternalLabourContractServiceImpl implements UmsExternalLabourContractService {

    @Autowired
    private UmsExternalLabourContractMapper externalLabourContractMapper;

    @Override
    public int insert(UmsExternalLabourContract entity) {
        return externalLabourContractMapper.insert(entity);
    }

    @Override
    public int delete(UmsExternalLabourContract entity) {
        return externalLabourContractMapper.deleteByPrimaryKey(entity.getUserId());
    }

    @Override
    public int deleteById(Object id) {
        return externalLabourContractMapper.deleteByPrimaryKey(id.toString());
    }

    @Override
    public int update(UmsExternalLabourContract entity) {
        return externalLabourContractMapper.updateByPrimaryKey(entity);
    }

    @Override
    public UmsExternalLabourContract selectById(Object id) {
        return externalLabourContractMapper.selectByPrimaryKey(id.toString());
    }

    @Override
    public int countAll() {
        UmsExternalLabourContractCriteria criteria = new UmsExternalLabourContractCriteria();
        criteria.createCriteria().getAllCriteria();

        return externalLabourContractMapper.countByExample(criteria);
    }

    @Override
    public List<UmsExternalLabourContract> search(UmsExternalLabourContractCriteria criteria, RowBounds rowBounds) {
        return externalLabourContractMapper.selectByExample(criteria, rowBounds);
    }

    @Override
    public int count(UmsExternalLabourContractCriteria criteria) {
        return externalLabourContractMapper.countByExample(criteria);
    }
}
