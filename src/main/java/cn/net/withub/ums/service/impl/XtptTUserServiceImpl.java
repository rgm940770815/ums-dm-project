package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.XtptTUserMapper;
import cn.net.withub.ums.entity.XtptTUser;
import cn.net.withub.ums.entity.XtptTUserExample;
import cn.net.withub.ums.service.XtptTUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/1/29.
 */
@Service
public class XtptTUserServiceImpl implements XtptTUserService {


    @Autowired
    XtptTUserMapper xtptTUserMapper;

    @Override
    public List<XtptTUser> selectByExample(XtptTUserExample example) {
        return xtptTUserMapper.selectByExample(example);
    }

    @Override
    public int update(XtptTUser user) {
        return xtptTUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int updateByExampleSelective(XtptTUser record, XtptTUserExample example)
    {

        return xtptTUserMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateAll(XtptTUser user) {
        return xtptTUserMapper.updateByPrimaryKey(user);
    }
}
