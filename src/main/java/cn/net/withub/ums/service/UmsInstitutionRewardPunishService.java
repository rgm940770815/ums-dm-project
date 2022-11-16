package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsInstitutionRewardPunish;
import cn.net.withub.ums.entity.UmsInstitutionRewardPunishCriteria;

import java.util.List;

public interface UmsInstitutionRewardPunishService {

    int countByExample(UmsInstitutionRewardPunishCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(UmsInstitutionRewardPunish record);

    int insertSelective(UmsInstitutionRewardPunish record);

    List<UmsInstitutionRewardPunish> selectByExample(UmsInstitutionRewardPunishCriteria example);

    UmsInstitutionRewardPunish selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UmsInstitutionRewardPunish record);

}
