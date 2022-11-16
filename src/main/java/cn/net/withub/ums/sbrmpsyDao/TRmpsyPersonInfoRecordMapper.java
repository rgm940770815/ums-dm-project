package cn.net.withub.ums.sbrmpsyDao;

import cn.net.withub.ums.entity.sbrmpsy.TRmpsyPersonInfoRecord;
import cn.net.withub.ums.entity.sbrmpsy.TRmpsyPersonInfoRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRmpsyPersonInfoRecordMapper {
    int countByExample(TRmpsyPersonInfoRecordExample example);

    int deleteByExample(TRmpsyPersonInfoRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(TRmpsyPersonInfoRecord record);

    int insertSelective(TRmpsyPersonInfoRecord record);

    List<TRmpsyPersonInfoRecord> selectByExample(TRmpsyPersonInfoRecordExample example);

    TRmpsyPersonInfoRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRmpsyPersonInfoRecord record, @Param("example") TRmpsyPersonInfoRecordExample example);

    int updateByExample(@Param("record") TRmpsyPersonInfoRecord record, @Param("example") TRmpsyPersonInfoRecordExample example);

    int updateByPrimaryKeySelective(TRmpsyPersonInfoRecord record);

    int updateByPrimaryKey(TRmpsyPersonInfoRecord record);
}