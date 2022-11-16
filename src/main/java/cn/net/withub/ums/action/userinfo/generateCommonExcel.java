package cn.net.withub.ums.action.userinfo;


import cn.net.withub.ums.dao.UmsExchangeInfoMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsCodeService;
import cn.net.withub.ums.service.UmsExtEnterSrcTypeService;
import cn.net.withub.ums.service.UmsUploadCodeContrastService;
import cn.net.withub.ums.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class generateCommonExcel {

    @Autowired
    private UmsExtEnterSrcTypeService enterSrcTypeService;

    @Autowired
    private UmsExchangeInfoMapper umsExchangeInfoMapper;

    @Autowired
    private UmsUploadCodeContrastService umsUploadCodeContrastService;

    @Autowired
    private UmsCodeService umsCodeService;

    public byte[] generateExcel(Integer excelDataType,List<UmsUserInfoView> list, List<Map> mapList, List<String> viewTables, UmsExtendUserInfoViewCriteria criteria,String selectData) {
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
        String sql_ = "user_id,n_education_background_text,n_major_text,c_college,DATE_FORMAT(d_graduate_date, 'YYYY-MM-DD') as d_graduate_date";
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

                List<Map<String, Object>> levelInfo = null;
                // 员额法官任职前 行政职务等级
                List<Map<String, Object>> maxAdminInfo = umsExchangeInfoMapper.selectMaxAdminInfo(criteria);
                if(criteria != null){
                    //取到当前查询条件的法官等级信息
                    levelInfo = umsExchangeInfoMapper.selectLevelInfo(criteria);

                }else{

                    try {
                        levelInfo = umsExchangeInfoMapper.selectLevelInfoByStr(selectData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 员额法官任职前 行政职务等级

                }

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
                        List<Map<String, Object>> list1 = new ArrayList<>();
                        if(criteria != null){
                            criteria.setFieldSql(sql_);
                            criteria.setTableName(viewName_);
                            criteria.setOrderBySql(order_);
                            list1 = umsExchangeInfoMapper.selectViewNoAspect(criteria);
                        }else{
                            Map<String,String> map = new HashMap<>();
                            map.put("fieldSql",sql_);
                            map.put("tableName",viewName_);
                            map.put("orderBySql",order_);
                            map.put("info",selectData);
                            list1 = umsExchangeInfoMapper.selectViewNoAspectByStr(map);
                        }

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
                        String fileName = rootPath + "\\zgyExcelDefaultPic.jpg";
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


}
