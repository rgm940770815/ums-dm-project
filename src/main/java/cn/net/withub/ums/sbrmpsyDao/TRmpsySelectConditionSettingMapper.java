package cn.net.withub.ums.sbrmpsyDao;

import cn.net.withub.ums.entity.sbrmpsy.TRmpsySelectConditionSetting;
import cn.net.withub.ums.entity.sbrmpsy.TRmpsySelectConditionSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRmpsySelectConditionSettingMapper {
    int countByExample(TRmpsySelectConditionSettingExample example);

    int deleteByExample(TRmpsySelectConditionSettingExample example);

    int deleteByPrimaryKey(String id);

    int insert(TRmpsySelectConditionSetting record);

    int insertSelective(TRmpsySelectConditionSetting record);

    List<TRmpsySelectConditionSetting> selectByExample(TRmpsySelectConditionSettingExample example);

    TRmpsySelectConditionSetting selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRmpsySelectConditionSetting record, @Param("example") TRmpsySelectConditionSettingExample example);

    int updateByExample(@Param("record") TRmpsySelectConditionSetting record, @Param("example") TRmpsySelectConditionSettingExample example);

    int updateByPrimaryKeySelective(TRmpsySelectConditionSetting record);

    int updateByPrimaryKey(TRmpsySelectConditionSetting record);
}