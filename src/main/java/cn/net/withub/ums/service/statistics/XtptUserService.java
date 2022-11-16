package cn.net.withub.ums.service.statistics;

import cn.net.withub.ums.entity.UserForXtpt;
import cn.net.withub.ums.entity.XtptUser;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/28.
 */
public interface XtptUserService {


    int deleteByUUID(String uuid);

    XtptUser searchByUUID(String uuid);

    UserForXtpt searchByUUIDForXtpt(String uuid);

    int insertForXtpt(UserForXtpt user);

    int insertForXtptN(UserForXtpt user);

    public List<XtptUser> getXtptUserList(String fydm, Map<String, Object> parameters);

    int updateByPrimaryKeySelective(XtptUser record);
}
