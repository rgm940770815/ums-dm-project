package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsApplyForUpdateMapper;
import cn.net.withub.ums.entity.UmsApplyForUpdate;
import cn.net.withub.ums.entity.UmsApplyForUpdateExample;
import cn.net.withub.ums.service.UmsApplyForUpdateService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Cypress on 2016/12/7.
 */
@Service
public class UmsApplyForUpdateServiceImpl implements UmsApplyForUpdateService {

    @Autowired
    UmsApplyForUpdateMapper mapper;

    @Override
    public int insert(UmsApplyForUpdate record) {
        return mapper.insert(record);
    }

    @Override
    public List<UmsApplyForUpdate> selectByExample(UmsApplyForUpdateExample example) {
        return mapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKeySelective(UmsApplyForUpdate record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int countByExample(UmsApplyForUpdateExample example) {
        return mapper.countByExample(example);
    }
}
