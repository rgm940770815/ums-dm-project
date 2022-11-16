/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.statistics;

import cn.net.withub.ums.action.config.verify.AuthUtils;
import cn.net.withub.ums.action.config.verify.PageUtil;
import cn.net.withub.ums.action.userinfo.generateCommonExcel;
import cn.net.withub.ums.auth.AuthorityHelper;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.UmsCodeMapper;
import cn.net.withub.ums.dao.XtptBmDepartementMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.*;
import cn.net.withub.ums.service.statistics.StatisticsService;
import cn.net.withub.ums.util.DateUtil;
import cn.net.withub.ums.util.StringTools;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.xssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static cn.net.withub.ums.util.StringTools.isNullOrEmpty;
import cn.net.withub.ums.util.SimpleDecodeParameter;
/**
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/chart")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"}),
        @Result(name = "stream", type = "stream",
                params = {"inputName", "inputStream", "contentDisposition", "attachment;filename=${filename}",})
})
public class ChartAction {

    @Autowired
    private UmsAuthorityService authorityService;

    @Autowired
    AuthUtils authUtils;

    @Autowired
    XtptBmDepartementMapper xtptBmDepartementMapper;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private UmsUserInfoService umsUserInfoService;

    @Autowired
    private UmsCourtFullService courtFullService;

    @Autowired
    private UmsUserInfoViewService umsUserInfoViewService;

    @Autowired
    private XtptBmDepartementService xtptBmDepartementService;

    @Autowired
    private UmsCodeMapper codeMapper;

    @Autowired
    private UmsCodeTypeService umsCodeTypeService;

    @Autowired
    private UmsCodeService umsCodeService;

    @Autowired
    private UmsExternalCompanyInfoService externalCompanyInfoService;

    @Autowired
    private UmsExtEnterSrcTypeService enterSrcTypeService;

    @Autowired
    private UmsCourtFullService umsCourtFullService;

    @Autowired
    private UmsAttachedTableService attachedTableService;

    @Autowired
    private UmsCustomStatisticsService umsCustomStatisticsService;

    @Autowired
    private AuthorityHelper authorityHelper;

    @Autowired
    private generateCommonExcel generateCommonExcel;

    @Autowired
    SqlSession sqlSession;

    private String chart;

    private Object data;

    private Integer courtStdNo;
    private String courtCode;

    private Integer list1Id;
    private String list2Id;
    private String selectData;

    private Integer start = 0;
    private Integer limit = 20;

    private String fullname;
    private Integer gender;
    private Integer administrationPosition;
    private Integer rank;
    private Date enterDateStart;
    private Date enterDateEnd;
    private String typeStr;
    private Integer gradation;
    private String preparation;
    private Integer userType;

    private Integer courtLevel;//法院级别

    private String leftColumn;
    private String topColumn;
    private Integer typeId_left = -1;
    private Integer typeId_top = -1;
    private String fydmList;
    private String leftCodeNeed;
    private String topCodeNeed;

    private String userFullName;
    private String courtNoText;

    private QueryEntity queryEntity;

    private Map sixTableMap = null;
    private String otherParam = null;

    private String title;
    private Integer type;
    private Integer id;

    public QueryEntity getQueryEntity() {
        return queryEntity;
    }

    public void setQueryEntity(QueryEntity queryEntity) {
        this.queryEntity = queryEntity;
    }

    public String getCourtNoText() {
        return courtNoText;
    }

    public void setCourtNoText(String courtNoText) {
        this.courtNoText = courtNoText;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    //以前的统计图表有问题 设置不解码
    public boolean noDecode = false;

    public boolean isNoDecode() {
        return noDecode;
    }

    public void setNoDecode(boolean noDecode) {
        this.noDecode = noDecode;
    }

    @Action("statChartData")
    public String statChart() {
        String extSql = "";
        switch (chart) {
            case "position":
                data = statisticsService.positionChart(null);
                break;
            case "court_user":
                data = statisticsService.courtUserChart(null);
                break;
            case "user_gender":
                data = statisticsService.userGenderChart(null);
                break;
            case "legal_job":
                data = statisticsService.legalJobChart(null);
                break;
            case "lawyer_level":
                data = statisticsService.lawyerLevelChart(null);
                break;
            case "admin_job":
                data = statisticsService.adminJobChart(null);
                break;
            case "law_position":
                data = statisticsService.lawPositionChart(null);
                break;
            case "law_position1":
                extSql = "and court_std_no IN (SELECT court_std_no from ums_court_full WHERE court_gradation =0)";
                data = statisticsService.lawPositionChart(extSql);
                break;
            case "law_position2":
                extSql = "and court_std_no IN (SELECT court_std_no from ums_court_full WHERE court_gradation =1 and court_std_no !=3185)";
                data = statisticsService.lawPositionChart(extSql);
                break;
            case "law_position3":
                extSql = "and court_std_no IN (SELECT court_std_no from ums_court_full WHERE court_gradation =2 or court_std_no =3185)";
                data = statisticsService.lawPositionChart(extSql);
                break;
        }
        return "json";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getChart() {
        return chart;
    }

    public void setChart(String chart) {
        this.chart = chart;
    }

    public Integer getCourtStdNo() {
        return courtStdNo;
    }

    public void setCourtStdNo(Integer courtStdNo) {
        this.courtStdNo = courtStdNo;
    }

    public Integer getList1Id() {
        return list1Id;
    }

    public void setList1Id(Integer list1Id) {
        this.list1Id = list1Id;
    }

    public String getList2Id() {
        return list2Id;
    }

    public void setList2Id(String list2Id) {
        this.list2Id = list2Id;
    }

    public String getSelectData() {
        return selectData;
    }

    public void setSelectData(String selectData) {
        this.selectData = selectData;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAdministrationPosition() {
        return administrationPosition;
    }

    public void setAdministrationPosition(Integer administrationPosition) {
        this.administrationPosition = administrationPosition;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Date getEnterDateStart() {
        return enterDateStart;
    }

    public void setEnterDateStart(Date enterDateStart) {
        this.enterDateStart = enterDateStart;
    }

    public Date getEnterDateEnd() {
        return enterDateEnd;
    }

    public void setEnterDateEnd(Date enterDateEnd) {
        this.enterDateEnd = enterDateEnd;
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

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public Integer getGradation() {
        return gradation;
    }

    public void setGradation(Integer gradation) {
        this.gradation = gradation;
    }

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

    public String getTopColumn() {
        return topColumn;
    }

    public void setTopColumn(String topColumn) {
        this.topColumn = topColumn;
    }

    public String getLeftColumn() {
        return leftColumn;
    }

    public void setLeftColumn(String leftColumn) {
        this.leftColumn = leftColumn;
    }

    public StatisticsService getStatisticsService() {
        return statisticsService;
    }

    public void setStatisticsService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public Integer getTypeId_top() {
        return typeId_top;
    }

    public void setTypeId_top(Integer typeId_top) {
        this.typeId_top = typeId_top;
    }

    public Integer getTypeId_left() {
        return typeId_left;
    }

    public void setTypeId_left(Integer typeId_left) {
        this.typeId_left = typeId_left;
    }

    public UmsUserInfoService getUmsUserInfoService() {
        return umsUserInfoService;
    }

    public void setUmsUserInfoService(UmsUserInfoService umsUserInfoService) {
        this.umsUserInfoService = umsUserInfoService;
    }

    public String getFydmList() {
        return fydmList;
    }

    public void setFydmList(String fydmList) {
        this.fydmList = fydmList;
    }

    public String getLeftCodeNeed() {
        return leftCodeNeed;
    }

    public void setLeftCodeNeed(String leftCodeNeed) {
        this.leftCodeNeed = leftCodeNeed;
    }

    public String getTopCodeNeed() {
        return topCodeNeed;
    }

    public void setTopCodeNeed(String topCodeNeed) {
        this.topCodeNeed = topCodeNeed;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Action("statData")
    public String statData() {
        Map<String, Object> param = new HashMap<>();
        if (courtLevel != null)
            param.put("courtLevel", courtLevel);
        if (courtCode != null && !"".equals(courtCode))
            param.put("courtCode", courtCode);
        if (preparation != null && !"".equals(preparation))
            param.put("preparation", preparation);
        List<UmsUserInfo> userInfos = umsUserInfoService.selectAll(param);

        LinkedHashMap<String, Integer> allMap = new LinkedHashMap<>();
        Map returnMap = new HashMap<>();
        List<LinkedHashMap<String, Integer>> list = new ArrayList<>();

        String[] l1 = new String[]{};
        String[] typel;
        List<XtptBmDepartement> deptList = null;
        try {
            if (typeStr.equals("fss_01_4") || typeStr.equals("fss_01_2")) {
                deptList = xtptBmDepartementService.selectDistinctAllUser(param);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map codeMap = null;
        switch (typeStr) {
            case "fss_01_1":
                codeMap = getCodeArrayMap(16);
                l1 = (String[]) codeMap.get("codeArray");
                returnMap.put("leftArray", codeMap.get("codeNameArray"));
                allMap = statData(userInfos, "-1");
                break;
            case "fss_01_2":
                codeMap = getCodeArrayMap(16);
                l1 = (String[]) codeMap.get("codeArray");
                returnMap.put("leftArray", codeMap.get("codeNameArray"));
                allMap = statData3(userInfos, "-1", deptList);
                break;
            case "fss_01_3":
                codeMap = getCodeArrayMap(15);
                l1 = (String[]) codeMap.get("codeArray");
                returnMap.put("leftArray", codeMap.get("codeNameArray"));
                allMap = statData(userInfos, "-1");
                break;
            case "fss_01_4":
                codeMap = getCodeArrayMap(15);
                l1 = (String[]) codeMap.get("codeArray");
                returnMap.put("leftArray", codeMap.get("codeNameArray"));
                allMap = statData3(userInfos, "-1", deptList);
                break;
            case "fss_01_5":
                l1 = new String[]{"000", "b1", "b2", "18", "13B", "159", "gs", "b3", "169", "pc", "43", "fdc"
                        , "071", "hs", "hshi", "ly", "b4",
                        "049", "019", "009", "400", "209", "01D", "jg", "190", "01A", "544", "998", "239", "189", "401"};
                allMap = statData(userInfos, "-1");
                break;
            case "fss_01_6":
                l1 = new String[]{"3", "0", "1", "2"};
                returnMap.put("leftArray", getLeft6());
                allMap = statData2(userInfos, "-1");
                break;
        }
        int xtptBmNum;
        if (typeStr.equals("fss_01_4") || typeStr.equals("fss_01_2")) {
            list = getFss_01_4_biaotou(list, deptList);
        } else if (typeStr.equals("fss_01_6")) {
            list = getFss_01_6_biaotou(list);
        }
        list.add(allMap);
        int index = 0;
        for (String s : l1) {
            LinkedHashMap<String, Integer> bmap1 = new LinkedHashMap<>();
            LinkedHashMap<String, Integer> bmaptemp = new LinkedHashMap<>();
            typel = new String[]{};
            switch (s) {
                case "a1":
                    typel = new String[]{"10", "11", "12", "13"};
                    break;
                case "a2":
                    typel = new String[]{"14", "15"};
                    break;
                case "a3":
                    typel = new String[]{"16", "17"};
                    break;
                case "a4":
                    typel = new String[]{"35", "65", "36", "66"};
                    break;
                case "b1":
                    typel = new String[]{"089", "090", "091", "079"};
                    break;
                case "b2":
                    typel = new String[]{"099", "109", "11C", "129", "149", "150"};
                    break;
                case "b3":
                    typel = new String[]{"067", "068", "069"};
                    break;
                case "b4":
                    typel = new String[]{"301", "302", "303", "304", "305", "306", "307", "308", "309", "310", "311", "312"};
                    break;
                default:
                    if (typeStr.equals("fss_01_1") || typeStr.equals("fss_01_3") || typeStr.equals("fss_01_5")) {
                        bmap1 = statData(userInfos, s);
                    } else if (typeStr.equals("fss_01_4") || typeStr.equals("fss_01_2")) {
                        bmap1 = statData3(userInfos, s, deptList);
                    } else {
                        bmap1 = statData2(userInfos, s);
                    }
                    break;
            }
            for (String s1 : typel) {
                if (typeStr.equals("fss_01_1") || typeStr.equals("fss_01_3") || typeStr.equals("fss_01_5")) {
                    bmaptemp = statData(userInfos, s1);
                } else {
                    bmaptemp = statData2(userInfos, s1);
                }
                if (bmap1.size() == 0) {
                    bmap1 = bmaptemp;
                } else {
                    bmap1 = add(bmaptemp, bmap1);
                }
            }

            list.add(bmap1);
            index++;
        }
        if ("fss_01_6".equals(typeStr)) {
            returnMap.put("list", list);
        } else if ("fss_01_4".equals(typeStr) || "fss_01_2".equals(typeStr)) {
            returnMap.put("list", getOther4(list));
        } else {
            returnMap.put("list", getOther(list));
        }
        //过滤掉表2和表4中统计结果为0的列
        if ("fss_01_2".equals(typeStr) || "fss_01_4".equals(typeStr) || "fss_01_6".equals(typeStr)) {
            returnMap = removeCount0(returnMap);
            returnMap = removeCount1(returnMap);
        }
        data = returnMap;
        return "json";
    }

    @Action("statData_new")
    public String statData_new() {
        Map<String, Object> param = new HashMap<>();
        if (courtLevel != null)
            param.put("courtLevel", courtLevel);
        if (courtCode != null && !"".equals(courtCode))
            param.put("courtCode", courtCode);
        if (preparation != null && !"".equals(preparation))
            param.put("preparation", preparation);
        List<UmsUserInfo> userInfos = umsUserInfoService.selectAll(param);

        LinkedHashMap<String, Integer> allMap;
        Map returnMap = new HashMap<>();
        List<LinkedHashMap<String, Integer>> list = new ArrayList<>();

        Map codeMap = getCodeArrayMap_new(94);//获得列

        String[] l1 = (String[]) codeMap.get("codeArray");
        returnMap.put("leftArray", codeMap.get("codeNameArray"));
        allMap = statData_new(userInfos, "-1");
        list.add(allMap);

        int maleCount_fg = 0, femaleCount_fg = 0;      //性别
        int hanCount_fg = 0, shaoShuCount_fg = 0;      //民族
        int zgdyCount_fg = 0, gqtyCount_fg = 0, mzdpCount_fg = 0, qtCount_fg = 0;    //政治面貌
        int bsCount_fg = 0, ssCount_fg = 0, bkCount_fg = 0, dzCount_fg = 0, gzzzCount_fg = 0, czCount_fg = 0, xxCount_fg = 0;//学历
        int age35_fg = 0, age36_fg = 0, age41_fg = 0, age46_fg = 0, age51_fg = 0, age56_fg = 0, age61_fg = 0, ageCount_fg_fg = 0, ageAvg_fg = 0;
        int Count_fgAll = 0;

        int maleCount_spfzry = 0, femaleCount_spfzry = 0;      //性别
        int hanCount_spfzry = 0, shaoShuCount_spfzry = 0;      //民族
        int zgdyCount_spfzry = 0, gqtyCount_spfzry = 0, mzdpCount_spfzry = 0, qtCount_spfzry = 0;    //政治面貌
        int bsCount_spfzry = 0, ssCount_spfzry = 0, bkCount_spfzry = 0, dzCount_spfzry = 0, gzzzCount_spfzry = 0, czCount_spfzry = 0, xxCount_spfzry = 0;//学历
        int age35_spfzry = 0, age36_spfzry = 0, age41_spfzry = 0, age46_spfzry = 0, age51_spfzry = 0, age56_spfzry = 0, age61_spfzry = 0, ageCount_spfzry_spfzry = 0, ageAvg_spfzry = 0;
        int Count_spfzryAll = 0;

        for (String s : l1) {
            LinkedHashMap<String, Integer> bmap1 = statData_new(userInfos, s);
            if ("11".equals(s) || "12".equals(s) || "13".equals(s) || "14".equals(s) || "15".equals(s) || "16".equals(s) || "17".equals(s)) {
                Count_fgAll += Integer.valueOf(bmap1.get("countAll"));
                maleCount_fg += Integer.valueOf(bmap1.get("maleCount"));
                femaleCount_fg += Integer.valueOf(bmap1.get("femaleCount"));
                hanCount_fg += Integer.valueOf(bmap1.get("hanCount"));
                shaoShuCount_fg += Integer.valueOf(bmap1.get("shaoShuCount"));
                zgdyCount_fg += Integer.valueOf(bmap1.get("zgdyCount"));
                gqtyCount_fg += Integer.valueOf(bmap1.get("gqtyCount"));
                mzdpCount_fg += Integer.valueOf(bmap1.get("mzdpCount"));
                qtCount_fg += Integer.valueOf(bmap1.get("qtCount"));
                age35_fg += Integer.valueOf(bmap1.get("age35"));
                age36_fg += Integer.valueOf(bmap1.get("age36"));
                age41_fg += Integer.valueOf(bmap1.get("age41"));
                age46_fg += Integer.valueOf(bmap1.get("age46"));
                age51_fg += Integer.valueOf(bmap1.get("age51"));
                age56_fg += Integer.valueOf(bmap1.get("age56"));
                age61_fg += Integer.valueOf(bmap1.get("age61"));
                bsCount_fg += Integer.valueOf(bmap1.get("bsCount"));
                ssCount_fg += Integer.valueOf(bmap1.get("ssCount"));
                bkCount_fg += Integer.valueOf(bmap1.get("bkCount"));
                dzCount_fg += Integer.valueOf(bmap1.get("dzCount"));
                gzzzCount_fg += Integer.valueOf(bmap1.get("gzzzCount"));
                czCount_fg += Integer.valueOf(bmap1.get("czCount"));
                xxCount_fg += Integer.valueOf(bmap1.get("xxCount"));
                ageAvg_fg += Integer.valueOf(bmap1.get("ageAvg"));
            } else if ("21".equals(s) || "22".equals(s) || "23".equals(s) || "24".equals(s) || "25".equals(s) || "29".equals(s)) {
                Count_spfzryAll += Integer.valueOf(bmap1.get("countAll"));
                maleCount_spfzry += Integer.valueOf(bmap1.get("maleCount"));
                femaleCount_spfzry += Integer.valueOf(bmap1.get("femaleCount"));
                hanCount_spfzry += Integer.valueOf(bmap1.get("hanCount"));
                shaoShuCount_spfzry += Integer.valueOf(bmap1.get("shaoShuCount"));
                zgdyCount_spfzry += Integer.valueOf(bmap1.get("zgdyCount"));
                gqtyCount_spfzry += Integer.valueOf(bmap1.get("gqtyCount"));
                mzdpCount_spfzry += Integer.valueOf(bmap1.get("mzdpCount"));
                qtCount_spfzry += Integer.valueOf(bmap1.get("qtCount"));
                age35_spfzry += Integer.valueOf(bmap1.get("age35"));
                age36_spfzry += Integer.valueOf(bmap1.get("age36"));
                age41_spfzry += Integer.valueOf(bmap1.get("age41"));
                age46_spfzry += Integer.valueOf(bmap1.get("age46"));
                age51_spfzry += Integer.valueOf(bmap1.get("age51"));
                age56_spfzry += Integer.valueOf(bmap1.get("age56"));
                age61_spfzry += Integer.valueOf(bmap1.get("age61"));
                bsCount_spfzry += Integer.valueOf(bmap1.get("bsCount"));
                ssCount_spfzry += Integer.valueOf(bmap1.get("ssCount"));
                bkCount_spfzry += Integer.valueOf(bmap1.get("bkCount"));
                dzCount_spfzry += Integer.valueOf(bmap1.get("dzCount"));
                gzzzCount_spfzry += Integer.valueOf(bmap1.get("gzzzCount"));
                czCount_spfzry += Integer.valueOf(bmap1.get("czCount"));
                xxCount_spfzry += Integer.valueOf(bmap1.get("xxCount"));
                ageAvg_spfzry += Integer.valueOf(bmap1.get("ageAvg"));
            }
            if ("法官合计".equals(s)) {
                bmap1.put("countAll", Count_fgAll);
                bmap1.put("maleCount", maleCount_fg);
                bmap1.put("femaleCount", femaleCount_fg);
                bmap1.put("hanCount", hanCount_fg);
                bmap1.put("shaoShuCount", shaoShuCount_fg);
                bmap1.put("zgdyCount", zgdyCount_fg);
                bmap1.put("gqtyCount", gqtyCount_fg);
                bmap1.put("mzdpCount", mzdpCount_fg);
                bmap1.put("qtCount", qtCount_fg);
                bmap1.put("age35", age35_fg);
                bmap1.put("age36", age36_fg);
                bmap1.put("age41", age41_fg);
                bmap1.put("age46", age46_fg);
                bmap1.put("age51", age51_fg);
                bmap1.put("age56", age56_fg);
                bmap1.put("age61", age61_fg);
                bmap1.put("bsCount", bsCount_fg);
                bmap1.put("ssCount", ssCount_fg);
                bmap1.put("bkCount", bkCount_fg);
                bmap1.put("dzCount", dzCount_fg);
                bmap1.put("gzzzCount", gzzzCount_fg);
                bmap1.put("czCount", czCount_fg);
                bmap1.put("xxCount", xxCount_fg);
                bmap1.put("ageAvg", ageAvg_fg / 7);
            } else if ("审判辅助人员合计".equals(s)) {
                bmap1.put("countAll", Count_spfzryAll);
                bmap1.put("maleCount", maleCount_spfzry);
                bmap1.put("femaleCount", femaleCount_spfzry);
                bmap1.put("hanCount", hanCount_spfzry);
                bmap1.put("shaoShuCount", shaoShuCount_spfzry);
                bmap1.put("zgdyCount", zgdyCount_spfzry);
                bmap1.put("gqtyCount", gqtyCount_spfzry);
                bmap1.put("mzdpCount", mzdpCount_spfzry);
                bmap1.put("qtCount", qtCount_spfzry);
                bmap1.put("age35", age35_spfzry);
                bmap1.put("age36", age36_spfzry);
                bmap1.put("age41", age41_spfzry);
                bmap1.put("age46", age46_spfzry);
                bmap1.put("age51", age51_spfzry);
                bmap1.put("age56", age56_spfzry);
                bmap1.put("age61", age61_spfzry);
                bmap1.put("bsCount", bsCount_spfzry);
                bmap1.put("ssCount", ssCount_spfzry);
                bmap1.put("bkCount", bkCount_spfzry);
                bmap1.put("dzCount", dzCount_spfzry);
                bmap1.put("gzzzCount", gzzzCount_spfzry);
                bmap1.put("czCount", czCount_spfzry);
                bmap1.put("xxCount", xxCount_spfzry);
                bmap1.put("ageAvg", ageAvg_spfzry / 6);
            }
            list.add(bmap1);
        }

        returnMap.put("list", list);

        //过滤掉表2和表4中统计结果为0的列

        data = returnMap;
        return "json";
    }

    private List getLeft6() {
        List<Map> leftList = new ArrayList<>();
        Map map = new HashMap<>();
        map.put("name", "合计");
        map.put("num", 1);
        leftList.add(map);
        map = new HashMap<>();
        map.put("name", "最高法院");
        map.put("num", 2);
        leftList.add(map);
        map = new HashMap<>();
        map.put("name", "高级法院");
        map.put("num", 3);
        leftList.add(map);
        map = new HashMap<>();
        map.put("name", "中级法院");
        map.put("num", 4);
        leftList.add(map);
        map = new HashMap<>();
        map.put("name", "基层法院");
        map.put("num", 5);
        leftList.add(map);
        return leftList;
    }

    private List getFss_01_6_biaotou(List list2) {
        List<String> biaotou6List = new ArrayList<>();
        List<Integer> biaotou6List_num = new ArrayList<>();
        biaotou6List.add("合计");
        biaotou6List.add("院领导");
        biaotou6List.add("刑庭");
        biaotou6List.add("民庭");
        biaotou6List.add("经济庭");
        biaotou6List.add("行政庭");
        biaotou6List.add("执行庭");
        biaotou6List.add("告申庭");
        biaotou6List.add("立案庭");
        biaotou6List.add("审监庭");
        biaotou6List.add("赔偿办");
        biaotou6List.add("知识产权");
        biaotou6List.add("房地产庭");
        biaotou6List.add("少年庭");
        biaotou6List.add("海商庭");
        biaotou6List.add("海事庭");
        biaotou6List.add("林业厅");
        biaotou6List.add("人民法庭");
        biaotou6List.add("研究室");
        biaotou6List.add("政治部处科");
        biaotou6List.add("办公厅室");
        biaotou6List.add("计财局处");
        biaotou6List.add("法警队");
        biaotou6List.add("教育培训机构");
        biaotou6List.add("机关管理局处");
        biaotou6List.add("纪检处监察室");
        biaotou6List.add("老干部局处科");
        biaotou6List.add("其他业务部门");
        biaotou6List.add("其他行政后勤部门");
        biaotou6List.add("其他");
        for (int i = 0; i < biaotou6List.size(); i++) {
            biaotou6List_num.add(i + 1);
        }
        list2.add(biaotou6List);
        list2.add(biaotou6List_num);
        return list2;
    }


   /* @Action("statData6")
    public String statData6()
    {
        Map<String,Object> param = new HashMap<>();
        if(courtLevel != null)
            param.put("courtLevel",courtLevel);
        if(courtCode != null && !"".equals(courtCode))
            param.put("courtCode",courtCode);
        if(preparation != null && !"".equals(preparation))
            param.put("preparation",preparation);
        //查询出哪些部门下面有人
        List<XtptBmDepartement> deptList =xtptBmDepartementService.selectDeptWithPerson(param);
        List<Map> resultMap = umsUserInfoService.selectPersonGroupBydept(param);
        List<LinkedHashMap<String, Integer>> list = new ArrayList<>();
        list = getFss_01_4_biaotou(list,deptList);
        String [] level = new String[]{"3","0","1","2"};
        LinkedHashMap<String, Integer> allMap = new LinkedHashMap<>();
            allMap = getCount6Map(deptList,resultMap,level);
        list.add(allMap);
        return null;
    }
*/
    /*private LinkedHashMap<String,Integer> getCount6Map(List<XtptBmDepartement> deptList, List<Map> resultList,String [] level)
    {
        int[] count = new int[deptList.size()];
        int countAll = 0,countOther=0;
        for (String l:level)
        {
            LinkedHashMap<String, Integer> returnData = new LinkedHashMap<>();
            int countLevel = 0;
            int countIndex = 0;
            for (XtptBmDepartement dept:deptList)
            {
                for(int i=0;i<resultList.size();i++)
                {
                    if(resultList.get(i).get("level").equals(l) && resultList.get(i).get("orgCode").equals(dept.getOrgCode()))
                    {
                        count[countIndex] += (int)resultList.get(i).get("count_");
                        countLevel += (int)resultList.get(i).get("count_");
                    }
                }
                countIndex++;
            }
            returnData.put("countAll",countLevel);
            countIndex = 0;
            for (XtptBmDepartement dept:deptList)
            {
                for(int i=0;i<resultList.size();i++)
                {
                    if(resultList.get(i).get("level").equals(l) && resultList.get(i).get("orgCode").equals(dept.getOrgCode()))
                    {
                        returnData.put("count"+countIndex,(int)resultList.get(i).get("count_"));
                    }
                }
                countIndex++;
            }

        }

        return null;
    }*/

    private LinkedHashMap<String, Integer> add(LinkedHashMap<String, Integer> map1, LinkedHashMap<String, Integer> map2) {
        try {
            for (String key : map1.keySet()) {
                if ("ageAvg".equals(key)) {
                    int avg1 = map1.get("ageAvg");
                    int avg2 = map2.get("ageAvg");
                    int allpeople1 = map1.get("countAll");
                    int allpeople2 = map2.get("countAll");
                    int allAge = avg1 * allpeople1 + avg2 * allpeople2;
                    int allpeople = allpeople1 + allpeople2;
                    int avg;
                    if (allpeople == 0 || allAge == 0)
                        map2.put(key, 0);
                    else {
                        avg = (avg1 * allpeople1 + avg2 * allpeople2) / allpeople;
                        map2.put(key, avg);
                    }

                } else {
                    map2.put(key, (map1.get(key) + map2.get(key)));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map2;
    }

    private List<LinkedHashMap<String, Integer>> getOther(List<LinkedHashMap<String, Integer>> list) {
        LinkedHashMap<String, Integer> allMap = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> otherMap = new LinkedHashMap<>();
        allMap = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            map = list.get(i);
            if (i == 1) {
                for (String key : map.keySet()) {
                    otherMap.put(key, allMap.get(key) - map.get(key));
                }
            } else {
                for (String key : map.keySet()) {
                    otherMap.put(key, otherMap.get(key) - map.get(key));
                }
            }
        }
        //其他里的年龄统计 不准确
        try {
            if (otherMap.get("ageAvg") != null) {
                if (otherMap.get("countAll") != 0) {
                    int ageAvg = (otherMap.get("age35") * 30 + otherMap.get("age36") * 38 +
                            otherMap.get("age41") * 43 + otherMap.get("age46") * 48 +
                            otherMap.get("age51") * 53 + otherMap.get("age56") * 58 +
                            otherMap.get("age61") * 65) / otherMap.get("countAll");
                    otherMap.put("ageAvg", ageAvg);
                } else
                    otherMap.put("ageAvg", -1);
            }
            list.add(otherMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //统计其他或未知，并放入list中
    private List<LinkedHashMap<String, Integer>> getOther4(List<LinkedHashMap<String, Integer>> list) {
        LinkedHashMap<String, Integer> allMap = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> otherMap = new LinkedHashMap<>();
        allMap = list.get(2);
        for (int i = 3; i < list.size(); i++) {
            map = list.get(i);
            if (i == 3) {
                for (String key : map.keySet()) {
                    otherMap.put(key, allMap.get(key) - map.get(key));
                }
            } else {
                for (String key : map.keySet()) {
                    otherMap.put(key, otherMap.get(key) - map.get(key));
                }
            }
        }
        list.add(otherMap);
        return list;
    }

    //统计其他或未知，并放入list中
    private List<LinkedHashMap<String, QueryEntity>> getOther4ForQuery(List<LinkedHashMap<String, QueryEntity>> list) {
        LinkedHashMap<String, QueryEntity> allMap = new LinkedHashMap<>();
        LinkedHashMap<String, QueryEntity> map = new LinkedHashMap<>();
        LinkedHashMap<String, QueryEntity> otherMap = new LinkedHashMap<>();
        allMap = list.get(2);
        for (int i = 3; i < list.size(); i++) {
            map = list.get(i);
            if (i == 3) {
                for (String key : map.keySet()) {
                    QueryEntity queryEntity = allMap.get(key);
                    QueryEntity queryEntity1 = map.get(key);
                    QueryEntity clone = queryEntity.clone();
                    clone.setCount(queryEntity.getCount() - queryEntity1.getCount());
                    clone.setLeftValue("other");
                    otherMap.put(key, clone);
                }
            } else {
                for (String key : map.keySet()) {
                    QueryEntity queryEntity = otherMap.get(key);
                    QueryEntity queryEntity1 = map.get(key);
                    QueryEntity clone = queryEntity.clone();
                    clone.setCount(queryEntity.getCount() - queryEntity1.getCount());
                    clone.setLeftValue("other");
                    otherMap.put(key, clone);
                }
            }
        }
        list.add(otherMap);
        return list;
    }

    private LinkedHashMap<String, Integer> statData(List<UmsUserInfo> data, String type) {
        LinkedHashMap<String, Integer> returnData = new LinkedHashMap<>();

        int maleCount = 0, femaleCount = 0;      //性别
        int hanCount = 0, shaoShuCount = 0;      //民族
        int dyCount = 0, tyCount = 0, qtCount = 0;    //政治面貌
        int bsCount = 0, ssCount = 0, yjsCount = 0, dxCount = 0, dzCount = 0, gzCount = 0, czCount = 0, xxCount = 0;//学历
        int zsCount = 0; //有证书
        int age35 = 0, age36 = 0, age41 = 0, age46 = 0, age51 = 0, age56 = 0, age61 = 0, ageCount = 0, ageAvg = 0;
        int countAll = 0, i = 0;

        for (UmsUserInfo ui : data) {
            String type1 = "-1";
            switch (typeStr) {
                case "fss_01_1":
                    type1 = ui.getLawPosition() + "";
                    break;
                case "fss_01_3":
                    type1 = ui.getAdministrationPosition() + "";
                    break;
                case "fss_01_5":
                    type1 = ui.getDeptOrgCode();

                    break;
            }
            if ((type.equals(type1) || type.equals("-1"))) {
                countAll++;
                //性别
                if (1 == ui.getGender()) {
                    maleCount++;
                } else {
                    femaleCount++;
                }

                //民族
                if (ui.getNation().equals("1")) {
                    hanCount++;
                } else {
                    shaoShuCount++;
                }

                //政治面貌
                if (ui.getPolitical() == null) {
                    qtCount++;
                } else if (!ui.getPolitical().equals("1") && !ui.getPolitical().equals("3")) {
                    qtCount++;
                } else if (ui.getPolitical().equals("1")) {
                    dyCount++;
                } else if (ui.getPolitical().equals("3")) {
                    tyCount++;
                }

                //学历信息
                if (ui.getEducationBackground() == null) {
                    xxCount++;
                } else if (ui.getEducationBackground().equals("1") || ui.getEducationBackground().equals("4")) {
                    bsCount++;
                } else if (ui.getEducationBackground().equals("2") || ui.getEducationBackground().equals("5")) {
                    ssCount++;
                } else if (ui.getEducationBackground().equals("3") || ui.getEducationBackground().equals("6") || ui.getEducationBackground().equals("9")) {
                    yjsCount++;
                } else if (ui.getEducationBackground().equals("11") || ui.getEducationBackground().equals("12") || ui.getEducationBackground()
                        .equals("13") || ui.getEducationBackground().equals("19") || ui.getEducationBackground().equals("20")) {
                    dxCount++;
                } else if (ui.getEducationBackground().equals("21") || ui.getEducationBackground().equals("22") || ui.getEducationBackground().equals("29")) {
                    dzCount++;
                } else if (ui.getEducationBackground().equals("31") || ui.getEducationBackground().equals("32") || ui.getEducationBackground().equals("39") ||
                        ui.getEducationBackground().equals("41") || ui.getEducationBackground().equals("49") || ui.getEducationBackground().equals("51") ||
                        ui.getEducationBackground().equals("52") || ui.getEducationBackground().equals("53") || ui.getEducationBackground().equals("59")) {
                    gzCount++;
                } else {
                    czCount++;
                }

                //专业证书
                if (ui.getProCert() != null) {
                    zsCount++;
                }
                //年龄 用userNO代替
                int uiAge = 0;
                if (ui.getUserNo() != null) {
                    uiAge = ui.getUserNo();
                    if (uiAge <= 35) {
                        age35++;
                    } else if (uiAge > 35 && uiAge <= 40) {
                        age36++;
                    } else if (uiAge > 40 && uiAge <= 45) {
                        age41++;
                    } else if (uiAge > 45 && uiAge <= 50) {
                        age46++;
                    } else if (uiAge > 50 && uiAge <= 55) {
                        age51++;
                    } else if (uiAge > 55 && uiAge <= 60) {
                        age56++;
                    } else {
                        age61++;
                    }
                }

                ageCount += uiAge;
            }
            i++;
            if (i == 1 && "M23".equals(ui.getCourtCode())) {
                //将李春燕算作院长
                if (typeStr.equals("fss_01_1") && type.equals("1")) {
                    countAll++;
                    femaleCount++;
                    hanCount++;
                    dyCount++;
                    age51++;
                    dxCount++;
                }
                if (typeStr.equals("fss_01_1") && type.equals("7")) {
                    countAll--;
                    femaleCount--;
                    hanCount--;
                    dyCount--;
                    age51--;
                    dxCount--;
                }
            }
        }

        if (countAll != 0) {
            ageAvg = ageCount / countAll;
        }

        returnData.put("countAll", countAll);

        returnData.put("maleCount", maleCount);
        returnData.put("femaleCount", femaleCount);

        returnData.put("hanCount", hanCount);
        returnData.put("shaoShuCount", shaoShuCount);

        returnData.put("dyCount", dyCount);
        returnData.put("tyCount", tyCount);
        returnData.put("qtCount", qtCount);

        returnData.put("age35", age35);
        returnData.put("age36", age36);
        returnData.put("age41", age41);
        returnData.put("age46", age46);
        returnData.put("age51", age51);
        returnData.put("age56", age56);
        returnData.put("age61", age61);

        returnData.put("bsCount", bsCount);
        returnData.put("ssCount", ssCount);
        returnData.put("yjsCount", yjsCount);
        returnData.put("dxCount", dxCount);
        returnData.put("dzCount", dzCount);
        returnData.put("gzCount", gzCount);
        returnData.put("czCount", czCount);
        returnData.put("xxCount", xxCount);

        returnData.put("zsCount", zsCount);

        returnData.put("ageAvg", ageAvg);


        return returnData;
    }

    private LinkedHashMap<String, Integer> statData_new(List<UmsUserInfo> data, String type) {

        LinkedHashMap<String, Integer> returnData = new LinkedHashMap<>();

        int maleCount = 0, femaleCount = 0;      //性别
        int hanCount = 0, shaoShuCount = 0;      //民族
        int zgdyCount = 0, gqtyCount = 0, mzdpCount = 0, qtCount = 0;    //政治面貌
        int bsCount = 0, ssCount = 0, bkCount = 0, dzCount = 0, gzzzCount = 0, czCount = 0, xxCount = 0;//学历
        int age35 = 0, age36 = 0, age41 = 0, age46 = 0, age51 = 0, age56 = 0, age61 = 0, ageCount = 0, ageAvg = 0;
        int countAll = 0, i = 0;

        for (UmsUserInfo ui : data) {

            String type1 = "-1";

//            type1 = ui.getAdministrationPosition() + "";
            type1 = ui.getPersonnelClassification() + "";

            if ((type.equals(type1) || type.equals("-1"))) {
                countAll++;
                //性别
                if (1 == ui.getGender()) {
                    maleCount++;
                } else {
                    femaleCount++;
                }

                //民族
                if (ui.getNation().equals("1")) {
                    hanCount++;
                } else {
                    shaoShuCount++;
                }

                //政治面貌
                if (ui.getPolitical() == null) {
                    qtCount++;
                } else if (ui.getPolitical().equals("4") || ui.getPolitical().equals("5") || ui.getPolitical().equals("6") || ui.getPolitical().equals("7") || ui.getPolitical().equals("8") || ui.getPolitical().equals("9") || ui.getPolitical().equals("10") || ui.getPolitical().equals("11")) {
                    mzdpCount++;
                } else if (ui.getPolitical().equals("1")) {
                    zgdyCount++;
                } else if (ui.getPolitical().equals("3")) {
                    gqtyCount++;
                }

                //学历信息
                if (ui.getEducationBackground() == null) {
                    xxCount++;
                } else if (ui.getEducationBackground().equals("1") || ui.getEducationBackground().equals("4")) {
                    bsCount++;
                } else if (ui.getEducationBackground().equals("2") || ui.getEducationBackground().equals("3") || ui.getEducationBackground().equals("5") || ui.getEducationBackground().equals("6") || ui.getEducationBackground().equals("9")) {
                    ssCount++;
                } else if (ui.getEducationBackground().equals("11") || ui.getEducationBackground().equals("12") || ui.getEducationBackground()
                        .equals("13") || ui.getEducationBackground().equals("19") || ui.getEducationBackground().equals("20")) {
                    bkCount++;
                } else if (ui.getEducationBackground().equals("21") || ui.getEducationBackground().equals("22") || ui.getEducationBackground().equals("29")) {
                    dzCount++;
                } else if (ui.getEducationBackground().equals("31") || ui.getEducationBackground().equals("32") || ui.getEducationBackground().equals("39") ||
                        ui.getEducationBackground().equals("41") || ui.getEducationBackground().equals("49") || ui.getEducationBackground().equals("51") ||
                        ui.getEducationBackground().equals("52") || ui.getEducationBackground().equals("53") || ui.getEducationBackground().equals("59")) {
                    gzzzCount++;
                } else {
                    czCount++;
                }

                //年龄 用userNO代替
                int uiAge = 0;
                if (ui.getUserNo() != null) {
                    uiAge = ui.getUserNo();
                    if (uiAge <= 35) {
                        age35++;
                    } else if (uiAge > 35 && uiAge <= 40) {
                        age36++;
                    } else if (uiAge > 40 && uiAge <= 45) {
                        age41++;
                    } else if (uiAge > 45 && uiAge <= 50) {
                        age46++;
                    } else if (uiAge > 50 && uiAge <= 55) {
                        age51++;
                    } else if (uiAge > 55 && uiAge <= 60) {
                        age56++;
                    } else {
                        age61++;
                    }
                }

                ageCount += uiAge;

            }


            i++;
        }

        if (countAll != 0) {
            ageAvg = ageCount / countAll;
        }

        returnData.put("countAll", countAll);

        returnData.put("maleCount", maleCount);
        returnData.put("femaleCount", femaleCount);

        returnData.put("hanCount", hanCount);
        returnData.put("shaoShuCount", shaoShuCount);

        returnData.put("zgdyCount", zgdyCount);
        returnData.put("gqtyCount", gqtyCount);
        returnData.put("mzdpCount", mzdpCount);
        returnData.put("qtCount", qtCount);

        returnData.put("age35", age35);
        returnData.put("age36", age36);
        returnData.put("age41", age41);
        returnData.put("age46", age46);
        returnData.put("age51", age51);
        returnData.put("age56", age56);
        returnData.put("age61", age61);

        returnData.put("bsCount", bsCount);
        returnData.put("ssCount", ssCount);
        returnData.put("bkCount", bkCount);
        returnData.put("dzCount", dzCount);
        returnData.put("gzzzCount", gzzzCount);
        returnData.put("czCount", czCount);
        returnData.put("xxCount", xxCount);

        returnData.put("ageAvg", ageAvg);


        return returnData;
    }

    private LinkedHashMap<String, Integer> statData2(List<UmsUserInfo> data, String type1) {
        LinkedHashMap<String, Integer> returnData = new LinkedHashMap<>();

        String[] l1 = new String[]{"000", "b1", "b2", "18", "13B", "159", "gs", "b3", "169", "pc", "43", "fdc"
                , "071", "hs", "hshi", "ly", "b4",
                "049", "019", "009", "400", "209", "01D", "jg", "190", "01A", "544", "998"};

        int i = 0, countAll = 0, count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, count7 = 0, count8 = 0, count9 = 0, count10 = 0, count11 = 0, count12 = 0, count13 = 0, count14 = 0, count15 = 0, count16
                = 0, count17 = 0, count18 = 0, count19 = 0, count20 = 0, count21 = 0, count22 = 0, count23 = 0, count24 = 0, count25 = 0, count26 = 0, count27 = 0, count28 = 0, countOther = 0;

        for (UmsUserInfo ui : data) {
            String type = "-1";
            switch (typeStr) {
                case "fss_01_2":
                    type = ui.getLawPosition() + "";

                    break;
                case "fss_01_4":
                    type = ui.getAdministrationPosition() + "";

                    break;
                case "fss_01_6":
                    type = ui.getLevel() + "";
                    //法院级别  用level代替
                    break;
            }
            if ((type.equals(type1) || type1.equals("-1"))) {
                countAll++;
                String[] typel;
                if (ui.getDeptOrgCode() != null) {
                    switch (ui.getDeptOrgCode()) {
                        case "000":
                            count1++;
                            break;
                        case "18":
                            count4++;
                            break;
                        case "13B":
                            count5++;
                            break;
                        case "159":
                            count6++;
                            break;
                        case "169":
                            count9++;
                            break;
                        case "43":
                            count11++;
                            break;
                        case "071":
                            count13++;
                            break;
                        case "049":
                            count18++;
                            break;
                        case "019":
                            count19++;
                            break;
                        case "009":
                            count20++;
                            break;
                        case "400":
                            count21++;
                            break;
                        case "209":
                            count22++;
                            break;
                        case "01D":
                            count23++;
                            break;
                        case "190":
                            count25++;
                            break;
                        case "01A":
                            count26++;
                            break;
                        case "544":
                            count27++;
                            break;
                        case "998":
                            count28++;
                            break;
                    }
                }
                for (String s : l1) {
                    switch (s) {
                        case "b1":
                            typel = new String[]{"079", "089", "090", "091"};
                            for (String s1 : typel) {
                                if (s1.equals(ui.getDeptOrgCode())) {
                                    count2++;
                                }
                            }
                            break;
                        case "b2":
                            typel = new String[]{"099", "109", "11C", "129", "149", "150"};
                            for (String s1 : typel) {
                                if (s1.equals(ui.getDeptOrgCode())) {
                                    count3++;
                                }
                            }
                            break;
                        case "b3":
                            typel = new String[]{"067", "068", "069"};
                            for (String s1 : typel) {
                                if (s1.equals(ui.getDeptOrgCode())) {
                                    count8++;
                                }
                            }
                            break;
                        case "b4":
                            typel = new String[]{"301", "302", "303", "304", "305", "306", "307", "308", "309", "310", "311", "312"};
                            for (String s1 : typel) {
                                if (s1.equals(ui.getDeptOrgCode())) {
                                    count17++;
                                }
                            }
                            break;
                    }
                }
                countOther = countAll - count1 - count2 - count3 - count4 - count5 - count6 - count7 - count8 - count9 - count10 - count11 - count12 - count13
                        - count14 - count15 - count16 - count17 - count18 - count19 - count20 - count21 - count22 - count23 - count24 - count25 - count26 - count27
                        - count28;
            }
            i++;
            if (i == 1 && "M23".equals(ui.getCourtCode())) {
                //将李春燕算作院长
                if (typeStr.equals("fss_01_2") && type1.equals("1")) {
                    countAll++;
                    count1++;
                }
                if (typeStr.equals("fss_01_2") && type1.equals("7")) {
                    countAll--;
                    count1--;
                }
            }

        }
        returnData.put("countAll", countAll);
        returnData.put("count1", count1);
        returnData.put("count2", count2);
        returnData.put("count3", count3);
        returnData.put("count4", count4);
        returnData.put("count5", count5);
        returnData.put("count6", count6);
        returnData.put("count7", count7);
        returnData.put("count8", count8);
        returnData.put("count9", count9);
        returnData.put("count10", count10);
        returnData.put("count11", count11);
        returnData.put("count12", count12);
        returnData.put("count13", count13);
        returnData.put("count14", count14);
        returnData.put("count15", count15);
        returnData.put("count16", count16);
        returnData.put("count17", count17);
        returnData.put("count18", count18);
        returnData.put("count19", count19);
        returnData.put("count20", count20);
        returnData.put("count21", count21);
        returnData.put("count22", count22);
        returnData.put("count23", count23);
        returnData.put("count24", count24);
        returnData.put("count25", count25);
        returnData.put("count26", count26);
        returnData.put("count27", count27);
        returnData.put("count28", count28);
        returnData.put("countOther", countOther);

        return returnData;
    }

    //获取表头
    private List getFss_01_4_biaotou(List list2, List<XtptBmDepartement> deptList) {
        List<String> l2 = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        l2.add("合计");
        int i = 1;
        for (XtptBmDepartement d : deptList) {
            l2.add(d.getDeptName());
            intList.add(i);
            i++;
        }
        l2.add("其他");
        intList.add(intList.size() + 1);
        intList.add(intList.size() + 1);
        list2.add(l2);
        list2.add(intList);
        return list2;
    }

    private LinkedHashMap<String, Integer> statData3(List<UmsUserInfo> data, String type1, List<XtptBmDepartement> deptList) {
        LinkedHashMap<String, Integer> returnData = new LinkedHashMap<>();
        int[] count = new int[deptList.size()];
        int countAll = 0, countOther = 0;
        try {
            for (UmsUserInfo ui : data) {
                String type = "-1";
                switch (typeStr) {
                    case "fss_01_2":
                        type = ui.getLawPosition() + "";

                        break;
                    case "fss_01_4":
                        type = ui.getAdministrationPosition() + "";

                        break;
                    case "fss_01_6":
                        type = ui.getLevel() + "";
                        //法院级别  用level代替
                        break;
                }
                if (type.equals(type1) || "-1".equals(type1)) {
                    countAll++;
                    for (int j = 0; j < deptList.size(); j++) {
                        if (deptList.get(j).getOrgCode().equals(ui.getDeptOrgCode())) {
                            count[j]++;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int countAllOther = 0;
        returnData.put("countAll", countAll);
        for (int i = 0; i < count.length; i++) {
            returnData.put("count" + i, count[i]);
            countAllOther += count[i];
        }
        countOther = countAll - countAllOther;
        returnData.put("countOther", countOther);

        return returnData;
    }

    @Action("getList2")
    public String getList2() {
        // 获取配置的字段信息
        list2();
        // 根据传来的条件,进行组装
        List<Map<String, Object>> list_return = new ArrayList<>();
        Map<String, Object> map_return;
        LinkedHashMap<String, Object> list_mapAllList_0 = mapAllList.get(0);
        LinkedHashMap<String, Object> list_mapAllList_1 = mapAllList.get(1);
        if (list1Id == -2) {
            for (Map.Entry<String, Object> entry_mapAll_0 : list_mapAllList_0.entrySet()) {
                map_return = new HashMap<>();
                String key_mapAll_0 = entry_mapAll_0.getKey();
                Object value_mapAll_0 = entry_mapAll_0.getValue();
                for (Map.Entry<String, Object> entry_mapAll_1 : list_mapAllList_1.entrySet()) {
                    String key_mapAll_1 = entry_mapAll_1.getKey();
                    Object value_mapAll_1 = entry_mapAll_1.getValue();
                    if (key_mapAll_0.equals(key_mapAll_1)) {
                        map_return.put("type_id", value_mapAll_1);
                        break;
                    }
                }
                // 如果有子表,添加标记
                if (null != list_mapAllList_1.get(key_mapAll_0 + "_child_sql")) {
                    map_return.put("child_sql", list_mapAllList_1.get(key_mapAll_0 + "_child_sql"));
                }
                map_return.put("id", key_mapAll_0);
                map_return.put("text", value_mapAll_0);
                list_return.add(map_return);
            }
            for (int i = 0; i < mapListn.size(); i++) {
                List list_ = (List) mapListn.get(i);
                Map<String, Object> map_0 = (Map<String, Object>) list_.get(0);
                Map<String, Object> map_1 = (Map<String, Object>) list_.get(1);
                for (Map.Entry<String, Object> entry_map_0 : map_0.entrySet()) {
                    String key_mapl21_0 = entry_map_0.getKey();
                    Object value_mapl21_0 = entry_map_0.getValue();
                    map_return = new HashMap<>();
                    // 循环获取type_id
                    for (Map.Entry<String, Object> entry_map_1 : map_1.entrySet()) {
                        String key_mapl21_1 = entry_map_1.getKey();
                        Object value_mapl21_1 = entry_map_1.getValue();
                        if (key_mapl21_0.equals(key_mapl21_1)) {
                            map_return.put("type_id", value_mapl21_1);
                            break;
                        }
                    }
                    // 如果有子表,添加标记
                    if (null != map_1.get(key_mapl21_0 + "_child_sql")) {
                        map_return.put("child_sql", map_1.get(key_mapl21_0 + "_child_sql"));
                    }
                    map_return.put("id", key_mapl21_0);
                    map_return.put("text", value_mapl21_0);
                    list_return.add(map_return);
                }
            }
        } else if (list1Id == -1) {
            for (Map.Entry<String, Object> entry_mapAll_0 : list_mapAllList_0.entrySet()) {
                map_return = new HashMap<>();
                String key_mapAll_0 = entry_mapAll_0.getKey();
                Object value_mapAll_0 = entry_mapAll_0.getValue();
                for (Map.Entry<String, Object> entry_mapAll_1 : list_mapAllList_1.entrySet()) {
                    String key_mapAll_1 = entry_mapAll_1.getKey();
                    Object value_mapAll_1 = entry_mapAll_1.getValue();
                    if (key_mapAll_0.equals(key_mapAll_1)) {
                        map_return.put("type_id", value_mapAll_1);
                        break;
                    }
                }
                // 如果有子表,添加标记
                if (null != list_mapAllList_1.get(key_mapAll_0 + "_child_sql")) {
                    map_return.put("child_sql", list_mapAllList_1.get(key_mapAll_0 + "_child_sql"));
                }
                map_return.put("id", key_mapAll_0);
                map_return.put("text", value_mapAll_0);
                list_return.add(map_return);
            }
        } else {
            List list_ = (List) mapListn.get(list1Id - 1);
            Map<String, Object> map_0 = (Map<String, Object>) list_.get(0);
            Map<String, Object> map_1 = (Map<String, Object>) list_.get(1);
            for (Map.Entry<String, Object> entry_map_0 : map_0.entrySet()) {
                String key_mapl21_0 = entry_map_0.getKey();
                Object value_mapl21_0 = entry_map_0.getValue();
                map_return = new HashMap<>();
                for (Map.Entry<String, Object> entry_map_1 : map_1.entrySet()) {
                    String key_mapl21_1 = entry_map_1.getKey();
                    Object value_mapl21_1 = entry_map_1.getValue();
                    if (key_mapl21_0.equals(key_mapl21_1)) {
                        map_return.put("type_id", value_mapl21_1);
                        break;
                    }
                }
                // 如果有子表,添加标记
                if (null != map_1.get(key_mapl21_0 + "_child_sql")) {
                    map_return.put("child_sql", map_1.get(key_mapl21_0 + "_child_sql"));
                }
                map_return.put("id", key_mapl21_0);
                map_return.put("text", value_mapl21_0);
                list_return.add(map_return);
            }
        }
        Map<String, Object> map = new HashMap();
        map.put("text", "");
        map.put("children", list_return);
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);
        data = list;
        return "json";
    }

    List mapListn = new ArrayList<>();
    List<LinkedHashMap<String, Object>> mapList = new ArrayList<>();
    LinkedHashMap<String, Object> mapl2 = new LinkedHashMap<>();   //第二类
    LinkedHashMap<String, Object> mapl21 = new LinkedHashMap<>();   //第二类
    LinkedHashMap<String, Object> mapAll = new LinkedHashMap<>();   //个人基本信息
    List<LinkedHashMap<String, Object>> mapAllList = new ArrayList<>();   //个人基本信息

    //列表1选完后的对应选项，根据列表1的选项下标获取
    private void list2() {

        // 数字是 code/codeListByType 里的编码
        mapl2.put("political_report", "政治面貌");
        mapl21.put("political_report", "1013");
        mapl21.put("political_report_child_sql", "exists (select 1 from ums_political_info where a.id = ums_political_info.user_id and ums_political_info.n_political_report");
        mapl2.put("political_date", "加入日期");
        mapl21.put("political_date_child_sql", "exists (select 1 from ums_political_info where a.id = ums_political_info.user_id and ums_political_info.d_entry_date");
        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);

        mapList = new ArrayList<>();
        mapl2 = new LinkedHashMap<>();
        mapl21 = new LinkedHashMap<>();
        mapl2.put("administration_position", "行政职务");
        mapl21.put("administration_position", "15");
        mapl21.put("administration_position_child_sql", "exists (select 1 from ums_administrative_job where a.id = ums_administrative_job.user_id and ums_administrative_job.n_job_report");
        mapl2.put("former_unit", "单位名称");
        mapl21.put("former_unit_child_sql", "exists (select 1 from ums_administrative_job where a.id = ums_administrative_job.user_id and ums_administrative_job.c_unit");
        mapl2.put("leave_reason", "任免原因");
        mapl21.put("leave_reason", "19");
        mapl21.put("leave_reason_child_sql", "exists (select 1 from ums_administrative_job where a.id = ums_administrative_job.user_id and ums_administrative_job.n_assign_reason");
        mapl2.put("verify_date", "批准日期");
        mapl21.put("verify_date_child_sql", "exists (select 1 from ums_administrative_job where a.id = ums_administrative_job.user_id and ums_administrative_job.d_approval_date");
        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);

        mapList = new ArrayList<>();
        mapl2 = new LinkedHashMap<>();
        mapl21 = new LinkedHashMap<>();
        mapl2.put("position_type", "任免类别");
        mapl21.put("position_type", "18");
        mapl21.put("position_type_child_sql", "exists (select 1 from ums_legal_job where a.id = ums_legal_job.user_id and ums_legal_job.n_assign_type");
        mapl2.put("law_position_report", "法律职务");
        mapl21.put("law_position_report", "1016");
        mapl21.put("law_position_report_child_sql", "exists (select 1 from ums_legal_job where a.id = ums_legal_job.user_id and ums_legal_job.n_job_report");
        mapl2.put("law_position_date", "任免日期");
        mapl21.put("law_position_date_child_sql", "exists (select 1 from ums_legal_job where a.id = ums_legal_job.user_id and ums_legal_job.d_assign_date");
        mapl2.put("former_unit", "单位");
        mapl21.put("former_unit_child_sql", "exists (select 1 from ums_legal_job where a.id = ums_legal_job.user_id and ums_legal_job.c_unit");
        mapl2.put("leave_reason", "任免原因");
        mapl21.put("leave_reason", "19");
        mapl21.put("leave_reason_child_sql", "exists (select 1 from ums_legal_job where a.id = ums_legal_job.user_id and ums_legal_job.n_assign_reason");
        mapl2.put("verify_date", "批准日期");
        mapl21.put("verify_date_child_sql", "exists (select 1 from ums_legal_job where a.id = ums_legal_job.user_id and ums_legal_job.d_approval_date");
        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);

        mapList = new ArrayList<>();
        mapl2 = new LinkedHashMap<>();
        mapl21 = new LinkedHashMap<>();
        mapl2.put("position_type", "任免类别");
        mapl21.put("position_type", "18");
        mapl21.put("position_type_child_sql", "exists (select 1 from ums_rank_info where a.id = ums_rank_info.user_id and ums_rank_info.n_assign_type");
        mapl2.put("rank_report", "职级");
        mapl21.put("rank_report", "1017");
        mapl21.put("rank_report_child_sql", "exists (select 1 from ums_rank_info where a.id = ums_rank_info.user_id and ums_rank_info.n_rank_report");
        mapl2.put("law_position_date", "任免日期");
        mapl21.put("law_position_date_child_sql", "exists (select 1 from ums_rank_info where a.id = ums_rank_info.user_id and ums_rank_info.d_assign_date");
        mapl2.put("former_unit", "单位");
        mapl21.put("former_unit_child_sql", "exists (select 1 from ums_rank_info where a.id = ums_rank_info.user_id and ums_rank_info.c_unit");
        mapl2.put("leave_reason", "任免原因");
        mapl21.put("leave_reason", "19");
        mapl21.put("leave_reason_child_sql", "exists (select 1 from ums_rank_info where a.id = ums_rank_info.user_id and ums_rank_info.n_assign_reason");
        mapl2.put("verify_date", "批准日期");
        mapl21.put("verify_date_child_sql", "exists (select 1 from ums_rank_info where a.id = ums_rank_info.user_id and ums_rank_info.d_approval_date");
        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);

        mapList = new ArrayList<>();
        mapl2 = new LinkedHashMap<>();
        mapl21 = new LinkedHashMap<>();
        mapl2.put("position_type", "任免类别");
        mapl21.put("position_type", "18");
        mapl21.put("position_type_child_sql", "exists (select 1 from ums_parttime_position where a.id = ums_parttime_position.user_id and ums_parttime_position.n_assign_type");
        mapl2.put("law_position_date", "任免日期");
        mapl21.put("law_position_date_child_sql", "exists (select 1 from ums_parttime_position where a.id = ums_parttime_position.user_id and ums_parttime_position.d_assign_date");
        mapl2.put("former_unit", "单位");
        mapl21.put("former_unit_child_sql", "exists (select 1 from ums_parttime_position where a.id = ums_parttime_position.user_id and ums_parttime_position.c_unit");
        mapl2.put("leave_reason", "任免原因");
        mapl21.put("leave_reason", "19");
        mapl21.put("leave_reason_child_sql", "exists (select 1 from ums_parttime_position where a.id = ums_parttime_position.user_id and ums_parttime_position.n_assign_reason");
        mapl2.put("verify_date", "批准日期");
        mapl21.put("verify_date_child_sql", "exists (select 1 from ums_parttime_position where a.id = ums_parttime_position.user_id and ums_parttime_position.d_approval_date");
        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);

        mapList = new ArrayList<>();
        mapl2 = new LinkedHashMap<>();
        mapl21 = new LinkedHashMap<>();
        // 这几个没找到,所以我给屏蔽了......
//        mapl2.put("position_type", "等级类别");
//        mapl21.put("position_type", "62");
//        mapl2.put("law_position", "等级名称");
//        mapl21.put("law_position", "16");
//        mapl2.put("law_position_date", "起算日期");
//        mapl2.put("leave_reason", "变动原因");
//        mapl21.put("leave_reason", "19");
        mapl2.put("judge_level", "法官等级");
        mapl21.put("judge_level", "117");
        mapl21.put("judge_level_child_sql", "exists (select 1 from ums_level_info where a.id = ums_level_info.user_id and ums_level_info.n_present_info = 1 and ums_level_info.n_level_type = 1 and ums_level_info.judge_level");
        mapl2.put("marshal_level", "法警等级");
        mapl21.put("marshal_level", "115");
        mapl21.put("marshal_level_child_sql", "exists (select 1 from ums_level_info where a.id = ums_level_info.user_id and ums_level_info.n_present_info = 1 and ums_level_info.n_level_type = 2 and ums_level_info.marshal_level");
        mapl2.put("helper_level", "法官助理等级");
        mapl21.put("helper_level", "118");
        mapl21.put("helper_level_child_sql", "exists (select 1 from ums_level_info where a.id = ums_level_info.user_id and ums_level_info.n_present_info = 1 and ums_level_info.n_level_type = 3 and ums_level_info.helper_level");
        mapl2.put("clerk_level", "书记员等级");
        mapl21.put("clerk_level", "116");
        mapl21.put("clerk_level_child_sql", "exists (select 1 from ums_level_info where a.id = ums_level_info.user_id and ums_level_info.n_present_info = 1 and ums_level_info.n_level_type = 4 and ums_level_info.clerk_level");

        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);

        mapList = new ArrayList<>();
        mapl2 = new LinkedHashMap<>();
        mapl2 = new LinkedHashMap<>();
        mapl2.put("education_background_report", "学历");
        mapl21.put("education_background_report", "1011");
        mapl21.put("education_background_report_child_sql", "exists (select 1 from ums_education_info where a.id = ums_education_info.user_id and ums_education_info.n_education_background_report");
        mapl2.put("major", "专业");
        mapl21.put("major", "12");
        mapl21.put("major_child_sql", "exists (select 1 from ums_education_info where a.id = ums_education_info.user_id and ums_education_info.n_major");
        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);

        mapList = new ArrayList<>();
        mapl2 = new LinkedHashMap<>();
        mapl21 = new LinkedHashMap<>();
        mapl2.put("degree", "学位");
        mapl21.put("degree", "23");
        mapl21.put("degree_child_sql", "exists (select 1 from ums_degree_info where a.id = ums_degree_info.user_id and ums_degree_info.n_degree");
        mapl2.put("major", "专业");
        mapl21.put("major", "12");
        mapl21.put("major_child_sql", "exists (select 1 from ums_education_info where a.id = ums_education_info.user_id and ums_education_info.n_major");
        mapl2.put("degree_date", "获得学位日期");
        mapl21.put("degree_date_child_sql", "exists (select 1 from ums_education_info where a.id = ums_education_info.user_id and ums_education_info.d_award_date");
        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);

        mapList = new ArrayList<>();
        mapl2 = new LinkedHashMap<>();
        mapl21 = new LinkedHashMap<>();
        mapl2.put("c_dept", "所在部门");
        mapl21.put("c_dept_child_sql", "exists (select 1 from ums_resume_info where a.id = ums_resume_info.user_id and ums_resume_info.c_dept");
        mapl2.put("d_start_date", "起始日期");
        mapl21.put("d_start_date_child_sql", "exists (select 1 from ums_resume_info where a.id = ums_resume_info.user_id and ums_resume_info.d_start_date");
        mapl2.put("c_position", "职务");
        mapl21.put("c_position_child_sql", "exists (select 1 from ums_resume_info where a.id = ums_resume_info.user_id and ums_resume_info.c_position");
        mapl2.put("c_rank", "职级");
        mapl21.put("c_rank", "17");
        mapl21.put("c_rank_child_sql", "exists (select 1 from ums_resume_info where a.id = ums_resume_info.user_id and ums_resume_info.c_rank");
        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);

        mapList = new ArrayList<>();
        mapl2 = new LinkedHashMap<>();
        mapl21 = new LinkedHashMap<>();
        mapl2.put("dept_org_code", "标准部门");
        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);

        mapl21 = new LinkedHashMap<>();
        mapAll.put("court_no_text", "法院");
        mapAll.put("fullname", "姓名");
        mapAll.put("former_name", "曾用名");
        mapAll.put("gender", "性别");
        mapl21.put("gender", "3");
        mapAll.put("department", "部门");
        mapl21.put("department", "601");
        mapAll.put("hometown", "籍贯");
        mapAll.put("birthplace", "出生地");
        mapAll.put("birthday", "出生日期");
        mapAll.put("physical_condition", "健康状况");
        mapl21.put("physical_condition", "10");
        mapAll.put("marital_status", "婚姻状况");
        mapl21.put("marital_status", "6");
        mapAll.put("nation_report", "民族");
        mapl21.put("nation_report", "1005");
        mapAll.put("idcard", "身份证号");
        mapAll.put("preparation", "编制");
        mapl21.put("preparation", "9");
        mapAll.put("position_type", "职务类别");
        mapl21.put("position_type", "62");
        mapAll.put("position_type_date", "职务类别时间");
        mapAll.put("assign", "任用方式");
        mapl21.put("assign", "63");
        mapAll.put("position_nature", "岗位性质");
        mapl21.put("position_nature", "64");
        mapAll.put("personnel_classification", "人员分类");
        mapl21.put("personnel_classification", "94");
        mapAll.put("job", "工作岗位");
        mapl21.put("job", "93");
        mapAll.put("education_background_report", "学历");
        mapl21.put("education_background_report", "1011");
        mapl21.put("education_background_report_child_sql", "exists (select 1 from ums_education_info where a.id = ums_education_info.user_id and ums_education_info.n_education_background_report");
        mapAll.put("major", "专业");
        mapl21.put("major", "12");
        mapl21.put("major_child_sql", "exists (select 1 from ums_education_info where a.id = ums_education_info.user_id and ums_education_info.n_major");
        mapAll.put("degree", "学位");
        mapl21.put("degree", "23");
        mapl21.put("degree_child_sql", "exists (select 1 from ums_degree_info where a.id = ums_degree_info.user_id and ums_degree_info.n_degree");
        mapAll.put("degree_date", "获得学位日期");
        mapl21.put("degree_date_child_sql", "exists (select 1 from ums_degree_info where a.id = ums_degree_info.user_id and ums_degree_info.d_award_date");
        mapAll.put("work_date", "工作日期");
        mapAll.put("enter_date", "进院日期");
        mapAll.put("pro_cert", "专业证书");
        mapl21.put("pro_cert", "47");
        mapAll.put("pro_cert_date", "获得证书日期");
        mapAll.put("political_report", "政治面貌");
        mapl21.put("political_report", "1013");
        mapl21.put("political_report_child_sql", "exists (select 1 from ums_political_info where a.id = ums_political_info.user_id and ums_political_info.n_political_report");
        mapAll.put("political_date", "政治面貌加入日期");
        mapl21.put("political_date_child_sql", "exists (select 1 from ums_political_info where a.id = ums_political_info.user_id and ums_political_info.d_entry_date");
        mapAll.put("politic_law_work_date", "政法工作日期");
        mapAll.put("administration_position", "行政职务");
        mapl21.put("administration_position", "15");
        mapl21.put("administration_position_child_sql", "exists (select 1 from ums_administrative_job where a.id = ums_administrative_job.user_id and ums_administrative_job.n_job_report");
        mapAll.put("administration_position_date", "行政职务任职日期");
        mapl21.put("administration_position_date_child_sql", "exists (select 1 from ums_administrative_job where a.id = ums_administrative_job.user_id and ums_administrative_job.d_assign_date");
        mapAll.put("law_position_report", "法律职务");
        mapl21.put("law_position_report", "1016");
        mapl21.put("law_position_report_child_sql", "exists (select 1 from ums_legal_job where a.id = ums_legal_job.user_id and ums_legal_job.n_job_report");
        mapAll.put("law_position_date", "法律职务任职日期");
        mapl21.put("law_position_date_child_sql", "exists (select 1 from ums_legal_job where a.id = ums_legal_job.user_id and ums_legal_job.d_assign_date");
        mapAll.put("is_parttime_presiding_judge_text", "是否兼任庭长");
        mapAll.put("party_office", "党组职务");
        mapl21.put("party_office", "57");
        mapAll.put("party_office_date", "党组职务日期");
        mapAll.put("lawyer_date", "法官资格日期");
        mapAll.put("extra_seniority", "补充工龄");
        mapAll.put("deduction_seniority", "扣减工龄");
        mapAll.put("before_court_work_year", "进院前法院工作年限");
        mapAll.put("rank_report", "职级");
        mapl21.put("rank_report", "1017");
        mapl21.put("rank_report_child_sql", "exists (select 1 from ums_rank_info where a.id = ums_rank_info.user_id and ums_rank_info.n_rank_report");
        mapAll.put("rank_date", "职级日期");
        mapl21.put("rank_date_child_sql", "exists (select 1 from ums_rank_info where a.id = ums_rank_info.user_id and ums_rank_info.d_assign_date");
