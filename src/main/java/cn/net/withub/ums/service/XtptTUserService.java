package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.XtptTUser;
import cn.net.withub.ums.entity.XtptTUserExample;

import java.util.List;

/**
 * Created by Administrator on 2016/1/29.
 */
public interface XtptTUserService {


    List<XtptTUser> selectByExample(XtptTUserExample example);

    int update(XtptTUser user);

    int updateByExampleSelective(XtptTUser record,XtptTUserExample example);

    int updateAll(XtptTUser user);

}
