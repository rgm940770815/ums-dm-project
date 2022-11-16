package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.attach.UmsUserInfoAttachedViewsMapper;
import cn.net.withub.ums.dao.attach.experimental.UmsAttachedTableMapper;
import cn.net.withub.ums.service.UmsUploadRecordInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Cypress on 2016/5/10.
 */
@Service
public class UmsUploadRecordInfoServiceImpl implements UmsUploadRecordInfoService {

    @Autowired
    private UmsUserInfoAttachedViewsMapper umsUserInfoAttachedViewsMapper;

    @Autowired
    private UmsAttachedTableMapper umsAttachedTableMapper;

    @Override
    public Date selectLastUpdateTime() {
        Date date = umsUserInfoAttachedViewsMapper.selectLastUpdateTime();
        return date;
    }

    @Override
    public int UpdateTime() {
        return umsAttachedTableMapper.insertUpdateTime();
    }
}
