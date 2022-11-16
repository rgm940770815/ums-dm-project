package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsNoticeMapper;
import cn.net.withub.ums.entity.UmsNotice;
import cn.net.withub.ums.entity.UmsNoticeExample;
import cn.net.withub.ums.service.UmsNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Cypress on 2016/11/28.
 */
@Service
public class UmsNoticeServiceImpl implements UmsNoticeService {

    @Autowired
    UmsNoticeMapper mapper;

    @Override
    public List<UmsNotice> selectByExample(UmsNoticeExample example) {
        return mapper.selectByExample(example);
    }

    @Override
    public int countByExample(UmsNoticeExample example) {
        return mapper.countByExample(example);
    }

    @Override
    public int infoInsert(UmsNotice record) {
        return mapper.insert(record);
    }

    @Override
    public int InfoDeleteByPrimaryKey(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public UmsNotice selectByPrimaryKey(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsNotice> selectByExampleOrderByTime(UmsNoticeExample example) {
        return mapper.selectByExampleOrderByTime(example);
    }

    @Override
    public List<UmsNotice> selectByCourt(Integer court) {
        return mapper.selectByCourt(court);
    }

    @Override
    public int updateByPrimaryKeySelective(UmsNotice record)
    {
        return mapper.updateByPrimaryKeySelective(record);
    }
}
