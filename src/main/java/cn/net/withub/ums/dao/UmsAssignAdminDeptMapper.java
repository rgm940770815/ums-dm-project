package cn.net.withub.ums.dao;


import cn.net.withub.ums.entity.UmsAssignAdminDept;
import cn.net.withub.ums.entity.UmsAssignAdminDeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsAssignAdminDeptMapper {
    long countByExample(UmsAssignAdminDeptExample example);

    int deleteByExample(UmsAssignAdminDeptExample example);

    int deleteByPrimaryKey(String id);

    int insert(UmsAssignAdminDept record);

    int insertSelective(UmsAssignAdminDept record);

    List<UmsAssignAdminDept> selectByExample(UmsAssignAdminDeptExample example);

    UmsAssignAdminDept selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UmsAssignAdminDept record, @Param("example") UmsAssignAdminDeptExample example);

    int updateByExample(@Param("record") UmsAssignAdminDept record, @Param("example") UmsAssignAdminDeptExample example);

    int updateByPrimaryKeySelective(UmsAssignAdminDept record);

    int updateByPrimaryKey(UmsAssignAdminDept record);
}