//        mapAll.put("level", "等级"); // 这个是在user2_add.jsp中,这字段是不是不用了?
//        mapl21.put("level", "20");
//        mapAll.put("level_date", "等级日期");
        mapAll.put("enter_way", "调入途径");
        mapl21.put("enter_way", "43");
        mapAll.put("enter_source", "调入来源");
        mapl21.put("enter_source", "44");
        mapAll.put("former_post", "原职务");
        mapl21.put("former_post", "15");
        mapAll.put("former_rank", "原职级");
        mapl21.put("former_rank", "17");
        mapAll.put("former_unit", "原单位");
        mapAll.put("verify_date", "审核日期");
        mapAll.put("leave_reason", "调出原因");
        mapl21.put("leave_reason", "45");
        mapAll.put("leave_date", "调出日期");
        mapAll.put("leave_destination", "调离去向");
        mapl21.put("leave_destination", "46");
        mapAll.put("additional_duration", "应加学制");
        mapAll.put("lawyer_cert_date", "取得法官资格证书时间");
        mapAll.put("lawyer_cert", "取得法官资格证书类型");
        mapl21.put("lawyer_cert", "86");
        mapAll.put("servant_level", "公务员级别");
        mapl21.put("servant_level", "83");
        mapl21.put("servant_level_child_sql", "exists (select 1 from ums_civil_servant_level where a.id = ums_civil_servant_level.user_id and ums_civil_servant_level.n_servant_level");
        mapAll.put("servant_level_date", "公务员级别起算日期");
        mapl21.put("servant_level_date_child_sql", "exists (select 1 from ums_civil_servant_level where a.id = ums_civil_servant_level.user_id and ums_civil_servant_level.d_start_date");
        mapl21.put("yefg", "00001");
        mapAll.put("yefg", "是否员额法官");
