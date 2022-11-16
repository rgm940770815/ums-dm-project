package cn.net.withub.ums.util;

import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import javax.xml.ws.Dispatch;

import cn.net.withub.ums.entity.UmsPhoto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

//
//import cn.net.withub.common.CommonPropertites;
//import cn.net.withub.common.utils.WordToPdfUtils;


public class WsmbUtil {

    private  final String EL_LEFT = "<";
    private  final String EL_RIGHT = ">";

    public  byte[] getBytes(InputStream is) throws Exception {
        byte[] data = null;

        Collection chunks = new ArrayList();
        byte[] buffer = new byte[1024 * 1000];
        int read = -1;
        int size = 0;

        while ((read = is.read(buffer)) != -1) {
            if (read > 0) {
                byte[] chunk = new byte[read];
                System.arraycopy(buffer, 0, chunk, 0, read);
                chunks.add(chunk);
                size += chunk.length;
            }
        }
        if (size > 0) {
            ByteArrayOutputStream bos = null;
            try {
                bos = new ByteArrayOutputStream(size);
                for (Iterator itr = chunks.iterator(); itr.hasNext(); ) {
                    byte[] chunk = (byte[]) itr.next();
                    bos.write(chunk);
                }
                data = bos.toByteArray();
            } finally {
                if (bos != null) {
                    bos.close();
                }
            }
        }
        return data;
    }

