package cn.net.withub.ums.sbrmpsyDao;

import cn.net.withub.ums.entity.sbrmpsy.TRmpsyLocalCourtSetting;
import cn.net.withub.ums.entity.sbrmpsy.TRmpsyLocalCourtSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRmpsyLocalCourtSettingMapper {
    int countByExample(TRmpsyLocalCourtSettingExample example);

    int deleteByExample(TRmpsyLocalCourtSettingExample example);

    int deleteByPrimaryKey(String id);

    int insert(TRmpsyLocalCourtSetting record);

    int insertSelective(TRmpsyLocalCourtSetting record);

    List<TRmpsyLocalCourtSetting> selectByExample(TRmpsyLocalCourtSettingExample example);

    TRmpsyLocalCourtSetting selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRmpsyLocalCourtSetting record, @Param("example") TRmpsyLocalCourtSettingExample example);

    int updateByExample(@Param("record") TRmpsyLocalCourtSetting record, @Param("example") TRmpsyLocalCourtSettingExample example);

    int updateByPrimaryKeySelective(TRmpsyLocalCourtSetting record);

    int updateByPrimaryKey(TRmpsyLocalCourtSetting record);
}