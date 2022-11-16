package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsLog;
import cn.net.withub.ums.entity.UmsLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsLogMapper {
    int countByExample(UmsLogCriteria example);

    int deleteByExample(UmsLogCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(UmsLog record);

    int insertSelective(UmsLog record);

    List<UmsLog> selectByExample(UmsLogCriteria example);

    UmsLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UmsLog record, @Param("example") UmsLogCriteria example);

    int updateByExample(@Param("record") UmsLog record, @Param("example") UmsLogCriteria example);

    int updateByPrimaryKeySelective(UmsLog record);

    int updateByPrimaryKey(UmsLog record);
}