//        mapl21.put("yefg_child_sql", "exists (select 1 from ums_level_info where a.id = ums_level_info.user_id and ifnull(ums_level_info.is_yefg, 0)");
        mapAllList.add(mapAll);
        mapAllList.add(mapl21);
    }


    @Action("bwGetList2")
    public String bwGetlist2() {

        bwList2();

        if (list1Id == -1) {
            data = mapAllList;
        } else {
            data = mapListn.get(list1Id - 1);
        }
        return "json";

    }

    private void bwList2() {

        mapl21 = new LinkedHashMap<>();
        mapAll.put("court_no_text", "法院");
        mapAll.put("fullname", "姓名");
        mapAll.put("dept_org_code", "部门");//修改20180423
        mapl21.put("dept_org_code", "601");//修改20180423
        mapAll.put("former_name", "曾用名");
        mapAll.put("gender", "性别");
        mapl21.put("gender", "3");
        mapAll.put("nation", "民族");
        mapl21.put("nation", "5");
//        mapAll.put("department", "部门");
//        mapl21.put("department", "601");
        mapAll.put("hometown", "籍贯");
        mapAll.put("birthplace", "出生地");
        mapAll.put("birthday", "出生日期");
        mapAll.put("physical_condition", "健康状况");
        mapl21.put("physical_condition", "10");
        mapAll.put("marital_status", "婚姻状况");
        mapl21.put("marital_status", "6");

        mapAll.put("idcard", "身份证号");
        mapAllList.add(mapAll);
        mapAllList.add(mapl21);

        mapList = new ArrayList<>();
        mapl2 = new LinkedHashMap<>();
        mapl21 = new LinkedHashMap<>();
        mapl2.put("education_background", "学历");
        mapl21.put("education_background", "11");
        mapl2.put("degree", "学位");
        mapl21.put("degree", "23");

        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);

        mapList = new ArrayList<>();
        mapl2 = new LinkedHashMap<>();
        mapl21 = new LinkedHashMap<>();
        mapl2.put("uk_no", "UK编号");
        mapl2.put("work_no", "工作证编号");
        mapl2.put("fanka_no", "饭卡编号");

        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);

        mapList = new ArrayList<>();
        mapl2 = new LinkedHashMap<>();
        mapl21 = new LinkedHashMap<>();
        mapl2.put("enter_src", "编外人员来源");
        mapl21.put("enter_src", "enter_src");

        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);

        mapList = new ArrayList<>();
        mapl2 = new LinkedHashMap<>();
        mapl21 = new LinkedHashMap<>();
        mapl2.put("company_info_id", "公司名称");
        mapl21.put("company_info_id", "company_info_id");

        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);

    }

    @Action("psyGetList2")
    public String psyGetList2() {

        psyList2();

        if (list1Id == -1) {
            data = mapAllList;
        } else {
            data = mapListn.get(list1Id - 1);
        }
        return "json";

    }

    private void psyList2() {

        mapl21 = new LinkedHashMap<>();
        mapAll.put("court_no_text", "法院");
        mapAll.put("fullname", "姓名");
        mapAll.put("former_name", "曾用名");
        mapAll.put("gender", "性别");
        mapl21.put("gender", "3");
        mapAll.put("nation", "民族");
        mapl21.put("nation", "5");
//        mapAll.put("department", "部门");
//        mapl21.put("department", "601");
        mapAll.put("hometown", "籍贯");
        mapAll.put("birthplace", "出生地");
        mapAll.put("birthday", "出生日期");
        mapAll.put("physical_condition", "健康状况");
        mapl21.put("physical_condition", "10");
        mapAll.put("marital_status", "婚姻状况");
        mapl21.put("marital_status", "6");

        mapAll.put("idcard", "身份证号");
        mapAllList.add(mapAll);
        mapAllList.add(mapl21);

        mapList = new ArrayList<>();
        mapl2 = new LinkedHashMap<>();
        mapl21 = new LinkedHashMap<>();
        mapl2.put("c_ps_xlxw", "学历学位");
        mapl21.put("c_ps_xlxw", "10001");
        mapl2.put("education_background", "学历");
        mapl21.put("education_background", "11");

        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);

        mapList = new ArrayList<>();
        mapl2 = new LinkedHashMap<>();
        mapl21 = new LinkedHashMap<>();
        mapl2.put("juror_company", "单位");
        mapl2.put("juror_juror_work", "职业");
        mapl21.put("juror_juror_work", "jurorWork");

        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);

        mapList = new ArrayList<>();
        mapl2 = new LinkedHashMap<>();
        mapl21 = new LinkedHashMap<>();
        mapl2.put("begin_time", "任命日期");
        mapl2.put("end_time", "免职日期");
        mapl2.put("juror_regional", "地区分布");
        mapl21.put("juror_regional", "109");
        mapl2.put("juror_number_of_times", "届数");
        mapl2.put("juror_type_of_case", "参与案件类型");
        mapl21.put("juror_type_of_case", "110");
        mapl2.put("juror_member_state", "陪审状态");
        mapl21.put("juror_member_state", "111");


        mapList.add(mapl2);
        mapList.add(mapl21);
        mapListn.add(mapList);


    }

    @Action("getAddList")
    public String getAddList() {
        List<LinkedHashMap<String, Object>> mapList1 = new ArrayList<>();  //弹出框
        LinkedHashMap<String, Object> map2 = new LinkedHashMap<>();
        List<LinkedHashMap<String, Object>> fenzu = new ArrayList<>();

        map2.put("is null", "为空");
        map2.put("=", "等于");
//        map2.put("find_in_set", "包含");
        //find_in_set 效率低
        map2.put("in", "包含");
        map2.put("like", "类似");
        fenzu.add(map2);        //第一种
        map2 = new LinkedHashMap<>();
        map2.put(">", "大于");
        map2.put(">=", "大于等于");
        map2.put("=", "等于");
        map2.put("<", "小于");
        map2.put("<=", "小于等于");
        fenzu.add(map2);     //第二种条件

        //如果是日期，就用第二种条件
        if (list2Id.toLowerCase().contains("date") || list2Id.equals("birthday") || list2Id.equals("begin_time") || list2Id.equals("end_time")) {
            mapList1.add(fenzu.get(1));
            LinkedHashMap type = new LinkedHashMap<>();
            type.put("type", "date");
            mapList1.add(type);
            //如果是其他，就用第一种条件
        } else {
            mapList1.add(fenzu.get(0));
        }

        //如果是法院，就返回法院树
        if (list2Id.equals("court_no_text")) {
            LinkedHashMap fyList = getFyList();
            mapList1.add(fyList);
        }

        data = mapList1;
        return "json";
    }

    //法院树
    private LinkedHashMap getFyList() {
//        List<UmsCourtFull> list;
//        List<LinkedHashMap> court0 = new ArrayList<>();
//        LinkedHashMap court21;
//        if (gradation !=-1) {
//            list = courtFullService.listByGradation(gradation);
//            for (UmsCourtFull court:list){
//                court21 = new LinkedHashMap<>();
//                court21.put("text", court.getCourtStdName());
//                court21.put("id", court.getCourtCode());
//                court21.put("courtStdNo", court.getCourtStdNo());
//                court0.add(court21);
//            }
//        } else {
//            list = courtFullService.listAllCourts();
//            List<LinkedHashMap> court1 = new ArrayList<>();
//            List<LinkedHashMap> court11 = new ArrayList<>();
//            List<LinkedHashMap> court12 = new ArrayList<>();
//            List<LinkedHashMap> court13 = new ArrayList<>();
//            List<LinkedHashMap> court14 = new ArrayList<>();
//            List<LinkedHashMap> court15 = new ArrayList<>();
//            for (UmsCourtFull court : list) {
//                court21 = new LinkedHashMap<>();
//                court21.put("text", court.getCourtStdName());
//                court21.put("id", court.getCourtCode());
//                court21.put("courtStdNo", court.getCourtStdNo());
//
//                if (court.getCourtArea() == null) {
//                    court1.add(court21);
//                } else if (court.getCourtArea() == 1) {
//                    court11.add(court21);
//                } else if (court.getCourtArea() == 2) {
//                    court12.add(court21);
//                } else if (court.getCourtArea() == 3) {
//                    court13.add(court21);
//                } else if (court.getCourtArea() == 4) {
//                    court14.add(court21);
//                } else {
//                    court15.add(court21);
//                }
//            }
//            LinkedHashMap mp = new LinkedHashMap<>();
//            court0.add(court1.get(0));
//            mp.putAll(court1.get(1));
//            mp.put("children", court11);
//            court0.add(mp);
//            mp = new LinkedHashMap<>();
//            mp.putAll(court1.get(2));
//            mp.put("children", court12);
//            court0.add(mp);
//            mp = new LinkedHashMap<>();
//            mp.putAll(court1.get(3));
//            mp.put("children", court13);
//            court0.add(mp);
//            mp = new LinkedHashMap<>();
//            mp.putAll(court1.get(4));
//            mp.put("children", court14);
//            court0.add(mp);
//            mp = new LinkedHashMap<>();
//            mp.putAll(court1.get(5));
//            mp.put("children", court15);
//            court0.add(mp);
//            if (court1.size()>6) {
//                court0.add(court1.get(6));
//            }
//        }
//
//        LinkedHashMap temp = new LinkedHashMap<>();
//        temp.put(0, court0);
//        return temp;
        // 该账户的权限名称
        String roleName = null;
        UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        if (u != null) {
            UmsAuth editAuth = authorityService.getAuthByName("编辑");
            for (UmsRole role : authorityService.userRoles(u.getId())) {
                roleName = role.getRoleName();
            }
        }
        List<UmsCourtFull> list;
        LinkedHashMap court21;
        LinkedHashMap temp = new LinkedHashMap<>();
        List<LinkedHashMap> court0 = new ArrayList<>();

        if (userFullName.contains("管理员") || roleName.contains("管理员")) {
            list = courtFullService.listAllCourts();
            List<LinkedHashMap> court1 = new ArrayList<>();
            List<LinkedHashMap> court11 = new ArrayList<>();
            List<LinkedHashMap> court12 = new ArrayList<>();
            List<LinkedHashMap> court13 = new ArrayList<>();
            List<LinkedHashMap> court14 = new ArrayList<>();
            List<LinkedHashMap> court15 = new ArrayList<>();
            List<LinkedHashMap> courtSingleCourt = new ArrayList<>();

            for (UmsCourtFull court : list) {
                court21 = new LinkedHashMap<>();
                court21.put("text", court.getCourtStdName());
                court21.put("id", court.getCourtCode());
                court21.put("courtStdNo", court.getCourtStdNo());

                if (court.getCourtArea() == null) {
                    court1.add(court21);
                } else if (court.getCourtArea() == 1) {
                    court11.add(court21);
                } else if (court.getCourtArea() == 2) {
                    court12.add(court21);
                } else if (court.getCourtArea() == 3) {
                    court13.add(court21);
                } else if (court.getCourtArea() == 4) {
                    court14.add(court21);
                } else if (court.getCourtArea() == 5) {
                    court15.add(court21);
                }

                if (courtNoText.equals(court.getCourtStdName())) {
                    courtSingleCourt.add(court21);
                }
            }

            LinkedHashMap mp = new LinkedHashMap<>();
            if (userFullName.equals("超级管理员") || roleName.equals("超级管理员") || userFullName.equals("高院管理员") || roleName.equals("高院管理员") || roleName.equals("超级管理员(仅查看)")) {
                court0.add(court1.get(0));
                mp.putAll(court1.get(1));
                mp.put("children", court11);
                court0.add(mp);
                mp = new LinkedHashMap<>();
                mp.putAll(court1.get(2));
                mp.put("children", court12);
                court0.add(mp);
                mp = new LinkedHashMap<>();
                mp.putAll(court1.get(3));
                mp.put("children", court13);
                court0.add(mp);
                mp = new LinkedHashMap<>();
                mp.putAll(court1.get(4));
                mp.put("children", court14);
                court0.add(mp);
                mp = new LinkedHashMap<>();
                mp.putAll(court1.get(5));
                mp.put("children", court15);
                court0.add(mp);
                if (court1.size() > 6) {
                    court0.add(court1.get(6));
                }
            } else if (userFullName.equals("一中院管理员") || roleName.equals("一中院管理员")) {
                mp = new LinkedHashMap<>();
                mp.putAll(court1.get(1));
                mp.put("children", court11);
                court0.add(mp);
            } else if (userFullName.equals("二中院管理员") || roleName.equals("二中院管理员")) {
                mp = new LinkedHashMap<>();
                mp.putAll(court1.get(2));
                mp.put("children", court12);
                court0.add(mp);
            } else if (userFullName.equals("三中院管理员") || roleName.equals("三中院管理员")) {
                mp = new LinkedHashMap<>();
                mp.putAll(court1.get(3));
                mp.put("children", court13);
                court0.add(mp);
            } else if (userFullName.equals("四中院管理员") || roleName.equals("四中院管理员")) {
                mp = new LinkedHashMap<>();
                mp.putAll(court1.get(4));
                mp.put("children", court14);
                court0.add(mp);
            } else if (userFullName.equals("五中院管理员") || roleName.equals("五中院管理员")) {
                mp = new LinkedHashMap<>();
                mp.putAll(court1.get(5));
                mp.put("children", court15);
                court0.add(mp);
            } else {
                temp.put("0", courtSingleCourt);
                return temp;
            }

        }

        temp.put("0", court0);
        return temp;
    }

    @Action("selInfo")
    public String selInfo() {

        try {
            //解码
            byte[] decode = SimpleDecodeParameter.decode(selectData);
            selectData = new String(decode,"utf-8");
            RowBounds rowBounds = new RowBounds(start, limit);

            if (!isNullOrEmpty(fullname)) {
                selectData += " and fullname like '%" + fullname + "%' ";
            }
            if (gender != null) {
                selectData += " and gender =" + gender + " ";
            }
            if (administrationPosition != null) {
                selectData += " and administration_position =" + administrationPosition + " ";
            }
            if (rank != null) {
                selectData += " and rank =" + rank + " ";
            }
            if (enterDateStart != null) {
                selectData += " and enter_date >= '" + enterDateStart + "' ";
            }
            if (enterDateEnd != null) {
                selectData += " and enter_date <= '" + enterDateEnd + "' ";
            }

            if (userType != null && !selectData.contains("user_type")) {
                selectData += " and user_type = " + userType + " ";
            } else if (!selectData.contains("user_type")) {
                selectData += " and user_type = 1 ";
            }

            List<UmsUserInfoView> umsUserInfoViews = null;
            int total = 0;

            if (selectData.contains("enter_src") || selectData.contains("company_info_id")) {
                //编外
                try {
                    umsUserInfoViews = umsUserInfoViewService.selectCustomM(selectData, rowBounds);
                    total = umsUserInfoViewService.countCustomM(selectData);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (selectData.contains("juror_")) {
                //陪审员
                selectData = selectData.replace("juror_", "");
                try {
                    umsUserInfoViews = umsUserInfoViewService.selectCustomK(selectData, rowBounds);
                    total = umsUserInfoViewService.countCustomK(selectData);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

                try {
//                    umsUserInfoViews = umsUserInfoViewService.selectCustomN(selectData, rowBounds);
                    umsUserInfoViews = umsUserInfoViewService.selectCustomN_(selectData, rowBounds);
                    total = umsUserInfoViewService.countCustomN(selectData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            Map<String, Object> map = new HashMap<>();
            map.put("success", true);
            map.put("results", total);
            map.put("rows", umsUserInfoViews);

            data = map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

    /**
     * @param typeId
     * @return
     */
    private Map getCodeArrayMap(int typeId) {
        UmsCodeCriteria c = new UmsCodeCriteria();
        if (typeId == 15)
            c.createCriteria().andTypeIdEqualTo(typeId).andIdNotEqualTo("99");
        else
            c.createCriteria().andTypeIdEqualTo(typeId);
        c.setOrderByClause("sort_no");
        List<UmsCode> codeList = codeMapper.selectByExample(c);
        Map returnMap = new HashMap<>();
        String[] codeArray = new String[codeList.size()];
        List<Map> codeNameList = new ArrayList<>();
        int i = 0;
        Map addMap = new HashMap<>();
        addMap.put("name", "合计");
        addMap.put("num", 1);
        codeNameList.add(addMap);
        for (UmsCode code : codeList) {
            codeArray[i] = code.getId() + "";
            Map codeNameMap = new HashMap<>();
            codeNameMap.put("name", code.getCodeName());
            codeNameMap.put("num", i + 2);
            codeNameList.add(codeNameMap);
            i++;
        }
        addMap = new HashMap<>();
        addMap.put("name", "其他");
        addMap.put("num", i + 2);
        codeNameList.add(addMap);
        returnMap.put("codeArray", codeArray);
        returnMap.put("codeNameArray", codeNameList);
        return returnMap;
    }

    /**
     * @param typeId
     * @return
     */
    private Map getCodeArrayMap_new(int typeId) {
        UmsCodeCriteria c = new UmsCodeCriteria();
        c.createCriteria().andTypeIdEqualTo(typeId);
        c.setOrderByClause("sort_no");
        List<UmsCode> codeList = codeMapper.selectByExample(c);
        Map returnMap = new HashMap<>();
        String[] codeArray = new String[codeList.size()];
        List<Map> codeNameList = new ArrayList<>();
        int i = 0;
        Map addMap = new HashMap<>();
        addMap.put("name", "合计");
        addMap.put("num", 1);
        codeNameList.add(addMap);
        for (UmsCode code : codeList) {
            if ("法官".equals(code.getCodeName()) || "审判辅助人员".equals(code.getCodeName())) {
                continue;
            }
            codeArray[i] = code.getId() + "";
            Map codeNameMap = new HashMap<>();
            codeNameMap.put("name", code.getCodeName());
            codeNameMap.put("num", i + 2);
            codeNameList.add(codeNameMap);
            i++;
            if ("助理审判员".equals(code.getCodeName())) {
                codeArray[i] = "法官合计";
                codeNameMap = new HashMap<>();
                codeNameMap.put("name", "法官合计");
                codeNameMap.put("num", i + 2);
                codeNameList.add(codeNameMap);
                i++;
            } else if ("其他审判辅助人员".equals(code.getCodeName())) {
                codeArray[i] = "审判辅助人员合计";
                codeNameMap = new HashMap<>();
                codeNameMap.put("name", "审判辅助人员合计");
                codeNameMap.put("num", i + 2);
                codeNameList.add(codeNameMap);
                i++;
            }
        }
        returnMap.put("codeArray", codeArray);
        returnMap.put("codeNameArray", codeNameList);
        return returnMap;
    }

    //去除掉统计结果合计为0的部门（列）
    private Map removeCount0(Map returnMap) {
        try {
            List list = (List) returnMap.get("list");
            Map<String, Integer> countAllMap = (Map) list.get(2);
            List<String> count0List = new ArrayList<>();
            List<Integer> count0List2 = new ArrayList<>();
            int i = 0;
            for (String key : countAllMap.keySet()) {
                if (countAllMap.get(key) == 0) {
                    count0List.add(key);
                    count0List2.add(i);
                }
                i++;
            }
            if (count0List.size() > 0) {
                for (int j = 0; j < count0List2.size(); j++) {
                    int removeIndex = count0List2.get(j) - j;
                    for (int k = 0; k < list.size(); k++) {
                        if (k == 0 || k == 1)
                            ((List) list.get(k)).remove(removeIndex);
                        else
                            ((Map) list.get(k)).remove(count0List.get(j));
                    }

                }
            }
            list.remove(1);
            list.add(1, getIndexList(((List) list.get(0)).size()));
            returnMap.put("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMap;
    }

    private Map removeCount0ForQuery(Map returnMap) {
        try {
            List list = (List) returnMap.get("list");
            Map<String, QueryEntity> countAllMap = (Map) list.get(2);
            List<String> count0List = new ArrayList<>();
            List<Integer> count0List2 = new ArrayList<>();
            int i = 0;
            for (String key : countAllMap.keySet()) {
                QueryEntity entity = countAllMap.get(key);
                if (entity.getCount() == 0) {
                    count0List.add(key);
                    count0List2.add(i);
                }

                i++;
            }
            if (count0List.size() > 0) {
                for (int j = 0; j < count0List2.size(); j++) {
                    int removeIndex = count0List2.get(j) - j;
                    for (int k = 0; k < list.size(); k++) {
                        if (k == 0 || k == 1)
                            ((List) list.get(k)).remove(removeIndex);
                        else
                            ((Map) list.get(k)).remove(count0List.get(j));
                    }

                }
            }
            list.remove(1);
            list.add(1, getIndexList(((List) list.get(0)).size()));
            returnMap.put("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMap;
    }

    private List getIndexList(int size) {
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < size; i++)
            indexList.add(i + 1);
        return indexList;
    }

    /**
     * 去除表格统计结果为0的行
     *
     * @param returnMap
     * @return
     */
    private Map removeCount1(Map returnMap) {
        try {
            List list = (List) returnMap.get("list");
            List leftList = (List) returnMap.get("leftArray");
            List<Integer> hangIndexList = new ArrayList<>();
            for (int i = 2; i < list.size(); i++) {
                Map hangMap = (Map) list.get(i);
                if ((int) hangMap.get("countAll") <= 0)
                    hangIndexList.add(i);
            }
            if (hangIndexList.size() > 0) {
                for (int i = 0; i < hangIndexList.size(); i++) {
                    list.remove(hangIndexList.get(i) - i);
                    leftList.remove(hangIndexList.get(i) - i - 2);
                }
            }
            leftList = getRemovedLeft(leftList);
            returnMap.put("list", list);
            returnMap.put("leftArray", leftList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMap;
    }

    /**
     * 重新获取表格左边的表头
     *
     * @param leftList
     * @return
     */
    private List getRemovedLeft(List leftList) {
        List<Map> returnList = new ArrayList<>();
        for (int i = 0; i < leftList.size(); i++) {
            Map map = (Map) leftList.get(i);
            map.put("num", i + 1);
            returnList.add(map);
        }
        return returnList;

    }

    @Action("customSearch_")
    public String customSearch() {
        if (StringUtils.isEmpty(leftColumn) || StringUtils.isEmpty(topColumn) || typeId_left < 0 || typeId_top < 0)
            return "Error";

        //解码
        try {
            if(!noDecode){
                if(StringUtils.hasText(leftCodeNeed)){
                    byte[] decode1 = SimpleDecodeParameter.decode(leftCodeNeed);
                    leftCodeNeed = new String(decode1,"utf-8");
                }
                if(StringUtils.hasText(topCodeNeed)){
                    byte[] decode1 = SimpleDecodeParameter.decode(topCodeNeed);
                    topCodeNeed = new String(decode1,"utf-8");
                }
                if(StringUtils.hasText(fydmList)){
                    byte[] decode1 = SimpleDecodeParameter.decode(fydmList);
                    fydmList = new String(decode1,"utf-8");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        List<UmsCode> leftCodeList = new ArrayList<>();
        // 获取左侧的法院列表
        if (typeId_left == 1) {
            leftCodeList = courtFullService.getUmsCodeFy();
        } else if (typeId_left == 900) {
            Map<String, Object> param = new HashMap<>();
            if (courtLevel != null)
                param.put("courtLevel", courtLevel);
            if (courtCode != null && !"".equals(courtCode))
                param.put("courtCode", courtCode);
            if (preparation != null && !"".equals(preparation))
                param.put("preparation", preparation);
            leftCodeList = xtptBmDepartementService.selectDistinctAllUserCode(param);
        } else if (typeId_left == 901) {
            UmsCode umsCode = new UmsCode();
            umsCode.setId("0");
            umsCode.setCodeName("高级法院");
            leftCodeList.add(umsCode);
            umsCode = new UmsCode();
            umsCode.setId("1");
            umsCode.setCodeName("中级法院");
            leftCodeList.add(umsCode);
            umsCode = new UmsCode();
            umsCode.setId("2");
            umsCode.setCodeName("基层法院");
            leftCodeList.add(umsCode);
        } else {
            leftCodeList = umsCodeService.selectCodesByType(typeId_left);
        }
        // 获取表头
        List<UmsCode> topCodeList;
        if (typeId_top == 1) {
            topCodeList = courtFullService.getUmsCodeFy();
        } else if (typeId_top == 900) {
            Map<String, Object> param = new HashMap<>();
            if (courtLevel != null)
                param.put("courtLevel", courtLevel);
            if (courtCode != null && !"".equals(courtCode))
                param.put("courtCode", courtCode);
            if (preparation != null && !"".equals(preparation))
                param.put("preparation", preparation);
            topCodeList = xtptBmDepartementService.selectDistinctAllUserCode(param);
        } else {
            topCodeList = umsCodeService.selectCodesByType(typeId_top);
        }
        //改成判断是否为all，避免 6 张报表那儿重复获取
        String[] leftCodeArry;
        if (leftCodeNeed.equals("all")) {
            List<String> collect = leftCodeList.stream().map(UmsCodeKey::getId).collect(Collectors.toList());
            leftCodeArry = collect.toArray(new String[collect.size()]);
        } else {
            leftCodeArry = getCodeArry(leftCodeNeed);
        }
        String[] topCodeArry;
        if (topCodeNeed.equals("all")) {
            List<String> collect = topCodeList.stream().map(UmsCodeKey::getId).collect(Collectors.toList());
            topCodeArry = collect.toArray(new String[collect.size()]);
        } else {
            topCodeArry = getCodeArry(topCodeNeed);
        }
        String[] fydmCodeArry = getCodeArry(fydmList);
        List<String> fydmList = new ArrayList<>();
        if (fydmCodeArry != null) {
            for (String fydm : fydmCodeArry) {
                fydmList.add(fydm);
            }
        }
        Map param = new HashMap<>();
        param.put("leftColumn", leftColumn);
        param.put("topColumn", topColumn);
        if (typeId_top != 1 && typeId_left != 1 && !leftColumn.equals("court_gradation")) {
            if (fydmList != null && fydmList.size() > 0) {
                param.put("fydmList", fydmList);
            } else if (StringUtils.hasText(courtCode)) {
                param.put("fydmList", Arrays.asList(courtCode));
            }
            if (courtLevel != null) {
                param.put("courtLevel", courtLevel);
            }
            // 如果在统计范围中未选择法院，就进行权限判断
            if (null == fydmList || fydmList.size() == 0) {
                // courtLevel != null 代表选择了法院级别
                if (null != courtLevel) {
                    // 这里在加入一个判断，如果是超级管理员，如果有法院级别（高院、中层法院、基层法院），那么优先使用该条件；单个法院的条件不生效；如果不是超级管理员，那么法院级别不生效；
                    boolean flag = false;
                    UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                    for (UmsRole role : authorityService.userRoles(u.getId())) {
                        if (role.getRoleName().contains("超级管理员")) {
                            flag = true;
                        }
                    }
                    if (flag) {
                        // 是超级管理员，可以选择法院级别作为条件
                        if (courtLevel == -1) {
                            param.remove("courtLevel");
                        }
                        // 移除法院条件
                        param.remove("fydmList");
                    } else {
                        // 不是超级管理员，不可以选择法院级别作为条件，只能用操作人所属法院作为条件
                        param.remove("courtLevel");
                    }
                } else {
                    // 如果没选择法院级别，不进行干预
                }
            } else {
                // 如果在统计范围中选择了法院，就不进行权限判断
                if (null != courtLevel && courtLevel == -1) {
                    param.remove("courtLevel");
                }
            }
        }

        if (StringUtils.hasText(preparation)) {
            param.put("preparation", preparation);
        }
        if (otherParam != null)
            param.put("otherParam", otherParam);


        try {
            List<Map> resultList = umsUserInfoService.customSearch(param);
            if (resultList.size() == 0) {
                Map backMap = new HashMap<>();
                backMap.put("error", "查询结果为空！");
                data = backMap;
                return "json";
            }
            LinkedHashMap<String, QueryEntity> allMap = new LinkedHashMap<>();
            Map returnMap = new HashMap<>();
            //获取左边表头
            returnMap.put("leftArray", getLeftArray(leftCodeArry, leftCodeList));
            List<LinkedHashMap<String, QueryEntity>> list = new ArrayList<>();
            //获取上部表头
            list = getTopBiaotou(list, topCodeArry, topCodeList);
            //统计第一行合计
            allMap = getCountForQuery(resultList, topCodeArry, "-1", topColumn.replace("a.", ""), leftColumn.replace("a.", ""), null);
            list.add(allMap);
            //统计下面的行
            for (String num : leftCodeArry) {
                allMap = getCountForQuery(resultList, topCodeArry, num, topColumn.replace("a.", ""), leftColumn.replace("a.", ""), null);
                list.add(allMap);
            }
            //统计未知
            returnMap.put("list", getOther4ForQuery(list));
            //去掉没有值得行和列
            if (sixTableMap == null) {
                returnMap = removeCount0ForQuery(returnMap);
            }

            if (sixTableMap != null) {
                sixTableMap = returnMap;
            }
            Gson g = new Gson();
            String s = g.toJson(returnMap);
            data = s;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

    // 刑事、民事、执行、立案、行政 获取5个部门的数据
    public String customSearchForDept() {
        if (StringUtils.isEmpty(leftColumn) || StringUtils.isEmpty(topColumn) || typeId_left < 0 || typeId_top < 0)
            return "Error";

        // 获取权限
        StringBuilder sql_author = new StringBuilder();
        List<Integer> authCourt = authUtils.getAuthCourt();
        if (authCourt != null && authCourt.size() > 0) {
            sql_author.append(" and a.court_no in ( ");
            for (int i = 0; i < authCourt.size(); i++) {
                Integer integer = authCourt.get(i);
                sql_author.append(" '").append(integer).append("' ");
                if (i != authCourt.size() - 1) {
                    sql_author.append(",");
                }
            }
            sql_author.append(" ) ");
        }

        List<UmsCode> leftCodeList = new ArrayList<>();
        // 获取左侧的法院列表
        if (typeId_left == 1) {
            leftCodeList = courtFullService.getUmsCodeFy_2(authCourt);
        }
        // 获取表头
        String[] topCodeList = new String[]{"刑事", "民事", "执行", "立案", "行政审判庭"};

        //改成判断是否为all，避免 6 张报表那儿重复获取
        String[] leftCodeArry;
        if (leftCodeNeed.equals("all")) {
            List<String> collect = leftCodeList.stream().map(UmsCodeKey::getId).collect(Collectors.toList());
            leftCodeArry = collect.toArray(new String[collect.size()]);
        } else {
            leftCodeArry = getCodeArry(leftCodeNeed);
        }
        String[] topCodeArry = new String[]{"刑事", "民事", "执行", "立案", "行政审判庭"};
        String[] fydmCodeArry = getCodeArry(fydmList);
        List<String> fydmList = new ArrayList<>();
        if (fydmCodeArry != null) {
            for (String fydm : fydmCodeArry) {
                fydmList.add(fydm);
            }
        }
        // 根据部门类型存放结果
        Map<String, Object> map_result = new HashMap();
        // 部门按标准部门编码进行分组（详细分组方式参见下表），报表按法院进行分组，分别统计刑事、民事、执行、立案、行政 部门下各院法官人数，最后加入汇总数量
        // 基础信息表yefg=1 或法官等级表中当前信息 员额法官 为 是 的数据满足
        // 根据法院为条件
        for (String topCode : topCodeArry) {
            Map param = new HashMap<>();
            param.put("deptName", topCode);
            param.put("groupBy", "a.court_code");
            param.put("authcourt", sql_author);
            // 这里的sql是写死的,只传了部门的名字
            List<Map> list_countYefgBydept = umsUserInfoService.countYefgGroupbyDept(param);
            map_result.put(topCode, list_countYefgBydept);
        }
        try {
            // 根据自定义查询,获取数据list
            if (map_result.size() == 0) {
                Map backMap = new HashMap<>();
                backMap.put("error", "查询结果为空！");
                data = backMap;
                return "json";
            }
            // returnMap是返回的总数据,包括所有的行与列
            Map returnMap = new HashMap<>();
            // 左边表头
            returnMap.put("leftArray", getLeftArray(leftCodeArry, leftCodeList));
            // list用来存放没有左边表头的所有数据
            List<LinkedHashMap<String, Object>> list = new ArrayList<>();
            // 获取上部表头
            list = getTopBiaotouForDept(list, topCodeArry);
            // 统计第一行 总数据
            // allMap用来保存每一行的数据
            LinkedHashMap<String, Object> allMap = new LinkedHashMap<>();
            ;
            int i = 1;
            for (String topCode : topCodeArry) {
                Map param = new HashMap<>();
                param.put("deptName", topCode);
                param.put("authcourt", sql_author);
                // 这里的sql是写死的,只传了部门的名字,用于统计总数
                List<Map> list_allCount = umsUserInfoService.countYefgGroupbyDept(param);
                Map<String, Object> map = new HashMap<>();
                map.put("count", list_allCount.get(0).get("COUNT(*)"));
                map.put("leftColumn", "court_code");
                map.put("leftValue", "all");
                map.put("topColumn", topCode);
                map.put("topValue", topCode);
                allMap.put("countAll" + i, map);
                i++;
            }
            list.add(allMap);
            //统计下面的行
            List<Map<String, Object>> list_0 = (List<Map<String, Object>>) map_result.get("刑事");
            List<Map<String, Object>> list_1 = (List<Map<String, Object>>) map_result.get("民事");
            List<Map<String, Object>> list_2 = (List<Map<String, Object>>) map_result.get("执行");
            List<Map<String, Object>> list_3 = (List<Map<String, Object>>) map_result.get("立案");
            List<Map<String, Object>> list_4 = (List<Map<String, Object>>) map_result.get("行政审判庭");
            // 每行的数据
            for (String courtCode : leftCodeArry) {
                Map<String, Object> map = new HashMap<>();
                allMap = new LinkedHashMap<>();
                for (Map<String, Object> map_0 : list_0) {
                    if (courtCode.equals(map_0.get("court_code"))) {
                        map.put("count", map_0.get("COUNT(*)"));
                        map.put("leftColumn", "court_code");
                        map.put("leftValue", courtCode);
                        map.put("topColumn", "a.dept_org_code");
                        map.put("topValue", "刑事");
                        allMap.put("count1", map);
                        break;
                    }
                }
                if (map.size() == 0) {
                    map.put("count", 0);
                    map.put("leftColumn", "court_code");
                    map.put("leftValue", courtCode);
                    map.put("topColumn", "a.dept_org_code");
                    map.put("topValue", "刑事");
                    allMap.put("count1", map);
                }
                map = new HashMap<>();
                for (Map<String, Object> map_1 : list_1) {
                    if (courtCode.equals(map_1.get("court_code"))) {
                        map.put("count", map_1.get("COUNT(*)"));
                        map.put("leftColumn", "court_code");
                        map.put("leftValue", courtCode);
                        map.put("topColumn", "a.dept_org_code");
                        map.put("topValue", "民事");
                        allMap.put("count2", map);
                        break;
                    }
                }
                if (map.size() == 0) {
                    map.put("count", 0);
                    map.put("leftColumn", "court_code");
                    map.put("leftValue", courtCode);
                    map.put("topColumn", "a.dept_org_code");
                    map.put("topValue", "民事");
                    allMap.put("count2", map);
                }
                map = new HashMap<>();
                for (Map<String, Object> map_2 : list_2) {
                    if (courtCode.equals(map_2.get("court_code"))) {
                        map.put("count", map_2.get("COUNT(*)"));
                        map.put("leftColumn", "court_code");
                        map.put("leftValue", courtCode);
                        map.put("topColumn", "a.dept_org_code");
                        map.put("topValue", "执行");
                        allMap.put("count3", map);
                        break;
                    }
                }
                if (map.size() == 0) {
                    map.put("count", 0);
                    map.put("leftColumn", "court_code");
                    map.put("leftValue", courtCode);
                    map.put("topColumn", "a.dept_org_code");
                    map.put("topValue", "执行");
                    allMap.put("count3", map);
                }
                map = new HashMap<>();
                for (Map<String, Object> map_3 : list_3) {
                    if (courtCode.equals(map_3.get("court_code"))) {
                        map.put("count", map_3.get("COUNT(*)"));
                        map.put("leftColumn", "court_code");
                        map.put("leftValue", courtCode);
                        map.put("topColumn", "a.dept_org_code");
                        map.put("topValue", "立案");
                        allMap.put("count4", map);
                        break;
                    }
                }
                if (map.size() == 0) {
                    map.put("count", 0);
                    map.put("leftColumn", "court_code");
                    map.put("leftValue", courtCode);
                    map.put("topColumn", "a.dept_org_code");
                    map.put("topValue", "立案");
                    allMap.put("count4", map);
                }
                map = new HashMap<>();
                for (Map<String, Object> map_4 : list_4) {
                    if (courtCode.equals(map_4.get("court_code"))) {
                        map.put("count", map_4.get("COUNT(*)"));
                        map.put("leftColumn", "court_code");
                        map.put("leftValue", courtCode);
                        map.put("topColumn", "a.dept_org_code");
                        map.put("topValue", "行政审判庭");
                        allMap.put("count5", map);
                        break;
                    }
                }
                if (map.size() == 0) {
                    map.put("count", 0);
                    map.put("leftColumn", "court_code");
                    map.put("leftValue", courtCode);
                    map.put("topColumn", "a.dept_org_code");
                    map.put("topValue", "行政审判庭");
                    allMap.put("count5", map);
                }
                list.add(allMap);
            }
            returnMap.put("list", list);
            Gson g = new Gson();
            String s = g.toJson(returnMap);
            data = s;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

    // 通过时间条件统计入额法官、退出入额人员、各法官等级人员数量
    public String customSearchForFg(String startDate, String endDate) {
        // 返回值
        String returnStr = "";

        // 该人员权限下的法院列表
        List<Integer> authCourt;
        List<Integer> courtNoList = authorityHelper.accessibleCourtNoList("查看");
        List<Integer> courts = new ArrayList<>();
        if (courtNoList.contains(-9999)) { //超级管理员来了
            // 法院集合
            List<UmsCourtFull> court_list = umsCourtFullService.selectByListAll();
            for (UmsCourtFull umsCourtFull : court_list) {
                courts.add(umsCourtFull.getCourtNo());
            }
        } else if (authorityHelper.isSelfOnly(courtNoList)) {
            UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
            courts = new ArrayList<>();
            courts.add(u.getCourtNo());
        } else {
            courts = courtNoList;
        }
        authCourt = courts;
        // 获取左侧的法院列表
        List<UmsCode> leftCodeList = courtFullService.getUmsCodeFy_2(authCourt);

        // 获取表头 typeId_top : tpye类型
        List<UmsCode> topCodeList = umsCodeService.selectCodesByType(typeId_top);
        UmsCode umsCode_refg = new UmsCode();
        umsCode_refg.setCodeName("入额法官");
        umsCode_refg.setId("yefg");
        topCodeList.add(0, umsCode_refg);
        UmsCode umsCode_tefg = new UmsCode();
        umsCode_tefg.setCodeName("退额法官");
        umsCode_tefg.setId("tefg");
        topCodeList.add(1, umsCode_tefg);

        // 左侧
        List<String> collect_left = leftCodeList.stream().map(UmsCodeKey::getId).collect(Collectors.toList());
        String[] leftCodeArry = collect_left.toArray(new String[collect_left.size()]);
        // 顶部
        List<String> collect_top = topCodeList.stream().map(UmsCodeKey::getId).collect(Collectors.toList());
        String[] topCodeArry = collect_top.toArray(new String[collect_top.size()]);

        Map param = new HashMap<>();
        param.put("leftColumn", leftColumn);
        param.put("topColumn", topColumn);

        if (StringUtils.hasText(startDate)) {
            param.put("startDate", startDate);
        }
        if (StringUtils.hasText(endDate)) {
            param.put("endDate", endDate);
        }

        // 获取权限, 权限下有哪些法院
        StringBuilder sql_author = new StringBuilder();
        if (authCourt != null && authCourt.size() > 0) {
            sql_author.append(" and a.court_no in ( ");
            for (int i = 0; i < authCourt.size(); i++) {
                Integer integer = authCourt.get(i);
                sql_author.append(" '").append(integer).append("' ");
                if (i != authCourt.size() - 1) {
                    sql_author.append(",");
                }
            }
            sql_author.append(" ) ");
        }
        // 针对每条数据,有该法院权限的才统计数据
        param.put("authcourt", sql_author);

        try {
            // 根据时间条件查询入额法官数量
            List<Map> list_yefg = umsUserInfoService.countyefg(param);
            // 根据时间条件查询退额法官数量
            List<Map> list_tefg = umsUserInfoService.counttefg(param);
            list_yefg.addAll(list_tefg);
            // 根据时间条件查询每种法官数量
            List<Map> list_EveryFg = umsUserInfoService.countEveryFg(param);
            list_yefg.addAll(list_EveryFg);

            if (list_yefg.size() == 0) {
                Map backMap = new HashMap<>();
                backMap.put("error", "查询结果为空！");
                data = backMap;
                return "json";
            }

            // 获取上部表头
            List<LinkedHashMap<String, QueryEntity>> list = new ArrayList<>();
            list = getTopBiaotouForFg(list, topCodeArry, topCodeList);

            // 统计第一行合计
            LinkedHashMap<String, QueryEntity> allMap = getCountForQueryForFg(list_yefg, topCodeArry, "-1", topColumn.replace("a.", ""), leftColumn.replace("a.", ""), null);
            list.add(allMap);

            // 统计下面每一行
            for (String num : leftCodeArry) {
                allMap = getCountForQueryForFg(list_yefg, topCodeArry, num, topColumn.replace("a.", ""), leftColumn.replace("a.", ""), null);
                list.add(allMap);
            }

            // 返回数据
            Map returnMap = new HashMap<>();
            // 数据
            returnMap.put("list", list);
            // 左边表头
            returnMap.put("leftArray", getLeftArray(leftCodeArry, leftCodeList));
            // 转换格式
            returnStr = new Gson().toJson(returnMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnStr;
    }

    // 通过时间条件统计入额法官、退出入额人员、各法官等级人员数量
    public String customSearchForTeFg(String startDate, String endDate) {
        // 返回值
        String returnStr = "";

        // 该人员权限下的法院列表
        List<Integer> authCourt;
        List<Integer> courtNoList = authorityHelper.accessibleCourtNoList("查看");
        List<Integer> courts = new ArrayList<>();
        if (courtNoList.contains(-9999)) { //超级管理员来了
            // 法院集合
            List<UmsCourtFull> court_list = umsCourtFullService.selectByListAll();
            for (UmsCourtFull umsCourtFull : court_list) {
                courts.add(umsCourtFull.getCourtNo());
            }
        } else if (authorityHelper.isSelfOnly(courtNoList)) {
            UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
            courts = new ArrayList<>();
            courts.add(u.getCourtNo());
        } else {
            courts = courtNoList;
        }
        authCourt = courts;
        // 获取左侧的法院列表
        List<UmsCode> leftCodeList = courtFullService.getUmsCodeFy_2(authCourt);

        // 获取表头 typeId_top : tpye类型
        List<UmsCode> topCodeList = umsCodeService.selectCodesByType(typeId_top);
        UmsCode umsCode_tefg = new UmsCode();
        umsCode_tefg.setCodeName("退额法官");
        umsCode_tefg.setId("tefg");
        topCodeList.add(0, umsCode_tefg);

        // 左侧
        List<String> collect_left = leftCodeList.stream().map(UmsCodeKey::getId).collect(Collectors.toList());
        String[] leftCodeArry = collect_left.toArray(new String[collect_left.size()]);
        // 顶部
        List<String> collect_top = topCodeList.stream().map(UmsCodeKey::getId).collect(Collectors.toList());
        String[] topCodeArry = collect_top.toArray(new String[collect_top.size()]);

        Map param = new HashMap<>();
        param.put("leftColumn", leftColumn);
        param.put("topColumn", topColumn);

        if (StringUtils.hasText(startDate)) {
            param.put("startDate", startDate);
        }
        if (StringUtils.hasText(endDate)) {
            param.put("endDate", endDate);
        }

        // 获取权限, 权限下有哪些法院
        StringBuilder sql_author = new StringBuilder();
        if (authCourt != null && authCourt.size() > 0) {
            sql_author.append(" and a.court_no in ( ");
            for (int i = 0; i < authCourt.size(); i++) {
                Integer integer = authCourt.get(i);
                sql_author.append(" '").append(integer).append("' ");
                if (i != authCourt.size() - 1) {
                    sql_author.append(",");
                }
            }
            sql_author.append(" ) ");
        }
        // 针对每条数据,有该法院权限的才统计数据
        param.put("authcourt", sql_author);

        try {
            // 根据时间条件查询退额法官数量
            List<Map> list_tefg = umsUserInfoService.counttefg(param);
            // 根据时间条件查询每种法官数量
            List<Map> list_EveryFg = umsUserInfoService.countEveryTeFg(param);
            list_tefg.addAll(list_EveryFg);

            if (list_tefg.size() == 0) {
                Map backMap = new HashMap<>();
                backMap.put("error", "查询结果为空！");
                data = backMap;
                return "json";
            }

            // 获取上部表头
            List<LinkedHashMap<String, QueryEntity>> list = new ArrayList<>();
            list = getTopBiaotouForFg(list, topCodeArry, topCodeList);

            // 统计第一行合计
            LinkedHashMap<String, QueryEntity> allMap = getCountForQueryForFg(list_tefg, topCodeArry, "-1", topColumn.replace("a.", ""), leftColumn.replace("a.", ""), null);
            list.add(allMap);

            // 统计下面每一行
            for (String num : leftCodeArry) {
                allMap = getCountForQueryForFg(list_tefg, topCodeArry, num, topColumn.replace("a.", ""), leftColumn.replace("a.", ""), null);
                list.add(allMap);
            }

            // 返回数据
            Map returnMap = new HashMap<>();
            // 数据
            returnMap.put("list", list);
            // 左边表头
            returnMap.put("leftArray", getLeftArray(leftCodeArry, leftCodeList));
            // 转换格式
            returnStr = new Gson().toJson(returnMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnStr;
    }

    @Action("customSearchForBw")
    public String customSearchForBw() {
        if (StringUtils.isEmpty(leftColumn) || StringUtils.isEmpty(topColumn) || typeId_left < 0 || typeId_top < 0)
            return "Error";


        //解码
        try {
            if(StringUtils.hasText(leftCodeNeed)){
                byte[] decode1 = SimpleDecodeParameter.decode(leftCodeNeed);
                leftCodeNeed = new String(decode1,"utf-8");
            }
            if(StringUtils.hasText(topCodeNeed)){
                byte[] decode1 = SimpleDecodeParameter.decode(topCodeNeed);
                topCodeNeed = new String(decode1,"utf-8");
            }
            if(StringUtils.hasText(fydmList)){
                byte[] decode1 = SimpleDecodeParameter.decode(fydmList);
                fydmList = new String(decode1,"utf-8");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        List<UmsCode> leftCodeList = new ArrayList<>();

        if (typeId_left == 1) {
            leftCodeList = courtFullService.getUmsCodeFy();
        } else if (typeId_left == 800) {
            //800 为编外人员来源
            List<UmsExtEnterSrcType> all = enterSrcTypeService.findAll();
            for (UmsExtEnterSrcType entity : all) {
                UmsCode umsCode = new UmsCode();
                umsCode.setCodeName(entity.getSrcTypeName());
                umsCode.setId(entity.getId() == null ? "" : entity.getId().toString());
                leftCodeList.add(umsCode);
            }

        } else if (typeId_left == 801) {
            // 801 为编外公司名称
            List<UmsExternalCompanyInfo> all = externalCompanyInfoService.findAll();
            for (UmsExternalCompanyInfo entity : all) {
                UmsCode umsCode = new UmsCode();
                umsCode.setCodeName(entity.getCompanyName());
                umsCode.setId(entity.getId() == null ? "" : entity.getId().toString());
                leftCodeList.add(umsCode);
            }

        } else {
            leftCodeList = umsCodeService.selectCodesByType(typeId_left);
        }


        List<UmsCode> topCodeList = new ArrayList<>();
        if (typeId_top == 1) {
            topCodeList = courtFullService.getUmsCodeFy();
        } else if (typeId_top == 800) {
            //800 为编外人员来源
            List<UmsExtEnterSrcType> all = enterSrcTypeService.findAll();
            for (UmsExtEnterSrcType entity : all) {
                UmsCode umsCode = new UmsCode();
                umsCode.setCodeName(entity.getSrcTypeName());
                umsCode.setId(entity.getId() == null ? "" : entity.getId().toString());
                topCodeList.add(umsCode);
            }
        } else if (typeId_top == 801) {
            List<UmsExternalCompanyInfo> all = externalCompanyInfoService.findAll();
            for (UmsExternalCompanyInfo entity : all) {
                UmsCode umsCode = new UmsCode();
                umsCode.setCodeName(entity.getCompanyName());
                umsCode.setId(entity.getId() == null ? "" : entity.getId().toString());
                topCodeList.add(umsCode);
            }
        } else {
            topCodeList = umsCodeService.selectCodesByType(typeId_top);
        }


        String[] leftCodeArry = getCodeArry(leftCodeNeed);
        String[] fydmCodeArry = getCodeArry(fydmList);
        String[] topCodeArry = getCodeArry(topCodeNeed);
        List<String> fydmList = new ArrayList<>();
        if (fydmCodeArry != null) {
            for (String fydm : fydmCodeArry) {
                fydmList.add(fydm);
            }
        }
        Map param = new HashMap<>();
        if (fydmList.size() > 0)
            param.put("fydmList", fydmList);
        param.put("leftColumn", leftColumn);
        param.put("topColumn", topColumn);
        //这里都是编外的人员
        param.put("userType", 2);
        if (courtLevel != null)
            param.put("courtLevel", courtLevel);

        try {
            List<Map> resultList;

            if (leftColumn.contains("enter_src") || leftColumn.contains("company_info_id") || topColumn.contains("enter_src") || topColumn.contains("company_info_id")) {
                // leftColumn 或者 topColumn 中包含 ums_external_user_info 中的额外信息时需要另外查询
                param.put("leftColumn", leftColumn.replace("a.", ""));
                resultList = umsUserInfoService.customSearchForBw(param);

            } else {
                if ("a.court_code".equals(leftColumn) && !StringUtils.isEmpty(leftCodeNeed)) {
                    String[] leftCodeNeedArray = leftCodeNeed.split(",");
                    if (fydmList == null) {
                        fydmList = new ArrayList<>();
                    }
                    for (int i = 0; i < leftCodeNeedArray.length; i++) {
                        fydmList.add(leftCodeNeedArray[i]);
                    }
                }
                if (fydmList != null && fydmList.size() > 0) {
                    param.put("fydmList", fydmList);
                }
                resultList = umsUserInfoService.customSearch(param);
            }

            if (resultList.size() == 0) {
                Map backMap = new HashMap<>();
                backMap.put("error", "查询结果为空！");
                data = backMap;
                return "json";
            }
            LinkedHashMap<String, QueryEntity> allMap = new LinkedHashMap<>();
            Map returnMap = new HashMap<>();
            //获取左边表头
            returnMap.put("leftArray", getLeftArray(leftCodeArry, leftCodeList));
            List<LinkedHashMap<String, QueryEntity>> list = new ArrayList<>();
            //获取上部表头
            list = getTopBiaotou(list, topCodeArry, topCodeList);
            //统计第一行合计
            allMap = getCountForQuery(resultList, topCodeArry, "-1", topColumn.replace("a.", ""), leftColumn.replace("a.", ""), null);
            list.add(allMap);
            //统计下面的行
            if (leftCodeArry != null && leftCodeArry.length > 0) {
                for (String num : leftCodeArry) {
                    allMap = getCountForQuery(resultList, topCodeArry, num, topColumn.replace("a.", ""), leftColumn.replace("a.", ""), null);
                    list.add(allMap);
                }
            }
            //统计未知
            returnMap.put("list", getOther4ForQuery(list));
            //去掉没有值得行和列
            returnMap = removeCount0ForQuery(returnMap);
            returnMap.put("topCodeNeed", topCodeNeed);
            returnMap.put("leftCodeNeed", leftCodeNeed);
            returnMap.put("fydmList", fydmList == null ? "" : fydmList.stream().collect(Collectors.joining(",")));
            //returnMap = removeCount1(returnMap);
            Gson g = new Gson();
            String s = g.toJson(returnMap);
            data = s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

    @Action("customSearchForPsy")
    public String customSearchForPsy() {
        if (StringUtils.isEmpty(leftColumn) || StringUtils.isEmpty(topColumn) || typeId_left < 0 || typeId_top < 0) {
            Map backMap = new HashMap<>();
            backMap.put("error", "查询结果为空！");
            data = backMap;
            return "json";
        }

        //解码
        try {
            if(StringUtils.hasText(leftCodeNeed)){
                byte[] decode1 = SimpleDecodeParameter.decode(leftCodeNeed);
                leftCodeNeed = new String(decode1,"utf-8");
            }
            if(StringUtils.hasText(topCodeNeed)){
                byte[] decode1 = SimpleDecodeParameter.decode(topCodeNeed);
                topCodeNeed = new String(decode1,"utf-8");
            }
            if(StringUtils.hasText(fydmList)){
                byte[] decode1 = SimpleDecodeParameter.decode(fydmList);
                fydmList = new String(decode1,"utf-8");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        List<UmsCode> leftCodeList = new ArrayList<>();

        if (typeId_left == 1) {
            leftCodeList = courtFullService.getUmsCodeFy();
        } else if (typeId_left == 800) {
            //800 为juror_work 职业
            String array[] = new String[]{"基层干部", "人民团体成员", "事业单位职员", "专业技术人员", "工商业人员", "社区工作者", "普通居民", "农民", "进城务工人员", "其他人员"};
            for (String s : array) {
                UmsCode umsCode = new UmsCode();
                umsCode.setCodeName(s);
                umsCode.setId(s);
                leftCodeList.add(umsCode);
            }
        } else if (typeId_left == 801) {
            //801 member_state 陪审状态
            UmsCode umsCode = new UmsCode();
            umsCode.setCodeName("在任");
            umsCode.setId("01");
            leftCodeList.add(umsCode);
            UmsCode umsCode1 = new UmsCode();
            umsCode1.setCodeName("退出");
            umsCode1.setId("02");
            leftCodeList.add(umsCode1);
        } else {
            leftCodeList = umsCodeService.selectCodesByType(typeId_left);
        }


        List<UmsCode> topCodeList = new ArrayList<>();
        if (typeId_top == 1) {
            topCodeList = courtFullService.getUmsCodeFy();
        } else if (typeId_top == 800) {
            //800 为juror_work 职业
            String array[] = new String[]{"基层干部", "人民团体成员", "事业单位职员", "专业技术人员", "工商业人员", "社区工作者", "普通居民", "农民", "进城务工人员", "其他人员"};
            for (String s : array) {
                UmsCode umsCode = new UmsCode();
                umsCode.setCodeName(s);
                umsCode.setId(s);
                topCodeList.add(umsCode);
            }
        } else if (typeId_top == 801) {
            //801 member_state 陪审状态
            UmsCode umsCode = new UmsCode();
            umsCode.setCodeName("在任");
            umsCode.setId("01");
            topCodeList.add(umsCode);
            UmsCode umsCode1 = new UmsCode();
            umsCode1.setCodeName("退出");
            umsCode1.setId("02");
            topCodeList.add(umsCode1);
        } else {
            topCodeList = umsCodeService.selectCodesByType(typeId_top);
        }


        String[] leftCodeArry = getCodeArry(leftCodeNeed);
        String[] fydmCodeArry = getCodeArry(fydmList);
        String[] topCodeArry = getCodeArry(topCodeNeed);
        List<String> fydmList = new ArrayList<>();
        if (fydmCodeArry != null) {
            for (String fydm : fydmCodeArry) {
                fydmList.add(fydm);
            }
        }
        Map param = new HashMap<>();
        if (fydmList.size() > 0)
            param.put("fydmList", fydmList);
        param.put("leftColumn", leftColumn);
        param.put("topColumn", topColumn);
        //这里都是人民陪审员
        param.put("userType", 3);
        if (courtLevel != null)
            param.put("courtLevel", courtLevel);

        try {
            List<Map> resultList;

            if (leftColumn.contains("juror_work") || leftColumn.contains("member_state")
                    || topColumn.contains("juror_work") || topColumn.contains("member_state")) {
                // leftColumn 或者 topColumn 中包含 ums_external_user_info 中的额外信息时需要另外查询
                param.put("leftColumn", leftColumn.replace("a.", ""));
                resultList = umsUserInfoService.customSearchForPsy(param);

            } else {
                if ("a.court_code".equals(leftColumn) && !StringUtils.isEmpty(leftCodeNeed)) {
                    String[] leftCodeNeedArray = leftCodeNeed.split(",");
                    if (fydmList == null) {
                        fydmList = new ArrayList<>();
                    }
                    for (int i = 0; i < leftCodeNeedArray.length; i++) {
                        fydmList.add(leftCodeNeedArray[i]);
                    }
                }
                if (fydmList != null && fydmList.size() > 0) {
                    param.put("fydmList", fydmList);
                }
                resultList = umsUserInfoService.customSearch(param);
            }

            if (resultList.size() == 0) {
                Map backMap = new HashMap<>();
                backMap.put("error", "查询结果为空！");
                data = backMap;
                return "json";
            }
            LinkedHashMap<String, QueryEntity> allMap = new LinkedHashMap<>();
            Map returnMap = new HashMap<>();
            //获取左边表头
            returnMap.put("leftArray", getLeftArray(leftCodeArry, leftCodeList));
            List<LinkedHashMap<String, QueryEntity>> list = new ArrayList<>();
            //获取上部表头
            list = getTopBiaotou(list, topCodeArry, topCodeList);
            //统计第一行合计
            allMap = getCountForQuery(resultList, topCodeArry, "-1", topColumn.replace("a.", ""), leftColumn.replace("a.", ""), null);
            list.add(allMap);
            //统计下面的行
            for (String num : leftCodeArry) {
                allMap = getCountForQuery(resultList, topCodeArry, num, topColumn.replace("a.", ""), leftColumn.replace("a.", ""), null);
                list.add(allMap);
            }
            //统计未知
            returnMap.put("list", getOther4ForQuery(list));
            //去掉没有值得行和列
            returnMap = removeCount0ForQuery(returnMap);
            returnMap.put("topCodeNeed", topCodeNeed);
            returnMap.put("leftCodeNeed", leftCodeNeed);
            returnMap.put("fydmList", fydmList == null ? "" : fydmList.stream().collect(Collectors.joining(",")));
            Gson g = new Gson();
            String s = g.toJson(returnMap);
            data = s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

    private String[] getCodeArry(String codeNeed) {
        String[] ss = null;
        if (!"".equals(codeNeed) && codeNeed != null)
            ss = codeNeed.split(",");
        return ss;
    }

    private List getTopBiaotou(List list, String[] topList, List<UmsCode> topCodeList) {
        List<String> nameList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        nameList.add("合计");
        numList.add(1);
        int i = 2;
        for (String code : topList) {
            for (UmsCode allcode : topCodeList) {
                if (code.equals(allcode.getId().toString())) {
                    nameList.add(allcode.getCodeName().toString());
                    numList.add(i);
                    i++;
                }
            }
        }
        nameList.add("未知");
        numList.add(i);
        list.add(nameList);
        list.add(numList);
        return list;
    }

    private List getTopBiaotouForFg(List list, String[] topList, List<UmsCode> topCodeList) {
        List<String> nameList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        int i = 0;
        for (String code : topList) {
            for (UmsCode allcode : topCodeList) {
                if (code.equals(allcode.getId().toString())) {
                    nameList.add(allcode.getCodeName().toString());
                    numList.add(i);
                    i++;
                }
            }
        }
        list.add(nameList);
        list.add(numList);
        return list;
    }

    private List getTopBiaotouForDept(List list, String[] topList) {
        List<String> nameList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        int i = 1;
        for (String code : topList) {
            nameList.add(code);
            numList.add(i);
            i++;
        }
        list.add(nameList);
        list.add(numList);
        return list;
    }

    @Action("customSearch_nnd")
    public String customSearch_nnd() {
        List<UmsCode> leftCodeList = null, topCodeList = null;

        //解码
        try {
            if(!noDecode){
                if(StringUtils.hasText(leftCodeNeed)){
                    byte[] decode1 = SimpleDecodeParameter.decode(leftCodeNeed);
                    leftCodeNeed = new String(decode1,"utf-8");
                }
                if(StringUtils.hasText(topCodeNeed)){
                    byte[] decode1 = SimpleDecodeParameter.decode(topCodeNeed);
                    topCodeNeed = new String(decode1,"utf-8");
                }
                if(StringUtils.hasText(fydmList)){
                    byte[] decode1 = SimpleDecodeParameter.decode(fydmList);
                    fydmList = new String(decode1,"utf-8");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        // 左边的列-99 代表的是年龄
        if (typeId_left != -99) {
            if (typeId_left == 1) {
                leftCodeList = courtFullService.getUmsCodeFy();
            } else if (typeId_left == 900) {
                Map<String, Object> param = new HashMap<>();
                if (courtLevel != null)
                    param.put("courtLevel", courtLevel);
                if (courtCode != null && !"".equals(courtCode))
                    param.put("courtCode", courtCode);
                if (preparation != null && !"".equals(preparation))
                    param.put("preparation", preparation);
                leftCodeList = xtptBmDepartementService.selectDistinctAllUserCode(param);
            } else {
                leftCodeList = umsCodeService.selectCodesByType(typeId_left);
            }
        }
        // 上边的行-99 代表的是年龄
        if (typeId_top != -99) {
            if (typeId_top == 1) {
                topCodeList = courtFullService.getUmsCodeFy();
            } else if (typeId_top == 900) {
                Map<String, Object> param = new HashMap<>();
                if (courtLevel != null)
                    param.put("courtLevel", courtLevel);
                if (courtCode != null && !"".equals(courtCode))
                    param.put("courtCode", courtCode);
                if (preparation != null && !"".equals(preparation))
                    param.put("preparation", preparation);
                topCodeList = xtptBmDepartementService.selectDistinctAllUserCode(param);
            } else {
                topCodeList = umsCodeService.selectCodesByType(typeId_top);
            }
        }

        //改成判断是否为all，避免 6 张报表那儿重复获取
        String[] leftCodeArry;
        if (leftCodeNeed.equals("all")) {
            List<String> collect = leftCodeList.stream().map(UmsCodeKey::getId).collect(Collectors.toList());
            leftCodeArry = collect.toArray(new String[collect.size()]);
        } else {
            leftCodeArry = getCodeArry(leftCodeNeed);
        }
        String[] topCodeArry;
        if (topCodeNeed.equals("all")) {
            List<String> collect = topCodeList.stream().map(UmsCodeKey::getId).collect(Collectors.toList());
            topCodeArry = collect.toArray(new String[collect.size()]);
        } else {
            topCodeArry = getCodeArry(topCodeNeed);
        }
        String[] fydmCodeArry = getCodeArry(fydmList);
        List<String> fydmList = null;
        if (fydmCodeArry != null) {
            fydmList = new ArrayList<>();
            for (String fydm : fydmCodeArry) {
                fydmList.add(fydm);
            }
        }
        List<Map> nndList;
        if (typeId_left == -99)
            nndList = getNndList(leftCodeNeed);
        else
            nndList = getNndList(topCodeNeed);
        Map param = new HashMap<>();
        param.put("nndList", nndList);
        if (typeId_left == -99)
            param.put("topColumn", topColumn);
        else
            param.put("topColumn", leftColumn);
        if (typeId_top != 1 && typeId_left != 1) {
            if (fydmList != null && fydmList.size() > 0) {
                param.put("fydmList", fydmList);
            } else if (StringUtils.hasText(courtCode)) {
                param.put("fydmList", Arrays.asList(courtCode));
            }
            if (courtLevel != null) {
                param.put("courtLevel", courtLevel);
            }
            // 如果在统计范围中未选择法院，就进行权限判断
            if (null == fydmList || fydmList.size() == 0) {
                // courtLevel != null 代表选择了法院级别
                if (null != courtLevel) {
                    // 这里在加入一个判断，如果是超级管理员，如果有法院级别（高院、中层法院、基层法院），那么优先使用该条件；单个法院的条件不生效；如果不是超级管理员，那么法院级别不生效；
                    boolean flag = false;
                    UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                    for (UmsRole role : authorityService.userRoles(u.getId())) {
                        if (role.getRoleName().contains("超级管理员")) {
                            flag = true;
                        }
                    }
                    if (flag) {
                        // 是超级管理员，可以选择法院级别作为条件
                        if (courtLevel == -1) {
                            param.remove("courtLevel");
                        }
                        // 移除法院条件
                        param.remove("fydmList");
                    } else {
                        // 不是超级管理员，不可以选择法院级别作为条件，只能用操作人所属法院作为条件
                        param.remove("courtLevel");
                    }
                } else {
                    // 如果没选择法院级别，不进行干预
                }
            } else {
                // 如果在统计范围中选择了法院，就不进行权限判断
                if (null != courtLevel && courtLevel == -1) {
                    param.remove("courtLevel");
                }
            }
        }
        if (StringUtils.hasText(preparation)) {
            param.put("preparation", preparation);
        }

        try {
            List<Map> resultList = umsUserInfoService.cunstomSearch_nnd(param);
            if (resultList.size() == 0) {
                Map backMap = new HashMap<>();
                backMap.put("error", "查询结果为空！");
                data = backMap;
                return "json";
            }
            LinkedHashMap<String, QueryEntity> allMap = new LinkedHashMap<>();
            Map returnMap = new HashMap<>();
            List<LinkedHashMap<String, QueryEntity>> list = new ArrayList<>();
            if ("birthday".equals(leftColumn)) {
                returnMap.put("leftArray", getLeftArray_nnd(nndList));//获取左边表头
                list = getTopBiaotou(list, topCodeArry, topCodeList);//获取上部表头
                allMap = getCountForQuery(resultList, topCodeArry, "-1", topColumn.replace("a.", ""), leftColumn.replace("a.", ""), null);//统计第一行合计
                list.add(allMap);
                for (String num : leftCodeArry) {
                    allMap = getCountForQuery(resultList, topCodeArry, num, topColumn.replace("a.", ""), leftColumn.replace("a.", ""), null);
                    list.add(allMap);
                }
            } else {
                returnMap.put("leftArray", getLeftArray(leftCodeArry, leftCodeList));//获取左边表头
                list = getTopBiaotou_nndForQuery(list, nndList);//获取上部表头
                allMap = getCountForQuery(resultList, topCodeArry, "-1", topColumn.replace("a.", ""), leftColumn.replace("a.", ""), 2);//统计第一行合计
                list.add(allMap);
                for (String num : leftCodeArry) {
                    allMap = getCountForQuery(resultList, topCodeArry, num, topColumn.replace("a.", ""), leftColumn.replace("a.", ""), 2);
                    list.add(allMap);
                }
            }

            //统计下面的行

            //统计未知
            returnMap.put("list", getOther4ForQuery(list));
            //去掉没有值得行和列

            //去掉没有值得行和列
            if (sixTableMap == null) {
                returnMap = removeCount0ForQuery(returnMap);
            }

            if (sixTableMap != null) {
                sixTableMap = returnMap;
            }
            Gson g = new Gson();
            String s = g.toJson(returnMap);
            data = s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }


    @Action("customSearchForAgeBw")
    public String customSearchForAgeBw() {
        List<UmsCode> leftCodeList = null, topCodeList = null;

        //解码
        try {
            if(StringUtils.hasText(leftCodeNeed)){
                byte[] decode1 = SimpleDecodeParameter.decode(leftCodeNeed);
                leftCodeNeed = new String(decode1,"utf-8");
            }
            if(StringUtils.hasText(topCodeNeed)){
                byte[] decode1 = SimpleDecodeParameter.decode(topCodeNeed);
                topCodeNeed = new String(decode1,"utf-8");
            }
            if(StringUtils.hasText(fydmList)){
                byte[] decode1 = SimpleDecodeParameter.decode(fydmList);
                fydmList = new String(decode1,"utf-8");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (typeId_left != -99) {

            leftCodeList = new ArrayList<>();
            if (typeId_left == 1) {
                leftCodeList = courtFullService.getUmsCodeFy();
            } else if (typeId_left == 800) {
                //800 为编外人员来源
                List<UmsExtEnterSrcType> all = enterSrcTypeService.findAll();
                for (UmsExtEnterSrcType entity : all) {
                    UmsCode umsCode = new UmsCode();
                    umsCode.setCodeName(entity.getSrcTypeName());
                    umsCode.setId(entity.getId() == null ? "" : entity.getId().toString());
                    leftCodeList.add(umsCode);
                }
            } else if (typeId_left == 801) {
                // 801 为编外公司名称
                List<UmsExternalCompanyInfo> all = externalCompanyInfoService.findAll();
                for (UmsExternalCompanyInfo entity : all) {
                    UmsCode umsCode = new UmsCode();
                    umsCode.setCodeName(entity.getCompanyName());
                    umsCode.setId(entity.getId() == null ? "" : entity.getId().toString());
                    leftCodeList.add(umsCode);
                }
            } else {
                leftCodeList = umsCodeService.selectCodesByType(typeId_left);
            }

        }
        if (typeId_top != -99) {
            topCodeList = new ArrayList<>();
            if (typeId_top == 1) {
                topCodeList = courtFullService.getUmsCodeFy();
            } else if (typeId_top == 800) {
                //800 为编外人员来源
                List<UmsExtEnterSrcType> all = enterSrcTypeService.findAll();
                for (UmsExtEnterSrcType entity : all) {
                    UmsCode umsCode = new UmsCode();
                    umsCode.setCodeName(entity.getSrcTypeName());
                    umsCode.setId(entity.getId() == null ? "" : entity.getId().toString());
                    topCodeList.add(umsCode);
                }
            } else if (typeId_top == 801) {
                List<UmsExternalCompanyInfo> all = externalCompanyInfoService.findAll();
                for (UmsExternalCompanyInfo entity : all) {
                    UmsCode umsCode = new UmsCode();
                    umsCode.setCodeName(entity.getCompanyName());
                    umsCode.setId(entity.getId() == null ? "" : entity.getId().toString());
                    topCodeList.add(umsCode);
                }
            } else {
                topCodeList = umsCodeService.selectCodesByType(typeId_top);
            }

        }


        String[] leftCodeArry = getCodeArry(leftCodeNeed);
        String[] fydmCodeArry = getCodeArry(fydmList);
        String[] topCodeArry = getCodeArry(topCodeNeed);
        List<String> fydmList = null;
        if (fydmCodeArry != null) {
            fydmList = new ArrayList<>();
            for (String fydm : fydmCodeArry) {
                fydmList.add(fydm);
            }
        }
        List<Map> nndList;
        if (typeId_left == -99)
            nndList = getNndList(leftCodeNeed);
        else
            nndList = getNndList(topCodeNeed);
        Map param = new HashMap<>();
        if (fydmList != null)
            param.put("fydmList", fydmList);
        param.put("nndList", nndList);
        param.put("userType", 2);
        if (typeId_left == -99)
            param.put("topColumn", topColumn);
        else
            param.put("topColumn", leftColumn);
        if (courtLevel != null)
            param.put("courtLevel", courtLevel);

        try {

            List<Map> resultList;
            if (leftColumn.contains("enter_src") || leftColumn.contains("company_info_id") || topColumn.contains("enter_src") || topColumn.contains("company_info_id")) {
                // leftColumn 或者 topColumn 中包含 ums_external_user_info 中的额外信息时需要另外查询
                param.put("leftColumn", leftColumn.replace("a.", ""));
                resultList = umsUserInfoService.customSearchForAgeBw(param);

            } else {
                if ("a.court_code".equals(leftColumn) && !StringUtils.isEmpty(leftCodeNeed)) {
                    String[] leftCodeNeedArray = leftCodeNeed.split(",");
                    if (fydmList == null) {
                        fydmList = new ArrayList<>();
                    }
                    for (int i = 0; i < leftCodeNeedArray.length; i++) {
                        fydmList.add(leftCodeNeedArray[i]);
                    }
                }
                if (fydmList != null && fydmList.size() > 0) {
                    param.put("fydmList", fydmList);
                }
                resultList = umsUserInfoService.cunstomSearch_nnd(param);
            }

            if (resultList.size() == 0) {
                Map backMap = new HashMap<>();
                backMap.put("error", "查询结果为空！");
                data = backMap;
                return "json";
            }
            LinkedHashMap<String, QueryEntity> allMap = new LinkedHashMap<>();
            Map returnMap = new HashMap<>();
            List<LinkedHashMap<String, QueryEntity>> list = new ArrayList<>();
            if ("birthday".equals(leftColumn)) {
                returnMap.put("leftArray", getLeftArray_nnd(nndList));//获取左边表头
                list = getTopBiaotou(list, topCodeArry, topCodeList);//获取上部表头
                allMap = getCountForQuery(resultList, topCodeArry, "-1", topColumn.replace("a.", ""), leftColumn.replace("a.", ""), null);//统计第一行合计
                list.add(allMap);
                for (String num : leftCodeArry) {
                    allMap = getCountForQuery(resultList, topCodeArry, num, topColumn.replace("a.", ""), leftColumn.replace("a.", ""), null);
                    list.add(allMap);
                }
            } else {
                returnMap.put("leftArray", getLeftArray(leftCodeArry, leftCodeList));//获取左边表头
                list = getTopBiaotou_nndForQuery(list, nndList);//获取上部表头
                allMap = getCountForQuery(resultList, topCodeArry, "-1", topColumn.replace("a.", ""), leftColumn.replace("a.", ""), 2);//统计第一行合计
                list.add(allMap);
                for (String num : leftCodeArry) {
                    allMap = getCountForQuery(resultList, topCodeArry, num, topColumn.replace("a.", ""), leftColumn.replace("a.", ""), 2);
                    list.add(allMap);
                }
            }

            //统计下面的行

            //统计未知
            returnMap.put("list", getOther4ForQuery(list));
            //去掉没有值得行和列
            returnMap = removeCount0ForQuery(returnMap);
            returnMap.put("topCodeNeed", topCodeNeed);
            returnMap.put("leftCodeNeed", leftCodeNeed);
            returnMap.put("fydmList", fydmList == null ? "" : fydmList.stream().collect(Collectors.joining(",")));
            Gson g = new Gson();
            String s = g.toJson(returnMap);
            data = s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

    @Action("customSearchForAgePsy")
    public String customSearchForAgePsy() {
        List<UmsCode> leftCodeList = null, topCodeList = null;


        //解码
        try {
            if(StringUtils.hasText(leftCodeNeed)){
                byte[] decode1 = SimpleDecodeParameter.decode(leftCodeNeed);
                leftCodeNeed = new String(decode1,"utf-8");
            }
            if(StringUtils.hasText(topCodeNeed)){
                byte[] decode1 = SimpleDecodeParameter.decode(topCodeNeed);
                topCodeNeed = new String(decode1,"utf-8");
            }
            if(StringUtils.hasText(fydmList)){
                byte[] decode1 = SimpleDecodeParameter.decode(fydmList);
                fydmList = new String(decode1,"utf-8");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (typeId_left != -99) {

            leftCodeList = new ArrayList<>();
            if (typeId_left == 1) {
                leftCodeList = courtFullService.getUmsCodeFy();
            } else if (typeId_left == 800) {
                //800 为juror_work 职业
                String array[] = new String[]{"基层干部", "人民团体成员", "事业单位职员", "专业技术人员", "工商业人员", "社区工作者", "普通居民", "农民", "进城务工人员", "其他人员"};
                for (String s : array) {
                    UmsCode umsCode = new UmsCode();
                    umsCode.setCodeName(s);
                    umsCode.setId(s);
                    leftCodeList.add(umsCode);
                }
            } else if (typeId_left == 801) {
                //801 member_state 陪审状态
                UmsCode umsCode = new UmsCode();
                umsCode.setCodeName("在任");
                umsCode.setId("01");
                leftCodeList.add(umsCode);
                UmsCode umsCode1 = new UmsCode();
                umsCode1.setCodeName("退出");
                umsCode1.setId("02");
                leftCodeList.add(umsCode1);
            } else {
                leftCodeList = umsCodeService.selectCodesByType(typeId_left);
            }

        }
        if (typeId_top != -99) {
            topCodeList = new ArrayList<>();
            if (typeId_top == 1) {
                topCodeList = courtFullService.getUmsCodeFy();
            } else if (typeId_top == 800) {
                //800 为juror_work 职业
                String array[] = new String[]{"基层干部", "人民团体成员", "事业单位职员", "专业技术人员", "工商业人员", "社区工作者", "普通居民", "农民", "进城务工人员", "其他人员"};
                for (String s : array) {
                    UmsCode umsCode = new UmsCode();
                    umsCode.setCodeName(s);
                    umsCode.setId(s);
                    topCodeList.add(umsCode);
                }
            } else if (typeId_top == 801) {
                //801 member_state 陪审状态
                UmsCode umsCode = new UmsCode();
                umsCode.setCodeName("在任");
                umsCode.setId("01");
                topCodeList.add(umsCode);
                UmsCode umsCode1 = new UmsCode();
                umsCode1.setCodeName("退出");
                umsCode1.setId("02");
                topCodeList.add(umsCode1);
            } else {
                topCodeList = umsCodeService.selectCodesByType(typeId_top);
            }

        }


        String[] leftCodeArry = getCodeArry(leftCodeNeed);
        String[] fydmCodeArry = getCodeArry(fydmList);
        String[] topCodeArry = getCodeArry(topCodeNeed);
        List<String> fydmList = null;
        if (fydmCodeArry != null) {
            fydmList = new ArrayList<>();
            for (String fydm : fydmCodeArry) {
                fydmList.add(fydm);
            }
        }
        List<Map> nndList;
        if (typeId_left == -99)
            nndList = getNndList(leftCodeNeed);
        else
            nndList = getNndList(topCodeNeed);
        Map param = new HashMap<>();
        if (fydmList != null)
            param.put("fydmList", fydmList);
        param.put("nndList", nndList);
        param.put("userType", 3);
        if (typeId_left == -99)
            param.put("topColumn", topColumn);
        else
            param.put("topColumn", leftColumn);
        if (courtLevel != null)
            param.put("courtLevel", courtLevel);

        try {

            List<Map> resultList;

            if (leftColumn.contains("juror_work") || leftColumn.contains("member_state")
                    || topColumn.contains("juror_work") || topColumn.contains("member_state")) {
                // leftColumn 或者 topColumn 中包含 ums_external_user_info 中的额外信息时需要另外查询
                param.put("leftColumn", leftColumn.replace("a.", ""));
                resultList = umsUserInfoService.customSearchForAgePsy(param);

            } else {
                if ("a.court_code".equals(leftColumn) && !StringUtils.isEmpty(leftCodeNeed)) {
                    String[] leftCodeNeedArray = leftCodeNeed.split(",");
                    if (fydmList == null) {
                        fydmList = new ArrayList<>();
                    }
                    for (int i = 0; i < leftCodeNeedArray.length; i++) {
                        fydmList.add(leftCodeNeedArray[i]);
                    }
                }
                if (fydmList != null && fydmList.size() > 0) {
                    param.put("fydmList", fydmList);
                }
                resultList = umsUserInfoService.cunstomSearch_nnd(param);
            }

            if (resultList.size() == 0) {
                Map backMap = new HashMap<>();
                backMap.put("error", "查询结果为空！");
                data = backMap;
                return "json";
            }
            LinkedHashMap<String, QueryEntity> allMap = new LinkedHashMap<>();
            Map returnMap = new HashMap<>();
            List<LinkedHashMap<String, QueryEntity>> list = new ArrayList<>();
            if ("birthday".equals(leftColumn)) {
                returnMap.put("leftArray", getLeftArray_nnd(nndList));//获取左边表头
                list = getTopBiaotou(list, topCodeArry, topCodeList);//获取上部表头
                allMap = getCountForQuery(resultList, topCodeArry, "-1", topColumn.replace("a.", ""), leftColumn.replace("a.", ""), null);//统计第一行合计
                list.add(allMap);
                for (String num : leftCodeArry) {
                    allMap = getCountForQuery(resultList, topCodeArry, num, topColumn.replace("a.", ""), leftColumn.replace("a.", ""), null);
                    list.add(allMap);
                }
            } else {
                returnMap.put("leftArray", getLeftArray(leftCodeArry, leftCodeList));//获取左边表头
                list = getTopBiaotou_nndForQuery(list, nndList);//获取上部表头
                allMap = getCountForQuery(resultList, topCodeArry, "-1", topColumn.replace("a.", ""), leftColumn.replace("a.", ""), 2);//统计第一行合计
                list.add(allMap);
                for (String num : leftCodeArry) {
                    allMap = getCountForQuery(resultList, topCodeArry, num, topColumn.replace("a.", ""), leftColumn.replace("a.", ""), 2);
                    list.add(allMap);
                }
            }

            //统计下面的行

            //统计未知
            returnMap.put("list", getOther4ForQuery(list));
            //去掉没有值得行和列
            returnMap = removeCount0ForQuery(returnMap);
            returnMap.put("topCodeNeed", topCodeNeed);
            returnMap.put("leftCodeNeed", leftCodeNeed);
            returnMap.put("fydmList", fydmList == null ? "" : fydmList.stream().collect(Collectors.joining(",")));
            Gson g = new Gson();
            String s = g.toJson(returnMap);
            data = s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

    //获取以年龄段为表头的表头
    private List<LinkedHashMap<String, Integer>> getTopBiaotou_nnd(List list, List<Map> nndList) {
        List<String> nameList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        nameList.add("合计");
        numList.add(1);
        int i = 2;
        for (Map m : nndList) {
            nameList.add(m.get("name").toString());
            numList.add(i);
            i++;
        }
        nameList.add("未知");
        numList.add(i);
        list.add(nameList);
        list.add(numList);
        return list;
    }

    private List getTopBiaotou_nndForQuery(List list, List<Map> nndList) {
        List<String> nameList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        nameList.add("合计");
        numList.add(1);
        int i = 2;
        for (Map m : nndList) {
            nameList.add(m.get("name").toString());
            numList.add(i);
            i++;
        }
        nameList.add("未知");
        numList.add(i);
        list.add(nameList);
        list.add(numList);
        return list;
    }

    private List<Map> getLeftArray_nnd(List<Map> nndList) {
        List<Map> leftList = new ArrayList<>();
        Map map = new HashMap<>();
        map.put("name", "合计");
        map.put("num", 1);
        leftList.add(map);
        int i = 2;
        for (Map m : nndList) {
            map = new HashMap<>();
            map.put("name", m.get("name"));
            map.put("num", i);
            leftList.add(map);
            i++;
        }
        map = new HashMap<>();
        map.put("name", "未知");
        map.put("num", i);
        leftList.add(map);
        return leftList;
    }

    private List<Map> getNndList(String columnStr) {
        List<Map> nndList = new ArrayList<>();
        String[] columnArray = columnStr.split(",");
        for (String s : columnArray) {
            Map map = new HashMap<>();
            map.put("name", s);
            String[] arr = s.split("-");
            if (arr.length == 2) {
                int start = Integer.parseInt(arr[0]);
                int end = Integer.parseInt(arr[1]);
                map.put("start", start);
                map.put("end", end);
            } else {
                int start = Integer.parseInt(arr[0]);
                map.put("start", start);
            }
            nndList.add(map);
        }
        return nndList;
    }


    //自定义查询中统计查询结果
    private LinkedHashMap<String, Integer> getCount(List<Map> resultList, String[] topList, String type) {
        LinkedHashMap<String, Integer> returnData = new LinkedHashMap<>();
        int countAll = 0;
        int[] count = new int[topList.length];
        int countOther = 0;
        for (Map resultMap : resultList) {
            if (resultMap.get("leftId").toString().equals(type) || type.equals("-1")) {
                countAll += Integer.parseInt(resultMap.get("coutNum").toString());
                for (int i = 0; i < topList.length; i++) {
                    if (resultMap.get("topId").toString().equals(topList[i])) {
                        count[i] += Integer.parseInt(resultMap.get("coutNum").toString());
                        break;
                    }
                }
            }
        }
        int countAllOther = 0;
        returnData.put("countAll", countAll);
        for (int i = 0; i < count.length; i++) {
            returnData.put("count" + i, count[i]);
            countAllOther += count[i];
        }
        countOther = countAll - countAllOther;
        returnData.put("countOther", countOther);
        return returnData;
    }

    //自定义查询中统计查询结果
    private LinkedHashMap<String, QueryEntity> getCountForQuery(List<Map> resultList, String[] topList, String type, String topColumn, String leftColumn, Integer columnType) {
        LinkedHashMap<String, QueryEntity> returnData = new LinkedHashMap<>();
        int countAll = 0;
        int[] count = new int[topList.length];
        int countOther = 0;
        String k1 = "leftId";
        String k2 = "topId";
        if (columnType == null) {
            columnType = 1;
        }
        if (columnType != 1) {
            k1 = "topId";
            k2 = "leftId";
        }
        for (Map resultMap : resultList) {
            if (resultMap.get(k1).toString().equals(type) || type.equals("-1")) {
//                if (
//                        (topList.length > 0 && Arrays.asList(topList).contains(resultMap.get(k2).toString()))
//                        || (StringUtils.isEmpty(resultMap.get(k2).toString()))
//                ) {
                    countAll += Integer.parseInt(resultMap.get("coutNum").toString());
//                }else{
//                    System.out.println(resultMap.get(k2).toString());
//                }
                for (int i = 0; i < topList.length; i++) {
                    if (resultMap.get(k2).toString().equals(topList[i])) {
                        count[i] += Integer.parseInt(resultMap.get("coutNum").toString());
                        break;
                    }
                }
            }
        }
        int countAllOther = 0;
        QueryEntity qall = new QueryEntity();
        qall.setCount(countAll);
        qall.setLeftColumn(leftColumn);
        if (type.equals("-1")) {
            qall.setLeftValue("all");
        } else {
            qall.setLeftValue(type);
        }
        qall.setTopColumn(topColumn);
        qall.setTopValue("all");
        returnData.put("countAll", qall);
        for (int i = 0; i < count.length; i++) {

            QueryEntity inner = new QueryEntity();
            inner.setCount(count[i]);
            inner.setLeftColumn(leftColumn);
            if (type.equals("-1")) {
                inner.setLeftValue("all");
            } else {
                inner.setLeftValue(type);
            }
            inner.setTopColumn(topColumn);
            inner.setTopValue(topList[i]);
            returnData.put("count" + i, inner);
            countAllOther += count[i];

        }
        countOther = countAll - countAllOther;

        QueryEntity otherAll = new QueryEntity();
        otherAll.setCount(countOther);
        otherAll.setLeftColumn(leftColumn);
        if (type.equals("-1")) {
            otherAll.setLeftValue("all");
        } else {
            otherAll.setLeftValue(type);
        }
        otherAll.setTopColumn(topColumn);
        otherAll.setTopValue("other");
        returnData.put("countOther", otherAll);
        return returnData;
    }

    private LinkedHashMap<String, QueryEntity> getCountForQueryForFg(List<Map> resultList, String[] topList, String type, String topColumn, String leftColumn, Integer columnType) {
        LinkedHashMap<String, QueryEntity> returnData = new LinkedHashMap<>();
        int countAll = 0;
        int[] count = new int[topList.length];
//        int countOther = 0;
        String k1 = "leftId";
        String k2 = "topId";
        if (columnType == null) {
            columnType = 1;
        }
        if (columnType != 1) {
            k1 = "topId";
            k2 = "leftId";
        }
        for (Map resultMap : resultList) {
            if (resultMap.get(k1).toString().equals(type) || type.equals("-1")) {
                countAll += Integer.parseInt(resultMap.get("coutNum").toString());
                for (int i = 0; i < topList.length; i++) {
                    if (resultMap.get(k2).toString().equals(topList[i])) {
                        count[i] += Integer.parseInt(resultMap.get("coutNum").toString());
                        break;
                    }
                }
            }
        }
//        int countAllOther = 0;
//        QueryEntity qall = new QueryEntity();
//        qall.setCount(countAll);
//        qall.setLeftColumn(leftColumn);
//        if (type.equals("-1")) {
//            qall.setLeftValue("all");
//        } else {
//            qall.setLeftValue(type);
//        }
//        qall.setTopColumn(topColumn);
//        qall.setTopValue("all");
//        returnData.put("countAll", qall);
        for (int i = 0; i < count.length; i++) {

            QueryEntity inner = new QueryEntity();
            inner.setCount(count[i]);
            inner.setLeftColumn(leftColumn);
            if (type.equals("-1")) {
                inner.setLeftValue("all");
            } else {
                inner.setLeftValue(type);
            }
            inner.setTopColumn(topColumn);
            inner.setTopValue(topList[i]);
            returnData.put("count" + i, inner);
//            countAllOther += count[i];

        }
//        countOther = countAll - countAllOther;

//        QueryEntity otherAll = new QueryEntity();
//        otherAll.setCount(countOther);
//        otherAll.setLeftColumn(leftColumn);
//        if (type.equals("-1")) {
//            otherAll.setLeftValue("all");
//        } else {
//            otherAll.setLeftValue(type);
//        }
//        otherAll.setTopColumn(topColumn);
//        otherAll.setTopValue("other");
//        returnData.put("countOther", otherAll);
        return returnData;
    }

    private LinkedHashMap<String, Integer> getCount2(List<Map> resultList, String[] topList, String type) {
        LinkedHashMap<String, Integer> returnData = new LinkedHashMap<>();
        int countAll = 0;
        int[] count = new int[topList.length];
        int countOther = 0;
        for (Map resultMap : resultList) {
            if (resultMap.get("topId").toString().equals(type) || type.equals("-1")) {
                countAll += Integer.parseInt(resultMap.get("coutNum").toString());
                for (int i = 0; i < topList.length; i++) {
                    if (resultMap.get("leftId").toString().equals(topList[i])) {
                        count[i] += Integer.parseInt(resultMap.get("coutNum").toString());
                        break;
                    }
                }
            }
        }
        int countAllOther = 0;
        returnData.put("countAll", countAll);
        for (int i = 0; i < count.length; i++) {
            returnData.put("count" + i, count[i]);
            countAllOther += count[i];
        }
        countOther = countAll - countAllOther;
        returnData.put("countOther", countOther);
        return returnData;
    }


    private List<Map> getLeftArray(String[] leftList, List<UmsCode> allCode) {
        List<Map> codeNameList = new ArrayList<>();
        int i = 0;
        Map addMap = new HashMap<>();
        addMap.put("name", "合计");
        addMap.put("num", 1);
        codeNameList.add(addMap);
        if (leftList != null && leftList.length > 0) {
            for (String num : leftList) {
                for (UmsCode code : allCode) {
                    if (code.getId().toString().equals(num)) {

                        addMap = new HashMap<>();
                        addMap.put("name", code.getCodeName());
                        addMap.put("num", i + 2);
                        codeNameList.add(addMap);
                        i++;
                    }
                }
            }
        }
        addMap = new HashMap<>();
        addMap.put("name", "未知");
        addMap.put("num", i + 2);
        codeNameList.add(addMap);
        return codeNameList;
    }


    private List<String> getLeftStr(List<UmsCode> topNeed) {
        List<String> aa = new ArrayList<>();
        for (UmsCode code : topNeed) {
            aa.add(code.getId() + "");
        }
        return aa;
    }

    //统计报表的 6 张固定表
    @Action("getNewReportTable")
    public String getNewReportTable() {
        Map alldataMap = new HashMap();
        Map<String, MergeConfig[]> mergeMap = new HashMap<>();


        noDecode = true;
        MergeConfig[] deptMerge = {
                new MergeConfig(new String[]{"刑事"}, "刑庭", true),
                new MergeConfig(new String[]{"民事"}, "民庭", true),
                new MergeConfig(new String[]{"人民法庭"}, "人民法庭", true),
                new MergeConfig(new String[]{"立案"}, "立案庭", true),
                new MergeConfig(new String[]{"纪检", "监察"}, "纪检处<br>监察室", true),
                new MergeConfig(new String[]{"其他业务"}, "其他业务部门", true),
                new MergeConfig(new String[]{"其他行政"}, "其他行政后勤", true),
                new MergeConfig(new String[]{"宣传处", "未知", "党委(总支、支部)", "法官中心", "技术(局、处、科)", "法医(处、科、室)", "来访接待(站、室)", "审判委员会办公室", "督导室", "速裁庭", "信息中心", "审管办", "财务处", "环境保护审判庭", "司法行政(厅、处、科)"}, "其他", true, false),
        };

        if ("fss_01_1".equals(typeStr) || "fss_01_3".equals(typeStr) || "fss_01_5".equals(typeStr) || "fss_01_9".equals(typeStr)) {
            //性别 gender，民族 nation，政治面貌 political，年龄 birthday，文化程度 education_background，专业证书 pro_cert
            //法律职务 law_position
            leftColumn = "law_position_report";
            typeId_left = 1016;
            if (typeStr.equals("fss_01_3")) {
                leftColumn = "administration_position_report";
                typeId_left = 1015;
            } else if (typeStr.equals("fss_01_5")) {
                leftColumn = "dept_org_code";
                typeId_left = 900;
            } else if (typeStr.equals("fss_01_9")) {
                leftColumn = "personnel_classification";
                typeId_left = 94;
            }
            leftCodeNeed = "all";

            LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
            map.put("gender", 3);
            map.put("nation", 5);
            map.put("political", 13);
            map.put("birthday", -99);
            map.put("education_background", 11);
            map.put("pro_cert", 47);

            mergeMap.put("nation", new MergeConfig[]{new MergeConfig(new String[]{"汉族"}, "少数民族", false)});
            mergeMap.put("political", new MergeConfig[]{new MergeConfig(new String[]{"中共党员", "共青团员"}, "其他", false, false)});
            mergeMap.put("birthday", new MergeConfig[]{new MergeConfig(new String[]{"未知"}, null, true)});
            mergeMap.put("gender", new MergeConfig[]{new MergeConfig(new String[]{"未知"}, null, true)});
            MergeConfig[] eduMerge = {
                    new MergeConfig(new String[]{"博士研究生"}, "博士", true),
                    new MergeConfig(new String[]{"硕士研究生"}, "硕士", true),
                    new MergeConfig(new String[]{"研究生"}, "研究生", true),
                    new MergeConfig(new String[]{"大学", "双学士"}, "大学", true),
                    new MergeConfig(new String[]{"专科"}, "大专", true),
                    new MergeConfig(new String[]{"中专", "中技", "技工", "高中"}, "高中中专", true),
                    new MergeConfig(new String[]{"初中"}, "初中", true),
                    new MergeConfig(new String[]{"小学", "文盲"}, "小学及以下", true)
            };
            mergeMap.put("education_background", eduMerge);
            mergeMap.put("pro_cert", new MergeConfig[]{
                    new MergeConfig(new String[]{"专业证书"}, "专业证书", true),
                    new MergeConfig(new String[]{"专业证书"}, null, false)
            });

            try {
                for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
                    topColumn = stringIntegerEntry.getKey();
                    typeId_top = stringIntegerEntry.getValue();
                    sixTableMap = new HashMap();
                    if (topColumn.equals("birthday")) {
                        topCodeNeed = "0-35,36-40,41-45,46-50,51-55,56-60,61-70";
                        customSearch_nnd();
                    } else {
                        topCodeNeed = "all";
                        customSearch();
                    }
                    if (typeStr.equals("fss_01_5")) {
                        mergeData(alldataMap, mergeMap.get(topColumn), deptMerge);
                    } else {
                        mergeData(alldataMap, mergeMap.get(topColumn), null);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Map param = new HashMap();
            param.put("topColumn", leftColumn);
            List<Map> mapList = umsUserInfoService.customSearch_avgnnd(param);
            Map avgMap = new HashMap();
            for (Map map1 : mapList) {
                avgMap.put(map1.get("topId"), map1.get("a"));
            }
            alldataMap.put("nnavg", avgMap);

        } else if ("fss_01_2".equals(typeStr) || "fss_01_4".equals(typeStr)) {
            topColumn = "dept_org_code";
            typeId_top = 900;
            leftColumn = "law_position_report";
            typeId_left = 1016;
            leftCodeNeed = "all";
            topCodeNeed = "all";
            if (typeStr.equals("fss_01_4")) {
                topColumn = "administration_position_report";
                typeId_top = 1015;
            }

            customSearch();
            return "json";
        } else if ("fss_01_6".equals(typeStr)) {
            topColumn = "dept_org_code";
            typeId_top = 900;
            leftColumn = "court_gradation";
            typeId_left = 901;
            leftCodeNeed = "all";
            topCodeNeed = "all";

            sixTableMap = new HashMap();
            customSearch();

            MergeConfig[] hMerge = {
                    new MergeConfig(new String[]{"未知"}, null, true)
            };
            alldataMap = new HashMap();
            try {
                mergeData(alldataMap, deptMerge, hMerge);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if ("fss_01_10".equals(typeStr)) {
            //法官助理和书记员  辅助人员等级统计表
            otherParam = " and personnel_classification in (22,23)";
            topColumn = "level";
            typeId_top = 20;
            leftColumn = "a.court_code";
            typeId_left = 1;
            leftCodeNeed = "all";
            topCodeNeed = "all";
            try {
                customSearch();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "json";
        } else if ("fss_01_11".equals(typeStr)) {
            //法官助理和书记员  辅助人员等级统计表
            otherParam = null;
            topColumn = "部门"; // 包括刑事、民事、执行、立案、行政 5个部门
            typeId_top = 21;
            leftColumn = "a.court_code";
            typeId_left = 1;
            leftCodeNeed = "all";
            topCodeNeed = "all";
            try {
                customSearchForDept();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "json";
        } else if ("fss_01_12".equals(typeStr)) {
            // 入额法官统计
            otherParam = null;
            topColumn = "a.judge_level";
            typeId_top = 117;
            leftColumn = "a.court_code";
            typeId_left = 1;
            leftCodeNeed = "all";
            topCodeNeed = "all";
            HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
            String startDate = httpServletRequest.getParameter("startDate");
            String endDate = httpServletRequest.getParameter("endDate");
            data = customSearchForFg(startDate, endDate);
            return "json";
        } else if ("fss_01_13".equals(typeStr)) {
            // 法官助理和书记员  辅助人员等级统计表
            otherParam = null;
            topColumn = "a.judge_level";
            typeId_top = 117;
            leftColumn = "a.court_code";
            typeId_left = 1;
            leftCodeNeed = "all";
            topCodeNeed = "all";
            HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
            String startDate = httpServletRequest.getParameter("startDate");
            String endDate = httpServletRequest.getParameter("endDate");
            data = customSearchForTeFg(startDate, endDate);
            return "json";
        }

        Gson gson = new Gson();
        data = gson.toJson(alldataMap);
        return "json";
    }

    /**
     * 合并 sixTableMap 和r resMap 的数据
     *
     * @param resMap
     * @param vmergeMap sixTableMap 的列合并规则
     * @param hmergeMap sixTableMap 的行合并规则
     */
    public void mergeData(Map resMap, MergeConfig[] vmergeMap, MergeConfig[] hmergeMap) {
        List sixList = (List) sixTableMap.get("list");
        //合并 列 对应数据
        if (vmergeMap != null) {
            for (int k = 0; k < vmergeMap.length; k++) {
                MergeConfig merge = vmergeMap[k];
                //isdelete true 表示 oldtext 里的合并为 newText，false 表示除了 oldtext 的合并为 newText
                //newText null则是直接删除

                List<String> six0List = (List<String>) sixList.get(0);
                List six1List = (List) sixList.get(1);
                List<Integer> indexs = new ArrayList<>();
                List<String> indexStr = new ArrayList<>();
                //处理列标题和数据
                for (int i = 1; i < six0List.size(); i++) {
                    boolean flag = false;
                    for (String s1 : merge.getOldText()) {
                        if (six0List.get(i).contains(s1)) {
                            flag = true;
                            break;
                        }
                    }
                    //将需要移除的列名记录
                    if ((merge.isDelete() && flag) || (!merge.isDelete() && !flag)) {
                        indexStr.add(six0List.get(i));
                        indexs.add(i);
                    }
                }
                if (indexs.size() == 0) continue;
                //移除列名及列序号
                six0List.removeAll(indexStr);
                int length = 0;
                if (merge.getNewText() != null) {
                    six0List.add((six0List.size() > indexs.get(0) && merge.isSaveSort()) ? indexs.get(0) : six0List.size(), merge.getNewText());
                    length = indexs.size() - 1;
                } else {
                    length = indexs.size();
                }
                for (int i = 0; i < length; i++) {
                    six1List.remove(six1List.size() - 1);
                }
                //移除列数据及合并移除的列数据到新列
                for (int i = 2; i < sixList.size(); i++) {
                    LinkedHashMap<String, QueryEntity> sixiMap = (LinkedHashMap<String, QueryEntity>) sixList.get(i);
                    QueryEntity entity = new QueryEntity();
                    StringBuilder s = new StringBuilder(" in (");
                    Iterator<Map.Entry<String, QueryEntity>> iterator = sixiMap.entrySet().iterator();
                    int index = 0;
                    while (iterator.hasNext()) {
                        QueryEntity queryEntity = iterator.next().getValue();
                        if (indexs.contains(index)) {
                            entity.count += queryEntity.getCount();
                            s.append(queryEntity.getTopValue()).append(",");
                            entity.setTopColumn(queryEntity.getTopColumn());
                            entity.setLeftValue(queryEntity.getLeftValue());
                            entity.setLeftColumn(queryEntity.getLeftColumn());
                            iterator.remove();
                        }
                        index++;
                    }
                    s.deleteCharAt(s.length() - 1);
                    s.append(")");
                    entity.setTopValue(s.toString());
                    if (merge.getNewText() != null) {
                        //为了保证顺序，需要重新创建 linkedhashmap
                        if (sixiMap.size() <= indexs.get(0) || !merge.isSaveSort()) {
                            sixiMap.put("counto" + entity.getTopColumn() + k, entity);
                            continue;
                        }
                        LinkedHashMap newlink = new LinkedHashMap();
                        iterator = sixiMap.entrySet().iterator();
                        index = 0;
                        while (iterator.hasNext()) {
                            Map.Entry<String, QueryEntity> next = iterator.next();
                            QueryEntity queryEntity = next.getValue();
                            if (indexs.get(0) == index) {
                                newlink.put("counto" + entity.getTopColumn() + k, entity);
                            }
                            newlink.put(next.getKey(), queryEntity);
                            index++;
                        }
                        sixList.set(i, newlink);
                    }
                }

            }
        }
        //合并 行 数据
        if (hmergeMap != null) {
            List<Map> leftArray = (List<Map>) sixTableMap.get("leftArray");
            for (int k = 0; k < hmergeMap.length; k++) {
                MergeConfig merge = hmergeMap[k];
                //isdelete true 表示 oldtext 里的合并为 newText，false 表示除了 oldtext 的合并为 newText
                //newText null则是直接删除

                List<Integer> indexs = new ArrayList<>();
                List<Map> indexMap = new ArrayList<>();
                //处理左侧标题和顺序
                for (int i = 0; i < leftArray.size(); i++) {
                    boolean flag = false;
                    for (String s1 : merge.getOldText()) {
                        if (leftArray.get(i).get("name").toString().contains(s1)) {
                            flag = true;
                            break;
                        }
                    }
                    //将需要移除的列名记录
                    if ((merge.isDelete() && flag) || (!merge.isDelete() && !flag)) {
                        indexMap.add(leftArray.get(i));
                        indexs.add(i);
                    }
                }

                if (indexs.size() == 0) continue;

                //    先处理行数据，不然不知道该处理哪个
                //移除行数据及合并移除的行数据到新行
                List<LinkedHashMap<String, QueryEntity>> removeList = new ArrayList<>();
                //需要反向移除，不然会出问题
                for (int i = indexs.size() - 1; i >= 0; i--) {
                    int j = indexs.get(i);
                    LinkedHashMap<String, QueryEntity> sixiMap = (LinkedHashMap<String, QueryEntity>) sixList.remove(j + 2);
                    removeList.add(sixiMap);
                }
                //合并移除的数据并设置回行数据中
                if (merge.getNewText() != null) {
                    LinkedHashMap<String, QueryEntity> newLink = new LinkedHashMap<>();
                    for (LinkedHashMap<String, QueryEntity> oldLink : removeList) {
                        if (newLink.size() == 0) {
                            newLink = oldLink;
                            continue;
                        }
                        for (String s : oldLink.keySet()) {
                            QueryEntity newEntity = newLink.get(s);
                            QueryEntity oldEntity = oldLink.get(s);
                            if (newEntity.getLeftValue().contains("in")) {
                                newEntity.setLeftValue(newEntity.getLeftValue().substring(0, newEntity.getLeftValue().length() - 1) + "," + oldEntity.getLeftValue() + ")");
                            } else {
                                String newLeftValue = newEntity.getLeftValue().equals("other") ? ("in (,null," + oldEntity.getLeftValue() + ")") : ("in (" + newEntity.getLeftValue() + "," + oldEntity.getLeftValue() + ")");
                                newEntity.setLeftValue(newLeftValue);
                            }
                            newEntity.setCount(newEntity.getCount() + oldEntity.getCount());
                        }
                    }
                    sixList.add((sixList.size() > (indexs.get(0) + 2) && merge.isSaveSort()) ? (indexs.get(0) + 2) : sixList.size(), newLink);
                }

                //再移除行
                leftArray.removeAll(indexMap);

                if (merge.getNewText() != null) {
                    Map map = new HashMap();
                    map.put("name", merge.getNewText());
                    map.put("num", 0);
                    leftArray.add((leftArray.size() > indexs.get(0) && merge.isSaveSort()) ? indexs.get(0) : leftArray.size(), map);
                }

                //    重新生成 list 内的 num 字段
                for (int i = 0; i < leftArray.size(); i++) {
                    leftArray.get(i).put("num", (i + 1));
                }

            }
        }
        if (resMap.size() == 0) {
            resMap.putAll(sixTableMap);
        } else {
            //    合并数据
            List reslist = (List) resMap.get("list");
            int length = 0;
            for (int i = 0; i < sixList.size(); i++) {
                if (i == 0) {   //移除除了第一个的所有“合计”，并添加所有列
                    List res0list = (List) reslist.get(0);
                    List six0List = (List) sixList.get(0);
                    six0List.remove(i);
                    res0list.addAll(six0List);
                } else if (i == 1) {    //添加列数
                    List resNum = (List) reslist.get(1);
                    List sixNum = (List) sixList.get(1);
                    length = resNum.size();
                    for (int k = 0; k < sixNum.size() - 1; k++) {
                        resNum.add((k + length + 1));
                    }
                } else {
                    Map resiMap = (Map) reslist.get(i);
                    Map sixiMap = (Map) sixList.get(i);
                    for (Object o : sixiMap.keySet()) {
                        if (!o.toString().equals("countAll")) {
                            resiMap.put(o.toString() + length, sixiMap.get(o));
                        }
                    }
                }
            }
        }
    }

    @Action("getAllCodeType")
    public String getAllCodeType() {
        List<Map> codeTypeList = umsCodeTypeService.getAllCodeType();
        data = codeTypeList;
        return "json";
    }


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

    //下载附件
    @Action("downLoadExcel")
    public String downLoad() {

        RowBounds rowBounds = new RowBounds();

        //解码
        try {
            byte[] decode = SimpleDecodeParameter.decode(selectData);
            selectData = new String(decode,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (!isNullOrEmpty(fullname)) {
            selectData += " and fullname like '%" + fullname + "%' ";
        }
        if (gender != null) {
            selectData += " and gender =" + gender + " ";
        }
        if (administrationPosition != null) {
            selectData += " and administration_position =" + administrationPosition + " ";
        }
        if (rank != null) {
            selectData += " and rank =" + rank + " ";
        }
        if (enterDateStart != null) {
            selectData += " and enter_date >= '" + enterDateStart + "' ";
        }
        if (enterDateEnd != null) {
            selectData += " and enter_date <= '" + enterDateEnd + "' ";
        }

        if (userType != null && !selectData.contains("user_type")) {
            selectData += " and user_type = " + userType + " ";
        } else if (!selectData.contains("user_type")) {
            selectData += " and user_type = 1 ";
        }

        List<UmsUserInfoView> umsUserInfoViews = null;

        if (selectData.contains("enter_src") || selectData.contains("company_info_id")) {
            try {
                umsUserInfoViews = umsUserInfoViewService.selectCustomM(selectData, rowBounds);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (selectData.contains("juror_")) {
            //陪审员
            selectData = selectData.replace("juror_", "");
            try {
                umsUserInfoViews = umsUserInfoViewService.selectCustomK(selectData, rowBounds);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            try {
                umsUserInfoViews = umsUserInfoViewService.selectCustomN(selectData, rowBounds);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (umsUserInfoViews == null || umsUserInfoViews.size() == 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("msg", "找不到这个附件或附件已删！");
            data = map;
            return "json";

        }


        //标题组

//        人员花名册：姓名、法院、部门、性别、民族、籍贯、出生日期、年龄、学历、学位、法律职务（人员分类）、
//        取得日期、法官等级、取得日期、行政职务、取得日期、职级、取得日期、专业、学校
//        姓名、法院、部门、性别、民族、籍贯、出生日期、年龄、学历、学位、法律职务（人员分类）、取得日期、法官等级、取得日期、行政职
//        务、取得日期、职级、取得日期、专业、学校
        String title[] = {"fullname", "courtNoText", "departmentText", "genderText", "nationText", "hometown", "birthday", "age", "educationBackgroundText",
                "degreeText", "lawPositionText", "lawPositionDate", "levelText", "levelDate", "administrationPositionText", "administrationPositionDate"
                , "rankText", "rankDate", "majorText", "school"};
        String title_u1[] = {"姓名", "法院", "部门", "性别", "民族", "籍贯", "出生日期", "年龄", "学历", "学位", "法律职务", "取得日期(法律职务)", "法官等级", "取得日期(法官等级)"
                , "行政职务", "取得日期(行政职务)", "职级", "取得日期(职级)", "专业", "学校",};
        //编外人员头信息
        if (userType != null && userType == 2) {

            title = new String[]{"fullname", "courtNoText", "departmentText", "genderText", "nationText", "hometown", "birthday", "age", "educationBackgroundText",
                    "degreeText"};
            title_u1 = new String[]{"姓名", "法院", "部门", "性别", "民族", "籍贯", "出生日期", "年龄", "学历", "学位"};

        }
        String title_extend[] = {"enterText", "companyText"};
        String title_extend_ul[] = {"编外人员来源", "公司名称"};
        //陪审员
        if (userType != null && userType == 3) {

            title = new String[]{"fullname", "courtNoText", "departmentText", "genderText", "nationText", "hometown", "birthday", "age", "beginTime", "educationBackgroundText",
            };
            title_u1 = new String[]{"姓名", "法院", "部门", "性别", "民族", "籍贯", "出生日期", "年龄", "任命日期", "学历"};

            title_extend = new String[]{"c_ps_xlxw", "company", "juror_work", "regional", "number_of_times", "type_of_case", "member_state"};
            title_extend_ul = new String[]{"学历学位", "单位", "职业", "地区分布", "届数", "参与案件类型", "陪审状态"};

        }


        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        XSSFWorkbook workbook = new XSSFWorkbook();
        String sheetName = "人员信息";
        XSSFSheet sheet = workbook.createSheet(sheetName);

        //格式
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER); //水平居中
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); //垂直居中
        style.setWrapText(true); //强制换行

        XSSFCellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        dateStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        XSSFDataFormat format = workbook.createDataFormat();
        dateStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
        sheet.setDefaultColumnWidth(20);
        sheet.setColumnWidth(1, 32 * 256);
//        sheet.setColumnWidth(8, 22 * 256);
//        sheet.setColumnWidth(14, 22 * 256);
//        sheet.setColumnWidth(title.length - 1, 10 * 256);
        sheet.setDefaultRowHeight((short) (68 * 20));
        if (userType != null && userType == 2) {
            sheet.setColumnWidth(11, 35 * 256);
        }

        int row_id = 0;
        XSSFRow row_title = workbook.getSheet(sheetName).createRow(row_id++);    //创建第一行
        row_title.setHeightInPoints(25);
        for (int i = 0; i < title_u1.length; i++) {
            XSSFCell cell = row_title.createCell(i);
            cell.setCellValue(title_u1[i]);
            cell.setCellStyle(style);
        }
        Map<String, List<Map<String, Object>>> collect = null;
        if (userType != null && 2 == userType) {
            for (int i = 0; i < title_extend_ul.length; i++) {
                XSSFCell cell = row_title.createCell(i + title_u1.length);
                cell.setCellValue(title_extend_ul[i]);
                cell.setCellStyle(style);
            }

            List<Map<String, Object>> maps = umsUserInfoViewService.selectCustomF(selectData);
            if (maps.size() > 0) {

                try {
                    collect = maps.stream().filter(stringObjectMap -> stringObjectMap.get("user_id") != null).collect(Collectors.groupingBy(o -> o.get("user_id").toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (userType != null && 3 == userType) {
            for (int i = 0; i < title_extend_ul.length; i++) {
                XSSFCell cell = row_title.createCell(i + title_u1.length);
                cell.setCellValue(title_extend_ul[i]);
                cell.setCellStyle(style);
            }

            List<Map<String, Object>> maps = umsUserInfoViewService.selectCustomG(selectData);
            if (maps.size() > 0) {

                try {
                    collect = maps.stream().filter(stringObjectMap -> stringObjectMap.get("user_id") != null).collect(Collectors.groupingBy(o -> o.get("user_id").toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

//        selectCustomG


        for (UmsUserInfoView umsUserInfoView : umsUserInfoViews) {

            XSSFRow row_title_2 = workbook.getSheet(sheetName).createRow(row_id++);    //创建第二行
            row_title_2.setHeightInPoints(25);
            for (int i = 0; i < title.length; i++) {
                XSSFCell cell = row_title_2.createCell(i);

                String t = title[i];
                Field declaredField = null;
                try {

                    if (t.equals("age")) {
                        Date birthday = umsUserInfoView.getBirthday();
                        if (birthday != null) {
                            String age = DateUtil.getAge(birthday);
                            cell.setCellValue(age);
                            cell.setCellStyle(style);
                            continue;
                        }
                    }

                    if (t.equals("school")) {
                        try {
                            String id = umsUserInfoView.getId();

                            Map<String, Object> map = attachedTableService.selectViewPresent("education_info", id);
                            if (map != null) {

                                cell.setCellValue(map.getOrDefault("c_college", "").toString());
                                cell.setCellStyle(style);

                            } else {

                                cell.setCellValue("");
                                cell.setCellStyle(style);
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        continue;
                    }

                    declaredField = UmsUserInfoView.class.getDeclaredField(t);
                    if (declaredField != null) {
                        declaredField.setAccessible(true);
                        Object obj = declaredField.get(umsUserInfoView);

                        if (obj != null) {

                            if (obj instanceof String) {

                                cell.setCellValue((String) obj);
                                cell.setCellStyle(style);

                            } else if (obj instanceof Integer) {

                                cell.setCellValue((Integer) obj);
                                cell.setCellStyle(style);

                            } else if (obj instanceof Date) {

                                cell.setCellValue((Date) obj);
                                cell.setCellStyle(dateStyle);

                            }

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            String id = umsUserInfoView.getId();
            if ((userType != null) && (userType == 2 || userType == 3) && (collect != null) && StringUtils.hasText(id)) {

                for (int i = 0; i < title_extend.length; i++) {

                    XSSFCell cell = row_title_2.createCell(i + title.length);

                    String t = title_extend[i];
                    List<Map<String, Object>> maps = collect.get(id);

                    if (maps != null && maps.size() > 0) {
                        Map<String, Object> map = maps.get(0);
                        String obj = map.getOrDefault(t, "").toString();
                        if (obj != null) {

                            cell.setCellValue(obj);
                            cell.setCellStyle(style);

                        }
                    }

                }


            }

        }


        ByteArrayOutputStream out = null;
        try {

            out = new ByteArrayOutputStream();
            workbook.write(out);
            this.inputStream = new ByteArrayInputStream(out.toByteArray());
            filename = "人员信息.xlsx";
            return "stream";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return "json";
//        Map<String,Object> map = new HashMap<>();
//        try {
//            if(uploadFile != null && StringUtils.hasText(uploadFile.getId()))
//            {
//                UmsInstitutionUploadFile uploadFile = uploadFileService.selectByPrimaryKey(this.uploadFile.getId());
//                if(uploadFile!= null && StringUtils.hasText(uploadFile.getSavePath()))
//                {
//                    byte[] bytes = FileUtils.readFileToByteArray(new File(uploadFile.getSavePath()));
//                    this.inputStream = new ByteArrayInputStream(bytes);
//                    filename = uploadFile.getFileName();
//                    return "stream";
//                }
//                else
//                {
//                    map.put("msg","找不到这个附件或附件已删！");
//                    data = map;
//                    return "json";
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            map.put("msg","找不到这个附件或附件已删！");
//            data = map;
//        }
    }

    //下载附件
    @Action("downLoadExcelNew")
    public String downLoadExcelNew() {

        RowBounds rowBounds = new RowBounds();
        //解码
        try {
            byte[] decode = SimpleDecodeParameter.decode(selectData);
            selectData = new String(decode,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (!isNullOrEmpty(fullname)) {
            selectData += " and fullname like '%" + fullname + "%' ";
        }
        if (gender != null) {
            selectData += " and gender =" + gender + " ";
        }
        if (administrationPosition != null) {
            selectData += " and administration_position =" + administrationPosition + " ";
        }
        if (rank != null) {
            selectData += " and rank =" + rank + " ";
        }
        if (enterDateStart != null) {
            selectData += " and enter_date >= '" + enterDateStart + "' ";
        }
        if (enterDateEnd != null) {
            selectData += " and enter_date <= '" + enterDateEnd + "' ";
        }

        if (userType != null && !selectData.contains("user_type")) {
            selectData += " and user_type = " + userType + " ";
        } else if (!selectData.contains("user_type")) {
            selectData += " and user_type = 1 ";
        }

        List<UmsUserInfoView> umsUserInfoViews = null;

        if (selectData.contains("enter_src") || selectData.contains("company_info_id")) {
            try {
                umsUserInfoViews = umsUserInfoViewService.selectCustomM(selectData, rowBounds);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (selectData.contains("juror_")) {
            //陪审员
            selectData = selectData.replace("juror_", "");
            try {
                umsUserInfoViews = umsUserInfoViewService.selectCustomK(selectData, rowBounds);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            try {
                umsUserInfoViews = umsUserInfoViewService.selectCustomN(selectData, rowBounds);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (umsUserInfoViews == null || umsUserInfoViews.size() == 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("msg", "找不到这个附件或附件已删！");
            data = map;
            return "json";

        }
        if (userType == null){
            userType = 1;
        }
        if (1 == userType) {
            byte[] bytes = generateCommonExcel.generateExcel(1,umsUserInfoViews, null, Collections.singletonList("education_info"), null,selectData);
            if (bytes == null || bytes.length == 0) {
                return "json";
            } else {
                this.inputStream = new ByteArrayInputStream(bytes);
                filename = "在编人员信息.xls";
                return "stream";
            }
        }else if (2 == userType) {
            byte[] bytes = generateCommonExcel.generateExcel(2,umsUserInfoViews, null, null, null,null);
            if (bytes == null || bytes.length == 0) {
                return "json";
            } else {
                this.inputStream = new ByteArrayInputStream(bytes);
                filename = "编外人员信息.xls";
                return "stream";
            }
        }else if (3 == userType) {
            byte[] bytes = generateCommonExcel.generateExcel(3,umsUserInfoViews, null, null, null,null);
            if (bytes == null || bytes.length == 0) {
                return "json";
            } else {
                this.inputStream = new ByteArrayInputStream(bytes);
                filename = "陪审员信息.xls";
                return "stream";
            }
        }

        return "json";





    }


    //反查数据
    @Action("fc")
    public String fc() {

        Map<String, Object> re = new HashMap<>();
        data = re;
        if (queryEntity != null && userType != null) {

            String leftColumn = queryEntity.getLeftColumn();
            String leftValue = queryEntity.getLeftValue();
            String topColumn = queryEntity.getTopColumn();
            String topValue = queryEntity.getTopValue();
            if (!StringUtils.hasText(leftColumn) || !StringUtils.hasText(leftValue) || !StringUtils.hasText(topColumn) || !StringUtils.hasText(topValue)) {
                return "json";
            }
            //年龄要另外算
            if (userType == 1) {

                try {
                    UmsUserInfoViewCriteria example = makeFcExample(queryEntity, userType);

                    if (example != null) {

                        if (typeStr != null && typeStr.equals("fss_01_10")) {
                            example.getOredCriteria().get(0).andPersonnelClassificationIn(Arrays.asList("22", "23"));
                        }

                        RowBounds rowBounds = new RowBounds(start, limit);
                        List<UmsUserInfoView> infoByExample = umsUserInfoViewService.getInfoByExample(example, rowBounds);

                        int i = umsUserInfoViewService.count(example);
                        re.put("rows", infoByExample);
                        re.put("results", i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (userType == 2 || userType == 3) {

                UmsUserInfoExternalCriteria example = makeFcExampleT(queryEntity, userType);

                if (example != null) {

                    example.setStart(start);
                    example.setLimit(limit);

                    List<UmsUserInfoView> infoByExample = null;
                    int i = 0;
                    if (userType == 2) {
                        try {
                            infoByExample = umsUserInfoViewService.selectByExampleLeftJoinExternal(example);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        i = umsUserInfoViewService.countByExampleLeftJoinExternal(example);
                    } else {
                        try {
                            infoByExample = umsUserInfoViewService.selectByExampleLeftJoinPsy(example);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        i = umsUserInfoViewService.countByExampleLeftJoinPsy(example);
                    }
                    re.put("rows", infoByExample);
                    re.put("results", i);
                }

            } else {
                re.put("rows", new ArrayList<>());
                re.put("results", 0);
            }

        }
        return "json";
    }


    //法官等级反查数据
    @Action("judgeLevelFc")
    public String judgeLevelFc() {

        Map<String, Object> re = new HashMap<>();
        data = re;
        try {
            if (queryEntity != null ) {

                String leftColumn = queryEntity.getLeftColumn();
                String leftValue = queryEntity.getLeftValue();
                String topColumn = queryEntity.getTopColumn();
                String topValue = queryEntity.getTopValue();
                if (!StringUtils.hasText(leftColumn)  || !StringUtils.hasText(topColumn)) {
                    return "json";
                }
                Map<String,Object> queryMap = new HashMap<>();
                if(StringUtils.hasText(leftValue)){
                    queryMap.put(leftColumn,leftValue);
                }
                if(StringUtils.hasText(topValue)){
                    queryMap.put(topColumn,topValue);
                }
                queryMap.put("start",start);
                queryMap.put("limit",limit);
                List<UmsUserInfoView> gList = sqlSession.selectList("StatisticalTableOfJudgesMapper.fcSelectRankOfJudgesNew", queryMap);
                re.put("rows", gList);
                Object o = sqlSession.selectOne("StatisticalTableOfJudgesMapper.fcCountRankOfJudgesNew", queryMap);
                re.put("results", o);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }


    //生成反查条件
    private UmsUserInfoViewCriteria makeFcExample(QueryEntity queryEntity, Integer userType) {

        String leftColumn = queryEntity.getLeftColumn();
        String leftValue = queryEntity.getLeftValue();
        String topColumn = queryEntity.getTopColumn();
        String topValue = queryEntity.getTopValue();
        String topCodeNeed = queryEntity.getTopCodeNeed();
        String leftCodeNeed = queryEntity.getLeftCodeNeed();
        String fydmList = queryEntity.getFydmList();
        int define = queryEntity.getDefine();

        if (!StringUtils.hasText(leftColumn) || !StringUtils.hasText(leftValue) || !StringUtils.hasText(topColumn) || !StringUtils.hasText(topValue)) {
            return null;
        }

        try {

            UmsUserInfoViewCriteria example = new UmsUserInfoViewCriteria();
            UmsUserInfoViewCriteria.Criteria criteria = example.createCriteria();
            criteria.andIsValidEqualTo(1);
            if (userType == 1) {
                criteria.andUserTypeEqualTo(1);
            }
            if (!leftColumn.equals("court_code") && !topColumn.equals("court_code")) {
                if (fydmList != null) {
                    List<String> list = Arrays.asList(fydmList.split(","));
                    if (list.size() > 0)
                        criteria.andCourtCodeIn(list);
                } else if (StringUtils.hasText(courtCode)) {
                    criteria.andCourtCodeEqualTo(courtCode);
                }
                if (StringUtils.hasText(preparation)) {
                    criteria.andPreparationEqualTo(Integer.parseInt(preparation));
                }
            }
            if (!leftColumn.equals("court_gradation")) {
                criteria = doExample(criteria, leftColumn, leftValue, topCodeNeed, leftCodeNeed, "left", fydmList, define);
            }
            criteria = doExample(criteria, topColumn, topValue, topCodeNeed, leftCodeNeed, "top", fydmList, define);

            if (criteria == null) {
                return null;
            }

            //特殊处理 联查ums_court_full
            if (leftColumn.equals("court_gradation") && !leftValue.equals("all")) {
                criteria.andExistCondition("  EXISTS (select 1 from ums_court_full where court_code = ums_court_full.court_code and court_gradation=" + leftValue + ") ");
            } else if (!leftColumn.equals("court_code") && !topColumn.equals("court_code") && courtLevel != null) {
                criteria.andExistCondition("  EXISTS (select 1 from ums_court_full where court_code = ums_court_full.court_code and court_gradation=" + courtLevel + ") ");
            } else {
                criteria.andExistCondition("  EXISTS (select 1 from ums_court_full where court_code = ums_court_full.court_code ) ");
            }
            return example;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    //
    private UmsUserInfoViewCriteria.Criteria doExample(UmsUserInfoViewCriteria.Criteria criteria, String column, String value, String topCodeNeed, String leftCodeNeed, String type, String fydmList, int define) {

        try {

            if (criteria == null) {
                return null;
            }

            if (!StringUtils.isEmpty(fydmList)) {
                String[] fydmArray = fydmList.split(",");
                criteria.andCourtCodeIn(Arrays.asList(fydmArray));
            }

            if (column.equals("b.judge_level")) {
                if (value.equals("all")) {
                    String sql = " EXISTS ( SELECT 1 FROM ums_level_info " +
                            "WHERE ums_user_info_view.id = ums_level_info.user_id " +
                            "AND n_level_type = 1 AND n_present_info = 1 " +
                            "AND ( yefg_start_time BETWEEN '" + queryEntity.startDate + "' AND '" + queryEntity.endDate +
                            "' OR yefg_end_time BETWEEN '" + queryEntity.startDate + "' AND '" + queryEntity.endDate + "' ) )";
                    criteria.andExistCondition(sql);
                } else if (!value.equals("all") && !value.equals("yefg") && !value.equals("tefg")) {
                    String sql = " EXISTS ( SELECT 1 FROM ums_level_info " +
                            "WHERE ums_user_info_view.id = ums_level_info.user_id AND is_yefg = 1 and judge_level = '" + value + "' " +
                            "AND n_level_type = 1 AND n_present_info = 1 " +
                            "AND ( yefg_start_time BETWEEN '" + queryEntity.startDate + "' AND '" + queryEntity.endDate +
                            "') )";
                    criteria.andExistCondition(sql);
                } else if (value.equals("yefg")) {
                    String sql = " EXISTS ( SELECT 1 FROM ums_level_info " +
                            "WHERE ums_user_info_view.id = ums_level_info.user_id AND is_yefg = 1 and n_level_type = 1 " +
                            "AND n_level_type = 1 AND n_present_info = 1 " +
                            "AND ( yefg_start_time BETWEEN '" + queryEntity.startDate + "' AND '" + queryEntity.endDate +
                            "') )";
                    criteria.andExistCondition(sql);
                } else if (value.equals("tefg")) {
                    String sql = " EXISTS ( SELECT 1 FROM ums_level_info " +
                            "WHERE ums_user_info_view.id = ums_level_info.user_id " +
                            "AND n_level_type = 1 AND n_present_info = 1 AND IFNULL(is_yefg, 0) != 1 " +
                            "AND ( yefg_end_time BETWEEN '" + queryEntity.startDate + "' AND '" + queryEntity.endDate + "' ) )";
                    criteria.andExistCondition(sql);
                }
                return criteria;
            }

            if (value.equals("all")) {
                //全查
                //自定义报表
                if (define == 1 && "top".equals(type)) {
                    String[] topCodeNeedArray = null;
                    if (!StringUtils.isEmpty(topCodeNeed)) {
                        topCodeNeedArray = topCodeNeed.split(",");
                    }
                    if ("birthday".equals(column)) {
                        if (topCodeNeedArray != null && topCodeNeedArray.length > 0) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            List<String> starts = new ArrayList<>();
                            List<String> ends = new ArrayList<>();
                            for (int i = 0; i < topCodeNeedArray.length; i++) {
                                String birthdayScope = topCodeNeedArray[i];
                                if (birthdayScope.indexOf("-") > 0) {
                                    String[] split = birthdayScope.split("-");
                                    int s = Integer.parseInt(split[0]);
                                    int e = Integer.parseInt(split[1]);
                                    Calendar instance = Calendar.getInstance();
                                    instance.add(Calendar.YEAR, -s);
                                    Date startTime = instance.getTime();
                                    instance.setTime(new Date());
                                    instance.add(Calendar.YEAR, -e - 1);
                                    Date endTime = instance.getTime();
                                    starts.add(format.format(startTime));
                                    ends.add(format.format(endTime));
                                }
                            }
                            criteria.orBirthdayBetween(ends, starts, 1, 70);
                        }
                    } else {
                        criteria.orColumnIn(column, topCodeNeedArray == null ? null : Arrays.asList(topCodeNeedArray));
                    }
                }
                return criteria;
            }

            ArrayList<String> collect = new ArrayList<>();
            if (value.equals("other")) {
                // 额外的信息 查编码
                if (column.trim().equals("court_code")) {
                    //法院另外查询
                    List<UmsCourtFull> umsCourtFulls = courtFullService.selectByListAll();
                    for (UmsCourtFull umsCourtFull : umsCourtFulls) {
                        collect.add(umsCourtFull.getCourtCode());
                    }

                } else if (column.trim().equals("enter_src")) {
                    List<UmsExtEnterSrcType> all = enterSrcTypeService.findAll();
                    for (UmsExtEnterSrcType e : all) {
                        if (e.getId() != null) {
                            collect.add(e.getId().toString());
                        }
                    }


                } else if (column.trim().equals("company_info_id")) {
                    List<UmsExternalCompanyInfo> all = externalCompanyInfoService.findAll();
                    for (UmsExternalCompanyInfo e : all) {
                        if (e.getId() != null) {
                            collect.add(e.getId().toString());
                        }
                    }

                } else if (column.trim().equals("dept_org_code")) {
                    List<UmsCode> umsCodes = xtptBmDepartementService.selectDistinctAllUserCode(new HashMap());
                    for (UmsCode e : umsCodes) {
                        if (e.getId() != null) {
                            collect.add(e.getId());
                        }
                    }
                } else if (column.trim().equals("juror_work")) {
                    String array[] = new String[]{"基层干部", "人民团体成员", "事业单位职员", "专业技术人员", "工商业人员", "社区工作者", "普通居民", "农民", "进城务工人员", "其他人员"};
                    collect = new ArrayList<>(Arrays.asList(array));

                } else if (column.trim().equals("member_state")) {
                    collect.add("01");
                    collect.add("02");

                } else if (column.trim().equals("birthday")) {
                    criteria.orBirthdayNull(1, 70);
                    return criteria;
                } else {

                    List<Map<String, Object>> maps = umsCodeTypeService.selectCodeTypeByColumn(column.trim());
                    if (maps.size() != 1) {
                        return null;
                    }
                    Map<String, Object> map = maps.get(0);
                    Integer id = Integer.parseInt(map.get("ID").toString());
                    List<UmsCode> umsCodes = umsCodeService.selectCodesByType(id);
                    collect = umsCodes.stream().filter(umsCode -> StringUtils.hasText(umsCode.getId())).map(UmsCodeKey::getId).collect(Collectors.toCollection(ArrayList::new));

                }

                Pattern pattern = Pattern.compile("[0-9]*");

                if (collect != null) {
                    StringBuilder sql = new StringBuilder();
                    sql.append("( ").append(column).append(" not in (");
                    for (int i = 0; i < collect.size(); i++) {
                        String str = collect.get(i);
                        Matcher isNum = pattern.matcher(str);
//                        if (isNum.matches()) {
//                            if (i == collect.size() - 1) {
//                                sql.append(str).append(" )");
//                                continue;
//                            }
//                            sql.append(str).append(",");
//
//                        } else {
                            if (i == collect.size() - 1) {
                                sql.append(String.format("'%s'", str)).append(" )");
                                continue;
                            }
                            sql.append(String.format("'%s'", str)).append(",");
//                        }


                    }
                    sql.append(" or ").append(column).append(" is null ");
                    sql.append(" or ").append(column).append(" = '' ) ");
                    String s = sql.toString();
                    criteria.andExistCondition(s);

                    return criteria;
                } else {
                    return null;
                }

            }


            if (column.equals("enter_src") || column.equals("company_info_id")) {
                //编外的信息
                StringBuilder sql = new StringBuilder();
                sql.append("  ").append(column).append(" = ").append(value);
                criteria.andExistCondition(sql.toString());

                return criteria;
            }

            if (column.equals("juror_work") || column.equals("member_state")) {
                //编外的信息
                StringBuilder sql = new StringBuilder();
                sql.append("  ").append(column).append(" = '").append(value).append("'");
                criteria.andExistCondition(sql.toString());

                return criteria;
            }

            if (column.equals("birthday")) {
                String[] split = value.split("-");
                int s = Integer.parseInt(split[0]);
                int e = Integer.parseInt(split[1]);
                Calendar instance = Calendar.getInstance();
                instance.add(Calendar.YEAR, -s);
                Date startTime = instance.getTime();
                instance.setTime(new Date());
                instance.add(Calendar.YEAR, -e - 1);
                Date endTime = instance.getTime();
                criteria.andBirthdayBetween(endTime, startTime);
                return criteria;

            }

            String l = StringTools.underlineToCamel(column);
            String methodName = null;
            if (value.contains("in")) {
                methodName = (new StringBuilder()).append("and").append(Character.toUpperCase(l.charAt(0))).append(l.substring(1)).append("In").toString();
            } else if (value.contains("not in")) {
                methodName = (new StringBuilder()).append("and").append(Character.toUpperCase(l.charAt(0))).append(l.substring(1)).append("NotIn").toString();
            } else {
                methodName = (new StringBuilder()).append("and").append(Character.toUpperCase(l.charAt(0))).append(l.substring(1)).append("EqualTo").toString();
            }

            String[] values = {"刑事", "民事", "执行", "立案", "行政审判庭"};
            if (column.equals("a.dept_org_code") || Arrays.asList(values).contains(value)) {
                criteria.andExistCondition(" dept_org_code in (select org_code from xtpt_bm_departement where dept_name like concat('%', '" + value + "', '%')) ");
                criteria.andExistCondition(" ( yefg = 1 or EXISTS ( SELECT 1 FROM ums_level_info WHERE ums_level_info.user_id = ums_user_info_view.id AND ums_level_info.n_level_type = 1 AND ums_level_info.n_present_info = 1 AND ums_level_info.is_yefg = 1))");
                return criteria;
            }

            Class<? extends UmsUserInfoViewCriteria.Criteria> aClass = criteria.getClass();
            Method[] declaredMethods = aClass.getDeclaredMethods();
            Method declaredMethod = null;
            for (Method declaredMethodi : declaredMethods) {
                String name = declaredMethodi.getName();
                if (name.equals(methodName)) {
                    declaredMethod = declaredMethodi;
                    break;
                }
            }

            if (declaredMethod == null) {
                return null;
            }

            declaredMethod.setAccessible(true);

            Type[] genericParameterTypes = declaredMethod.getGenericParameterTypes();
            if (genericParameterTypes.length > 0) {
                Type genericParameterType = genericParameterTypes[0];
                if (genericParameterType.toString().equals("class java.lang.String")) {
                    declaredMethod.invoke(criteria, value);
                } else if (genericParameterType.toString().equals("class java.lang.Integer")) {
                    declaredMethod.invoke(criteria, Integer.valueOf(value));
                } else if (genericParameterType.toString().equals("interface java.util.List")) {
                    String[] split = value.replace("not", "").replace("in", "").replace("(", "").replace(")", "").replace(" ", "").split(",");
                    declaredMethod.invoke(criteria, Arrays.asList(split));
                }
            }



            return criteria;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //生成反查条件
    private UmsUserInfoExternalCriteria makeFcExampleT(QueryEntity queryEntity, Integer userType) {

        String leftColumn = queryEntity.getLeftColumn();
        String leftValue = queryEntity.getLeftValue();
        String topColumn = queryEntity.getTopColumn();
        String topValue = queryEntity.getTopValue();
        String topCodeNeed = queryEntity.getTopCodeNeed();
        String leftCodeNeed = queryEntity.getLeftCodeNeed();
        String fydmList = queryEntity.getFydmList();
        int define = queryEntity.getDefine();

        if (!StringUtils.hasText(leftColumn) || !StringUtils.hasText(leftValue) || !StringUtils.hasText(topColumn) || !StringUtils.hasText(topValue)) {
            return null;
        }

        try {

            UmsUserInfoExternalCriteria example = new UmsUserInfoExternalCriteria();
            UmsUserInfoViewCriteria.Criteria criteria = example.createCriteria();
            criteria.andIsValidEqualTo(1);
            if (userType == 1) {
                criteria.andUserTypeEqualTo(1);
            }
            criteria = doExample(criteria, leftColumn, leftValue, topCodeNeed, leftCodeNeed, "left", fydmList, define);
            criteria = doExample(criteria, topColumn, topValue, topCodeNeed, leftCodeNeed, "top", fydmList, define);

            if (criteria == null) {
                return null;
            }
            //特殊处理 联查ums_court_full
            criteria.andExistCondition("  EXISTS (select 1 from ums_court_full where a.court_code = ums_court_full.court_code ) ");
            return example;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 记录一条自定义统计的记录
     *
     * @return
     */
    @Action("add")
    public String add() {


        Map map = null;
        int insert = 0;

        try {
            //解码
            byte[] decode = SimpleDecodeParameter.decode(selectData);
            selectData = new String(decode,"utf-8");

            String sql = selectData.replace("'", "\"");
            map = new HashMap();
            data = map;

            ActionContext ctx = ActionContext.getContext();
            HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
            UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

            //先判断同一类型是否已存在相同sql；
            UmsCustomStatisticsCriteria customStatisticsCriteria = new UmsCustomStatisticsCriteria();
            UmsCustomStatisticsCriteria.Criteria criteria = customStatisticsCriteria.createCriteria().andSelectSqlEqualTo(sql).andUserTypeEqualTo(userType);
            if (type == 2) {
                criteria.andAuthorEqualTo(user_.getId());
            }
            List<UmsCustomStatistics> search = umsCustomStatisticsService.search(customStatisticsCriteria);
            if (search.size() > 0) {
                map.put("success", -1);
                map.put("entity", search.get(0));
                return "json";
            }

            UmsCustomStatistics umsCustomStatistics = new UmsCustomStatistics();
            umsCustomStatistics.setSelectsql(sql);
            umsCustomStatistics.setTitle(title);
            umsCustomStatistics.setDatatype(type);
            umsCustomStatistics.setUserType(userType);

            insert = 0;
            try {
                umsCustomStatistics.setAuthor(user_.getId());
                umsCustomStatistics.setAuthorName(user_.getFullname());
                insert = umsCustomStatisticsService.insert(umsCustomStatistics);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("success", insert > 0);
        return "json";
    }

    /**
     * 修改一条已存储的自定义统计的记录
     *
     * @return
     */
    @Action("update")
    public String update() {

        UmsCustomStatistics umsCustomStatistics = new UmsCustomStatistics();
        umsCustomStatistics.setId(id);
        umsCustomStatistics.setTitle(title);
        umsCustomStatistics.setDatatype(type);

        int update = 0;
        try {
            update = umsCustomStatisticsService.update(umsCustomStatistics);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        map.put("success", update > 0);
        data = map;
        return "json";
    }

    /**
     * 删除一条已记录的自定义统计记录
     *
     * @return
     */
    @Action("delete")
    public String delete() {
        int delete = 0;
        try {
            delete = umsCustomStatisticsService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        map.put("success", delete > 0);
        data = map;
        return "json";
    }

    /**
     * @return 查询已记录的自定义统计
     */
    @Action("select")
    public String select() {
        UmsCustomStatisticsCriteria customStatisticsCriteria = new UmsCustomStatisticsCriteria();
        UmsCustomStatisticsCriteria.Criteria criteria = customStatisticsCriteria.createCriteria().andUserTypeEqualTo(userType);
        if (type == null || type == 2) {

            ActionContext ctx = ActionContext.getContext();
            HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
            UmsUserInfoView user_ = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

            criteria.andAuthorEqualTo(user_.getId());
        }
        if (StringUtils.hasText(fullname)) criteria.andAuthorNameLike("%" + fullname + "%");
        if (StringUtils.hasText(title)) criteria.andTitleLike("%" + title + "%");
        RowBounds rowBounds = new RowBounds(start, limit);
        List<UmsCustomStatistics> search = new ArrayList<>();
        int count = 0;
        try {
            search = umsCustomStatisticsService.search(customStatisticsCriteria, rowBounds);
            count = umsCustomStatisticsService.count(customStatisticsCriteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        map.put("rows", search);
        map.put("results", count);
        data = map;
        return "json";
    }

    private class QueryEntity implements Cloneable {

        private Integer count = 0;
        private String leftColumn;
        private String leftValue;
        private String topColumn;
        private String topValue;

        private String startDate;
        private String endDate;

        private String topCodeNeed;
        private String leftCodeNeed;
        private String fydmList;
        private int define;

        public String getTopCodeNeed() {
            return topCodeNeed;
        }

        public void setTopCodeNeed(String topCodeNeed) {
            this.topCodeNeed = topCodeNeed;
        }

        public String getLeftCodeNeed() {
            return leftCodeNeed;
        }

        public void setLeftCodeNeed(String leftCodeNeed) {
            this.leftCodeNeed = leftCodeNeed;
        }

        public String getFydmList() {
            return fydmList;
        }

        public void setFydmList(String fydmList) {
            this.fydmList = fydmList;
        }

        public int getDefine() {
            return define;
        }

        public void setDefine(int define) {
            this.define = define;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getLeftColumn() {
            return leftColumn;
        }

        public void setLeftColumn(String leftColumn) {
            this.leftColumn = leftColumn;
        }

        public String getLeftValue() {
            return leftValue;
        }

        public void setLeftValue(String leftValue) {
            this.leftValue = leftValue;
        }

        public String getTopColumn() {
            return topColumn;
        }

        public void setTopColumn(String topColumn) {
            this.topColumn = topColumn;
        }

        public String getTopValue() {
            return topValue;
        }

        public void setTopValue(String topValue) {
            this.topValue = topValue;
        }

        public QueryEntity clone() {
            QueryEntity entity = null;
            try {
                entity = (QueryEntity) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return entity;
        }

    }

    private class MergeConfig {
        private String[] oldText;
        private String newText;
        private boolean isDelete;       //true 表示 oldtext 里的合并为 newText，false 表示除了 oldtext 的合并为 newText
        private boolean saveSort = true;

        public MergeConfig(String[] oldText, String newText, boolean isDelete) {
            this.oldText = oldText;
            this.newText = newText;
            this.isDelete = isDelete;
        }

        public MergeConfig(String[] oldText, String newText, boolean isDelete, boolean saveSort) {
            this.oldText = oldText;
            this.newText = newText;
            this.isDelete = isDelete;
            this.saveSort = saveSort;
        }

        public String[] getOldText() {
            return oldText;
        }

        public void setOldText(String[] oldText) {
            this.oldText = oldText;
        }

        public String getNewText() {
            return newText;
        }

        public void setNewText(String newText) {
            this.newText = newText;
        }

        public boolean isDelete() {
            return isDelete;
        }

        public void setDelete(boolean delete) {
            isDelete = delete;
        }

        public boolean isSaveSort() {
            return saveSort;
        }

        public void setSaveSort(boolean saveSort) {
            this.saveSort = saveSort;
        }
    }

    // 针对统计法官等级中,进行列表的反查
    @Action("fc_fgList")
    public String fc_fgList() {
        Map<String, Object> returnMap = new HashMap<>();
        data = returnMap;

        HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
        Integer page = Integer.valueOf(httpServletRequest.getParameter("page"));

        Map<String, Object> param = new HashMap<>();
        PageUtil pageUtil = new PageUtil(page, limit);
        param.put("page", pageUtil.getStart());
        param.put("limit", pageUtil.getLimit());

        // 组装查询参数
        if (null != queryEntity) {
            // 组装左侧列的参数
            if ("all".equals(queryEntity.leftValue)) {
                param.put("courtCodeParam", " AND a.court_code IN (SELECT court_code FROM ums_court_full WHERE a.court_code = court_code)");
            } else {
                param.put("courtCodeParam", " AND a.court_code = '" + queryEntity.leftValue + "'");
            }
            if ("yefg".equals(queryEntity.topValue)) {
                param.put("yefgParam", " AND (a.yefg = 1 OR EXISTS (SELECT 1 FROM ums_level_info b WHERE a.id = b.user_id AND a.court_no = b.court_no AND b.n_present_info = 1 AND b.is_yefg = 1))");
                param.put("startDate_re", queryEntity.startDate);
                param.put("endDate_re", queryEntity.endDate);
            } else if ("tefg".equals(queryEntity.topValue)) {
                param.put("tefgParam", " AND (IFNULL(a.yefg, '') != 1 OR EXISTS (SELECT 1 FROM ums_level_info b WHERE a.id = b.user_id AND a.court_no = b.court_no AND b.n_level_type = 1 AND b.n_present_info = 1 AND IFNULL(b.is_yefg, 0) != 1))");
                param.put("startDate_te", queryEntity.startDate);
                param.put("endDate_te", queryEntity.endDate);
            } else {
                param.put("judgeLevelParam", " AND b.n_level_type = 1 AND b.n_present_info = 1 AND b.is_yefg = 1 AND (b.yefg_end_reason IS NULL OR b.yefg_end_reason = '') AND (b.yefg_end_time IS NULL OR b.yefg_end_time = '') AND b.judge_level = '" + queryEntity.topValue + "'");
                param.put("startDate_re", queryEntity.startDate);
                param.put("endDate_re", queryEntity.endDate);
            }
        }

        // 该人员权限下的法院列表
        List<Integer> authCourt;
        List<Integer> courtNoList = authorityHelper.accessibleCourtNoList("查看");
        List<Integer> courts = new ArrayList<>();
        if (courtNoList.contains(-9999)) { //超级管理员来了
            // 法院集合
            List<UmsCourtFull> court_list = umsCourtFullService.selectByListAll();
            for (UmsCourtFull umsCourtFull : court_list) {
                courts.add(umsCourtFull.getCourtNo());
            }
        } else if (authorityHelper.isSelfOnly(courtNoList)) {
            UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
            courts = new ArrayList<>();
            courts.add(u.getCourtNo());
        } else {
            courts = courtNoList;
        }
        authCourt = courts;

        // 获取权限, 权限下有哪些法院
        StringBuilder sql_author = new StringBuilder();
        if (authCourt != null && authCourt.size() > 0) {
            sql_author.append(" and a.court_no in ( ");
            for (int i = 0; i < authCourt.size(); i++) {
                Integer integer = authCourt.get(i);
                sql_author.append(" '").append(integer).append("' ");
                if (i != authCourt.size() - 1) {
                    sql_author.append(",");
                }
            }
            sql_author.append(" ) ");
        }

        param.put("authcourt", sql_author);

        List<UmsUserInfoView> infoByExample = umsUserInfoViewService.selectFgList_Fc(param);
        Integer fgCount_fc = umsUserInfoViewService.selectFgCount_Fc(param);
        returnMap.put("code", 0);
        returnMap.put("data", infoByExample);
        returnMap.put("count", fgCount_fc);
        return "json";
    }

    // 针对统计退额法官等级中,进行列表的反查
    @Action("fc_teFgList")
    public String fc_teFgList() {
        Map<String, Object> returnMap = new HashMap<>();
        data = returnMap;

        HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
        Integer page = Integer.valueOf(httpServletRequest.getParameter("page"));

        Map<String, Object> param = new HashMap<>();
        PageUtil pageUtil = new PageUtil(page, limit);
        param.put("page", pageUtil.getStart());
        param.put("limit", pageUtil.getLimit());

        // 组装查询参数
        if (null != queryEntity) {
            // 组装左侧列的参数
            if ("all".equals(queryEntity.leftValue)) {
                param.put("courtCodeParam", " AND a.court_code IN (SELECT court_code FROM ums_court_full WHERE a.court_code = court_code)");
            } else {
                param.put("courtCodeParam", " AND a.court_code = '" + queryEntity.leftValue + "'");
            }
            param.put("tefgParam", " AND (IFNULL(a.yefg, '') != 1 OR EXISTS (SELECT 1 FROM ums_level_info b WHERE a.id = b.user_id AND a.court_no = b.court_no AND b.n_level_type = 1 AND b.n_present_info = 1 AND IFNULL(b.is_yefg, 0) != 1))");
            param.put("startDate_te", queryEntity.startDate);
            param.put("endDate_te", queryEntity.endDate);
        }

        // 该人员权限下的法院列表
        List<Integer> authCourt;
        List<Integer> courtNoList = authorityHelper.accessibleCourtNoList("查看");
        List<Integer> courts = new ArrayList<>();
        if (courtNoList.contains(-9999)) { //超级管理员来了
            // 法院集合
            List<UmsCourtFull> court_list = umsCourtFullService.selectByListAll();
            for (UmsCourtFull umsCourtFull : court_list) {
                courts.add(umsCourtFull.getCourtNo());
            }
        } else if (authorityHelper.isSelfOnly(courtNoList)) {
            UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
            courts = new ArrayList<>();
            courts.add(u.getCourtNo());
        } else {
            courts = courtNoList;
        }
        authCourt = courts;

        // 获取权限, 权限下有哪些法院
        StringBuilder sql_author = new StringBuilder();
        if (authCourt != null && authCourt.size() > 0) {
            sql_author.append(" and a.court_no in ( ");
            for (int i = 0; i < authCourt.size(); i++) {
                Integer integer = authCourt.get(i);
                sql_author.append(" '").append(integer).append("' ");
                if (i != authCourt.size() - 1) {
                    sql_author.append(",");
                }
            }
            sql_author.append(" ) ");
        }

        param.put("authcourt", sql_author);

        List<UmsUserInfoView> infoByExample = umsUserInfoViewService.selectFgList_Fc(param);
        Integer fgCount_fc = umsUserInfoViewService.selectFgCount_Fc(param);
        returnMap.put("code", 0);
        returnMap.put("data", infoByExample);
        returnMap.put("count", fgCount_fc);
        return "json";
    }
}
