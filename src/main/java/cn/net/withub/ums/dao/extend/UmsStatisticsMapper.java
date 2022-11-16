/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.dao.extend;

import cn.net.withub.ums.entity.UmsCourtFull;
import cn.net.withub.ums.entity.UmsUserInfoView;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author Diluka
 */
public interface UmsStatisticsMapper {

    /**
     * 职务类型
     *
     * @param courtNoList
     * @return
     */
    @Select("<script>"
            + "select \n"
            + "    IFNULL(position_type_text, '无') as 'name',\n"
            + "    count(0) as 'y'\n"
            + "from\n"
            + "    ums_user_info_view\n"
            + "<if test='list != null'>"
            + "where is_valid =1 AND user_type=1 and court_no in\n"
            + "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</if>"
            + "group by position_type\n"
            + "order by position_type"
            + "</script>")
    List<Map<String, Object>> positionStat(@Param("list") List<Integer> courtNoList);

    /**
     * 法院人员分布
     *
     * @param courtNoList
     * @return
     */
    @Select("<script>"
            + "select \n"
            + "    c.court_short_name as 'name', count(0) as 'y'\n"
            + "from\n"
            + "    ums_user_info u\n"
            + "        join\n"
            + "    ums_court_full c ON u.court_no = c.court_no\n"
            + "<if test='list != null'>"
            + "where u.is_valid =1 AND u.user_type=1 and u.court_no in\n"
            + "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</if>"
            + "group by u.court_no\n"
            + "order by c.sort_no"
            + "</script>")
    List<Map<String, Object>> courtUserStat(@Param("list") List<Integer> courtNoList);

    /**
     * 人员性别比例
     *
     * @param courtNoList
     * @return
     */
    @Select("<script>"
            + "select \n"
            + "    gender_text as 'name', count(0) as 'y'\n"
            + "from\n"
            + "    ums_user_info_view\n"
            + "where \n"
            + "    gender is not null\n"
            + "<if test='list != null'>"
            + "and court_no in\n"
            + "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</if>"
            + "group by gender\n"
            + "order by gender"
            + "</script>")
    List<Map<String, Object>> userGenderStat(@Param("list") List<Integer> courtNoList);

    /**
     * 法律职务
     *
     * @param courtNoList
     * @return
     */
    @Select("<script>"
            + "SELECT \n"
            + "    law_position_text as 'name', count(0)+1 as 'y'\n"
            + "FROM\n"
            + "    ums_user_info_view\n"
            + "where is_valid =1 AND user_type=1 and \n"
            + "    law_position is not null\n"
            + "<if test='list != null'>"
            + "and court_no in\n"
            + "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</if>"
            + "group by law_position\n"
            + "order by law_position"
            + "</script>")
    List<Map<String, Object>> legalJobStat(@Param("list") List<Integer> courtNoList);

    /**
     * 法官等级
     *
     * @param courtNoList
     * @return
     */
    @Select("<script>"
            + "SELECT \n"
            + "    level_text as 'name', count(0) as 'y'\n"
            + "FROM\n"
            + "    ums_user_info_view\n"
            + "where is_valid =1 AND user_type=1 and (level &lt;= 24) \n" //小于号坑出翔
            + "<if test='list != null'>"
            + "and court_no in\n"
            + "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</if>"
            + "group by level\n"
            + "order by level"
            + "</script>")
    List<Map<String, Object>> lawyerLevelStat(@Param("list") List<Integer> courtNoList);

    /**
     * 行政职务
     *
     * @param courtNoList
     * @return
     */
    @Select("<script>"
            + "SELECT \n"
            + "    administration_position_text as 'name', count(0) as 'y'\n"
            + "FROM\n"
            + "    ums_user_info_view\n"
            + "where is_valid =1 AND user_type=1 and \n"
            + "    administration_position is not null\n"
            + "        and administration_position_text not like '无%'\n"
            + "<if test='list != null'>"
            + "and court_no in\n"
            + "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</if>"
            + "group by administration_position \n"
            + "union SELECT \n"
            + "    '无' as 'name', count(0) as 'y'\n"
            + "FROM\n"
            + "    ums_user_info_view\n"
            + "where is_valid =1 AND user_type=1 and \n"
            + "    administration_position_text like '无%'\n"
            + "<if test='list != null'>"
            + "and court_no in\n"
            + "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</if>"
            + "</script>")
    List<Map<String, Object>> adminJobStat(@Param("list") List<Integer> courtNoList);

    /**
     * 人员最多法院
     *
     * @param courtNoList
     * @return
     */
    @Select("<script>"
            + "select \n"
            + "    *\n"
            + "from\n"
            + "    ums_court_full\n"
            + "where\n"
            + "    court_no in (select \n"
            + "            court_no\n"
            + "        from\n"
            + "            (select \n"
            + "                court_no, max(y)\n"
            + "            from\n"
            + "                (SELECT \n"
            + "                court_no, count(0) as 'y'\n"
            + "            FROM\n"
            + "                ums.ums_user_info\n"
            + "<if test='list != null'>"
            + "where  is_valid =1 AND user_type=1 and court_no in\n"
            + "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</if>"
            + "            group by court_no) t) t1)"
            + "</script>")
    UmsCourtFull mostUserCourt(@Param("list") List<Integer> courtNoList);

