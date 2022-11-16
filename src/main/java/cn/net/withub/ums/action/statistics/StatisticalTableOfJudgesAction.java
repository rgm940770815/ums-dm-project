package cn.net.withub.ums.action.statistics;

import cn.net.withub.ums.entity.UmsCode;
import cn.net.withub.ums.service.UmsCodeService;
import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/statisticalTable")
@Results({@Result(name = "json", type = "json", params = {"root", "data"})})
public class StatisticalTableOfJudgesAction {

    private Integer courtLevel;//法院级别
    private String preparation;//编制
    private String courtCode;//法院代码
    private String law_position_report_text;//人员职位
    private String personnel_classification;//法律职务
    private Object data;

    @Autowired
    private UmsCodeService umsCodeService;

    public Integer getCourtLevel() {
        return courtLevel;
    }

    public void setCourtLevel(Integer courtLevel) {
        this.courtLevel = courtLevel;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getCourtCode() {
        return courtCode;
    }

    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode;
    }

    public String getLaw_position_report_text() {
        return law_position_report_text;
    }

    public void setLaw_position_report_text(String law_position_report_text) {
        this.law_position_report_text = law_position_report_text;
    }

    public String getPersonnel_classification() {
        return personnel_classification;
    }

    public void setPersonnel_classification(String personnel_classification) {
        this.personnel_classification = personnel_classification;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Autowired
    SqlSession sqlSession;

    /**
     * 法官等级情况统计表  做旧
     *
     * @return
     */
    @Action("countRankOfJudges")
    public String countRankOfJudges() {

        //获取前端传来的参数
        Map<String, Object> param = new HashMap<>();
        if (courtLevel != null)
            param.put("courtLevel", courtLevel);
        if (preparation != null && !"".equals(preparation))
            param.put("preparation", preparation);
        if (courtCode != null && !"".equals(courtCode)) {
            param.put("courtCode", courtCode);
        }
        if (law_position_report_text != null && !"".equals(law_position_report_text)) {
            param.put("law_position_report_text", law_position_report_text);
        }

        //统计法官等级数据
        List<Map<String, Object>> list_RankOfJudges = sqlSession.selectList("StatisticalTableOfJudgesMapper.countRankOfJudges", param);

        Map returnMap = new HashMap<>();
        returnMap.put("list", list_RankOfJudges);
        data = returnMap;

        return "json";
    }

    /**
     * 法官等级情况统计表  目前使用的统计
     *
     * @return
     */
    @Action("countRankOfJudgesNew")
    public String countRankOfJudgesNew() {


        List<Map<String,Object>> groupList = new ArrayList<>();
        try {
            Map<String, Object> param = new HashMap<>();
            //在查询全部的分组统计
            List<Map<String, Object>> allList = sqlSession.selectList("StatisticalTableOfJudgesMapper.countRankOfJudgesNew", param);
//        Map<String, List<Map<String, Object>>> collect1 = allList.stream().collect(Collectors.groupingBy(map -> {
//            Object judge_level = map.get("judge_level");
//            return judge_level == null ? "" : judge_level.toString();
//        }));
            Map<String, Object> allM = new HashMap<>();
            allM.put("text", "合计");
            allM.put("law_position","");
            allM.put("data",allList);

            groupList.add(allM);

            //先查询全部的法律职务
            List<UmsCode> umsCodes = umsCodeService.selectCodesByType(16);

            for (UmsCode umsCode : umsCodes) {
                String codeId = umsCode.getId();
                param.put("law_position",codeId);
                //分法律职务进行统计
                List<Map<String, Object>> gList = sqlSession.selectList("StatisticalTableOfJudgesMapper.countRankOfJudgesNew", param);
    //            Map<String, List<Map<String, Object>>> collect2 = gList.stream().collect(Collectors.groupingBy(map -> {
    //                Object judge_level = map.get("judge_level");
    //                return judge_level == null ? "" : judge_level.toString();
    //            }));
                Map<String,Object> gMap = new HashMap<>();
                gMap.put("text",umsCode.getCodeName());
                gMap.put("law_position",codeId);
                gMap.put("data",gList);

                groupList.add(gMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        data = groupList;
        return "json";
    }


    /**
     * 法官等级情况统计表
     *
     * @return
     */
    @Action("countRankOfStaff")
    public String countRankOfStaff() {

        //获取前端传来的参数
        Map<String, Object> param = new HashMap<>();
        if (courtLevel != null)
            param.put("courtLevel", courtLevel);
        if (preparation != null && !"".equals(preparation))
            param.put("preparation", preparation);
        if (courtCode != null && !"".equals(courtCode)) {
            param.put("courtCode", courtCode);
        }
        if (law_position_report_text != null && !"".equals(law_position_report_text)) {
            param.put("law_position_report_text", law_position_report_text);
        }

        //统计法官等级数据
        List<Map<String, Object>> list_RankOfJudges = sqlSession.selectList("StatisticalTableOfJudgesMapper.countRankOfStaff", param);

        Map returnMap = new HashMap<>();
        returnMap.put("list", list_RankOfJudges);
        data = returnMap;

        return "json";
    }

}
