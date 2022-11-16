package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsInstitutionRewardPunish;
import cn.net.withub.ums.entity.UmsInstitutionRewardPunishCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsInstitutionRewardPunishMapper {
    int countByExample(UmsInstitutionRewardPunishCriteria example);

    int deleteByExample(UmsInstitutionRewardPunishCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(UmsInstitutionRewardPunish record);

    int insertSelective(UmsInstitutionRewardPunish record);

    List<UmsInstitutionRewardPunish> selectByExample(UmsInstitutionRewardPunishCriteria example);

    UmsInstitutionRewardPunish selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UmsInstitutionRewardPunish record, @Param("example") UmsInstitutionRewardPunishCriteria example);

    int updateByExample(@Param("record") UmsInstitutionRewardPunish record, @Param("example") UmsInstitutionRewardPunishCriteria example);

    int updateByPrimaryKeySelective(UmsInstitutionRewardPunish record);

    int updateByPrimaryKey(UmsInstitutionRewardPunish record);
}