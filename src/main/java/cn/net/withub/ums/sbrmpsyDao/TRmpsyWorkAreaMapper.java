package cn.net.withub.ums.sbrmpsyDao;

import cn.net.withub.ums.entity.sbrmpsy.TRmpsyWorkArea;
import cn.net.withub.ums.entity.sbrmpsy.TRmpsyWorkAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRmpsyWorkAreaMapper {
    int countByExample(TRmpsyWorkAreaExample example);

    int deleteByExample(TRmpsyWorkAreaExample example);

    int insert(TRmpsyWorkArea record);

    int insertSelective(TRmpsyWorkArea record);

    List<TRmpsyWorkArea> selectByExample(TRmpsyWorkAreaExample example);

    int updateByExampleSelective(@Param("record") TRmpsyWorkArea record, @Param("example") TRmpsyWorkAreaExample example);

    int updateByExample(@Param("record") TRmpsyWorkArea record, @Param("example") TRmpsyWorkAreaExample example);
}