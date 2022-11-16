package cn.net.withub.ums.sbrmpsyDao;

import cn.net.withub.ums.entity.sbrmpsy.TRmpsyAssessorJoinRecord;
import cn.net.withub.ums.entity.sbrmpsy.TRmpsyAssessorJoinRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRmpsyAssessorJoinRecordMapper {
    int countByExample(TRmpsyAssessorJoinRecordExample example);

    int deleteByExample(TRmpsyAssessorJoinRecordExample example);

    int insert(TRmpsyAssessorJoinRecord record);

    int insertSelective(TRmpsyAssessorJoinRecord record);

    List<TRmpsyAssessorJoinRecord> selectByExample(TRmpsyAssessorJoinRecordExample example);

    int updateByExampleSelective(@Param("record") TRmpsyAssessorJoinRecord record, @Param("example") TRmpsyAssessorJoinRecordExample example);

    int updateByExample(@Param("record") TRmpsyAssessorJoinRecord record, @Param("example") TRmpsyAssessorJoinRecordExample example);
}