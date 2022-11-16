package cn.net.withub.ums.util;


import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


public class WordManager {


    public  void main(String[] args) {
//
//        WordManager Test = new WordManager();
//        try {
//            Test.openDocument("D:\\test.docx");
////			Test.replaceAllImage("$YZ$", "D:\\untitled.bmp");
////			//Test.replaceAllText("$YZ$", "ddd");
////			Test.moveStart();
//			File file=new File("d:\\aaa.doc");
//			if(file.exists()){
//				file.delete();
//			}
//            Map map = new HashMap<>();
//            map.put("${fullname}","aaa");
//            map.put("${genderText}","bbb");
//            map.put("${birthday}","ccc\nbbb");
//            map.put("${nationText}","47岁");
//            String oldText;
//            String newValue;
//            Iterator keys = map.keySet().iterator();
//            while (keys.hasNext()) {
//                oldText = (String) keys.next();
//                newValue = (String)map.get(oldText);
//                replaceAllText(oldText, newValue);
//            }
////            Test.replaceImageQz("<pic>", "D:\\ums\\target\\ums-1.0-SNAPSHOT\\word\\05b46959-9b90-4ba8-8651-afa28a46e266.png", "");
//            Test.save("d:\\aaa.doc");
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        } finally {
//            Test.close();
//        }
    }

        //WordManager.wordToHtml("d:\\input.doc", "d:\\output.htm");
        /*
		WordManager Test=new WordManager();
		Test.openDocument("E:/YYQD.doc");
		for (int i = 0; i < 4; i++) {
			Test.addLastTableRow(1);
			for (int j = 0; j <= 4; j++) {
				Test.putTxtToCellCenter(1,5+j,1,""+(j+1));
				Test.putTxtToCellCenter(1,5+j,2,"fqwffwefw23");
//				Test.putTxtToCellCenter(1,3+j,3,"wwwe");
//				Test.putTxtToCellCenter(1,3+j,1,"50000000000");
			}
		}
		File file=new File("d:/addrows.doc");
		if(file.exists()){
			file.delete();
		}
		Test.save("d:/addrows.doc");
		Test.close();	*/

//		WordManager Test=new WordManager();
//		Test.openDocument("d:/YYQD.doc");
//		
//		for (int i = 0; i < 5-1; i++) {
//			Test.addLastTableRow(1);
//		}
//		Test.moveStart();
//		Test.replaceAllText("$XZTZSWH$","高法第2013年80号");
//		Test.moveStart();
//		Test.replaceAllText("$YHMC$","重庆银行");
//		Test.moveStart();
//		Test.replaceAllText("$SQFSL$","10");
//		Test.moveStart();
//		Test.replaceAllText("$SQSL$","3");
//		Test.moveStart();
//		Test.replaceAllText("$SQZRRSL$","2");
//		Test.moveStart();
//		Test.replaceAllText("$SQFRSL$","1");
//		Test.moveStart();
//		Test.replaceAllText(" $CURRENTDATE$","2013-11-11");
//		Test.moveStart();
        //Test.createTable("$QSSJCJDTABLE$",16,9);

//		File file=new File("d:/addrows.doc");
//		if(file.exists()){
//			file.delete();
//		}
//		Test.save("d:/addrows.doc");
//		Test.close();


    // word文档
    private  Dispatch doc;
    // word运行程序对象
    private  ActiveXComponent word;
    // 所有word文档集合
    private  Dispatch documents;
    // 选定的范围或插入点
    private  Dispatch selection;
    private  boolean saveOnExit = true;

