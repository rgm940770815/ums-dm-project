package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsCheckInfoDetailMapper;
import cn.net.withub.ums.entity.UmsCheckInfoDetail;
import cn.net.withub.ums.entity.UmsCheckInfoDetailExample;
import cn.net.withub.ums.service.UmsCheckInfoDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsCheckInfoDetailServiceImpl implements UmsCheckInfoDetailService {

    @Autowired
    UmsCheckInfoDetailMapper mapper;


    @Override
    public int countByExample(UmsCheckInfoDetailExample example) {
        return mapper.countByExample(example);
    }

    @Override
    public int insert(UmsCheckInfoDetail record) {
        return mapper.insert(record);
    }

    @Override
    public int deleteByExample(UmsCheckInfoDetailExample example) {
        return mapper.deleteByExample(example);
    }

    @Override
    public List<UmsCheckInfoDetail> selectByExample(UmsCheckInfoDetailExample example) {
        return mapper.selectByExample(example);
    }
}
