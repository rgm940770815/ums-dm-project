/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.dao.attach.experimental;

import cn.net.withub.ums.action.userinfo.UserInfoAttachedViewsAction;
import cn.net.withub.ums.util.StringTools;
import org.apache.ibatis.jdbc.Null;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Diluka
 */
public class AttachedTableProvider {

    public String selectView(Map<String, Object> params) {
        final String viewName = (String) params.get("0");
        final String userId = (String) params.get("1");
        final String order = (String) params.get("2");

        return new SQL() {
            {
                String tableName = " ums_" + StringTools.camelOrPascalToUnderline(viewName) + "_view ";

                FROM(tableName);

                WHERE(String.format(" user_id = '%s' ", userId));

                SELECT(" * ");

                if (!StringTools.isNullOrEmpty(order)) {
                    String[] o = order.split("[.]");
                    if (o.length == 1) {
                        ORDER_BY(String.format(" %s ", o[0]));
                    } else if (o.length == 2) {
                        ORDER_BY(String.format(" %s %s ", o[0], o[1]));
                    }
                } else {
                    ORDER_BY(" sort_no ");
                }
            }
        }.toString();
    }

    // 在上一个方法的基础上,添加个传入where条件的参数
    public String selectViewAppendWhere(Map<String, Object> params) {
        final String viewName = (String) params.get("0");
        final String userId = (String) params.get("1");
        final String order = (String) params.get("2");
        final String where = (String) params.get("3");

        return new SQL() {
            {
                String tableName = " ums_" + StringTools.camelOrPascalToUnderline(viewName) + "_view ";

                FROM(tableName);

                String whereSql = String.format(" user_id = '%s' ", userId) + where;

                WHERE(whereSql);

                SELECT(" * ");

                if (!StringTools.isNullOrEmpty(order)) {
                    String[] o = order.split("[.]");
                    if (o.length == 1) {
                        ORDER_BY(String.format(" %s ", o[0]));
                    } else if (o.length == 2) {
                        ORDER_BY(String.format(" %s %s ", o[0], o[1]));
                    }
                } else {
                    ORDER_BY(" sort_no ");
                }
            }
        }.toString();
    }

    public String selectView1(Map<String, Object> params) {
        final String viewName = (String) params.get("0");
        final String userId = (String) params.get("1");
        final String order = (String) params.get("2");
        final Map otherParam = (Map) params.get("3");

        return new SQL() {
            {
                String tableName = " ums_" + StringTools.camelOrPascalToUnderline(viewName) + "_view ";

                FROM(tableName);

                String where = String.format(" user_id = '%s' ", userId);
                if (otherParam != null) {
                    for (Object o : otherParam.keySet()) {
                        where += " and " + o + " = " + otherParam.get(o);
                    }
                }
                WHERE(where);
                SELECT(" * ");

                if (!StringTools.isNullOrEmpty(order)) {
                    String[] o = order.split("[.]");
                    if (o.length == 1) {
                        ORDER_BY(String.format(" %s ", o[0]));
                    } else if (o.length == 2) {
                        ORDER_BY(String.format(" %s %s ", o[0], o[1]));
                    }
                } else {
                    ORDER_BY(" sort_no ");
                }
            }
        }.toString();
    }


    public String selectDataById(Map<String, Object> params) {
        final String viewName = (String) params.get("0");
        final String id = (String) params.get("1");

        return new SQL() {
            {
                String tableName = StringTools.camelOrPascalToUnderline(viewName);

                FROM(tableName);

                String where = String.format(" id = '%s' ", id);
                WHERE(where);
                SELECT(" * ");
            }
        }.toString() + " limit 1";
    }

    public String selectPresentDataById(Map<String, Object> params) {
        final String viewName = (String) params.get("0");
        final String userId = (String) params.get("1");

        return new SQL() {
            {
                String tableName = StringTools.camelOrPascalToUnderline(viewName);

                FROM(tableName);

                String where = String.format(" user_id = '%s' and n_present_info = 1 ", userId);
                WHERE(where);
                SELECT(" * ");
                ORDER_BY( "  update_time DESC " );
            }
        }.toString() + " limit 1";
    }

