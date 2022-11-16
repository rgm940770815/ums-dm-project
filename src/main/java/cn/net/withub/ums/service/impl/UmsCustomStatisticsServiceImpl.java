package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsCustomStatisticsMapper;
import cn.net.withub.ums.entity.UmsCustomStatistics;
import cn.net.withub.ums.entity.UmsCustomStatisticsCriteria;
import cn.net.withub.ums.service.BaseService;
import cn.net.withub.ums.service.UmsCustomStatisticsService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cuizhibin on 2018/9/13.
 */
@Service
public class UmsCustomStatisticsServiceImpl implements UmsCustomStatisticsService {

    @Autowired
    UmsCustomStatisticsMapper umsCustomStatisticsMapper;

    @Override
    public int insert(UmsCustomStatistics entity) {
        return umsCustomStatisticsMapper.insertSelective(entity);
    }

    @Override
    public int delete(UmsCustomStatistics entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int deleteById(Object id) {
        return umsCustomStatisticsMapper.deleteByPrimaryKey((Integer) id);
    }

    @Override
    public int update(UmsCustomStatistics entity) {
        return umsCustomStatisticsMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public UmsCustomStatistics selectById(Object id) {
        return umsCustomStatisticsMapper.selectByPrimaryKey((Integer) id);
    }

    @Override
    public int countAll() {
        return umsCustomStatisticsMapper.countByExample(new UmsCustomStatisticsCriteria());
    }

    @Override
    public List<UmsCustomStatistics> search(UmsCustomStatisticsCriteria criteria) {
        return umsCustomStatisticsMapper.selectByExampleWithBLOBs(criteria);
    }

    @Override
    public List<UmsCustomStatistics> search(UmsCustomStatisticsCriteria criteria, RowBounds rowBounds) {
        return umsCustomStatisticsMapper.selectByExampleWithBLOBsWithRowbounds(criteria, rowBounds);
    }

    @Override
    public int count(UmsCustomStatisticsCriteria criteria) {
        return umsCustomStatisticsMapper.countByExample(criteria);
    }
}