    /**
     * 人员最少法院
     *
     * @param courtNoList
     * @return
     */
    @Select("<script>"
            + "select \n"
            + "    *\n"
            + "from\n"
            + "    ums_court_full\n"
            + "where\n"
            + "    court_no in (select \n"
            + "            court_no\n"
            + "        from\n"
            + "            (select \n"
            + "                court_no, min(y)\n"
            + "            from\n"
            + "                (SELECT \n"
            + "                court_no, count(0) as 'y'\n"
            + "            FROM\n"
            + "                ums.ums_user_info\n"
            + "<if test='list != null'>"
            + "where  is_valid =1 AND user_type=1 and court_no in\n"
            + "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</if>"
            + "            group by court_no) t) t1)"
            + "</script>")
    UmsCourtFull leastUserCourt(@Param("list") List<Integer> courtNoList);

    /**
     * 最近职务变动人员
     *
     * @param size
     * @return
     */
    @Select("<script>"
            + "select \n"
            + "    *\n"
            + "from\n"
            + "    (select \n"
            + "        *\n"
            + "    from\n"
            + "        (select \n"
            + "        user_id, d_assign_date\n"
            + "    from\n"
            + "        ums_legal_job\n"
            + "    where\n"
            + "        d_assign_date is not null\n"
            //            + "<if test='list != null'>"
            //            + "and court_no in\n"
            //            + "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>"
            //            + "#{item}"
            //            + "</foreach>"
            //            + "</if>"
            + "    group by user_id\n"
            + "    order by d_assign_date desc\n"
            + "    limit 0 , #{size}) t2 union select \n"
            + "        *\n"
            + "    from\n"
            + "        (select \n"
            + "        user_id, d_assign_date\n"
            + "    from\n"
            + "        ums_administrative_job\n"
            + "    where\n"
            + "        d_assign_date is not null\n"
            //            + "<if test='list != null'>"
            //            + "and court_no in\n"
            //            + "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>"
            //            + "#{item}"
            //            + "</foreach>"
            //            + "</if>"
            + "    group by user_id\n"
            + "    order by d_assign_date desc\n"
            + "    limit 0 , #{size}) t1\n"
            + "    group by user_id\n"
            + "    order by d_assign_date desc\n"
            + "    limit 0 , #{size}) t0\n"
            + "        join\n"
            + "    ums_user_info_view u ON t0.user_id = u.id\n"
            + "group by user_id\n"
            + "order by d_assign_date desc"
            + "</script>")
    @ResultMap("cn.net.withub.ums.dao.UmsUserInfoViewMapper.BaseResultMap")
    List<UmsUserInfoView> recentlyPositionAlteredPersonnelList(int size);

    /**
     * 全市法院人员分布情况
     */
    @Select("<script>" +
            "SELECT SPRY AS '审判辅助人员',FG0 AS '院领导法官',FG1 as '一线法官',FG2 AS '司法行政部门法官',QT AS '司法行政人员' from (SELECT A.*,ALL_RY-QT-FG AS SPRY  FROM (select\n" +
            "  sum(case when law_position_text  IN ('院长','代院长','副院长','审判委员会委员','庭长','副庭长','审判员','助理审判员')\n" +
            "    then 1 else 0 end ) FG,\n" +
            "  sum(case when law_position_text  IN ('院长','代院长','副院长','审判委员会委员','庭长','副庭长','审判员','助理审判员')\n" +
            "                AND department  IN (1)  then 1 else 0 end ) FG0,\n" +
            "  sum(case when law_position_text  IN ('院长','代院长','副院长','审判委员会委员','庭长','副庭长','审判员','助理审判员')\n" +
            "                AND ((department NOT IN (51,52,53,54,56,58,63,67)) AND (department between 2 and 48\n" +
            "                                                                                     OR department  IN (50,51,52,53,54,56,58,63,67,74,75,76,77,78,79,80) OR department  BETWEEN 88 AND 123 )) then 1 else 0 end ) FG1,\n" +
            "  sum(case when law_position_text  IN ('院长','代院长','副院长','审判委员会委员','庭长','副庭长','审判员','助理审判员')\n" +
            "                AND ((department  IN (51,52,53,54,56,58,63,67)) or (department > 48\n" +
            "                                                                                 AND department NOT IN (50,51,52,53,54,56,58,63,67,74,75,76,77,78,79,80)\n" +
            "                                                                                 AND department NOT BETWEEN 88 AND 123 )) then 1 else 0 end ) FG2,\n" +
            "  sum(case when ((law_position_text = '书记员' AND department NOT IN (1))\n" +
            "                 OR (law_position_text NOT IN ('院长','代院长','副院长','审判委员会委员','庭长','副庭长','审判员','助理审判员','执行员','法官助理','书记员','法警','法医')))\n" +
            "                AND ((department  IN (1,51,52,53,54,56,58,63,67)) or (department > 48\n" +
            "                                                                                   AND department NOT IN (50,51,52,53,54,56,57,58,63,67,74,75,76,77,78,79,80)\n" +
            "                                                                                   AND department NOT BETWEEN 88 AND 123 )) then 1 else 0 end ) QT,\n" +
            "  sum(1) ALL_RY\n" +
            "from ums_user_info_view where is_valid =1 AND user_type=1 " +
            "<if test='court != null' >" +
            "        ${court}" +
            "      </if>" +
            ") A) aaa" +
            "</script>")
    Map<String, Object> lawPositionChart(@Param("court") String court);
}
