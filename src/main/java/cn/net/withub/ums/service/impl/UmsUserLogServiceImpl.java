package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsUserLogMapper;
import cn.net.withub.ums.entity.UmsUserLog;
import cn.net.withub.ums.entity.UmsUserLogExample;
import cn.net.withub.ums.service.UmsUserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/12/29.
 */
@Service
public class UmsUserLogServiceImpl implements UmsUserLogService {

    @Autowired
    UmsUserLogMapper umsUserLogMapper;

    @Override
    public int insertSelective(UmsUserLog record) {
        return umsUserLogMapper.insertSelective(record);
    }

    @Override
    public List<UmsUserLog> selectByExample(UmsUserLogExample example) {
        return umsUserLogMapper.selectByExample(example);
    }

    @Override
    public UmsUserLog selectByPrimaryKey(String id) {
        return umsUserLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UmsUserLog record) {
        return umsUserLogMapper.updateByPrimaryKeySelective(record);
    }
}
