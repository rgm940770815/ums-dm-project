package cn.net.withub.ums.dao.extend;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by Cypress on 2017/4/26.
 */
public interface UmsQueryDataMapper {

//    @Select("<script> \n" +
//            " SELECT fullname,gender_text as genderText,birthday,court_no_text as courtNoText,department_text as departmentText,\n" +
//            "education_background_text as educationBackgroundText,administration_position_text as administrationPositionText \n " +
//            "from ums_user_info_view where is_valid =1 AND user_type=1 AND level is not null and (level <= 24) \n" +
//            "and  level = #{keyValue} \n" +
//            "limit  #{start} , #{limit} \n "
//            + "</script>")
    List<Map<String, Object>> getLawyerLevelData(@Param("keyValue") String keyValue, @Param("start") Integer start, @Param("limit") Integer limit);

//    @Select("<script> \n" +
//            " SELECT fullname,gender_text as genderText,birthday,court_no_text as courtNoText,department_text as departmentText,\n" +
//            "education_background_text as educationBackgroundText,administration_position_text as administrationPositionText \n " +
//            "from ums_user_info_view where is_valid =1 AND user_type =1 AND rank is not null \n" +
//            "and  rank = #{keyValue} \n" +
//            "limit  #{start} , #{limit} \n "
//            + "</script>")
    List<Map<String, Object>> getRankData(@Param("keyValue") String keyValue, @Param("start") Integer start, @Param("limit") Integer limit);

//    @Select("<script> \n" +
//            " SELECT fullname,gender_text as genderText,birthday,court_no_text as courtNoText,department_text as departmentText,\n" +
//            "education_background_text as educationBackgroundText,administration_position_text as administrationPositionText \n " +
//            "from ums_user_info_view where is_valid = 1 and user_type = 1 AND gender is not null \n" +
//            "and  gender = #{keyValue} \n" +
//            "limit  #{start} , #{limit} \n "
//            + "</script>")
    List<Map<String, Object>> getGenderData(@Param("keyValue") String keyValue, @Param("start") Integer start, @Param("limit") Integer limit);

//    @Select("<script> \n" +
//            " SELECT a.* \n" +
//            "from \n" +
//            "( SELECT fullname,gender_text as genderText,birthday,court_no_text as courtNoText,department_text as departmentText,\n" +
//            "education_background_text as educationBackgroundText,administration_position_text as administrationPositionText," +
//            " DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(birthday)), '%Y')+0 AS age  \n " +
//            "from ums_user_info_view where is_valid = 1 and user_type = 1 AND birthday is not null ) a \n" +
//            "where ${condition} \n" +
//            "limit  #{start} , #{limit} \n "
//            + "</script>")
    List<Map<String, Object>> getAgeData(@Param("condition") String condition, @Param("start") Integer start, @Param("limit") Integer limit);


//    @Select("<script> \n" +
//            " SELECT fullname,gender_text as genderText,birthday,court_no_text as courtNoText,department_text as departmentText,\n" +
//            "education_background_text as educationBackgroundText,administration_position_text as administrationPositionText \n " +
//            "from ums_user_info_view where is_valid = 1 and user_type = 1 AND political is not null \n" +
//            "and ${condition} \n" +
//            "limit  #{start} , #{limit} \n "
//            + "</script>")
    List<Map<String, Object>> getPoliticalData(@Param("condition") String condition, @Param("start") Integer start, @Param("limit") Integer limit);

//    @Select("<script> \n" +
//            " SELECT fullname,gender_text as genderText,birthday,court_no_text as courtNoText,department_text as departmentText,\n" +
//            "education_background_text as educationBackgroundText,administration_position_text as administrationPositionText \n " +
//            "from ums_user_info_view where is_valid = 1 and user_type = 1 AND education_background is not null \n" +
//            "and ${condition} \n" +
//            "limit  #{start} , #{limit} \n "
//            + "</script>")
    List<Map<String, Object>> getEducationBackgroundData(@Param("condition") String condition, @Param("start") Integer start, @Param("limit") Integer limit);

//    @Select("<script> \n" +
//            " SELECT fullname,gender_text as genderText,birthday,court_no_text as courtNoText,department_text as departmentText,\n" +
//            "education_background_text as educationBackgroundText,administration_position_text as administrationPositionText \n " +
//            "from ums_user_info_view where is_valid = 1 and user_type = 1 AND degree is not null \n" +
//            "and ${condition} \n" +
//            "limit  #{start} , #{limit} \n "
//            + "</script>")
    List<Map<String, Object>> getDegreeData(@Param("condition") String condition, @Param("start") Integer start, @Param("limit") Integer limit);


//    @Select("<script> \n" +
//            "select count(1) \n" +
//            "from ums_user_info_view where is_valid = 1 and user_type = 1 AND level is not null and level <= 24 \n" +
//            "and level = ${keyValue} \n" +
//            "limit  #{start} , #{limit} \n "
//            + "</script>")
    int countLawyerLevelData(@Param("keyValue") String keyValue, @Param("start") Integer start, @Param("limit") Integer limit);


//    @Select("<script> \n" +
//            " SELECT count(1) \n" +
//            "from ums_user_info_view where is_valid =1 AND user_type =1 AND rank is not null \n" +
//            "and  rank = #{keyValue} \n" +
//            "limit  #{start} , #{limit} \n "
//            + "</script>")
    int countRankData(@Param("keyValue") String keyValue, @Param("start") Integer start, @Param("limit") Integer limit);


//    @Select("<script> \n" +
//            " SELECT count(1) \n" +
//            "from ums_user_info_view where is_valid = 1 and user_type = 1 AND gender is not null \n" +
//            "and  gender = #{keyValue} \n" +
//            "limit  #{start} , #{limit} \n "
//            + "</script>")
    int countGenderData(@Param("keyValue") String keyValue, @Param("start") Integer start, @Param("limit") Integer limit);


//    @Select("<script> \n" +
//            " SELECT count(1) \n" +
//            "from \n" +
//            "( SELECT \n" +
//            " DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(birthday)), '%Y')+0 AS age  \n " +
//            "from ums_user_info_view where is_valid = 1 and user_type = 1 AND birthday is not null ) a \n" +
//            "where ${condition} \n" +
//            "limit  #{start} , #{limit} \n "
//            + "</script>")
    int countAgeData(@Param("condition") String condition, @Param("start") Integer start, @Param("limit") Integer limit);


//    @Select("<script> \n" +
//            " SELECT count(1) \n" +
//            "from ums_user_info_view where is_valid = 1 and user_type = 1 AND political is not null \n" +
//            "and ${condition} \n" +
//            "limit  #{start} , #{limit} \n "
//            + "</script>")
    int countPoliticalData(@Param("condition") String condition, @Param("start") Integer start, @Param("limit") Integer limit);

//    @Select("<script> \n" +
//            " SELECT count(1) \n" +
//            "from ums_user_info_view where is_valid = 1 and user_type = 1 AND education_background is not null \n" +
//            "and ${condition} \n" +
//            "limit  #{start} , #{limit} \n "
//            + "</script>")
    int countEducationBackgroundData(@Param("condition") String condition, @Param("start") Integer start, @Param("limit") Integer limit);


//    @Select("<script> \n" +
//            " SELECT count(1) \n" +
//            "from ums_user_info_view where is_valid = 1 and user_type = 1 AND degree is not null \n" +
//            "and ${condition} \n" +
//            "limit  #{start} , #{limit} \n "
//            + "</script>")
    int countDegreeData(@Param("condition") String condition, @Param("start") Integer start, @Param("limit") Integer limit);

