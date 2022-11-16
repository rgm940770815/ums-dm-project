package cn.net.withub.ums.sbrmpsyDao;

import cn.net.withub.ums.entity.sbrmpsy.TRmpsyStaggerRecord;
import cn.net.withub.ums.entity.sbrmpsy.TRmpsyStaggerRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRmpsyStaggerRecordMapper {
    int countByExample(TRmpsyStaggerRecordExample example);

    int deleteByExample(TRmpsyStaggerRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(TRmpsyStaggerRecord record);

    int insertSelective(TRmpsyStaggerRecord record);

    List<TRmpsyStaggerRecord> selectByExample(TRmpsyStaggerRecordExample example);

    TRmpsyStaggerRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRmpsyStaggerRecord record, @Param("example") TRmpsyStaggerRecordExample example);

    int updateByExample(@Param("record") TRmpsyStaggerRecord record, @Param("example") TRmpsyStaggerRecordExample example);

    int updateByPrimaryKeySelective(TRmpsyStaggerRecord record);

    int updateByPrimaryKey(TRmpsyStaggerRecord record);
}