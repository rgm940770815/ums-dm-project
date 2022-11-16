package cn.net.withub.ums.action.statistics;

import cn.net.withub.ums.action.userinfo.UserInfoViewAction;
import cn.net.withub.ums.common.CourtConstant;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.extend.UmsDataMapper;
import cn.net.withub.ums.dao.extend.UmsQueryDataMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.*;
import cn.net.withub.ums.service.statistics.StatisticsService;
import cn.net.withub.ums.util.SimpleDecodeParameter;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Cypress on 2017/4/20.
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/chartData")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class ChartDataAction {

    static {

    }

    @Autowired
    UmsDataMapper umsDataMapper;

    @Autowired
    UmsQueryDataMapper umsQueryDataMapper;

    @Autowired
    private UmsUserInfoViewService userInfoViewService;

    @Autowired
    private UmsDepartmentService umsDepartmentService;

    @Autowired
    private UmsCourtFullService umsCourtFullService;

    @Autowired
    private UmsPartyOrganizationService umsPartyOrganizationService;

    @Autowired
    UmsInstitutionPersonRecordService umsInstitutionPersonRecordService;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    CourtConstant courtConstant;

    @Autowired
    private UmsAuthorityService authorityService;

    @Autowired
    private UmsCourtFullService courtFullService;

    private String chart;
    private Object data;
    private String keyValue;
    private Integer page = 1;
    private Integer start = 0;
    private Integer limit = 10;
    private boolean queryAll;
    private boolean queryFy = false;

    private String fydm;
    private Integer courtNo;

    @Action("getUserCourtText")
    public String getUserCourtText() {
        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
        UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        data = user_.getCourtNoText();
        return "json";
    }

    @Action("statChartData")
    public String statChart() {

        System.out.println("courtNo"+courtNo);
        Map<String, Object> returnMap = new HashMap<>();
        data = returnMap;
        List<String> nameList = new ArrayList<>();
        List<Object> valueList = new ArrayList<>();
        List<Object> keyValueList = new ArrayList<>();
        List<Map<String, Object>> list = null;
        LinkedHashMap<String, Object> linkedHashMap = null;
        String extSql;
        String suffixStr = null;
        // queryFy 为true时那么查询当前法院的信息
        if (queryFy) {
            ActionContext ctx = ActionContext.getContext();
            HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
            UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
            suffixStr = String.format(" and court_code = '%s' ", user_.getCourtCode());
        }
        System.out.println(chart + "\n\n\n\n");
        try {
            switch (chart) {
                case "lawyer_level":
                    list = umsDataMapper.lawyerLevelData(courtNo);
                    break;
                case "rank":
                    list = umsDataMapper.rankData(courtNo);
                    break;
                case "gender":
                    list = umsDataMapper.genderData(suffixStr,courtNo);
                    break;
                case "age":
                    linkedHashMap = umsDataMapper.ageData(ChartDataEnum.AGE.getList(), suffixStr,courtNo);
                    break;
                case "political":
                    linkedHashMap = umsDataMapper.politicalData(ChartDataEnum.POLITICAL.getList(),courtNo);
                    break;
                case "educationBackground":
                    linkedHashMap = umsDataMapper.educationBackgroundData(ChartDataEnum.EDUCATION.getList(), suffixStr,courtNo);
                    break;
                case "degree":
                    linkedHashMap = umsDataMapper.degreeData(ChartDataEnum.DEGREE.getList(),courtNo);
                    break;
                case "law_position":
                    linkedHashMap = umsDataMapper.personnelClassificationChart(null,courtNo);
                    break;
                case "law_position1":
                    extSql = "and court_std_no IN (SELECT court_std_no from ums_court_full WHERE court_gradation =0)";
                    linkedHashMap = umsDataMapper.personnelClassificationChart(extSql,courtNo);
                    break;
                case "law_position2":
                    extSql = "and court_std_no IN (SELECT court_std_no from ums_court_full WHERE court_gradation =1 and court_std_no !=3185)";
                    linkedHashMap = umsDataMapper.personnelClassificationChart(extSql,courtNo);
                    break;
                case "law_position3":
                    extSql = "and court_std_no IN (SELECT court_std_no from ums_court_full WHERE court_gradation =2 or court_std_no =3185)";
                    linkedHashMap = umsDataMapper.personnelClassificationChart(extSql,courtNo);
                    break;
                case "law_position4":

                    if (StringUtils.hasText(suffixStr)) {
                        extSql = suffixStr;

                    } else {
                        extSql = null;
                    }
                    linkedHashMap = umsDataMapper.lawPositionChart(extSql,courtNo);
                    //查找员额法官
//                    0 = {LinkedHashMap$Entry@9186} "审判辅助人员" -> "79"
//                    1 = {LinkedHashMap$Entry@9187} "院领导法官" -> "6"
//                    2 = {LinkedHashMap$Entry@9188} "一线法官" -> "169"
//                    3 = {LinkedHashMap$Entry@9189} "司法行政部门法官" -> "55"
//                    4 = {LinkedHashMap$Entry@9190} "司法行政人员" -> "73"

                    //TODO 本院统计异常，更换数据库后，sql无异常，但是类型转换错误，mysql中没这个问题
                    BigDecimal n1 = new BigDecimal((Long) linkedHashMap.getOrDefault("院领导法官", 0));
                    BigDecimal n2 = new BigDecimal((Long) linkedHashMap.getOrDefault("一线法官", 0));
                    BigDecimal n3 = new BigDecimal((Long) linkedHashMap.getOrDefault("司法行政部门法官", 0));
                    BigDecimal nal = n1.add(n2).add(n3);

                    Integer yefg = umsDataMapper.yefgChart(suffixStr,courtNo);
                    BigDecimal fyefg = nal.subtract(new BigDecimal(yefg));
                    linkedHashMap.remove("院领导法官");
                    linkedHashMap.remove("一线法官");
                    linkedHashMap.remove("司法行政部门法官");
                    linkedHashMap.put("员额法官", yefg);
                    linkedHashMap.put("非员额法官", fyefg);
                    break;
                case "courtInstitution":
                    Integer courtNo = null;
                    if (!queryAll) {
                        ActionContext ctx = ActionContext.getContext();
                        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
                        UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                        courtNo = user_.getCourtNo();
                    }
                    linkedHashMap = queryForInstitution(courtNo);
                    break;
            }
            if (list != null) {
                list.forEach(stringObjectMap -> {
                    nameList.add(objToString(stringObjectMap.get("name")));
                    valueList.add(objToString(stringObjectMap.get("value")));
                    keyValueList.add(objToString(stringObjectMap.get("keyValue")));
                });

            } else if (linkedHashMap != null) {

                Stream<Map.Entry<String, Object>> stream = linkedHashMap.entrySet().stream();

                stream.forEach(stringObjectEntry -> {
                    nameList.add(stringObjectEntry.getKey());
                    valueList.add(stringObjectEntry.getValue());
                    keyValueList.add(stringObjectEntry.getKey());
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        returnMap.put("name", nameList);
        returnMap.put("value", valueList);
        returnMap.put("keyValue", keyValueList);
        returnMap.put("key", chart);
        return "json";
    }


    @Action("getUserData")
    public String getUserData() {
        Map<String, Object> returnMap = new HashMap<>();
        data = returnMap;
        int total = 0;
        List<Map<String, Object>> dataInfo = new ArrayList<>();
        returnMap.put("results", total);
        returnMap.put("rows", dataInfo);
        if (!StringUtils.hasText(chart) || !StringUtils.hasText(keyValue)) {
            return "json";
        }
        String condition = null;
        String extSql = null;
        try {
            switch (chart) {
                case "lawyer_level":
                    total = umsQueryDataMapper.countLawyerLevelData(keyValue, start, limit);
                    dataInfo = umsQueryDataMapper.getLawyerLevelData(keyValue, start, limit);
                    break;
                case "rank":
                    total = umsQueryDataMapper.countRankData(keyValue, start, limit);
                    dataInfo = umsQueryDataMapper.getRankData(keyValue, start, limit);
                    break;
                case "gender":
                    total = umsQueryDataMapper.countGenderData(keyValue, start, limit);
                    dataInfo = umsQueryDataMapper.getGenderData(keyValue, start, limit);
                    break;
                case "age":
                    List<Map<String, Object>> list1 = ChartDataEnum.AGE.getList();
                    for (Map<String, Object> map : list1) {
                        String name = objToString(map.get("name"));
                        String condition_ = objToString(map.get("condition"));
                        if (keyValue.trim().equalsIgnoreCase(name.trim())) {
                            condition = condition_;
                            break;
                        }
                    }
                    if (StringUtils.hasText(condition)) {
                        total = umsQueryDataMapper.countAgeData(condition, start, limit);
                        dataInfo = umsQueryDataMapper.getAgeData(condition, start, limit);
                    }
                    break;
                case "political":
                    List<Map<String, Object>> list2 = ChartDataEnum.POLITICAL.getList();
                    for (Map<String, Object> map : list2) {
                        String name = objToString(map.get("name"));
                        String condition_ = objToString(map.get("condition"));
                        if (keyValue.trim().equalsIgnoreCase(name.trim())) {
                            condition = condition_;
                            break;
                        }
                    }
                    if (StringUtils.hasText(condition)) {
                        total = umsQueryDataMapper.countPoliticalData(condition, start, limit);
                        dataInfo = umsQueryDataMapper.getPoliticalData(condition, start, limit);
                    }
                    break;
                case "educationBackground":
                    List<Map<String, Object>> list3 = ChartDataEnum.EDUCATION.getList();
                    for (Map<String, Object> map : list3) {
                        String name = objToString(map.get("name"));
                        String condition_ = objToString(map.get("condition"));
                        if (keyValue.trim().equalsIgnoreCase(name.trim())) {
                            condition = condition_;
                            break;
                        }
                    }
                    if (StringUtils.hasText(condition)) {
                        total = umsQueryDataMapper.countEducationBackgroundData(condition, start, limit);
                        dataInfo = umsQueryDataMapper.getEducationBackgroundData(condition, start, limit);
                    }
                    break;
                case "degree":
                    List<Map<String, Object>> list4 = ChartDataEnum.DEGREE.getList();
                    for (Map<String, Object> map : list4) {
                        String name = objToString(map.get("name"));
                        String condition_ = objToString(map.get("condition"));
                        if (keyValue.trim().equalsIgnoreCase(name.trim())) {
                            condition = condition_;
                            break;
                        }
                    }
                    if (StringUtils.hasText(condition)) {
                        total = umsQueryDataMapper.countDegreeData(condition, start, limit);
                        dataInfo = umsQueryDataMapper.getDegreeData(condition, start, limit);
                    }
                    break;
                case "law_position":
                    List<Map<String, Object>> list5 = ChartDataEnum.PERSONNEL.getList();
                    for (Map<String, Object> map : list5) {
                        String name = objToString(map.get("name"));
                        String condition_ = objToString(map.get("condition"));
                        if (keyValue.trim().equalsIgnoreCase(name.trim())) {
                            condition = condition_;
                            break;
                        }
                    }
                    total = umsQueryDataMapper.countLawPositionData(condition, null);
                    dataInfo = umsQueryDataMapper.getLawPositionData(condition, start, limit, null);
                    break;
                case "law_position1":
                    List<Map<String, Object>> list6 = ChartDataEnum.PERSONNEL.getList();
                    for (Map<String, Object> map : list6) {
                        String name = objToString(map.get("name"));
                        String condition_ = objToString(map.get("condition"));
                        if (keyValue.trim().equalsIgnoreCase(name.trim())) {
                            condition = condition_;
                            break;
                        }
                    }
                    extSql = "and court_std_no IN (SELECT court_std_no from ums_court_full WHERE court_gradation =0)";
                    total = umsQueryDataMapper.countLawPositionData(condition, extSql);
                    dataInfo = umsQueryDataMapper.getLawPositionData(condition, start, limit, extSql);
                    break;
                case "law_position2":
                    List<Map<String, Object>> list7 = ChartDataEnum.PERSONNEL.getList();
                    for (Map<String, Object> map : list7) {
                        String name = objToString(map.get("name"));
                        String condition_ = objToString(map.get("condition"));
                        if (keyValue.trim().equalsIgnoreCase(name.trim())) {
                            condition = condition_;
                            break;
                        }
                    }
                    extSql = "and court_std_no IN (SELECT court_std_no from ums_court_full WHERE court_gradation =1 and court_std_no !=3185)";
                    total = umsQueryDataMapper.countLawPositionData(condition, extSql);
                    dataInfo = umsQueryDataMapper.getLawPositionData(condition, start, limit, extSql);
                    break;
                case "law_position3":
                    List<Map<String, Object>> list8 = ChartDataEnum.PERSONNEL.getList();
                    for (Map<String, Object> map : list8) {
                        String name = objToString(map.get("name"));
                        String condition_ = objToString(map.get("condition"));
                        if (keyValue.trim().equalsIgnoreCase(name.trim())) {
                            condition = condition_;
                            break;
                        }
                    }
                    extSql = "and court_std_no IN (SELECT court_std_no from ums_court_full WHERE court_gradation =2 or court_std_no =3185)";
                    total = umsQueryDataMapper.countLawPositionData(condition, extSql);
                    dataInfo = umsQueryDataMapper.getLawPositionData(condition, start, limit, extSql);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        returnMap.put("results", total);
        returnMap.put("rows", dataInfo);
        return "json";
    }


    /*
    员额法院权限 目前只有超级管理员可以出所有的统计
     */
    @Action("chartAuth")
    public String chartAuth() {

        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
        UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        String courtCode = user_.getCourtCode();
        //法院不一样 要看权限
        boolean flag = false;
        for (UmsRole role : authorityService.userRoles(user_.getId())) {
            if (role.getRoleName().contains("超级管理员")) {
                flag = true;
            }
        }
        Map<String, Object> res = new HashMap<>();
        Map<String, String> court = courtConstant.getCourt();
        res.put("auth", flag);
        res.put("courtCode", courtCode);
        res.put("courtName", court.get(courtCode));
        data = res;
        if (flag) {
            res.put("court", court);
        }
        return "json";
    }

    /*
    员额法官统计
     */
    @Action("judgeChartData")
    public String judgeChartData() {
        Map<String, Object> returnMap = new HashMap<>();

        List<String> nameList = new ArrayList<>();
        List<Object> valueList = new ArrayList<>();
        List<Object> keyValueList = new ArrayList<>();

        //查询结果
        List<Map<String, Object>> list = null;
        LinkedHashMap<String, Object> linkedHashMap = null;

        try {
            //解码
            byte[] decode1 = SimpleDecodeParameter.decode(fydm);
            fydm = new String(decode1,"utf-8");

            byte[] decode2 = SimpleDecodeParameter.decode(chart);
            chart = new String(decode2,"utf-8");

            ActionContext ctx = ActionContext.getContext();
            HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
            UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
            String courtCode = user_.getCourtCode();
            if (!StringUtils.hasText(fydm)) {
                fydm = courtCode;
            } else {
                if (!courtCode.equalsIgnoreCase(fydm)) {
                    //法院不一样 要看权限
                    boolean flag = false;
                    for (UmsRole role : authorityService.userRoles(user_.getId())) {
                        if (role.getRoleName().contains("超级管理员")) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        return "json";
                    }
                }
            }

            if (!StringUtils.hasText(fydm)) {
                return "json";
            }
            String suffixStr;
            String fydms = " ( ";
            if ("all".equals(fydm)) {
                //获取权限可以访问的法院
                List<Map<String, Object>> clist = courtFullService.listByAuthUserID(user_.getId());
                //自身权限可以查询的法院列表
                List<String> courtList;
                boolean flag = false;
                for (Map<String, Object> rlist : clist) {
                    //超级管理员 显示高院 和 所有中级法院
                    if (rlist.get("rcourt_no").toString().equals("9999")) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    List<UmsCourtFull> umsCourtFulls = courtFullService.selectByListAll();
                    courtList = umsCourtFulls.stream().map(UmsCourtFull::getCourtCode).collect(Collectors.toList());
                } else {
                    courtList = clist.stream().map(stringObjectMap -> {
                        Object court_code = stringObjectMap.get("court_code");
                        if (court_code == null) {
                            return "";
                        }
                        return court_code.toString();
                    }).collect(Collectors.toList());
                }
                for (int i = 0; i < courtList.size(); i++) {
                    String court = courtList.get(i);
                    if (i == (courtList.size() - 1)) {
                        fydms += "'" + court + "'";
                    } else {
                        fydms += "'" + court + "', ";
                    }
                }
                fydms += " ) ";
            }else{

            }

            if (StringUtils.hasText(fydms) && !" ( ".equals(fydms)) {
                fydm = fydms;
            } else {
                fydm = "('" + fydm + "')";
            }

            suffixStr = " AND court_code in " + fydm + " AND (yefg = 1 OR EXISTS (SELECT 1 FROM ums_level_info b WHERE a.user_id = b.user_id AND a.court_no = b.court_no AND b.is_yefg = 1 AND b.n_present_info = 1)) ";

            //进行统计
            switch (chart) {
                case "base":
                    linkedHashMap = umsDataMapper.courtJudgeBaseInfo(ChartDataEnum.BASE_Q.getList(), fydm);
                    //顺便把文本也返回回去
                    Map<String, String> court = courtConstant.getCourt();
                    linkedHashMap.put("court", court.get(fydm));
                    //基本信息直接返回
                    data = linkedHashMap;
                    return "json";
                case "lawyer_level":
                    list = umsDataMapper.courtJudgeLawyerLevelData(fydm);
                    break;
                case "educationBackground":
                    linkedHashMap = umsDataMapper.educationBackgroundData(ChartDataEnum.EDUCATION.getList(), suffixStr,courtNo);
                    break;
                case "age":
                    linkedHashMap = umsDataMapper.ageData(ChartDataEnum.AGE_N.getList(), suffixStr,courtNo);
                    break;
                case "department":
                    list = umsDataMapper.courtJudgeDepartmentData(fydm);
                    break;
            }

            if (list != null) {
                list.forEach(stringObjectMap -> {
                    nameList.add(objToString(stringObjectMap.get("name")));
                    valueList.add(objToString(stringObjectMap.get("value")));
                    keyValueList.add(objToString(stringObjectMap.get("keyValue")));
                });
            } else if (linkedHashMap != null) {
                Stream<Map.Entry<String, Object>> stream = linkedHashMap.entrySet().stream();
                stream.forEach(stringObjectEntry -> {
                    nameList.add(stringObjectEntry.getKey());
                    valueList.add(stringObjectEntry.getValue());
                    keyValueList.add(stringObjectEntry.getKey());
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        returnMap.put("name", nameList);
        returnMap.put("value", valueList);
        returnMap.put("keyValue", keyValueList);
        returnMap.put("key", chart);
        data = returnMap;

        return "json";
    }

    /*
     员额法官反查
     */
    @Action("judgeFc")
    public String judgeFc() {
        if (!StringUtils.hasText(fydm) || !StringUtils.hasText(chart) || !StringUtils.hasText(keyValue)) {
            return "json";
        }

        if (page != null) {
            start = (page - 1) * limit;
        }

        //一定要分页
        if (start == null || limit == null) {
            return "json";
        }
        //分页
        List<Map<String, Object>> dataInfo = null;
        int total = 0;

        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
        UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        String courtCode = user_.getCourtCode();
        if (!StringUtils.hasText(fydm)) {
            fydm = courtCode;
        } else {
            if (!courtCode.equalsIgnoreCase(fydm)) {
                //法院不一样 要看权限
                boolean flag = false;
                for (UmsRole role : authorityService.userRoles(user_.getId())) {
                    if (role.getRoleName().contains("超级管理员")) {
                        flag = true;
                    }
                }
                if (!flag) {
                    return "json";
                }
            }
        }

        if (!StringUtils.hasText(fydm)) {
            return "json";
        }
        String fydms = " ( ";
        if ("all".equals(fydm)) {
            //获取权限可以访问的法院
            List<Map<String, Object>> clist = courtFullService.listByAuthUserID(user_.getId());
            //自身权限可以查询的法院列表
            List<String> courtList;
            boolean flag = false;
            for (Map<String, Object> rlist : clist) {
                //超级管理员 显示高院 和 所有中级法院
                if (rlist.get("rcourt_no").toString().equals("9999")) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                List<UmsCourtFull> umsCourtFulls = courtFullService.selectByListAll();
                courtList = umsCourtFulls.stream().map(UmsCourtFull::getCourtCode).collect(Collectors.toList());
            } else {
                courtList = clist.stream().map(stringObjectMap -> {
                    Object court_code = stringObjectMap.get("court_code");
                    if (court_code == null) {
                        return "";
                    }
                    return court_code.toString();
                }).collect(Collectors.toList());
            }
            for (int i = 0; i < courtList.size(); i++) {
                String court = courtList.get(i);
                if (i == (courtList.size() - 1)) {
                    fydms += "'" + court + "'";
                } else {
                    fydms += "'" + court + "', ";
                }
            }
            fydms += " ) ";
        }

        if (StringUtils.hasText(fydms) && !" ( ".equals(fydms)) {
            fydm = fydms;
        } else {
            fydm = "('" + fydm + "')";
        }



        String condition = String.format(" court_code in %s and yefg = 1 ", fydm);
        try {
            switch (chart) {
                case "lawyer_level":
                    total = umsQueryDataMapper.countCourtLevelData(condition, keyValue);
                    dataInfo = umsQueryDataMapper.getCourtLawyerLevelData(condition, keyValue, start, limit);
                    break;
                case "educationBackground":
                    List<Map<String, Object>> list3 = ChartDataEnum.EDUCATION.getList();
                    for (Map<String, Object> map : list3) {
                        String name = objToString(map.get("name"));
                        String condition_ = objToString(map.get("condition"));
                        if (keyValue.trim().equalsIgnoreCase(name.trim())) {
                            condition += " and " + condition_;
                            break;
                        }
                    }
                    total = umsQueryDataMapper.countEducationBackgroundData(condition, start, limit);
                    dataInfo = umsQueryDataMapper.getCourtEducationBackgroundData(condition, start, limit);
                    break;
                case "age":
                    String vd = null;
                    List<Map<String, Object>> list1 = ChartDataEnum.AGE_N.getList();
                    for (Map<String, Object> map : list1) {
                        String name = objToString(map.get("name"));
                        String condition_ = objToString(map.get("condition"));
                        if (keyValue.trim().equalsIgnoreCase(name.trim())) {
                            vd = condition_;
                            break;
                        }
                    }
                    total = umsQueryDataMapper.countCourtAgeData(condition, vd);
                    dataInfo = umsQueryDataMapper.getCourtAgeData(condition, vd, start, limit);
                    break;
                case "department":
                    total = umsQueryDataMapper.countCountCourtLevelData(condition, keyValue);
                    dataInfo = umsQueryDataMapper.getCourtJudgeDepartmentData(condition, keyValue, start, limit);
                    break;
                case "base":
                    condition = String.format(" court_code in %s ", fydm);
                    List<Map<String, Object>> list4 = ChartDataEnum.BASE_Q.getList();
                    for (Map<String, Object> map : list4) {
                        String name = objToString(map.get("name"));
                        String condition_ = objToString(map.get("condition"));
                        if (keyValue.trim().equalsIgnoreCase(name.trim())) {
                            condition += " and " + condition_;
                            break;
                        }
                    }

                    total = umsQueryDataMapper.countCourtJudgeBaseInfo(condition);
                    dataInfo = umsQueryDataMapper.getCourtJudgeBaseInfo(condition, start, limit);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> re = new HashMap<>();
        re.put("code", 0);
        re.put("count", total);
        re.put("data", dataInfo);

        data = re;
        return "json";

    }


    //法院编制统计
    private LinkedHashMap<String, Object> queryForInstitution(Integer courtNo) {
        LinkedHashMap<String, Object> returnMap = new LinkedHashMap<>();


        List<Integer> collect = new ArrayList<>();
        if (courtNo == null) {

            UmsCourtFullCriteria example = new UmsCourtFullCriteria();
            example.createCriteria().andCourtNoIsNotNull();
            List<UmsCourtFull> umsCourtFulls = umsCourtFullService.selectByExample(example);
            collect = umsCourtFulls.stream().map(UmsCourtFull::getCourtNo).collect(Collectors.toList());

        }

        //组织机构管理填写的编制数
        UmsInstitutionPersonRecordCriteria example = new UmsInstitutionPersonRecordCriteria();
        UmsInstitutionPersonRecordCriteria.Criteria criteria = example.createCriteria();
        criteria.andDeptNoIsNull();
        if (courtNo != null) {
            criteria.andCourtNoEqualTo(courtNo);
        } else {
            criteria.andCourtNoIn(collect);
        }
        List<UmsInstitutionPersonRecord> umsInstitutionPersonRecords = umsInstitutionPersonRecordService.selectByExample(example);

        int allbzs = 0;
        for (UmsInstitutionPersonRecord umsInstitutionPersonRecord : umsInstitutionPersonRecords) {

            Integer dfbzs = umsInstitutionPersonRecord.getDfbzs();
            Integer zybzs = umsInstitutionPersonRecord.getZybzs();
            if (dfbzs == null) {
                dfbzs = 0;
            }
            if (zybzs == null) {
                zybzs = 0;
            }
            allbzs += (zybzs + dfbzs);
        }
        returnMap.put("编制数", allbzs);

        //中央编制人员实有人数
        UmsUserInfoViewCriteria userExample = new UmsUserInfoViewCriteria();
        UmsUserInfoViewCriteria.Criteria c = userExample.createCriteria();
        String orgCode = "1";
        List<Integer> codeList = UserInfoViewAction.orgCodeMap.get(orgCode);
        List<Integer> codeList2 = new ArrayList<>();
        codeList.forEach(integer -> {
            codeList2.add(integer);
        });
        c.andPreparationIn(codeList2);
        c.andIsValidEqualTo(1).andUserTypeEqualTo(1).andLeaveReasonIsNull();
        if (courtNo != null) {
            c.andCourtNoEqualTo(courtNo);
        } else {
            c.andCourtNoIn(collect);
        }
        int zybzxys = userInfoViewService.count(userExample);

        //地方编制人员实有人数
        orgCode = "2";
        codeList = UserInfoViewAction.orgCodeMap.get(orgCode);
        codeList2.clear();
        codeList.forEach(integer -> {
            codeList2.add(integer);
        });
        userExample.clear();
        UmsUserInfoViewCriteria.Criteria d = userExample.createCriteria();
        d.orPreparationIn(codeList2);
        d.andIsValidEqualTo(1).andUserTypeEqualTo(1).andLeaveReasonIsNull();
        if (courtNo != null) {
            d.andCourtNoEqualTo(courtNo);
        } else {
            d.andCourtNoIn(collect);
        }
        int dfbzxys = userInfoViewService.count(userExample);
        returnMap.put("编制实有数", zybzxys + dfbzxys);

        //计算人民陪审员数量
        userExample.clear();
        UmsUserInfoViewCriteria.Criteria e = userExample.createCriteria();
        e.andIsValidEqualTo(1).andUserTypeEqualTo(3);
        if (courtNo != null) {
            e.andCourtNoEqualTo(courtNo);
        } else {
            e.andCourtNoIn(collect);
        }
        int rmpsy = userInfoViewService.count(userExample);
        returnMap.put("人民陪审员数量", rmpsy);

        //聘用人员数量
        userExample.clear();
        UmsUserInfoViewCriteria.Criteria f = userExample.createCriteria();
        f.andIsValidEqualTo(1).andUserTypeEqualTo(2);
        if (courtNo != null) {
            f.andCourtNoEqualTo(courtNo);
        } else {
            f.andCourtNoIn(collect);
        }
        int pyry = userInfoViewService.count(userExample);
        returnMap.put("聘用人员数量", pyry);

        //机构数量
        UmsDepartmentCriteria departmentExample = new UmsDepartmentCriteria();
        UmsDepartmentCriteria.Criteria g = departmentExample.createCriteria();
        g.andIsValidEqualTo(1);
        if (courtNo != null) {
            g.andCourtNoEqualTo(courtNo);
        } else {
            g.andCourtNoIn(collect);
        }
        int jgsl = umsDepartmentService.countByExample(departmentExample);
        returnMap.put("机构数", jgsl);

        //党组织数
        UmsPartyOrganizationCriteria umsPartyOrgExample = new UmsPartyOrganizationCriteria();
        UmsPartyOrganizationCriteria.Criteria h = umsPartyOrgExample.createCriteria();
        if (courtNo != null) {
            h.andCourtNoEqualTo(courtNo);
        } else {
            h.andCourtNoIn(collect);
        }
        h.andIsBuildEqualTo("1");

        int dzz = umsPartyOrganizationService.countByExample(umsPartyOrgExample);
        returnMap.put("党组织", dzz);

        //党员数
        userExample.clear();
        UmsUserInfoViewCriteria.Criteria i = userExample.createCriteria();
        i.andIsValidEqualTo(1).andUserTypeEqualTo(1).andLeaveReasonIsNull();
        if (courtNo != null) {
            i.andCourtNoEqualTo(courtNo);
        } else {
            i.andCourtNoIn(collect);
        }
        i.andPoliticalEqualTo(1); // 1是中共党员
        int dys = userInfoViewService.count(userExample);
        returnMap.put("党员数", dys);

        return returnMap;
    }

    public boolean isQueryFy() {
        return queryFy;
    }

    public void setQueryFy(boolean queryFy) {
        this.queryFy = queryFy;
    }

    private String objToString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public String getChart() {
        return chart;
    }

    public void setChart(String chart) {
        this.chart = chart;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }


    public boolean isQueryAll() {
        return queryAll;
    }

    public void setQueryAll(boolean queryAll) {
        this.queryAll = queryAll;
    }

    public String getFydm() {
        return fydm;
    }

    public void setFydm(String fydm) {
        this.fydm = fydm;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }
}
