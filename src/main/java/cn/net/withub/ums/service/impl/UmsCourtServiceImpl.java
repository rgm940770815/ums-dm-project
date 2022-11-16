/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.extend.UmsCourtExtendMapper;
import cn.net.withub.ums.entity.UmsCourt;
import cn.net.withub.ums.entity.UmsCourtCriteria;
import cn.net.withub.ums.service.UmsCourtService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsCourtServiceImpl implements UmsCourtService {

    @Autowired
    private UmsCourtExtendMapper courtMapper;

    @Override
    public int insert(UmsCourt entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(UmsCourt entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(UmsCourt entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UmsCourt> listAll() {
        UmsCourtCriteria criteria = new UmsCourtCriteria();
        criteria.createCriteria().getAllCriteria();
        criteria.setOrderByClause("court_std_no");
        return courtMapper.selectByExample(criteria);
    }

    @Override
    public UmsCourt selectById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer courtNo2CourtStdNo(Integer courtNo) {
        return courtMapper.courtNo2CourtStdNo(courtNo);
    }

    @Override
    public List<UmsCourt> search(UmsCourtCriteria criteria, RowBounds rowBounds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count(UmsCourtCriteria criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String courtNo2CourtCode(Integer courtNo) {
        return courtMapper.courtNo2CourtCode(courtNo);
    }

    @Override
    public String courtTexForCourtStdNo(Integer courtStdNo) {
        return courtMapper.courtTexForCourtStdNo(courtStdNo);
    }

    @Override
    public Integer courtNoForCourtStdNo(Integer courtStdNo) {
        return courtMapper.courtNoForCourtStdNo(courtStdNo);
    }

    @Override
    public String courtTexForCourtCode(String courtCode) {
        return courtMapper.courtTexForCourtCode(courtCode);
    }

}
