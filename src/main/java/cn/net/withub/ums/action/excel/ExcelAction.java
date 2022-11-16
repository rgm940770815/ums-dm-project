package cn.net.withub.ums.action.excel;

import cn.net.withub.ums.dao.attach.UmsUserInfoAttachedViewsMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsCourtFullService;
import cn.net.withub.ums.service.UmsDepartmentService;
import cn.net.withub.ums.service.UmsUserInfoViewService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/4/26.
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/generateExcel")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class ExcelAction {

    @Autowired
    private UmsDepartmentService umsDepartmentService;

    @Autowired
    private UmsUserInfoViewService umsUserInfoViewService;

    @Autowired
    private UmsCourtFullService umsCourtFullService;

    @Autowired
    private UmsUserInfoAttachedViewsMapper mapper;

    private Map<Integer, Object> codeMap;

    public ExcelAction() {
        Map<Integer, Object> m = new HashMap<>();
        m.put(0, "总理级"); //编码表并没有总理级
        m.put(1, "副总理级");
        m.put(2, "正部长");
        m.put(3, "省部级副职");
        m.put(4, "厅局级正职");
        m.put(5, "厅局级副职");
        m.put(6, "县处级正职");
        m.put(7, "县处级副职");
        m.put(8, "乡科级正职");
        m.put(9, "乡科级副职");
        m.put(10, "科员");
        m.put(11, "办事员");
        m.put(98, "职级未定");
        m.put(99, "其他");
        this.codeMap = m;

    }

    @Action("export")
    public void excelExport() {
        List<UmsCourtFull> court_list = umsCourtFullService.selectByListAll();

        for (UmsCourtFull courtFull : court_list) {
            excelExportByCourtCode(courtFull.getCourtCode());
        }
    }

    /**
     * 单个法院生成一份excel
     *
     * @param courtCode
     */
    public void excelExportByCourtCode(String courtCode) {

        UmsCourtFull Court = umsCourtFullService.getCourtByCourtCode(courtCode);
        if (Court == null) {
            return;
        }
        String courtName = Court.getCourtStdName();

        String path = ServletActionContext.getServletContext().getRealPath("/excel") + "/" + courtName + ".xls";
        File file = new File(path);

        if (file.exists()) {
            deleteFile(file);
        }

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        //部门
        List<Map> list = new ArrayList<>();
        Map<String, Object> Map_level_1 = new HashMap<>(); //一级部门map
        Map<String, Object> Map_level_2 = new HashMap<>();//二级部门map

        UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
        criteria.createCriteria().andCourtCodeEqualTo(courtCode).andLevelEqualTo(1);//一级部门
        criteria.setOrderByClause("sort_no asc");

        List<UmsDepartment> level_1 = umsDepartmentService.selectByExampleNoAspect(criteria);
        for (UmsDepartment de : level_1) {
            Map<String, Object> innerMap = new HashMap<>();
            innerMap.put("C_Name", de.getDeptName());
            innerMap.put("C_PID", "");
            innerMap.put("C_CORP", courtName);
            innerMap.put("N_Order", de.getSortNo());
            Map_level_1.put(String.format("%04d", de.getDeptNo()), de.getDeptName());
            list.add(innerMap);

        }

        criteria.clear();
        criteria.createCriteria().andCourtCodeEqualTo(courtCode).andLevelEqualTo(2);//二级级部门
        criteria.setOrderByClause("sort_no asc");

        List<UmsDepartment> level_2 = umsDepartmentService.selectByExampleNoAspect(criteria);
        for (UmsDepartment de : level_2) {
            Map<String, Object> innerMap = new HashMap<>();
            //父Id
            String parent = de.getLevelCode().substring(0, 4);
            String info = "";
            if (Map_level_1.get(parent) != null) {
                info = (String) Map_level_1.get(parent);
            }
            innerMap.put("C_Name", de.getDeptName());
            innerMap.put("C_PID", info);
            innerMap.put("C_CORP", courtName);
            innerMap.put("N_Order", de.getSortNo());
            Map_level_2.put(String.format("%04d", de.getDeptNo()), de.getDeptName());
            list.add(innerMap);
        }

        criteria.clear();
        criteria.createCriteria().andCourtCodeEqualTo(courtCode).andLevelEqualTo(3);//二级级部门
        criteria.setOrderByClause("sort_no asc");

        List<UmsDepartment> level_3 = umsDepartmentService.selectByExampleNoAspect(criteria);
        for (UmsDepartment de : level_3) {
            Map<String, Object> innerMap = new HashMap<>();
            //父Id
            String parent_1 = de.getLevelCode().substring(0, 4);
            String parent_2 = de.getLevelCode().substring(4, 8);
            String info = "";
            if (Map_level_1.get(parent_1) != null) {
                info += Map_level_1.get(parent_1) + "/";
            }
            if (Map_level_2.get(parent_2) != null) {
                info += (String) Map_level_2.get(parent_2);
            }
            innerMap.put("C_Name", de.getDeptName());
            innerMap.put("C_PID", info);
            innerMap.put("C_CORP", courtName);
            innerMap.put("N_Order", de.getSortNo());
            list.add(innerMap);
        }

        //标题组
        String title[] = {"C_Name", "C_PID", "C_CORP", "N_Order"};
        String title_ch[] = {"部门名称(*)", "部门所在路径", "所属单位名称(*)", "显示顺序"};
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        HSSFWorkbook workbook = new HSSFWorkbook();
        String sheetName = "人民陪审员基本信息";
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
        sheet.setDefaultColumnWidth(20);
//        sheet.setColumnWidth(4, 22 * 256);
//        sheet.setColumnWidth(8, 22 * 256);
//        sheet.setColumnWidth(14, 22 * 256);
//        sheet.setColumnWidth(title.length - 1, 10 * 256);
        FileOutputStream out = null;
        sheet.setDefaultRowHeight((short) (68 * 20));

        int row_id = 0;
        HSSFRow row_title = workbook.getSheet(sheetName).createRow(row_id++);    //创建第一行
        row_title.setHeightInPoints(25);
        for (int i = 0; i < title.length; i++) {
            HSSFCell cell = row_title.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        HSSFRow row_title_2 = workbook.getSheet(sheetName).createRow(row_id++);    //创建第二行
        row_title_2.setHeightInPoints(25);
        for (int i = 0; i < title_ch.length; i++) {
            HSSFCell cell = row_title_2.createCell(i);
            cell.setCellValue(title_ch[i]);
            cell.setCellStyle(style);
        }

        //循环生成数据
        for (Map m : list) {

            try {
                HSSFRow row_title_n = workbook.getSheet(sheetName).createRow(row_id++);

                for (int i = 0; i < title.length; i++) {
                    HSSFCell cell = row_title_n.createCell(i);
                    Object obj = m.get(title[i]);

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
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        try {
            out = new FileOutputStream(path);
            workbook.write(out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Action("dept")
    /**
     * 所有法院生成一个excel
     */
    public void excelExportAll() {


        List<UmsCourtFull> court_list = umsCourtFullService.selectByListAll();

        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
        String str = df.format(new Date());

        String path = ServletActionContext.getServletContext().getRealPath("/excel") + "/" + str + "/" + "部门列表.xls";
        File file = new File(path);

        if (file.exists()) {
            deleteFile(file);
        }

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        //标题组
        String title[] = {"C_Name", "C_PID", "C_CORP", "N_Order"};
        String title_ch[] = {"部门名称(*)", "部门所在路径", "所属单位名称(*)", "显示顺序"};
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        HSSFWorkbook workbook = new HSSFWorkbook();
        String sheetName = "部门基本信息";
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
        sheet.setDefaultColumnWidth(25);
//        sheet.setColumnWidth(4, 22 * 256);
//        sheet.setColumnWidth(8, 22 * 256);
//        sheet.setColumnWidth(14, 22 * 256);
//        sheet.setColumnWidth(title.length - 1, 10 * 256);
        FileOutputStream out = null;
        sheet.setDefaultRowHeight((short) (68 * 6));

        int row_id = 0;
        HSSFRow row_title = workbook.getSheet(sheetName).createRow(row_id++);    //创建第一行
        row_title.setHeightInPoints(25);
        for (int i = 0; i < title.length; i++) {
            HSSFCell cell = row_title.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        HSSFRow row_title_2 = workbook.getSheet(sheetName).createRow(row_id++);    //创建第二行
        row_title_2.setHeightInPoints(25);
        for (int i = 0; i < title_ch.length; i++) {
            HSSFCell cell = row_title_2.createCell(i);
            cell.setCellValue(title_ch[i]);
            cell.setCellStyle(style);
        }

        for (UmsCourtFull courtFull : court_list) {

            String courtCode = courtFull.getCourtCode();
            String courtName = courtFull.getCourtStdName();
            //部门
            List<Map> list = new ArrayList<>();
            Map<String, Object> Map_level_1 = new HashMap<>(); //一级部门map
            Map<String, Object> Map_level_2 = new HashMap<>();//二级部门map

            UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
            criteria.createCriteria().andCourtCodeEqualTo(courtCode).andLevelEqualTo(1).andIsValidEqualTo(1);//一级部门
            criteria.setOrderByClause("sort_no asc");

            List<UmsDepartment> level_1 = umsDepartmentService.selectByExampleNoAspect(criteria);
            for (UmsDepartment de : level_1) {
                Map<String, Object> innerMap = new HashMap<>();
                innerMap.put("C_Name", de.getDeptName());
                innerMap.put("C_PID", "");
                innerMap.put("C_CORP", courtName);
                innerMap.put("N_Order", de.getSortNo());
                Map_level_1.put(String.format("%04d", de.getDeptNo()), de.getDeptName());
                list.add(innerMap);

            }

            criteria.clear();
            criteria.createCriteria().andCourtCodeEqualTo(courtCode).andLevelEqualTo(2).andIsValidEqualTo(1);//二级级部门
            criteria.setOrderByClause("sort_no asc");

            List<UmsDepartment> level_2 = umsDepartmentService.selectByExampleNoAspect(criteria);
            for (UmsDepartment de : level_2) {
                Map<String, Object> innerMap = new HashMap<>();
                //父Id
                String parent = de.getLevelCode().substring(0, 4);
                String info = "";
                if (Map_level_1.get(parent) != null) {
                    info = (String) Map_level_1.get(parent);
                }
                innerMap.put("C_Name", de.getDeptName());
                innerMap.put("C_PID", info);
                innerMap.put("C_CORP", courtName);
                innerMap.put("N_Order", de.getSortNo());
                Map_level_2.put(String.format("%04d", de.getDeptNo()), de.getDeptName());
                list.add(innerMap);
            }

            criteria.clear();
            criteria.createCriteria().andCourtCodeEqualTo(courtCode).andLevelEqualTo(3).andIsValidEqualTo(1);//二级级部门
            criteria.setOrderByClause("sort_no asc");

            List<UmsDepartment> level_3 = umsDepartmentService.selectByExampleNoAspect(criteria);
            for (UmsDepartment de : level_3) {
                Map<String, Object> innerMap = new HashMap<>();
                //父Id
                String parent_1 = de.getLevelCode().substring(0, 4);
                String parent_2 = de.getLevelCode().substring(4, 8);
                String info = "";
                if (Map_level_1.get(parent_1) != null) {
                    info += Map_level_1.get(parent_1) + "/";
                }
                if (Map_level_2.get(parent_2) != null) {
                    info += (String) Map_level_2.get(parent_2);
                }
                innerMap.put("C_Name", de.getDeptName());
                innerMap.put("C_PID", info);
                innerMap.put("C_CORP", courtName);
                innerMap.put("N_Order", de.getSortNo());
                list.add(innerMap);
            }


            //循环生成数据
            for (Map m : list) {

                try {
                    HSSFRow row_title_n = workbook.getSheet(sheetName).createRow(row_id++);
                    row_title_n.setHeightInPoints(25);

                    for (int i = 0; i < title.length; i++) {
                        HSSFCell cell = row_title_n.createCell(i);
                        Object obj = m.get(title[i]);

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
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }


        try {
            out = new FileOutputStream(path);
            workbook.write(out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Action("user")
    /**
     * 生成用户的excel
     */
    public void exportUser() {

        List<UmsCourtFull> court_list = umsCourtFullService.selectByListAll();

        for (UmsCourtFull courtFull : court_list) {
            exportUserByCourtCode(courtFull.getCourtCode());
        }

    }

    public void exportUserByCourtCode(String courtCode) {


        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
        String str = df.format(new Date());

        UmsCourtFull Court = umsCourtFullService.getCourtByCourtCode(courtCode);
        if (Court == null) {
            return;
        }

        String courtName = Court.getCourtStdName();

        String path = ServletActionContext.getServletContext().getRealPath("/excel") + "/" + str + "/" + courtName + "人员信息.xls";
        File file = new File(path);

        if (file.exists()) {
            deleteFile(file);
        }

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        //人员
        UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
        criteria.createCriteria().andCourtCodeEqualTo(courtCode).andIsValidEqualTo(1).andUserTypeEqualTo(1);
        criteria.setOrderByClause("sort_no asc");
        List<UmsUserInfoView> userInfoViews = umsUserInfoViewService.selectViewByExample(criteria);
        //部门
        Map<String, Object> Map_level_1 = new HashMap<>(); //一级部门map
        Map<String, Object> Map_level_2 = new HashMap<>();//二级部门map
        Map<Integer, Object> deptMap = new HashMap<>(); //用来放部门 和部门的全称，如“信息中心/办公室”或“政治部/警务部/警训处”。

        UmsDepartmentCriteria deptCriteria = new UmsDepartmentCriteria();
        deptCriteria.createCriteria().andCourtCodeEqualTo(courtCode).andLevelEqualTo(1);//一级部门
        deptCriteria.setOrderByClause("sort_no asc");

        List<UmsDepartment> level_1 = umsDepartmentService.selectByExampleNoAspect(deptCriteria);
        for (UmsDepartment de : level_1) {

            Map_level_1.put(String.format("%04d", de.getDeptNo()), de.getDeptName());
            deptMap.put(de.getDeptNo(), de.getDeptName());
        }

        deptCriteria.clear();
        deptCriteria.createCriteria().andCourtCodeEqualTo(courtCode).andLevelEqualTo(2);//二级级部门
        deptCriteria.setOrderByClause("sort_no asc");

        List<UmsDepartment> level_2 = umsDepartmentService.selectByExampleNoAspect(deptCriteria);
        for (UmsDepartment de : level_2) {
            //父Id
            String parent = de.getLevelCode().substring(0, 4);
            String info = "";
            if (Map_level_1.get(parent) != null) {
                info = (String) Map_level_1.get(parent);
            }

            Map_level_2.put(String.format("%04d", de.getDeptNo()), de.getDeptName());
            deptMap.put(de.getDeptNo(), info.equals("") ? de.getDeptName() : info + "/" + de.getDeptName());

        }

        deptCriteria.clear();
        deptCriteria.createCriteria().andCourtCodeEqualTo(courtCode).andLevelEqualTo(3);//二级级部门
        deptCriteria.setOrderByClause("sort_no asc");

        List<UmsDepartment> level_3 = umsDepartmentService.selectByExampleNoAspect(deptCriteria);
        for (UmsDepartment de : level_3) {
            //父Id
            String parent_1 = de.getLevelCode().substring(0, 4);
            String parent_2 = de.getLevelCode().substring(4, 8);
            String info = "";
            if (Map_level_1.get(parent_1) != null) {
                info += Map_level_1.get(parent_1) + "/";
            }
            if (Map_level_2.get(parent_2) != null) {
                info += (String) Map_level_2.get(parent_2);
            }
            deptMap.put(de.getDeptNo(), info.equals("") ? de.getDeptName() : info + "/" + de.getDeptName());
        }


        String title[] = {"C_Name", "C_CORP", "C_DEPT", "C_Sfz", "C_Xzzw", "C_Xzjb", "C_Bzhi", "N_Order", "C_Sex"
                , "C_Mz", "C_Sj", "C_Bgdh", "C_Zzmm", "C_Fgdj"};
        String title_ch[] = {" 用户姓名（*）", "所属单位名称(*)", "所属部门路径(*)", "身份证", "职务"
                , "行政级别", "编制", "显示顺序", "性别", "民族", "手机", "办公电话", "政治面貌", "法官等级"};

        //UmsUserInfoView 和 字段的对应表
        Map<String, Object> matchMap = new HashMap<>();
        matchMap.put("C_Name", "fullname");
//        matchMap.put("C_CORP",courtName);
        matchMap.put("C_DEPT", "department");
        matchMap.put("C_Sfz", "idcard");
        matchMap.put("C_Xzzw", "administrationPositionText");
        matchMap.put("C_Xzjb", "rank");
        matchMap.put("C_Bzhi", "preparationText");
        matchMap.put("N_Order", "sortNo");
        matchMap.put("C_Sex", "genderText");
        matchMap.put("C_Mz", "nationText");
//        matchMap.put("C_Sj", "");
//        matchMap.put("C_Bgdh", "");
        matchMap.put("C_Zzmm", "politicalText");
        matchMap.put("C_Fgdj", "levelText");

        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        HSSFWorkbook workbook = new HSSFWorkbook();
        String sheetName = "人员基本信息";
        HSSFSheet sheet = workbook.createSheet(sheetName);

        //格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //垂直居中
        HSSFDataFormat Dformat = workbook.createDataFormat();
        style.setWrapText(true); //强制换行
        style.setDataFormat(Dformat.getFormat("@"));


        HSSFCellStyle NumberStyle = workbook.createCellStyle();
        NumberStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中
        NumberStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //垂直居中
        NumberStyle.setWrapText(true); //强制换行

        HSSFCellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        dateStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFDataFormat format = workbook.createDataFormat();
        dateStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
        sheet.setDefaultColumnWidth(25);
//        sheet.setColumnWidth(4, 22 * 256);
//        sheet.setColumnWidth(8, 22 * 256);
//        sheet.setColumnWidth(14, 22 * 256);
//        sheet.setColumnWidth(title.length - 1, 10 * 256);
        FileOutputStream out = null;
        sheet.setDefaultRowHeight((short) (68 * 6));

        int row_id = 0;
        HSSFRow row_title = workbook.getSheet(sheetName).createRow(row_id++);    //创建第一行
        row_title.setHeightInPoints(25);
        for (int i = 0; i < title.length; i++) {
            HSSFCell cell = row_title.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        HSSFRow row_title_2 = workbook.getSheet(sheetName).createRow(row_id++);    //创建第二行
        row_title_2.setHeightInPoints(25);
        for (int i = 0; i < title_ch.length; i++) {
            HSSFCell cell = row_title_2.createCell(i);
            cell.setCellValue(title_ch[i]);
            cell.setCellStyle(style);
        }

        for (UmsUserInfoView umsUserInfoView : userInfoViews) {
            try {
                Class<UmsUserInfoView> umsUserInfoViewClass = UmsUserInfoView.class;


                HSSFRow row_title_n = workbook.getSheet(sheetName).createRow(row_id++);
                row_title_n.setHeightInPoints(25);

                for (int i = 0; i < title.length; i++) {

                    HSSFCell cell = row_title_n.createCell(i);
                    //这个是单位名称(法院名称)
                    if (title[i].equals("C_CORP")) {
                        cell.setCellValue(courtName);
                        cell.setCellStyle(style);
                        continue;
                    }

                    Object o = matchMap.get(title[i]);
                    if (o != null && !(o).equals("")) try {
                        Field field = umsUserInfoViewClass.getDeclaredField((String) o);

                        field.setAccessible(true);

                        Object o1 = field.get(umsUserInfoView);

                        //职级中文名有点对不上要转换
                        if (field.getName().equals("rank")) {
                            if (o1 != null) {
                                Object o2 = this.codeMap.get((Integer) o1);
                                cell.setCellValue(o2 == null ? "" : o2.toString());
                                cell.setCellStyle(style);
                            }
                            continue;
                        }
                        //部门要求的格式不一样 全部门路径
                        if (field.getName().equals("department")) {
                            if (o1 instanceof Integer) {
                                Object o2 = deptMap.get(o1);
                                cell.setCellValue(o2 == null ? "" : o2.toString());
                                cell.setCellStyle(style);
                                continue;
                            }
                        }

                        if (o1 instanceof String) {

                            cell.setCellValue((String) o1);
                            cell.setCellStyle(style);

                        } else if (o1 instanceof Integer) {

                            cell.setCellValue((Integer) o1);
                            cell.setCellStyle(NumberStyle);

                        } else if (o1 instanceof Date) {

                            cell.setCellValue((Date) o1);
                            cell.setCellStyle(dateStyle);

                        }
                    } catch (Exception e) {
                        e.getMessage();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        try {
            out = new FileOutputStream(path);
            workbook.write(out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 通过递归调用删除一个文件夹及下面的所有文件
     */
    private void deleteFile(File file) {
        if (file.isFile()) {//表示该文件不是文件夹
            file.delete();
        } else {
            //首先得到当前的路径
            String[] childFilePaths = file.list();
            for (String childFilePath : childFilePaths) {
                File childFile = new File(file.getAbsolutePath() + "\\" + childFilePath);
                deleteFile(childFile);
            }
            file.delete();
        }
    }


    @Action("dept")
    /**
     * 部门生成
     * @param courtCode
     */
    public void excelExportForDept() {


        System.out.println("in ");


        List<Map<String, Object>> list = mapper.getDept();


        System.out.println("out");


        String path = ServletActionContext.getServletContext().getRealPath("/excel") + "/info.xls";
        File file = new File(path);

        if (file.exists()) {
            deleteFile(file);
        }

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }


        //标题组
        String title[] = {"court_name", "dept_name", "dept_st_name", "type"};
        String title_ch[] = {"法院名称", "部门名称", "标准部门名称", "部门类型"};
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        HSSFWorkbook workbook = new HSSFWorkbook();
        String sheetName = "部门信息";
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
        sheet.setDefaultColumnWidth(20);
//        sheet.setColumnWidth(4, 22 * 256);
//        sheet.setColumnWidth(8, 22 * 256);
//        sheet.setColumnWidth(14, 22 * 256);
//        sheet.setColumnWidth(title.length - 1, 10 * 256);
        FileOutputStream out = null;
        sheet.setDefaultRowHeight((short) (68 * 20));

        int row_id = 0;
        HSSFRow row_title = workbook.getSheet(sheetName).createRow(row_id++);    //创建第一行
        row_title.setHeightInPoints(25);
        for (int i = 0; i < title.length; i++) {
            HSSFCell cell = row_title.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        HSSFRow row_title_2 = workbook.getSheet(sheetName).createRow(row_id++);    //创建第二行
        row_title_2.setHeightInPoints(25);
        for (int i = 0; i < title_ch.length; i++) {
            HSSFCell cell = row_title_2.createCell(i);
            cell.setCellValue(title_ch[i]);
            cell.setCellStyle(style);
        }

        //循环生成数据
        for (Map m : list) {

            try {
                HSSFRow row_title_n = workbook.getSheet(sheetName).createRow(row_id++);

                for (int i = 0; i < title.length; i++) {
                    HSSFCell cell = row_title_n.createCell(i);
                    Object obj = m.get(title[i]);

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
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        try {
            out = new FileOutputStream(path);
            workbook.write(out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
