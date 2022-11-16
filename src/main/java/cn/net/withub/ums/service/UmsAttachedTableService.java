/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * @author Diluka
 */
public interface UmsAttachedTableService {

    int countView(String viewName, String userId);

    int countView1(String viewName, String userId, Map otherParam);

    List selectView(String viewName, String userId, String order, RowBounds rowBounds);

    List selectViewAppendWhere(String viewName, String userId, String order, RowBounds rowBounds, String where);

    List selectView1(String viewName, String userId, String order, Map otherParam, RowBounds rowBounds);

    List selectView(String viewName, String userId);

    Map<String, Object> selectViewNoAspect(String viewName, String userId, String order , String[] field);

    Map<String, Object> selectDataSetView(Map views, String userId);

    int insert(Object entity);

    int updateSelective(Object entity, String primarykey, boolean onlyaddnew, List<String> compareFields, boolean tempTable);

    int delete(Object entity);

    int deleteById(Object entity, String id);

    int update(Object entity);

    <T> List<T> selectView(Class<?> mapperClass, String userId);

    List<Map<String, Object>> selectAll(String tableName, int start, int limit);

    List<Map<String, Object>> selectAllView(String tableName, int start, int limit);

    int count(String tableName);

    Integer countNoAspect(String tableName);

    List<Map<String, Object>> selectSingleInfo(String tableName, String userid);

    int countViewBatch(String viewName);

    List selectViewBatch(String viewName, String order, RowBounds rowBounds);

    List selectViewAllBatch(String viewName, String order, String where);

    int insertBatch(Object entity);

    int updateBatch(Object entity);

    int AttachmentCourtUpdate(String userId, Integer courtNo);

    Map<String,Object> selectViewPresent(String viewName, String userId);

    //获取n_present_type = 1 的  level_info
    List<Map<String,Object>> selectActiveLevelInfo(Integer nLevelType);
}
