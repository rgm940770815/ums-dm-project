package cn.net.withub.ums.sync;

import cn.net.withub.ums.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class DataSynchronization {

    private static final Log log = LogFactory.getLog(DataSynchronization.class);
    Connection _destConn = null; // DB2数据库

    /**
     * 人事系统的数据库连接获取
     *
     * @param ip
     * @param port
     * @param dbname
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public Connection getSrcConn(String ip, String port, String dbname,
            String username, String password) throws Exception {
        Connection con;
        Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance();
        String url = "jdbc:sybase:Tds:" + ip + ":" + port + "/fyrs2009";// myDB为你的数据库名
        Properties sysProps = System.getProperties();
        sysProps.put("user", "dba"); // 设置数据库访问用户名
        sysProps.put("password", "sql"); // 密码
        con = DriverManager.getConnection(url, sysProps);
        return con;
    }

    /**
     * 同步点的数据库连接获取
     *
     * @return
     */
    public Connection getDestConn() {
        if (_destConn == null) {
            String driver = "org.mariadb.jdbc.Driver";
            String URL = "jdbc:mysql://149.0.0.149:3306/ums";
            String username = "root";
            String password = "1234";
            try {
                Class.forName(driver);
                _destConn = DriverManager.getConnection(URL, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return _destConn;
    }

    public static void main(String[] args) {
        runMain();
    }

    public static void runMain() {
        DataSynchronization jdbcTemplateQuery = new DataSynchronization();
        String[][] address = new String[][]{{"149.0.0.209", "2638", "dba", "sql", "M00", "重庆市高级人民法院", ""}};
        for (String[] ss : address) {
            try {
                //导数据
                jdbcTemplateQuery.export_data(jdbcTemplateQuery, ss[0], ss[1], ss[3], ss[4]);
                //导图片
                //jdbcTemplateQuery.export_dataPhoto2(jdbcTemplateQuery, ss[0], ss[1], ss[3], ss[4]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    boolean writetofile = false;
    String mysqlLinkTableSql = "update `%s` a set user_id=(select id from ums_user_info where court_no=a.court_no and user_no=a.user_no);";

    public void export_data(DataSynchronization jdbcTemplateQuery, String ip,
            String port, String password, String courtCode) {
        long s = System.currentTimeMillis();

        try (
                Connection srcConn = jdbcTemplateQuery.getSrcConn(ip, port, "zxgl", "sa", password);
                Connection destConn = jdbcTemplateQuery.getDestConn();
                Statement destStmt = destConn.createStatement();
                Statement srcStmt1 = srcConn.createStatement();
                Statement srcStmt2 = srcConn.createStatement();) {
            String tablesql = "select name,id  from sysobjects where type='U' and (name like 'T_%' or name like 'TH_%') and name not in ('T_DATAUPLOAD_CHANGELOG','T_RYWH_LOG','T_Customtmpl','T_Version') "
                    + "and name not in ('T_RYSX_PHOTO') and name in ('T_RYSX_SYYYX')";
            //String tablesql = "select name,id  from sysobjects where type='U' and  name  in ('T_RYSX_PHOTO') ";
            List<String> mysqlTableNames = new ArrayList<>();
            try (ResultSet rsTbls = srcStmt2.executeQuery(tablesql)) {
                String tableName = "";
                log.info(courtCode + "开始导入编码表数据.......");
                int tableCount = 0;
                String[] cols;
                while (rsTbls.next()) {
                    tableCount++;
                    try {
                        tableName = rsTbls.getString(1);
                        if (tableName.contains("T_DD")) {
                            tableName = tableName.toUpperCase();//T_DD的这几张表一定要用大写
                        }
                        String tableId = rsTbls.getString(2);
                        String destTableName = columnName.get(tableName.toUpperCase()).get(tableName.toUpperCase());
                        log.info("开始导第" + tableCount + " 张表  " + tableName + " 数据...");
//					System.out.println("delete from \"ODS_FYRS_SRC\".\"" + tableName + "\" where courtCode='" + courtCode + "'");;
                        destStmt.execute(String.format("DELETE FROM `%s` WHERE COURT_CODE = '%s';", destTableName, courtCode));
                        destStmt.clearBatch();
                        mysqlTableNames.add(destTableName);

                        long startTime = System.currentTimeMillis();
                        String colNameSql = "select cname,coltype from  syscolumns  c where tname='" + tableName + "' order by colno";
                        String colname;
                        try (ResultSet rsColNames = srcStmt1.executeQuery(colNameSql)) {
                            colname = "null";
                            while (rsColNames.next()) {
                                colname += ";" + rsColNames.getString(1) + ","
                                        + rsColNames.getString(2);
                            }
                        }
                        colname = colname.replace("null;", "");
                        cols = colname.split(";");

                        try ( //if(tableName.equals("T_RYJBXX")) dataSql = "select * from " + tableName+"  where n_rybh<>194193215 and n_rybh<>195241751 ";
                                ResultSet rsTableRows = srcStmt1.executeQuery("select * from " + tableName)) {
                            int insertSqlCount = 0;
                            boolean sqlerr;
                            while (rsTableRows.next()) {
                                String dataValue;
                                sqlerr = false;
                                Map<String, String> row = new HashMap<>();

                                for (int i = 0; i < cols.length; i++) {
                                    //datevalue = rsTableRows.getString(cols[i].split(",")[0]);
                                    if (cols[i].split(",")[0].contains("PHOTO")) {//图片暂时不导
                                        dataValue = "";
                                    } else {
                                        dataValue = rsTableRows.getString(cols[i].split(",")[0]);
                                    }

                                    if (null != dataValue && !dataValue.equals("") && dataValue.length() > 2500) {
                                        dataValue = dataValue.substring(0, 2500);
                                    }

                                    if (i == 0) {//当第一个(关键)字段为null时不导入
                                        if (getValues(cols[i].split(",")[1], dataValue).equals("''")
                                                || getValues(cols[i].split(",")[1], dataValue).toLowerCase().equals("null")) {
                                            sqlerr = true;
                                            break;
                                        }
                                    }

                                    row.put(cols[i].split(",")[0], getValues(cols[i].split(",")[1], dataValue));
                                }
                                String insertSql = mysqlInsertSql(tableName, row, courtCode);
                                if (!sqlerr && !insertSql.trim().equals("")) {
                                    destStmt.addBatch(insertSql);
//							System.out.println(insert);
//							destStmt.execute(insert);
                                    insertSqlCount++;
                                }
                                if (insertSqlCount == 10000) { // 10000条数据提交一次
                                    destStmt.executeBatch();
                                    destStmt.clearBatch();
                                    insertSqlCount = 0;
                                    log.info("提交数据");
                                }
                            }
                            destStmt.executeBatch();
                            destStmt.clearBatch();
                        }
                        log.info(tableName + "用时："
                                + (System.currentTimeMillis() - startTime));
                    } catch (Exception e) {
//					System.out.println(insert);
                        System.out.println(courtCode + "导入表" + tableName + "时失败");
                        e.printStackTrace();
                    }
                }
            }

            log.info("开始处理表连接");
            for (String tn : mysqlTableNames) {
                if (tn.equals("ums_user_info")) {//主表
                    continue;
                }
                long st = System.currentTimeMillis();
                log.info("处理表连接：" + tn);
                try {
                    destStmt.execute(String.format(mysqlLinkTableSql, tn));
                } catch (Exception e) {
                    log.error("连表失败：" + tn);
                }
                log.info("连表：" + tn + ",用时：" + (System.currentTimeMillis() - st));
            }
            log.info("处理表连接结束");

            log.info(System.currentTimeMillis() - s);
            log.info("编码表数据数据导入结束.......");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String mysqlInsertSql(String tableName, Map<String, String> row, String courtCode) {
        if (row.isEmpty()) {
            return "";
        }

        StringBuilder cols = new StringBuilder("id,court_code");
        StringBuilder vals = new StringBuilder("'" + UUID.randomUUID().toString() + "','" + courtCode + "'");
        Map<String, String> colName = columnName.get(tableName.toUpperCase());
        String myTblName = colName.get(tableName.toUpperCase());

        String s = "{";
        for (Map.Entry<String, String> e : row.entrySet()) {
            cols.append(",").append(colName.get(e.getKey().toUpperCase()));
            vals.append(",").append(e.getValue());
            s += String.format("\"%s\":\"%s\",", e.getKey(), e.getValue());
        }
        s += "}";
        String sql = String.format("INSERT INTO `%s` (%s) VALUES(%s)", myTblName, cols, vals);
        //log.info(s + "\n" + sql);
        //log.info(sql);
        return sql;
    }

    public void export_dataPhoto2(DataSynchronization jdbcTemplateQuery, String ip,
            String port, String password, String courtCode) {
        long startTime = System.currentTimeMillis();

        try (
                Connection srcConn = jdbcTemplateQuery.getSrcConn(ip, port, "zxgl", "sa", password);
                Connection destConn = jdbcTemplateQuery.getDestConn();
                Statement srcStmt1 = srcConn.createStatement();
                Statement srcStmt2 = srcConn.createStatement();
                Statement destStmt = destConn.createStatement();) {
            String tableSql = "select name,id  from sysobjects where type='U' and (name like 'T_%' or name like 'TH_%')"
                    + " and  name in('T_RYSX_PHOTO')";
            Map<String, String> photoCols = columnName.get("T_RYSX_PHOTO");

            try (ResultSet rsTables = srcStmt2.executeQuery(tableSql);) {
                String tableName;

                while (rsTables.next()) {
                    tableName = rsTables.getString(1);
                    if (tableName.contains("T_DD")) {
                        tableName = tableName.toUpperCase();//T_DD的这几张表一定要用大写
                    }
                    destStmt.execute(String.format("DELETE FROM `ums_photo` WHERE COURT_CODE = '%s';", courtCode));
                    String colNameSql = "select cname,coltype from  syscolumns  c where tname='" + tableName + "'";
                    String colname = "null";
                    String[] cols;

                    try (ResultSet rsColNames = srcStmt1.executeQuery(colNameSql);) {
                        //I_PHOTO
                        while (rsColNames.next()) {
                            colname += ";" + rsColNames.getString(1) + ","
                                    + rsColNames.getString(2);
                        }
                    }
                    colname = colname.replace("null;", "");
                    cols = colname.split(";");
                    String dataSql = "select * from " + tableName;

                    try (ResultSet rsData = srcStmt1.executeQuery(dataSql);) {
                        while (rsData.next()) {
                            List<Object> values = new ArrayList<>();
                            String userId;
                            int courtNo = rsData.getInt("N_FY");
                            int userNo = rsData.getInt("N_RYBH");

                            try (ResultSet rsId = destStmt.executeQuery("select id from ums_user_info where user_no=" + userNo + " and court_no=" + courtNo);) {
                                if (rsId.next()) {
                                    userId = rsId.getString(1);
                                } else {
                                    userId = "_" + UUID.randomUUID().toString();
                                }
                            }
                            values.add(userId);
                            values.add(courtCode);
                            StringBuilder insertSql = new StringBuilder("INSERT INTO UMS_PHOTO (user_id, court_code");

                            for (String col : cols) {
                                insertSql.append(",").append(photoCols.get(col.split(",")[0]));
                                if (col.split(",")[0].contains("PHOTO")) {
                                    //图片导入
                                    values.add(rsData.getBytes(col.split(",")[0]));
                                } else {
                                    values.add(rsData.getString(col.split(",")[0]));
                                }
                            }

                            insertSql.append(") VALUES ( ?, ? ");
                            for (int i = 0; i < cols.length; i++) {
                                insertSql.append(", ?");
                            }
                            insertSql.append(")");

                            //log.info(insertSql);
                            log.info("u: " + userNo + " c: " + courtNo + " id: " + userId);

                            try (PreparedStatement ps = destConn.prepareStatement(insertSql.toString());) {
                                for (int i = 0; i < values.size(); i++) {
                                    ps.setObject(i + 1, values.get(i));
                                }
                                log.info(Arrays.toString(values.toArray()));
                                ps.execute();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
//            log.info("开始处理表连接");
//            String tn = "ums_photo";
//            long st = System.currentTimeMillis();
//            log.info("处理表连接：" + tn);
//            try {
//                destStmt.execute(String.format(mysqlLinkTableSql, tn));
//            } catch (Exception e) {
//                log.error("连表失败：" + tn);
//            }
//            log.info("连表：" + tn + ",用时：" + (System.currentTimeMillis() - st));
//
//            log.info("处理表连接结束");

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info(System.currentTimeMillis() - startTime);
        log.info("编码表数据数据导入结束.......");
    }

    public void export_dataPhoto(DataSynchronization jdbcTemplateQuery, String ip,
            String port, String password, String courtCode) {
        long s = System.currentTimeMillis();
        Connection conn = null;
        Statement stm = null;
        Statement stm1 = null;
        Connection con = null;
        Statement tstm = null;
        try {
            conn = jdbcTemplateQuery.getSrcConn(ip, port, "zxgl", "sa",
                    password);
            con = jdbcTemplateQuery.getDestConn();
            tstm = con.createStatement();
            String tablesql = "select name,id  from sysobjects where type='U' and (name like 'T_%' or name like 'TH_%')"
                    + " and  name in('T_RYSX_PHOTO')";

            stm = conn.createStatement();
            stm1 = conn.createStatement();
            ResultSet rs = stm1.executeQuery(tablesql);
            String tablename = "";
            String tableid = "";
            String colnamesql = "";
            String insertsql = "";
            String insert = "";
            String datsql = "";
            String datevalue = "";
            ResultSet rs1 = null;
            ResultSet rs2 = null;

            byte[] aa = null;
            File file = null;
            InputStream in = null;
            PreparedStatement ps = null;

            log.info(courtCode + "开始导入编码表数据.......");
            int ii = 0;
            String path = "D:/exportdata/data/" + courtCode + "/";
            if (writetofile) {
                creatMD(path);
            }
            String[] cols;
            while (rs.next()) {
                ii++;

                FileWriter fw = null;
                try {
                    tablename = rs.getString(1).toString();
                    if (tablename.indexOf("T_DD") > -1) {
                        tablename = tablename.toUpperCase();//T_DD的这几张表一定要用大写
                    }
                    if (writetofile) {
                        fw = new FileWriter(path + tablename + ".sql");
                    }
                    tableid = rs.getString(2);
                    log.info("开始导第" + ii + " 张表  " + tablename + " 数据...");

                    long ls = System.currentTimeMillis();
                    colnamesql = "select cname,coltype from  syscolumns  c where tname='" + tablename + "'";
                    rs1 = stm.executeQuery(colnamesql);
                    String colname = "null";
                    //I_PHOTO
                    insertsql = "update  \"ODS_FYRS_SRC\".\"" + tablename + "\" set I_PHOTO=? where fydm='" + courtCode + "' ";
                    while (rs1.next()) {
                        colname += ";" + rs1.getString(1) + ","
                                + rs1.getString(2);
                    }
                    rs1.close();
                    colname = colname.replace("null;", "");
                    cols = colname.split(";");
                    datsql = "select * from " + tablename;
                    rs2 = stm.executeQuery(datsql);
                    int insql = 0;
                    boolean sqlerr = false;
                    while (rs2.next()) {
                        sqlerr = false;
                        insert = insertsql;

                        for (int i = 0; i < cols.length; i++) {
                            if (cols[i].split(",")[0].indexOf("PHOTO") > -1) {//图片导入
                                datevalue = "";
                                aa = rs2.getBytes(cols[i].split(",")[0]);
                            } else {
                                datevalue = rs2.getString(cols[i].split(",")[0]);
                                insert += " and " + cols[i].split(",")[0] + "="
                                        + getValues(cols[i].split(",")[1], datevalue);
                            }
                        }

                        if (!sqlerr && !insert.trim().equals("")) {
                            ps = con.prepareStatement(insert);
                            ps.setBytes(1, aa);
                            ps.executeUpdate();
                            ps.close();
                            insql++;
                        }

//						//测试读取出来--------
//						String mysql = "select * from ODS_FYRS_SRC.T_RYSX_PHOTO where courtCode='M00'  and N_FY=2955 and N_RYBH=193658916";
//						ResultSet rscs = destStmt.executeQuery(mysql);
//						while(rscs.next()){
//							byte b[] = null; //**保存从BLOB读出的字节
//							Blob blob = rscs.getBlob("I_PHOTO");
//							BufferedInputStream bin = new BufferedInputStream(blob.getBinaryStream());
//							    FileOutputStream fout = new FileOutputStream("D:\\test.jpg");
//							    byte[] cache = new byte[10240];
//							    while (bin.read(cache) != -1) {
//							     fout.write(cache);
//							    }
//							    fout.close();
//						}
//						rscs=null;
//						//---测试完成--------
                    }
                    rs2.close();
                    log.info(tablename + "用时："
                            + (System.currentTimeMillis() - ls));
                } catch (Exception e) {
                    System.out.println(courtCode + "导入表" + tablename + "时失败");
                    System.out.println(insert);
                    e.printStackTrace();
                } finally {
                    try {
                        if (fw != null) {
                            fw.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            rs.close();

            log.info(System.currentTimeMillis() - s);
            log.info("编码表数据数据导入结束.......");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (conn != null) {
                    conn.close();
                }

                conn = null;
                stm = null;
                stm1 = null;
                con = null;
                tstm = null;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean creatMD(String path) {// 创建目录,格式：d:\2006-3\06
        boolean bz = false;
        String p[] = null;
        path = path.replaceAll("/", "\\\\");
        String path1;
        p = path.split("\\\\");
        path1 = p[0];
        for (int i = 1; i < p.length; i++) {
            path1 += "\\" + p[i];

            if (!path1.equals("")) {
                File d = new File(path1);// 建立代表path目录的File对象，并得到它的一个引用
                try {
                    if (!d.exists())// 检查path目录是否存在
                    {
                        d.mkdir();// 建立path目录
                    }
                    bz = true;

                } catch (Exception e) {
                    bz = false;
                }
            }
        }
        return bz;
    }

    // T_WS_JPAL
    public static String getValues(String type, String value) {
//		System.out.println("type==="+type+"		value===="+value);
        if (type.contains("char") || type.contains("text")) {
            value = (value == null || StringUtils.isEmpty(value.trim())) ? "null"
                    : "'" + value.replaceAll("'", "''") + "'";
        } else if (type.contains("date") || type.contains("time")) {
            value = (value == null || StringUtils.isEmpty(value.trim())) ? "null"
                    : "'" + value + "'";
        } else {
            value = (value == null || StringUtils.isEmpty(value.trim())) ? "null"
                    : value;
        }
        return value;
    }

    public static String getTargetBaseColumnName(String tableName, String columnName) {
        String returnStr = "";

        return returnStr;
    }

    final static Map<String, Map<String, String>> columnName = new HashMap<>();

    static {

        //领导班子
        Map<String, String> ums_leadership = new HashMap<>();
        ums_leadership.put("T_RYSX_LDBZ", "UMS_LEADERSHIP");//表名
        ums_leadership.put("N_ID", "old_id");
        ums_leadership.put("N_FY", "court_no");
        ums_leadership.put("N_RYBH", "user_no");
        ums_leadership.put("C_QGZDW", "c_former_unit");
        ums_leadership.put("C_QGZBM", "c_former_dept");
        ums_leadership.put("N_ZW", "N_POSITION");
        ums_leadership.put("N_ZQDZYJ", "n_is_party_view");
        ums_leadership.put("N_CJKC", "N_IS_STUDY");
        ums_leadership.put("N_XSSX", "SORT_NO");
        ums_leadership.put("FYDM", "court_code");

        columnName.put("T_RYSX_LDBZ", ums_leadership);

        //通讯录
        Map<String, String> ums_contact = new HashMap<>();
        ums_contact.put("T_RYSX_TXL", "UMS_CONTACT");//表名
        ums_contact.put("N_ID", "old_id");
        ums_contact.put("N_FY", "court_no");
        ums_contact.put("N_RYBH", "user_no");
        ums_contact.put("C_QH", "C_AREA_CODE");
        ums_contact.put("C_BGDH", "C_OFFICE_TEL");
        ums_contact.put("C_JTDH", "C_FAMILY_TEL");
        ums_contact.put("C_YDDH", "c_mobile");
        ums_contact.put("C_YZBM", "c_postcode");
        ums_contact.put("C_TXDZ", "c_address");
        ums_contact.put("N_XSSX", "SORT_NO");
        ums_contact.put("FYDM", "court_code");

        columnName.put("T_RYSX_TXL", ums_contact);

        Map<String, String> ums_reserve_cadre = new HashMap<>();
        ums_reserve_cadre.put("T_RYSX_HBGB", "UMS_RESERVE_CADRE");
        ums_reserve_cadre.put("N_ID", "old_id");
        ums_reserve_cadre.put("N_FY", "court_no");
        ums_reserve_cadre.put("N_RYBH", "user_no");
        ums_reserve_cadre.put("N_HBZW", "N_RESERVE_POS");
        ums_reserve_cadre.put("N_DLZW", "N_EXERCISE_POS");
        ums_reserve_cadre.put("C_DLDW", "C_EXERCISE_UNIT");
        ums_reserve_cadre.put("D_KSSJ", "D_START_DATE");
        ums_reserve_cadre.put("D_JSSJ", "D_END_DATE");
        ums_reserve_cadre.put("C_JL", "C_REMARK");
        ums_reserve_cadre.put("N_XSSX", "SORT_NO");
        ums_reserve_cadre.put("FYDM", "court_code");

        columnName.put("T_RYSX_HBGB", ums_reserve_cadre);

        //工资信息
        Map<String, String> ums_wage_info = new HashMap<>();
        ums_wage_info.put("T_RYSX_GZXX", "UMS_WAGE_INFO");
        ums_wage_info.put("N_ID", "old_id");
        ums_wage_info.put("N_FY", "court_no");
        ums_wage_info.put("N_RYBH", "user_no");
        ums_wage_info.put("C_ZWGZDC", "C_POS_WAGE_GRADE");
        ums_wage_info.put("M_ZWGZE", "M_POS_WAGE");
        ums_wage_info.put("D_ZWGZDCSJ", "D_POS_WAGE_GRADE_TIME");
        ums_wage_info.put("C_XJB", "C_Current_Grade");
        ums_wage_info.put("M_JBGZE", "M_LEVEL_WAGE");
        ums_wage_info.put("D_XJBSJ", "D_CURRENT_GRADE_TIME");
        ums_wage_info.put("M_JCGZ", "M_BASE_WAGE");
        ums_wage_info.put("M_GLGZ", "M_TIME_WAGE");
        ums_wage_info.put("M_ZWGWBT", "M_POSI_ALLOWANCE");
        ums_wage_info.put("M_JT", "M_ALLOWANCE");
        ums_wage_info.put("M_JHLJT", "M_OTHER_ALLOWANCE");
        ums_wage_info.put("N_XSSX", "SORT_NO");
        ums_wage_info.put("FYDM", "court_code");

        columnName.put("T_RYSX_GZXX", ums_wage_info);

        //声音与影像
        Map<String, String> ums_audio_video = new HashMap<>();
        ums_audio_video.put("T_RYSX_SYYYX", "UMS_AUDIO_VIDEO");

        ums_audio_video.put("N_ID", "old_id");
        ums_audio_video.put("N_FY", "court_no");
        ums_audio_video.put("N_RYBH", "user_no");
        ums_audio_video.put("C_FILENAME", "C_FILE_NAME");
        ums_audio_video.put("C_PATH", "C_FILE_PATH");
        ums_audio_video.put("C_MS", "C_DESCRIPTION");
        ums_audio_video.put("N_XSSX", "SORT_NO");
        ums_audio_video.put("FYDM", "court_code");

        columnName.put("T_RYSX_SYYYX", ums_audio_video);

        //备注信息
        Map<String, String> ums_remark = new HashMap<>();
        ums_remark.put("T_RYSX_BZXX", "UMS_REMARK");

        ums_remark.put("N_ID", "old_id");
        ums_remark.put("N_FY", "court_no");
        ums_remark.put("N_RYBH", "user_no");
        ums_remark.put("C_BZ", "C_REMARK");
        ums_remark.put("N_XSSX", "SORT_NO");
        ums_remark.put("FYDM", "court_code");

        columnName.put("T_RYSX_BZXX", ums_remark);

        //伤亡信息
        Map<String, String> ums_casualty_info = new HashMap<>();
        ums_casualty_info.put("T_RYSX_SWXX", "UMS_CASUALTY_INFO");

        ums_casualty_info.put("N_ID", "old_id");
        ums_casualty_info.put("N_FY", "court_no");
        ums_casualty_info.put("N_RYBH", "user_no");
        ums_casualty_info.put("N_SWCD", "N_CST_DAMAGE");
        ums_casualty_info.put("N_SWYY", "N_CST_CAUSE");
        ums_casualty_info.put("D_SWRQ", "D_Cst_Date");
        ums_casualty_info.put("N_XSSX", "SORT_NO");
        ums_casualty_info.put("FYDM", "court_code");

        columnName.put("T_RYSX_SWXX", ums_casualty_info);

        //专业技术职务
        Map<String, String> ums_pro_technical_position = new HashMap<>();
        ums_pro_technical_position.put("T_RYSX_ZYJSZW", "UMS_PRO_TECHNICAL_POSITION");

        ums_pro_technical_position.put("N_ID", "old_id");
        ums_pro_technical_position.put("N_FY", "court_no");
        ums_pro_technical_position.put("N_RYBH", "user_no");
        ums_pro_technical_position.put("N_QDMC", "N_PT_NAME");
        ums_pro_technical_position.put("N_QDTJ", "N_PT_WAY");
        ums_pro_technical_position.put("D_QDRQ", "D_PT_DATE");
        ums_pro_technical_position.put("N_PRMC", "N_ENGAGE_NAME");
        ums_pro_technical_position.put("D_PRRQ", "D_ENGAGE_DATE");
        ums_pro_technical_position.put("N_ZCDJ", "N_TITLE_RANK");
        ums_pro_technical_position.put("N_XSSX", "SORT_NO");
        ums_pro_technical_position.put("N_SFSH", "n_is_verified");
        ums_pro_technical_position.put("FYDM", "court_code");

        columnName.put("T_RYSX_ZYJSZW", ums_pro_technical_position);

        //司法考试
        Map<String, String> ums_judicial_exam = new HashMap<>();
        ums_judicial_exam.put("T_RYSX_SFKS", "UMS_JUDICIAL_EXAM");

        ums_judicial_exam.put("N_ID", "old_id");
        ums_judicial_exam.put("N_FY", "court_no");
        ums_judicial_exam.put("N_RYBH", "user_no");
        ums_judicial_exam.put("N_ZSLX", "N_CERT_TYPE");
        ums_judicial_exam.put("D_BZRQ", "D_ISSUE_DATE");
        ums_judicial_exam.put("C_ZSBH", "C_Cert_No");
        ums_judicial_exam.put("N_XSSX", "SORT_NO");
        ums_judicial_exam.put("N_SFSH", "n_is_verified");
        ums_judicial_exam.put("FYDM", "court_code");

        columnName.put("T_RYSX_SFKS", ums_judicial_exam);

        //交流信息
        Map<String, String> ums_exchange_info = new HashMap<>();
        ums_exchange_info.put("T_RYSX_JIAOLIUXX", "UMS_EXCHANGE_INFO");

        ums_exchange_info.put("N_ID", "old_id");
        ums_exchange_info.put("N_FY", "court_no");
        ums_exchange_info.put("N_RYBH", "user_no");
        ums_exchange_info.put("N_JLLB", "N_EX_TYPE");
        ums_exchange_info.put("N_JLFS", "N_EX_WAY");
        ums_exchange_info.put("N_JLZWXZ", "N_EX_NATURE");
        ums_exchange_info.put("D_KSRQ", "D_START_DATE");
        ums_exchange_info.put("D_JSRQ", "D_END_DATE");
        ums_exchange_info.put("C_JLZWMC", "C_EX_POS_NAME");
        ums_exchange_info.put("C_JLGZDW", "C_EX_COMP_NAME");
        ums_exchange_info.put("C_JLGZBM", "C_EX_DEPT_NAME");
        ums_exchange_info.put("N_TBJL", "n_sync");
        ums_exchange_info.put("C_JLID", "record_id");
        ums_exchange_info.put("N_XSSX", "SORT_NO");
        ums_exchange_info.put("FYDM", "court_code");

        columnName.put("T_RYSX_JIAOLIUXX", ums_exchange_info);

        //外语信息
        Map<String, String> ums_foreign_lang = new HashMap<>();
        ums_foreign_lang.put("T_RYSX_WYXX", "UMS_FOREIGN_LANG");

        ums_foreign_lang.put("N_ID", "old_id");
        ums_foreign_lang.put("N_FY", "court_no");
        ums_foreign_lang.put("N_RYBH", "user_no");
        ums_foreign_lang.put("N_WYYZ", "N_LANGUAGE");
        ums_foreign_lang.put("N_SLCD", "N_PROFICIENCY");
        ums_foreign_lang.put("N_SPJB", "N_LEVEL");
        ums_foreign_lang.put("N_XSSX", "SORT_NO");
        ums_foreign_lang.put("FYDM", "Court_Code");

        columnName.put("T_RYSX_WYXX", ums_foreign_lang);

        //出国信息
        Map<String, String> ums_abroad_info = new HashMap<>();
        ums_abroad_info.put("T_RYSX_CGXX", "UMS_ABROAD_INFO");

        ums_abroad_info.put("N_ID", "old_id");
        ums_abroad_info.put("N_FY", "court_no");
        ums_abroad_info.put("N_RYBH", "user_no");
        ums_abroad_info.put("N_GB", "N_COUNTRY");
        ums_abroad_info.put("N_CGXZ", "N_NATURE");
        ums_abroad_info.put("D_KSSJ", "d_start_date");
        ums_abroad_info.put("D_JSSJ", "d_end_date");
        ums_abroad_info.put("N_CGSF", "N_Identity");
        ums_abroad_info.put("C_JFLY", "C_Fund_Source");
        ums_abroad_info.put("N_XSSX", "sort_no");
        ums_abroad_info.put("FYDM", "Court_Code");

        columnName.put("T_RYSX_CGXX", ums_abroad_info);

        //惩处信息
        Map<String, String> ums_punish_info = new HashMap<>();
        ums_punish_info.put("T_RYSX_CCXX", "ums_punish_info");

        ums_punish_info.put("N_ID", "old_id");
        ums_punish_info.put("N_FY", "court_no");
        ums_punish_info.put("N_RYBH", "user_no");
        ums_punish_info.put("N_CCLB", "n_punish_type");
        ums_punish_info.put("N_CCYY", "n_punish_reason");
        ums_punish_info.put("D_CCRQ", "d_start_date");
        ums_punish_info.put("D_JCRQ", "d_end_date");
        ums_punish_info.put("N_CGSF", "N_Identity");
        ums_punish_info.put("C_PZDW", "C_Unit");
        ums_punish_info.put("N_XSSX", "sort_no");
        ums_punish_info.put("FYDM", "court_code");

        columnName.put("T_RYSX_CCXX", ums_punish_info);

        //考核信息
        Map<String, String> ums_assess_info = new HashMap<>();
        ums_assess_info.put("T_RYSX_KHXX", "UMS_ASSESS_INFO");

        ums_assess_info.put("N_ID", "old_id");
        ums_assess_info.put("N_FY", "court_no");
        ums_assess_info.put("N_RYBH", "user_no");
        ums_assess_info.put("N_ND", "n_year");
        ums_assess_info.put("N_KHJG", "n_result");
        ums_assess_info.put("N_XSSX", "sort_no");
        ums_assess_info.put("FYDM", "court_code");

        columnName.put("T_RYSX_KHXX", ums_assess_info);

        //家庭信息
        Map<String, String> ums_family_info = new HashMap<>();
        ums_family_info.put("T_RYSX_JTXX", "ums_family_info");

        ums_family_info.put("N_ID", "old_id");
        ums_family_info.put("N_FY", "court_no");
        ums_family_info.put("N_RYBH", "user_no");
        ums_family_info.put("C_XM", "c_name");
        ums_family_info.put("N_YBRGX", "n_relationship");
        ums_family_info.put("N_XSSX", "sort_no");
        ums_family_info.put("FYDM", "court_code");
        ums_family_info.put("D_CSRQ", "d_birthday");
        ums_family_info.put("N_ZZMM", "n_political");
        ums_family_info.put("C_JTDH", "c_telephone");
        ums_family_info.put("C_YZBM", "c_postcode");
        ums_family_info.put("N_ZFMJ", "n_house_area");
        ums_family_info.put("C_JTZZ", "c_address");
        ums_family_info.put("C_DWJZW", "c_unit_job");

        columnName.put("T_RYSX_JTXX", ums_family_info);

        //简历信息
        Map<String, String> ums_resume_info = new HashMap<>();
        ums_resume_info.put("T_RYSX_JLXX", "ums_resume_info");

        ums_resume_info.put("N_ID", "old_id");
        ums_resume_info.put("N_FY", "court_no");
        ums_resume_info.put("N_RYBH", "user_no");
        ums_resume_info.put("N_XSSX", "sort_no");
        ums_resume_info.put("FYDM", "court_code");

        ums_resume_info.put("C_SZDW", "c_unit");
        ums_resume_info.put("C_SZBM", "c_dept");
        ums_resume_info.put("D_QSRQ", "d_start_date");
        ums_resume_info.put("D_JZRQ", "d_end_date");
        ums_resume_info.put("C_ZW", "c_position");
        ums_resume_info.put("C_ZJ", "c_rank");
        ums_resume_info.put("C_ZMR", "c_reference");
        ums_resume_info.put("C_GLXX", "c_manage_info");

        columnName.put("T_RYSX_JLXX", ums_resume_info);

        //培训信息
        Map<String, String> ums_training_info = new HashMap<>();
        ums_training_info.put("T_RYSX_PXXX", "ums_training_info");

        ums_training_info.put("N_ID", "old_id");
        ums_training_info.put("N_FY", "court_no");
        ums_training_info.put("N_RYBH", "user_no");
        ums_training_info.put("N_XSSX", "sort_no");
        ums_training_info.put("FYDM", "court_code");

        ums_training_info.put("N_PXXS", "n_training_form");
        ums_training_info.put("C_QTPXXS", "c_other_training_form");
        ums_training_info.put("C_PXBMC", "c_class_name");
        ums_training_info.put("D_KSRQ", "d_start_date");
        ums_training_info.put("D_JSRQ", "d_end_date");
        ums_training_info.put("N_JGLB", "n_agency_type");
        ums_training_info.put("C_QTJGLB", "c_other_agency_type");
        ums_training_info.put("C_PXJG", "c_agency");
        ums_training_info.put("N_ZY", "n_specialty");
        ums_training_info.put("N_XZ", "n_duration");
        ums_training_info.put("C_PXCJ", "c_result");
        ums_training_info.put("N_PXFS", "n_training_way");
        ums_training_info.put("N_PXZL", "n_training_type");
        ums_training_info.put("N_TBJL", "n_sync");
        ums_training_info.put("C_JLID", "record_id");
        ums_training_info.put("N_SFQDZS", "n_has_cert");
        ums_training_info.put("N_SFSH", "n_is_verified");

        columnName.put("T_RYSX_PXXX", ums_training_info);

        //奖励信息
        Map<String, String> ums_reward_info = new HashMap<>();
        ums_reward_info.put("T_RYSX_JIANGLIXX", "ums_reward_info");

        ums_reward_info.put("N_ID", "old_id");
        ums_reward_info.put("N_FY", "court_no");
        ums_reward_info.put("N_RYBH", "user_no");
        ums_reward_info.put("N_XSSX", "sort_no");
        ums_reward_info.put("FYDM", "court_code");

        ums_reward_info.put("N_JLLB", "n_reward_type");
        ums_reward_info.put("N_JLYY", "n_reward_reason");
        ums_reward_info.put("N_GRQK", "n_personal_state");
        ums_reward_info.put("N_JLJB", "n_reward_level");
        ums_reward_info.put("C_PZDW", "c_approval_unit");
        ums_reward_info.put("C_PZWH", "c_approval_doc_no");
        ums_reward_info.put("D_JLSJ", "d_reward_date");
        ums_reward_info.put("C_JLLBSM", "c_reward_type_info");
        ums_reward_info.put("C_JLYYXX", "c_reward_reason_info");
        ums_reward_info.put("N_SFSH", "n_is_verified");

        columnName.put("T_RYSX_JIANGLIXX", ums_reward_info);

        //在读信息
        Map<String, String> ums_study_info = new HashMap<>();
        ums_study_info.put("T_RYSX_ZDXX", "ums_study_info");

        ums_study_info.put("N_ID", "old_id");
        ums_study_info.put("N_FY", "court_no");
        ums_study_info.put("N_RYBH", "user_no");
        ums_study_info.put("N_XSSX", "sort_no");
        ums_study_info.put("FYDM", "court_code");

        ums_study_info.put("D_RXRQ", "d_entry_date");
        ums_study_info.put("N_ZDXL", "n_education_background");
        ums_study_info.put("C_ZDYX", "c_college");
        ums_study_info.put("N_SXZY", "n_origin_major");
        ums_study_info.put("C_ZDZY", "c_major");
        ums_study_info.put("N_JYXS", "n_educate_form");
        ums_study_info.put("N_XXXS", "n_study_form");
        ums_study_info.put("N_XZ", "n_duration");
        ums_study_info.put("N_DQXX", "n_present_info");

        columnName.put("T_RYSX_ZDXX", ums_study_info);

        //学位信息
        Map<String, String> ums_degree_info = new HashMap<>();
        ums_degree_info.put("T_RYSX_XWXX", "ums_degree_info");

        ums_degree_info.put("N_ID", "old_id");
        ums_degree_info.put("N_FY", "court_no");
        ums_degree_info.put("N_RYBH", "user_no");
        ums_degree_info.put("N_XSSX", "sort_no");
        ums_degree_info.put("FYDM", "court_code");

        ums_degree_info.put("D_RXRQ", "d_entry_date");
        ums_degree_info.put("D_BYRQ", "d_graduate_date");
        ums_degree_info.put("N_XW", "n_degree");
        ums_degree_info.put("C_XXMC", "c_college");
        ums_degree_info.put("C_SXZY", "c_major");
        ums_degree_info.put("N_SXZY", "n_major");
        ums_degree_info.put("N_JYXS", "n_educate_form");
        ums_degree_info.put("N_XXXS", "n_study_form");
        ums_degree_info.put("N_XXLB", "n_school_type");
        ums_degree_info.put("N_XZ", "n_duration");
        ums_degree_info.put("N_DQXX", "n_present_info");
        ums_degree_info.put("D_SYRQ", "d_award_date");
        ums_degree_info.put("N_SYGJ", "n_award_country");
        ums_degree_info.put("N_TBJL", "n_sync");
        ums_degree_info.put("C_JLID", "record_id");

        columnName.put("T_RYSX_XWXX", ums_degree_info);

        //学历信息
        Map<String, String> ums_education_info = new HashMap<>();
        ums_education_info.put("T_RYSX_XLXX", "ums_education_info");

        ums_education_info.put("N_ID", "old_id");
        ums_education_info.put("N_FY", "court_no");
        ums_education_info.put("N_RYBH", "user_no");
        ums_education_info.put("N_XSSX", "sort_no");
        ums_education_info.put("FYDM", "court_code");

        ums_education_info.put("D_RXRQ", "d_entry_date");
        ums_education_info.put("D_BYRQ", "d_graduate_date");
        ums_education_info.put("N_XL", "n_education_background");
        ums_education_info.put("C_XXMC", "c_college");
        ums_education_info.put("C_SXZY", "c_major");
        ums_education_info.put("N_SXZY", "n_major");
        ums_education_info.put("N_JYXS", "n_educate_form");
        ums_education_info.put("N_XXXS", "n_study_form");
        ums_education_info.put("N_XXLB", "n_school_type");
        ums_education_info.put("N_XZ", "n_duration");
        ums_education_info.put("N_DQXX", "n_present_info");
        ums_education_info.put("C_SYDW", "c_award_unit");
        ums_education_info.put("N_XXSZGJ", "n_school_country");
        ums_education_info.put("N_TBJL", "n_sync");
        ums_education_info.put("C_JLID", "record_id");
        ums_education_info.put("N_JYXL", "n_enter_court_edu_bg");

        columnName.put("T_RYSX_XLXX", ums_education_info);

        //公务员
        Map<String, String> ums_civil_servant_level = new HashMap<>();
        ums_civil_servant_level.put("T_RYSX_GWY", "ums_civil_servant_level");

        ums_civil_servant_level.put("N_ID", "old_id");
        ums_civil_servant_level.put("N_FY", "court_no");
        ums_civil_servant_level.put("N_RYBH", "user_no");
        ums_civil_servant_level.put("N_XSSX", "sort_no");
        ums_civil_servant_level.put("FYDM", "court_code");

        ums_civil_servant_level.put("N_GWYJB", "n_servant_level");
        ums_civil_servant_level.put("D_QSRQ", "d_start_date");
        ums_civil_servant_level.put("C_DW", "c_unit");
        ums_civil_servant_level.put("N_GZDC", "n_wage_grade");

        ums_civil_servant_level.put("N_DQXX", "n_present_info");

        columnName.put("T_RYSX_GWY", ums_civil_servant_level);

        //等级信息
        columnName.put("T_RYSX_DJXX", new HashMap<String, String>() {
            {
                put("T_RYSX_DJXX", "ums_level_info");
                put("N_ID", "old_id");
                put("N_FY", "court_no");
                put("N_RYBH", "user_no");
                put("N_XSSX", "sort_no");
                put("FYDM", "court_code");

                put("N_DQXX", "n_present_info");

                put("N_DJLB", "n_level_type");
                put("N_DJMC", "n_level_name");
                put("D_QSRQ", "d_start_date");
                put("C_PZDW", "c_approval_unit");
                put("C_PZWH", "c_approval_doc_no");
                put("C_ZSBH", "c_cert_no");
                put("N_BDLB", "n_alt_type");
                put("N_BDYY", "n_alt_reason");
                put("C_BDYJ", "c_alt_basis");
            }
        });

        //法律职务
        columnName.put("T_RYSX_FLZW", new HashMap<String, String>() {
            {
                put("T_RYSX_FLZW", "ums_legal_job");
                put("N_ID", "old_id");
                put("N_FY", "court_no");
                put("N_RYBH", "user_no");
                put("N_XSSX", "sort_no");
                put("FYDM", "court_code");

                put("N_DQXX", "n_present_info");

                put("C_JLID", "record_id");

                put("N_RMLB", "n_assign_type");
                put("N_FLZW", "n_job");
                put("D_RMRQ", "d_assign_date");
                put("C_DW", "c_unit");
                put("C_BM", "c_department");
                put("N_RMYY", "n_assign_reason");
                put("C_PZDW", "c_approval_unit");
                put("C_PZWH", "c_approval_doc_no");
                put("D_PZRQ", "d_approval_date");
                put("N_CRFG", "n_is_first_appoint_judge");
                put("N_CRFGNX", "n_first_judge_year");
                put("N_FLGZQK", "n_job_situation");
                put("N_FLZYZS", "n_is_pro");
                put("N_FLZYZSQK", "n_pro_situation");
            }
        });

        //职级信息
        columnName.put("T_RYSX_ZJXX", new HashMap<String, String>() {
            {
                put("T_RYSX_ZJXX", "ums_rank_info");
                put("N_ID", "old_id");
                put("N_FY", "court_no");
                put("N_RYBH", "user_no");
                put("N_XSSX", "sort_no");
                put("FYDM", "court_code");

                put("N_DQXX", "n_present_info");

                put("C_JLID", "record_id");

                put("N_RMLB", "n_assign_type");
                put("N_ZJ", "n_rank");
                put("N_ZJXZ", "n_rank_nature");
                put("D_RMRQ", "d_assign_date");
                put("C_DW", "c_unit");
                put("C_BM", "c_department");
                put("N_RMYY", "n_assign_reason");
                put("C_PZDW", "c_approval_unit");
                put("C_PZWH", "c_approval_doc_no");
                put("D_PZRQ", "d_approval_date");
            }
        });

        //兼任职务
        columnName.put("T_RYSX_JZZW", new HashMap<String, String>() {
            {
                put("T_RYSX_JZZW", "ums_parttime_position");
                put("N_ID", "old_id");
                put("N_FY", "court_no");
                put("N_RYBH", "user_no");
                put("N_XSSX", "sort_no");
                put("FYDM", "court_code");

                put("N_DQXX", "n_present_info");

                put("C_JLID", "record_id");
                put("N_TBJL", "n_is_sync");
                put("N_SFSH", "n_is_verified");

                put("N_RMLB", "n_assign_type");
                put("C_JRZW", "c_parttime_job");
                put("D_RMRQ", "d_assign_date");
                put("C_DW", "c_unit");
                put("C_BM", "c_department");
                put("N_RMYY", "n_assign_reason");
                put("C_PZDW", "c_approval_unit");
                put("C_PZWH", "c_approval_doc_no");
                put("D_PZRQ", "d_approval_date");
            }
        });

        //行政职务
        columnName.put("T_RYSX_XZZW", new HashMap<String, String>() {
            {
                put("T_RYSX_XZZW", "ums_administrative_job");
                put("N_ID", "old_id");
                put("N_FY", "court_no");
                put("N_RYBH", "user_no");
                put("N_XSSX", "sort_no");
                put("FYDM", "court_code");

                put("N_DQXX", "n_present_info");

                put("C_JLID", "record_id");
                //put("N_TBJL", "n_is_sync");
                //put("N_SFSH", "n_is_verified");

                put("N_RMLB", "n_assign_type");
                put("N_XZZW", "n_job");
                put("D_RMRQ", "d_assign_date");
                put("C_DW", "c_unit");
                put("C_BM", "c_department");
                put("N_RMYY", "n_assign_reason");
                put("C_PZDW", "c_approval_unit");
                put("C_PZWH", "c_approval_doc_no");
                put("D_PZRQ", "d_approval_date");
            }
        });

        //政治面貌
        columnName.put("T_RYSX_ZZMM", new HashMap<String, String>() {
            {
                put("T_RYSX_ZZMM", "ums_political_info");
                put("N_ID", "old_id");
                put("N_FY", "court_no");
                put("N_RYBH", "user_no");
                put("N_XSSX", "sort_no");
                put("FYDM", "court_code");

                put("N_DQXX", "n_present_info");

                //put("C_JLID", "record_id");
                //put("N_TBJL", "n_is_sync");
                //put("N_SFSH", "n_is_verified");
                put("N_ZZMM", "n_political");
                put("D_JRRQ", "d_entry_date");
                put("D_ZZRQ", "d_positive_date");

            }
        });

        /*
         "N_FY", "N_RYBH", "C_BS", "C_MM", "C_RYBH", "C_CODE_JG1", "C_CODE_JG2",
         "C_CODE_JG3", "C_XM", "C_CYM", "N_XB", "N_BM", "N_UNICODE", "N_GWXZ",
         "C_JG", "C_CSD", "D_CSRQ", "N_JKZK", "N_HYZK", "N_MZ", "C_SFZH", "N_BZ",
         "N_ZWLB", "D_ZWLBSJ", "N_RYFS", "N_XL", "N_ZY", "N_XW", "D_HDXWRQ",
         "D_GZRQ", "D_JYRQ", "N_ZYZS", "D_HDZSRQ", "N_ZZMM", "D_ZZMM", "D_ZFGZRQ",
         "N_XZZW", "D_XZZW_RZRQ", "N_FLZW", "D_FLZW_RZRQ", "N_JRTZ", "N_DZZW",
         "D_DZZWRQ", "D_FGZGRQ", "N_BCGL", "N_KJGL", "N_JYQFYNX", "N_ZJ", "D_ZJRQ",
         "N_DJ", "D_DJRQ", "N_JTJ", "N_JLY", "N_YZW", "N_YZJ", "C_YDW", "D_SHRQ",
         "N_CYY", "D_CRQ", "N_QX", "N_XSSX", "N_YX", "N_YJXZ", "D_QDFGZGZS",
         "N_GWYJB", "D_GWYRQ", "N_QDFGZGZS", "FYDM", "ORDER_NO"
         */
        //基本信息
        columnName.put("T_RYJBXX", new HashMap<String, String>() {
            {
                put("T_RYJBXX", "ums_user_info");

                put("N_FY", "court_no");
                put("N_RYBH", "user_no");
                put("C_BS", "username");
                put("C_MM", "password");
                put("C_RYBH", "user_code");
                put("C_CODE_JG1", "C_CODE_JG1");
                put("C_CODE_JG2", "C_CODE_JG2");
                put("C_CODE_JG3", "C_CODE_JG3");
                put("C_XM", "fullname");
                put("C_CYM", "former_name");
                put("N_XB", "gender");
                put("N_BM", "department");
                put("N_UNICODE", "unicode");
                put("N_GWXZ", "position_nature");
                put("C_JG", "hometown");
                put("C_CSD", "birthplace");
                put("D_CSRQ", "birthday");
                put("N_JKZK", "physical_condition");
                put("N_HYZK", "marital_status");
                put("N_MZ", "nation");
                put("C_SFZH", "idcard");
                put("N_BZ", "preparation");
                put("N_ZWLB", "position_type");
                put("D_ZWLBSJ", "position_type_date");
                put("N_RYFS", "assign");
                put("N_XL", "education_background");
                put("N_ZY", "major");
                put("N_XW", "degree");
                put("D_HDXWRQ", "degree_date");
                put("D_GZRQ", "work_date");
                put("D_JYRQ", "enter_date");
                put("N_ZYZS", "pro_cert");
                put("D_HDZSRQ", "pro_cert_date");
                put("N_ZZMM", "political");
                put("D_ZZMM", "political_date");
                put("D_ZFGZRQ", "politic_law_work_date");
                put("N_XZZW", "administration_position");
                put("D_XZZW_RZRQ", "administration_position_date");
                put("N_FLZW", "law_position");
                put("D_FLZW_RZRQ", "law_position_date");
                put("N_JRTZ", "is_parttime_presiding_judge");
                put("N_DZZW", "party_office");
                put("D_DZZWRQ", "party_office_date");
                put("D_FGZGRQ", "lawyer_date");
                put("N_BCGL", "extra_seniority");
                put("N_KJGL", "deduction_seniority");
                put("N_JYQFYNX", "before_court_work_year");
                put("N_ZJ", "rank");
                put("D_ZJRQ", "rank_date");
                put("N_DJ", "level");
                put("D_DJRQ", "level_date");
                put("N_JTJ", "enter_way");
                put("N_JLY", "enter_source");
                put("N_YZW", "former_post");
                put("N_YZJ", "former_rank");
                put("C_YDW", "former_unit");
                put("D_SHRQ", "verify_date");
                put("N_CYY", "leave_reason");
                put("D_CRQ", "leave_date");
                put("N_QX", "leave_destination");
                put("N_XSSX", "sort_no");
                put("N_YX", "is_valid");
                put("N_YJXZ", "additional_duration");
                put("D_QDFGZGZS", "lawyer_cert_date");
                put("N_GWYJB", "servant_level");
                put("D_GWYRQ", "servant_level_date");
                put("N_QDFGZGZS", "lawyer_cert");
                put("FYDM", "court_code");
                put("ORDER_NO", "order_no");
            }
        });

        //相片
        columnName.put("T_RYSX_PHOTO", new HashMap<String, String>() {
            {
                put("T_RYSX_PHOTO", "ums_photo");

                put("N_FY", "court_no");
                put("N_RYBH", "user_no");

                put("FYDM", "court_code");

                put("I_PHOTO", "photo");

            }
        });

    }

}
