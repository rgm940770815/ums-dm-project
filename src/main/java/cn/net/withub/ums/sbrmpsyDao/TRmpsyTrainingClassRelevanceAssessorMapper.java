package cn.net.withub.ums.sbrmpsyDao;

import cn.net.withub.ums.entity.sbrmpsy.TRmpsyTrainingClassRelevanceAssessor;
import cn.net.withub.ums.entity.sbrmpsy.TRmpsyTrainingClassRelevanceAssessorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRmpsyTrainingClassRelevanceAssessorMapper {
    int countByExample(TRmpsyTrainingClassRelevanceAssessorExample example);

    int deleteByExample(TRmpsyTrainingClassRelevanceAssessorExample example);

    int deleteByPrimaryKey(String id);

    int insert(TRmpsyTrainingClassRelevanceAssessor record);

    int insertSelective(TRmpsyTrainingClassRelevanceAssessor record);

    List<TRmpsyTrainingClassRelevanceAssessor> selectByExample(TRmpsyTrainingClassRelevanceAssessorExample example);

    TRmpsyTrainingClassRelevanceAssessor selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRmpsyTrainingClassRelevanceAssessor record, @Param("example") TRmpsyTrainingClassRelevanceAssessorExample example);

    int updateByExample(@Param("record") TRmpsyTrainingClassRelevanceAssessor record, @Param("example") TRmpsyTrainingClassRelevanceAssessorExample example);

    int updateByPrimaryKeySelective(TRmpsyTrainingClassRelevanceAssessor record);

    int updateByPrimaryKey(TRmpsyTrainingClassRelevanceAssessor record);
}