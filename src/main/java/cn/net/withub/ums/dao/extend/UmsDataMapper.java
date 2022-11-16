package cn.net.withub.ums.dao.extend;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cypress on 2017/4/20.
 */
public interface UmsDataMapper {

//    @Select("<script> \n" +
//            " SELECT     ( SELECT code_name FROM ums_code WHERE id = LEVEL AND type_id = 20   ) as \"name\", count(1) as \"value\",level as \"keyValue\" FROM    \n" +
//            "ums_user_info_view  where is_valid =1 AND user_type=1 AND level is not null and (level <= 24)  \n" +
//            "<if test=\"courtNo != null and courtNo != ''\"> and court_no = #{courtNo}</if> \n" +
//            "group by level order by level ; \n"+
//            "</script>")
    List<Map<String, Object>> lawyerLevelData(@Param("courtNo") Integer courtNo);


//    @Select(
//            "  SELECT ( SELECT code_name FROM ums_code WHERE ums_code.id = rank_report AND type_id = 1017 ) as \"name\", count(1) as \"value\",rank_report as \"keyValue\" \n" +
//            "FROM ums_user_info_view where is_valid =1 AND user_type =1 and exists ( select 1 from  ums_code WHERE ums_code.id = rank_report AND type_id = 1017  )" +
//            "<if test='courtNo != null'> and court_no = #{courtNo}</if> \n" +
//            " group by rank_report order by rank_report ; \n")
    List<Map<String, Object>> rankData(@Param("courtNo") Integer courtNo);

    @Select("<script> \n" +
            " SELECT  ( SELECT code_name FROM ums_code WHERE id = gender AND type_id = 3   )  as \"name\", count(1) as \"value\",gender as \"keyValue\"  " +
            "from ums_user_info_view where is_valid = 1 and user_type = 1 AND gender is not null \n" +
            "<if test='suffixStr != null' >" +
            "        ${suffixStr}" +
            "</if>" +
            "<if test=\"courtNo != null and courtNo != ''\"> and court_no = #{courtNo}</if> \n" +
            "group by gender order by gender ; " +
            "</script>")
    List<Map<String, Object>> genderData(@Param("suffixStr") String suffixStr,@Param("courtNo") Integer courtNo);

    @Select("<script> \n" +
            "SELECT \n" +
            "<if test=' list != null ' >  \n" +
            "<foreach item='item' index='index' collection='list'  separator=',' > \n" +
            " sum(CASE when ${item.condition} THEN 1 ELSE 0 END) as \"${item.name}\" \n" +
            "</foreach> \n" +
            "</if> \n" +
//            "sum(case when a.age BETWEEN 0 and 25  then 1 else 0 end) '25岁以下',\n" +
//            "sum(case when a.age BETWEEN 26 and 30 then 1 else 0 end) '26岁到30岁',\n" +
//            "sum(case when a.age BETWEEN 31 and 35  then 1 else 0 end) '31岁到35岁',\n" +
//            "sum(case when a.age BETWEEN 36 and 40 then 1 else 0 end) '36岁到40岁',\n" +
//            "sum(case when a.age BETWEEN 41 and 45 then 1 else 0 end) '41岁到45岁',\n" +
//            "sum(case when a.age BETWEEN 46 and 50 then 1 else 0 end) '46岁到50岁',\n" +
//            "sum(case when a.age BETWEEN 51 and 55 then 1 else 0 end) '51岁到55岁',\n" +
//            "sum(case when a.age BETWEEN 56 and 60 then 1 else 0 end) '56岁到60岁',\n" +
//            "sum(case when a.age > 60 then 1 else 0 end) '60岁以上' \n" +
            "FROM\n" +
            "(\n" +
            "SELECT floor( MONTHS_BETWEEN(now(),birthday) / 12) as age  from ums_user_info_view a\n" +
            "WHERE user_type = 1 and is_valid = 1 AND birthday is not null \n" +
            "<if test=\"courtNo != null and courtNo != ''\"> and court_no = #{courtNo}</if> \n" +
            "<if test='suffixStr != null' >" +
            "        ${suffixStr}" +
            "      </if>" +
            ") a ;"
            + "</script>")
    LinkedHashMap<String, Object> ageData(@Param("list") List<Map<String, Object>> list, @Param("suffixStr") String suffixStr,@Param("courtNo") Integer courtNo);