    /**
     * 查询指定list的数据集里的指定字段
     * 参数为 {
     *     view : "field, field2",
     *     view2 : "field, field2"
     * }
     * @param params
     * @return
     */
    public String selectDataSetView(Map<String, Object> params) {
        Map<String, String> fields = (Map<String, String>) params.get("0");
        final String userId = (String) params.get("1");

        return new SQL() {
            {
                List<String> allview = new ArrayList<>(fields.keySet());
                String view0 = allview.get(0);
                String tableName = " ums_" + StringTools.camelOrPascalToUnderline(view0) + "_view a ";

                FROM(tableName);
                String[] split = fields.get(view0).split(",");
                String s = "";
                for (String s1 : split) {
                    s += " a." + s1 + ",";
                }
                allview.remove(0);
                for (int i = 0; i < allview.size(); i++) {
                    String view = allview.get(i);
                    String leftSql = " ums_" + StringTools.camelOrPascalToUnderline(view) + "_view a" + i +
                            " on a" + i + ".user_id=a.user_id and a" + i + ".n_present_info=1 ";
                    if (view.equals("levelInfo")) {
                        leftSql += " and a" + i + " .helper_level is not null " + " and a" + i + " .helper_level != '' ";
                    }
                    LEFT_OUTER_JOIN(leftSql);
                    String[] split1 = fields.get(view).split(",");
                    for (String s1 : split1) {
                        s += " a" + i + "." + s1 + ",";
                    }
                }

                String whereSql = String.format(" a.user_id = '%s' and a.n_present_info=1 ", userId);
                if (view0.equals("levelInfo")) {
                    whereSql += " and a.helper_level is not null and a.helper_level != '' ";
                }
                WHERE(whereSql);
                s = s.substring(0, s.lastIndexOf(","));
                SELECT(" " + s + " ");
            }
        }.toString();
    }

    public String selectViewNoAspect(Map<String, Object> params) {
        final String viewName = (String) params.get("0");
        final String userId = (String) params.get("1");
        final String order = (String) params.get("2");
        final String[] field = (String[]) params.get("3");

        return new SQL() {
            {
                String tableName = " ums_" + StringTools.camelOrPascalToUnderline(viewName) + "_view ";

                FROM(tableName);

                WHERE(String.format(" user_id = '%s' ", userId));

                if (field != null && field.length > 0) {
                    StringBuilder field_ = new StringBuilder();
                    for (String str : field) {
                        field_.append(str).append(",");
                    }
                    SELECT(field_.substring(0, field_.length() - 1));
                } else {
                    SELECT(" * ");
                }

                if (StringUtils.hasText(order)) {
                    ORDER_BY(order);
                }

            }
        }.toString() + " limit 0,1";
    }

    public String countView(Map<String, Object> params) {
        final String viewName = (String) params.get("0");
        final String userId = (String) params.get("1");
        return new SQL() {
            {
                String tableName = " ums_" + StringTools.camelOrPascalToUnderline(viewName) + "_view ";

                FROM(tableName);

                WHERE(String.format(" user_id = '%s' ", userId));

                SELECT(" count(0) ");
            }
        }.toString();
    }

    public String countView1(Map<String, Object> params) {
        final String viewName = (String) params.get("0");
        final String userId = (String) params.get("1");
        final Map otherParam = (Map) params.get("2");
        return new SQL() {
            {
                String tableName = " ums_" + StringTools.camelOrPascalToUnderline(viewName) + "_view ";

                FROM(tableName);

                String where = String.format(" user_id = '%s' ", userId);
                if (otherParam != null) {
                    for (Object o : otherParam.keySet()) {
                        where += " and " + o + " = " + otherParam.get(o);
                    }
                }
                WHERE(where);

                SELECT(" count(0) ");
            }
        }.toString();
    }


