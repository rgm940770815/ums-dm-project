package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsRmpsyJbxx;
import cn.net.withub.ums.entity.UmsRmpsyJbxxExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UmsRmpsyJbxxMapper {
    int countByExample(UmsRmpsyJbxxExample example);

    int deleteByExample(UmsRmpsyJbxxExample example);

    int deleteByPrimaryKey(String id);

    int insert(UmsRmpsyJbxx record);

    int insertSelective(UmsRmpsyJbxx record);

    List<UmsRmpsyJbxx> selectByExample(UmsRmpsyJbxxExample example);

    List<Map> selectByExampleForMap(UmsRmpsyJbxxExample example);

    UmsRmpsyJbxx selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UmsRmpsyJbxx record, @Param("example") UmsRmpsyJbxxExample example);

    int updateByExample(@Param("record") UmsRmpsyJbxx record, @Param("example") UmsRmpsyJbxxExample example);

    int updateByPrimaryKeySelective(UmsRmpsyJbxx record);

    int updateByPrimaryKey(UmsRmpsyJbxx record);
}