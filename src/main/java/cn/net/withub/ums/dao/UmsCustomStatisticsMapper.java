package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsCustomStatistics;
import cn.net.withub.ums.entity.UmsCustomStatisticsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UmsCustomStatisticsMapper {
    int countByExample(UmsCustomStatisticsCriteria example);

    int deleteByExample(UmsCustomStatisticsCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsCustomStatistics record);

    int insertSelective(UmsCustomStatistics record);

    List<UmsCustomStatistics> selectByExampleWithBLOBsWithRowbounds(UmsCustomStatisticsCriteria example, RowBounds rowBounds);

    List<UmsCustomStatistics> selectByExampleWithBLOBs(UmsCustomStatisticsCriteria example);

    List<UmsCustomStatistics> selectByExampleWithRowbounds(UmsCustomStatisticsCriteria example, RowBounds rowBounds);

    List<UmsCustomStatistics> selectByExample(UmsCustomStatisticsCriteria example);

    UmsCustomStatistics selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsCustomStatistics record, @Param("example") UmsCustomStatisticsCriteria example);

    int updateByExampleWithBLOBs(@Param("record") UmsCustomStatistics record, @Param("example") UmsCustomStatisticsCriteria example);

    int updateByExample(@Param("record") UmsCustomStatistics record, @Param("example") UmsCustomStatisticsCriteria example);

    int updateByPrimaryKeySelective(UmsCustomStatistics record);

    int updateByPrimaryKeyWithBLOBs(UmsCustomStatistics record);

    int updateByPrimaryKey(UmsCustomStatistics record);
}