    public String selectAll(Map<String, Object> params) {
        final String table = (String) params.get("0");
        final int start = (int) params.get("1");
        final int limit = (int) params.get("2");

        String i = new SQL() {
            {
                FROM(table);

                SELECT(" * ");
                if (table.equalsIgnoreCase("ums_user_info")) {

                    WHERE(" user_type = 1 ");
                }


            }
        }.toString();


        return i + " limit " + String.valueOf(start) + "," + String.valueOf(limit);
    }

    public String selectAllView(Map<String, Object> params) {
        String table = (String) params.get("0");
        final int start = (int) params.get("1");
        final int limit = (int) params.get("2");
        int nLevelType = -1;
        if (table.equalsIgnoreCase("ums_level_info_fg")) {
            nLevelType = 1;
        } else if (table.equalsIgnoreCase("ums_level_info_fj")) {
            nLevelType = 2;
        } else if (table.equalsIgnoreCase("ums_level_info_fgzl")) {
            nLevelType = 3;
        } else if (table.equalsIgnoreCase("ums_level_info_sjy")) {
            nLevelType = 4;
        }
        if (table.startsWith("ums_level_info")) {
            table = "ums_level_info";
        }

        String finalTable = table;
        int finalNLevelType = nLevelType;
        String i = new SQL() {
            {
                FROM(finalTable);

                SELECT(" * ");
                if (finalTable.equalsIgnoreCase("ums_user_info_view")) {
                    WHERE(" user_type = 1 ");
                } else if (finalTable.equalsIgnoreCase("ums_level_info")) {
                    WHERE(" n_level_type = " + finalNLevelType);
                }


            }
        }.toString();


        return i + " limit " + String.valueOf(start) + "," + String.valueOf(limit);
    }

    public String count(String tableName) {

        int nLevelType = -1;
        if (tableName.equalsIgnoreCase("ums_level_info_fg")) {
            nLevelType = 1;
        } else if (tableName.equalsIgnoreCase("ums_level_info_fj")) {
            nLevelType = 2;
        } else if (tableName.equalsIgnoreCase("ums_level_info_fgzl")) {
            nLevelType = 3;
        } else if (tableName.equalsIgnoreCase("ums_level_info_sjy")) {
            nLevelType = 4;
        }
        if (tableName.startsWith("ums_level_info")) {
            tableName = "ums_level_info";
        }

        String finalTable = tableName;
        int finalNLevelType = nLevelType;
        return new SQL() {
            {
                FROM(finalTable);

                SELECT(" count(1) ");
                if (finalTable.equalsIgnoreCase("ums_level_info")) {
                    WHERE(" n_level_type = " + finalNLevelType);
                }
            }
        }.toString();

    }


    public String selectSingleInfo(Map<String, Object> params) {

        final String table = (String) params.get("0");
        final String userid = (String) params.get("1");

        String i = new SQL() {
            {
                FROM(table);

                SELECT(" * ");

                if (table.equalsIgnoreCase("ums_user_info")) {

                    WHERE(" user_type = 1 and id = '" + userid + "'");

                } else {

                    WHERE(" user_id = '" + userid + "'");

                }

            }
        }.toString();

        return i;

    }

