package cn.net.withub.ums.dao;

import java.util.List;

import cn.net.withub.ums.entity.UmsCheckInfoSql;
import cn.net.withub.ums.entity.UmsCheckInfoSqlExample;
import org.apache.ibatis.annotations.Param;

public interface UmsCheckInfoSqlMapper {
    int countByExample(UmsCheckInfoSqlExample example);

    int deleteByExample(UmsCheckInfoSqlExample example);

    int deleteByPrimaryKey(String id);

    int insert(UmsCheckInfoSql record);

    int insertSelective(UmsCheckInfoSql record);

    List<UmsCheckInfoSql> selectByExample(UmsCheckInfoSqlExample example);

    UmsCheckInfoSql selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UmsCheckInfoSql record, @Param("example") UmsCheckInfoSqlExample example);

    int updateByExample(@Param("record") UmsCheckInfoSql record, @Param("example") UmsCheckInfoSqlExample example);

    int updateByPrimaryKeySelective(UmsCheckInfoSql record);

    int updateByPrimaryKey(UmsCheckInfoSql record);
}