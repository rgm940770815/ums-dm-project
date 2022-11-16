/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.dao.attach.experimental;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * @author Diluka
 */
public interface UmsAttachedTableMapper {

    @SelectProvider(type = AttachedTableProvider.class, method = "selectView")
        //@Select("select * from ums_rank_info_view where user_id=#{1} order by sort_no")
    List<Map<String, Object>> selectView(String viewName, String userId, String order, RowBounds rowBounds);

    // 在上一个方法的基础上,添加个传入where条件的参数
    @SelectProvider(type = AttachedTableProvider.class, method = "selectViewAppendWhere")
    List<Map<String, Object>> selectViewAppendWhere(String viewName, String userId, String order, RowBounds rowBounds, String where);

    @SelectProvider(type = AttachedTableProvider.class, method = "selectView1")
        //@Select("select * from ums_rank_info_view where user_id=#{1} order by sort_no")
    List<Map<String, Object>> selectView1(String viewName, String userId, String order, Map otherParam, RowBounds rowBounds);

    @SelectProvider(type = AttachedTableProvider.class, method = "selectDataSetView")
    Map<String, Object> selectDataSetView(Map views, String userId);

    @SelectProvider(type = AttachedTableProvider.class, method = "countView")
    int countView(String viewName, String userId);

    @SelectProvider(type = AttachedTableProvider.class, method = "countView1")
    int countView1(String viewName, String userId, Map otherParam);

    @SelectProvider(type = AttachedTableProvider.class, method = "selectViewNoAspect")
    Map<String, Object> selectViewNoAspect(String viewName, String userId, String order, String[] filed);

    @SelectProvider(type = AttachedTableProvider.class, method = "selectAll")
    List<Map<String, Object>> selectAll(String tableName, int start, int limit);

    @SelectProvider(type = AttachedTableProvider.class, method = "selectAllView")
    List<Map<String, Object>> selectAllView(String tableName, int start, int limit);

    @SelectProvider(type = AttachedTableProvider.class, method = "count")
    int count(String tableName);

    @SelectProvider(type = AttachedTableProvider.class, method = "selectSingleInfo")
    List<Map<String, Object>> selectSingleInfo(String tableName, String userid);

    @InsertProvider(type = MabitsProvider.class, method = "insertUpdateTime")
    int insertUpdateTime();

    @SelectProvider(type = AttachedTableProvider.class, method = "selectViewBatch")
    List<Map<String, Object>> selectViewBatch(String viewName, String order, RowBounds rowBounds);

    @SelectProvider(type = AttachedTableProvider.class, method = "countViewBatch")
    int countViewBatch(String viewName);

    @SelectProvider(type = AttachedTableProvider.class, method = "selectViewAllBatch")
    List<Map<String, Object>> selectViewAllBatch(String viewName, String order, String where);

    @UpdateProvider(type = AttachedTableProvider.class, method = "AttachmentCourtUpdate")
    int AttachmentCourtUpdate(String userId, Integer courtNo);

    @SelectProvider(type = AttachedTableProvider.class, method = "selectViewPresent")
    Map<String, Object> selectViewPresent(String viewName, String userId);

    @SelectProvider(type = AttachedTableProvider.class, method = "selectActiveLevelInfo")
    List<Map<String, Object>> selectActiveLevelInfo(Integer nLevelType);

    @SelectProvider(type = AttachedTableProvider.class, method = "selectDataById")
    Map<String, Object> selectDataById(String viewName, String id);

    @SelectProvider(type = AttachedTableProvider.class, method = "selectPresentDataById")
    Map<String, Object> selectPresentDataById(String viewName, String userId);
}