    public WordManager() {
        //ComThread.InitSTA();
        if (word == null) {
            try {
                new ActiveXComponent("Word.Application");
            } catch (Exception e) {
                e.printStackTrace();
            }
            word = new ActiveXComponent("Word.Application");
            word.setProperty("Visible", new Variant(false));
        }
        if (documents == null)
            documents = word.getProperty("Documents").toDispatch();
    }
    /** */
    /**
     * 设置退出时参数 * * @param saveOnExit * boolean true-退出时保存文件，false-退出时不保存文件
     */
    public void setSaveOnExit(boolean saveOnExit) {
        this.saveOnExit = saveOnExit;
    }
    /** */
    /**
     * 创建一个新的word文档 *
     */
    public void createNewDocument() {
        doc = Dispatch.call(documents, "Add").toDispatch();
        selection = Dispatch.get(word, "Selection").toDispatch();
    }
    /** */
    /**
     * 打开一个已存在的文档 * * @param docPath
     */
    public  void openDocument(String docPath) {
        try {
            closeDocument();
            doc = Dispatch.call(documents, "Open", docPath).toDispatch();
            selection = Dispatch.get(word, "Selection").toDispatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** */
    /**
     * 把选定的内容或插入点向上移动 * * @param pos * 移动的距离
     */
    public void moveUp(int pos) {
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        for (int i = 0; i < pos; i++)
            Dispatch.call(selection, "MoveUp");
    }
    /** */
    /**
     * 把选定的内容或者插入点向下移动 * * @param pos * 移动的距离
     */
    public void moveDown(int pos) {
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        for (int i = 0; i < pos; i++)
            Dispatch.call(selection, "MoveDown");
    }
    /** */
    /**
     * 把选定的内容或者插入点向左移动 * * @param pos * 移动的距离
     */
    public void moveLeft(int pos) {
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        for (int i = 0; i < pos; i++) {
            Dispatch.call(selection, "MoveLeft");
        }
    }
    /** */
    /**
     * 把选定的内容或者插入点向右移动 * * @param pos * 移动的距离
     */
    public void moveRight(int pos) {
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        for (int i = 0; i < pos; i++)
            Dispatch.call(selection, "MoveRight");
    }
    /** */
    /**
     * 把插入点移动到文件首位置 *
     */
    public void moveStart() {
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        Dispatch.call(selection, "HomeKey", new Variant(6));
    }
    /** */
    /**
     * 从选定内容或插入点开始查找文本 * * @param toFindText * 要查找的文本 * @return boolean true-查找到并选中该文本，false-未查找到文本
     */
    public  boolean find(String toFindText) {
        if (toFindText == null || toFindText.equals(""))
            return false;
        // 从selection所在位置开始查询
        Dispatch find = word.call(selection, "Find").toDispatch();
        // 设置要查找的内容
        Dispatch.put(find, "Text", toFindText);
        // 向前查找
        Dispatch.put(find, "Forward", "True");
        // 设置格式
        Dispatch.put(find, "Format", "True");
        // 大小写匹配
        Dispatch.put(find, "MatchCase", "True");
        // 全字匹配
        Dispatch.put(find, "MatchWholeWord", "True");
        // 查找并选中
        return Dispatch.call(find, "Execute").getBoolean();
    }
    /** */
    /**
     * 把选定选定内容设定为替换文本 * * @param toFindText * 查找字符串 * @param newText * 要替换的内容 * @return
     */
    public  boolean replaceText(String toFindText, String newText) {
        if (! find(toFindText))
            return false;
        Dispatch.put(selection, "Text", newText);
        return true;
    } /** */
    /**
     * 全局替换文本 * * @param toFindText * 查找字符串 * @param newText * 要替换的内容
     */
    public  void replaceAllText(String toFindText, String newText) {
        while (find(toFindText)) {
            Dispatch.put(selection, "Text", newText);
            Dispatch.call(selection, "MoveRight");
            Dispatch.call(selection, "TypeParagraph"); //换行
        }
    }
    /** */
    /**
     * 在当前插入点插入字符串 * * @param newText * 要插入的新字符串
     */
    public void insertText(String newText) {
        Dispatch.put(selection, "Text", newText);
    }
    /** */
    /**
     * * @param toFindText * 要查找的字符串 * @param imagePath * 图片路径 * @return
     */
    public boolean replaceImage(String toFindText, String imagePath) {
        if (!find(toFindText))
            return false;
        Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), "AddPicture", imagePath);
        return true;
    }

    public  boolean replaceImageQz(String toFindText, String imagePath, String fydm) {
        if (!find(toFindText))
            return false;
        Dispatch picture = Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), "AddPicture", imagePath).toDispatch();
        Dispatch.call(picture, "Select");
        if (fydm.endsWith("00")) {//高院
            Dispatch.put(picture, "Width", new Variant(140));
            Dispatch.put(picture, "Height", new Variant(141));
        } else if (fydm.endsWith("0")) {//中院
            Dispatch.put(picture, "Width", new Variant(135));
            Dispatch.put(picture, "Height", new Variant(136));
        } else {//基层法院
            Dispatch.put(picture, "Width", new Variant(105));
            Dispatch.put(picture, "Height", new Variant(125));
        }
        Dispatch ShapeRange = Dispatch.call(picture, "ConvertToShape").toDispatch(); // 取得图片区域
        Dispatch WrapFormat = Dispatch.get(ShapeRange, "WrapFormat").toDispatch(); // 取得图片的格式对象
        Dispatch.put(WrapFormat, "Type", 5); // 设置环绕格式（0 - 7）下面是参数说明

