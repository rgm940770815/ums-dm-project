/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsCourtFullMapper;
import cn.net.withub.ums.entity.UmsCode;
import cn.net.withub.ums.entity.UmsCourtFull;
import cn.net.withub.ums.entity.UmsCourtFullCriteria;
import cn.net.withub.ums.service.UmsCourtFullService;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UmsCourtFullServiceImpl implements UmsCourtFullService {
    @Autowired
    private SqlSession sqlSession;



    @Autowired
    private UmsCourtFullMapper courtFullMapper;

    @Override
    public int insert(UmsCourtFull entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(UmsCourtFull entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(UmsCourtFull entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UmsCourtFull selectById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UmsCourtFull> listAllCourts() {
        UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
        criteria.createCriteria().andCourtStdNoLessThan(7000);

        criteria.setOrderByClause("sort_no");

        return courtFullMapper.selectByExample(criteria);
    }

    @Override
    public List<UmsCourtFull> listArea() {
        UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
        criteria.createCriteria().andCourtStdNoGreaterThanOrEqualTo(7000);
        criteria.setOrderByClause("sort_no");
        return courtFullMapper.selectByExample(criteria);
    }

    @Override
    public List<UmsCourtFull> listByGradation(Integer gradation) {
        UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
        criteria.createCriteria().andCourtGradationEqualTo(gradation);

        criteria.setOrderByClause("sort_no");

        return courtFullMapper.selectByExample(criteria);
    }

    @Override
    public List<UmsCourtFull> listByArea(Integer areaNo) {
        UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
        criteria.createCriteria().andCourtAreaNoEqualTo(areaNo);

        criteria.setOrderByClause("sort_no");

        return courtFullMapper.selectByExample(criteria);
    }

    @Override
    public List<UmsCourtFull> listSubByCourt(UmsCourtFull parent) {
        if (parent == null) {
            return null;
        }
        UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
        criteria.createCriteria()
                .andCourtGradationEqualTo(parent.getCourtGradation() + 1) //下一级别
                .andCourtAreaNoEqualTo(parent.getCourtAreaNo()); //同区域

        criteria.setOrderByClause("sort_no");

        return courtFullMapper.selectByExample(criteria);
    }

    @Override
    public List<UmsCourtFull> listChildrenByCourtStdNo(Integer courtStdNo) {
        UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
        criteria.createCriteria().andCourtStdNoEqualTo(courtStdNo);

        List<UmsCourtFull> list = courtFullMapper.selectByExample(criteria);
        UmsCourtFull ucf = list.size() > 0 ? list.get(0) : null;

        if (ucf == null) {
            return null;
        } else if (ucf.getCourtGradation() == 0) { //高院，顶级
            return listByGradation(1);
        }

        return listSubByCourt(ucf);
    }

    /**
     * 查询某法院本身
     *
     * @param courtStdNo
     * @return
     */
    @Override
    public UmsCourtFull getCourtByCourtStdNo(Integer courtStdNo) {
        UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
        criteria.createCriteria().andCourtStdNoEqualTo(courtStdNo);

        List<UmsCourtFull> list = courtFullMapper.selectByExample(criteria);
        UmsCourtFull ucf = list.size() > 0 ? list.get(0) : null;

        return ucf;
    }

    @Override
    public List<UmsCourtFull> listChildrenByCourtNo(Integer courtNo) {
        UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
        criteria.createCriteria().andCourtNoEqualTo(courtNo);

        List<UmsCourtFull> list = courtFullMapper.selectByExample(criteria);
        UmsCourtFull ucf = list.size() > 0 ? list.get(0) : null;

        if (ucf == null) {
            return null;
        } else if (ucf.getCourtGradation() == 0) { //高院，顶级
            return listByGradation(1);
        }

        return listSubByCourt(ucf);
    }

    @Override
    public List<UmsCourtFull> selectByList(List courtList) {
        return courtFullMapper.selectByList(courtList);
    }

    @Override
    public List<UmsCourtFull> selectByListAll() {
        return courtFullMapper.selectAllCourt();
    }

    @Override
    public UmsCourtFull getCourtByCourtCode(String courtCode) {
        UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
        criteria.createCriteria().andCourtCodeEqualTo(courtCode);

        List<UmsCourtFull> list = courtFullMapper.selectByExample(criteria);
        UmsCourtFull ucf = list.size() > 0 ? list.get(0) : null;

        return ucf;
    }

    @Override
    public  List<UmsCourtFull> getCourtByOrganization(List<String> courtList, List<Integer> codeList) {
        return courtFullMapper.getCourtByOrganization(courtList,codeList);
    }

    @Override
    public List<UmsCourtFull> selectByExample(UmsCourtFullCriteria criteria) {
        return courtFullMapper.selectByExample(criteria);
    }

    @Override
    public int countByExample(UmsCourtFullCriteria criteria) {
        return courtFullMapper.countByExample(criteria);
    }

    @Override
    public List<UmsCourtFull> selectCourtForNOLeaderDept(Map<String, Object> map) {
        return courtFullMapper.selectCourtForNOLeaderDept(map);
    }

    @Override
    public int countCourtForNOLeaderDept(Map<String, Object> map) {
        return courtFullMapper.countCourtForNOLeaderDept(map);
    }

    @Override
    public int updateByPrimaryKeySelective(UmsCourtFull record) {
        return courtFullMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<UmsCourtFull> search(UmsCourtFullCriteria criteria, RowBounds rowBounds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count(UmsCourtFullCriteria criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UmsCourtFull> listAll() {
        UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
        criteria.createCriteria().getAllCriteria();
        return courtFullMapper.selectByExample(criteria);
    }


    /**
     * 根据用户的权限获取可访问的法院
     *
     * @param userID
     * @return
     */
    @Override
    public List<Map<String,Object>> listByAuthUserID(String userID) {


        String sqlStr =
                "select b.court_no rcourt_no,b.court_std_no rcourt_std_no,c.* from ums_user_role a,ums_role b left join ums_court_full c on b.court_no = c.court_no\n" +
                "where a.role_id = b.id  \n" +
                "and a.user_id = '"+userID+"'";

        List<Map<String,Object>> list = null;
        try {
            list = sqlSession.selectList("cn.net.withub.ums.dao.UmsCourtFullMapper.countByAuthForMap",userID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<UmsCode> getUmsCodeFy() {
        List<UmsCode> umsCodes = new ArrayList<>();
        selectByListAll().forEach(umsCourtFull -> {
            UmsCode umsCode = new UmsCode();
            umsCode.setId(umsCourtFull.getCourtCode());
            umsCode.setCodeName(umsCourtFull.getCourtStdName());
            umsCode.setParentId(umsCourtFull.getParentId());
            umsCode.setCourtCode("M00");
            umsCode.setSortNo(umsCourtFull.getSortNo());
            umsCodes.add(umsCode);
        });
        return umsCodes;
    }

    public List<UmsCode> getUmsCodeFy_2(List<Integer> court_no_list) {
        List<UmsCode> umsCodes = new ArrayList<>();
        selectByListAll().forEach(umsCourtFull -> {
            Integer court_no = umsCourtFull.getCourtNo();
            if (null != court_no_list && court_no_list.contains(court_no)) {
                UmsCode umsCode = new UmsCode();
                umsCode.setId(umsCourtFull.getCourtCode());
                umsCode.setCodeName(umsCourtFull.getCourtStdName());
                umsCode.setParentId(umsCourtFull.getParentId());
                umsCode.setCourtCode("M00");
                umsCode.setSortNo(umsCourtFull.getSortNo());
                umsCodes.add(umsCode);
            }
        });
        return umsCodes;
    }
}
