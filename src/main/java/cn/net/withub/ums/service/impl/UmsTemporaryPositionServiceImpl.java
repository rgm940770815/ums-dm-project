package cn.net.withub.ums.service.impl;

/**
 * Created by Administrator on 2015/12/23.
 */

import cn.net.withub.ums.dao.UmsTemporaryPositionMapper;
import cn.net.withub.ums.entity.UmsTemporaryPosition;
import cn.net.withub.ums.entity.UmsTemporaryPositionExample;
import cn.net.withub.ums.service.UmsTemporaryPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/23.
 */
@Service
public class UmsTemporaryPositionServiceImpl implements UmsTemporaryPositionService {

    @Autowired
    private UmsTemporaryPositionMapper umsTemporaryPositionMapper;

    @Override
    public int insert(UmsTemporaryPosition entity) {
        return umsTemporaryPositionMapper.insert(entity);
    }

    @Override
    public int delete(UmsTemporaryPosition entity) {
        return umsTemporaryPositionMapper.deleteByPrimaryKey(entity);
    }

    @Override
    public int deleteByUUID(UmsTemporaryPosition entity) {
        return umsTemporaryPositionMapper.deleteByUUID(entity);
    }


    @Override
    public int update(UmsTemporaryPosition entity) {
        return umsTemporaryPositionMapper.updateByPrimaryKeySelective(entity);
    }


    @Override
    public List<UmsTemporaryPosition> selectByExample(UmsTemporaryPositionExample example) {
        return umsTemporaryPositionMapper.selectByExample(example);
    }

    @Override
    public List<Map> selectViewByExample(UmsTemporaryPositionExample example) {
        return umsTemporaryPositionMapper.selectViewByExample(example);
    }

    @Override
    public int updatePartTimeJob(Map map) {
        return umsTemporaryPositionMapper.updatePartTimeJob(map);
    }

}