//    	wdWrapInline 7 将形状嵌入到文字中。 
//    	wdWrapNone 3 将形状放在文字前面。请参阅 wdWrapFront 。
//    	wdWrapSquare 0 使文字环绕形状。行在形状的另一侧延续。
//    	wdWrapThrough 2 使文字环绕形状。
//    	wdWrapTight 1 使文字紧密地环绕形状。
//    	wdWrapTopBottom 4 将文字放在形状的上方和下方。
//    	wdWrapBehind 5 将形状放在文字后面。
//    	wdWrapFront 6 将形状放在文字前面。
        return true;
    }
    /** */
    /**
     * 全局替换图片 * * @param toFindText * 查找字符串 * @param imagePath * 图片路径
     */
    public void replaceAllImage(String toFindText, String imagePath) {
        while (find(toFindText)) {
            //Dispatch picture =Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), "AddPicture", imagePath);
            Dispatch picture = Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), "AddPicture", imagePath).toDispatch(); // 添加图片
            Dispatch.call(picture, "Select"); // 选中图片
            Dispatch.call(selection, "MoveRight");
            Dispatch ShapeRange = Dispatch.call(picture, "ConvertToShape").toDispatch(); // 取得图片区域
            Dispatch WrapFormat = Dispatch.get(ShapeRange, "WrapFormat").toDispatch(); // 取得图片的格式对象
            Dispatch.put(WrapFormat, "Type", 5); // 设置环绕格式（0 - 7）下面是参数说明 5浮于文字下方
            // Dispatch.put(picture, "Width", new Variant(189)); // 图片的宽度
            // Dispatch.put(picture, "Height", new Variant(189)); // 图片的高度
            // wdWrapInline 7 将形状嵌入到文字中。
            // wdWrapNone 3 将形状放在文字前面。请参阅 wdWrapFront 。
            // wdWrapSquare 0 使文字环绕形状。行在形状的另一侧延续。
            // wdWrapThrough 2 使文字环绕形状。
            // wdWrapTight 1 使文字紧密地环绕形状。
            // wdWrapTopBottom 4 将文字放在形状的上方和下方。
            // wdWrapBehind 5 将形状放在文字后面。
            // wdWrapFront 6 将形状放在文字前面。
        }
    }
    /** */
    /**
     * 在当前插入点插入图片 * * @param imagePath * 图片路径
     */
    public void insertImage(String imagePath) {
        Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), "AddPicture", imagePath);
    }
    /** */
    /**
     * 合并单元格 * * @param tableIndex * @param fstCellRowIdx * @param fstCellColIdx * @param secCellRowIdx * @param secCellColIdx
     */
    public void mergeCell(int tableIndex, int fstCellRowIdx, int fstCellColIdx, int secCellRowIdx, int secCellColIdx) {
        // 所有表格
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        Dispatch fstCell = Dispatch.call(table, "Cell", new Variant(fstCellRowIdx), new Variant(fstCellColIdx)).toDispatch();
        Dispatch secCell = Dispatch.call(table, "Cell", new Variant(secCellRowIdx), new Variant(secCellColIdx)).toDispatch();
        Dispatch.call(fstCell, "Merge", secCell);
    }
    /** */
    /**
     * 在指定的单元格里填写数据 * * @param tableIndex * @param cellRowIdx * @param cellColIdx * @param txt
     */
    public void putTxtToCell(int tableIndex, int cellRowIdx, int cellColIdx, String txt) {
        // 所有表格
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx), new Variant(cellColIdx)).toDispatch();
        Dispatch.call(cell, "Select");
        Dispatch.put(selection, "Text", txt);
    }
    /** */
    /**
     * 在指定的单元格里填写数据 * * @param tableIndex * @param cellRowIdx * @param cellColIdx * @param txt
     */
    public void putTxtToCellCenter(int tableIndex, int cellRowIdx, int cellColIdx, String txt) {
        // 所有表格
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx), new Variant(cellColIdx)).toDispatch();
        Dispatch.call(cell, "Select");
        Dispatch alignment = Dispatch.get(selection, "ParagraphFormat").toDispatch();
        Dispatch.put(alignment, "Alignment", "3");
        Dispatch.put(selection, "Text", txt);
    }
    /** */
    /**
     * 在当前文档拷贝剪贴板数据 * * @param pos
     */
    public void pasteExcelSheet(String pos) {
        moveStart();
        if (this.find(pos)) {
            Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
            Dispatch.call(textRange, "Paste");
        }
    }
    /** */
    /**
     * 在当前文档指定的位置拷贝表格 * * @param pos * 当前文档指定的位置 * @param tableIndex * 被拷贝的表格在word文档中所处的位置
     */
    public void copyTable(String pos, int tableIndex) {
        // 所有表格
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        Dispatch range = Dispatch.get(table, "Range").toDispatch();
        Dispatch.call(range, "Copy");
        if (this.find(pos)) {
            Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
            Dispatch.call(textRange, "Paste");
        }
    }
    /** */
    /**
     * 在当前文档指定的位置拷贝来自另一个文档中的表格 * * @param anotherDocPath * 另一个文档的磁盘路径 * @param tableIndex * 被拷贝的表格在另一格文档中的位置 * @param pos * 当前文档指定的位置
     */
    public void copyTableFromAnotherDoc(String anotherDocPath, int tableIndex, String pos) {
        Dispatch doc2 = null;
        try {
            doc2 = Dispatch.call(documents, "Open", anotherDocPath).toDispatch();
            // 所有表格
            Dispatch tables = Dispatch.get(doc2, "Tables").toDispatch();
            // 要填充的表格
            Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
            Dispatch range = Dispatch.get(table, "Range").toDispatch();
            Dispatch.call(range, "Copy");
            if (this.find(pos)) {
                Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
                Dispatch.call(textRange, "Paste");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (doc2 != null) {
                Dispatch.call(doc2, "Close", new Variant(saveOnExit));
                doc2 = null;
            }
        }
    }
    /** */
    /**
     * 在当前文档指定的位置拷贝来自另一个文档中的图片 * * @param anotherDocPath * 另一个文档的磁盘路径 * @param shapeIndex * 被拷贝的图片在另一格文档中的位置 * @param pos * 当前文档指定的位置
     */
    public void copyImageFromAnotherDoc(String anotherDocPath, int shapeIndex, String pos) {
        Dispatch doc2 = null;
        try {
            doc2 = Dispatch.call(documents, "Open", anotherDocPath).toDispatch();
            Dispatch shapes = Dispatch.get(doc2, "InLineShapes").toDispatch();
            Dispatch shape = Dispatch.call(shapes, "Item", new Variant(shapeIndex)).toDispatch();
            Dispatch imageRange = Dispatch.get(shape, "Range").toDispatch();
            Dispatch.call(imageRange, "Copy");
            if (this.find(pos)) {
                Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
                Dispatch.call(textRange, "Paste");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (doc2 != null) {
                Dispatch.call(doc2, "Close", new Variant(saveOnExit));
                doc2 = null;
            }
        }
    }

    /** */
    /**
     * 创建表格 * * @param pos * 位置 * @param cols * 列数 * @param rows * 行数
     */
    public void createTable(String pos, int numCols, int numRows) {
        if (find(pos)) {
            Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
            Dispatch range = Dispatch.get(selection, "Range").toDispatch();
            Dispatch newTable = Dispatch.call(tables, "Add", range, new Variant(numRows), new Variant(numCols)).toDispatch();
            for (int b = 1; b <= 6; b++) {
                Dispatch oBorders = Dispatch.call(newTable, "Borders", 0 - b).toDispatch();
                Dispatch.put(oBorders, "LineStyle", new Variant(1));
            }
            Dispatch.call(selection, "MoveRight");
        }
    }
    /** */
    /**
     * 在指定行前面增加行 * * @param tableIndex * word文件中的第N张表(从1开始) * @param rowIndex * 指定行的序号(从1开始)
     */
    public void addTableRow(int tableIndex, int rowIndex) {
        // 所有表格
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        // 表格的所有行
        Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
        Dispatch row = Dispatch.call(rows, "Item", new Variant(rowIndex)).toDispatch();
        Dispatch.call(rows, "Add", new Variant(row));
    }


    /** */
    /**
     * 在第1行前增加一行 * * @param tableIndex * word文档中的第N张表(从1开始)
     */
    public void addFirstTableRow(int tableIndex) {
        // 所有表格
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        // 表格的所有行
        Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
        Dispatch row = Dispatch.get(rows, "First").toDispatch();
        Dispatch.call(rows, "Add", new Variant(row));
    }
    /** */
    /**
     * 在最后1行前增加一行 * * @param tableIndex * word文档中的第N张表(从1开始)
     */
    public void addLastTableRow(int tableIndex) {
        // 所有表格
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        // 表格的所有行
        Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
        Dispatch row = Dispatch.get(rows, "Last").toDispatch();
        Dispatch.call(rows, "Add", new Variant(row));
    }
    /** */
    /**
     * 增加一行 * * @param tableIndex * word文档中的第N张表(从1开始)
     */
    public void addRow(int tableIndex) {
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        // 表格的所有行
        Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
        Dispatch.call(rows, "Add");
    }
    /** */
    /**
     * 增加一列 * * @param tableIndex * word文档中的第N张表(从1开始)
     */
    public void addCol(int tableIndex) {
        // 所有表格
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        // 表格的所有行
        Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
        Dispatch.call(cols, "Add").toDispatch();
        Dispatch.call(cols, "AutoFit");
    }
    /** */
    /**
     * 在指定列前面增加表格的列 * * @param tableIndex * word文档中的第N张表(从1开始) * @param colIndex * 制定列的序号 (从1开始)
     */
    public void addTableCol(int tableIndex, int colIndex) {
        // 所有表格
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        // 表格的所有行
        Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
        System.out.println(Dispatch.get(cols, "Count"));
        Dispatch col = Dispatch.call(cols, "Item", new Variant(colIndex)).toDispatch();
        // Dispatch col = Dispatch.get(cols, "First").toDispatch();
        Dispatch.call(cols, "Add", col).toDispatch();
        Dispatch.call(cols, "AutoFit");
    }
    /** */
    /**
     * 在第1列前增加一列 * * @param tableIndex * word文档中的第N张表(从1开始)
     */
    public void addFirstTableCol(int tableIndex) {
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        // 表格的所有行
        Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
        Dispatch col = Dispatch.get(cols, "First").toDispatch();
        Dispatch.call(cols, "Add", col).toDispatch();
        Dispatch.call(cols, "AutoFit");
    }
    /** */
    /**
     * 在最后一列前增加一列 * * @param tableIndex * word文档中的第N张表(从1开始)
     */
    public void addLastTableCol(int tableIndex) {
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        // 表格的所有行
        Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
        Dispatch col = Dispatch.get(cols, "Last").toDispatch();
        Dispatch.call(cols, "Add", col).toDispatch();
        Dispatch.call(cols, "AutoFit");
    }
    /** */
    /**
     * 设置当前选定内容的字体 * * @param boldSize * @param italicSize * @param underLineSize * 下划线 * @param colorSize * 字体颜色 * @param size * 字体大小 * @param name * 字体名称
     */
    public void setFont(boolean bold, boolean italic, boolean underLine, String colorSize, String size, String name) {
        Dispatch font = Dispatch.get(selection, "Font").toDispatch();
        Dispatch.put(font, "Name", new Variant(name));
        Dispatch.put(font, "Bold", new Variant(bold));
        Dispatch.put(font, "Italic", new Variant(italic));
        Dispatch.put(font, "Underline", new Variant(underLine));
        Dispatch.put(font, "Color", colorSize);
        Dispatch.put(font, "Size", size);
    }

    public void setFontCenter(String name) {
        Dispatch font = Dispatch.get(selection, "Font").toDispatch();
        Dispatch alignment = Dispatch.get(selection, "ParagraphFormat").toDispatch();
        Dispatch.put(alignment, "Alignment", "3");
        Dispatch.call(selection, "TypeText", name);
    }
    /** */
    /**
     * 文件保存或另存为 * * @param savePath * 保存或另存为路径
     */
    public  void save(String savePath) {
        Dispatch.call(doc, "SaveAs", savePath);
        // 保存 /**/
		/* * Dispatch.call(Dispatch.call(word, "WordBasic").getDispatch(), * "FileSaveAs", savePath);
		 */
    } /** */
    /**
     * 关闭当前word文档 *
     */
    public  void closeDocument() {
        if (doc != null) {
            //Dispatch.call(doc, "Save");
            try {
                Dispatch.call(doc, "Close", new Variant(saveOnExit));
                doc = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /** */
    /**
     * 关闭全部应用 *
     */
    public  void close() {
        closeDocument();
        if (word != null) {
            try {
                Dispatch.call(word, "Quit");
                word = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        selection = null;
        documents = null;
        //ComThread.Release();
    }
    /** */
    /**
     * 打印当前word文档 *
     */
    public void printFile() {
        if (doc != null) {
            Dispatch.call(doc, "PrintOut");
        }
    }
    /** */
    /**
     * 删除一行 * * @param tableIndex * word文档中的第N张表(从1开始)
     */
    public void delRow(int tableIndex) {
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        // 表格的所有行
        Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
        Object temp1 = Dispatch.get(rows, "Count");
        String temp2 = temp1.toString();
        int count = Integer.parseInt(temp2);
        while (count > 1) {
            Dispatch row = Dispatch.get(rows, "Last").toDispatch();
            Dispatch.call(row, "Delete");
            rows = Dispatch.get(table, "Rows").toDispatch();
            temp1 = Dispatch.get(rows, "Count");
            temp2 = temp1.toString();
            count = Integer.parseInt(temp2);
        }
    }

    public void setProp(String sName, String sValue) {
        Dispatch props = Dispatch.get(doc, "CustomDocumentProperties").toDispatch();
        Dispatch prop = Dispatch.call(props, "Item", sName).toDispatch();
        String sOldVal = Dispatch.get(prop, "Value").toString();
        if (!sOldVal.equals(sValue))
            Dispatch.put(prop, "Value", sValue);
    }
    /** */
    /**
     * @param nType: * 1, number; 2,bool; 3,date; 4,str;
     */
    public void addProp(String sName, int nType, String sValue) {
        Dispatch props = Dispatch.get(doc, "CustomDocumentProperties").toDispatch();
        Dispatch prop = null;
        try {
            prop = Dispatch.call(props, "Item", sName).toDispatch();
        } catch (Exception e) {
            prop = null;
        }
        if (prop != null)
            return; // 1, number; 2,bool; 3,date; 4,str;
        prop = Dispatch.call(props, "Add", sName, false, nType, sValue).toDispatch();
        Dispatch.put(prop, "Value", sValue);
    }

    public String getProp(String sName) {
        String sValue = null;
        Dispatch props = Dispatch.get(doc, "CustomDocumentProperties").toDispatch();
        Dispatch prop = Dispatch.call(props, "Item", sName).toDispatch();
        sValue = Dispatch.get(prop, "Value").toString();
        @SuppressWarnings("unused")
        String sType = Dispatch.get(prop, "Type").toString();
        try {
            Dispatch prop0 = Dispatch.call(doc, "CustomDocumentProperties", sName).toDispatch();
            sValue = Dispatch.get(prop0, "Value").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sValue;
    }

    public void fack_change() {
        Dispatch _sel = Dispatch.call(doc, "Range", 0, 0).toDispatch();
        Dispatch.call(_sel, "InsertBefore", "A");
        Dispatch.call(_sel, "Select");
        Dispatch.call(_sel, "Delete");
    }

    public  void wordToHtml(String docfile, String htmlfile) {
        ActiveXComponent app = new ActiveXComponent("Word.Application"); // 启动word   
        try {
            app.setProperty("Visible", new Variant(false));
            Dispatch docs = app.getProperty("Documents").toDispatch();
            Dispatch doc = Dispatch.invoke(
                    docs,
                    "Open",
                    Dispatch.Method,
                    new Object[]{docfile, new Variant(false),
                            new Variant(true)}, new int[1]).toDispatch();
            /*
             * new Variant(10)筛选过的网页
             * new Variant(9) 单个文件网页
             * new Variant(8) 另存为网页
             * new Variant(7) 另存为txt格式
             * new Variant(6) 另存为rtf格式
             * new Variant(0) 另存为doc格式
             */
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[]{
                    htmlfile, new Variant(8)}, new int[1]);
            Variant f = new Variant(false);
            Dispatch.call(doc, "Close", f);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            app.invoke("Quit", new Variant[]{});
        }
    }


}