    /**
     * 解析Doc格式的文书模板，结果为逗号分隔的字符串
     *
     * @param is
     * @return
     */
    public  String parseWsMbDoc(InputStream is) {
        String temp = "";
        try {
            WordExtractor wordExtractor = new WordExtractor(is);
            //String[] paragraph = wordExtractor.getParagraphText();
            temp = wordExtractor.getText();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getContentSjzd(temp);
    }

    public  String parseWsMbHtml(InputStream input) {
        Document doc = null;
        try {
            doc = Jsoup.parse(input, "GB2312", "http://example.com/");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String content = doc.html();
        return getContentSjzd(content);
    }

    /**
     * 解析Rtf格式的文书模板，结果为逗号分隔的字符串
     *
     * @param is
     * @return
     */

    public  String parseWsmbRtf(InputStream is) {
        String temp = null;
        try {
            DefaultStyledDocument styledDoc = new DefaultStyledDocument();

            new RTFEditorKit().read(is, styledDoc, 0);
            temp = new String(styledDoc.getText(0, styledDoc.getLength())
                    .getBytes("ISO8859_1"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return getContentSjzd(temp);
    }

    public  String getContentSjzd(String str) {
        StringBuffer finalStr = new StringBuffer();
        if (StringUtils.isNotEmpty(str)) {
            Pattern p = Pattern.compile("\\$.*?\\$");
            Matcher m = p.matcher(str);
            while (m.find()) {
                String tempStr = m.group().trim().toString();
                tempStr = tempStr.substring(1);
                tempStr = tempStr.substring(0, tempStr.length() - 1);
                finalStr.append(tempStr + ",");
            }
        }
        if (finalStr.toString().length() > 0) {
            return finalStr.toString().substring(0, finalStr.toString().length() - 1);
        }
        return finalStr.toString();
    }


    public  String mapToString(Map<String, String> groupIdMap) {
        StringBuffer result = new StringBuffer("");
        Iterator it = groupIdMap.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            result.append(key).append(",");
        }
        return result.toString();
    }

    /**
     * 转化字符串数组为字符串
     *
     * @param datas
     * @param data
     */
    private  void fetchItems(String[] datas, StringBuffer data) {
        if (datas != null) {
            for (int i = 0; i < datas.length; i++) {
                if (i != 0 && i % 2 == 1) {
                    data.append(datas[i]).append(",");
                }

            }

        }
    }

    /**
     * 删除以逗号分隔字符串中重复字段，并把其转化为数组
     *
     * @param datas
     * @return
     */
    private  Object[] getDataArr(String datas) {
        Object[] result = null;
        String[] dataArr = datas.split(",");
        Map<String, String> dataMap = new HashMap<String, String>();
        for (int i = 0; i < dataArr.length; i++) {
            if (!dataMap.containsKey(dataArr[i].split("_")[0])) {
                dataMap.put(dataArr[i].split("_")[0], dataArr[i].split("_")[0]);
            }

        }
        result = dataMap.keySet().toArray();

        return result;
    }


    /**
     * 判断字符串str是否包含在字符串datas中
     *
     * @param str
     * @param
     * @return
     */
    private  boolean pbzd(String sysType, String str, String[] strArr) {
        boolean result = false;
        List<String> parentIdList = new ArrayList<String>();
        if (strArr != null && strArr.length > 0 && strArr[3] != null) {// strArr[3]  ：当前日期,法院名称,原告
            String parentGroupId = strArr[0];                            //systemInfo
            String[] dataArr = strArr[3].split(",");
            for (int i = 0; i < dataArr.length; i++) {
                if (dataArr[i].equals(str)) {
                    if (parentGroupId != null && sysType != null && parentGroupId.indexOf(sysType) > -1) {
                        result = true;
                        break;
                    } else {
                        result = true;
                    }

                }
            }
        }

        return result;
    }

    public  void streamToFile(InputStream in, File file) {
        OutputStream out = null;
        ;
        try {
            out = new FileOutputStream(file);
            int readBytes = 0;
            byte[] buffer = new byte[8192];

            while ((readBytes = in.read(buffer, 0, 8192)) != -1) {
                out.write(buffer, 0, readBytes);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 返回Docx中需要替换的特殊字符，没有重复项
    // 推荐传入正则表达式参数"\\$\\{[^{}]+\\}"  
    public List<String> getReplaceElementsInWord(String filePath, String regex) {
        String[] p = filePath.split("\\.");
        if (p.length > 0) {// 判断文件有无扩展名  
            // 比较文件扩展名  
            if (p[p.length - 1].equalsIgnoreCase("doc")) {
                List<String> al = new ArrayList();
                File file = new File(filePath);
                HWPFDocument document = null;
                try (InputStream is = new FileInputStream(file);) {
                    document = new HWPFDocument(is);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Range range = document.getRange();
                String rangeText = range.text();
                CharSequence cs = rangeText.subSequence(0, rangeText.length());
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(cs);
                int startPosition = 0;
                while (matcher.find(startPosition)) {
                    if (!al.contains(matcher.group())) {
                        al.add(matcher.group());
                    }
                    startPosition = matcher.end();
                }
                return al;
            } else if (p[p.length - 1].equalsIgnoreCase("docx")) {
                List<String> al = new ArrayList();
                XWPFDocument document = null;
                try {
                    document = new XWPFDocument(POIXMLDocument.openPackage(filePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 遍历段落  
                Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
                while (itPara.hasNext()) {
                    XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
                    String paragraphString = paragraph.getText();
                    CharSequence cs = paragraphString.subSequence(0, paragraphString.length());
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(cs);
                    int startPosition = 0;
                    while (matcher.find(startPosition)) {
                        if (!al.contains(matcher.group())) {
                            al.add(matcher.group());
                        }
                        startPosition = matcher.end();
                    }
                }
                // 遍历表  
                Iterator<XWPFTable> itTable = document.getTablesIterator();
                while (itTable.hasNext()) {
                    XWPFTable table = (XWPFTable) itTable.next();
                    int rcount = table.getNumberOfRows();
                    for (int i = 0; i < rcount; i++) {
                        XWPFTableRow row = table.getRow(i);
                        List<XWPFTableCell> cells = row.getTableCells();
                        for (XWPFTableCell cell : cells) {
                            String cellText = "";
                            cellText = cell.getText();
                            CharSequence cs = cellText.subSequence(0, cellText.length());
                            Pattern pattern = Pattern.compile(regex);
                            Matcher matcher = pattern.matcher(cs);
                            int startPosition = 0;
                            while (matcher.find(startPosition)) {
                                if (!al.contains(matcher.group())) {
                                    al.add(matcher.group());
                                }
                                startPosition = matcher.end();
                            }
                        }
                    }
                }
                return al;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * html模板生成二维码
     *
     * @return
     * @throws IOException
     */
//    public  void createHtmlEWM(String content,String imagePath) throws IOException{
//    	String imgType = CommonPropertites.ewm_imgType;
//		int size = Integer.parseInt(CommonPropertites.ewm_size);
//		boolean falg = true;
//    	String logoPath = CommonPropertites.ewm_logoPath;
//		//===========================================
//		BufferedImage bufImg = QRCodeUtil.qRCodeCommon(content,
//				logoPath, size, falg);
//		//===========================================
//		File imgFile = new File(imagePath);
//		// 生成二维码QRCode图片
//		ImageIO.write(bufImg, imgType, imgFile);
//    }

    public  class CustomXWPFDocument extends XWPFDocument {
        public CustomXWPFDocument() {
            super();
        }

        public CustomXWPFDocument(OPCPackage opcPackage) throws IOException {
            super(opcPackage);
        }

        public CustomXWPFDocument(InputStream in) throws IOException {
            super(in);
        }
        public void createPicture(int id, int width, int height,XWPFParagraph paragraph) {
            final int EMU = 9525;
            width *= EMU;
            height *= EMU;
            String blipId = getAllPictures().get(id).getPackageRelationship()
                    .getId();

            CTInline inline = paragraph.createRun().getCTR()
                    .addNewDrawing().addNewInline();

            String picXml = ""
                    + "<a:graphic xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">"
                    + "   <a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">"
                    + "      <pic:pic xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">"
                    + "         <pic:nvPicPr>" + "            <pic:cNvPr id=\""
                    + id
                    + "\" name=\"Generated\"/>"
                    + "            <pic:cNvPicPr/>"
                    + "         </pic:nvPicPr>"
                    + "         <pic:blipFill>"
                    + "            <a:blip r:embed=\""
                    + blipId
                    + "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/>"
                    + "            <a:stretch>"
                    + "               <a:fillRect/>"
                    + "            </a:stretch>"
                    + "         </pic:blipFill>"
                    + "         <pic:spPr>"
                    + "            <a:xfrm>"
                    + "               <a:off x=\"0\" y=\"0\"/>"
                    + "               <a:ext cx=\""
                    + width
                    + "\" cy=\""
                    + height
                    + "\"/>"
                    + "            </a:xfrm>"
                    + "            <a:prstGeom prst=\"rect\">"
                    + "               <a:avLst/>"
                    + "            </a:prstGeom>"
                    + "         </pic:spPr>"
                    + "      </pic:pic>"
                    + "   </a:graphicData>" + "</a:graphic>";

            inline.addNewGraphic().addNewGraphicData();
            XmlToken xmlToken = null;
            try {
                xmlToken = XmlToken.Factory.parse(picXml);
            } catch (XmlException xe) {
                xe.printStackTrace();
            }
            inline.set(xmlToken);

            inline.setDistT(0);
            inline.setDistB(0);
            inline.setDistL(0);
            inline.setDistR(0);

            CTPositiveSize2D extent = inline.addNewExtent();
            extent.setCx(width);
            extent.setCy(height);

            CTNonVisualDrawingProps docPr = inline.addNewDocPr();
            docPr.setId(id);
            docPr.setName("图片" + id);
            docPr.setDescr("descr");
        }
    }

    /**
     * @param srcPath  模板路径
     * @param tempPath 文件生成路径
     *                 map 		替换参数键值
     * @return
     */
    // 替换word中需要替换的特殊字符  
    public  boolean replaceAndGenerateWord(String srcPath,
                                                 String tempPath, Map<String, String> map) {


        String[] sp = srcPath.split("\\.");
        String[] dp = tempPath.split("\\.");

        //先处理下字符串中的null
        for (Entry<String, String> entry : map.entrySet()){
            map.put(entry.getKey(),entry.getValue().replace("null",""));
        }
        File f = new File(tempPath);
        try {
            if(f.exists()){
                f.delete();
            }
            f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if ((sp.length > 0) && (dp.length > 0)) {// 判断文件有无扩展名
            // 比较文件扩展名  
            if (sp[sp.length - 1].equalsIgnoreCase("docx")) {
                try (FileOutputStream outStream = new FileOutputStream(f)) {
                    OPCPackage pack = POIXMLDocument.openPackage(srcPath);
//					InputStream is = new FileInputStream(srcPath);
                    CustomXWPFDocument document = new CustomXWPFDocument(pack);
                    // 替换段落中的指定文字  
                    Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
                    while (itPara.hasNext()) {
                        XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
                        List<XWPFRun> runs = paragraph.getRuns();
                        try {
                            for (int i = 0; i < runs.size(); i++) {
                                String oneparaString = runs.get(i).getText(runs.get(i).getTextPosition());
                                for (Entry<String, String> entry : map.entrySet()) {
                                    oneparaString = oneparaString.replace(entry.getKey(), entry.getValue());
                                }
                                runs.get(i).setText(oneparaString, 0);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    // 替换表格中的指定文字
                    Iterator<XWPFTable> itTable = document.getTablesIterator();
                    while (itTable.hasNext()) {
                        XWPFTable table = (XWPFTable) itTable.next();
                        String[] s1 = map.get("<res>").split("[\n]");
                        if (s1.length>16) {
                            XWPFTableRow row1 = table.insertNewTableRow(10);
                            int cellSize = getCellSizeWithMergeNum(table.getRow(0));
                            for (int i = 0; i < cellSize; i++) {
                                row1.addNewTableCell();
                            }
                            mergeCellsHorizontal(table, 10, 1, 14);
                            row1.getCell(1).removeParagraph(0);
                            for (int ii = 16; ii < s1.length; ii++) {
                                XWPFParagraph p1 = row1.getCell(1).addParagraph();//添加新段落
                                XWPFRun run = p1.createRun();
                                // 给入额时间汉子设置红色字体
                                String resj_text = "";
                                if (s1[ii].contains("（入额时间")) {
                                    resj_text = s1[ii].substring(s1[ii].indexOf("（入额时间"));
                                    s1[ii] = s1[ii].replace(resj_text, "");
                                }
                                run.setText(s1[ii]);
                                run.setFontSize(12);
                                if (!"".equals(resj_text)) {
                                    XWPFRun run2 = p1.createRun();
                                    run2.setText(resj_text);
                                    run2.setFontSize(12);
                                    run2.setColor("FF0000");
                                }
                                p1.setAlignment(ParagraphAlignment.LEFT);
                            }
                            row1.setHeight(((s1.length - 16) > 4 ? (s1.length - 16) : 4) * 500);
                        }
                        int rcount = table.getNumberOfRows();
                        for (int i = 0; i < rcount; i++) {
                            XWPFTableRow row = table.getRow(i);
                            List<XWPFTableCell> cells = row.getTableCells();
                            for (XWPFTableCell cell : cells) {/**取得单元格*/
                                if ("<pic>".equals(cell.getText())) {/**判断单元格的内容是否为需要替换的图片内容*/
                                    File pic = new File(map.get("<pic>"));
                                    if (pic.exists()) {
                                        try (FileInputStream is = new FileInputStream(pic)) {
                                            cell.removeParagraph(0);
                                            XWPFParagraph pargraph = cell.addParagraph();
                                            document.addPictureData(is, XWPFDocument.PICTURE_TYPE_PNG);
                                            document.createPicture(document.getAllPictures().size() - 1, 130, 180, pargraph);
                                        } catch (InvalidFormatException | IOException e) {
                                            throw new Exception("写图片失败：" + e.getMessage());
                                        }
                                    } else {
                                        map.put("<pic>", "");
                                    }
                                }
                                List<XWPFParagraph> pars = cell.getParagraphs();
                                for (XWPFParagraph par : pars) {
                                    List<XWPFRun> runs = par.getRuns();
                                    for (XWPFRun run : runs) {
                                        run.removeBreak();
                                    }
                                }
                                for (Entry<String, String> e : map.entrySet()) {
                                    if (cell.getText().contains(e.getKey())) {
                                        String[] s = e.getValue().split("[\n]");//按回车符分割字符
                                        if (s.length==1) {
                                            cell.removeParagraph(0);
                                            XWPFParagraph p = cell.addParagraph();
                                            // 给入额时间汉子设置红色字体
                                            String resj_text = "";
                                            if (s[0].contains("（入额时间")) {
                                                resj_text = s[0].substring(s[0].indexOf("（入额时间"));
                                                s[0] = s[0].replace(resj_text, "");
                                            }
                                            p.createRun().setText(s[0]);
                                            p.setAlignment(ParagraphAlignment.CENTER);
                                            if (!"".equals(resj_text)) {
                                                XWPFRun run2 = p.createRun();
                                                run2.setText(resj_text);
                                                run2.setFontSize(12);
                                                run2.setColor("FF0000");
                                            }
                                            cell.setParagraph(p);
                                        }else{
                                            if (s.length > 16){
                                                int height = s.length * 500;
                                                if (height <= 7000) {
                                                    row.setHeight(height);
                                                } else {
                                                    row.setHeight(7000);
                                                }
                                            }
                                            cell.removeParagraph(0);
                                            XWPFParagraph p = cell.addParagraph();
                                            XWPFRun run = p.createRun();
                                            // 给入额时间汉子设置红色字体
                                            String resj_text = "";
                                            if (s[0].contains("（入额时间")) {
                                                resj_text = s[0].substring(s[0].indexOf("（入额时间"));
                                                s[0] = s[0].replace(resj_text, "");
                                            }
                                            run.setText(s[0]);
                                            if (e.getKey().equals("<res>")) {
                                                run.setFontSize(12);
                                                if (!"".equals(resj_text)) {
                                                    XWPFRun run2 = p.createRun();
                                                    run2.setText(resj_text);
                                                    run2.setFontSize(12);
                                                    run2.setColor("FF0000");
                                                }
                                            }
                                            if (e.getKey().equals("<rewordInfo>")) {
                                                run.setFontSize(11);
                                            }
                                            p.setAlignment(ParagraphAlignment.LEFT);
                                            if (e.getKey().contains("assessInfo")) p.setAlignment(ParagraphAlignment.CENTER);
                                            cell.setParagraph(p);

                                            for (int ii = 1; ii < (s.length > 16 ? 16 : s.length); ii++) {
                                                XWPFParagraph p1 = cell.addParagraph();//添加新段落
                                                XWPFRun run1 = p1.createRun();
                                                // 给入额时间汉子设置红色字体
                                                resj_text = "";
                                                if (s[ii].contains("（入额时间")) {
                                                    resj_text = s[ii].substring(s[ii].indexOf("（入额时间"));
                                                    s[ii] = s[ii].replace(resj_text, "");
                                                }
                                                run1.setText(s[ii]);
                                                if (e.getKey().equals("<res>")) {
                                                    run1.setFontSize(12);
                                                    if (!"".equals(resj_text)) {
                                                        XWPFRun run2 = p1.createRun();
                                                        run2.setText(resj_text);
                                                        run2.setFontSize(12);
                                                        run2.setColor("FF0000");
                                                    }
                                                }
                                                if (e.getKey().equals("<rewordInfo>")) {
                                                    run1.setFontSize(11);
                                                }
                                                p1.setAlignment(ParagraphAlignment.LEFT);
                                                if (e.getKey().contains("assessInfo")) p1.setAlignment(ParagraphAlignment.CENTER);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }


                    document.write(outStream);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }

            } else
            //有bug，处理后的文件无内容
                // doc只能生成doc，如果生成docx会出错
                if (((sp[sp.length - 1].equalsIgnoreCase("doc"))
                        && (dp[dp.length - 1].equalsIgnoreCase("doc")))) {
                    HWPFDocument document = null;
                    try {
//					document = new HWPFDocument(new FileInputStream(srcPath));
                        POIFSFileSystem pfs = new POIFSFileSystem(new FileInputStream(srcPath));
                        document = new HWPFDocument(pfs);
                        Range range = document.getRange();
                        TableIterator it = new TableIterator(range);
                        // 迭代文档中的表格
                        while (it.hasNext()) {
                            Table tb = it.next();
                            // 迭代行，默认从0开始
                            for (int i = 0; i < tb.numRows(); i++) {
                                TableRow tr = tb.getRow(i);
                                // 迭代列，默认从0开始
                                for (int j = 0; j < tr.numCells(); j++) {
                                    TableCell td = tr.getCell(j);// 取得单元格
                                    // 取得单元格的内容
                                    for (int k = 0; k < td.numParagraphs(); k++) {
                                        Paragraph para = td.getParagraph(k);

                                        String s = para.text().trim();
                                        final String old = s;
                                        for (Entry<String, String> key : map.entrySet()) {
                                            if (s.contains(key.getKey())) {
                                                s = s.replace(key.getKey(), key.getValue());
                                            }
                                        }
                                        if (!old.equals(s)) {// 有变化
                                            para.replaceText(old, s);
                                            s = para.text();
                                            System.out.println("old:" + old + "->" + "s:" + s);
                                        }

                                    } // end for
                                } // end for
                            } // end for
                        } // end while

                        for (Entry<String, String> entry : map.entrySet()) {
                            range.replaceText(entry.getKey(), entry.getValue());
                        }

//                        if (mapImage != null) {
//                            OPCPackage opcPackage = POIXMLDocument.openPackage(srcPath);
//                            CustomXWPFDocument doc = new CustomXWPFDocument(opcPackage);
//                            //replaceTextToImage(_document, mapImage, 115, 115);
//                            //document.get
//                        }

                        OutputStream out = new FileOutputStream(tempPath);
                        document.write(out);
                        out.flush();
                        out.close();
                        return true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (((sp[sp.length - 1].equalsIgnoreCase("rtf"))
                        && (dp[dp.length - 1].equalsIgnoreCase("rtf")))) {
                    return true;
                } else {
                    return false;
                }
        }
        {
            return false;
        }
    }
    /**
     * @Description: 统计列数(包括合并的列数)
     */
    public int getCellSizeWithMergeNum(XWPFTableRow row) {
        List<XWPFTableCell> firstRowCellList = row.getTableCells();
        int cellSize = firstRowCellList.size();
        for (XWPFTableCell xwpfTableCell : firstRowCellList) {
            CTTc ctTc = xwpfTableCell.getCTTc();
            if (ctTc.isSetTcPr()) {
                CTTcPr tcPr = ctTc.getTcPr();
                if (tcPr.isSetGridSpan()) {
                    CTDecimalNumber gridSpan = tcPr.getGridSpan();
                    cellSize += gridSpan.getVal().intValue() - 1;
                }
            }
        }
        return cellSize;
    }
    /**
     * @Description: 跨列合并
     */
    public void mergeCellsHorizontal(XWPFTable table, int row, int fromCell,
                                     int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if (cellIndex == fromCell) {
                // The first merged cell is set with RESTART merge value
                getCellCTTcPr(cell).addNewHMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one,are set with CONTINUE
                getCellCTTcPr(cell).addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }
    /**
     *
     * @Description: 得到Cell的CTTcPr,不存在则新建
     */
    public CTTcPr getCellCTTcPr(XWPFTableCell cell) {
        CTTc cttc = cell.getCTTc();
        CTTcPr tcPr = cttc.isSetTcPr() ? cttc.getTcPr() : cttc.addNewTcPr();
        return tcPr;
    }

    public  void copyFileForImage(String copyFrom, String fileName)
            throws IOException {
        if (copyFrom == null)
            return;
        FileInputStream inputStream = new FileInputStream(copyFrom);
        File destFile = new File(fileName);
        destFile.getParentFile().mkdirs();
        FileOutputStream outputStream = new FileOutputStream(destFile);
        byte[] arrayOfByte = new byte[10240];
        int i;
        while ((i = inputStream.read(arrayOfByte, 0, arrayOfByte.length)) > 0)
            outputStream.write(arrayOfByte, 0, i);
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public  void replaceTextToImage(XWPFDocument document,Map mapImage,int width,int height){
        List listRun;
        List<XWPFParagraph> listParagraphs = document.getParagraphs();
        for (int sa = 0; sa < listParagraphs.size(); sa++) {
            Iterator img = mapImage.keySet().iterator();
            while(img.hasNext()){
                String key = img.next().toString();
                String value = mapImage.get(key).toString();


                for(XWPFParagraph xw : listParagraphs){
                    System.out.println(xw.getText());
                    if(xw.getText().equals(key)){
                        break;
                    }
                    sa++;
                }
//                if (listParagraphs.get(sa).getText().contains(key)) {
//                sa = 1;
                if (listParagraphs.get(sa).getText().contains(key)) {
                    listRun = listParagraphs.get(sa).getRuns();
                    for (int p = 0; p < listRun.size(); p++) {
                        if (listRun.get(p).toString().equals(key)) {
                            listParagraphs.get(sa).removeRun(p);//移除占位符
                            //获得当前CTInline
                            CTInline inline = listParagraphs.get(sa).createRun().getCTR().addNewDrawing().addNewInline();
                            try {
                                insertPicture(document,value,inline,width,height);
                            } catch (InvalidFormatException e1) {
                                e1.printStackTrace();
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }

        }
    }


    public  void insertPicture(XWPFDocument document, String filePath, CTInline inline, int width, int height) throws InvalidFormatException, FileNotFoundException {

        for(XWPFPictureData xw : document.getAllPictures()){
            System.out.println(xw.getFileName());
        }


        int id = document.getAllPictures().size() - 1;
        final int EMU = 9525;
        width *= EMU;
        height *= EMU;
        String blipId = document.getAllPictures().get(id).getPackageRelationship()
                .getId();
        String picXml = ""
                + ""
                + "   "
                + "      "
                + "         " + "           "
                + id
                + "\" name=\"Generated\"/>"
                + "            "
                + "         "
                + "         "
                + "           "
                + blipId
                + "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/>"
                + "            "
                + "               "
                + "            "
                + "         "
                + "         "
                + "            "
                + "               "
                + "              "
                + width
                + "\" cy=\""
                + height
                + "\"/>"
                + "            "
                + "            "
                + "               "
                + "            "
                + "         "
                + "      "
                + "   " + "";
        inline.addNewGraphic().addNewGraphicData();
        XmlToken xmlToken = null;
        try {
            xmlToken = XmlToken.Factory.parse(picXml);
        } catch (XmlException xe) {
            xe.printStackTrace();
        }
        inline.set(xmlToken);
        inline.setDistT(0);
        inline.setDistB(0);
        inline.setDistL(0);
        inline.setDistR(0);
        CTPositiveSize2D extent = inline.addNewExtent();
        extent.setCx(width);
        extent.setCy(height);
        CTNonVisualDrawingProps docPr = inline.addNewDocPr();
        docPr.setId(id);
        docPr.setName("IMG_" + id);
        docPr.setDescr("IMG_" + id);
    }

    /**
     * @param wslx 1 格式文书 2 费格式文书
     * @param mbOrWs 1 模板路径  2 文书路径
     * @param fydm 法院代码  
     * @param savePathOrFullPath  1 文件全路径  2 文件 保存路径
     * @return
     */
//    public  String geyFileDatePath(int wslx,int mbOrWs,String fydm,int savePathOrFullPath){
//    	Calendar cl = Calendar.getInstance();
//    	String wslxName = "fgsws";
//    	if(wslx == 1){
//    		wslxName = "gsws";
//    	}
//    	String rootPath = "";
//    	if(mbOrWs == 1){
//    		rootPath = CommonPropertites.sp_wsmb_save_path;
//    	}else{
//    		rootPath = CommonPropertites.sp_wswj_save_path;
//    	}
//    	if(StringUtils.isEmpty(fydm)){
//    		fydm = "all";
//    	}
//    	String fileDataPath = fydm+"/"+wslxName+"/"+cl.get(Calendar.YEAR)+"/"+(cl.get(Calendar.MONTH)+1)+"_"+cl.get(Calendar.DATE)+"/";
//    	if(savePathOrFullPath == 1){
//    		return rootPath+fileDataPath;
//    	}
//    	return fileDataPath;
//    }

//    public  String geyFileDatePathByzw(String fydm,int savePathOrFullPath){
//    	Calendar cl = Calendar.getInstance();
//    	String rootPath = CommonPropertites.sp_spdzw_save_path;
//    	if(StringUtils.isEmpty(fydm)){
//    		fydm = "all";
//    	}
//    	String fileDataPath = fydm+"/zw/"+cl.get(Calendar.YEAR)+"/"+(cl.get(Calendar.MONTH)+1)+"_"+cl.get(Calendar.DATE)+"/";
//    	if(savePathOrFullPath == 1){
//    		return rootPath+fileDataPath;
//    	}
//    	return fileDataPath;
//    }

    /**
     * 以文件流的方式复制文件
     *
     * @param src  文件源目录
     * @param dest 文件目的目录
     * @throws IOException
     */
    public  void copyFile(File src, File dest) throws IOException {
        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(dest);
        int c;
        byte buffer[] = new byte[1024];
        while ((c = in.read(buffer)) != -1) {
            for (int i = 0; i < c; i++)
                out.write(buffer[i]);
        }
        out.flush();
        out.close();
        in.close();
    }
}
