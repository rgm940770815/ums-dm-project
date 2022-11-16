/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsLogViewMapper;
import cn.net.withub.ums.entity.UmsLogView;
import cn.net.withub.ums.entity.UmsLogViewCriteria;
import cn.net.withub.ums.service.UmsLogViewService;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmsLogViewServiceImpl implements UmsLogViewService {

    @Autowired
    private UmsLogViewMapper logViewMapper;

    @Override
    public int insert(UmsLogView entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(UmsLogView entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(UmsLogView entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UmsLogView selectById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UmsLogView> search(UmsLogViewCriteria criteria, RowBounds rowBounds) {
        return logViewMapper.selectByExample(criteria, rowBounds);
    }

    @Override
    public int count(UmsLogViewCriteria criteria) {
        return logViewMapper.countByExample(criteria);
    }

}
