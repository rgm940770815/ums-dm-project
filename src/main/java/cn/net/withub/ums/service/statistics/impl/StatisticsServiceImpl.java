/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.statistics.impl;

import cn.net.withub.ums.dao.extend.UmsStatisticsMapper;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.model.statistics.ChartItem;
import cn.net.withub.ums.service.statistics.StatisticsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private UmsStatisticsMapper statisticsMapper;

    @Override
    public List<ChartItem> positionChart(List<Integer> courts) {
        List<Map<String, Object>> list = statisticsMapper.positionStat(courts);
        return ChartItem.fromMapList(list);
    }

    @Override
    public List<ChartItem> courtUserChart(List<Integer> courts) {
        List<Map<String, Object>> list = statisticsMapper.courtUserStat(courts);
        return ChartItem.fromMapList(list);
    }

    @Override
    public List<ChartItem> userGenderChart(List<Integer> courts) {
        List<Map<String, Object>> list = statisticsMapper.userGenderStat(courts);
        return ChartItem.fromMapList(list);
    }

    @Override
    public List<ChartItem> legalJobChart(List<Integer> courts) {
        List<Map<String, Object>> list = statisticsMapper.legalJobStat(courts);
        return ChartItem.fromMapList(list);
    }

    @Override
    public List<ChartItem> lawyerLevelChart(List<Integer> courts) {
        List<Map<String, Object>> list = statisticsMapper.lawyerLevelStat(courts);
        return ChartItem.fromMapList(list);
    }

    @Override
    public List<ChartItem> adminJobChart(List<Integer> courts) {
        List<Map<String, Object>> list = statisticsMapper.adminJobStat(courts);
        return ChartItem.fromMapList(list);
    }

    @Override
    public List<UmsUserInfoView> recentlyPositionAlteredPersonnelList(int size) {
        return statisticsMapper.recentlyPositionAlteredPersonnelList(size);
    }

    @Override
    public List<ChartItem> lawPositionChart(String s) {
        Map<String, Object> map = statisticsMapper.lawPositionChart(s);
        List<Map<String, Object>> list = new ArrayList<>();
        for (String ss:map.keySet()) {
            Map map1 = new HashMap<>();
            map1.put("name",ss);
            map1.put("y",map.get(ss));
            list.add(map1);
        }
        return ChartItem.fromMapList(list);
    }

}
