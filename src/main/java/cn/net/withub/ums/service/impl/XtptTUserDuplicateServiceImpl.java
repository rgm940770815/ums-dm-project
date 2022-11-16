package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.XtptTUserDuplicateMapper;
import cn.net.withub.ums.entity.XtptTUserDuplicate;
import cn.net.withub.ums.entity.XtptTUserDuplicateExample;
import cn.net.withub.ums.entity.XtptUser;
import cn.net.withub.ums.service.XtptTUserDuplicateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/1/1.
 */
@Service
public class XtptTUserDuplicateServiceImpl implements XtptTUserDuplicateService {

    @Autowired
    XtptTUserDuplicateMapper xtptTUserDuplicateMapper;

    @Override
    public int insert(XtptTUserDuplicate entity) {
        return xtptTUserDuplicateMapper.insert(entity);
    }

    @Override
    public int insertForXtptUser(XtptUser entity) {
        return xtptTUserDuplicateMapper.insertForXtptUser(entity);
    }

    @Override
    public int deleteByUUID(XtptTUserDuplicate entity) {
        return xtptTUserDuplicateMapper.deleteByUUID(entity);
    }

    @Override
    public int update(XtptTUserDuplicate entity) {
        return xtptTUserDuplicateMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<XtptTUserDuplicate> selectByExample(XtptTUserDuplicateExample example) {
        return xtptTUserDuplicateMapper.selectByExample(example);
    }
}
