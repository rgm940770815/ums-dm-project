package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.XtptUserMapper;
import cn.net.withub.ums.entity.UserForXtpt;
import cn.net.withub.ums.entity.XtptUser;
import cn.net.withub.ums.entity.XtptUserCriteria;
import cn.net.withub.ums.service.statistics.XtptUserService;
import cn.net.withub.ums.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/28.
 */

@Service
public class XtptUserServiceImpl implements XtptUserService {

    @Autowired
    private XtptUserMapper xtptUserMapper;

    @Override
    public int deleteByUUID(String uuid) {
        return xtptUserMapper.deleteByUUID(uuid);
    }

    @Override
    public XtptUser searchByUUID(String uuid) {
        return xtptUserMapper.searchByUUID(uuid);
    }

    @Override
    public UserForXtpt searchByUUIDForXtpt(String uuid) {
        return xtptUserMapper.searchByUUIDForXtpt(uuid);
    }

    @Override
    public int insertForXtpt(UserForXtpt user) {
        return  xtptUserMapper.insertForXtpt(user);
    }

    @Override
    public int insertForXtptN(UserForXtpt user) {
        return  xtptUserMapper.insertForXtptN(user);
    }

    @Override
    public List<XtptUser> getXtptUserList(String fydm, Map<String, Object> parameters) {
        XtptUserCriteria example = new XtptUserCriteria();

        String username = "";

        if(parameters != null && parameters.get("username") != null){
            username = parameters.get("username").toString();
        }


        XtptUserCriteria.Criteria criteria = example.createCriteria();

        criteria.andEnabledEqualTo(true);
        if(StringUtils.isNotEmpty(fydm)){
            criteria.andCourtCodeEqualTo(fydm);
        }
        if(StringUtils.isNotEmpty(username)){
            criteria.andUsernameLike("%"+username+"%");
        }

        List<XtptUser> list = xtptUserMapper.selectByExample(example);

        return list;
    }

    @Override
    public int updateByPrimaryKeySelective(XtptUser record) {
        return xtptUserMapper.updateByPrimaryKeySelective(record);
    }
}

