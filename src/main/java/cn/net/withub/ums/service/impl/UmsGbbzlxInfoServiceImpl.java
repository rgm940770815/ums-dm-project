package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsGbbzlxInfoMapper;
import cn.net.withub.ums.entity.UmsGbbzlxInfo;
import cn.net.withub.ums.entity.UmsGbbzlxInfoExample;
import cn.net.withub.ums.service.UmsGbbzlxInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsGbbzlxInfoServiceImpl implements UmsGbbzlxInfoService {

    @Autowired
    UmsGbbzlxInfoMapper umsGbbzlxInfoMapper;

    @Override
    public int countByExample(UmsGbbzlxInfoExample example) {
        return umsGbbzlxInfoMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(UmsGbbzlxInfoExample example) {
        return umsGbbzlxInfoMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String changeuuid) {
        return deleteByPrimaryKey(changeuuid);
    }

    @Override
    public int insert(UmsGbbzlxInfo record) {
        return umsGbbzlxInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(UmsGbbzlxInfo record) {
        return umsGbbzlxInfoMapper.insertSelective(record);
    }

    @Override
    public List<UmsGbbzlxInfo> selectByExample(UmsGbbzlxInfoExample example) {
        return umsGbbzlxInfoMapper.selectByExample(example);
    }

    @Override
    public UmsGbbzlxInfo selectByPrimaryKey(String changeuuid) {
        return umsGbbzlxInfoMapper.selectByPrimaryKey(changeuuid);
    }

    @Override
    public int updateByExampleSelective(UmsGbbzlxInfo record, UmsGbbzlxInfoExample example) {
        return umsGbbzlxInfoMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(UmsGbbzlxInfo record, UmsGbbzlxInfoExample example) {
        return umsGbbzlxInfoMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(UmsGbbzlxInfo record) {
        return umsGbbzlxInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UmsGbbzlxInfo record) {
        return umsGbbzlxInfoMapper.updateByPrimaryKey(record);
    }
}
