package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsFieldCode;
import cn.net.withub.ums.entity.UmsFieldCodeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsFieldCodeMapper {
    int countByExample(UmsFieldCodeExample example);

    int deleteByExample(UmsFieldCodeExample example);

    int insert(UmsFieldCode record);

    int insertSelective(UmsFieldCode record);

    List<UmsFieldCode> selectByExample(UmsFieldCodeExample example);

    int updateByExampleSelective(@Param("record") UmsFieldCode record, @Param("example") UmsFieldCodeExample example);

    int updateByExample(@Param("record") UmsFieldCode record, @Param("example") UmsFieldCodeExample example);
}