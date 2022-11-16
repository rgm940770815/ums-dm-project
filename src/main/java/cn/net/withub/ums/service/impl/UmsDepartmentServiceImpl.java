/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsDepartmentMapper;
import cn.net.withub.ums.entity.UmsDepartment;
import cn.net.withub.ums.entity.UmsDepartmentCriteria;
import cn.net.withub.ums.entity.UmsDepartmentKey;
import cn.net.withub.ums.service.UmsDepartmentService;
import cn.net.withub.ums.util.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UmsDepartmentServiceImpl implements UmsDepartmentService {

    @Autowired
    private UmsDepartmentMapper departmentMapper;

    @Override
    public List<UmsDepartment> selectByCourtNo(Integer courtNo) {
        UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
        criteria.createCriteria().andCourtNoEqualTo(courtNo).andIsValidEqualTo(1);

        return departmentMapper.selectByExample(criteria);
    }

    @Override
    public List<UmsDepartment> selectByCourtNo(String fydm) {
        UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();

        if(StringUtils.isNotEmpty(fydm)){
            criteria.createCriteria().andCourtCodeEqualTo(fydm).andIsValidEqualTo(1);
            criteria.setOrderByClause("sort_no");
        }

        return departmentMapper.selectByExample(criteria);
    }

    @Override
    public int insert(UmsDepartment entity) {
        return departmentMapper.insert(entity);
    }

    @Override
    public int delete(UmsDepartment entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(UmsDepartment entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UmsDepartment selectById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UmsDepartment> listChildrenByDept(UmsDepartment parent) {
        if (parent == null) {
            return null;
        }
        UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
        String partten = "";
        switch (parent.getLevel()) {
            case 1:
                partten = parent.getLevelCode().substring(0, 4) + "________";
                break;
            case 2:
                partten = parent.getLevelCode().substring(0, 8) + "____";
                break;
            case 3:
                break;
        }
        criteria.createCriteria()
                .andCourtNoEqualTo(parent.getCourtNo()) //法院编号
                .andLevelCodeLike(partten) //级别编码
                .andLevelCodeNotEqualTo(parent.getLevelCode()) //非自己
                .andLevelEqualTo(parent.getLevel() + 1) //下级列表
                .andIsValidEqualTo(1); //状态启用

        criteria.setOrderByClause("sort_no");

        return departmentMapper.selectByExample(criteria);
    }

    @Override
    public List<UmsDepartment> listChildrenByKey(UmsDepartmentKey key) {
        UmsDepartment dp = departmentMapper.selectByPrimaryKey(key);
        return listChildrenByDept(dp);
    }

    @Override
    public List<UmsDepartment> listAllChildrenByKey(UmsDepartmentKey key) {
        UmsDepartment dp = departmentMapper.selectByPrimaryKey(key);
        List<UmsDepartment> umsDepartments = listChildrenByDept(dp);
        if (dp.getLevel() == 1) {
            List<UmsDepartment> level3child = new ArrayList<>();
            for (UmsDepartment umsDepartment : umsDepartments) {
                level3child.addAll(listChildrenByDept(umsDepartment));
            }
            umsDepartments.addAll(level3child);
            return umsDepartments.stream().sorted(Comparator.comparingInt(UmsDepartment::getSortNo)).collect(Collectors.toList());
        }
        return umsDepartments;
    }

    @Override
    public Boolean isLeaf(UmsDepartmentKey key) {
        UmsDepartment dp = departmentMapper.selectByPrimaryKey(key);
        return isLeaf(dp);
    }

    @Override
    public Boolean isLeaf(UmsDepartment parent) {
        if (parent == null) {
            return true;
        }
        UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
        String partten = "";
        switch (parent.getLevel()) {
            case 1:
                partten = parent.getLevelCode().substring(0, 4) + "________";
                break;
            case 2:
                partten = parent.getLevelCode().substring(0, 8) + "____";
                break;
            case 3:
                break;
        }
        criteria.createCriteria()
                .andCourtNoEqualTo(parent.getCourtNo()) //法院编号
                .andLevelCodeLike(partten) //级别编码
                .andLevelCodeNotEqualTo(parent.getLevelCode()); //非自己

        return departmentMapper.countByExample(criteria) == 0;
    }

    @Override
    public UmsDepartment selectByCourtNoAndDeptNo(Integer courtNo, Integer deptNo) {
        UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
        criteria.createCriteria().andCourtNoEqualTo(courtNo).andDeptNoEqualTo(deptNo);
        return departmentMapper.selectByExample(criteria).get(0);
    }

    @Override
    public int updateByPrimaryKeySelective(UmsDepartment department) {
        return departmentMapper.updateByPrimaryKeySelective(department);
    }

    @Override
    public Integer selectMaxDeptNo(Map map) {
        return departmentMapper.selectMaxDeptNo(map);
    }

    @Override
    public Integer selectMaxSortNo(Map map) {
        return departmentMapper.selectMaxSortNo(map);
    }

    @Override
    public List<UmsDepartment> selectByExample(UmsDepartmentCriteria criteria) {
        return departmentMapper.selectByExample(criteria);
    }

    @Override
    public List<UmsDepartment> selectByExample(UmsDepartmentCriteria criteria, RowBounds rowBounds) {
        return departmentMapper.selectByExample(criteria, rowBounds);
    }

    @Override
    public List<UmsDepartment> selectByExampleWithNoAspect(UmsDepartmentCriteria criteria) {
        return departmentMapper.selectByExample(criteria);
    }

    @Override
    public UmsDepartment selectByPrimaryKey(UmsDepartmentKey key) {
        return  departmentMapper.selectByPrimaryKey(key);

    }

    @Override
    public int countByExample(UmsDepartmentCriteria criteria) {
        return  departmentMapper.countByExample(criteria);
    }

    @Override
    public List<UmsDepartment> selectByExampleNoAspect(UmsDepartmentCriteria criteria) {
        return departmentMapper.selectByExample(criteria);
    }


    @Override
    public int countListChildren(UmsDepartmentKey key) {
        int i = 0;
        //如果没有部门编码 出该法院第一级部门列表
        try {
            if (key.getDeptNo() == null) {

                UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
                criteria.createCriteria()
                        .andCourtNoEqualTo(key.getCourtNo()) //法院编号
                        .andLevelEqualTo(1) //部门级别
                        .andIsValidEqualTo(1);

                i = departmentMapper.countByExample(criteria);

            } else {

                UmsDepartment parent = departmentMapper.selectByPrimaryKey(key);

                if (parent == null) {
                    return 0;
                }

                UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
                String partten = "";

                switch (parent.getLevel()) {
                    case 1:
                        partten = parent.getLevelCode().substring(0, 4) + "________";
                        break;
                    case 2:
                        partten = parent.getLevelCode().substring(0, 8) + "____";
                        break;
                    case 3:
                        break;
                }

                criteria.createCriteria()
                        .andCourtNoEqualTo(parent.getCourtNo()) //法院编号
                        .andLevelCodeLike(partten) //级别编码
                        .andLevelCodeNotEqualTo(parent.getLevelCode())//非自己
                        .andLevelEqualTo(parent.getLevel() + 1) //本级列表
                        .andIsValidEqualTo(1); //状态启用


                i = departmentMapper.countByExample(criteria);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<Integer> getLevelDeptNo(Map map) {

        return departmentMapper.getLevelDeptNo(map);
    }

    @Override
    public List<UmsDepartment> selectByCourtNoAndDeptLevel(Integer courtNo, Integer level) {
        UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
        criteria.createCriteria()
                .andCourtNoEqualTo(courtNo) //法院编号
                .andLevelEqualTo(level) //部门级别
                .andIsValidEqualTo(1); //状态启用

        criteria.setOrderByClause("sort_no");

        return departmentMapper.selectByExample(criteria);
    }

    @Override
    public List<UmsDepartment> search(UmsDepartmentCriteria criteria, RowBounds rowBounds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count(UmsDepartmentCriteria criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateDeptSort(Map map) {
        return departmentMapper.updateDeptSort(map);
    }

    @Override
    public Integer countByCourtNo(Integer courtNo) {
        return departmentMapper.countByCourtNo(courtNo);
    }
}
