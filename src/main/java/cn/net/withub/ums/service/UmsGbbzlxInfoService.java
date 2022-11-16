package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsGbbzlxInfo;
import cn.net.withub.ums.entity.UmsGbbzlxInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsGbbzlxInfoService {

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
