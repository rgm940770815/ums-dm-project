package cn.net.withub.ums.action.statistics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cypress on 2017/4/26.
 */
public enum ChartDataEnum {

    AGE("age", Arrays.asList(
            new HashMap<String, Object>() {{
                put("name", "25岁以下");
                put("condition", "a.age BETWEEN 0 and 25");
            }},
            new HashMap<String, Object>() {{
                put("name", "26岁到30岁");
                put("condition", "a.age BETWEEN 26 and 30");
            }},
            new HashMap<String, Object>() {{
                put("name", "31岁到35岁");
                put("condition", "a.age BETWEEN 31 and 35");
            }},
            new HashMap<String, Object>() {{
                put("name", "36岁到40岁");
                put("condition", "a.age BETWEEN 36 and 40");
            }},
            new HashMap<String, Object>() {{
                put("name", "41岁到45岁");
                put("condition", " a.age BETWEEN 41 and 45");
            }},
            new HashMap<String, Object>() {{
                put("name", "46岁到50岁");
                put("condition", "a.age BETWEEN 46 and 50");
            }},
            new HashMap<String, Object>() {{
                put("name", "51岁到55岁");
                put("condition", "a.age BETWEEN 51 and 55");
            }},
            new HashMap<String, Object>() {{
                put("name", "56岁到60岁");
                put("condition", "a.age BETWEEN 56 and 60");
            }},
            new HashMap<String, Object>() {{
                put("name", "60岁以上");
                put("condition", "a.age > 60");
            }}
    )),
    AGE_N("age_n", Arrays.asList(
            new HashMap<String, Object>() {{
                put("name", "25岁以下");
                put("condition", "a.age BETWEEN 0 and 25");
            }},
            new HashMap<String, Object>() {{
                put("name", "26岁到40岁");
                put("condition", "a.age BETWEEN 26 and 40");
            }},
            new HashMap<String, Object>() {{
                put("name", "41岁到50岁");
                put("condition", " a.age BETWEEN 41 and 50");
            }},
            new HashMap<String, Object>() {{
                put("name", "51岁到60岁");
                put("condition", "a.age BETWEEN 51 and 60");
            }},
            new HashMap<String, Object>() {{
                put("name", "60岁以上");
                put("condition", "a.age > 60");
            }}
    )),
    POLITICAL("political", Arrays.asList(
            new HashMap<String, Object>() {{
                put("name", "中共党员");
                put("condition", "political = 1 ");
            }},
            new HashMap<String, Object>() {{
                put("name", "中共预备党员");
                put("condition", "political = 2");
            }},
            new HashMap<String, Object>() {{
                put("name", "共青团员");
                put("condition", "political = 3");
            }},
            new HashMap<String, Object>() {{
                put("name", "民主党派");
                put("condition", "political >= 4 and  political <= 11");
            }},
            new HashMap<String, Object>() {{
                put("name", "无党派民主人士");
                put("condition", "political = 12");
            }},
            new HashMap<String, Object>() {{
                put("name", "群众");
                put("condition", "political = 13");
            }}
    )),
    EDUCATION("educationBackground", Arrays.asList(
            new HashMap<String, Object>() {{
                put("name", "博士研究生");
                put("condition", "education_background = 1 ");
            }},
            new HashMap<String, Object>() {{
                put("name", "硕士研究生");
                put("condition", "education_background >=  2 and  education_background <= 9");
            }},
            new HashMap<String, Object>() {{
                put("name", "大学本科");
                put("condition", "education_background >= 11 and  education_background < 20");
            }},
            new HashMap<String, Object>() {{
                put("name", "大专及以下");
                put("condition", "education_background >= 21");
            }},
            new HashMap<String, Object>() {{
                put("name", "未知");
                put("condition", "IFNULL(education_background, '') = ''");
            }}
    )),
    DEGREE("degree", Arrays.asList(
            new HashMap<String, Object>() {{
                put("name", "博士");
                put("condition", "degree >= 200 and  degree < 300");
            }},
            new HashMap<String, Object>() {{
                put("name", "硕士");
                put("condition", "degree >= 300 and  degree < 400");
            }},
            new HashMap<String, Object>() {{
                put("name", "学士");
                put("condition", "degree >= 400 and  degree < 500");
            }}
    )),
    POSITION("law_position", Arrays.asList(
            new HashMap<String, Object>() {{
                put("name", "院领导法官");
                put("condition", " law_position_text  IN ('院长','代院长','副院长','审判委员会委员','庭长','副庭长','审判员','助理审判员')\n" +
                        "AND department  IN (1) ");
            }},
            new HashMap<String, Object>() {{
                put("name", "一线法官");
                put("condition", " law_position_text  IN ('院长','代院长','副院长','审判委员会委员','庭长','副庭长','审判员','助理审判员')\n" +
                        "AND ((department NOT IN (51,52,53,54,56,58,63,67)) AND (department between 2 and 48 OR department  IN (50,51,52,53,54,56,58,63,67,74,75,76,77,78,79,80) OR department  BETWEEN 88 AND 123 )) ");
            }},
            new HashMap<String, Object>() {{
                put("name", "司法行政部门法官");
                put("condition", "law_position_text  IN ('院长','代院长','副院长','审判委员会委员','庭长','副庭长','审判员','助理审判员')\n" +
                        "AND ((department  IN (51,52,53,54,56,58,63,67)) or (department > 48 AND department NOT IN (50,51,52,53,54,56,58,63,67,74,75,76,77,78,79,80) AND department NOT BETWEEN 88 AND 123 ))");
            }},
            new HashMap<String, Object>() {{
                put("name", "司法行政人员");
                put("condition", " ((law_position_text = '书记员' AND department NOT IN (1))OR (law_position_text NOT IN ('院长','代院长','副院长','审判委员会委员','庭长','副庭长','审判员','助理审判员','执行员','法官助理','书记员','法警','法医')))\n" +
                        "AND ((department  IN (1,51,52,53,54,56,58,63,67)) or (department > 48\n" +
                        "AND department NOT IN (50,51,52,53,54,56,57,58,63,67,74,75,76,77,78,79,80)\n" +
                        "AND department NOT BETWEEN 88 AND 123 )) ");
            }},
            new HashMap<String, Object>() {{
                put("name", "审判辅助人员");
                put("condition", "  (\n" +
                        "NOT (((law_position_text = '书记员' AND department NOT IN (1))OR (law_position_text NOT IN ('院长','代院长','副院长','审判委员会委员','庭长','副庭长','审判员','助理审判员','执行员','法官助理','书记员','法警','法医')))\n" +
                        "AND ((department  IN (1,51,52,53,54,56,58,63,67)) or (department > 48\n" +
                        "AND department NOT IN (50,51,52,53,54,56,57,58,63,67,74,75,76,77,78,79,80)\n" +
                        "AND department NOT BETWEEN 88 AND 123 )) )\n" +
                        "AND NOT ( law_position_text  IN ('院长','代院长','副院长','审判委员会委员','庭长','副庭长','审判员','助理审判员') )\n" +
                        "or law_position_text IS NULL\n" +
                        ") ");
            }}
    )),
    PERSONNEL("personnel_classification", Arrays.asList(
            new HashMap<String, Object>() {{
                put("name", "法官");
                put("condition", "  personnel_classification IN ( select id from ums_code WHERE TYPE_ID = 94 and IS_VALID = 1 and ( id = 1 or parent_id = 1 ) )  ");
            }},
            new HashMap<String, Object>() {{
                put("name", "审判辅助人员");
                put("condition", " personnel_classification IN ( select id from ums_code WHERE TYPE_ID = 94 and IS_VALID = 1 and ( id = 2 or parent_id = 2 ) ) ");
            }},
            new HashMap<String, Object>() {{
                put("name", "司法行政人员");
                put("condition", " personnel_classification IN ( select id from ums_code WHERE TYPE_ID = 94 and IS_VALID = 1 and ( id = 3 ) )  ");
            }}
    )),
    BASE_Q("base_Q", Arrays.asList(
            new HashMap<String, Object>() {{
                put("name", "male");
                put("condition", "gender = 1");
            }},
            new HashMap<String, Object>() {{
                put("name", "female");
                put("condition", "gender = 2");
            }},
            new HashMap<String, Object>() {{
                put("name", "yefg");
                put("condition", "yefg = 1");
            }},
            new HashMap<String, Object>() {{
                put("name", "male_yefg");
                put("condition", "gender = 1 and yefg = 1");
            }},
            new HashMap<String, Object>() {{
                put("name", "female_yefg");
                put("condition", "gender = 2 and yefg = 1");
            }}
    ));

    private String name;
    private List<Map<String, Object>> list;

    private ChartDataEnum(String name, List<Map<String, Object>> list) {
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }
}
