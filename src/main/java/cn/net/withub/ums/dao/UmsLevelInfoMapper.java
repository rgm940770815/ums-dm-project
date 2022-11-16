package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsLevelInfo;
import cn.net.withub.ums.entity.UmsLevelInfoCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsLevelInfoMapper {
    int countByExample(UmsLevelInfoCriteria example);

    int deleteByExample(UmsLevelInfoCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(UmsLevelInfo record);

    int insertSelective(UmsLevelInfo record);

    List<UmsLevelInfo> selectByExample(UmsLevelInfoCriteria example);

    UmsLevelInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UmsLevelInfo record, @Param("example") UmsLevelInfoCriteria example);

    int updateByExample(@Param("record") UmsLevelInfo record, @Param("example") UmsLevelInfoCriteria example);

    int updateByPrimaryKeySelective(UmsLevelInfo record);

    int updateByPrimaryKey(UmsLevelInfo record);

    int updateUmsUserInfo(UmsLevelInfo record);
}