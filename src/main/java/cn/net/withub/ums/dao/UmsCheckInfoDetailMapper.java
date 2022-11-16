package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsCheckInfoDetail;
import cn.net.withub.ums.entity.UmsCheckInfoDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsCheckInfoDetailMapper {
    int countByExample(UmsCheckInfoDetailExample example);

    int deleteByExample(UmsCheckInfoDetailExample example);

    int insert(UmsCheckInfoDetail record);

    int insertSelective(UmsCheckInfoDetail record);

    List<UmsCheckInfoDetail> selectByExample(UmsCheckInfoDetailExample example);

    int updateByExampleSelective(@Param("record") UmsCheckInfoDetail record, @Param("example") UmsCheckInfoDetailExample example);

    int updateByExample(@Param("record") UmsCheckInfoDetail record, @Param("example") UmsCheckInfoDetailExample example);
}