    int countLawPositionData(@Param("condition") String condition,@Param("court") String court);

    List<Map<String, Object>> getLawPositionData(@Param("condition") String condition, @Param("start") Integer start, @Param("limit") Integer limit,@Param("court") String court);


    List<Map<String, Object>> getCourtLawyerLevelData(@Param("condition") String condition, @Param("keyValue") String keyValue, @Param("start") Integer start, @Param("limit") Integer limit);

    int countCourtLevelData(@Param("condition") String condition, @Param("keyValue") String keyValue);

    List<Map<String, Object>> getCourtJudgeDepartmentData(@Param("condition") String condition, @Param("keyValue") String keyValue, @Param("start") Integer start, @Param("limit") Integer limit);

    int countCountCourtLevelData(@Param("condition") String condition, @Param("keyValue") String keyValue);


    int countCourtAgeData(@Param("query") String query,@Param("condition") String condition);

    List<Map<String, Object>> getCourtAgeData(@Param("query") String query,@Param("condition") String condition, @Param("start") Integer start, @Param("limit") Integer limit);

    List<Map<String, Object>> getCourtEducationBackgroundData(@Param("condition") String condition, @Param("start") Integer start, @Param("limit") Integer limit);

    int countCourtJudgeBaseInfo(@Param("condition") String condition);

    List<Map<String, Object>> getCourtJudgeBaseInfo(@Param("condition") String condition, @Param("start") Integer start, @Param("limit") Integer limit);
}
