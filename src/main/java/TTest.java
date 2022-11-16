import cn.net.withub.ums.util.SimpleDecodeParameter;
import org.apache.commons.collections.MapUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by jacky on 2015/9/23.
 */
public class TTest {
    public static void captureHtml(String ip) throws Exception {
       /* String strURL = "http://ip.chinaz.com/?IP=" + ip;
        strURL = ip;
        URL url = new URL(strURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        InputStreamReader input = new InputStreamReader(httpConn
                .getInputStream(), "utf-8");
        BufferedReader bufReader = new BufferedReader(input);
        String line = "";
        StringBuilder contentBuf = new StringBuilder();
        while ((line = bufReader.readLine()) != null) {
            contentBuf.append(line);
        }
        String buf = contentBuf.toString();
//        int beginIx = buf.indexOf("查询结果[");
//        int endIx = buf.indexOf("上面四项依次显示的是");
//        String result = buf.substring(beginIx, endIx);
        System.out.println("captureHtml()的结果：\n" + buf);
        String ss = "sssadfasd";
        ss.length();
        String[] sss = new String[5];
        System.out.print(sss.length);*/

        System.out.print(3<<4);
        String ss = "AABB";
        ss = ss.replace('A','B');
        System.out.print(ss);

    }

    public static void main(String[] args) {
        try {
//            String selectData = "JTIwJTIwYW5kJTIwJTIwKCUyMGEuYWRtaW5pc3RyYXRpb25fcG9zaXRpb24lMjBpbiUyMCglMjAnMSclMkMnMzgnJTIwKSUyMCUyMG9yJTIwZXhpc3RzJTIwKHNlbGVjdCUyMDElMjBmcm9tJTIwdW1zX2FkbWluaXN0cmF0aXZlX2pvYiUyMHdoZXJlJTIwYS5pZCUyMCUzRCUyMHVtc19hZG1pbmlzdHJhdGl2ZV9qb2IudXNlcl9pZCUyMGFuZCUyMHVtc19hZG1pbmlzdHJhdGl2ZV9qb2Iubl9qb2JfcmVwb3J0JTIwaW4lMjAoJzEnJTJDJzM4JyUyMCklMjApKQ==";
//            byte[] decode = SimpleDecodeParameter.decode(selectData);
//            selectData = new String(decode,"utf-8");
//            System.out.println(selectData);
            compareData();

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    /*
    比较数据
     */
    private static void compareData(){

        try {
            DriverManagerDataSource dataSource1 = new DriverManagerDataSource();
            dataSource1.setDriverClassName("org.mariadb.jdbc.Driver");
            dataSource1.setUrl("jdbc:mariadb://149.0.150.142:3306/system_platform");
            dataSource1.setUsername("adm_srv_4");
            dataSource1.setPassword("x24esO#r0U");
            JdbcTemplate jdbcTemplate1 = new JdbcTemplate(dataSource1);


            DriverManagerDataSource dataSource2 = new DriverManagerDataSource();
            dataSource2.setDriverClassName("dm.jdbc.driver.DmDriver");
            dataSource2.setUrl("jdbc:dm://149.0.152.2:5236/system_platform");
            dataSource2.setUsername("cy");
            dataSource2.setPassword("123456789");
            JdbcTemplate jdbcTemplate2 = new JdbcTemplate(dataSource2);


            String s1 = " SELECT TABLE_NAME from information_schema.`TABLES` WHERE TABLE_SCHEMA = 'system_platform'\n" +
                    " and TABLE_TYPE = 'BASE TABLE' and TABLE_NAME not like 'xtpt_t_login%' ";
            List<Map<String, Object>> list = jdbcTemplate1.queryForList(s1);
            for (Map<String, Object> map : list) {
                String table_name = MapUtils.getString(map, "TABLE_NAME");
                try {
                    String countSql = " select count(1) from  " +table_name ;
                    Integer c1 = jdbcTemplate1.queryForObject(countSql, Integer.class);
                    Integer c2 = jdbcTemplate2.queryForObject(countSql, Integer.class);
                    if(c1.intValue() !=  c2.intValue()){
                        System.out.println("=================================");
                        System.out.println(table_name );
                        System.out.println("c1 : " + c1 + " c2 :" + c2);
                        System.out.println("=================================");
                    }


                } catch (Exception e) {
                    System.out.println("=================================");
                    System.out.println(table_name );
                    System.out.println("=================================");
                }


            }


        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }
}
