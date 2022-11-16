package cn.net.withub.ums.dao;
import cn.net.withub.ums.entity.UmsCourtVerifyConfig;
import cn.net.withub.ums.entity.UmsCourtVerifyConfigCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UmsCourtVerifyConfigMapper {
    int countByExample(UmsCourtVerifyConfigCriteria example);

    int deleteByExample(UmsCourtVerifyConfigCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsCourtVerifyConfig record);

    int insertSelective(UmsCourtVerifyConfig record);

    List<UmsCourtVerifyConfig> selectByExample(UmsCourtVerifyConfigCriteria example);

    UmsCourtVerifyConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsCourtVerifyConfig record, @Param("example") UmsCourtVerifyConfigCriteria example);

    int updateByExample(@Param("record") UmsCourtVerifyConfig record, @Param("example") UmsCourtVerifyConfigCriteria example);

    int updateByPrimaryKeySelective(UmsCourtVerifyConfig record);

    int updateByPrimaryKey(UmsCourtVerifyConfig record);

    int insertByBatch(List<Map<String, Object>> list);

    int insertAuto(UmsCourtVerifyConfig record);
}