    public String selectViewBatch(Map<String, Object> params) {
        final String viewName = (String) params.get("0");
        final String order = (String) params.get("1");
        String orderby = " ORDER BY ";
        orderby += "CASE WHEN ";

        String field = "";
        String asc = "";
        if (!StringTools.isNullOrEmpty(order)) {
            String[] o = order.split("[.]");
            if (o.length == 1) {
                field = String.format(" %s ", o[0]);
            } else if (o.length == 2) {
                field = String.format(" %s ", o[0]);
                asc = String.format(" %s ", o[1]);
            }
        }
        if (StringUtils.isEmpty(field.replaceAll(" ", ""))) {
            field = " sort_no ";
        }
        orderby += ("(" + field + " IS NULL OR " + field + " = '') THEN 0 ELSE 1 END DESC," + field + asc);

        String tableName = "ums_" + StringTools.camelOrPascalToUnderline(viewName) + "_view";
//        return "select b.c,a.* from" +
//                " (SELECT * FROM " + tableName + " where update_time is not null GROUP BY update_time " + orderby + " ) a," +
//                " (SELECT count(*) c,update_time FROM " + tableName + " where update_time is not null GROUP BY update_time " + orderby + " ) b " +
//                "where b.update_time = a.update_time";

        String queryField = "";
        String groupField = "";
        switch (tableName) {
            case "ums_legal_job_view":
                queryField = "sort_no,c_unit,d_assign_date,d_approval_date,update_time,n_assign_type,n_job," +
                        "n_assign_type_text,n_job_text,c_department,n_assign_reason,c_approval_unit,c_approval_doc_no," +
                        "n_present_info,n_is_first_appoint_judge,n_first_judge_year,n_job_situation,n_is_pro,n_pro_situation";
                break;
            case "ums_rank_info_view":
                queryField = "sort_no,n_assign_type,n_assign_type_text,c_unit,c_department,d_assign_date,n_assign_reason,update_time," +
                        "c_approval_unit,d_approval_date,n_present_info_text,n_rank_text,n_rank,n_rank_nature,c_approval_doc_no,n_present_info";
                break;
            case "ums_level_info_view":
//                queryField = "sort_no,c_approval_unit,n_present_info_text,c_approval_doc_no,n_present_info,update_time,d_start_date,c_cert_no," +
//                        "n_alt_type,n_alt_reason,c_alt_basis,n_level_type,n_level_type_text,judge_level,judge_level_text,marshal_level," +
//                        "marshal_level_text,helper_level,helper_level_text,clerk_level_text,clerk_level";
                queryField = "sort_no,c_approval_unit,n_present_info_text,c_approval_doc_no,n_present_info,update_time," +
                        "d_start_date,c_cert_no,n_alt_type,n_alt_reason,c_alt_basis,n_level_type,n_level_type_text," +
                        "CASE " +
                        "WHEN n_level_type == 1 THEN judge_level " +
                        "WHEN n_level_type == 2 THEN marshal_level " +
                        "WHEN n_level_type == 3 THEN helper_level " +
                        "WHEN n_level_type == 4 THEN clerk_level " +
                        "END AS n_level_name," +
                        "CASE " +
                        "WHEN n_level_type == 1 THEN judge_level_text " +
                        "WHEN n_level_type == 2 THEN marshal_level_text " +
                        "WHEN n_level_type == 3 THEN helper_level_text " +
                        "WHEN n_level_type == 4 THEN clerk_level_text " +
                        "END AS n_level_name_text";
                groupField = "sort_no,c_approval_unit,n_present_info_text,c_approval_doc_no,n_present_info,update_time," +
                        "d_start_date,c_cert_no,n_alt_type,n_alt_reason,c_alt_basis,n_level_type,n_level_type_text," +
                        "CASE " +
                        "WHEN n_level_type == 1 THEN judge_level " +
                        "WHEN n_level_type == 2 THEN marshal_level " +
                        "WHEN n_level_type == 3 THEN helper_level " +
                        "WHEN n_level_type == 4 THEN clerk_level " +
                        "END," +
                        "CASE " +
                        "WHEN n_level_type == 1 THEN judge_level_text " +
                        "WHEN n_level_type == 2 THEN marshal_level_text " +
                        "WHEN n_level_type == 3 THEN helper_level_text " +
                        "WHEN n_level_type == 4 THEN clerk_level_text " +
                        "END";
                break;
            case "ums_assess_info_view":
                queryField = "sort_no,n_year,n_result,n_result_text,update_time";
                break;
            case "ums_reward_info_view":
                queryField = "sort_no,n_reward_type,n_reward_type_text,n_reward_reason,n_reward_reason_text,n_personal_state," +
                        "n_personal_state_text,n_reward_level,n_reward_level_text,d_reward_date,c_approval_doc_no,c_reward_type_info,c_reward_reason_info," +
                        "c_approval_unit_code,c_approval_unit,c_approval_unit_add,approval_type,approval_name,approval_name_info,update_time";
                break;
        }
        String sql = "SELECT " +
                    queryField +
                    ",COUNT(*) AS c " +
                    "FROM " +
                    tableName +
                    " WHERE " +
                    "update_time IS NOT NULL " +
                    "GROUP BY ";
                    if ("ums_level_info_view".equals(tableName)) {
                        sql += groupField + " ";
                    } else {
                        sql += queryField + " ";
                    }
                    sql += orderby;
        return sql;
    }

