package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.XtptTUserDuplicate;
import cn.net.withub.ums.entity.XtptTUserDuplicateExample;
import cn.net.withub.ums.entity.XtptUser;

import java.util.List;

/**
 * Created by Administrator on 2016/1/1.
 */
public interface XtptTUserDuplicateService {

    public int insert(XtptTUserDuplicate entity);

    public int insertForXtptUser(XtptUser entity);

    public int deleteByUUID(XtptTUserDuplicate entity);

    public int update(XtptTUserDuplicate entity);

    public List<XtptTUserDuplicate> selectByExample(XtptTUserDuplicateExample example);

}
