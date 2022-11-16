package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsCheckInfoConfig;
import cn.net.withub.ums.entity.UmsCheckInfoConfigExample;

import java.util.List;

public interface UmsCheckInfoConfigService {

    int countByExample(UmsCheckInfoConfigExample example);

    int deleteByPrimaryKey(String id);

    int insert(UmsCheckInfoConfig record);

    List<UmsCheckInfoConfig> selectByExample(UmsCheckInfoConfigExample example);

    UmsCheckInfoConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UmsCheckInfoConfig record);
}
