package cn.net.withub.ums.sbrmpsyDao;

import cn.net.withub.ums.entity.sbrmpsy.TRmpsyTrainingClassInfo;
import cn.net.withub.ums.entity.sbrmpsy.TRmpsyTrainingClassInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRmpsyTrainingClassInfoMapper {
    int countByExample(TRmpsyTrainingClassInfoExample example);

    int deleteByExample(TRmpsyTrainingClassInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(TRmpsyTrainingClassInfo record);

    int insertSelective(TRmpsyTrainingClassInfo record);

    List<TRmpsyTrainingClassInfo> selectByExample(TRmpsyTrainingClassInfoExample example);

    TRmpsyTrainingClassInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRmpsyTrainingClassInfo record, @Param("example") TRmpsyTrainingClassInfoExample example);

    int updateByExample(@Param("record") TRmpsyTrainingClassInfo record, @Param("example") TRmpsyTrainingClassInfoExample example);

    int updateByPrimaryKeySelective(TRmpsyTrainingClassInfo record);

    int updateByPrimaryKey(TRmpsyTrainingClassInfo record);
}