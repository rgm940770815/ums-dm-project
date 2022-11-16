package cn.net.withub.ums.action.statistics;


import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.UmsCountDataConfigMapper;
import cn.net.withub.ums.dao.extend.UmsCourtExtendMapper;
import cn.net.withub.ums.entity.UmsCountDataConfig;
import cn.net.withub.ums.entity.UmsCountDataConfigExample;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.util.IpTools;
import cn.net.withub.ums.util.PoiExcelUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.collections.MapUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.*;

@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/countStatistics")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"}),
        @Result(name = "stream", type = "stream",
                params = {"inputName", "inputStream", "contentDisposition", "attachment;filename=${filename}",})
})
public class CountStatisticsAction {

    @Autowired
    UmsCountDataConfigMapper umsCountDataConfigMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //====================== 反查需要的参数=======================
    public String fydm;
    public String configId;
    public String keyValue;
    public Integer page;
    public Integer limit;

    public String getFydm() {
        return fydm;
    }

    public void setFydm(String fydm) {
        this.fydm = fydm;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    //========================== excel 下载  ===========================================
    private ByteArrayInputStream inputStream;
    private String filename;

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFilename() throws UnsupportedEncodingException {
        return URLEncoder.encode(filename, "UTF-8");
    }

    public void setFilename(String filename) throws UnsupportedEncodingException {
        this.filename = new String(filename.getBytes("utf-8"), "utf-8");
    }

    /**
     * 根据配置项进行统计
     *
     * @return
     */
    @Action("getStatistics")
    public String getStatistics() {
        try {
            Map<String, Object> res = new HashMap<>();
            //当前用户的法院代码
            ActionContext ctx = ActionContext.getContext();
            HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
            UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

            String fydm = user_.getCourtCode();
            String appendSql = " and court_code = '" + fydm + "' ";
            List<Map<String, Object>> rData = new ArrayList<>();
            UmsCountDataConfigExample example = new UmsCountDataConfigExample();
            example.setOrderByClause( " id " );
            List<UmsCountDataConfig> umsCountDataConfigs = umsCountDataConfigMapper.selectByExample(example);
            Gson gson = new Gson();
            //基础sql
            String baseConditionSql = " from  ums_user_info_view a WHERE is_valid = 1 and user_type =1 and leave_reason is null ";
            String baseCountSql = " select count(1)  " + baseConditionSql;
            String singleSql = baseCountSql + appendSql;
            if (umsCountDataConfigs != null && umsCountDataConfigs.size() > 0) {

                // 先求总数
                Integer allCount = jdbcTemplate.queryForObject(singleSql, Integer.class);
                BigDecimal allCountB = BigDecimal.valueOf(allCount);
                // 一个 UmsCountDataConfig 就是一个类型
                for (UmsCountDataConfig umsCountDataConfig : umsCountDataConfigs) {
                    Integer checkType = umsCountDataConfig.getCheckType();
                    String checkName = umsCountDataConfig.getCheckName();
                    String queryCondition = umsCountDataConfig.getQueryCondition();

                    Map<String, Object> nx = new HashMap<>();
                    nx.put("checkName", checkName);
                    nx.put("checkId", umsCountDataConfig.getId());
                    List<Map<String, Object>> dax = new ArrayList<>();
                    if (checkType == 1) {
                        //单项匹配 拼接sql  1个项就是1个指标
                        List<Map<String, Object>> conditionList = gson.fromJson(queryCondition, new TypeToken<List<Map<String, Object>>>() {
                        }.getType());
                        for (Map<String, Object> map : conditionList) {
                            Map<String, Object> inerx = new HashMap<>();
                            String key = MapUtils.getString(map, "key", "").replace(".0", "");
                            String kName = MapUtils.getString(map, "name");
                            String cSql = MapUtils.getString(map, "sql");
                            //单项总数
                            String countSx = singleSql + cSql;
                            Integer bCount = jdbcTemplate.queryForObject(countSx, Integer.class);

                            inerx.put("key", key);
                            //编码名
                            inerx.put("name", kName);
                            inerx.put("value", bCount);
                            inerx.put("percent", getDoublePercent(bCount, allCountB));
                            dax.add(inerx);
                        }


                    } else if (checkType == 2) {
                        //这种是 group by
                        Map<String, Object> conditionMap = gson.fromJson(queryCondition, new TypeToken<Map<String, Object>>() {
                        }.getType());
                        //获取group by 语句
                        String groupField = MapUtils.getString(conditionMap, "groupField");
                        //编码
                        String umsCode = MapUtils.getString(conditionMap, "umsCode");
                        //取编码
                        String codeSql = " SELECT id,code_name from ums_code WHERE type_id =  " + umsCode;
                        List<Map<String, Object>> codeList = jdbcTemplate.queryForList(codeSql);
                        //
                        Map<String, Object> umsCodeMap = new HashMap<>();
                        for (Map<String, Object> codeM : codeList) {
                            umsCodeMap.put(MapUtils.getString(codeM, "id"), MapUtils.getString(codeM, "code_name"));
                        }

                        String groupSql = " select count(1) as count , " + groupField + baseConditionSql + appendSql + "  group by " + groupField;
                        List<Map<String, Object>> list = jdbcTemplate.queryForList(groupSql);
                        for (Map<String, Object> map : list) {

                            Integer countX = MapUtils.getInteger(map, "count");
                            String groupFieldV = MapUtils.getString(map, groupField);
                            if (StringUtils.hasText(groupFieldV)) {

                                Map<String, Object> inerx = new HashMap<>();
                                //求百分比
                                inerx.put("key", groupFieldV);
                                //编码名
                                inerx.put("name", umsCodeMap.get(groupFieldV));
                                inerx.put("value", countX);
                                inerx.put("percent", getDoublePercent(countX, allCountB));
                                dax.add(inerx);

                            }


                        }
                        dax.sort((o1, o2) -> {
                            Integer v1 = Integer.valueOf(o1.get("key").toString());
                            Integer v2 = Integer.valueOf(o2.get("key").toString());
                            return v1.compareTo(v2);
                        });
                    } else if (checkType == 3) {
                        // EXISTS
                        List<Map<String, Object>> conditionList = gson.fromJson(queryCondition, new TypeToken<List<Map<String, Object>>>() {
                        }.getType());
                        for (Map<String, Object> map : conditionList) {
                            Map<String, Object> inerx = new HashMap<>();
                            String key = MapUtils.getString(map, "key", "").replace(".0", "");
                            String kName = MapUtils.getString(map, "name");
                            String cSql = MapUtils.getString(map, "sql");
                            //单项总数
                            String countSx = singleSql + " and EXISTS ( " + cSql + " )";
                            Integer bCount = jdbcTemplate.queryForObject(countSx, Integer.class);

                            inerx.put("key", key);
                            //编码名
                            inerx.put("name", kName);
                            inerx.put("value", bCount);
                            inerx.put("percent", getDoublePercent(bCount, allCountB));
                            dax.add(inerx);
                        }

                    } else if (checkType == 4) {
                        // 近五年
                        Map<String, Object> conditionMap = gson.fromJson(queryCondition, new TypeToken<Map<String, Object>>() {
                        }.getType());
                        //获取group by 语句
                        String name = MapUtils.getString(conditionMap, "name");
                        //编码
                        String sql = MapUtils.getString(conditionMap, "sql");
                        int year = LocalDate.now().getYear();
                        for (int i = 1; i <= 5; i++) {
                            Integer nxx = year - i;
                            Map<String, Object> inerx = new HashMap<>();
                            String qSql = sql.replace("@year@", nxx.toString());
                            String countSx = singleSql + " and EXISTS ( " + qSql + " )";
                            Integer bCount = jdbcTemplate.queryForObject(countSx, Integer.class);
                            inerx.put("key", nxx);
                            //编码名
                            inerx.put("name", nxx + name);
                            inerx.put("value", bCount);
                            inerx.put("percent", getDoublePercent(bCount, allCountB));
                            dax.add(inerx);
                        }

                    }
                    nx.put("checkData", dax);
                    rData.add(nx);
                }

                //求平均年龄 特殊处理
                String sumAgeSql = " select SUM( floor(MONTHS_BETWEEN (now(), birthday) / 12) ) as sumage " + baseConditionSql + appendSql;
                Integer ageCount = jdbcTemplate.queryForObject(sumAgeSql, Integer.class);
                BigDecimal d2x = BigDecimal.valueOf(ageCount);
                BigDecimal ageAvg = d2x.divide(allCountB, 2, BigDecimal.ROUND_HALF_UP);
                res.put("ageAvg", ageAvg.doubleValue());
                res.put("total", allCount);

            }


            res.put("fydm", fydm);
            res.put("fymc", user_.getCourtNoText());
            res.put("data", rData);
            data = res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

    /**
     * 反查
     *
     * @return
     */
    @Action("fcStatistics")
    public String fcStatistics() {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("code", 500);
        try {
            //拼接法院代码
            String appendSql = "";
            if (StringUtils.hasText(fydm)) {
                appendSql = " and a.court_code = '" + fydm + "' ";
            }
            //基础统计sql
            String baseCountSql = " select count(1)  from  ums_user_info_view a WHERE is_valid = 1 and user_type =1 and leave_reason is null ";
            StringBuilder singleCountSql = new StringBuilder(baseCountSql + appendSql);
            String baseQuerySql = " SELECT a.*, b.sort_no AS dept_sortNo FROM ums_user_info a, ums_department b WHERE a.court_no = b.court_no AND a.department = b.dept_no and a.user_type = 1 AND a.is_valid = 1 AND a.leave_reason IS NULL ";
            StringBuilder singleQuerySql = new StringBuilder(baseQuerySql + appendSql);
            Gson gson = new Gson();
            List<String> configIdList = gson.fromJson(configId, new TypeToken<List<String>>() {
            }.getType());
            List<String> keyValueList = gson.fromJson(keyValue, new TypeToken<List<String>>() {
            }.getType());

            for (int i = 0; i < configIdList.size(); i++) {
                String configIdN = configIdList.get(i);
                Integer configIdM = Integer.valueOf(configIdN);
                String keyValueN = keyValueList.get(i);

                UmsCountDataConfig umsCountDataConfig = umsCountDataConfigMapper.selectByPrimaryKey(configIdM);
                if (umsCountDataConfig != null) {

                    Integer checkType = umsCountDataConfig.getCheckType();
                    String queryCondition = umsCountDataConfig.getQueryCondition();

                    if (checkType == 1) {
                        //单项匹配 拼接sql  1个项就是1个指标
                        List<Map<String, Object>> conditionList = gson.fromJson(queryCondition, new TypeToken<List<Map<String, Object>>>() {
                        }.getType());
                        for (Map<String, Object> map : conditionList) {
                            String key = MapUtils.getString(map, "key", "").replace(".0", "");
                            String cSql = MapUtils.getString(map, "sql");
                            // 编码对得上
                            if (key.equals(keyValueN) && StringUtils.hasText(cSql)) {
                                singleCountSql.append(cSql);
                                singleQuerySql.append(cSql);
                                break;
                            }
                        }

                    } else if (checkType == 2) {
                        //这种是 group by
                        Map<String, Object> conditionMap = gson.fromJson(queryCondition, new TypeToken<Map<String, Object>>() {
                        }.getType());
                        //获取group by 语句
                        String groupField = MapUtils.getString(conditionMap, "groupField");
                        singleCountSql.append(" and  a.").append(groupField).append("  =  '").append(keyValueN).append("'");
                        singleQuerySql.append(" and  a." + groupField + "  =  '" + keyValueN + "'");

                    } else if (checkType == 3) {
                        //EXISTS
                        List<Map<String, Object>> conditionList = gson.fromJson(queryCondition, new TypeToken<List<Map<String, Object>>>() {
                        }.getType());
                        for (Map<String, Object> map : conditionList) {
                            String key = MapUtils.getString(map, "key", "").replace(".0", "");
                            String cSql = MapUtils.getString(map, "sql");
                            // 编码对得上
                            if (key.equals(keyValueN) && StringUtils.hasText(cSql)) {
                                singleCountSql.append(" and EXISTS ( ").append(cSql).append(" )");
                                singleQuerySql.append(" and EXISTS ( ").append(cSql).append(" )");
                                break;
                            }
                        }
                    } else if (checkType == 4) {
                        // 近五年
                        Map<String, Object> conditionMap = gson.fromJson(queryCondition, new TypeToken<Map<String, Object>>() {
                        }.getType());
                        //获取group by 语句
                        String name = MapUtils.getString(conditionMap, "name");
                        //编码
                        String sql = MapUtils.getString(conditionMap, "sql");
                        String cSql = sql.replace("@year@", keyValueN);
                        singleCountSql.append(" and EXISTS ( ").append(cSql).append(" )");
                        singleQuerySql.append(" and EXISTS ( ").append(cSql).append(" )");

                    }
                }

            }

            String countSql = singleCountSql.toString();
            String querySql = singleQuerySql.toString();

            //进行计算
            Integer count = jdbcTemplate.queryForObject(countSql, Integer.class);
            if (limit == null) {
                limit = 20;
            }
            if (page == null || page < 1) {
                page = 1;
            }
            //拼接统计sql
            String finalQuerySql = " SELECT\n" +
                    "\t a.id, a.fullname, a.law_position_date,a.administration_position_date,work_date,enter_date ,CODE_TEXT (a.court_no, 1) AS court_no_text,\n" +
                    " DEPT_TEXT (a.court_no, a.department) AS department_text, " +
                    "\tCODE_TEXT (a.gender, 3) AS gender_text,\n" +
                    "  a.birthday,\n" +
                    "  floor(MONTHS_BETWEEN (now(), birthday) / 12) as age ,\n" +
                    "\tCODE_TEXT (a.nation_report, 1005) AS nation_report_text,\n" +
                    "  a.hometown,\n" +
                    "  CODE_TEXT (a.personnel_classification, 94) AS personnel_classification_text, \n" +
                    "  CODE_TEXT (a.law_position_report, 1016) AS law_position_report_text, CODE_TEXT (a.administration_position_report, 1015) AS administration_position_report_text, " +
                    " CODE_TEXT (a.education_background_report, 1011) AS education_background_report_text,CODE_TEXT (a.degree, 23) AS degree_text " +
                    "FROM\n" +
                    "\t(\n" +
                    querySql +
                    "\t) a\n" +
                    "\n" +
                    "ORDER BY\n" +
                    "\ta.court_no,\n" +
                    "\ta.dept_sortNo,\n" +
                    "\ta.sort_no nulls last\n" +
                    "LIMIT  " + (page - 1) * limit + ", " + limit + " ;";
            List<Map<String, Object>> resD = jdbcTemplate.queryForList(finalQuerySql);
            //近五年考核情况
            int year = LocalDate.now().getYear();
            StringBuilder yearb = new StringBuilder(" SELECT  n_year,code_name from ums_assess_info a ,ums_code b  WHERE a.n_result = b.id and b.type_id =  39 and  user_id = '@userid@' and n_year in  ( ");
            for (int i = 1; i <= 5; i++) {
                Integer nxx = year - i;
                if (i == 1) {
                    yearb.append(nxx);
                } else {
                    yearb.append(",").append(nxx);
                }
            }
            yearb.append(" ) ORDER BY n_year ");
            String baseN = yearb.toString();
            for (Map<String, Object> map : resD) {

                String id = MapUtils.getString(map, "id");
                String replace = baseN.replace("@userid@", id);
                List<Map<String, Object>> khqk = jdbcTemplate.queryForList(replace);
                StringBuilder khqxs = new StringBuilder();
                for (Map<String, Object> stringObjectMap : khqk) {
                    String n_year = MapUtils.getString(stringObjectMap, "n_year", "");
                    String n_result = MapUtils.getString(stringObjectMap, "code_name", "");
                    khqxs.append(n_year).append(" ").append(n_result).append("    ");
                }
                map.put("khqk", khqxs.toString());

            }


            resMap.put("code", 0);
            resMap.put("count", count);
            resMap.put("data", resD);


        } catch (Exception e) {
            e.printStackTrace();
        }
        data = resMap;
        return "json";
    }


    /**
     * 下载excel
     *
     * @return
     */
    @Action("downloadExcel")
    public String downloadExcel() {
        ByteArrayOutputStream outputStream = null;
        try {
            //拼接法院代码
            String appendSql = "";
            if (StringUtils.hasText(fydm)) {
                appendSql = " and a.court_code = '" + fydm + "' ";
            }
            //基础统计sql
            String baseQuerySql = " SELECT a.*, b.sort_no AS dept_sortNo FROM ums_user_info a, ums_department b WHERE a.court_no = b.court_no AND a.department = b.dept_no and a.user_type = 1 AND a.is_valid = 1 AND a.leave_reason IS NULL ";
            StringBuilder singleQuerySql = new StringBuilder(baseQuerySql + appendSql);
            Gson gson = new Gson();
            List<String> configIdList = gson.fromJson(configId, new TypeToken<List<String>>() {
            }.getType());
            List<String> keyValueList = gson.fromJson(keyValue, new TypeToken<List<String>>() {
            }.getType());

            for (int i = 0; i < configIdList.size(); i++) {
                String configIdN = configIdList.get(i);
                Integer configIdM = Integer.valueOf(configIdN);
                String keyValueN = keyValueList.get(i);

                UmsCountDataConfig umsCountDataConfig = umsCountDataConfigMapper.selectByPrimaryKey(configIdM);
                if (umsCountDataConfig != null) {

                    Integer checkType = umsCountDataConfig.getCheckType();
                    String queryCondition = umsCountDataConfig.getQueryCondition();

                    if (checkType == 1) {
                        //单项匹配 拼接sql  1个项就是1个指标
                        List<Map<String, Object>> conditionList = gson.fromJson(queryCondition, new TypeToken<List<Map<String, Object>>>() {
                        }.getType());
                        for (Map<String, Object> map : conditionList) {
                            String key = MapUtils.getString(map, "key", "").replace(".0", "");
                            String cSql = MapUtils.getString(map, "sql");
                            // 编码对得上
                            if (key.equals(keyValueN) && StringUtils.hasText(cSql)) {
                                singleQuerySql.append(cSql);
                                break;
                            }
                        }

                    } else if (checkType == 2) {
                        //这种是 group by
                        Map<String, Object> conditionMap = gson.fromJson(queryCondition, new TypeToken<Map<String, Object>>() {
                        }.getType());
                        //获取group by 语句
                        String groupField = MapUtils.getString(conditionMap, "groupField");
                        singleQuerySql.append(" and  a." + groupField + "  =  '" + keyValueN + "'");

                    } else if (checkType == 3) {
                        //EXISTS
                        List<Map<String, Object>> conditionList = gson.fromJson(queryCondition, new TypeToken<List<Map<String, Object>>>() {
                        }.getType());
                        for (Map<String, Object> map : conditionList) {
                            String key = MapUtils.getString(map, "key", "").replace(".0", "");
                            String cSql = MapUtils.getString(map, "sql");
                            // 编码对得上
                            if (key.equals(keyValueN) && StringUtils.hasText(cSql)) {
                                singleQuerySql.append(" and EXISTS ( ").append(cSql).append(" )");
                                break;
                            }
                        }
                    } else if (checkType == 4) {
                        // 近五年
                        Map<String, Object> conditionMap = gson.fromJson(queryCondition, new TypeToken<Map<String, Object>>() {
                        }.getType());
                        //获取group by 语句
                        String name = MapUtils.getString(conditionMap, "name");
                        //编码
                        String sql = MapUtils.getString(conditionMap, "sql");
                        String cSql = sql.replace("@year@", keyValueN);
                        singleQuerySql.append(" and EXISTS ( ").append(cSql).append(" )");

                    }
                }

            }

            String querySql = singleQuerySql.toString();
            //拼接统计sql  查询全部数据
            String finalQuerySql = " SELECT\n" +
                    "\t a.id, a.fullname, a.law_position_date,a.administration_position_date,work_date,enter_date ,CODE_TEXT (a.court_no, 1) AS court_no_text,\n" +
                    " DEPT_TEXT (a.court_no, a.department) AS department_text, " +
                    "\tCODE_TEXT (a.gender, 3) AS gender_text,\n" +
                    "  a.birthday,\n" +
                    "  floor(MONTHS_BETWEEN (now(), birthday) / 12) as age ,\n" +
                    "\tCODE_TEXT (a.nation_report, 1005) AS nation_report_text,\n" +
                    "  a.hometown,\n" +
                    "  CODE_TEXT (a.personnel_classification, 94) AS personnel_classification_text, \n" +
                    "  CODE_TEXT (a.law_position_report, 1016) AS law_position_report_text, CODE_TEXT (a.administration_position_report, 1015) AS administration_position_report_text, " +
                    " CODE_TEXT (a.education_background_report, 1011) AS education_background_report_text,CODE_TEXT (a.degree, 23) AS degree_text " +
                    "FROM\n" +
                    "\t(\n" +
                    querySql +
                    "\t) a\n" +
                    "\n" +
                    "ORDER BY\n" +
                    "\ta.court_no,\n" +
                    "\ta.dept_sortNo,\n" +
                    "\ta.sort_no nulls last\n";
            List<Map<String, Object>> resD = jdbcTemplate.queryForList(finalQuerySql);
            //近五年考核情况
            int year = LocalDate.now().getYear();
            StringBuilder yearb = new StringBuilder(" SELECT  n_year,code_name from ums_assess_info a ,ums_code b  WHERE a.n_result = b.id and b.type_id =  39 and  user_id = '@userid@' and n_year in  ( ");
            for (int i = 1; i <= 5; i++) {
                Integer nxx = year - i;
                if (i == 1) {
                    yearb.append(nxx);
                } else {
                    yearb.append(",").append(nxx);
                }
            }
            yearb.append(" ) ORDER BY n_year ");
            String baseN = yearb.toString();
            //excel 下载 组装下载的数据
            int index = 0;
            for (Map<String, Object> map : resD) {
                //序号
                index++;
                map.put("index", index);
                //法律职务
                String law_position = getFieldText(map, "law_position_date") + "  " + getFieldText(map, "law_position_report_text");
                map.put("law_position", law_position);
                // 行政职务
                String administration_position = getFieldText(map, "administration_position_date") + "  " + getFieldText(map, "administration_position_report_text");
                map.put("administration_position", administration_position);

                String id = MapUtils.getString(map, "id");
                String replace = baseN.replace("@userid@", id);
                List<Map<String, Object>> khqk = jdbcTemplate.queryForList(replace);
                StringBuilder khqxs = new StringBuilder();
                for (Map<String, Object> stringObjectMap : khqk) {
                    String n_year = MapUtils.getString(stringObjectMap, "n_year", "");
                    String n_result = MapUtils.getString(stringObjectMap, "code_name", "");
                    khqxs.append(n_year).append(" ").append(n_result).append("    ");
                }
                map.put("khqk", khqxs.toString());

            }
            // 显示的excel 列
            LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();
            fieldMap.put("index", "序号");
            fieldMap.put("fullname", "姓名");
            fieldMap.put("court_no_text", "法院");
            fieldMap.put("department_text", "部门");
            fieldMap.put("gender_text", "性别");
            fieldMap.put("birthday", "出生年月");
            fieldMap.put("age", "年龄");
            fieldMap.put("nation_report_text", "民族");
            fieldMap.put("hometown", "籍贯");
            fieldMap.put("personnel_classification_text", "人员分类");
            fieldMap.put("law_position", "法律职务");
            fieldMap.put("administration_position", "行政职务");
            fieldMap.put("education_background_report_text", "学历");
            fieldMap.put("degree_text", "学位");
            fieldMap.put("work_date", "工作日期");
            fieldMap.put("enter_date", "进院日期");
            fieldMap.put("khqk", "近五年年度考核情况");

            SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(10);
            outputStream = new ByteArrayOutputStream();
            PoiExcelUtil.listMapToExcel(resD, fieldMap, "人员信息", 60000, sxssfWorkbook, false, 0, outputStream);
            sxssfWorkbook.write(outputStream);
            sxssfWorkbook.close();
            this.inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            filename = "人员信息.xlsx";
            return "stream";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        data = "excel下载出错";
        return "json";
    }

    private Double getDoublePercent(Integer countX, BigDecimal allCountB) {
        BigDecimal d2 = BigDecimal.valueOf(countX * 100);
        BigDecimal divide = d2.divide(allCountB, 2, BigDecimal.ROUND_HALF_UP);
        return divide.doubleValue();
    }

    private String getFieldText(Map<String, Object> d, String f) {
        return MapUtils.getString(d, f, "");
    }
}
