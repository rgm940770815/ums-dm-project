package cn.net.withub.ums.sbrmpsyDao;

import cn.net.withub.ums.entity.sbrmpsy.TRmpsyAssessmentEvaluation;
import cn.net.withub.ums.entity.sbrmpsy.TRmpsyAssessmentEvaluationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRmpsyAssessmentEvaluationMapper {
    int countByExample(TRmpsyAssessmentEvaluationExample example);

    int deleteByExample(TRmpsyAssessmentEvaluationExample example);

    int deleteByPrimaryKey(String id);

    int insert(TRmpsyAssessmentEvaluation record);

    int insertSelective(TRmpsyAssessmentEvaluation record);

    List<TRmpsyAssessmentEvaluation> selectByExample(TRmpsyAssessmentEvaluationExample example);

    TRmpsyAssessmentEvaluation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRmpsyAssessmentEvaluation record, @Param("example") TRmpsyAssessmentEvaluationExample example);

    int updateByExample(@Param("record") TRmpsyAssessmentEvaluation record, @Param("example") TRmpsyAssessmentEvaluationExample example);

    int updateByPrimaryKeySelective(TRmpsyAssessmentEvaluation record);

    int updateByPrimaryKey(TRmpsyAssessmentEvaluation record);
}