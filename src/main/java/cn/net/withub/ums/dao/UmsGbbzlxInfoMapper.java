package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsGbbzlxInfo;
import cn.net.withub.ums.entity.UmsGbbzlxInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsGbbzlxInfoMapper {
    int countByExample(UmsGbbzlxInfoExample example);

    int deleteByExample(UmsGbbzlxInfoExample example);

    int deleteByPrimaryKey(String changeuuid);

    int insert(UmsGbbzlxInfo record);

    int insertSelective(UmsGbbzlxInfo record);

    List<UmsGbbzlxInfo> selectByExample(UmsGbbzlxInfoExample example);

    UmsGbbzlxInfo selectByPrimaryKey(String changeuuid);

    int updateByExampleSelective(@Param("record") UmsGbbzlxInfo record, @Param("example") UmsGbbzlxInfoExample example);

    int updateByExample(@Param("record") UmsGbbzlxInfo record, @Param("example") UmsGbbzlxInfoExample example);

    int updateByPrimaryKeySelective(UmsGbbzlxInfo record);

    int updateByPrimaryKey(UmsGbbzlxInfo record);
}