    public String countViewBatch(String params) {
        String tableName = " ums_" + StringTools.camelOrPascalToUnderline(params) + "_view ";
        return " select count(b) from (select COUNT(*) b from " + tableName + " where update_time is not null GROUP BY update_time) a ";
    }

    public String selectViewAllBatch(Map<String, Object> params) {
        final String viewName = (String) params.get("0");
        String table = " ums_" + StringTools.camelOrPascalToUnderline(viewName) + "_view ";
        final String order = (String) params.get("1");
        final String where = (String) params.get("2");
        return new SQL() {
            {
                FROM(table);
                SELECT(" id,user_id,user_no ");
                WHERE(" update_time = '" + where + "'");

                if (!StringTools.isNullOrEmpty(order)) {
                    String[] o = order.split("[.]");
                    if (o.length == 1) {
                        ORDER_BY(String.format(" %s ", o[0]));
                    } else if (o.length == 2) {
                        ORDER_BY(String.format(" %s %s ", o[0], o[1]));
                    }
                } else {
                    ORDER_BY(" sort_no ");
                }
            }
        }.toString();
    }

    public String getCourtByOrganization(Map<String, Object> params) {
        String sql = null;
        try {
            List<String> courtList = (List<String>) params.get("0");
            List<String> codeList = (List<String>) params.get("1");
            if (courtList == null || courtList.size() == 0 || codeList == null || codeList.size() == 0) {
                return null;
            }
            sql = "SELECT * from ums_court_full where exists ( " +
                    "SELECT court_code from ums_user_info  " +
                    "where court_code in ( " +
                    listToStr(courtList, ',') +
                    " ) " +
                    "and preparation in ( " +
                    listToString(codeList, ',') +
                    ") and ums_user_info.is_valid = 1  and ums_court_full.court_code = court_code " +
                    " GROUP BY court_code" +
                    ") ORDER BY sort_no";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sql;
    }

    public String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    public String listToStr(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(String.format("'%s'", list.get(i)));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    public String AttachmentCourtUpdate(Map<String, Object> params) {
        String sql = "";
        try {

            String userId = (String) params.get("0");
            Integer courtNo = (Integer) params.get("1");

            List<String> attachmentTable = UserInfoAttachedViewsAction.AttachmentTable;
            for (String list : attachmentTable) {
                sql += String.format("UPDATE " + list + " set court_no = %d where user_id = '%s';\n", courtNo, userId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sql;
    }

    public String selectViewPresent(Map<String, Object> params) {
        final String viewName = (String) params.get("0");
        final String userId = (String) params.get("1");

        return new SQL() {
            {
                String tableName = " ums_" + StringTools.camelOrPascalToUnderline(viewName) + "_view ";

                FROM(tableName);

                StringBuilder builder = new StringBuilder();
                String s = builder.append(String.format(" user_id = '%s' ", userId)).append(" and  n_present_info = 1 ").toString();
                WHERE(s);

                SELECT(" * ");

            }
        }.toString() + " limit 0,1";
    }


    public String selectActiveLevelInfo(Map<String, Object> params) {
        Object levelType = null;
        if(params != null){
            levelType= params.get("0");
        }


        Object finalLevelType = levelType;
        return new SQL() {
            {
//                String tableName = " ums_" + StringTools.camelOrPascalToUnderline(viewName) + "_view ";

                FROM( "ums_level_info" );

                String sql = " n_present_info = 1  ";

                if(finalLevelType != null){
                    sql += " and n_level_type = " + finalLevelType.toString();
                }
//                n_present_info = 1 and n_level_type = 1;
                WHERE(sql );

                SELECT(" * ");

                ORDER_BY(" n_level_type asc ");


            }
        }.toString();
    }

}
