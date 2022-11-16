package cn.net.withub.ums.action.config.verify;

import cn.net.withub.ums.action.config.VerifyConfigAction;
import cn.net.withub.ums.entity.UmsVerifyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class VerifyRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AuthUtils authUtils;

    @Autowired
    VerifyConfigAction verifyConfigAction;

    private static final Map<String, String> countMap = new HashMap<>();
    private static final Map<String, String> queryMap = new HashMap<>();

    //退休提醒时间
    private static final Integer retireMonth = 1;

    static {
        //放在查询sql
        countMap.put("zzzx", "select count(1) from ums_user_info_view a where is_valid = 1 and user_type = 1 ");
        queryMap.put("zzzx", "select * from ums_user_info_view a where is_valid = 1 and user_type = 1 ");
        countMap.put("yefg", "select count(1) from ums_user_info_view a where is_valid = 1 and user_type = 1 and (a.yefg = 1 OR EXISTS (SELECT 1 FROM ums_level_info WHERE a.id = ums_level_info.user_id AND is_yefg = 1 AND n_present_info = 1)) ");
        queryMap.put("yefg", "select * from ums_user_info_view a where is_valid = 1 and user_type = 1 and (a.yefg = 1 OR EXISTS (SELECT 1 FROM ums_level_info WHERE a.id = ums_level_info.user_id AND is_yefg = 1 AND n_present_info = 1)) ");

        //晋升
        countMap.put("promote", "SELECT count(DISTINCT a.id) from ums_user_info_view a\n" +
                "LEFT JOIN ums_level_info b on a.id = b.user_id\n" +
                "where a.user_type =1 and a.is_valid =1 and b.n_present_info = 1  and n_level_type =1  \n" +
                "AND (a.yefg = 1 OR EXISTS (SELECT 1 FROM ums_level_info WHERE a.id = ums_level_info.user_id AND is_yefg = 1 AND n_present_info = 1)) " +
                "and EXISTS (\n" +
                "SELECT 1 from ums_code c where c.type_id = 117 and b.judge_level = c.id\n" +
                ")");
        //晋升
        queryMap.put("promote", "SELECT distinct a.* from ums_user_info_view a\n" +
                "LEFT JOIN ums_level_info b on a.id = b.user_id\n" +
                "where a.user_type =1 and a.is_valid =1 and b.n_present_info = 1  and n_level_type =1  \n" +
                "AND (a.yefg = 1 OR EXISTS (SELECT 1 FROM ums_level_info WHERE a.id = ums_level_info.user_id AND is_yefg = 1 AND n_present_info = 1)) " +
                "and EXISTS (\n" +
                "SELECT 1 from ums_code c where c.type_id = 117 and b.judge_level = c.id\n" +
                ")");

    }


    //基本统计
    public int count(Integer courtNo, String type) {


        String countStr = countMap.get(type);
        if (!StringUtils.hasText(countStr)) {
            return 0;
        }


        StringBuilder sql = new StringBuilder(countStr);
        try {
            authUtils.appendCourt(sql, courtNo);
        } catch (Exception e) {
            return 0;
        }

        return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
    }

    //基本查询
    public List<Map<String, Object>> queryForPage(Integer courtNo, String type, PageUtil pageUtil) {

        String queryStr = queryMap.get(type);
        if (!StringUtils.hasText(queryStr)) {
            return null;
        }

        StringBuilder sql = new StringBuilder(queryStr);
        try {
            authUtils.appendCourt(sql, courtNo);
        } catch (Exception e) {
            return null;
        }
        sql.append(" limit ").append(pageUtil.getStart()).append(",").append(pageUtil.getLimit());

        return jdbcTemplate.queryForList(sql.toString());
    }

    //直接进行分组查询职级
    public List<Map<String, Object>> countForLevelByGroup(Integer courtNo) {

        StringBuilder sql = new StringBuilder("SELECT count(DISTINCT a.id) as count ,b.judge_level as level from ums_user_info_view a\n" +
                "        LEFT JOIN ums_level_info b on a.id = b.user_id\n" +
                "        where a.user_type =1 and a.is_valid =1 and b.n_present_info = 1  and n_level_type =1\n" +
                "        and EXISTS (SELECT 1 from ums_code c where c.type_id = 117 and b.judge_level = c.id) ");
        try {
            authUtils.appendCourt(sql, courtNo);
        } catch (Exception e) {
            return null;
        }

        sql.append(" GROUP BY b.judge_level ");

        return jdbcTemplate.queryForList(sql.toString());
    }

    //根据职级进行统计
    public int countForLevel(Integer courtNo, String level) {
        if (level == null) {
            return 0;
        }
        StringBuilder sql = new StringBuilder("SELECT count(1) from ums_user_info_view a \n" +
                "WHERE a.is_valid = 1 and a.user_type = 1 ");

        //为了防止数据对不上和重复查询sql 把appensql提出来
        try {
            authUtils.appendCourt(sql, courtNo);
        } catch (Exception e) {
            return 0;
        }


        sql.append(" and EXISTS (SELECT 1 from ums_level_info b where  a.id = b.user_id and b.n_present_info = 1 " + " and b.n_level_type = 1 and  b.judge_level = ").append(level).append(" )");
        return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
    }

    //根据职级进行查询
    public List<Map<String, Object>> queryForLevel(Integer courtNo, String level, PageUtil pageUtil) {
        if (level == null) {
            return null;
        }
        StringBuilder sql = new StringBuilder("SELECT * from ums_user_info_view a \n" +
                "WHERE a.is_valid = 1 and a.user_type = 1 ");
        try {
            authUtils.appendCourt(sql, courtNo);
        } catch (Exception e) {
            return null;
        }

        sql.append(" and EXISTS (SELECT 1 from ums_level_info b where  a.id = b.user_id and b.n_present_info = 1 " + " and b.n_level_type = 1 and  b.judge_level = ").append(level).append(" )");

        sql.append(" limit ").append(pageUtil.getStart()).append(",").append(pageUtil.getLimit());

        return jdbcTemplate.queryForList(sql.toString());
    }

    //根据配置法官的晋升 当前法官等级如果系统配置中有配置几年晋升时间，通过法官等级时间进行判断满足要求进行提示 “X级大法官满足晋升人数：33”
    public int countForPromote(Integer courtNo, String param, Integer year) {

        String countStr = countMap.get("promote");
        if (!StringUtils.hasText(countStr) || !StringUtils.hasText(param) || year == null) {
            return 0;
        }

        StringBuilder sql = new StringBuilder(countStr);
        try {
            authUtils.appendCourt(sql, courtNo);
        } catch (Exception e) {
            return 0;
        }
        //在加上法官等级
        sql.append(" and  b.judge_level = ").append(param);
        //在加上要求的晋升年限
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.minusYears(year);
        sql.append(" and d_start_date < ").append(String.format("'%s'", localDate.toString()));

        return jdbcTemplate.queryForObject(sql.toString(), Integer.class);

    }

    //法官的晋升反查
    public List<Map<String, Object>> queryForPromote(Integer courtNo, String param, Integer year, PageUtil pageUtil) {

        String countStr = queryMap.get("promote");
        if (!StringUtils.hasText(countStr) || !StringUtils.hasText(param) || year == null) {
            return null;
        }

        StringBuilder sql = new StringBuilder(countStr);
        try {
            authUtils.appendCourt(sql, courtNo);
        } catch (Exception e) {
            return null;
        }
        //在加上法官等级
        sql.append(" and  b.judge_level = ").append(param);
        //在加上要求的晋升年限
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.minusYears(year);
        sql.append(" and d_start_date < ").append(String.format("'%s'", localDate.toString()));

        sql.append(" limit ").append(pageUtil.getStart()).append(",").append(pageUtil.getLimit());

        return jdbcTemplate.queryForList(sql.toString());

    }

    ////退休年龄距离还要1个月的人员查询
    public int countForRetire(Integer courtNo) {
        //可以使用政治专项的统计

        String countStr = countMap.get("zzzx");
        if (!StringUtils.hasText(countStr)) {
            return 0;
        }

        StringBuilder sql = new StringBuilder(countStr);
        try {
            authUtils.appendCourt(sql, courtNo);
        } catch (Exception e) {
            return 0;
        }
        //添加自己的限制
        sql.append(" and ( ");

        verifyConfigAction.setScope("retire");
        verifyConfigAction.getConfig();
        ArrayList<UmsVerifyConfig> promoteConfig = (ArrayList<UmsVerifyConfig>) verifyConfigAction.getData();
        //避免sql凭接错误 判断是不是空
        boolean isEmptyQuery = true;
        //男女晋升时间不一样

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (UmsVerifyConfig conf : promoteConfig) {
            String ckey = conf.getcKey();
            Short cValue = conf.getcValue();

            if (StringUtils.hasText(ckey) && cValue != null) {

                if (!isEmptyQuery) {
                    sql.append(" or ");
                }

                sql.append("(");
                sql.append(" gender = ").append(ckey);
                LocalDate localDate = LocalDate.now();
                LocalDate retireTime = localDate.plusMonths(retireMonth);
                sql.append(" and  DATE_ADD(birthday,INTERVAL ").append(cValue)
                        .append(" YEAR)  BETWEEN ")
                        .append(String.format("'%s'", localDate.format(formatters)))
                        .append(" and ")
                        .append(String.format("'%s'", retireTime.format(formatters)));
                sql.append(")");
                isEmptyQuery = false;
            }

        }
        //空查询直接返回
        if (isEmptyQuery) {
            return 0;
        }
        sql.append(" ) ");


        return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
    }

    ////退休年龄距离还要1个月的人员查询
    public List<Map<String, Object>> queryForRetire(Integer courtNo, PageUtil pageUtil) {
        //可以使用政治专项的统计

        String countStr = queryMap.get("zzzx");
        if (!StringUtils.hasText(countStr)) {
            return null;
        }

        StringBuilder sql = new StringBuilder(countStr);
        try {
            authUtils.appendCourt(sql, courtNo);
        } catch (Exception e) {
            return null;
        }

        //添加自己的限制
        sql.append(" and ( ");

        verifyConfigAction.setScope("retire");
        verifyConfigAction.getConfig();
        ArrayList<UmsVerifyConfig> promoteConfig = (ArrayList<UmsVerifyConfig>) verifyConfigAction.getData();

        //避免sql凭接错误 判断是不是空
        boolean isEmptyQuery = true;
        //男女晋升时间不一样
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (UmsVerifyConfig conf : promoteConfig) {
            String ckey = conf.getcKey();
            Short cValue = conf.getcValue();

            if (StringUtils.hasText(ckey) && cValue != null) {

                if (!isEmptyQuery) {
                    sql.append(" or ");
                }
//
//                sql.append("(");
//                sql.append(" gender = ").append(ckey);
//                LocalDate localDate = LocalDate.now();
//                LocalDate retireTime = localDate.plusMonths(retireMonth);
//                sql.append(" and  DATE_ADD(birthday,INTERVAL ").append(cValue)
//                        .append(" YEAR)  BETWEEN ")
//                        .append(String.format("'%s'", localDate.format(formatters)))
//                        .append(" and ")
//                        .append(String.format("'%s'", retireTime.format(formatters)));
//                sql.append(")");

                sql.append("(");
                sql.append(" gender = ").append(ckey);
                LocalDate localDate = LocalDate.now();
                //between 开始
                LocalDate startYear = localDate.minusYears(cValue);
                LocalDate endYear = localDate.plusMonths(retireMonth).minusYears(cValue);
                sql.append(" and  birthday  BETWEEN")
                        .append(String.format("'%s'", startYear.format(formatters)))
                        .append(" and ")
                        .append(String.format("'%s'", endYear.format(formatters)));
                sql.append(")");
                isEmptyQuery = false;
            }

        }
        //空查询直接返回
        if (isEmptyQuery) {
            return null;
        }
        sql.append(" ) ");

        sql.append(" limit ").append(pageUtil.getStart()).append(",").append(pageUtil.getLimit());

        return jdbcTemplate.queryForList(sql.toString());
    }


}
