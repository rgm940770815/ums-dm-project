package cn.net.withub.ums.sbrmpsyDao;

import cn.net.withub.ums.entity.sbrmpsy.TRmpsyAchievementEvaluation;
import cn.net.withub.ums.entity.sbrmpsy.TRmpsyAchievementEvaluationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRmpsyAchievementEvaluationMapper {
    int countByExample(TRmpsyAchievementEvaluationExample example);

    int deleteByExample(TRmpsyAchievementEvaluationExample example);

    int deleteByPrimaryKey(String id);

    int insert(TRmpsyAchievementEvaluation record);

    int insertSelective(TRmpsyAchievementEvaluation record);

    List<TRmpsyAchievementEvaluation> selectByExample(TRmpsyAchievementEvaluationExample example);

    TRmpsyAchievementEvaluation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRmpsyAchievementEvaluation record, @Param("example") TRmpsyAchievementEvaluationExample example);

    int updateByExample(@Param("record") TRmpsyAchievementEvaluation record, @Param("example") TRmpsyAchievementEvaluationExample example);

    int updateByPrimaryKeySelective(TRmpsyAchievementEvaluation record);

    int updateByPrimaryKey(TRmpsyAchievementEvaluation record);
}