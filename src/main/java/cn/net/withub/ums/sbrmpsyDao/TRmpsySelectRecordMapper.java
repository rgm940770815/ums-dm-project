package cn.net.withub.ums.sbrmpsyDao;

import cn.net.withub.ums.entity.sbrmpsy.TRmpsySelectRecord;
import cn.net.withub.ums.entity.sbrmpsy.TRmpsySelectRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRmpsySelectRecordMapper {
    int countByExample(TRmpsySelectRecordExample example);

    int deleteByExample(TRmpsySelectRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(TRmpsySelectRecord record);

    int insertSelective(TRmpsySelectRecord record);

    List<TRmpsySelectRecord> selectByExample(TRmpsySelectRecordExample example);

    TRmpsySelectRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRmpsySelectRecord record, @Param("example") TRmpsySelectRecordExample example);

    int updateByExample(@Param("record") TRmpsySelectRecord record, @Param("example") TRmpsySelectRecordExample example);

    int updateByPrimaryKeySelective(TRmpsySelectRecord record);

    int updateByPrimaryKey(TRmpsySelectRecord record);
}