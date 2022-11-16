/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.dao.attach;

import cn.net.withub.ums.entity.UmsAdministrativeJobView;
import cn.net.withub.ums.entity.UmsLegalJobView;
import cn.net.withub.ums.entity.UmsParttimePositionView;
import cn.net.withub.ums.entity.UmsPoliticalInfoView;
import cn.net.withub.ums.entity.UmsRankInfoView;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

/**
 * 基本表附属表视图共用Mapper
 *
 * @author Diluka
 */
public interface UmsUserInfoAttachedViewsMapper {

    /**
     * 政治面貌
     *
     * @param userId
     * @param rowBounds
     * @return
     */
    @Select("select * from ums_political_info_view where user_id=#{userId} order by sort_no")
    @ResultMap("cn.net.withub.ums.dao.UmsPoliticalInfoViewMapper.BaseResultMap")
    List<UmsPoliticalInfoView> politicalInfo(String userId, RowBounds rowBounds);

    @Select("select count(0) from ums_political_info_view where user_id=#{userId}")
    int politicalInfoCount(String userId);

    /**
     * 行政职务
     *
     * @param userId
     * @param rowBounds
     * @return
     */
    @Select("select * from ums_administrative_job_view where user_id=#{userId} order by sort_no")
    @ResultMap("cn.net.withub.ums.dao.UmsAdministrativeJobViewMapper.BaseResultMap")
    List<UmsAdministrativeJobView> administrativeJob(String userId, RowBounds rowBounds);

    @Select("select count(0) from ums_administrative_job_view where user_id=#{userId}")
    int administrativeJobCount(String userId);

    /**
     * 法律职务
     *
     * @param userId
     * @param rowBounds
     * @return
     */
    @Select("select * from ums_legal_job_view where user_id=#{userId} order by sort_no")
    @ResultMap("cn.net.withub.ums.dao.UmsLegalJobViewMapper.BaseResultMap")
    List<UmsLegalJobView> legalJob(String userId, RowBounds rowBounds);

    @Select("select count(0) from ums_legal_job_view where user_id=#{userId}")
    int legalJobCount(String userId);

    /**
     * 职级信息
     *
     * @param userId
     * @param rowBounds
     * @return
     */
    @Select("select * from ums_rank_info_view where user_id=#{userId} order by sort_no")
    @ResultMap("cn.net.withub.ums.dao.UmsRankInfoViewMapper.BaseResultMap")
    List<UmsRankInfoView> rankInfo(String userId, RowBounds rowBounds);

    @Select("select count(0) from ums_rank_info_view where user_id=#{userId}")
    int rankInfoCount(String userId);

    /**
     * 兼任职务
     *
     * @param userId
     * @param rowBounds
     * @return
     */
    @Select("select * from ums_parttime_position_view where user_id=#{userId} order by sort_no")
    @ResultMap("cn.net.withub.ums.dao.UmsParttimePositionViewMapper.BaseResultMap")
    List<UmsParttimePositionView> parttimePosition(String userId, RowBounds rowBounds);

    @Select("select count(0) from ums_parttime_position_view where user_id=#{userId}")
    int parttimePositionCount(String userId);

    @Select("select MAX(upload_time) from ums_upload_record_info")
    Date selectLastUpdateTime();

    @Select( " SELECT   ( SELECT   court_std_name  from  ums_court_full   where court_no = a.court_no   ) as court_name,dept_name,dept_st_name,\n" +
            "( case when org_type = 1 then '业务部门'  when org_type =  2  then '综合部门'  when  org_type = 8 then '派出法庭'  else '其他部门' end ) as type\n" +
            "  from ums_department  a where is_valid = 1  ORDER BY  court_no ,level,sort_no")
    List<Map<String,Object>> getDept();

}
