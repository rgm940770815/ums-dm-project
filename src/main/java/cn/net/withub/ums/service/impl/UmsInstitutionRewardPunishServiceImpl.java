package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsInstitutionRewardPunishMapper;
import cn.net.withub.ums.entity.UmsInstitutionRewardPunish;
import cn.net.withub.ums.entity.UmsInstitutionRewardPunishCriteria;
import cn.net.withub.ums.service.UmsInstitutionRewardPunishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsInstitutionRewardPunishServiceImpl implements UmsInstitutionRewardPunishService {

    @Autowired
    UmsInstitutionRewardPunishMapper mapper;

    @Override
    public int countByExample(UmsInstitutionRewardPunishCriteria example) {
        return mapper.countByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UmsInstitutionRewardPunish record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(UmsInstitutionRewardPunish record) {
        return mapper.insertSelective(record);
    }

    @Override
    public List<UmsInstitutionRewardPunish> selectByExample(UmsInstitutionRewardPunishCriteria example) {
        return mapper.selectByExample(example);
    }

    @Override
    public UmsInstitutionRewardPunish selectByPrimaryKey(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UmsInstitutionRewardPunish record) {
        return mapper.updateByPrimaryKeySelective(record);
    }
}
