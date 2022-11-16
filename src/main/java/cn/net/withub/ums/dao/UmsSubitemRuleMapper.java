package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsSubitemRule;
import cn.net.withub.ums.entity.UmsSubitemRuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsSubitemRuleMapper {
    long countByExample(UmsSubitemRuleExample example);

    int deleteByExample(UmsSubitemRuleExample example);

    int deleteByPrimaryKey(String tableKey);

    int insert(UmsSubitemRule record);

    int insertSelective(UmsSubitemRule record);

    List<UmsSubitemRule> selectByExample(UmsSubitemRuleExample example);

    UmsSubitemRule selectByPrimaryKey(String tableKey);

    int updateByExampleSelective(@Param("record") UmsSubitemRule record, @Param("example") UmsSubitemRuleExample example);

    int updateByExample(@Param("record") UmsSubitemRule record, @Param("example") UmsSubitemRuleExample example);

    int updateByPrimaryKeySelective(UmsSubitemRule record);

    int updateByPrimaryKey(UmsSubitemRule record);
}