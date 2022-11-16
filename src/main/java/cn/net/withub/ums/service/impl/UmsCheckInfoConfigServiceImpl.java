package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsCheckInfoConfigMapper;
import cn.net.withub.ums.entity.UmsCheckInfoConfig;
import cn.net.withub.ums.entity.UmsCheckInfoConfigExample;
import cn.net.withub.ums.service.UmsCheckInfoConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsCheckInfoConfigServiceImpl implements UmsCheckInfoConfigService {

    @Autowired
    UmsCheckInfoConfigMapper mapper;


    @Override
    public int countByExample(UmsCheckInfoConfigExample example) {
        return mapper.countByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UmsCheckInfoConfig record) {
        return mapper.insert(record);
    }

    @Override
    public List<UmsCheckInfoConfig> selectByExample(UmsCheckInfoConfigExample example) {
        return mapper.selectByExample(example);
    }

    @Override
    public UmsCheckInfoConfig selectByPrimaryKey(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UmsCheckInfoConfig record) {
        return mapper.updateByPrimaryKeySelective(record);
    }
}
