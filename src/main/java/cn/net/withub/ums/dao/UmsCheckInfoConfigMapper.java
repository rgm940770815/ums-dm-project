package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsCheckInfoConfig;
import cn.net.withub.ums.entity.UmsCheckInfoConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsCheckInfoConfigMapper {
    int countByExample(UmsCheckInfoConfigExample example);

    int deleteByExample(UmsCheckInfoConfigExample example);

    int deleteByPrimaryKey(String id);

    int insert(UmsCheckInfoConfig record);

    int insertSelective(UmsCheckInfoConfig record);

    List<UmsCheckInfoConfig> selectByExample(UmsCheckInfoConfigExample example);

    UmsCheckInfoConfig selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UmsCheckInfoConfig record, @Param("example") UmsCheckInfoConfigExample example);

    int updateByExample(@Param("record") UmsCheckInfoConfig record, @Param("example") UmsCheckInfoConfigExample example);

    int updateByPrimaryKeySelective(UmsCheckInfoConfig record);

    int updateByPrimaryKey(UmsCheckInfoConfig record);
}