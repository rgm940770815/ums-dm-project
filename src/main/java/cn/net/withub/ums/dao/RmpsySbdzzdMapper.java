package cn.net.withub.ums.dao;

import java.util.List;

import cn.net.withub.ums.entity.RmpsySbdzzd;
import cn.net.withub.ums.entity.RmpsySbdzzdExample;
import org.apache.ibatis.annotations.Param;

public interface RmpsySbdzzdMapper {
    int countByExample(RmpsySbdzzdExample example);

    int deleteByExample(RmpsySbdzzdExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RmpsySbdzzd record);

    int insertSelective(RmpsySbdzzd record);

    List<RmpsySbdzzd> selectByExample(RmpsySbdzzdExample example);

    RmpsySbdzzd selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RmpsySbdzzd record, @Param("example") RmpsySbdzzdExample example);

    int updateByExample(@Param("record") RmpsySbdzzd record, @Param("example") RmpsySbdzzdExample example);

    int updateByPrimaryKeySelective(RmpsySbdzzd record);

    int updateByPrimaryKey(RmpsySbdzzd record);
}