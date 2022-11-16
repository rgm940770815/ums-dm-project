/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.util;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Diluka
 */
public class CriteriaTools {

    /**
     * 加载条件
     *
     * @param criteria 不是内部类，是XXXCriteria
     * @param conditions 之前生成的Map
     */
    public static void loadMap(Object criteria, Map<String, Object[]> conditions) {
        try {
            Class<?> t = criteria.getClass();
            Object c = t.getMethod("createCriteria", (Class<?>[]) null).invoke(criteria, (Object[]) null);
            Class<?> t1 = c.getClass();
            for (Map.Entry<String, Object[]> entry : conditions.entrySet()) {
                try {
                    Object[] params = entry.getValue();
                    Class<?>[] types = new Class[params.length];
                    for (int i = 0; i < params.length; i++) {
                        types[i] = params[i].getClass();
                    }
                    if (entry.getKey().equals("setOrderByClause")) {
                        Method m = t.getMethod("setOrderByClause", String.class);
                        m.setAccessible(true);
                        m.invoke(criteria, params);
                    } else {
                        Method m = t1.getMethod(entry.getKey(), types);
                        m.setAccessible(true);
                        m.invoke(c, params);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
        }
    }

//    public static Map<String, Object[]> fromQueryString(String query) {
//        Map<String, Object[]> conditions = new HashMap<>();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        if (query != null) {
//            String[] s = query.split("[|]");
//            OUTTER:
//            for (String q : s) {
//                String[] qs = q.split("@");
//                Object[] values = new Object[qs.length - 1];
//
//                if (qs[0].toUpperCase().endsWith("[ORDER]")) {
//                    values = new Object[]{""};
//                    for (int i = 1; i < qs.length; i++) {
//                        if (i != 1) {
//                            values[0] += ",";
//                        }
//                        if (qs[0].startsWith("D_")) {
//                            values[0] += qs[i] + " DESC ";
//                        } else if (qs[0].startsWith("A_")) {
//                            values[0] += qs[i] + " ASC";
//                        } else {
//                            continue OUTTER;
//                        }
//                    }
//
//                } else {
//                    for (int i = 1; i < qs.length; i++) {
//                        if (qs[0].startsWith("N_")) {
//                            values[i - 1] = new Integer(qs[i]);
//                        } else if (qs[i].startsWith("D_")) {
//                            try {
//                                values[i - 1] = sdf.parse(qs[i]);
//                            } catch (Exception e) {
//                            }
//                        } else if (qs[i].startsWith("S_")) {
//                            values[i - 1] = qs[i];
//                        } else {
//                            continue OUTTER;
//                        }
//                    }
//                }
//
//                conditions.put(qs[0].substring(2), values);
//            }
//        }
//
//        return conditions;
//    }
    /**
     * 生成查詢條件，插件上传
     *
     * 这不是个好主意
     *
     * @param qtype 字段
     * @param query 內容
     * @param sortname 字段
     * @param sortorder ASC/DESC
     * @return
     */
    @Deprecated
    public static Map<String, Object[]> fromQueryString(String qtype, String query, String sortname, String sortorder) {
        Map<String, Object[]> conditions = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if (query.length() > 0 && qtype.length() > 0) {
                if (qtype.startsWith("N_")) {
                    conditions.put(qtype.substring(2), new Object[]{new Integer(query)});
                } else if (qtype.startsWith("D_")) {
                    conditions.put(qtype.substring(2), new Object[]{sdf.parse(query)});
                } else if (qtype.startsWith("S_")) {
                    conditions.put(qtype.substring(2), new Object[]{query});
                }
            }
        } catch (Exception e) {
        }

        try {
            if (sortname.length() > 0 && sortorder.length() > 0) {
                conditions.put("setOrderByClause", new Object[]{StringTools.camelOrPascalToUnderline(sortname) + " " + sortorder});
            }
        } catch (Exception e) {
        }

        return conditions;
    }
}
