/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.userinfo;

import cn.net.withub.ums.action.xml.ZipUtils;
import cn.net.withub.ums.auth.AuthorityHelper;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.*;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.*;
import cn.net.withub.ums.service.statistics.XtptUserService;
import cn.net.withub.ums.util.DateUtil;
import cn.net.withub.ums.util.*;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static cn.net.withub.ums.util.StringTools.isNullOrEmpty;

/**
 * 人事信息视图
 *
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/view")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"}),
        @Result(name = "empty", location = "/board/personnel/info/empty_data.jsp"),
        @Result(name = "detail", location = "/board/personnel/info/user2_detail.jsp"),
        @Result(name = "detail_new", location = "/board/personnel/info/user2_new_detail.jsp"),
        @Result(name = "batchEdit", location = "/board/personnel/info/userinfo_batch_process.jsp"),
        @Result(name = "basicinfo", location = "/board/personnel/info/userinfo/detail_user.jsp"),
        @Result(name = "basicinfo_new", location = "/board/personnel/info/userinfo/detail_new_user.jsp"),
        @Result(name = "basicinfo2", location = "/board/personnel/info/userinfo/detail_user_ext.jsp"),
        @Result(name = "basicinfo3", location = "/board/personnel/info/userinfo/detail_user_3.jsp"),
        @Result(name = "stream", type = "stream", params = {"inputName", "inputStream", "contentDisposition", "attachment;filename=${filename}.xls",})
})
public class UserInfoViewAction {

    @Autowired
    UmsLevelInfoMapper umsLevelInfoMapper;
    @Autowired
    UmsUserInfoOldService umsUserInfoOldService;

    @Autowired
    UmsDepartmentService umsDepartmentService;

    @Autowired
    UmsUserInfoService umsUserInfoService;

    @Autowired
    XtptUserService xtptUserService;

    @Autowired
    private UmsUserInfoViewService userInfoViewService;

    @Autowired
    private UmsUserInfoService userInfoService;

    @Autowired
    private UmsExtEnterSrcTypeService enterSrcTypeService;

    @Autowired
    private UmsCourtFullService courtFullService;

    @Autowired
    private UmsAttachedTableService attachedTableService;

    @Autowired
    private UmsExchangeInfoMapper umsExchangeInfoMapper;

    @Autowired
    private UmsUploadCodeContrastService umsUploadCodeContrastService;

    @Autowired
    private UmsCodeService umsCodeService;

    @Autowired
    private AuthorityHelper authorityHelper;

    @Autowired
    private UmsPhotoService photoService;


    @Autowired
    private UmsCheckInfoSqlMapper infoSqlMapper;

    @Autowired
    SqlSession sqlSession;

    @Autowired
    private UmsExternalUserInfoService externalUserInfoService;

    @Autowired
    private UmsExternalJobTypeMapper externalJobTypeMapper;

    @Autowired
    private generateCommonExcel generateCommonExcel;

    @Autowired
    private UmsUserStatusMapper umsUserStatusMapper;



    SimpleDateFormat simpleDateFormat_1 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat simpleDateFormat_2 = new SimpleDateFormat("yyyy-MM");

    private String query;
    private Integer start = 0;
    private Integer page = 1;
    private Integer pageIndex = 1;
    private Integer limit = 20;
    private String sort;
    private String field;
    private String direction;

    private String fullname;
    private Integer gender;
    private Integer administrationPosition;
    private Integer rank;
    private Date enterDateStart;
    private Date enterDateEnd;

    private String courtNo;
    private Integer deptNo;
    private Integer isInfoComplete;

    private Integer isValid;

    private Integer leaveReason;

    private Integer userType = 1;

    private String id;

    private Object data;

    private Date birthdayN;

    private Integer yefg;

    private Integer isFg;//是否为法官

    private Date birthdayM;

    private Integer department;

    private String innerCourtNo;

    private Integer educationBackground;

    private Integer excelDataType;

    private String allId;

    private String phoneNumber;

    private String orgCode;
    private Map<String, String> fields = new HashMap<>();

    private String personnelClassification;
    private String job;
    private Integer lawPosition;
    private Map<String, Object> mapInfo = new HashMap<>();
    private String uuid;
    private List<String> ids;

    private UmsCheckInfoSql infoSql;

    public UmsCheckInfoSql getInfoSql() {
        return infoSql;
    }

    public void setInfoSql(UmsCheckInfoSql infoSql) {
        this.infoSql = infoSql;
    }

    //
    private boolean simpleQuery ;

    public boolean isSimpleQuery() {
        return simpleQuery;
    }

    public void setSimpleQuery(boolean simpleQuery) {
        this.simpleQuery = simpleQuery;
    }

    /**
     * 人事信息列表
     *
     * @return
     * @link http://miemiedev.github.io/mmGrid/
     */
    @Action("userinfo")
    public String userinfo() {
        String sortName = "";
        String sortOrder = "";

        if (sort != null && !sort.isEmpty()) {
            String[] s = sort.split("[.]");
            sortName = s[0];
            sortOrder = s[1];
        }

        if (sortName.endsWith("Text")) {
            sortName = sortName.substring(0, sortName.length() - 4);
        }

        Map<String, Object[]> conditions = CriteriaTools.fromQueryString("", "", sortName, sortOrder);

        List<UmsUserInfoView> list = userInfoViewService.search(conditions, page, limit);
        int total = userInfoViewService.count(conditions);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("page", page);
        map.put("limit", limit);
        map.put("totalCount", total);
        map.put("items", list);

        data = map;

        return "json";

    }

    // 人事信息列表，查询在编人员信息列表
    @Action("userinfo2")
    public String userinfo2() {
        if (!StringTools.isNullOrEmpty(field) && field.endsWith("Text")) {
            field = field.substring(0, field.length() - 4);
        }
        if (StringUtils.isNotEmpty(innerCourtNo)) {
            courtNo = innerCourtNo;
        }
        if (department != null) {
            deptNo = department;
        }
        UmsUserInfoViewCriteria criteria = makeCriteria("userinfo2");
        // sortNo 已经默认排序了
        if( ! ( org.springframework.util.StringUtils.hasText(field) && field.equalsIgnoreCase("sortNo"))){
            criteria.setOrderByClause( StringTools.camelOrPascalToUnderline(field) + " " + direction );
        }
        RowBounds rowBounds = new RowBounds(start, limit);
        List<UmsUserInfoView> list = null;
        try {
            //只作展示时 为了提高查询效率 调用另一个方法
            if(simpleQuery){
                list = userInfoViewService.simpleSearchBySort(criteria, rowBounds);
            }else{
                list = userInfoViewService.searchBySort(criteria, rowBounds);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        int total = userInfoViewService.count(criteria);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("results", total);
        map.put("rows", list);
        data = map;
        return "json";
    }

    @Action("userinfo3")
    public String userinfo3() {
        if (!StringTools.isNullOrEmpty(field) && field.endsWith("Text")) {
            field = field.substring(0, field.length() - 4);
        }
        if (StringUtils.isNotEmpty(innerCourtNo)) {
            courtNo = innerCourtNo;
        }
        if (department != null) {
            deptNo = department;
        }
        UmsUserInfoViewCriteria criteria = makeCriteria("userinfo3");
        criteria.setOrderByClause(" case when " + StringTools.camelOrPascalToUnderline(field) + " is null then 1 else 0 end ," + StringTools.camelOrPascalToUnderline(field) + " " + direction);
        RowBounds rowBounds = new RowBounds(start, limit);
        List<UmsUserInfoView> list = null;
        try {
            list = userInfoViewService.searchBySort(criteria, rowBounds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int total = userInfoViewService.count(criteria);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("results", total);
        map.put("rows", list);
        data = map;
        return "json";
    }

    // 待完善资料的人员列表
    @Action("userinfo_dwszl")
    public String userinfo_dwszl() {
        if (!StringTools.isNullOrEmpty(field) && field.endsWith("Text")) {
            field = field.substring(0, field.length() - 4);
        }
        if (StringUtils.isNotEmpty(innerCourtNo)) {
            courtNo = innerCourtNo;
        }
        if (department != null) {
            deptNo = department;
        }
        // 组成查询条件
        UmsUserInfoViewCriteria criteria = makeCriteriaForGbbzlx();
        // sortNo 已经默认排序了
        if( ! ( org.springframework.util.StringUtils.hasText(field) && field.equalsIgnoreCase("sortNo"))){
            criteria.setOrderByClause( StringTools.camelOrPascalToUnderline(field) + " " + direction );
        }
        RowBounds rowBounds = new RowBounds(start, limit);
        List<UmsUserInfoView> list = null;
        try {
            //只作展示时 为了提高查询效率 调用另一个方法
            if(simpleQuery){
                list = userInfoViewService.simpleSearchBySort_dwszl(criteria, rowBounds);
            }else{
                list = userInfoViewService.searchBySort_dwszl(criteria, rowBounds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int total = userInfoViewService.count_dwszl(criteria);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("results", total);
        map.put("rows", list);
        data = map;
        return "json";
    }

    @Action("excelDownload")
    public String excel() {
        if (StringUtils.isNotEmpty(innerCourtNo)) {
            courtNo = innerCourtNo;
        }
        UmsUserInfoViewCriteria criteria = makeCriteria("excelDownload");
        if (excelDataType != null && (excelDataType == 3 || excelDataType == 31)) {
            // 陪审员查询
            List<Map> maps = userInfoViewService.seleteJurorInfo(criteria);
            byte[] bytes = generateCommonExcel.generateExcel(excelDataType,null, maps, null, null,null);
            if (bytes == null || bytes.length == 0) {
                return "json";
            } else {
                this.inputStream = new ByteArrayInputStream(bytes);
                filename = "人民陪审员信息";
                return "stream";
            }
        } else if (excelDataType != null && excelDataType == 1) {
            criteria.setOrderByClause("  court_no, dept_level,dept_sortNo,sort_no , case when sort_no is null then 1 else 0 end  ");
            RowBounds rowBounds = new RowBounds();
            List<UmsUserInfoView> list = null;
            try {
                list = userInfoViewService.search(criteria, rowBounds);
            } catch (Exception e) {
                e.printStackTrace();
            }
            UmsExtendUserInfoViewCriteria criteria1 = criteria;
            byte[] bytes = generateCommonExcel.generateExcel(excelDataType,list, null, Collections.singletonList("education_info"), criteria1,null);
            if (bytes == null || bytes.length == 0) {
                return "json";
            } else {
                this.inputStream = new ByteArrayInputStream(bytes);
                filename = "在编人员信息";
                return "stream";
            }
        } else if (excelDataType != null && excelDataType == 2) {
            //编外员查询
            List<Map> maps = userInfoViewService.seleteOffStaffInfo(criteria);
            for (Map map : maps) {
                // 再加个条件,查询工作类型
                UmsExternalUserInfo info = externalUserInfoService.selectById(map.get("id"));
                if (null != info) {
                    Integer jobtype1 = info.getJobType1();
                    Integer jobtype2 = info.getJobType2();
                    UmsExternalJobType umsExternalJobType1 = externalJobTypeMapper.selectByPrimaryKey(jobtype1);
                    UmsExternalJobType umsExternalJobType2 = externalJobTypeMapper.selectByPrimaryKey(jobtype2);
                    if (null != umsExternalJobType1) {
                        map.put("jobtype1", umsExternalJobType1.getJobName());
                    }
                    if (null != umsExternalJobType2) {
                        map.put("jobtype2", umsExternalJobType2.getJobName());
                    }
                }
            }
            byte[] bytes = generateCommonExcel.generateExcel(excelDataType,null, maps, null, null,null);
            if (bytes == null || bytes.length == 0) {
                return "json";
            } else {
                this.inputStream = new ByteArrayInputStream(bytes);
                filename = "编外人员信息";
                return "stream";
            }
        }
        return "json";
    }

    @Action("userinfoById")
    public String getUserinfoById() {
        UmsUserInfo umsUserInfo = userInfoService.selectById(id);
        //现在要求给人事系统存储、获取身份证证和手机号的信息对应加密、解密
        EncodeDecodeDataInfo.deCodeDataForUms(umsUserInfo);
        Map<String, Object> map = UserInfoAction.entityToMap(umsUserInfo);
        List<UmsUserStatus> userStatuses = umsUserStatusMapper.selectByUserId(id);
        map.put("userStatus",userStatuses);
        if (fields != null && fields.size() > 0) {
            Map<String, Object> map1 = attachedTableService.selectDataSetView(fields, id);
            map.put("otherfield", map1);
        }
        data = map;
        return "json";
    }

    @Action("detail")
    public String detail() {
        data = userInfoService.selectById(id);
        return "detail";
    }

    @Action("detail_new")
    public String detail_new() {
        //使用过滤aop权限的查询 无权限的人无法查看
        data = userInfoViewService.selectById(id);
        if(data == null){
            return "empty";
        }
//        data = userInfoService.selectById(id);
        return "detail_new";
    }

    @Action("basicinfo")
    public String basicinfo() {
        return "basicinfo";
    }

    @Action("basicinfo_new")
    public String basicinfo_new() {
        //使用过滤aop权限的查询 无权限的人无法查看
        UmsUserInfoView umsUserInfoView = userInfoViewService.selectById(id);
        if(umsUserInfoView == null){
            return "empty";
        }
        return "basicinfo_new";
    }

    @Action("basicinfo2")
    public String basicinfo2() {
        return "basicinfo2";
    }

    @Action("basicinfo3")
    public String basicinfo3() {
        return "basicinfo3";
    }

    private byte[] generateExcel(List<UmsUserInfoView> list, List<Map> mapList, List<String> viewTables, UmsExtendUserInfoViewCriteria criteria) {
        // 标题
        String title[] = new String[0];
        String extend_title[] = new String[0];
        if (excelDataType != null && excelDataType == 3) {
            // 陪审员查询
            title = new String[]{"序号", "姓名", "法院", "曾用名", "性别", "民族", "籍贯", "常住地", "通讯地址", "婚姻状况", "健康状况", "身份证", "出生日期", "年龄", "政治面貌", "学历学位", "任命日期",};
            extend_title = new String[]{"单位", "职业"};
        } else if (excelDataType != null && excelDataType == 31) {
            // 陪审员查询
            title = new String[]{"序号", "姓名", "性别", "出生日期（格式：1981-1-1）", "身份证类型", "身份证号码", "民族", "学历", "政治面貌", "家庭住址", "固定电话", "手机号码", "电子邮箱", "地区分布", "职业", "工作单位", "工作区域（一级）", "工作区域（二级）", "工作区域（三级）", "工作区域（四级）", "专业特长", "任期开始日期（格式：1981-1-1）", "是否新增", "届数", "参与案件类型", "个人照片"};
        } else if (excelDataType != null && excelDataType == 1) {
            // 0921加入6个字段, 这个是在编人员
            title = new String[]{"序号", "姓名", "法院", "部门" ,
                    "入额前职级", "任职时间" , "入额时间", "确认法官等级","确认等级时间", "现法官等级","任职时间",
                    "行政职务", "行政职务入职时间",  "法律职务", "法律职务入职时间" , "员额法官", "曾用名", "性别", "民族", "出生地", "籍贯", "婚姻状况", "健康状况", "身份证", "出生日期", "年龄", "学历", "政治面貌", "等级", "公务员等级", "职级", "职级类型获取时间", "人员分类"};
//            title = new String[]{"序号", "姓名", "法院", "部门", "曾用名", "性别", "民族", "出生地", "籍贯", "婚姻状况", "健康状况", "身份证", "出生日期", "年龄", "学历", "政治面貌", "行政职务", "法律职务", "职级", "等级", "公务员等级", "法律职务", "法律职务入职时间", "行政职务", "行政职务入职时间", "职级类型", "职级类型获取时间", "人员分类", "员额法官"};
        } else if (excelDataType != null && excelDataType == 2) {
            title = new String[]{"序号", "姓名", "法院", "部门", "曾用名", "性别", "民族", "出生地", "籍贯", "婚姻状况", "健康状况", "身份证", "出生日期", "年龄", "工作分类一", "工作分类二"};
            extend_title = new String[]{"进入来源", "公司"};
        }
        // 标题对应的实体类字段  序号省略
        String title_entity[] = new String[0];
        String extend_title_entity[] = new String[0];
        if (excelDataType != null && excelDataType == 3) {
            title_entity = new String[]{"fullname", "court_no_text", "former_name", "gender_text", "nation_text", "hometown", "local_address", "postal_address", "marital_status_text", "physical_condition_text", "idcard", "birthday", "age", "political_text", "juror_edu_text", "begin_time"};
            extend_title_entity = new String[]{"company", "juror_work"};
        } else if (excelDataType != null && excelDataType == 31) {
            // 人民陪审员最高院导入excel需要设置的东西
            title_entity = new String[]{"fullname", "gender", "birthday", "cardtype", "idcard", "nation", "education_background", "political", "local_address", "machine_number", "phone_number", "email", "diqu", "juror_work", "company", "quyu1", "quyu2", "quyu3", "quyu4", "zytc", "begin_time", "isnew", "jieshu", "ajlx"};
        } else if (excelDataType != null && excelDataType == 1) {
            // 0921加入字段 这个是在编人员
            title_entity = new String[]{"fullname", "courtNoText", "departmentText",
                    "n_job_report_text", "d_assign_date", "yefg_start_time",  "d_s_te" , "d_s_ti", "d_e_te","d_e_ti",
                    "administrationPositionText", "administrationPositionDate", "lawPositionText", "lawPositionDate", "is_yefg_by_level", "formerName", "genderText", "nationText", "birthplace", "hometown", "maritalStatusText", "physicalConditionText", "idcard", "birthday", "age", "educationBackgroundText", "politicalText", "levelText", "servantLevelText",  "rankText", "rankDate", "personnelClassificationText"};
//            title_entity = new String[]{"fullname", "courtNoText", "departmentText", "formerName", "genderText", "nationText", "birthplace", "hometown", "maritalStatusText", "physicalConditionText", "idcard", "birthday", "age", "educationBackgroundText", "politicalText", "administrationPositionText", "lawPositionText", "rankText", "levelText", "servantLevelText", "lawPositionText", "lawPositionDate", "administrationPositionText", "administrationPositionDate", "rankText", "rankDate", "personnelClassificationText", "yefg"};
        } else if (excelDataType != null && excelDataType == 2) {
            title_entity = new String[]{"fullname", "court_no_text", "department_text", "former_name", "gender_text", "nation_text", "birthplace", "hometown", "marital_status_text", "physical_condition_text", "idcard", "birthday", "age", "jobtype1", "jobtype2"};
            extend_title_entity = new String[]{"enter_src", "company_name"};
        }
        // 如果要为在编人员的excel下载添加额外的信息 则必须要配置
        Map<String, String[]> attachedTitle = new HashMap<>();
        Map<String, String> attachedField = new HashMap<>();
        Map<String, String> attachedViewName = new HashMap<>();
        Map<String, String> attachedOrderBySql = new HashMap<>();
        attachedTitle.put("education_info_title", new String[]{"额外信息-学历", "所学专业", "学校名称", "毕业日期"});
        attachedTitle.put("education_info_entity", new String[]{"n_education_background_text", "n_major_text", "c_college", "d_graduate_date"});
//        String sql_ = "user_id,\n" +
//                "  case when ((ifnull(n_present_info,'') = 1) or ( n_education_background = min(n_education_background)) ) then n_education_background_text end n_education_background_text,\n" +
//                "  case when ((ifnull(n_present_info,'') = 1) or ( n_education_background = min(n_education_background)) ) then n_major_text end n_major_text,\n" +
//                "  case when ((ifnull(n_present_info,'') = 1) or ( n_education_background = min(n_education_background)) ) then c_college end c_college,\n" +
//                "  case when ((ifnull(n_present_info,'') = 1) or ( n_education_background = min(n_education_background)) ) then DATE_FORMAT(d_graduate_date,\"%Y-%c-%d\") end d_graduate_date\n";
        String sql_ = "user_id,n_education_background_text,n_major_text,c_college,DATE_FORMAT(d_graduate_date,\"%Y-%c-%d\") as d_graduate_date";
        attachedField.put("education_info", sql_);
        attachedViewName.put("education_info", "ums_education_info_view");
        attachedOrderBySql.put("education_info", " ORDER BY  case when  n_present_info is null then 1 else 0 end , n_present_info asc, n_education_background ASC ");
        // 添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        HSSFWorkbook workbook = new HSSFWorkbook();
        String sheetName = "在编人员信息";
        if (excelDataType != null && excelDataType == 31) {
            workbook.createSheet("Sheet2");
            workbook.createSheet("Sheet1");
            workbook.createSheet("Sheet3");
            sheetName = "陪审员人员信息";
        }
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //垂直居中
        style.setWrapText(true); //强制换行
        HSSFCellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        dateStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFDataFormat format = workbook.createDataFormat();
        dateStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFDataFormat format_ = workbook.createDataFormat();
        cellStyle.setWrapText(true); //强制换行
        cellStyle.setDataFormat(format_.getFormat("@"));
        sheet.setDefaultColumnWidth(22);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        FileOutputStream out = null;
        sheet.setDefaultRowHeight((short) (68 * 20));
        int row_id = 0;
        byte[] bytes = null;
        FileOutputStream fileOutputStream = null;
        try {
            HSSFRow row_title = workbook.getSheet(sheetName).createRow(row_id++);    //创建第一行
            row_title.setHeightInPoints(25);
            for (int i = 0; i < title.length; i++) {
                HSSFCell cell = row_title.createCell(i);
                cell.setCellValue(title[i]);
                cell.setCellStyle(style);
            }
            if (excelDataType != null && excelDataType != 1) {
                for (int i = 0; i < extend_title.length; i++) {
                    HSSFCell cell = row_title.createCell(i + title.length);
                    cell.setCellValue(extend_title[i]);
                    cell.setCellStyle(style);
                }
            }
//            String path_ = path + "/" + fileName + ".xls";
//            File file = new File(path_);
//            if (!file.getParentFile().exists()) {
//                file.getParentFile().mkdirs();
//            }
            int sort = 1;
            // 这是在编人员
            if (excelDataType != null && excelDataType == 1 && list != null && list.size() > 0) {

                //取到当前查询条件的法官等级信息
                List<Map<String, Object>> levelInfo = umsExchangeInfoMapper.selectLevelInfo(criteria);
                // 员额法官任职前 行政职务等级
                List<Map<String, Object>> maxAdminInfo = umsExchangeInfoMapper.selectMaxAdminInfo(criteria);

                Map<String, List<Map<String, Object>>> maxAdminInfoMap = new HashMap<>();
                if(maxAdminInfo != null && maxAdminInfo.size() != 0){
                    maxAdminInfoMap = maxAdminInfo.stream().collect(Collectors.groupingBy(map -> map.getOrDefault("user_id", "").toString()));
                }

                Map<String, List<Map<String, Object>>> levelInfoMap = null;

                if(levelInfo != null && levelInfo.size() > 0){
                    levelInfoMap = levelInfo.stream().collect(Collectors.groupingBy(map -> map.getOrDefault("user_id", "").toString()));
                }

                Class<UmsUserInfoView> umsUserInfoViewClass = UmsUserInfoView.class;
                Field[] declaredFields = umsUserInfoViewClass.getDeclaredFields();
                // 把实体类的属性名称放入list当中
                List<String> field_list = new ArrayList<>();
                for (Field field : declaredFields) {
                    if (Modifier.isStatic(field.getModifiers())) {
                        continue;
                    }
                    field.setAccessible(true);
                    try {
                        field_list.add(field.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // 额外的头信息
                Map<String, Map<String, List<Map<String, Object>>>> viewController = new HashMap<>();
                if (viewTables != null) {
                    for (String viewName : viewTables) {
                        String[] title_ = attachedTitle.get(viewName + "_title");
                        // 要查询的字段
                        String[] entity_ = attachedTitle.get(viewName + "_entity");
                        String order_ = attachedOrderBySql.get(viewName);
                        String field_ = attachedField.get(viewName);
                        String viewName_ = attachedViewName.get(viewName);
                        if (title_ == null || entity_ == null || !org.springframework.util.StringUtils.hasText(field_) || !org.springframework.util.StringUtils.hasText(viewName_)) {
                            continue;
                        }
                        criteria.setFieldSql(sql_);
                        criteria.setTableName(viewName_);
                        criteria.setOrderBySql(order_);
                        List<Map<String, Object>> list1 = umsExchangeInfoMapper.selectViewNoAspect(criteria);
                        Map<String, List<Map<String, Object>>> v = list1.stream().filter(stringObjectMap -> stringObjectMap.get("user_id") != null).collect(Collectors.groupingBy(stringObjectMap -> stringObjectMap.get("user_id").toString()));
                        viewController.put(viewName, v);
                        int b_ = title.length;
                        for (String aTitle_ : title_) {
                            HSSFCell cell_ = row_title.createCell(b_++);
                            cell_.setCellValue(aTitle_);
                            cell_.setCellStyle(cellStyle);
                        }
                    }
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                for (UmsUserInfoView view : list) {
                    HSSFRow row = workbook.getSheet(sheetName).createRow(row_id++);
                    row.setHeightInPoints(20);
                    // 序号
                    HSSFCell cell = row.createCell(0);
                    cell.setCellValue(sort++);
                    cell.setCellStyle(style);

                    String userId = view.getId();

                    //特殊的字段  员额法官任职前 行政职务等级
                    List<String> specialField1 = Arrays.asList("n_job_report_text", "d_assign_date" );
                    //等级信息
                    List<String> specialField2 = Arrays.asList("yefg_start_time", "d_s_ti","d_s_te","d_e_ti","d_e_te" );

                    for (int i = 0; i < title_entity.length; i++) {
                        HSSFCell cell_ = row.createCell(i + 1);
                        if (!field_list.contains(title_entity[i])) {
                            String value = "";
                            if (title_entity[i].equals("age")) {
                                try {
                                    Date birthday = view.getBirthday();
                                    if (birthday != null) {
                                        value = DateUtil.getAge(birthday);
                                        Integer i1 = Integer.parseInt(value);
                                        cell_.setCellValue(i1);
                                        cell_.setCellStyle(style);
                                        continue;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            if("is_yefg_by_level".equals(title_entity[i])){

                                List<Map<String, Object>> maps = new ArrayList<>();
                                if (null != levelInfoMap && levelInfoMap.size() > 0) {
                                    maps = levelInfoMap.get(userId);
                                }

                                String obj = "";
                                if(maps != null && maps.size() > 0){
                                    obj = "是";
                                } else {
                                    obj = "否";
                                }
                                cell_.setCellValue(obj);
                                cell_.setCellStyle(style);
                                continue;

                            }

                            //特殊的处理 员额法官任职前 行政职务等级 is_yefg_by_level
                            if(specialField1.contains(title_entity[i])){
                                List<Map<String, Object>> maps = maxAdminInfoMap.get(userId);
                                if(maps != null && maps.size() > 0){
                                    Map<String, Object> stringObjectMap = maps.get(0);
                                    cell_.setCellValue(stringObjectMap.getOrDefault(title_entity[i],"").toString());
                                    cell_.setCellStyle(style);
                                    continue;
                                }
                            }

                            //特殊的处理 员额法官任职前 行政职务等级 is_yefg_by_level
                            if(specialField2.contains(title_entity[i])){

                                List<Map<String, Object>> maps = new ArrayList<>();
                                if (null != levelInfoMap && levelInfoMap.size() > 0 ) {
                                    maps = levelInfoMap.get(userId);
                                }

                                if(maps != null && maps.size() > 0){
                                    if("yefg_start_time".equals(title_entity[i])){

                                        List<Map<String, Object>> yefg_start_time = maps.stream().filter(map -> map.get("yefg_start_time") != null).sorted(Comparator.comparing(map -> {
                                            Object j = map.get("yefg_start_time");
                                            return j == null ? null : (Date) j;
                                        }, Comparator.nullsLast(Comparator.naturalOrder()))).collect(Collectors.toList());
                                        if(yefg_start_time != null && yefg_start_time.size() > 0){
                                            Map<String, Object> stringObjectMap = yefg_start_time.get(0);
                                            cell_.setCellValue(stringObjectMap.getOrDefault(title_entity[i],"").toString());
                                            cell_.setCellStyle(style);
                                            continue;
                                        }

                                    }

                                    if("d_s_ti".equals(title_entity[i]) || "d_s_te".equals(title_entity[i] )
                                            || "d_e_ti".equals(title_entity[i]) || "d_e_te".equals(title_entity[i])){


                                        List<Map<String, Object>> sorted = maps.stream().filter(map -> map.get("d_start_date") != null).sorted(Comparator.comparing(map -> {
                                            Object j = map.get("d_start_date");
                                            return j == null ? null : (Date) j;
                                        }, Comparator.nullsLast(Comparator.naturalOrder()))).collect(Collectors.toList());
                                        if(sorted != null && sorted.size() > 0){

                                            if("d_s_ti".equals(title_entity[i])){
                                                Map<String, Object> stringObjectMap = sorted.get(0);
                                                try {
                                                    Object d_start_date = stringObjectMap.getOrDefault("d_start_date", null);
                                                    cell_.setCellValue( d_start_date == null ? "" : sdf.format((Date) d_start_date));
                                                    cell_.setCellStyle(style);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                continue;
                                            }
                                            if("d_s_te".equals(title_entity[i])){
                                                Map<String, Object> stringObjectMap = sorted.get(0);
                                                cell_.setCellValue(stringObjectMap.getOrDefault("judge_level_text","").toString());
                                                cell_.setCellStyle(style);
                                                continue;
                                            }

                                            if("d_e_ti".equals(title_entity[i])){
                                                Map<String, Object> stringObjectMap = sorted.get(sorted.size() - 1);
                                                try {
                                                    Object d_start_date = stringObjectMap.getOrDefault("d_start_date", null);
                                                    cell_.setCellValue( d_start_date == null ? "" : sdf.format((Date) d_start_date));
                                                    cell_.setCellStyle(style);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                continue;
                                            }
                                            if("d_e_te".equals(title_entity[i])){
                                                Map<String, Object> stringObjectMap = sorted.get(sorted.size() - 1);
                                                cell_.setCellValue(stringObjectMap.getOrDefault("judge_level_text","").toString());
                                                cell_.setCellStyle(style);
                                                continue;
                                            }

                                        }
                                    }

                                }
                            }

                            cell_.setCellValue(value);
                            cell_.setCellStyle(style);
                            continue;
                        }
                        Field declaredField = umsUserInfoViewClass.getDeclaredField(title_entity[i]);
                        declaredField.setAccessible(true);
                        Object obj = declaredField.get(view);
                        // 这里加个额外判断,针对是否是员额法官的
                        if ("yefg".equals(title_entity[i])) {
                            if (null != obj && "1".equals(obj.toString())) {
                                obj = "是";
                            } else {
                                obj = "否";
                            }
                        }
                        if (obj instanceof String) {
                            cell_.setCellValue((String) obj);
                            cell_.setCellStyle(cellStyle);
                        } else if (obj instanceof Integer) {
                            cell_.setCellValue((Integer) obj);
                            cell_.setCellStyle(style);
                        } else if (obj instanceof Date) {
                            cell_.setCellValue((Date) obj);
                            cell_.setCellStyle(dateStyle);
                        }
                    }
                    // 编内人员额外的信息查询

                    Integer nowCellNumber = title_entity.length + 1;
                    if (viewTables != null) {
                        for (String viewName : viewTables) {
                            try {
                                String[] title_ = attachedTitle.get(viewName + "_title");
                                // 要查询的字段
                                String[] entity_ = attachedTitle.get(viewName + "_entity");
                                String field_ = attachedField.get(viewName);
                                String viewName_ = attachedViewName.get(viewName);
                                if (title_ == null || entity_ == null || !org.springframework.util.StringUtils.hasText(field_) || !org.springframework.util.StringUtils.hasText(viewName_)) {
                                    continue;
                                }
                                Map<String, List<Map<String, Object>>> userInfoExtendList = viewController.get(viewName);
                                List<Map<String, Object>> list2 = userInfoExtendList.get(userId);
                                if (list2 == null || list2.size() == 0) {
                                    continue;
                                }
                                Map<String, Object> map = list2.get(0);
                                for (int i = 0; i < title_.length; i++) {
                                    HSSFCell cell_ = row.createCell(nowCellNumber);
                                    Object obj = map.get(entity_[i]);
                                    if (obj == null) {
                                        cell_.setCellValue("");
                                        cell_.setCellStyle(cellStyle);
                                    } else if (obj instanceof String) {
                                        cell_.setCellValue((String) obj);
                                        cell_.setCellStyle(cellStyle);
                                    } else if (obj instanceof Integer) {
                                        cell_.setCellValue((Integer) obj);
                                        cell_.setCellStyle(style);
                                    } else if (obj instanceof Date) {
                                        cell_.setCellValue((Date) obj);
                                        cell_.setCellStyle(dateStyle);
                                    }
                                    nowCellNumber++;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            if (excelDataType != null && excelDataType != 1 && mapList != null && mapList.size() > 0) {
                Map<Integer, String> EnterSrc = new HashMap<>();
                Map<Integer, List<UmsUploadCodeContrast>> contrastMap = new HashMap<>();
                if (excelDataType != null && excelDataType == 2) {
                    // 编外人员进入来源
                    List<UmsExtEnterSrcType> srcTypes = enterSrcTypeService.findAll();
                    for (UmsExtEnterSrcType umsExtEnterSrcType : srcTypes) {
                        EnterSrc.put(umsExtEnterSrcType.getId(), umsExtEnterSrcType.getSrcTypeName());
                    }
                } else if (excelDataType != null && excelDataType == 31) {
                    // 陪审员对应最高院id
                    // 性别，民族，学历，政治面貌
                    Integer[] sourceTypeIdList1 = new Integer[]{3, 5, 11, 13};
                    List<Integer> sourceTypeIdList = Arrays.asList(sourceTypeIdList1);
                    UmsUploadCodeContrastExample example = new UmsUploadCodeContrastExample();
                    example.createCriteria().andSourceTypeIdIn(sourceTypeIdList);
                    List<UmsUploadCodeContrast> umsUploadCodeContrasts = umsUploadCodeContrastService.selectByExample(example);
                    UmsCodeCriteria codeCriteria = new UmsCodeCriteria();
                    codeCriteria.createCriteria().andTypeIdIn(sourceTypeIdList);
                    List<UmsCode> umsCodeList = umsCodeService.selectByExample(codeCriteria);
                    // 聚合两个列表
                    for (UmsCode umsCode : umsCodeList) {
                        boolean isSame = false;
                        for (UmsUploadCodeContrast umsUploadCodeContrast : umsUploadCodeContrasts) {
                            if (umsUploadCodeContrast.getSourceTypeId().equals(umsCode.getTypeId())
                                    && umsUploadCodeContrast.getSourceField().equals(umsCode.getId() + "")) {
                                isSame = true;
                                break;
                            }
                        }
                        if (!isSame) {
                            UmsUploadCodeContrast umsUploadCodeContrast = new UmsUploadCodeContrast();
                            umsUploadCodeContrast.setTargetField(umsCode.getId() + "");
                            umsUploadCodeContrast.setSourceTypeId(umsCode.getTypeId());
                            umsUploadCodeContrast.setSourceField(umsCode.getId() + "");
                            umsUploadCodeContrast.setTargetFieldDescribe(umsCode.getCodeName());
                            umsUploadCodeContrasts.add(umsUploadCodeContrast);
                        }
                    }
                    contrastMap = umsUploadCodeContrasts.stream().collect(Collectors.groupingBy(UmsUploadCodeContrast::getSourceTypeId));
                }
                for (Map map : mapList) {
                    HSSFRow row = workbook.getSheet(sheetName).createRow(row_id++);
                    row.setHeightInPoints(20);
                    // 序号
                    HSSFCell cell = row.createCell(0);
                    cell.setCellValue(sort++);
                    cell.setCellStyle(style);
                    for (int i = 0; i < title_entity.length; i++) {
                        HSSFCell cell_ = row.createCell(i + 1);
                        // 年龄计算
                        if (title_entity[i].equals("age")) {
                            try {
                                Date birthday = null;
                                Object birthday_str = map.get("birthday");
                                if (birthday_str != null) {
                                    birthday = (Date) birthday_str;
                                }
                                if (birthday != null) {
                                    String value = DateUtil.getAge(birthday);
                                    Integer i1 = Integer.parseInt(value);
                                    cell_.setCellValue(i1);
                                    cell_.setCellStyle(style);
                                    continue;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        // 人民陪审员最高院导入excel需要设置的东西
                        if (excelDataType != null && excelDataType == 31) {
                            int defaultValue = 0, targetType = 0;
                            boolean getValue = true;
                            switch (title_entity[i]) {
                                case "gender":
                                    defaultValue = 1;
                                    targetType = 3;
                                    break;
                                case "cardtype":
                                    map.put(title_entity[i], "1,大陆居民身份证");
                                    getValue = false;
                                    break;
                                case "nation":
                                    defaultValue = 1;
                                    targetType = 5;
                                    break;
                                case "political":
                                    defaultValue = 13;
                                    targetType = 13;
                                    break;
                                case "diqu":
                                    map.put(title_entity[i], "1,城镇人口");
                                    getValue = false;
                                    break;
                                case "juror_work":
                                    String juror_work_array[] = new String[]{"01,基层干部", "02,人民团体成员", "03,事业单位职员", "04,专业技术人员", "05,工商业人员", "06,社区工作者", "07,普通居民", "08,农民", "09,进城务工人员", "10,其他人员"};
                                    for (String s : juror_work_array) {
                                        if (s.contains(map.getOrDefault(title_entity[i], defaultValue).toString())) {
                                            getValue = false;
                                            map.put(title_entity[i], s);
                                            break;
                                        }
                                    }
                                    if (getValue) {
                                        map.put(title_entity[i], "10,其他人员");
                                        getValue = false;
                                    }
                                    break;
                                case "isnew":
                                    map.put(title_entity[i], "1,是");
                                    getValue = false;
                                    break;
                                case "jieshu":
                                    map.put(title_entity[i], "1");
                                    getValue = false;
                                    break;
                                case "ajlx":
                                    map.put(title_entity[i], "1,刑事|2,民事|3,行政");
                                    getValue = false;
                                    break;
                                case "quyu1":
                                    map.put(title_entity[i], "_50_重庆市");
                                    getValue = false;
                                    break;
                                case "quyu2":
                                    map.put(title_entity[i], "_500112_渝北区");
                                    getValue = false;
                                    break;
                                case "quyu3":
                                    map.put(title_entity[i], "_500112003_龙溪街道");
                                    getValue = false;
                                    break;
                                case "local_address":
                                    getValue = false;
                                    break;
                                case "pic":
                                    getValue = false;
                                    break;
                                case "education_background":
                                    if (map.get(title_entity[i]) != null) {
                                        switch (map.get(title_entity[i]).toString()) {
                                            case "3":
                                                map.put(title_entity[i], 2);
                                                break;
                                            case "6":
                                                map.put(title_entity[i], 5);
                                                break;
                                        }
                                    }
                                    defaultValue = 11;
                                    targetType = 11;
                                    break;
                                case "idcard":
                                    map.put(title_entity[i], map.get(title_entity[i]).toString().toUpperCase());
                                    getValue = false;
                                    break;
                                default:
                                    getValue = false;
                                    break;
                            }
                            if (getValue) {
                                int sourceGender = Integer.parseInt(map.getOrDefault(title_entity[i], defaultValue).toString());
                                List<UmsUploadCodeContrast> umsUploadCodeContrasts = contrastMap.get(targetType);
                                for (UmsUploadCodeContrast umsUploadCodeContrast : umsUploadCodeContrasts) {
                                    if (Integer.parseInt(umsUploadCodeContrast.getSourceField()) == sourceGender) {
                                        String target = umsUploadCodeContrast.getTargetField() + "," + umsUploadCodeContrast.getTargetFieldDescribe();
                                        map.put(title_entity[i], target);
                                        break;
                                    }
                                }
                            }
                        }
                        Object obj = map.get(title_entity[i]);
                        if (obj instanceof String) {
                            if (excelDataType != null && excelDataType == 31) {
                                cell_.setCellValue(((String) obj).replace("'", "").replace("\"", "").replace("#", "号"));
                                cell_.setCellStyle(cellStyle);
                            } else {
                                cell_.setCellValue((String) obj);
                                cell_.setCellStyle(cellStyle);
                            }
                        } else if (obj instanceof Integer) {
                            cell_.setCellValue((Integer) obj);
                            cell_.setCellStyle(style);
                        } else if (obj instanceof Date) {
                            cell_.setCellValue((Date) obj);
                            cell_.setCellStyle(dateStyle);
                        }
                    }
                    for (int i = 0; i < extend_title_entity.length; i++) {
                        HSSFCell cell_ = row.createCell(i + 1 + title_entity.length);
                        // 进入途径 没有视图自己查编码
                        if (extend_title_entity[i].equals("enter_src")) {
                            try {
                                Object enter_src = map.get("enter_src");
                                if (enter_src instanceof Integer) {
                                    String s = EnterSrc.get(enter_src);
                                    cell_.setCellValue(s);
                                    cell_.setCellStyle(cellStyle);
                                    continue;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        Object obj = map.get(extend_title_entity[i]);
                        if (obj instanceof String) {
                            cell_.setCellValue((String) obj);
                            cell_.setCellStyle(cellStyle);
                        } else if (obj instanceof Integer) {
                            cell_.setCellValue((Integer) obj);
                            cell_.setCellStyle(style);
                        } else if (obj instanceof Date) {
                            cell_.setCellValue((Date) obj);
                            cell_.setCellStyle(dateStyle);
                        }
                    }
                    // 人民陪审员最高院导入excel需要插入图片
                    if (excelDataType != null && excelDataType == 31) {
                        String rootPath = ServletActionContext.getServletContext().getRealPath("/");
                        String fileName = rootPath + "/zgyExcelDefaultPic.jpg";
                        InputStream is = new FileInputStream(fileName);
                        byte[] bytes1 = IOUtils.toByteArray(is);
                        int pictureIdx = workbook.addPicture(bytes1, Workbook.PICTURE_TYPE_JPEG);
                        CreationHelper helper = workbook.getCreationHelper();
                        Drawing drawing = sheet.createDrawingPatriarch();
                        ClientAnchor anchor = helper.createClientAnchor();
                        // 图片插入坐标
                        anchor.setCol1(25);
                        int num = sort - 1;
                        anchor.setRow1(num);
                        // 插入图片
                        Picture pict = drawing.createPicture(anchor, pictureIdx);
                        pict.resize(0.5, 0.5);//缩放为0.5倍，避免出现覆盖的情况
                    }
                }
            }
            File tempFile = File.createTempFile(UUID.randomUUID().toString(), ".xls");
            fileOutputStream = new FileOutputStream(tempFile);
            workbook.write(fileOutputStream);
//            System.out.println(tempFile.getAbsolutePath());
            bytes = FileUtils.readFileToByteArray(tempFile);
//            bytes = workbook.getBytes();
            //记得删除临时文件
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @Action("batchEdit")
    public String batchEdit() {
        System.out.println(getCourtNo() + "\n");
        System.out.println(getDeptNo() + "\n");
        return "batchEdit";
    }

    /**
     * 组成查询条件
     * @param smff 来自什么方法
     * @return
     */
    private UmsUserInfoViewCriteria makeCriteria(String smff) {
        UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
        UmsUserInfoViewCriteria.Criteria c = criteria.createCriteria();
        if (userType < 10) {
            c.andUserTypeEqualTo(userType);
        } else {
            c.andUserTypeGreaterThanOrEqualTo(userType);
        }
        if (!isNullOrEmpty(fullname)) {
            c.andFullnameLike("%" + fullname + "%");
        }
        if (gender != null) {
            c.andGenderEqualTo(gender);
        }
        if (StringUtils.isNotEmpty(administrationPosition)) {
            c.andAdministrationPositionEqualTo(administrationPosition);
        }
        if (StringUtils.isNotEmpty(rank)) {
            c.andRankEqualTo(rank);
        }
        if (enterDateStart != null) {
            c.andEnterDateGreaterThanOrEqualTo(enterDateStart);
        }
        if (enterDateEnd != null) {
            c.andEnterDateLessThanOrEqualTo(enterDateEnd);
        }
        if (StringUtils.isNotEmpty(courtNo)) {
            String[] split = courtNo.split(",");
            List<Integer> courtList = Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
            c.andCourtNoIn(courtList);
        }
        if (deptNo != null) {
            // 需要查询出当前部门及所有子部门下的人员
            UmsDepartmentKey key = new UmsDepartment();
            key.setCourtNo(Integer.parseInt(courtNo));
            key.setDeptNo(deptNo);
            List<Integer> collect = umsDepartmentService.listAllChildrenByKey(key).stream().map(UmsDepartment::getDeptNo).collect(Collectors.toList());
            collect.add(deptNo);
            c.andDepartmentIn(collect);
        }
        if (isInfoComplete != null && isInfoComplete == 1) {
            c.andIsInfoCompleteEqualTo(1);
        }
        if (isInfoComplete != null && isInfoComplete == 0) {
            c.andIsInfoCompleteNotEqualTo(1);
        }
        if (isValid != null) {
            c.andIsValidEqualTo(isValid);
            // 如果是启用状态要把调离原因不为空的排除掉
            if (isValid == 1 && leaveReason == null && userType == 1) {
                c.andLeaveReasonIsNull();
            }
        }
        if (leaveReason != null) {
            c.andLeaveReasonEqualTo(leaveReason);
        }
        if (birthdayN != null && birthdayM == null) {
            c.andBirthdayGreaterThanOrEqualTo(birthdayN);
        }
        if (birthdayM != null && birthdayN == null) {
            c.andBirthdayLessThanOrEqualTo(birthdayM);
        }
        if (birthdayN != null && birthdayM != null && birthdayN.before(birthdayM)) {
            c.andBirthdayBetween(birthdayN, birthdayM);
        }
        if (birthdayM != null && birthdayN != null && birthdayM.before(birthdayN)) {
            c.andBirthdayBetween(birthdayM, birthdayN);
        }
        if (StringUtils.isNotEmpty(educationBackground)) {
            c.andEducationBackgroundEqualTo(educationBackground);
        }
        if (department != null) {
            c.andDepartmentEqualTo(department);
        }
        if ("null".equals(phoneNumber)) {
            c.andPhoneNumberIsNull();
        }
        if ("NotNull".equals(phoneNumber)) {
            c.andPhoneNumberIsNotNull();
        }
        if (yefg != null) {
            if ("excelDownload".equals(smff)) {
                c.andYefgEqualTo(yefg);
            } else {
                if(yefg == 1){
                    // 判断是否是员额法官，是有两个条件，1 主表中yefg=1 2 子表中当前信息时员额法官
                    c.andYefgEqualTo1();
                }else{
                    c.andYefgEqualTo(yefg);
                }

            }
        }
        if (StringUtils.isNotEmpty(personnelClassification)) {
            c.andPersonnelClassificationEqualTo(personnelClassification);
        }
        if (StringUtils.isNotEmpty(job)) {
            c.andJobEqualTo(job);
        }
        if (StringUtils.isNotEmpty(lawPosition)) {
            c.andLawPositionEqualTo(lawPosition);
        }
        if (isFg != null) {
            if (isFg == 1)
                c.andLawPositionLessThanOrEqualTo(7);
            else if (isFg == 0)
                c.andLawPositionGreaterThan(7);
        }
        // 地方编制 中央编制
        if (orgCode != null) {
            List<Integer> codeList = orgCodeMap.get(orgCode);
            List<Integer> codeList2 = new ArrayList<>();
            for (Integer integer : codeList) {
                codeList2.add(integer);
            }
            if (codeList != null) {
                if ("2".equals(orgCode)) {
                    // 将preparation为null的放入地方事业编制里面
                    c.orPreparationIn(codeList2);
                }
                else {
                    c.andPreparationIn(codeList2);
                }
            }
        }
        return criteria;
    }

    // 组成查询条件改变编制类型
    private UmsUserInfoViewCriteria makeCriteriaForGbbzlx() {
        UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
        UmsUserInfoViewCriteria.Criteria c = criteria.createCriteria();
        if (userType < 10) {
            c.andUserTypeEqualTo(userType);
        } else {
            c.andUserTypeGreaterThanOrEqualTo(userType);
        }
        if (!isNullOrEmpty(fullname)) {
            c.andFullnameLike("%" + fullname + "%");
        }
        if (gender != null) {
            c.andGenderEqualTo(gender);
        }
        if (StringUtils.isNotEmpty(administrationPosition)) {
            c.andAdministrationPositionEqualTo(administrationPosition);
        }
        if (StringUtils.isNotEmpty(rank)) {
            c.andRankEqualTo(rank);
        }
        if (enterDateStart != null) {
            c.andEnterDateGreaterThanOrEqualTo(enterDateStart);
        }
        if (enterDateEnd != null) {
            c.andEnterDateLessThanOrEqualTo(enterDateEnd);
        }
        if (StringUtils.isNotEmpty(courtNo)) {
            String[] split = courtNo.split(",");
            List<Integer> courtList = Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
            c.andCourtNoIn(courtList);
            criteria.setCourtNo(Integer.valueOf(courtNo));
        }
        if (deptNo != null) {
            // 需要查询出当前部门及所有子部门下的人员
            UmsDepartmentKey key = new UmsDepartment();
            key.setCourtNo(Integer.parseInt(courtNo));
            key.setDeptNo(deptNo);
            List<Integer> collect = umsDepartmentService.listAllChildrenByKey(key).stream().map(UmsDepartment::getDeptNo).collect(Collectors.toList());
            collect.add(deptNo);
            c.andDepartmentIn(collect);
        }
        if (isInfoComplete != null && isInfoComplete == 1) {
            c.andIsInfoCompleteEqualTo(1);
        }
        if (isInfoComplete != null && isInfoComplete == 0) {
            c.andIsInfoCompleteNotEqualTo(1);
        }
        if (isValid != null) {
            c.andIsValidEqualTo(isValid);
            // 如果是启用状态要把调离原因不为空的排除掉
            if (isValid == 1 && leaveReason == null && userType == 1) {
                c.andLeaveReasonIsNull();
            }
        }
        if (leaveReason != null) {
            c.andLeaveReasonEqualTo(leaveReason);
        }
        if (birthdayN != null && birthdayM == null) {
            c.andBirthdayGreaterThanOrEqualTo(birthdayN);
        }
        if (birthdayM != null && birthdayN == null) {
            c.andBirthdayLessThanOrEqualTo(birthdayM);
        }
        if (birthdayN != null && birthdayM != null && birthdayN.before(birthdayM)) {
            c.andBirthdayBetween(birthdayN, birthdayM);
        }
        if (birthdayM != null && birthdayN != null && birthdayM.before(birthdayN)) {
            c.andBirthdayBetween(birthdayM, birthdayN);
        }
        if (StringUtils.isNotEmpty(educationBackground)) {
            c.andEducationBackgroundEqualTo(educationBackground);
        }
        if (department != null) {
            c.andDepartmentEqualTo(department);
        }
        if ("null".equals(phoneNumber)) {
            c.andPhoneNumberIsNull();
        }
        if ("NotNull".equals(phoneNumber)) {
            c.andPhoneNumberIsNotNull();
        }
        if (yefg != null) {
            c.andYefgEqualTo(yefg);
        }
        if (StringUtils.isNotEmpty(personnelClassification)) {
            c.andPersonnelClassificationEqualTo(personnelClassification);
        }
        if (StringUtils.isNotEmpty(job)) {
            c.andJobEqualTo(job);
        }
        if (StringUtils.isNotEmpty(lawPosition)) {
            c.andLawPositionEqualTo(lawPosition);
        }
        if (isFg != null) {
            if (isFg == 1)
                c.andLawPositionLessThanOrEqualTo(7);
            else if (isFg == 0)
                c.andLawPositionGreaterThan(7);
        }
        // 地方编制 中央编制
        if (orgCode != null) {
            List<Integer> codeList = orgCodeMap.get(orgCode);
            List<Integer> codeList2 = new ArrayList<>();
            for (Integer integer : codeList) {
                codeList2.add(integer);
            }
            if (codeList != null) {
                if ("2".equals(orgCode)) {
                    // 将preparation为null的放入地方事业编制里面
                    c.orPreparationIn(codeList2);
                }
                else {
                    c.andPreparationIn(codeList2);
                }
            }
        }
        return criteria;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAllId() {
        return allId;
    }

    public void setAllId(String allId) {
        this.allId = allId;
    }

    public String getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(String courtNo) {
        this.courtNo = courtNo;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getIsInfoComplete() {
        return isInfoComplete;
    }

    public void setIsInfoComplete(Integer isInfoComplete) {
        this.isInfoComplete = isInfoComplete;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(Integer leaveReason) {
        this.leaveReason = leaveReason;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getInnerCourtNo() {
        return innerCourtNo;
    }

    public void setInnerCourtNo(String innerCourtNo) {
        this.innerCourtNo = innerCourtNo;
    }

    public Integer getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(Integer educationBackground) {
        this.educationBackground = educationBackground;
    }

    public Date getBirthdayN() {
        return birthdayN;
    }

    public void setBirthdayN(Date birthdayN) {
        this.birthdayN = birthdayN;
    }

    public Date getBirthdayM() {
        return birthdayM;
    }

    public void setBirthdayM(Date birthdayM) {
        this.birthdayM = birthdayM;
    }

    public Integer getExcelDataType() {
        return excelDataType;
    }

    public void setExcelDataType(Integer excelDataType) {
        this.excelDataType = excelDataType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getYefg() {
        return yefg;
    }

    public void setYefg(Integer yefg) {
        this.yefg = yefg;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getIsFg() {
        return isFg;
    }

    public void setIsFg(Integer isFg) {
        this.isFg = isFg;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    public String getPersonnelClassification() {
        return personnelClassification;
    }

    public void setPersonnelClassification(String personnelClassification) {
        this.personnelClassification = personnelClassification;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getLawPosition() {
        return lawPosition;
    }

    public void setLawPosition(Integer lawPosition) {
        this.lawPosition = lawPosition;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
//=================================新加方法2016-1-13

    /**
     * 旧人事信息列表
     *
     * @return
     */
    @Action("userinfo_old")
    public String userinfo_old() {
        HttpServletRequest request = ServletActionContext.getRequest();
        UmsUserInfoView userinfo = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

        String fydm = request.getParameter("fydm") == null ? userinfo.getCourtCode() : request.getParameter("fydm");
        String username = request.getParameter("username") == null ? userinfo.getCourtCode() : request.getParameter("username");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", username);


        List<UmsUserInfoOld> list = umsUserInfoOldService.getUserOldList(fydm, parameters);

        data = list;

        return "json";

    }

    /**
     * 新人事信息列表
     *
     * @return
     */
    @Action("userinfo_new")
    public String userinfo_new() {
        HttpServletRequest request = ServletActionContext.getRequest();

        UmsUserInfoView userinfo = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

        String fydm = request.getParameter("fydm") == null ? "" : request.getParameter("fydm");
        String username = request.getParameter("username") == null ? "" : request.getParameter("username");

        Map<String, Object> parameters = new HashMap<>();

        if (StringUtils.isNotEmpty(username)) {
            parameters.put("username", username);
        }


        List<XtptUser> list = xtptUserService.getXtptUserList(fydm, parameters);

        data = list;

        return "json";
    }

    /**
     * 获取部门
     *
     * @return
     */
    @Action("departmentList")
    public String department_list() {
        HttpServletRequest request = ServletActionContext.getRequest();

        String fydm = request.getParameter("fydm") == null ? "" : request.getParameter("fydm");

        List<UmsDepartment> list = umsDepartmentService.selectByCourtNo(fydm);

        data = list;

        return "json";
    }


    @Action("updateXtptUser")
    public String updateXtptUser() {
        HttpServletRequest request = ServletActionContext.getRequest();

        String userid = request.getParameter("userid") == null ? "" : request.getParameter("userid");
        String idcard = request.getParameter("idcard") == null ? "" : request.getParameter("idcard");
        String ignore = request.getParameter("ignore") == null ? "" : request.getParameter("ignore");


        if (StringUtils.isEmpty(userid)) {
            data = "error";
            return "json";
        }


        UmsUserInfoOld old = new UmsUserInfoOld();
        old.setId(userid);

        if (StringUtils.isNotEmpty(idcard)) {
            old.setIdcard(idcard);
        }


        if (StringUtils.isNotEmpty(ignore)) {
            old.setIsIgnore(Integer.valueOf(ignore));
        }

        int upCount = umsUserInfoOldService.update(old);

        if (upCount == 0) {
            data = "0";
        } else {
            data = "1";
        }

        return "json";
    }

    @Action("download")
    public String download() throws IOException {
        String fileName = "test.xls";
        String rspName = ServletActionContext.getServletContext().getRealPath("")
                + "excelDownload/167ddccd-0b29-4ee2-8f78-671957e58679";
        File file = new File(rspName, fileName);
        if (file.exists()) {
            System.out.println("inoutdfd");
        }
        byte[] bytes = FileUtils.readFileToByteArray(file);
        this.inputStream = new ByteArrayInputStream(bytes);
        filename = "通讯录";
        return "stream";

    }

    public static final Map<String, List<Integer>> orgCodeMap = new HashMap<String, List<Integer>>() {
        {
            put("1", Collections.singletonList(1)); //中央政治编制
            put("2", Arrays.asList(2, 4, 5, 8));  //地方事业编制人员
            put("3", Collections.singletonList(3));  //管理纪委派驻人员
        }
    };

    //根据编制类型来进行法院的groupby
    @Action("getCourtByOrganization")
    public String getCourtByOrganization() {

        if (orgCode == null) {
            return "json";
        }
        List<Integer> codeList = orgCodeMap.get(orgCode);
        if (codeList == null) {
            return "json";
        }
        try {

            UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

            //获取权限可以访问的法院
            List<Map<String, Object>> clist = courtFullService.listByAuthUserID(u.getId());

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

            //根据地方编制和中央编制和权限来查询法院

            data = courtFullService.getCourtByOrganization(courtList, codeList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "json";
    }

    private ByteArrayInputStream inputStream;

    private String filename;

    public ByteArrayInputStream getInputStream() throws UnsupportedEncodingException {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFilename() throws UnsupportedEncodingException {
        return URLEncoder.encode(filename, "UTF-8");
    }

    public void setFilename(String filename) throws UnsupportedEncodingException {
        this.filename = new String(filename.getBytes("ISO8859-1"), "utf-8");
    }

    @Action("batch")
    public String selectBatchById() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, allId.split(","));
        data = userInfoViewService.selectBatchById(list);
        return "json";
    }

    //质量计算 统计字段为空的人员
    @Action("zljs")
    public String zljs() {
        // 最后加上自己配置的信息
        UmsCheckInfoSqlExample sqlExample = new UmsCheckInfoSqlExample();
        // 查询配置在数据库表ums_check_info_sql里的sql语句,条件是空的
        List<UmsCheckInfoSql> umsCheckInfoSqls = infoSqlMapper.selectByExample(sqlExample);
//        // 加上 level (这个是什么意思?为什么不在数据库里加)
//        UmsCheckInfoSql newSql = new UmsCheckInfoSql();
//        newSql.setId("level");
//        newSql.setSqlQuery("SELECT  count(DISTINCT a.id ) FROM  ums_user_info_view a  , ums_level_info b\n" + "WHERE a.id = b.user_id and a.level is NULL and a.user_type = 1 and a.is_valid = 1  and ifnull(b.helper_level,'') = ''\n" + "and  ( a.is_info_complete is null or a.is_info_complete != 1) and b.n_present_info=1");
//        newSql.setSqlReverse("SELECT  DISTINCT a.* FROM  ums_user_info_view a  , ums_level_info b\n" + "WHERE a.id = b.user_id and a.level is NULL and a.user_type = 1 and a.is_valid = 1  and ifnull(b.helper_level,'') = ''\n" + "and  ( a.is_info_complete is null or a.is_info_complete != 1) and b.n_present_info=1");
        // 这个sortList是用来存什么的?
        List<String> sortList = new ArrayList<>();
        for (UmsCheckInfoSql u : umsCheckInfoSqls) {
            String uid = "user-defined_" + u.getId();
            sortList.add(uid);
        }
//        umsCheckInfoSqls.add(newSql);
//        List<String> sortList_old = Arrays.asList("preparation", "nation", "birthday", "political", "job", "personnelClassification", "positionNature", "level", "lawPosition", "incompleteLawPosition", "incompleteYefg", "gender", "educationBackground", "degree", "major", "rank", "idcard", "enterDate", "incompletePolitical", "incompleteFamily", "institution1", "institution2", "institution3", "institution4", "institution5", "institution6");
//        sortList.addAll(sortList_old);
        Map<String, Object> returnMap = new TreeMap<>(Comparator.comparing(sortList::indexOf));
//        Map<String, Object> returnMap = new TreeMap<>(Comparator.comparing(sortList::indexOf));
        // 编制 preparation 民族 nation 年龄 birthday 政治面貌 political 性别 gender 学历  educationBackground 学位 degree 专业 major  职级 rank 身份证  idcard 进入日期 enterDate
//        List<String> nullFieldList = Arrays.asList("preparation", "nation", "birthday", "political", "gender", "educationBackground", "degree", "major", "idcard", "rank", "enterDate", "job", "personnelClassification", "positionNature");
        // 需要联查的list 法官等级 level 法律职务 lawPosition 法律职务不完整 由于生产方式的原因  这种问题应该不会出现 lawPosition "法官法律职务为空" "未删除且在职的人员中，法律职务子集有当前信息为是的记录，但人员基本信息法律职务为空的"
//        List<String> unionFieldList = Arrays.asList("lawPosition");
//        UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
        // 获得该登陆人员能查看的法院的gfdm
        List<Integer> authCourt = getAuthCourt();
//        for (String s : nullFieldList) {
//            try {
//                UmsUserInfoViewCriteria criteria1 = makeCriteriaForCount(s, criteria, authCourt);
//                // 查询该条件在表ums_user_info_view中数量
//                int total = userInfoViewService.count(criteria1);
//                returnMap.put(s, total);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        Map<String, Object> baseQueryMap = new HashMap<>();
//        if (authCourt != null && authCourt.size() > 0) {
//            baseQueryMap.put("list", authCourt);
//        }
//        if (org.springframework.util.StringUtils.hasText(courtNo)) {
//            baseQueryMap.put("courtNo", courtNo);
//        }
//        for (String s : unionFieldList) {
//            try {
//                Map<String, Object> queryMap = new HashMap<>();
//                queryMap.putAll(baseQueryMap);
//                // 获得表名:1"level":"ums_level_info"; 2"lawPosition":"ums_legal_job";3其他为空
//                String tableName = getTableNameByField(s);
//                if (StringTools.isNullOrEmpty(tableName)) {
//                    queryMap.put(s, 0);
//                    continue;
//                }
//                queryMap.put("table", tableName);
//                queryMap.put("field", StringTools.camelOrPascalToUnderline(s));
//                // 查询表ums_user_info_view和上面中的一种:select count(distinct a.id ) from ums_user_info_view a, ${table} b where a.id = b.user_id and b.n_present_info = 1 and a.${field} is null and a.user_type = 1 and a.is_valid = 1 and (a.is_info_complete is null or a.is_info_complete != 1)
//                Integer count = userInfoViewService.unionCount(queryMap);
//                returnMap.put(s, count);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        // 法律职务不完整 incompleteLawPosition
        // sql:select count(1) from ums_user_info_view a where a.user_type = 1 and a.is_valid = 1 and (a.is_info_complete is null or a.is_info_complete != 1) and (a.personnel_classification = 1 or a.personnel_classification like '1_') and not exists (select 1 from ums_legal_job b where a.id = b.user_id and b.n_present_info = 1)
//        Integer incompleteCountForLegal = userInfoViewService.incompleteCountForLegal(baseQueryMap);
        // 1政治面貌，必填 2、加入日期，政治面貌选择共青团员、无党派民主人士、群众、其他之外的项目必填 3、转正日期，政治面貌选择中共党员时必填 4、是否当前信息，必填
        // sql:select count(distinct a.id) from ums_user_info_view a, ums_political_info b where a.id = b.user_id and a.user_type = 1 and a.is_valid = 1 and (a.is_info_complete is null or a.is_info_complete != 1) and (b.n_political is null or (b.n_political not in (3, 12, 13, 14) and b.d_entry_date is null ) or(b.n_political = 1 and b.d_positive_date is null) or b.n_present_info is null)
//        Integer incompleteCountForPolitical = userInfoViewService.incompleteCountForPolitical(baseQueryMap);
        // 未删除且在职的人员中，未维护家庭成员的
        // sql:select count(distinct a.id) from ums_user_info_view a where a.user_type = 1 and a.is_valid = 1 and (a.is_info_complete is null or a.is_info_complete != 1) and not exists (select 1 from ums_family_info b where a.id = b.user_id)
//        Integer incompleteCountForFamily = userInfoViewService.incompleteCountForFamily(baseQueryMap);
        // 员额法官信息不完整
        // sql:select count(1) from ums_user_info_view a where a.user_type = 1 and a.is_valid = 1 and ( a.is_info_complete is null or a.is_info_complete != 1) and (a.personnel_classification=1 or a.personnel_classification like '1_') and yefg = 1 and not exists ( select 1 from ums_level_info b where a.id = b.user_id and b.n_present_info = 1 and b.yefg_start_time is not null and b.is_yefg = 1)
//        Integer incompleteCountForYefg = userInfoViewService.incompleteCountForYefg(baseQueryMap);
//        returnMap.put("incompleteLawPosition", incompleteCountForLegal);
//        returnMap.put("incompletePolitical", incompleteCountForPolitical);
//        returnMap.put("incompleteFamily", incompleteCountForFamily);
//        returnMap.put("incompleteYefg", incompleteCountForYefg);
        // institution1 未撤销、未删除且机构名称中包含“法院”，但机构基本信息中“单位性质”未选择的; institution3 未撤销、未删除且机构名称中包含“高级”，但机构基本信息中“单位（部门）级别”未选择“高级法院”的； 机构名称中包含“中级”，但机构基本信息中“单位（部门）级别”未选择“中级法院”的;
//        List<String> institutionList1 = Arrays.asList("institution1", "institution3", "institution5");
//        for (String s : institutionList1) {
//            if ("institution5".equals(s)) {
//                // institution5 每家法院必须存在一个院领导部门
//                // sql: select count(1) from ums_court_full a where a.court_no is not null and not exists (select 1 from ums_department b where a.court_no = b.court_no and b.org_code = "000")
//                int count = courtFullService.countCourtForNOLeaderDept(baseQueryMap);
//                returnMap.put(s, count);
//                continue;
//            }
//            UmsCourtFullCriteria criteria1 = makeInstitutionExample(s, authCourt);
//            // sql:select count(*) from ums_court_full
//            int count = courtFullService.countByExample(criteria1);
//            returnMap.put(s, count);
//        }
        //institution2 未撤销、未删除且机构名称中不包含“法院”，但机构基本信息中“单位（部门）级别”选择为法院的; institution4 未撤销、未删除且机构名称中包含“班子成员”且不包含“非”，但机构基本信息中“是否院领导”未选择是的; institution6 未撤销、未删除且机构名称中包含“人民法庭”，但机构基本信息中“是否人民法庭”未选择是的
//        List<String> institutionList2 = Arrays.asList("institution2", "institution4", "institution6");
//        for (String s : institutionList2) {
//            UmsDepartmentCriteria umsDepartmentCriteria = makeInstitutionExample2(s, authCourt);
//            // sql:select count(*) from ums_department
//            int count = umsDepartmentService.countByExample(umsDepartmentCriteria);
//            returnMap.put(s + "", count);
//        }
        // 这里是在处理数据库表ums_check_info_sql里配置的sql语句
        for (UmsCheckInfoSql u : umsCheckInfoSqls) {
            try {
                String sqlQuery = u.getSqlQuery();
                if (org.springframework.util.StringUtils.hasText(courtNo)) {
                    sqlQuery += " and a.court_no = " + courtNo;
                }
                // 拼接额外的sql,这里是在拼接法院
                StringBuffer str = new StringBuffer("");
                if (authCourt != null && authCourt.size() > 0) {
                    str.append(" and a.court_no in ( ");
                    for (int i = 0; i < authCourt.size(); i++) {
                        Integer integer = authCourt.get(i);
                        str.append(" '").append(integer).append("' ");
                        if (i != authCourt.size() - 1) {
                            str.append(",");
                        }
                    }
                    str.append(" ) ");
                }
                sqlQuery += str;
                Integer count = sqlSession.selectOne("cn.net.withub.ums.dao.UmsUserInfoViewMapper.executeCountSql", sqlQuery);
                if (u.getId().equals("level")) {
                    returnMap.put("level", count);
                } else {
                    String uid = "user-defined_" + u.getId();
                    returnMap.put(uid, count);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        data = returnMap;
        return "json";
    }

    @Action("getZljsConfig")
    public String getZljsConfig() {

        Map<String, Object> returnMap = new HashMap<>();
        data = returnMap;

        UmsCheckInfoSqlExample sqlExample = new UmsCheckInfoSqlExample();
        List<UmsCheckInfoSql> umsCheckInfoSqls = infoSqlMapper.selectByExample(sqlExample);
        if (umsCheckInfoSqls.size() > 0) {

            for (UmsCheckInfoSql u : umsCheckInfoSqls) {
                Map<String, Object> innerMap = new HashMap<>();
                innerMap.put("val1", u.getRuleTitle());
                innerMap.put("val2", u.getRuleDescribe());
                returnMap.put("user-defined_" + u.getId(), innerMap);

            }

        }

        return "json";
    }

    @Action("getZljsResult")
    public String getZljsResult() {
        Map<String, Object> returnMap = new HashMap<>();
//        List<String> nullFieldList = Arrays.asList("preparation", "nation", "birthday", "political", "gender", "educationBackground", "degree", "major", "idcard", "rank", "enterDate", "job", "personnelClassification", "positionNature");
//        List<String> unionFieldList = Arrays.asList("lawPosition");
        Integer total = 0;
        List<UmsUserInfoView> list = new ArrayList<>();
        if (StringTools.isNullOrEmpty(query)) {
            returnMap.put("results", total);
            returnMap.put("rows", list);
            data = returnMap;
            return "json";
        }
        try {
//            UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
            List<Integer> authCourt = getAuthCourt();
//            if (nullFieldList.indexOf(query) > -1) {
//                UmsUserInfoViewCriteria criteria1 = makeCriteriaForCount(query, criteria, authCourt);
//                criteria1.setOrderByClause("  case when sort_no is null then 1 else 0 end ,sort_no ASC ");
//                RowBounds rowBounds = new RowBounds(start, limit);
//                list = userInfoViewService.searchBySort(criteria1, rowBounds);
//                total = userInfoViewService.count(criteria1);
//                returnMap.put("results", total);
//                returnMap.put("rows", list);
//                data = returnMap;
//                return "json";
//            }
            Map<String, Object> baseQueryMap = new HashMap<>();
            if (authCourt != null && authCourt.size() > 0) {
                baseQueryMap.put("list", authCourt);
            }
            if (org.springframework.util.StringUtils.hasText(courtNo)) {
                baseQueryMap.put("courtNo", courtNo);
            }
            baseQueryMap.put("start", start);
            baseQueryMap.put("limit", limit);
            // 机构查询
//            if (query.contains("institution")) {
//                if ("institution5".equals(query)) {
//                    total = courtFullService.countCourtForNOLeaderDept(baseQueryMap);
//                    List<UmsCourtFull> umsCourtFulls = courtFullService.selectCourtForNOLeaderDept(baseQueryMap);
//                    returnMap.put("results", total);
//                    returnMap.put("rows", umsCourtFulls);
//                    data = returnMap;
//                    return "json";
//                }
//                List<String> institutionList1 = Arrays.asList("institution1", "institution3");
//                if (institutionList1.contains(query)) {
//                    UmsCourtFullCriteria criteria1 = makeInstitutionExample(query, authCourt);
//                    total = courtFullService.countByExample(criteria1);
//                    List<UmsCourtFull> umsCourtFulls = courtFullService.selectByExample(criteria1);
//                    returnMap.put("results", total);
//                    returnMap.put("rows", umsCourtFulls);
//                    data = returnMap;
//                    return "json";
//                }
//                List<String> institutionList2 = Arrays.asList("institution2", "institution4", "institution6");
//                if (institutionList2.contains(query)) {
//                    UmsDepartmentCriteria umsDepartmentCriteria = makeInstitutionExample2(query, authCourt);
//                    total = umsDepartmentService.countByExample(umsDepartmentCriteria);
//                    List<UmsDepartment> umsDepartments = umsDepartmentService.selectByExample(umsDepartmentCriteria);
//                    returnMap.put("results", total);
//                    returnMap.put("rows", umsDepartments);
//                    data = returnMap;
//                    return "json";
//                }
//            }
//            if (unionFieldList.indexOf(query) > -1) {
//                Map<String, Object> queryMap = new HashMap<>();
//                queryMap.putAll(baseQueryMap);
//                String tableName = getTableNameByField(query);
//                queryMap.put("table", tableName);
//                queryMap.put("field", StringTools.camelOrPascalToUnderline(query));
//                total = userInfoViewService.unionCount(queryMap);
//                list = userInfoViewService.unionQuery(queryMap);
//                returnMap.put("results", total);
//                returnMap.put("rows", list);
//                data = returnMap;
//                return "json";
//            }
//            switch (query) {
//                case "incompleteLawPosition":
//                    total = userInfoViewService.incompleteCountForLegal(baseQueryMap);
//                    list = userInfoViewService.incompleteQueryForLegal(baseQueryMap);
//                    break;
//                case "incompletePolitical":
//                    total = userInfoViewService.incompleteCountForPolitical(baseQueryMap);
//                    list = userInfoViewService.incompleteQueryForPolitical(baseQueryMap);
//                    break;
//                case "incompleteFamily":
//                    total = userInfoViewService.incompleteCountForFamily(baseQueryMap);
//                    list = userInfoViewService.incompleteQueryForFamily(baseQueryMap);
//                    break;
//                case "incompleteYefg":
//                    total = userInfoViewService.incompleteCountForYefg(baseQueryMap);
//                    list = userInfoViewService.incompleteQueryForYefg(baseQueryMap);
//                    break;
//            }
            try {
                //自己定义的东西
                if (query.contains("user-defined_")) {
                    String[] split = query.split("_");
                    String definedId = split[1];
                    UmsCheckInfoSql umsCheckInfoSql = infoSqlMapper.selectByPrimaryKey(definedId.trim());
                    if (umsCheckInfoSql != null) {
                        String sqlQuery = umsCheckInfoSql.getSqlQuery();
                        String sqlReverse = umsCheckInfoSql.getSqlReverse();
                        //拼接额外的sql
                        StringBuffer str = new StringBuffer("");
                        if (authCourt != null && authCourt.size() > 0) {
                            str.append(" and a.court_no in ( ");
                            for (int i = 0; i < authCourt.size(); i++) {
                                Integer integer = authCourt.get(i);
                                str.append(" '").append(integer).append("' ");
                                if (i != authCourt.size() - 1) {
                                    str.append(",");
                                }
                            }
                            str.append(" ) ");
                        }
                        if (org.springframework.util.StringUtils.hasText(courtNo)) {
                            str.append(" and a.court_no = ").append(courtNo);
                        }
                        sqlQuery += str;
                        sqlReverse += str + " limit " + start + "," + limit;
                        total = sqlSession.selectOne("cn.net.withub.ums.dao.UmsUserInfoViewMapper.executeCountSql", sqlQuery);
                        list = sqlSession.selectList("cn.net.withub.ums.dao.UmsUserInfoViewMapper.executeJdbcSql", sqlReverse);
                    }
                }
                //自己定义的东西
//                if (query.equals("level")) {
//                    //加上 level
//                    UmsCheckInfoSql umsCheckInfoSql = new UmsCheckInfoSql();
//                    umsCheckInfoSql.setId("level");
//                    umsCheckInfoSql.setSqlQuery("SELECT  count(DISTINCT a.id ) FROM  ums_user_info_view a  , ums_level_info b\n" + "WHERE a.id = b.user_id and a.level is NULL and a.user_type = 1 and a.is_valid = 1  and ifnull(b.helper_level,'') = ''\n" + "and  ( a.is_info_complete is null or a.is_info_complete != 1) and b.n_present_info=1");
//                    umsCheckInfoSql.setSqlReverse("SELECT  DISTINCT a.* FROM  ums_user_info_view a  , ums_level_info b\n" + "WHERE a.id = b.user_id and a.level is NULL and a.user_type = 1 and a.is_valid = 1  and ifnull(b.helper_level,'') = ''\n" + "and  ( a.is_info_complete is null or a.is_info_complete != 1) and b.n_present_info=1");
//                    String sqlQuery = umsCheckInfoSql.getSqlQuery();
//                    String sqlReverse = umsCheckInfoSql.getSqlReverse();
//                    //拼接额外的sql
//                    StringBuffer str = new StringBuffer("");
//                    if (authCourt != null && authCourt.size() > 0) {
//                        str.append(" and a.court_no in ( ");
//                        for (int i = 0; i < authCourt.size(); i++) {
//                            Integer integer = authCourt.get(i);
//                            str.append(" '").append(integer).append("' ");
//                            if (i != authCourt.size() - 1) {
//                                str.append(",");
//                            }
//                        }
//                        str.append(" ) ");
//                    }
//                    if (org.springframework.util.StringUtils.hasText(courtNo)) {
//                        str.append(" and a.court_no = ").append(courtNo);
//                    }
//                    sqlQuery += str;
//                    sqlReverse += str + " limit " + start + "," + limit;
//                    total = sqlSession.selectOne("cn.net.withub.ums.dao.UmsUserInfoViewMapper.executeCountSql", sqlQuery);
//                    list = sqlSession.selectList("cn.net.withub.ums.dao.UmsUserInfoViewMapper.executeJdbcSqlForUser", sqlReverse);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            returnMap.put("results", total);
            returnMap.put("rows", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = returnMap;
        return "json";
    }

    private String getTableNameByField(String field) {
        String re = null;
        switch (field) {
            case "level":
                re = "ums_level_info";
                break;
            case "lawPosition":
                re = "ums_legal_job";
                break;
        }
        return re;
    }

    private List<Integer> lawIndex = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 38);

    public UmsUserInfoViewCriteria makeCriteriaForCount(String field, UmsUserInfoViewCriteria criteria, List<Integer> authCourt) {
        if (criteria == null) {
            criteria = new UmsUserInfoViewCriteria();
        }
        criteria.clear();
        UmsUserInfoViewCriteria.Criteria example = criteria.createCriteria();
        example.andUserTypeEqualTo(1);
        example.andIsValidEqualTo(1);
        example.andIsInfoCompleteNotEqualTo(1);
        if (org.springframework.util.StringUtils.hasText(courtNo)) {
            example.andCourtNoEqualTo(Integer.parseInt(courtNo));
        }
        if (authCourt != null && authCourt.size() > 0) {
            example.andCourtNoIn(authCourt);
        }
        switch (field) {
            case "preparation":
                example.andPreparationIsNull();
                break;
            case "nation":
                example.andNationReportIsNull();
                break;
            case "birthday":
                example.andBirthdayIsNull();
                break;
            case "political":
                example.andPoliticalReportIsNull();
                break;
            case "gender":
                example.andGenderIllegalValidate();
                break;
            case "educationBackground":
                example.andEducationBackgroundIsNull();
                break;
            case "degree":
                example.andDegreeIsNull();
                break;
            case "major":
                example.andMajorIsNull();
                break;
            case "idcard":
                example.andIdcardIllegalValidate();
                break;
            case "rank":
                example.andLawPositionNotIn(lawIndex).andRankReportIsNull();
                break;
            case "enterDate":
                example.andEnterDateIsNull();
                break;
            case "personnelClassification":
                example.andPersonnelClassificationIsNull();
                break;
            case "positionNature":
                example.andPositionNatureIsNull();
                break;
            case "job":
                example.andJobIsNull();
                break;
        }
        return criteria;
    }

    public UmsCourtFullCriteria makeInstitutionExample(String field, List<Integer> authCourt) {
        UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
        criteria.clear();
        criteria.setOrderByClause("sort_no");
        UmsCourtFullCriteria.Criteria example = criteria.createCriteria();
        example.andCourtNoIsNotNull();
        if (org.springframework.util.StringUtils.hasText(courtNo)) {
            example.andCourtNoEqualTo(Integer.parseInt(courtNo));
        }
        if (authCourt != null && authCourt.size() > 0) {
            example.andCourtNoIn(authCourt);
        }
        switch (field) {
            case "institution1":
                example.andCourtTypeIsNull();
                break;
            case "institution3":
                example.andCourtStdNameLike("%高级%").andCourtGradeNotEqualTo("7");
                UmsCourtFullCriteria.Criteria criteria1 = new UmsCourtFullCriteria().createCriteria();
                if (authCourt != null && authCourt.size() > 0) {
                    criteria1.andCourtNoIn(authCourt);
                }
                criteria1.andCourtNoIsNotNull().andCourtStdNameLike("%中级%").andCourtGradeNotEqualTo("13");
                criteria.or(criteria1);
                break;
        }
        return criteria;
    }

    public UmsDepartmentCriteria makeInstitutionExample2(String field, List<Integer> authCourt) {
        UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
        criteria.setOrderByClause(" court_no , sort_no ");
        UmsDepartmentCriteria.Criteria example = criteria.createCriteria();
        example.andIsValidEqualTo(1);
        if (org.springframework.util.StringUtils.hasText(courtNo)) {
            example.andCourtNoEqualTo(Integer.parseInt(courtNo));
        }
        if (authCourt != null && authCourt.size() > 0) {
            example.andCourtNoIn(authCourt);
        }
        switch (field) {
            case "institution2":
                example.andCourtLevelIn(Arrays.asList("7", "13", "23"));
                break;
            case "institution4":
                example.andOrgCodeEqualTo("000").andIsLeadersNotEqualTo("1");
                break;
            case "institution6":
                example.andDeptNameLike("%人民法庭%").andIsPeplesNotEqualTo("1");
                break;
        }
        return criteria;
    }

    private List<Integer> getAuthCourt() {
        UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        List<Integer> courtNoList = authorityHelper.accessibleCourtNoList("查看");
        if (courtNoList.contains(-9999)) {//管理员权限 GOGOGO
            return null;
        }
        return courtNoList;
    }

    // 干部任免审批表
    @Action("selectInfo")
    public String selectInfo() {
        Map<String, Object> map = new HashMap<>();
        UmsUserInfoView userInfoView = userInfoViewService.selectById(id);
        // 不同人员分类的现任职务显示不一样
        String personnelClassification = userInfoView.getPersonnelClassification();
        String xrzw = "";
        // 法官 增加编码后需要在这加
        if (personnelClassification.startsWith("1")) {
            xrzw = (userInfoView.getDepartmentText() == null ? "-" : userInfoView.getDepartmentText()) + "   " + (userInfoView.getLawPositionReportText() == null ? "-" : userInfoView.getLawPositionReportText()) + "   " + (userInfoView.getAdministrationPositionReportText() == null ? "-" : userInfoView.getAdministrationPositionReportText()) + "   " + (userInfoView.getLevelText() == null ? "-" : userInfoView.getLevelText());
            // 审判辅助人员
        } else if (personnelClassification.startsWith("2")) {
            // 法官助理
            // 书记员
            // 司法警察
            if (personnelClassification.equals("22") || personnelClassification.equals("23") || personnelClassification.equals("24")) {
                //根据 人员分类 去查找类型
                String helper_level = "";
                String viewName = "levelInfo";
                String whereSql = "";
                String queryField = "";
                RowBounds rowBounds = new RowBounds();
                if(personnelClassification.equals("22") ){
                    whereSql = " and n_level_type = 3  and n_present_info = 1";
                    queryField = "helper_level_text";
                }
                if(personnelClassification.equals("23") ){
                    whereSql = " and n_level_type = 4  and n_present_info = 1 ";
                    queryField = "clerk_level_text";
                }
                if(personnelClassification.equals("24") ){
                    whereSql = " and n_level_type = 2  and n_present_info = 1 ";
                    queryField = "marshal_level_text";
                }
                List list = attachedTableService.selectViewAppendWhere(viewName, id, null, rowBounds, whereSql);
                if(list != null && list.size() > 0){
                    Map<String, Object> o = ( Map<String, Object>)list.get(0);
                    helper_level = MapUtils.getString(o,queryField,"");
                }
//                Map<String, String> fields = new HashMap<>();
//                fields.put("levelInfo", "helper_level_text");
//                Map<String, Object> map1 = attachedTableService.selectDataSetView(fields, id);
//                 = map1 == null ? null : (String) map1.get("helper_level_text");
                xrzw = (userInfoView.getDepartmentText() == null ? "-" : userInfoView.getDepartmentText()) + "   " + (userInfoView.getLawPositionReportText() == null ? "-" : userInfoView.getLawPositionReportText()) + "   " + (userInfoView.getAdministrationPositionReportText() == null ? "-" : userInfoView.getAdministrationPositionReportText()) + "   " + (helper_level == null ? "-" : helper_level);
                // 执行员
                // 司法技术人员
                // 其他审判辅助人员
            } else if (personnelClassification.equals("21") || personnelClassification.equals("25") || personnelClassification.equals("29")) {
                xrzw = (userInfoView.getDepartmentText() == null ? "-" : userInfoView.getDepartmentText()) + "   " + (userInfoView.getLawPositionReportText() == null ? "-" : userInfoView.getLawPositionReportText()) + "   " + (userInfoView.getAdministrationPositionReportText() == null ? "-" : userInfoView.getAdministrationPositionReportText()) + "   " + (userInfoView.getRankReportText() == null ? "-" : userInfoView.getRankReportText());
            }
            // 司法行政人员
        } else if (personnelClassification.startsWith("3")) {
            xrzw = (userInfoView.getDepartmentText() == null ? "-" : userInfoView.getDepartmentText()) + "   " + (userInfoView.getAdministrationPositionReportText() == null ? "-" : userInfoView.getAdministrationPositionReportText()) + "   " + (userInfoView.getRankReportText() == null ? "-" : userInfoView.getRankReportText());
        }
        userInfoView.setAdministrationPositionText(xrzw);
        Map<String, Object> map1 = UserInfoAction.entityToMap(userInfoView);
        // 学历信息
        String eduView = "education_info";
        RowBounds rowBounds = new RowBounds();
        try {
            List<Map<String, Object>> list = attachedTableService.selectView(eduView, id, " sort_no", rowBounds);
            List<Map<String, Object>> returnList = new ArrayList<>();
            // 分别取出学历中 全日制教育教育类型 和 在职教育类型中 学历类型最大的一条数据
            if (list != null && list.size() > 0) {
                Map<String, List<Map<String, Object>>> collect = list.stream().filter(stringObjectMap -> (stringObjectMap.get("n_educate_form") != null && stringObjectMap.get("n_education_background") != null)).collect(Collectors.groupingBy(stringObjectMap1 -> stringObjectMap1.get("n_educate_form").toString()));

                for (String s : collect.keySet()) {
                    List<Map<String, Object>> list1 = collect.get(s);
                    Map<String, Object> map2 = list1.stream().filter(stringObjectMap -> (stringObjectMap.get("n_educate_form") != null && stringObjectMap.get("n_education_background") != null)).min((o1, o2) -> {
                        Integer o1_ = 0;
                        Integer o2_ = 0;
                        try {
                            o1_ = Integer.parseInt(CommonUtil.getMapStringValue(o1, "n_education_background", "0"));
                        } catch (Exception ignored) {
                        }
                        try {
                            o2_ = Integer.parseInt(CommonUtil.getMapStringValue(o2, "n_education_background", "0"));
                        } catch (Exception ignored) {
                        }
                        return o1_.compareTo(o2_);
                    }).orElse(null);

                    if (map2 != null) {
                        returnList.add(map2);
                    }
                }
            }
            map1.put("eduInfo", returnList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 学位信息
        String degreeView = "degree_info";
        try {
            List<Map<String, Object>> list = attachedTableService.selectView(degreeView, id, "n_degree, sort_no ", rowBounds);
            if (list != null && list.size() > 0) {
                map1.put("degreeList", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 简历信息
        String resumeView = "resume_info";
        try {
            // 添加一个条件,是否上任免表,参数1为上,2为不上.老数据默认是1.新添加数据为必填项.
            List<Map<String, Object>> list = attachedTableService.selectViewAppendWhere(resumeView, id, "d_start_date", rowBounds, " and c_srmb = '1'");
            if (list != null && list.size() > 0) {
                map1.put("resume", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 奖惩信息
        String rewardView = "reward_info";
        try {
            List<Map<String, Object>> list = attachedTableService.selectView(rewardView, id, null, rowBounds);
            if (list != null && list.size() > 0) {
                map1.put("reward", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String punishView = "punish_info";
        try {
            List<Map<String, Object>> list = attachedTableService.selectView(punishView, id, null, rowBounds);
            if (list != null && list.size() > 0) {
                map1.put("punish", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //考核信息
        String assessInfo = "assess_info";
        try {
            RowBounds rowBoundsAssess = new RowBounds(0, 3);
            List<Map<String, Object>> list = attachedTableService.selectView(assessInfo, id, "n_year desc", rowBoundsAssess);
            if (list != null && list.size() > 0) {
                map1.put("assess", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 家庭信息
        String familyView = "family_info";
        try {
            List<Map<String, Object>> list = attachedTableService.selectView(familyView, id, null, rowBounds);
            if (list != null && list.size() > 0) {
                map1.put("family", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 是否是员额及员额法官开始日期
        try {
            if (userInfoView.getYefg() == 1) {
                UmsLevelInfoCriteria umsLevelInfoCriteria = new UmsLevelInfoCriteria();
                umsLevelInfoCriteria.createCriteria().andUserIdEqualTo(id).andYefgStartTimeIsNotNull();
                // 这里order by yefg_start_time，第一条数据是时间最早的
                umsLevelInfoCriteria.setOrderByClause("yefg_start_time");
                List<UmsLevelInfo> list_umsLevelInfo = umsLevelInfoMapper.selectByExample(umsLevelInfoCriteria);
                if (null != list_umsLevelInfo && list_umsLevelInfo.size() >0) {
                    map1.put("yefg_start_time", list_umsLevelInfo.get(0).getYefgStartTime());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 格式化日期
        formatDate(map1);
        map.putAll(map1);
        mapInfo.putAll(map1);
        data = map;
        return "json";
    }

    private void formatDate(Map<String, Object> map) {

//        20170921日期格式化为年。月，去除日
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");

        map.forEach((key, value) -> {
            if (value instanceof ArrayList) {
                List<Map<String, Object>> valueMap = (List<Map<String, Object>>) value;
                valueMap.forEach(this::formatDate);
            }
            if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
                boolean is = (key.toLowerCase().endsWith("birthday") && !key.toLowerCase().equals("d_birthday")) ||
                        key.toLowerCase().endsWith("date") ||
                        key.toLowerCase().endsWith("time");
                if (is) {
                    String date = value.toString();
                    boolean ishm = false;
                    try {
                        Long.parseLong(value.toString());
                        ishm = true;
                    } catch (Exception ignored) {
                    }

                    if (ishm) {
                        try {
                            Date d = new Date();
                            d.setTime(Long.parseLong(value.toString()));
                            date = df.format(d);
                        } catch (Exception e) {
                            System.out.println("not a date param");
                        }
                    } else {
                        try {
                            date = df.format(value);
                        } catch (Exception e) {
                            System.out.println("not a date param");
                        }
                    }
                    map.put(key, date);
                }
            }
        });
    }

    @Action("wordEdit")
    public String wordEdit() {
        Map map1 = new HashMap<>();
        selectInfo();
        WsmbUtil wsmbUtil = new WsmbUtil();
        String rootPath = ServletActionContext.getServletContext().getRealPath("/").replace("\\","/");
        rootPath = rootPath.substring(0,rootPath.lastIndexOf("/"));
        String srcPath = rootPath + "/templet.docx";
        String temp = rootPath + "/word/" + uuid + "/temp" + UUID.randomUUID().toString() + ".docx";
        // 获取法院部门
        UmsUserInfoView userInfoView = userInfoViewService.selectById(id);
        String filename = userInfoView.getCourtNoText() + "_" + userInfoView.getDepartmentText() + "_" + userInfoView.getFullname() + ".docx";
        String idTemp = rootPath + "/word/" + uuid + "/" + filename;
        String wordPhoto = rootPath + "/word/" + uuid + "/" + id + ".png";
        try {
            FileTools.createPath(rootPath + "/word");
            FileTools.createPath(rootPath + "/word/" + uuid);
            FileTools.fileChannelCopy(new File(srcPath), new File(temp));
        } catch (Exception e) {
            e.printStackTrace();
        }
        UmsPhoto photo = photoService.selectById(id);
        if (photo != null) {
            try (FileOutputStream fs = new FileOutputStream(wordPhoto);) {
                fs.write(photo.getPhoto());
            } catch (Exception e) {
                System.out.println("word生成时写图片出错：" + e.getMessage());
            }
        }
        // 获取模板中的所有待替换项，并默认设置为空字符串。
        Map<String, String> map = new HashMap<>();
        List<String> strList = wsmbUtil.getReplaceElementsInWord(temp, "<\\w+>");
        for (String s : strList) {
            map.put(s, "");
        }
        try {
            for (Map.Entry<String, Object> entry : mapInfo.entrySet()) {
                if (StringUtils.isNotEmpty(entry.getValue())) {
                    List<Map<String, Object>> rs;
                    map.put("<" + entry.getKey() + ">", entry.getValue() + "");
                    if (entry.getKey().equals("birthday")) {
                        String date = (String) entry.getValue();
                        map.put("<birthday_age>", date.replace("-", ".") + "\n(" + DateUtil.getAge(date) + "岁)");
                    }
                    if (entry.getKey().equals("workDate")) {
                        map.put("<workDate>", ((String) entry.getValue()).replace("-", "."));
                    }
                    if (entry.getKey().equals("politicalDate")) {
                        map.put("<politicalType>", "入党\n时间");
                        String s = (String) entry.getValue();
                        if (StringUtils.isNotEmpty(s) && CommonUtil.getMapStringValue(mapInfo, "politicalText").contains("党员")) {
                            map.put("<politicalDate>", s.replace("-", "."));
                        } else {
                            map.put("<politicalType>", "政治\n面貌");
                            map.put("<politicalDate>", CommonUtil.getMapStringValue(mapInfo, "politicalText"));
                        }
                    }
                    if (entry.getKey().equals("family")) {
                        for (int i = 1; i <= 8; i++) {
                            map.put("<n_relationship_text" + i + ">", "");
                            map.put("<c_name" + i + ">", "");
                            map.put("<tage" + i + ">", "");
                            map.put("<n_political_text" + i + ">", "");
                            map.put("<c_unit_job" + i + ">", "");
                        }
                        rs = (List) entry.getValue();
                        for (int i = 0; i < rs.size(); i++) {
                            String n_relationship_text = CommonUtil.getMapStringValue(rs.get(i), "n_relationship_text");
                            String c_name = CommonUtil.getMapStringValue(rs.get(i), "c_name");
                            String tage = "";
                            if ("1".equals(rs.get(i).get("is_dead"))) {
                                tage = "已故";
                            } else if (rs.get(i).get("d_birthday") != null) {
                                String age = CommonUtil.getMapStringValue(rs.get(i), "d_birthday");
                                tage = DateUtil.getAge(age);
                                //tage = age;
                            }
                            String n_political_text = CommonUtil.getMapStringValue(rs.get(i), "n_political_text");
                            if (n_political_text == null) {
                                n_political_text = "";
                            }
                            String c_unit_job = CommonUtil.getMapStringValue(rs.get(i), "c_unit_job");
                            int i1 = i + 1;
                            map.put("<n_relationship_text" + i1 + ">", n_relationship_text);
                            map.put("<c_name" + i1 + ">", c_name);
                            map.put("<tage" + i1 + ">", tage);
                            map.put("<n_political_text" + i1 + ">", n_political_text);
                            map.put("<c_unit_job" + i1 + ">", c_unit_job);
                        }
                    }
                    if (entry.getKey().equals("resume")) {
                        Date yefg_start_time = mapInfo.get("yefg_start_time") == null ? null : simpleDateFormat_2.parse(mapInfo.get("yefg_start_time").toString());
                        String resumeInfo = "";
                        rs = (List) entry.getValue();
                        // 添加入额时间次数，默认为0，不等于代表已添加，不再添加
                        int yefg_start_time_count = 0;
                        for (int i = 0; i < rs.size(); i++) {
                            rs.get(i).putIfAbsent("d_end_date", "至今");
                            rs.get(i).putIfAbsent("c_unit", "");
                            rs.get(i).putIfAbsent("c_rank", "");
                            String c_rank = StringUtils.isEmpty(CommonUtil.getMapStringValue(rs.get(i), "c_rank")) ? "" : "(" + CommonUtil.getMapStringValue(rs.get(i), "c_rank") + ")";
                            if ((null != yefg_start_time && yefg_start_time_count == 0) && ((!"至今".equals(rs.get(i).get("d_end_date")) && simpleDateFormat_2.parse(rs.get(i).get("d_start_date").toString()).before(yefg_start_time) && simpleDateFormat_2.parse(rs.get(i).get("d_end_date").toString()).after(yefg_start_time)) || ("至今".equals(rs.get(i).get("d_end_date")) || yefg_start_time.before(simpleDateFormat_2.parse(rs.get(i).get("d_end_date").toString()))))) {
                                resumeInfo += CommonUtil.getMapStringValue(rs.get(i), "d_start_date").replace("-", ".") + "-" + CommonUtil.getMapStringValue(rs.get(i), "d_end_date").replace("-", ".") + "  " + CommonUtil.getMapStringValue(rs.get(i), "c_unit") + CommonUtil.getMapStringValue(rs.get(i), "c_dept") + CommonUtil.getMapStringValue(rs.get(i), "c_position") + c_rank;
                                resumeInfo += "（入额时间：" + mapInfo.get("yefg_start_time").toString() + "）\n";
                                yefg_start_time_count++;
                            } else {
                                resumeInfo += CommonUtil.getMapStringValue(rs.get(i), "d_start_date").replace("-", ".") + "-" + CommonUtil.getMapStringValue(rs.get(i), "d_end_date").replace("-", ".") + "  " + CommonUtil.getMapStringValue(rs.get(i), "c_unit") + CommonUtil.getMapStringValue(rs.get(i), "c_dept") + CommonUtil.getMapStringValue(rs.get(i), "c_position") + c_rank + "\n";

                            }
                        }
                        map.put("<res>", resumeInfo);
                    }
                    if (entry.getKey().equals("reward")) {
                        rs = (List) entry.getValue();
                        String rewordInfo = "";
                        for (int i = 0; i < rs.size(); i++) {
                            rewordInfo += CommonUtil.getMapStringValue(rs.get(i), "d_reward_date") + "  " + CommonUtil.getMapStringValue(rs.get(i), "c_approval_unit_code_text") + "  " + CommonUtil.getMapStringValue(rs.get(i), "approval_name_text") + "\n";
                        }
                        map.put("<rewordInfo>", rewordInfo);
                    }
                    if (entry.getKey().equals("punish")) {
                        rs = (List) entry.getValue();
                        String rewordInfo = map.get("<rewordInfo>") == null ? "" : map.get("<rewordInfo>");
                        rewordInfo += "\n";
                        for (int i = 0; i < rs.size(); i++) {
                            rewordInfo += CommonUtil.getMapStringValue(rs.get(i), "d_start_date") + "  " + CommonUtil.getMapStringValue(rs.get(i), "c_unit") + "  " + CommonUtil.getMapStringValue(rs.get(i), "n_punish_reason_text") + "\n";
                        }
                        map.put("<rewordInfo>", rewordInfo);
                    }
                    if (entry.getKey().equals("assess")) {
                        rs = (List) entry.getValue();
                        String assessInfo = "";
                        for (int i = 0; i < (rs.size() >= 3 ? 3 : rs.size()); i++) {
                            assessInfo += CommonUtil.getMapStringValue(rs.get(i), "n_year") + "年度考核 " + CommonUtil.getMapStringValue(rs.get(i), "n_result_text") + "\n";
                        }
                        map.put("<assessInfo>", assessInfo);
                    }
                    if (entry.getKey().equals("eduInfo")) {
                        for (int i = 1; i <= 5; i++) {
                            map.put("<n_education_background_text" + i + ">", "");
                            map.put("<c_college" + i + ">", "");
                            map.put("<n_educate_form_text" + i + ">", "");
                            map.put("<n_edu" + i + ">", "");
                        }
                        // eduInfo 学历
                        rs = (List) entry.getValue();
                        // degree 学位
                        List<Map> degreeList = (List) mapInfo.get("degreeList");
                        // sfyqrzbj:全日制flag 1:有了;0:没有;
                        String sfyqrzbj = "0";
                        // sfyzzbj:在职flag 1:有了;10:没有;
                        String sfyzzbj = "0";

                        // 全日制 和在职 是当前信息的
                        for (int i = 0; i < rs.size(); i++) {

                            // n_educate_form:1为全日制;2位在职教育;
                            String n_educate_form = CommonUtil.getMapStringValue(rs.get(i), "n_educate_form");
                            // 是否是当前信息
                            String n_present_info = CommonUtil.getMapStringValue(rs.get(i), "n_present_info");


                            if ("1".equals(n_present_info)) {
                                if ("0".equals(sfyqrzbj) && "1".equals(n_educate_form)) {

//                                    int j = i + 1;
                                    map.put("<n_education_background_text" + 1 + ">", CommonUtil.getMapStringValue(rs.get(i), "n_education_background_report_text") + " \n ");
                                    map.put("<c_college" + 1 + ">", CommonUtil.getMapStringValue(rs.get(i), "c_college") + "  " + CommonUtil.getMapStringValue(rs.get(i), "n_major_text"));
                                    if (rs.get(i).get("n_educate_form_text") != null) {
                                        map.put("<n_educate_form_text" + 1 + ">", CommonUtil.getMapStringValue(rs.get(i), "n_educate_form_text"));
                                    } else {
                                        map.put("<n_educate_form_text" + 1 + ">", "");
                                    }
                                    map.put("<n_edu" + 1 + ">", "毕业院校系及专业");

                                    sfyqrzbj = "1";
                                }

                                if ("0".equals(sfyzzbj) && "2".equals(n_educate_form)) {

//                                    int j = i + 2;
                                    map.put("<n_education_background_text" + 2 + ">", CommonUtil.getMapStringValue(rs.get(i), "n_education_background_report_text") + " \n ");
                                    map.put("<c_college" + 2 + ">", CommonUtil.getMapStringValue(rs.get(i), "c_college") + "  " + CommonUtil.getMapStringValue(rs.get(i), "n_major_text"));
                                    if (rs.get(i).get("n_educate_form_text") != null) {
                                        map.put("<n_educate_form_text" + 2 + ">", CommonUtil.getMapStringValue(rs.get(i), "n_educate_form_text"));
                                    } else {
                                        map.put("<n_educate_form_text" + 2 + ">", "");
                                    }
                                    map.put("<n_edu" + 2 + ">", "毕业院校系及专业");

                                    sfyzzbj = "1";
                                }

                            }

                        }

                        // 全日制 不是当前信息的第一条
                        if ("0".equals(sfyqrzbj)) {
                            for (int i = 0; i < rs.size(); i++) {
                                // n_educate_form:1为全日制;2位在职教育;
                                String n_educate_form = CommonUtil.getMapStringValue(rs.get(i), "n_educate_form");
                                // 是否是当前信息
                                String n_present_info = CommonUtil.getMapStringValue(rs.get(i), "n_present_info");

                                if ("0".equals(sfyqrzbj) && "1".equals(n_educate_form)) {

//                                    int j = i + 1;
                                    map.put("<n_education_background_text" + 1 + ">", CommonUtil.getMapStringValue(rs.get(i), "n_education_background_report_text") + " \n ");
                                    map.put("<c_college" + 1 + ">", CommonUtil.getMapStringValue(rs.get(i), "c_college") + "  " + CommonUtil.getMapStringValue(rs.get(i), "n_major_text"));
                                    if (rs.get(i).get("n_educate_form_text") != null) {
                                        map.put("<n_educate_form_text" + 1 + ">", CommonUtil.getMapStringValue(rs.get(i), "n_educate_form_text"));
                                    } else {
                                        map.put("<n_educate_form_text" + 1 + ">", "");
                                    }
                                    map.put("<n_edu" + 1 + ">", "毕业院校系及专业");

                                    sfyqrzbj = "1";
                                }
                            }
                        }

                        // 在职
                        if ("0".equals(sfyzzbj)) {
                            for (int i = 0; i < degreeList.size(); i++) {
                                // n_educate_form:1为全日制;2位在职教育;
                                String n_educate_form = CommonUtil.getMapStringValue(degreeList.get(i), "n_educate_form");
                                // 是否是当前信息
                                String n_present_info = CommonUtil.getMapStringValue(degreeList.get(i), "n_present_info");

                                if ("0".equals(sfyzzbj) && "2".equals(n_educate_form) && "1".equals(n_present_info)) {

//                                    int j = i + 2;
                                    map.put("<n_education_background_text" + 2 + ">", CommonUtil.getMapStringValue(degreeList.get(i), "n_degree_text") + " \n ");
                                    map.put("<c_college" + 2 + ">", CommonUtil.getMapStringValue(degreeList.get(i), "c_college") + "  " + CommonUtil.getMapStringValue(degreeList.get(i), "n_major_text"));
                                    if (rs.get(i).get("n_educate_form_text") != null) {
                                        map.put("<n_educate_form_text" + 2 + ">", CommonUtil.getMapStringValue(degreeList.get(i), "n_educate_form_text"));
                                    } else {
                                        map.put("<n_educate_form_text" + 2 + ">", "");
                                    }
                                    map.put("<n_edu" + 2 + ">", "毕业院校系及专业");

                                    sfyzzbj = "1";
                                }
                            }
                        }

                        if ("0".equals(sfyzzbj)) {
                            for (int i = 0; i < degreeList.size(); i++) {
                                // n_educate_form:1为全日制;2位在职教育;
                                String n_educate_form = CommonUtil.getMapStringValue(degreeList.get(i), "n_educate_form");

                                if ("0".equals(sfyzzbj) && "2".equals(n_educate_form)) {

//                                    int j = i + 2;
                                    map.put("<n_education_background_text" + 2 + ">", CommonUtil.getMapStringValue(degreeList.get(i), "n_degree_text") + " \n ");
                                    map.put("<c_college" + 2 + ">", CommonUtil.getMapStringValue(degreeList.get(i), "c_college") + "  " + CommonUtil.getMapStringValue(degreeList.get(i), "n_major_text"));
                                    if (rs.get(i).get("n_educate_form_text") != null) {
                                        map.put("<n_educate_form_text" + 2 + ">", CommonUtil.getMapStringValue(degreeList.get(i), "n_educate_form_text"));
                                    } else {
                                        map.put("<n_educate_form_text" + 2 + ">", "");
                                    }
                                    map.put("<n_edu" + 2 + ">", "毕业院校系及专业");

                                    sfyzzbj = "1";
                                }
                            }
                        }

                    }
                    map.putIfAbsent("<levelText>", "");
                    map.putIfAbsent("<rewordInfo>", "");
                    map.putIfAbsent("<politicalDate>", "");
                    map.putIfAbsent("<lawPositionText>", "");
                }
            }
            // 这两个不显示
            map.put("<levelText>", "");
            map.put("<lawPositionText>", "");
            if (map.get("<politicalType>").equals("")) {
                map.put("<politicalType>", "政治\n面貌");
                map.put("<politicalDate>", CommonUtil.getMapStringValue(mapInfo, "politicalText"));
            }
            map.put("<pic>", wordPhoto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean b = wsmbUtil.replaceAndGenerateWord(temp, idTemp, map);
        map1.put("success", b);
        data = map1;
        return "json";
    }

    @Action("dlWord")
    public void dlFj() {
        HttpServletResponse response = ServletActionContext.getResponse();
        String rootPath = ServletActionContext.getServletContext().getRealPath("/");
        // 获取法院部门
        UmsUserInfoView userInfoView = userInfoViewService.selectById(id);
        String filename = userInfoView.getCourtNoText() + "_" + userInfoView.getDepartmentText() + "_" + userInfoView.getFullname() + ".docx";
        String filePath = rootPath + "/word/" + uuid + "/" + filename;
        File file = new File(filePath);
        InputStream in = null;
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            in = new FileInputStream(filePath);
            response.setContentType("multipart/form-data");
            response.addHeader("Content-Length", "" + file.length());
            response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(filename, "UTF-8"));
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(in != null){
                    in.close();
                }
                if(out != null){
                    out.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Action("downWord")
    public void downWord() {
        HttpServletResponse response = ServletActionContext.getResponse();
        String rootPath = ServletActionContext.getServletContext().getRealPath("/");
        // 获取法院部门
        UmsUserInfoView userInfoView = userInfoViewService.selectById(id);
        String filename = userInfoView.getCourtNoText() + "_" + userInfoView.getDepartmentText() + "_" + userInfoView.getFullname() + ".docx";
        String filePath = rootPath + "/word/" + uuid + "/" + filename;
        File file = new File(filePath);
        InputStream in = null;
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            in = new FileInputStream(filePath);
//            response.setContentType("multipart/form-data");
            response.reset();
//            response.setContentType("application/octet-stream");
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(filename, "UTF-8"));
//            response.setHeader("Content-Disposition", "attachment; filename="+filename);
            response.addHeader("Content-Length", "" + file.length());
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(in != null){
                    in.close();
                }
                if(out != null){
                    out.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Action("dlBatchWord")
    public void dlBatchWord() {
        uuid = UUID.randomUUID().toString();
        //先去文件夹把多余文件删除
        String rootPath = ServletActionContext.getServletContext().getRealPath("/").replace("\\","/");
        rootPath = rootPath.substring(0,rootPath.lastIndexOf("/"));
        String filePath = rootPath + "/word/" + uuid;
        String fp = filePath + "_zip/";
        new File(filePath + "_zip/").mkdirs();
        new File(rootPath + "/word/zip/").mkdirs();
        try {
            long l = System.currentTimeMillis();
            if (ids != null && ids.size() > 0) {
                for (String s : ids) {
                    id = s;
                    wordEdit();
                }
            } else {
                limit = 10000;
                userinfo2();
                Map map = (Map) data;
                List<UmsUserInfoView> list = (List<UmsUserInfoView>) map.get("rows");
                //循环生成
                for (UmsUserInfoView umsUserInfoView : list) {
                    id = umsUserInfoView.getId();
                    wordEdit();
                }
            }
            //坑爹的poi，不知道怎么关闭流，直接关报错，不关又占用文件删不掉，把需要的文件复制到新文件夹再压缩
            File[] files = new File(filePath).listFiles();
            for (File file : files) {
                if (file.getName().contains(".docx") && !file.getName().contains("temp")) {
                    FileTools.fileChannelCopy(file, new File(fp + file.getName()));
                }
            }
            System.out.println(System.currentTimeMillis() - l);
            generZip();
            System.out.println(System.currentTimeMillis() - l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //生成压缩包
    private void generZip() {
        HttpServletResponse response = ServletActionContext.getResponse();

        String rootPath = ServletActionContext.getServletContext().getRealPath("/");
        String filePath = rootPath + "/word/" + uuid + "_zip";
        String zipFile = rootPath + "/word/zip/" + uuid + ".zip";
        ZipUtils.zip(filePath, zipFile);

        File file = new File(zipFile);
        try (InputStream in = new FileInputStream(zipFile); OutputStream out = response.getOutputStream();) {
            response.setContentType("multipart/form-data");
            response.addHeader("Content-Length", "" + file.length());
            response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode("人事任免表.zip", "UTF-8"));

            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Action("selectSql")
    public String selectSql() {

        UmsCheckInfoSqlExample example = new UmsCheckInfoSqlExample();
        if (infoSql != null && org.springframework.util.StringUtils.hasText(infoSql.getId())) {
            example.createCriteria().andIdEqualTo(infoSql.getId());
        }

        if (start != null && limit != null) {
            example.setOffset(start);
            example.setLimit(limit);
        }
        example.setOrderByClause(" create_time desc ");
        List<UmsCheckInfoSql> umsCheckInfoSqls = infoSqlMapper.selectByExample(example);
        int count = infoSqlMapper.countByExample(example);
        Map<String, Object> re = new HashMap<>();
        data = re;
        re.put("rows", umsCheckInfoSqls);
        re.put("results", count);

        return "json";
    }

    @Action("insertSql")
    public String insertSql() {

        Map<String, Object> re = new HashMap<>();
        re.put("success", false);
        data = re;
        if (infoSql == null) {
            return "json";
        }


        try {
            if (org.springframework.util.StringUtils.hasText(infoSql.getSqlQuery())) {
                infoSql.setSqlQuery(infoSql.getSqlQuery().trim());
            }

            if (org.springframework.util.StringUtils.hasText(infoSql.getSqlReverse())) {
                infoSql.setSqlReverse(infoSql.getSqlReverse().trim());
            }


            UmsUserInfoView user = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

            infoSql.setCreateTime(new Date());
            infoSql.setCreator(user.getFullname());
            infoSql.setCreatorId(user.getUserId());

            int insert = 0;
            String id = infoSql.getId();
            if (org.springframework.util.StringUtils.hasText(id)) {
                insert = infoSqlMapper.updateByPrimaryKeySelective(infoSql);
            } else {
                infoSql.setId(UUID.randomUUID().toString());
                insert = infoSqlMapper.insert(infoSql);
            }


            if (insert > 0) {
                re.put("success", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "json";
    }
}
