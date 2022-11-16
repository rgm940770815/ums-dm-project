package cn.net.withub.ums.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/2/26.
 */
public class PoiExcelUtil {
    /**
     * POI填充数据到Excel中
     * @param sheet
     * @param list
     * @param fieldMap
     * @param firstIndex
     * @param lastIndex
     * @param <T>
     * @throws Exception
     */
    private static <T> void fillSheet4Map(
            Sheet sheet,
            List<T> list,
            LinkedHashMap<String, String> fieldMap,
            int firstIndex,
            int lastIndex
    ) throws Exception {
        //定义存放英文字段名和中文字段名的数组
        String[] enFields = new String[fieldMap.size()];
        String[] cnFields = new String[fieldMap.size()];
        //填充数组
        int count = 0;
        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            enFields[count] = entry.getKey();
            cnFields[count] = entry.getValue();
            count++;
        }
        int rows=sheet.getLastRowNum();
        //获取表中行数
        if(rows==0){
            //填充表头
            Row row = sheet.createRow(0);
            for (int i = 0; i < cnFields.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(cnFields[i]);
            }
        }
        //填充内容
        int rowNo = 1;
        if(rows!=0){
            rowNo=rows+1;
        }
        for (int index = firstIndex; index <= lastIndex; index++) {
            Row row = sheet.createRow(rowNo);
            //获取单个对象
            Map<String, Object> item = (Map) list.get(index);
            for (int i = 0; i < enFields.length; i++) {
                String objValue = CommonUtil.getMapStringValue(item,enFields[i]);
                String fieldValue = !StringUtils.hasText(objValue)? "--" : objValue;
                Cell cell = row.createCell(i);
                cell.setCellValue(fieldValue);
            }
            rowNo++;
        }
    }

    public static <T> void listMapToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String sheetName, int sheetSize, SXSSFWorkbook wwb, boolean isNewFile, Integer startIndex, OutputStream out) throws ExcelException {
        if (list.size() == 0 || list == null) {
            throw new ExcelException("数据源中没有任何数据");
        }
        if (sheetSize > 65535 || sheetSize < 1) {
            sheetSize = 65535;
        }
        //创建工作簿并发送到OutputStream指定的地方
        try {
            if(wwb==null&&isNewFile){
                wwb = new SXSSFWorkbook(100); // 这里100是在内存中的数量，如果大于此数量时，会写到硬盘，以避免在内存导致内存溢出
            }
            if(startIndex==null){
                //因为2003的Excel一个工作表最多可以有65536条记录，除去列头剩下65535条
                //所以如果记录太多，需要放到多个工作表中，其实就是个分页的过程
                //1.计算一共有多少个工作表
                double sheetNum = Math.ceil(list.size() / new Integer(sheetSize).doubleValue());
                //2.创建相应的工作表，并向其中填充数据
                for (int i = 0; i < sheetNum; i++) {
                    //如果只有一个工作表的情况
                    if (1 == sheetNum) {
                        Sheet sheet = wwb.createSheet(sheetName);
                        fillSheet4Map(sheet, list, fieldMap, 0, list.size() - 1);
                        //有多个工作表的情况
                    } else {
                        Sheet sheet = wwb.createSheet(sheetName + (i + 1));
                        //获取开始索引和结束索引
                        int firstIndex = i * sheetSize;
                        int lastIndex = (i + 1) * sheetSize - 1 > list.size() - 1 ? list.size() - 1 : (i + 1) * sheetSize - 1;
                        //填充工作表
                        fillSheet4Map(sheet, list, fieldMap, firstIndex, lastIndex);
                    }
                }
            }else{
                int endIndex=startIndex+list.size();
                int startSheet=startIndex/sheetSize;
                int endSheet=endIndex%sheetSize==0?endIndex/sheetSize:(endIndex/sheetSize+1);
                int firstIndex =0;
                for(int i=startSheet;i<endSheet;i++){
                    Sheet sheet =null;
                    if(startIndex%sheetSize==0||i>startSheet){
                        if(i==0){
                            sheet = wwb.createSheet(sheetName);
                        }else{
                            sheet = wwb.createSheet(sheetName + (i + 1));
                        }
                        firstIndex=i*sheetSize;
                    }else{
                        sheet = wwb.getSheetAt(i);
                        firstIndex=startIndex;
                    }
                    //获取开始索引和结束索引
                    int lastIndex = (i + 1) * sheetSize - 1 > endIndex - 1 ? endIndex- 1 : (i + 1) * sheetSize - 1;
                    //填充工作表
                    fillSheet4Map(sheet, list, fieldMap, firstIndex-startIndex, lastIndex-startIndex);
                }
            }
            if(isNewFile){
                wwb.write(out);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //如果是ExcelException，则直接抛出
            if (e instanceof ExcelException) {
                throw (ExcelException) e;
                //否则将其它异常包装成ExcelException再抛出
            } else {
                throw new ExcelException("导出Excel失败");
            }
        }
    }
}
