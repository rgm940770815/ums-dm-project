package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsVerifyConfig;
import cn.net.withub.ums.entity.UmsVerifyConfigCriteria;
import cn.net.withub.ums.entity.UmsVerifyConfigKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UmsVerifyConfigMapper {
    int countByExample(UmsVerifyConfigCriteria example);

    int deleteByExample(UmsVerifyConfigCriteria example);

    int deleteByPrimaryKey(UmsVerifyConfigKey key);

    int insert(UmsVerifyConfig record);

    int insertSelective(UmsVerifyConfig record);

    List<UmsVerifyConfig> selectByExample(UmsVerifyConfigCriteria example);

    UmsVerifyConfig selectByPrimaryKey(UmsVerifyConfigKey key);

    int updateByExampleSelective(@Param("record") UmsVerifyConfig record, @Param("example") UmsVerifyConfigCriteria example);

    int updateByExample(@Param("record") UmsVerifyConfig record, @Param("example") UmsVerifyConfigCriteria example);

    int updateByPrimaryKeySelective(UmsVerifyConfig record);

    int updateByPrimaryKey(UmsVerifyConfig record);

    int insertByBatch(List<Map<String, Object>> list);
}