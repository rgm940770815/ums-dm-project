package cn.net.withub.ums.sbrmpsyDao;

import cn.net.withub.ums.entity.sbrmpsy.TRmpsyAssessorInfo;
import cn.net.withub.ums.entity.sbrmpsy.TRmpsyAssessorInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRmpsyAssessorInfoMapper {
    int countByExample(TRmpsyAssessorInfoExample example);

    int deleteByExample(TRmpsyAssessorInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(TRmpsyAssessorInfo record);

    int insertSelective(TRmpsyAssessorInfo record);

    List<TRmpsyAssessorInfo> selectByExample(TRmpsyAssessorInfoExample example);

    TRmpsyAssessorInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRmpsyAssessorInfo record, @Param("example") TRmpsyAssessorInfoExample example);

    int updateByExample(@Param("record") TRmpsyAssessorInfo record, @Param("example") TRmpsyAssessorInfoExample example);

    int updateByPrimaryKeySelective(TRmpsyAssessorInfo record);

    int updateByPrimaryKey(TRmpsyAssessorInfo record);
}