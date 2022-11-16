/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsDepartment;
import cn.net.withub.ums.entity.UmsDepartmentCriteria;
import cn.net.withub.ums.entity.UmsDepartmentKey;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * @author Diluka
 */
public interface UmsDepartmentService extends BaseService<UmsDepartment, UmsDepartmentCriteria> {

    /**
     * 通过法院编号查询该法院部门列表
     *
     * @param courtNo
     * @return
     */
    List<UmsDepartment> selectByCourtNo(Integer courtNo);

    /**
     * 通过法院代码查询该法院部门列表
     *
     * @param fydm
     * @return
     */
    public List<UmsDepartment> selectByCourtNo(String fydm);

    /**
     * 通过法院编号和部门级别查询该法院部门列表
     *
     * @param courtNo
     * @param level
     * @return
     */
    List<UmsDepartment> selectByCourtNoAndDeptLevel(Integer courtNo, Integer level);

    /**
     * 查询子部门
     *
     * @param parent
     * @return
     */
    List<UmsDepartment> listChildrenByDept(UmsDepartment parent);

    /**
     * 查询子部门
     *
     * @param key
     * @return
     */
    List<UmsDepartment> listChildrenByKey(UmsDepartmentKey key);

    /**
     * 查询所有层级子部门
     *
     * @param key
     * @return
     */
    List<UmsDepartment> listAllChildrenByKey(UmsDepartmentKey key);

    /**
     * 是否为叶节点（无子部门）
     *
     * @param key
     * @return
     */
    Boolean isLeaf(UmsDepartmentKey key);

    /**
     * 是否为叶节点（无子部门）
     *
     * @param parent
     * @return
     */
    Boolean isLeaf(UmsDepartment parent);

    /**
     * 根据部门和法院编号查询具体部门信息
     *
     * @param courtNo
     * @param deptNo
     * @return
     */
    UmsDepartment selectByCourtNoAndDeptNo(Integer courtNo, Integer deptNo);

    /**
     * 更新部门信息
     *
     * @param department
     * @return
     */
    int updateByPrimaryKeySelective(UmsDepartment department);

    /**
     * 获取同级中  deptNo最大值
     *
     * @param
     * @return
     */
    Integer selectMaxDeptNo(Map map);

    /**
     * 获取同级中  sortNo最大值
     *
     * @param map
     * @return
     */
    Integer selectMaxSortNo(Map map);


    List<UmsDepartment> selectByExample(UmsDepartmentCriteria criteria);

    List<UmsDepartment> selectByExample(UmsDepartmentCriteria criteria,RowBounds rowBounds);

    List<UmsDepartment> selectByExampleWithNoAspect(UmsDepartmentCriteria criteria);

    int countListChildren(UmsDepartmentKey key);

    List<Integer> getLevelDeptNo(Map map);

    UmsDepartment selectByPrimaryKey(UmsDepartmentKey key);

    int countByExample(UmsDepartmentCriteria criteria);

    List<UmsDepartment> selectByExampleNoAspect(UmsDepartmentCriteria criteria);

    int updateDeptSort(Map map);

    Integer countByCourtNo(Integer courtNo);
}
