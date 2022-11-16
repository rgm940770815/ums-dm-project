package cn.net.withub.ums.sbrmpsyDao;

import cn.net.withub.ums.entity.sbrmpsy.TRmpsyMsgAndReply;
import cn.net.withub.ums.entity.sbrmpsy.TRmpsyMsgAndReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRmpsyMsgAndReplyMapper {
    int countByExample(TRmpsyMsgAndReplyExample example);

    int deleteByExample(TRmpsyMsgAndReplyExample example);

    int deleteByPrimaryKey(String id);

    int insert(TRmpsyMsgAndReply record);

    int insertSelective(TRmpsyMsgAndReply record);

    List<TRmpsyMsgAndReply> selectByExample(TRmpsyMsgAndReplyExample example);

    TRmpsyMsgAndReply selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRmpsyMsgAndReply record, @Param("example") TRmpsyMsgAndReplyExample example);

    int updateByExample(@Param("record") TRmpsyMsgAndReply record, @Param("example") TRmpsyMsgAndReplyExample example);

    int updateByPrimaryKeySelective(TRmpsyMsgAndReply record);

    int updateByPrimaryKey(TRmpsyMsgAndReply record);
}