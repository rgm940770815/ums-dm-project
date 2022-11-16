/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.statistics;

import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.model.statistics.ChartItem;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Diluka
 */
public interface StatisticsService {

    /**
     * 职务饼图
     *
     * @return
     */
    List<ChartItem> positionChart(List<Integer> courts);

    /**
     * 人员分布柱状图
     *
     * @return
     */
    List<ChartItem> courtUserChart(List<Integer> courts);

    /**
     * 性别比例
     *
     * @param courts
     * @return
     */
    List<ChartItem> userGenderChart(List<Integer> courts);

    /**
     * 法律职务
     *
     * @param courts
     * @return
     */
    List<ChartItem> legalJobChart(List<Integer> courts);

    /**
     * 法官等级
     *
     * @param courts
     * @return
     */
    List<ChartItem> lawyerLevelChart(List<Integer> courts);

    /**
     * 行政职务
     *
     * @param courts
     * @return
     */
    List<ChartItem> adminJobChart(List<Integer> courts);

    /**
     * 最近职务变动人员
     *
     * @return
     */
    List<UmsUserInfoView> recentlyPositionAlteredPersonnelList(int size);

    /*全市法院人员分布
     */
    List<ChartItem> lawPositionChart(String s);
}
