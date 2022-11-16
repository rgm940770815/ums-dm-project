package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsCustomStatistics;
import cn.net.withub.ums.entity.UmsCustomStatisticsCriteria;

import java.util.List;

/**
 * Created by cuizhibin on 2018/9/13.
 */
public interface UmsCustomStatisticsService extends BaseService<UmsCustomStatistics, UmsCustomStatisticsCriteria> {
    public List<UmsCustomStatistics> search(UmsCustomStatisticsCriteria criteria);
}
