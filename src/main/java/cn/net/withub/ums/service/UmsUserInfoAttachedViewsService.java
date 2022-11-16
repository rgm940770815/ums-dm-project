/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import java.util.List;
import org.apache.ibatis.session.RowBounds;

/**
 *
 * @author Diluka
 */
public interface UmsUserInfoAttachedViewsService {

    /**
     * 根据附表名称和UserID查询
     *
     * @param <T>
     * @param viewName
     * @param userId
     * @return
     */
    <T> List<T> attachedView(String viewName, String userId);

    /**
     * 根据附表名称和UserID查询
     *
     * @param <T>
     * @param viewName
     * @param userId
     * @param rowBounds
     * @return
     */
    <T> List<T> attachedView(String viewName, String userId, RowBounds rowBounds);

    int attachedCount(String viewName, String userId);
}
