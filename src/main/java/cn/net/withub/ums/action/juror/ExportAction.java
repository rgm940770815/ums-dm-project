package cn.net.withub.ums.action.juror;

import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.*;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.poi.hssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.*;

/**
 * Created by Administrator on 2016/3/21.
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/jurexcel")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class ExportAction {

    @Autowired
    private UmsUserInfoService umsUserInfoService;

    @Autowired
    private UmsPhotoService photoService;

    @Autowired
    private UmsJurorInfoService umsJurorInfoService;

    @Autowired
    private UmsCourtFullService umsCourtFullService;

    @Autowired
    private UmsCodeService umsCodeService;

    private Object data;

    @Action("export")
    public String excelExport() {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();
        data = map;
        //用于代码的查询
        Map<Integer, Map<String, String>> query_map = new HashMap<>();
        //要对应信息的字段
        Map<String, Integer> info_map = new HashMap<>();
        info_map.put("gender", 3);
        info_map.put("nation", 5);
        info_map.put("education", 10001);
        info_map.put("political", 13);

//        List<UmsCourtFull> court_list = umsCourtFullService.selectByListAll();

        UmsCodeCriteria criteria = new UmsCodeCriteria();
        for (String field : info_map.keySet()) {
            Integer i = info_map.get(field);
            criteria.clear();
            criteria.createCriteria().andTypeIdEqualTo(i);
            List<UmsCode> umsCodes = umsCodeService.selectByExample(criteria);
            Map<String, String> map_1 = new HashMap<>();
            for (UmsCode code : umsCodes) {
                map_1.put(code.getId(), code.getCodeName());
            }
            query_map.put(i, map_1);
        }


        generateExcel("M30", "重庆市第三中级人民法院", query_map, info_map);
        generateExcel("M14", "重庆市沙坪坝区人民法院", query_map, info_map);
        generateExcel("M34", "重庆市南川区人民法院", query_map, info_map);
        generateExcel("M57", "重庆市江津区人民法院", query_map, info_map);
        generateExcel("M24", "重庆市梁平县人民法院", query_map, info_map);


//        for (UmsCourtFull courtFull : court_list) {
//            generateExcel(courtFull.getCourtCode(), courtFull.getCourtStdName());
//        }

        long end = System.currentTimeMillis();
        Duration duration = Duration.ofMillis(end - start);
        System.out.println("花费的时间为" + duration.getSeconds());

        return "json";
    }


    private void generateExcel(String courtCode, String excelName, Map<Integer, Map<String, String>> query_map, Map<String, Integer> info_map) {
        if (courtCode == null) {
            return;
        }
        String jurorPath = ServletActionContext.getServletContext().getRealPath("/images");
        File juror_file = new File(jurorPath, "juror.png");
        //要写入excel的信息
        UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
        criteria.createCriteria().andCourtCodeEqualTo(courtCode).andUserTypeEqualTo(3).andIsValidEqualTo(1);
        List<UmsUserInfo> list = umsUserInfoService.selectByExample(criteria);
        if (list.size() == 0) {
            return;
        }

        //文件路径
        String path = ServletActionContext.getServletContext().getRealPath("/excel") + "/" + excelName + "人民陪审员信息采集表.xls";
        File file = new File(path);
        if (file.exists()) {
            deleteFile(file);
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        //标题组
        String title[] = {"序号", "姓名", "性别", "出生日期", "身份证号码", "民族", "学历", "政治面貌", "家庭住址", "固定电话", "手机号码"
                , "电子邮箱", "地区分布", "职业", "工作单位", "工作区域(一级)", "工作区域(二级)", "工作区域(三级)", "工作区域(四级)", "专业特长", "任期开始日期"
                , "是否新增", "届数", "参与案件类型", "个人照片"};
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

        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFDataFormat format_ = workbook.createDataFormat();
        cellStyle.setDataFormat(format_.getFormat("00"));
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        sheet.setDefaultColumnWidth(14);
        sheet.setColumnWidth(4, 22 * 256);
        sheet.setColumnWidth(8, 22 * 256);
        sheet.setColumnWidth(14, 22 * 256);
        sheet.setColumnWidth(title.length - 1, 10 * 256);
        FileOutputStream out = null;
        sheet.setDefaultRowHeight((short) (68 * 20));
        //特殊处理编码的字段
        List<String> spe_field = new ArrayList<>();
        spe_field.add("nation");
        spe_field.add("political");
        List<String> code_field = new ArrayList<>();
        for (String str : info_map.keySet()) {
            code_field.add(str);
        }

        int row_id = 0;
        try {
            //添加表头
            HSSFRow row_title = workbook.getSheet(sheetName).createRow(row_id++);    //创建第一行
            row_title.setHeightInPoints(25);
            for (int i = 0; i < title.length; i++) {
                HSSFCell cell = row_title.createCell(i);
                cell.setCellValue(title[i]);
                cell.setCellStyle(style);
            }
            int sort = 1;
            for (UmsUserInfo umsList : list) {
                int cell_id = 1; //第一列是序号 从一开始
                HSSFRow row = workbook.getSheet(sheetName).createRow(row_id++);
                row.setHeightInPoints(68);
                //序号
                HSSFCell cell = row.createCell(0);
                cell.setCellValue(sort);
                cell.setCellStyle(style);

                //查找相关信息
                UmsJurorInfo umsJurorInfo = umsJurorInfoService.selectById(umsList.getId());
                JurorUser jurorUser = changeUser(umsList, umsJurorInfo);

                //反射写入excel
                Class<JurorUser> jurorUserClass = JurorUser.class;
                Field[] declaredFields = jurorUserClass.getDeclaredFields();
                for (int i = 0; i < declaredFields.length; i++) {
                    Field field = declaredFields[i];
                    field.setAccessible(true);
                    Object obj = field.get(jurorUser);
                    //写入
                    HSSFCell new_cell = row.createCell(cell_id);
                    //特殊处理的编码

                    if (code_field.contains(field.getName())) {
                        if (obj instanceof Integer) {
                            Integer intValue = (Integer) obj;
                            String val = obj.toString();
                            if (0 <= intValue && intValue < 10) {
                                val = String.format("%02d", intValue);
                            }
                            Integer f = info_map.get(field.getName());
                            if (f == null) {
                                new_cell.setCellValue((Integer) obj);
                                new_cell.setCellStyle(style);
                                cell_id++;
                                continue;
                            }
                            Map<String, String> integerStringMap = query_map.get(f);
                            if (integerStringMap == null) {
                                new_cell.setCellValue((Integer) obj);
                                new_cell.setCellStyle(style);
                                cell_id++;
                                continue;
                            }
                            String s = integerStringMap.get(obj);
                            if (s == null) {
                                new_cell.setCellValue((Integer) obj);
                            } else {
                                new_cell.setCellValue(val + "," + s);
                            }
                            new_cell.setCellStyle(style);
                            cell_id++;
                            continue;


                        } else {
                            new_cell.setCellValue((Integer) obj);
                            new_cell.setCellStyle(style);
                            cell_id++;
                            continue;
                        }

                    }

//                    if (spe_field.contains(field.getName())) {
//
//                        new_cell.setCellValue((Integer) obj);
//                        new_cell.setCellStyle(cellStyle);
//                        cell_id++;
//                        continue;
//                    }

                    if (obj instanceof String) {

                        new_cell.setCellValue((String) obj);
                        new_cell.setCellStyle(style);

                    } else if (obj instanceof Integer) {

                        new_cell.setCellValue((Integer) obj);
                        new_cell.setCellStyle(style);

                    } else if (obj instanceof Date) {

                        new_cell.setCellValue((Date) obj);
                        new_cell.setCellStyle(dateStyle);

                    }
                    cell_id++;
                }
                sort++;
                //写入照片
                UmsPhoto umsPhoto = photoService.selectById(umsList.getId());
                byte[] photo = null;
                //要压缩图片质量
                byte[] zipImage = new byte[0];

                if (umsPhoto != null && umsPhoto.getUserId() != null) {

                    photo = umsPhoto.getPhoto();

                    ByteArrayInputStream in = new ByteArrayInputStream(photo);    //将b作为输入流；
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(in);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (in != null) {
                            in.close();
                        }
                    }

                    //压缩文件
                    try {
                        zipImage = zipImage(image, 0.4f);
                    } catch (IOException e) {
                        e.printStackTrace();
                        zipImage = photo;
                    }

                } else {
                    if (!juror_file.exists()) {
                        continue;
                    } else {
                        FileInputStream fileInputStream = null;
                        ByteArrayOutputStream outStream = null;
                        try {
                            fileInputStream = new FileInputStream(juror_file);
                            outStream = new ByteArrayOutputStream();
                            //创建一个Buffer字符串
                            byte[] buffer = new byte[1024];
                            //每次读取的字符串长度，如果为-1，代表全部读取完毕
                            int len = 0;
                            //使用一个输入流从buffer里把数据读取出来
                            while ((len = fileInputStream.read(buffer)) != -1) {
                                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                                outStream.write(buffer, 0, len);
                            }
                            //把outStream里的数据写入内存
                            zipImage = outStream.toByteArray();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            if (fileInputStream != null){
                                fileInputStream.close();
                            }
                            if (outStream != null){
                                outStream.close();
                            }
                        }
                    }
                }



                //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
                HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
                //anchor主要用于设置图片的属性
                HSSFClientAnchor anchor = new HSSFClientAnchor(5, 5, 0, 0, (short) (title.length - 1), sort - 1, (short) (title.length), sort);
                anchor.setAnchorType(3);
                //插入图片
                patriarch.createPicture(anchor, workbook.addPicture(zipImage, HSSFWorkbook.PICTURE_TYPE_JPEG));


            }


            out = new FileOutputStream(path);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 压缩图片质量
     *
     * @param
     * @return
     */
    private byte[] zipImage(BufferedImage image, float quality) throws IOException {
        // 如果图片空，返回空
        if (image == null) {
            return null;
        }
        byte[] bytes = null;
        // 开始开始，写入byte[]
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流
        // 设置压缩参数
        JPEGEncodeParam param = JPEGCodec.getDefaultJPEGEncodeParam(image);
        param.setQuality(quality, false);
        // 设置编码器
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(
                byteArrayOutputStream, param);
        encoder.encode(image);
        bytes = byteArrayOutputStream.toByteArray();

        if (byteArrayOutputStream != null) {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;

    }


    /**
     * 根据UmsUserInfo信息生成JurorUser的信息
     *
     * @return
     */
    private JurorUser changeUser(UmsUserInfo userInfo, UmsJurorInfo jurorInfo) {

        JurorUser jurorUser = new JurorUser();
        if (userInfo == null) {
            return jurorUser;
        }
        jurorUser.setFullName(userInfo.getFullname());
        jurorUser.setGender(userInfo.getGender());
        jurorUser.setBirthday(userInfo.getBirthday());
        jurorUser.setIdcard(userInfo.getIdcard());
        jurorUser.setNation(userInfo.getNation());
        jurorUser.setEducation(userInfo.getCPsXlxw());
        jurorUser.setPolitical(userInfo.getPolitical());
        jurorUser.setLocalAddress(userInfo.getLocalAddress());
        jurorUser.setMachineNumber(userInfo.getMachineNumber());
        jurorUser.setPhoneNumber(userInfo.getPhoneNumber());
        if (jurorInfo != null) {
            jurorUser.setJurorWork(jurorInfo.getJurorWork());
            jurorUser.setCompany(jurorInfo.getCompany());
        }
        jurorUser.setSpecialty(userInfo.getSPsZy());
        jurorUser.setBeginTime(userInfo.getBeginTime());
        return jurorUser;
    }


    /**
     * 通过递归调用删除一个文件夹及下面的所有文件
     *
     * @param file
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


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
