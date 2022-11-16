package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.RmpsySbdzb;
import cn.net.withub.ums.entity.RmpsySbdzbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RmpsySbdzbMapper {
    int countByExample(RmpsySbdzbExample example);

    int deleteByExample(RmpsySbdzbExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RmpsySbdzb record);

    int insertSelective(RmpsySbdzb record);

    List<RmpsySbdzb> selectByExample(RmpsySbdzbExample example);

    RmpsySbdzb selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RmpsySbdzb record, @Param("example") RmpsySbdzbExample example);

    int updateByExample(@Param("record") RmpsySbdzb record, @Param("example") RmpsySbdzbExample example);

    int updateByPrimaryKeySelective(RmpsySbdzb record);

    int updateByPrimaryKey(RmpsySbdzb record);
}