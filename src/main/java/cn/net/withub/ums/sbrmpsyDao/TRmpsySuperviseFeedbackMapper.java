package cn.net.withub.ums.sbrmpsyDao;

import cn.net.withub.ums.entity.sbrmpsy.TRmpsySuperviseFeedback;
import cn.net.withub.ums.entity.sbrmpsy.TRmpsySuperviseFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRmpsySuperviseFeedbackMapper {
    int countByExample(TRmpsySuperviseFeedbackExample example);

    int deleteByExample(TRmpsySuperviseFeedbackExample example);

    int deleteByPrimaryKey(String id);

    int insert(TRmpsySuperviseFeedback record);

    int insertSelective(TRmpsySuperviseFeedback record);

    List<TRmpsySuperviseFeedback> selectByExample(TRmpsySuperviseFeedbackExample example);

    TRmpsySuperviseFeedback selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRmpsySuperviseFeedback record, @Param("example") TRmpsySuperviseFeedbackExample example);

    int updateByExample(@Param("record") TRmpsySuperviseFeedback record, @Param("example") TRmpsySuperviseFeedbackExample example);

    int updateByPrimaryKeySelective(TRmpsySuperviseFeedback record);

    int updateByPrimaryKey(TRmpsySuperviseFeedback record);
}