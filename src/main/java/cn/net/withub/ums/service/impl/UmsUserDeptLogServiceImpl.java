package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsUserDeptLogMapper;
import cn.net.withub.ums.entity.UmsUserDeptLog;
import cn.net.withub.ums.service.UmsUserDeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/1/22.
 */
@Service
public class UmsUserDeptLogServiceImpl implements UmsUserDeptLogService {

    @Autowired
    UmsUserDeptLogMapper umsUserDeptLogMapper;

    @Override
    public int insert(UmsUserDeptLog log) {
        return umsUserDeptLogMapper.insert(log);
    }
}