    @Select("<script> \n" +
            " SELECT \n" +
            "<if test=' list != null ' >  \n" +
            "<foreach item='item' index='index' collection='list'  separator=',' > \n" +
            " sum(CASE when ${item.condition} THEN 1 ELSE 0 END) as  ${item.name} \n" +
            "</foreach> \n" +
            "</if> \n" +
//            "sum(CASE when political = 1 THEN 1 ELSE 0 END) '中共党员',\n" +
//            "sum(CASE when political = 2 THEN 1 ELSE 0 END) '中共预备党员',\n" +
//            "sum(CASE when political = 3 THEN 1 ELSE 0 END) '共青团员',\n" +
//            "sum(CASE when political >= 4 and  political <= 11 THEN 1 ELSE 0 END) '民主党派',\n" +
//            "sum(CASE when political = 12 THEN 1 ELSE 0 END) '无党派民主人士',\n" +
//            "sum(CASE when political = 13 THEN 1 ELSE 0 END) '群众'\n" +
            "from ums_user_info_view where user_type = 1 and is_valid = 1 \n" +
            "<if test=\"courtNo != null and courtNo != ''\"> and court_no = #{courtNo}</if> \n" +
            "AND political is not null; "
            + "</script>")
    LinkedHashMap<String, Object> politicalData(@Param("list") List<Map<String, Object>> list,@Param("courtNo") Integer courtNo);


    @Select("<script> \n" +
            " SELECT \n" +
            "<if test=' list != null ' >  \n" +
            "<foreach item='item' index='index' collection='list'  separator=',' > \n" +
            " sum(CASE when ${item.condition} THEN 1 ELSE 0 END) as ${item.name} \n" +
            "</foreach> \n" +
            "</if> \n" +
//            "sum(CASE when education_background = 1 THEN 1 ELSE 0 END) '博士研究生',\n" +
//            "sum(CASE when education_background >=  2 and  education_background <= 9 THEN 1 ELSE 0 END) '硕士研究生',\n" +
//            "sum(CASE when education_background >= 11 and  education_background < 20 THEN 1 ELSE 0 END) '大学本科',\n" +
//            "sum(CASE when education_background >= 21 THEN 1 ELSE 0 END) '大专及以下'\n" +
            "from ums_user_info_view a where user_type = 1 and is_valid = 1 \n" +
            "AND education_background is not null " +
            "<if test=\"courtNo != null and courtNo != ''\"> and court_no = #{courtNo}</if> \n" +
            "<if test='suffixStr != null' >" +
            "        ${suffixStr}" +
            "      </if>" +
            "</script>")
    LinkedHashMap<String, Object> educationBackgroundData(@Param("list") List<Map<String, Object>> list, @Param("suffixStr") String suffixStr,@Param("courtNo") Integer courtNo);

    @Select("<script> \n" +
            " SELECT \n" +
            "<if test=' list != null ' >  \n" +
            "<foreach item='item' index='index' collection='list'  separator=',' > \n" +
            " sum(CASE when ${item.condition} THEN 1 ELSE 0 END) as ${item.name} \n" +
            "</foreach> \n" +
            "</if> \n" +
//            "sum(CASE when degree >= 200 and  degree < 300 THEN 1 ELSE 0 END) '博士',\n" +
//            "sum(CASE when degree >= 300 and  degree < 400 THEN 1 ELSE 0 END) '硕士',\n" +
//            "sum(CASE when degree >= 400 and  degree < 500 THEN 1 ELSE 0 END) '学士'\n" +
            "from ums_user_info_view where user_type = 1 and is_valid = 1 \n" +
            "<if test=\"courtNo != null and courtNo != ''\"> and court_no = #{courtNo}</if> \n" +
            "AND degree is not null; "
            + "</script>")
    LinkedHashMap<String, Object> degreeData(@Param("list") List<Map<String, Object>> list,@Param("courtNo") Integer courtNo);


    /**
     * 全市法院人员分布情况
     */
    @Select("<script> \n" +
            "SELECT SPRY AS \"审判辅助人员\",FG0 AS \"院领导法官\",FG1 as \"一线法官\",FG2 AS \"司法行政部门法官\",QT AS \"司法行政人员\" from (SELECT A.*,ALL_RY-QT-FG AS SPRY  FROM (select\n" +
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
            "<if test=\"courtNo != null and courtNo != ''\"> and court_no = #{courtNo}</if> \n" +
            "<if test='court != null' >" +
            "        ${court}" +
            "      </if>" +
            ") A) aaa" +
            "</script>")
    LinkedHashMap<String, Object> lawPositionChart(@Param("court") String court,@Param("courtNo") Integer courtNo);


    /*
    人员分类
     */
    @Select("<script> \n" +
            "SELECT\n" +
            "          sum( CASE WHEN personnel_classification IN ( select id from ums_code WHERE TYPE_ID = 94 and IS_VALID = 1 and ( id = 1 or parent_id = 1 ) ) THEN 1 ELSE 0 END )  as \"法官\",\n" +
            "\t\t\t\t\tsum( CASE WHEN personnel_classification IN ( select id from ums_code WHERE TYPE_ID = 94 and IS_VALID = 1 and ( id = 2 or parent_id = 2 ) ) THEN 1 ELSE 0 END )  as \"审判辅助人员\",\n" +
            "\t\t\t\t\tsum( CASE WHEN personnel_classification IN ( select id from ums_code WHERE TYPE_ID = 94 and IS_VALID = 1 and ( id = 3 ) ) THEN 1 ELSE 0 END )  as \"司法行政人员\"\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tums_user_info_view\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tis_valid = 1\n" +
            "\t\t\t\tAND user_type = 1" +
            "<if test=\"courtNo != null and courtNo != ''\"> and court_no = #{courtNo}</if> \n" +
            "<if test='court != null' >" +
            "        ${court}" +
            "      </if>" +
            "</script>")
    LinkedHashMap<String, Object> personnelClassificationChart(@Param("court") String court,@Param("courtNo") Integer courtNo);

