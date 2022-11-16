package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsFieldContrastMapper;
import cn.net.withub.ums.entity.UmsFieldContrast;
import cn.net.withub.ums.entity.UmsFieldContrastExample;
import cn.net.withub.ums.service.UmsFieldContrastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/1/15.
 */
@Service
public class UmsFieldContrastServiceImpl implements UmsFieldContrastService {

    @Autowired
    UmsFieldContrastMapper umsFieldContrastMapper;

    @Override
    public List<UmsFieldContrast> selectByExample(UmsFieldContrastExample example) {
        return umsFieldContrastMapper.selectByExample(example);
    }

    @Override
    public List<String> selectTableName() {
        return umsFieldContrastMapper.selectTableName();
    }
}
