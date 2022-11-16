package cn.net.withub.ums.action.xml;

import java.util.Map;

/**
 * Created by Administrator on 2016/1/25.
 */
public class TableTranslateUtil {

    // A01(人员基本信息表) ums_user_info
    public Map<String, Object> umsUserInfoChange(Map<String, Object> m, Map<String, Map> map) {

        try {
            //婚姻状况
            m = DoMap("marital_status", "6", m, map);
            //民族
            m = DoMap("nation", "5", m, map);
            //学位
            m = DoMap("degree", "23", m, map);
            //行政职务
            m = DoMap("administration_position", "15", m, map);
            //党组职务
            //职级
            m = DoMap("rank", "17", m, map);
            //公务员级别
            m = DoMap("servant_level", "83", m, map);
            //党组织职务
            m = DoMap("party_office", "57", m, map);
            //政治面貌
            m = DoMap("political", "13", m, map);
            //调离原因

            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // A02（行政职务表） ums_administrative_job
    public Map<String, Object> umsAdministrativeJobChange(Map<String, Object> m, Map<String, Map> map) {

        try {
            m = DoMap("n_job", "15", m, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }

    // T_RYSX_ZZMM（政治面貌） ums_political_info
    public Map<String, Object> umsPoliticalInfoChange(Map<String, Object> m, Map<String, Map> map) {
        //政治面貌
        try {
            m = DoMap("n_political", "13", m, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }

    //A05（职级信息表） ums_rank_info
    public Map<String, Object> umsRankInfoChange(Map<String, Object> m, Map<String, Map> map) {
        try {
            //职级
            m = DoMap("n_rank", "17", m, map);
            m = DoMap("n_rank_nature", "59", m, map);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //T_RYSX_DJXX（等级信息表） ums_level_info
    public Map<String, Object> umsLevelInfoChange(Map<String, Object> m, Map<String, Map> map) {
        try {
            //职级
            m = DoMap("n_level_type", "60", m, map);
            //退额原因
            m = DoMap("yefg_end_reason", "45", m, map);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //A08（人员属性_学历信息表） ums_education_info
    public Map<String, Object> umsEducationInfoChange(Map<String, Object> m, Map<String, Map> map) {
        try {
            //学习形式
            m = DoMap("n_study_form", "22", m, map);
            //国家
            m = DoMap("n_school_country", "24", m, map);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //A09（人员属性_学位信息表） ums_degree_info
    public Map<String, Object> umsDegreeInfoChange(Map<String, Object> m, Map<String, Map> map) {
        try {
            //学位
            m = DoMap("n_degree", "23", m, map);
            //学习形式
            m = DoMap("n_study_form", "22", m, map);
            //国家
            m = DoMap("n_award_country", "24", m, map);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // T_RYSX_ZDXX（人员属性_在读信息表） ums_study_info
    public Map<String, Object> umsStudyInfoChange(Map<String, Object> m, Map<String, Map> map) {
        try {

            //学习形式
            m = DoMap("n_study_form", "22", m, map);

            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // A11（人员属性_培训信息表） ums_training_info
    public Map<String, Object> umsTrainingInfoChange(Map<String, Object> m, Map<String, Map> map) {
        try {

            //培训形式
            m = DoMap("n_training_form", "22", m, map);
            //培训方式
            m = DoMap("n_training_way", "50", m, map);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // A36（家庭成员信息表）ums_family_info
    public Map<String, Object> umsFamilyInfoChange(Map<String, Object> m, Map<String, Map> map) {
        try {

            //与该人关系
            m = DoMap("n_relationship", "40", m, map);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //A15（年度考核信息表）ums_assess_info
    public Map<String, Object> umsAssessInfoChange(Map<String, Object> m, Map<String, Map> map) {
        try {

            //考核结果
            m = DoMap("n_result", "39", m, map);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // A14Z3（人员属性_惩罚信息表）ums_punish_info
    public Map<String, Object> umsPunishInfoChange(Map<String, Object> m, Map<String, Map> map) {
        try {

            //惩戒类别
            m = DoMap("n_punish_type", "27", m, map);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // A12（人员属性_出国信息表) ums_abroad_info
    public Map<String, Object> umsAbroadInfoChange(Map<String, Object> m, Map<String, Map> map) {
        try {

            //国家
            m = DoMap("n_country", "24", m, map);
            //出国性质
            m = DoMap("n_nature", "37", m, map);
            //出国(境)身份
            m = DoMap("n_identity", "38", m, map);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // A10（人员属性_外语信息表）ums_foreign_lang
    public Map<String, Object> umsForeignLangChange(Map<String, Object> m, Map<String, Map> map) {
        try {

            //语种
            m = DoMap("n_language", "34", m, map);

            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // A06（人员属性_专业技术职务信息表）ums_pro_technical_position
    public Map<String, Object> umsProTechnicalPositionChange(Map<String, Object> m, Map<String, Map> map) {
        try {

            //职称等级
            m = DoMap("n_title_rank", "58", m, map);

            //取得名称
            m = DoMap("n_pt_name", "42", m, map);

            //聘任名称
            m = DoMap("n_engage_name", "42", m, map);
            
            return m;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    // A23（人员属性_后备干部表） ums_reserve_cadre
    public Map<String, Object> umsReserveCadreChange(Map<String, Object> m, Map<String, Map> map) {
        try {

            //后备职务
            m = DoMap("n_reserve_pos", "14", m, map);
            //锻炼职务
            m = DoMap("n_exercise_pos", "14", m, map);

            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //T_RYSX_LDBZ（人员属性_领导班子表）ums_leadership
    public Map<String, Object> umsLeadershipChange(Map<String, Object> m, Map<String, Map> map) {
        try {

            //职务
            m = DoMap("n_position", "14", m, map);

            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // T_RYSX_GWY（人员属性_公务员表）             ums_civil_servant_level
    public Map<String, Object> umsCivilServantLevelChange(Map<String, Object> m, Map<String, Map> map) {
        try {

            //公务员级别
            m = DoMap("n_servant_level", "83", m, map);

            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    private Map<String, Object> DoMap(String field, String codeId, Map<String, Object> m, Map<String, Map> map) throws Exception {

        if (m == null || map == null) {
            return m;
        }
        //原本信息对应的编码
        Object s = m.get(field);
        if (s == null) {
            return m;
        }
        //编码转换表 根据codeId得到要转换编码的Map
        Map l = map.get(codeId);
        if (l == null) {
            return m;
        }
        //在转换的Map里面取对应转换后的编码
        Object obj = l.get(s.toString());
        if (obj == null) {
            return m;
        }
        //放入信息的Map中
        m.put(field, obj.toString());
        return m;

    }


}