    @Select("<script> \n" +
            " SELECT count(1) as yefg from ums_user_info_view WHERE user_type = 1 and is_valid = 1 and yefg = 1 " +
            "<if test=\"courtNo != null and courtNo != ''\"> and court_no = #{courtNo}</if> \n" +
            "<if test='suffixStr != null' >" +
            "        ${suffixStr}" +
            "      </if>" +
            "</script>")
    Integer yefgChart(@Param("suffixStr") String suffixStr,@Param("courtNo") Integer courtNo);


    @Select("<script> \n" +
            " SELECT count(1) as \"all\",\n" +
            "<if test=' list != null ' >  \n" +
            "<foreach item='item' index='index' collection='list'  separator=',' > \n" +
            " sum(CASE when ${item.condition} THEN 1 ELSE 0 END) ${item.name} \n" +
            "</foreach> \n" +
            "</if> \n" +
            "FROM ums_user_info_view a\n" +
            "WHERE\n" +
            "\tuser_type = 1\n" +
            "AND is_valid = 1\n" +
            "AND leave_reason IS NULL " +
            "AND court_code in ${fydm} "
            + "</script>")
    LinkedHashMap<String, Object> courtJudgeBaseInfo(@Param("list") List<Map<String, Object>> list, @Param("fydm") String fydm);

    @Select(" SELECT     level_text as \"name\", count(1) as \"value\",level as \"keyValue\" FROM    \n" +
            "ums_user_info_view a where is_valid =1 AND user_type=1 AND court_code in ${fydm} AND (yefg = 1 OR EXISTS (SELECT 1 FROM ums_level_info b WHERE a.user_id = b.user_id AND a.court_no = b.court_no AND b.is_yefg = 1 AND b.n_present_info = 1))  \n" +
            "group by level,level_text order by level; ")
    List<Map<String, Object>> courtJudgeLawyerLevelData(@Param("fydm") String fydm);

//    @Select(" SELECT\n" +
//            "\tdepartment_text AS \"name\",\n" +
//            "\tcount(1) AS \"value\",\n" +
//            "\tdepartment AS \"keyValue\"\n" +
//            "FROM\n" +
//            "\tums_user_info_view \n" +
//            "WHERE\n" +
//            "\tis_valid = 1\n" +
//            "AND user_type = 1\n" +
//            "AND department IS NOT NULL\n" +
//            "AND court_code in ${fydm} AND (yefg = 1 OR EXISTS (SELECT 1 FROM ums_level_info b WHERE ums_user_info_view.user_id = b.user_id AND ums_user_info_view.court_no = b.court_no AND b.is_yefg = 1 AND b.n_present_info = 1))\n" +
//            "GROUP BY\n" +
//            "\tdepartment\n" +
//            "ORDER BY\n" +
//            "\t(\n" +
//            "SELECT sort_no from ums_department WHERE\n" +
//            "ums_department.court_no=ums_user_info_view.court_no and ums_department.dept_no=ums_user_info_view.department\n" +
//            ");")

    @Select("SELECT t.keyValue,t.value AS \"value\",d.dept_name AS \"name\" FROM (" +
            "SELECT\n" +
            "\tcount(1) AS \"value\",\n" +
            "\tdepartment AS \"keyValue\"\n" +
            "FROM\n" +
            "\tums_user_info_view \n" +
            "WHERE\n" +
            "\tis_valid = 1\n" +
            "AND user_type = 1\n" +
            "AND department IS NOT NULL\n" +
            "AND court_code in ${fydm} AND (yefg = 1 OR EXISTS (SELECT 1 FROM ums_level_info b WHERE ums_user_info_view.user_id = b.user_id AND ums_user_info_view.court_no = b.court_no AND b.is_yefg = 1 AND b.n_present_info = 1))\n" +
            "GROUP BY\n" +
            "\tdepartment\n" +
            ") t LEFT JOIN (" +
            "select f.* FROM (\n" +
            "SELECT s.*,ROW_NUMBER() OVER (PARTITION BY dept_no ORDER BY dept_no) AS group_idx FROM (\n" +
            "select dept_no,dept_name,count(*) from ums_department group by dept_no,dept_name\n" +
            ") s\n" +
            ") f WHERE f.group_idx = 1\n" +
            ") d \n" +
            "ON t.keyValue = d.dept_no"
    )
    List<Map<String, Object>> courtJudgeDepartmentData(@Param("fydm") String fydm);
}
