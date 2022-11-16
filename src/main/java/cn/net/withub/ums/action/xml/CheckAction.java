package cn.net.withub.ums.action.xml;

import cn.net.withub.ums.entity.UmsCode;
import cn.net.withub.ums.entity.UmsCodeKey;
import cn.net.withub.ums.entity.XmlTable;
import cn.net.withub.ums.websocket.Test;
import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.words.CellCollection;
import com.aspose.words.Document;
import com.aspose.words.NodeCollection;
import com.aspose.words.NodeType;
import com.aspose.words.Row;
import com.aspose.words.SaveFormat;
import com.aspose.words.Table;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by cuizhibin on 2018/10/11.
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/check")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class CheckAction {
    private Object data;
    private String docPath;
    private String xmlPath;
    static boolean checkTypeAndLength = false;
    static boolean checkRequired = false;
    static boolean checkCodes = true;
    static boolean checkB01 = true;

    @Action("/checkdata")
    public void checkdate() {
        main(null);
    }

    public static void main(String[] args) {

        //读取文档
        List<XmlTable> xmlTableList = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\w+");

        try {
            String path = "E:\\work\\UMS\\文档\\上报最高院文档\\人民法院队伍建设信息化项目--上行数据接口_v4.3.doc";
            Document doc = new Document(path);

            NodeCollection tables = doc.getChildNodes(NodeType.TABLE, true);
            for (Table table : (Iterable<Table>) tables) {
                if (table.getFirstRow().getCells().getCount() < 5) continue;
                String title = table.getPreviousSibling().toString(SaveFormat.TEXT).trim();
                Matcher matcher = pattern.matcher(title);
                if (!matcher.find()) {
                    continue;
                }
                String xmlt = matcher.group(0);
                XmlTable xmlTable = new XmlTable();
                xmlTableList.add(xmlTable);
                xmlTable.setTable(xmlt.trim());
                xmlTable.setTablename(title.replace(xmlt, ""));

                for (Row row : table.getRows()) {
                    CellCollection cells = row.getCells();
                    XmlTable.Column columns = xmlTable.new Column();
                    String field = cells.get(0).toString(SaveFormat.TEXT).trim();
                    Matcher matcher1 = pattern.matcher(field);
                    if (!matcher1.find()) continue;

                    columns.setField(field);
                    String fieldName = cells.get(1).toString(SaveFormat.TEXT).trim();
                    columns.setFieldName(fieldName);
                    columns.setRequired(fieldName.contains("*"));
                    String type = cells.get(2).toString(SaveFormat.TEXT).trim();
                    String[] typesplit = type.contains("（") ? type.split("（") : type.split("\\(");
                    if (!typesplit[0].equalsIgnoreCase("varchar2") && !typesplit[0].equalsIgnoreCase("number") && !typesplit[0].equalsIgnoreCase("date")) {
                        System.err.println("文档表格数据错误！类型不存在，title：" + title);
                        return;
                    }
                    columns.setType(typesplit[0]);
                    columns.setTypeLength(typesplit.length == 2 ? Integer.parseInt(typesplit[1].replace(")", "").replace("）", "")) : 0);
                    columns.setHasCode(cells.get(3).toString(SaveFormat.TEXT).trim().contains("是"));
                    columns.setCodeTable(cells.get(4).toString(SaveFormat.TEXT).trim());
                    xmlTable.getColumnsList().add(columns);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //读取编码
        List<UmsCode> codeList = new ArrayList<>();
        try (FileInputStream fstream = new FileInputStream("E:\\work\\UMS\\文档\\上报最高院文档\\人民法院队伍建设信息化项目--上行业务字典表_v4.3.xlsx")) {
            Workbook workbook = new Workbook(fstream);

            Worksheet worksheet = workbook.getWorksheets().get("字典表");
            Cells cells = worksheet.getCells();
            com.aspose.cells.RowCollection rows = cells.getRows();
            for (com.aspose.cells.Row row : (Iterable<com.aspose.cells.Row>) rows) {
                String row1text = row.get(1).getStringValue();
                Matcher matcher = pattern.matcher(row1text);
                if (!matcher.find()) {
                    continue;
                }
                String row0text = row.get(0).getStringValue();
                UmsCode umsCode = new UmsCode();
                if (row0text.equalsIgnoreCase("GSOT")) {
                    umsCode.setCourtCode(row0text);
                    umsCode.setId(row1text);
                } else {
                    umsCode.setCourtCode(row1text);
                    umsCode.setId(row0text);
                }
                umsCode.setCodeName(row.get(2).getStringValue());
                codeList.add(umsCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, List<UmsCode>> codeMap = codeList.stream().collect(Collectors.groupingBy(UmsCode::getCourtCode));
        Map<String, List<XmlTable>> tableMap = xmlTableList.stream().collect(Collectors.groupingBy(XmlTable::getTable));

        //开始检查
        Collection<File> files = FileUtils.listFiles(new File("E:\\work\\UMS\\ums\\target\\ums\\generater\\QL_CQGY_20181025\\QL_CQGY_20181025"), new String[]{"xml"}, true);
        List<String> list = new ArrayList<>();
        List<String> error = new ArrayList<>();
        for (File file : files) {
            try {
                String table = file.getParentFile().getName();
                XmlTable xmlTable = tableMap.get(table).get(0);
                String content = FileUtils.readFileToString(file);
                org.dom4j.Document document = DocumentHelper.parseText(content);
                List<Element> fileColumns = document.selectNodes("/dataset/table[@name='" + table + "']/column");
                //检查上报表字段是否都有，类型是否正确
                List<XmlTable.Column> columnsList = xmlTable.getColumnsList();
                List<String> fields = columnsList.stream().map(XmlTable.Column::getField).collect(Collectors.toList());
                for (XmlTable.Column column : columnsList) {
                    int has = 0;
                    for (int i = 0; i < fileColumns.size(); i++) {
                        Element fileColumnEle = fileColumns.get(i);
                        if (column.getField().equalsIgnoreCase(fileColumnEle.getText())) {
                            column.setIndex(i + 1);
                            has = 1;
                            if (!column.getType().equalsIgnoreCase(fileColumnEle.attributeValue("type"))) {
                                break;
                            }
                            has = 2;
                            break;
                        }
                    }
                    if (has == 0) {
                        print(file.getName() + "  " + xmlTable.getTable() + " ，field: " + column.getField() + " not exist");
                    } else if (has == 1) {
                        print(file.getName() + "  " + xmlTable.getTable() + " ，field: " + column.getField() + " type error");
                    }
                }
                for (Element fileColumnEle : fileColumns) {
                    if (!fields.contains(fileColumnEle.getText())) {
                        String msg = xmlTable.getTable() + " ，field: " + fileColumnEle.getText();
                        if (!list.contains(msg)) list.add(msg);
                    }
                }
                //检查上报表值类型、长度、必填是否符合，有编码表的值是否在编码表中
                for (XmlTable.Column column : columnsList) {
                    List<Element> idEleList = document.selectNodes("/dataset/table[@name='" + table + "']/row/value[1]");
                    List<String> idList = idEleList.stream().map(Element::getText).collect(Collectors.toList());
                    List<Element> rowList = document.selectNodes("/dataset/table[@name='" + table + "']/row/value[" + column.getIndex() + "]");
                    for (int i = 0; i < rowList.size(); i++) {
                        Element element = rowList.get(i);
                        int has = 0;
                        String text = element.getText().replace(" ", "");
                        //检查必填
                        if (checkRequired) {
                            if (!StringUtils.hasText(text) && column.isRequired()) {
                                print(file.getName() + "  " + xmlTable.getTable() + " ，id: " + idList.get(i) + " ，field: " + column.getField() + " must not be null ");
                                continue;
                            }
                        }
                        String type = column.getType();
                        int typeLength = column.getTypeLength();
                        //检查类型，长度
                        if (checkTypeAndLength) {
                            if (type.contains("VARCHAR2") && text.length() > typeLength) {
                                print(file.getName() + "  " + xmlTable.getTable() + " ，id: " + idList.get(i) + " ，field: " + column.getField() + " ，value:" + text + " too long ");
                                continue;
                            } else if (type.contains("NUMBER") && text.length() > (typeLength == 0 ? -1 : typeLength) && StringUtils.hasText(text) && !text.matches("\\d*")) {
                                print(file.getName() + "  " + xmlTable.getTable() + " ，id: " + idList.get(i) + " ，field: " + column.getField() + " ，value:" + text + " must be number ");
                                continue;
                            } else if (type.contains("DATE") && StringUtils.hasText(text) && !text.matches("\\d{4}(\\\\-|\\\\/|.)\\d{1,2}\\1\\d{1,2}")) {
                                print(file.getName() + "  " + xmlTable.getTable() + " ，id: " + idList.get(i) + " ，field: " + column.getField() + " ，value:" + text + " can not convert to date");
                                continue;
                            }
                        }
                        //如果有编码表，校验编码是否存在
                        if (checkCodes) {
                            if (column.isHasCode() && StringUtils.hasText(text)) {
                                List<UmsCode> umsCodes = codeMap.get(column.getCodeTable());
                                if (umsCodes != null) {
                                    if (!umsCodes.stream().map(UmsCodeKey::getId).collect(Collectors.toList()).contains(text)) {
                                        if (!error.contains(xmlTable.getTable() + "_" + column.getField())) {
                                            error.add(xmlTable.getTable() + "_" + column.getField());
                                            print(file.getName() + "  " + xmlTable.getTable() + " ，id: " + idList.get(i) + " ，field: " + column.getField() + " ，value:" + text + " code not exist ");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                print(file.getAbsolutePath());
                e.printStackTrace();
            }

        }

        //生成树，手动检测分别以排序或id,parentid生成树判断是否正确
        if (checkB01) {
            List<Map<String, String>> mapList = new ArrayList<>();
            for (File file : files) {
                if (!file.getName().equalsIgnoreCase("b010.xml")) {
                    continue;
                }
                try {
                    String table = file.getParentFile().getName();
                    XmlTable xmlTable = tableMap.get(table).get(0);

                    List<XmlTable.Column> columnsList = xmlTable.getColumnsList();
                    String content = FileUtils.readFileToString(file);
                    org.dom4j.Document document = DocumentHelper.parseText(content);
                    Map<Integer, List<XmlTable.Column>> collect = columnsList.stream().collect(Collectors.groupingBy(XmlTable.Column::getIndex));
                    List<Element> rowList = document.selectNodes("/dataset/table[@name='" + table + "']/row");
                    for (Element element : rowList) {
                        List<Element> list1 = element.selectNodes("value");
                        Map<String, String> treeMap = new HashMap<>();
                        for (int i1 = 0; i1 < list1.size(); i1++) {
                            String field = collect.get(i1 + 1).get(0).getField();
                            treeMap.put(field, list1.get(i1).getText().replace(" ", ""));
                        }
                        mapList.add(treeMap);
                    }
                } catch (Exception e) {
                    print(file.getAbsolutePath());
                    e.printStackTrace();
                }
            }
            List<Map<String, Object>> maps = generTree1(mapList, "30401");
            List<Map<String, Object>> maps1 = generTree2(mapList, "2e1f119b-4d83-4a31-ae46-c28b77891b0e");
            //System.out.println(maps);
            //System.out.println(maps1);
        }

        print("多余字段");
        for (String s : list) {
            print(s);
        }
    }

    public static List<Map<String, Object>> generTree1(List<Map<String, String>> list, String pindex) {
        List<Map<String, Object>> list1 = new ArrayList<>();
        for (Map<String, String> map : list) {
            Map<String, Object> childMap = new HashMap<>();
            childMap.putAll(map);
            for (String s : map.keySet()) {
                if (s.equalsIgnoreCase("PINDEX")) {
                    String parentPindex = map.get(s).substring(0, map.get(s).length() - 2);
                    if (parentPindex.length() == pindex.length() && parentPindex.contains(pindex)) {
                        List<Map<String, Object>> map1 = generTree1(list, map.get(s));
                        childMap.put("child", map1);
                        list1.add(childMap);
                    }
                    break;
                }
            }
        }
        return list1;
    }

    public static List<Map<String, Object>> generTree2(List<Map<String, String>> list, String parentid) {
        List<Map<String, Object>> list1 = new ArrayList<>();
        for (Map<String, String> map : list) {
            Map<String, Object> childMap = new HashMap<>();
            childMap.putAll(map);
            for (String s : map.keySet()) {
                if (s.equalsIgnoreCase("B0144B")) {
                    if (map.get(s).equalsIgnoreCase(parentid)) {
                        List<Map<String, Object>> map1 = generTree2(list, map.get("B00"));
                        childMap.put("child", map1);
                        list1.add(childMap);
                    }
                    break;
                }
            }
        }
        return list1;
    }

    public static void print(String msg) {
        System.out.println(msg);
        Enumeration<String> keys = Test.linkMap.keys();
        while (keys.hasMoreElements()) {
            String s = keys.nextElement();
            try {
                Test.linkMap.get(s).getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
