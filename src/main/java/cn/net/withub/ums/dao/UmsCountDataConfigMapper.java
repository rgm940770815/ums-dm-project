package cn.net.withub.ums.dao;


import cn.net.withub.ums.entity.UmsCountDataConfig;
import cn.net.withub.ums.entity.UmsCountDataConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsCountDataConfigMapper {
    long countByExample(UmsCountDataConfigExample example);

    int deleteByExample(UmsCountDataConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsCountDataConfig record);

    int insertSelective(UmsCountDataConfig record);

    List<UmsCountDataConfig> selectByExample(UmsCountDataConfigExample example);

    UmsCountDataConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsCountDataConfig record, @Param("example") UmsCountDataConfigExample example);

    int updateByExample(@Param("record") UmsCountDataConfig record, @Param("example") UmsCountDataConfigExample example);

    int updateByPrimaryKeySelective(UmsCountDataConfig record);

    int updateByPrimaryKey(UmsCountDataConfig record);
}