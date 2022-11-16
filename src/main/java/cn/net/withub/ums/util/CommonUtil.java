package cn.net.withub.ums.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonUtil {

    /**
     * 字符串是否为空
     *
     * @param str
     * @return 是否为空
     */
    public static boolean isNullorEmpty(String str) {
        if (str != null && !str.trim().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 字符串是否为空
     * d
     *
     * @param str
     * @return 是否为空
     */
    public static boolean isNotEmpty(String str) {
        return ((str != null) && (str.trim().length() > 0));
    }

    /**
     * 将null转为空串
     *
     * @return
     */
    public static String toString(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * 字符串左边补齐
     *
     * @return
     */
    public static String lpad(String str, int length, String strPad) {
        String ret = "";
        if(str.length()>length){
            return str;
        }
        for (int i = 0; i < length; i++) {
            ret += strPad;
        }
        if (!CommonUtil.isNullorEmpty(str)) {
            str = ret + str;
            str = str.substring(str.length() - length);
        }
        return str;
    }
    /**
     * 字符串右边补齐
     *
     * @return
     */
    public static String rpad(String str, int length, String strPad) {
        String ret = "";
        if(str.length()>length){
            return str;
        }
        for (int i = 0; i < length; i++) {
            ret += strPad;
        }
        if (!CommonUtil.isNullorEmpty(str)) {
            str = str+ret;
            str = str.substring(0,length);
        }
        return str;
    }
    public static Date formatStringToDate(String dateString) {
        if (StringUtils.hasText(dateString)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.parse(dateString);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public static Integer formatStringToNum(String s) {
        if (s != null) {
            try {
                return Integer.valueOf(s);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public static String formatNumber(Object db, int dot) {
        String ret = " ";
        if (db == null)
            return ret;
        Number num = null;
        try {
            num = (Number) db;
        } catch (Exception e) {
            num = new Double((String) db);
        }
        return formatNumber(num.doubleValue(), dot);
    }

    public static String formatPrecent(int fz, int fm) throws Exception {
        if (fm == 0) {
            return "0.00%";
        }
        double ret = fz * 100 / fm;
        return formatNumber(ret, 2) + '%';
    }

    public static Double formatNumberToDouble(Double dConverData, int num)
            throws Exception {
        if (dConverData != null) {
            return formatNumberToDouble(dConverData.doubleValue(), num);
        }
        return 0D;
    }

    public static Double formatNumberToDouble(double dConverData, int num)
            throws Exception {
        return new Double(formatNumber(dConverData, num));
    }

    public static String formatNumber(Double dConverData, int num)
            throws Exception {
        if (dConverData != null) {
            return formatNumber(dConverData.doubleValue(), num);
        }
        return "";
    }

    public static String formatNumber(double dConverData, int num) {
        String sSign = ""; // 前缀符号
        String sConverData = (new BigDecimal(dConverData)).toString();
        if (sConverData.substring(0, 1).equals("-")) {
            sConverData = sConverData.substring(1, sConverData.length());
            sSign = "-";
        } else if (sConverData.substring(0, 1).equals("+")) {
            sConverData = sConverData.substring(1, sConverData.length());
            sSign = "+";
        } else if (sConverData.substring(0, 1).equals(",")) {
            sConverData = sConverData.substring(1, sConverData.length());
            sSign = ",";
        }
        int dot = sConverData.indexOf(".");
        String main = "";
        String little = "";
        int length = sConverData.length();
        if (dot < 0) {
            main = sConverData;
            for (int i = 0; i < num; i++) {
                little = little + "0";
            }
            if (num > 0) {
                little = "." + little;
            }
        } else {
            main = sConverData.substring(0, dot);
            String teml = sConverData.substring(dot + 1, length);
            int litlength = teml.length();
            if (num > 0) {
                if (litlength <= num) {
                    little = teml;
                    for (int i = 0; i < (num - litlength); i++) {
                        little = little + "0";
                    }
                    little = "." + little;
                } else {
                    little = "";
                    int pre = 0;
                    try {
                        String ts = teml.substring(0, num);
                        String tl = teml.substring(num, num + 1);
                        if (Integer.parseInt(tl) >= 5) {
                            pre = 1;
                        }
                        for (int i = num - 1; i >= 0; i--) {
                            if ((Integer.parseInt(ts.substring(i, i + 1)) + pre) <= 9) {
                                little = (Integer.parseInt(ts.substring(i,
                                        i + 1)) + pre)
                                        + little;
                                pre = 0;
                            } else {
                                little = "0" + little;
                                pre = 1;
                            }
                        }
                        main = "" + (Long.parseLong(main) + pre);
                    } catch (Exception e) {
                    }
                    little = "." + little;
                }
            }
        }
        return sSign + main + little;
    }

    /**
     * 检查字符串中是否包含指定的字符。如果字符串为<code>null</code>，将返回<code>false</code>。
     * <p/>
     * <pre>
     * StringUtil.contains(null, *)    = false
     * StringUtil.contains(&quot;&quot;, *)      = false
     * StringUtil.contains(&quot;abc&quot;, 'a') = true
     * StringUtil.contains(&quot;abc&quot;, 'z') = false
     * </pre>
     *
     * @param str        要扫描的字符串
     * @param searchChar 要查找的字符
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean contains(String str, char searchChar) {
        if ((str == null) || (str.length() == 0)) {
            return false;
        }

        return str.indexOf(searchChar) >= 0;
    }

    public static String getRemoteIpAddr(HttpServletRequest req) {
        String ip = null;
        try {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = req.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = req.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = req.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = req.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = req.getRemoteAddr();
            }
            if (ip != null && ip.equals("0:0:0:0:0:0:0:1")) {
                return "127.0.0.1";
            }
            //ip = ip!=null&&ip.trim().indexOf(",")>0?(ip.split(",").length>1?ip.split(",")[1]:ip.split(",")[0]):ip;
            if (ip != null && ip.trim().split(",").length > 1) {
                String[] arrs = ip.trim().split(",");
                for (String ipAddress : arrs) {
                    if (ipAddress.startsWith("149.")) {
                        return ipAddress.trim();
                    }
                }
            }
        } catch (Exception e) {
            ip = req.getRemoteAddr();
        }
        if (ip != null && -1 == ip.indexOf(".")) {
            return "127.0.0.1";
        }
        return ip;
    }

    public static String sqlReplace(String source) {
        return source == null ? "" : source.replace("'", "");
    }


    public static String creatUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 驼峰法和帕斯卡法转换成数据库的下划线表示
     *
     * @param s
     * @return
     */
    public static String camelOrPascalToUnderline(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append(("_" + c).toLowerCase());
            } else {
                sb.append(c);
            }
        }

        if (sb.charAt(0) == '_') {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }
//==================================================================================

    public static String getUrlConnection2(String toUrl) throws Exception {
        toUrl = toUrl.replaceAll(" ", "%20");
        URL url = new URL(toUrl);
        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(1000);
        String json = null;
        InputStream inStream = null;
        BufferedReader br = null;
        try {
            conn.connect();
            inStream = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
            String line = null;
            json = "";
            while ((line = br.readLine()) != null) {
                json +=line;
            }
        } catch (Exception e) {
        } finally {
            if (br != null) {
                br.close();
            }
            if (inStream != null) {
                inStream.close();
            }
        }
        return json;
    }

    public static String getUrlConnection3(String toUrl) throws Exception {
        toUrl = toUrl.replaceAll(" ", "%20");
        URL url = new URL(toUrl);
        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(1000);
        String json = null;
        InputStream inStream = null;
        BufferedReader br = null;
        try {
            conn.connect();
            inStream = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
            String line = null;
            json = "";
            while ((line = br.readLine()) != null) {
                json +=line;
                json += "<br/>";
            }
        } catch (IOException e) {
//            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
            if (inStream != null) {
                inStream.close();
            }
        }
        return json;
    }

    /**
     * 将字符串发送到远程服务器上
     *
     * @param
     * @param urlStr
     * @return
     */
    public static String postStringToUrl(String str, String urlStr) {
        urlStr = urlStr.replaceAll(" ", "%20");
        BufferedWriter bWriter = null;
        HttpURLConnection urlConn = null;
        String sCurrentLine = "";
        String sTotalString = "";
        int res = 0;
        try {
            byte[] byteBuffer = str.getBytes("utf-8");
            URL httpurl = new URL(urlStr);
            urlConn = (HttpURLConnection) httpurl.openConnection();
            urlConn.setRequestProperty("Content-Type", "application/octet-stream");
            urlConn.setRequestProperty("Content-length", "" + byteBuffer.length);
            urlConn.setRequestProperty("pure-data", "yes");
            urlConn.setRequestProperty("Connection", "Keep-Alive");
            urlConn.setDoOutput(true);
            OutputStream out = urlConn.getOutputStream();
            try {
                out.write(byteBuffer);
            } finally {
                out.flush();
                out.close();
            }
            //url请求返回code值
            res = urlConn.getResponseCode();
            if (res == 200) {
                InputStream is = urlConn.getInputStream();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is));
                while ((sCurrentLine = reader.readLine()) != null)
                    if (sCurrentLine.length() > 0)
                        sTotalString = sTotalString + sCurrentLine.trim();
                String tmpStr = new String(sTotalString.getBytes("utf-8"));
                if(tmpStr!=null&&tmpStr.indexOf("success")!=-1){
                    sTotalString = "success";
                }else{
                    sTotalString = "error";
                }
            } else {
                sTotalString = "error";
            }
            if (bWriter != null) {
                bWriter.close();
            }
        } catch (Exception e) {
            sTotalString = "error";
        }
        return sTotalString;
    }

	/**
	 * 传入对象
	 * @param obj 前提obj存在set get方法
	 * @return返回对象存在的属性值
	 * @throws Exception
	 */
	public static Object ObjectParesToString(Object obj) throws Exception{
		Field[] fields=obj.getClass().getDeclaredFields();
		for(Field f:fields){
			f.setAccessible(true);
			//属性类型
			String type = f.getType().toString();// 得到此属性的类型
			Object value=f.get(obj);
			if (value==null||"null".equals(value)){
				if(type.endsWith("String")){
					f.set(obj,"");
				}else if(type.endsWith("Integer")){
					f.set(obj,0);
				}
			}
		}
		return obj;
	}

	/**
	 * 通过反射机制，实现将Map对象转换为对象
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
			return null;
		Object obj = beanClass.newInstance();		//新创建一个对象

		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				int mod = field.getModifiers();
				if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
					continue;
				}
				field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }catch (Exception e){
//                e.printStackTrace();
//                System.out.println(field.getName()+",type:"+field.getType()+",value:"+map.get(field.getName())+",错误:"+e.getMessage());
			}
		}
		return obj;
	}
    public static Map<String, Object> beanToMap2(Object obj) {
        Map<String, Object> params = new HashMap<>();
        try {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                params.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            System.out.println("对象转换为Map时报错:"+e.getMessage());
        }
        return params;
    }
	/**
	 * @param src
	 *            源字符串
	 * @return 字符串，将src的第一个字母转换为大写，src为空时返回null
	 */
	public static String change(String src) {
		if (src != null) {
			StringBuffer sb = new StringBuffer(src);
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
			return sb.toString();
		} else {
			return null;
		}
	}

    public static String getSubStr(String str){
        str=str.replaceAll("\\D",",");
        String[] strs=str.split(",");
        List<String> values=new ArrayList<>();
        for(String s:strs){
            if(!"".equals(s)){
                values.add(s);
            }
        }
       return values.get(values.size()-1);
    }

    /**
     *  将PDF转换成base64编码
     *  1.使用BufferedInputStream和FileInputStream从File指定的文件中读取内容；
     *  2.然后建立写入到ByteArrayOutputStream底层输出流对象的缓冲输出流BufferedOutputStream
     *  3.底层输出流转换成字节数组，然后由BASE64Encoder的对象对流进行编码
     * */
   public static String getPDFBinary(File file) {
        FileInputStream fin =null;
        BufferedInputStream bin =null;
        ByteArrayOutputStream baos = null;
        BufferedOutputStream bout =null;
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            //建立读取文件的文件输出流
            fin = new FileInputStream(file);
            //在文件输出流上安装节点流（更大效率读取）
            bin = new BufferedInputStream(fin);
            // 创建一个新的 byte 数组输出流，它具有指定大小的缓冲区容量
            baos = new ByteArrayOutputStream();
            //创建一个新的缓冲输出流，以将数据写入指定的底层输出流
            bout = new BufferedOutputStream(baos);
            byte[] buffer = new byte[1024];
            int len = bin.read(buffer);
            while(len != -1){
                bout.write(buffer, 0, len);
                len = bin.read(buffer);
            }
            //刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
            bout.flush();
            byte[] bytes = baos.toByteArray();
            //sun公司的API
            return encoder.encodeBuffer(bytes).trim();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fin.close();
                bin.close();
                baos.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将base64编码转换成PDF
     * @param base64sString
     * 1.使用BASE64Decoder对编码的字符串解码成字节数组
     *  2.使用底层输入流ByteArrayInputStream对象从字节数组中获取数据；
     *  3.建立从底层输入流中读取数据的BufferedInputStream缓冲输出流对象；
     *  4.使用BufferedOutputStream和FileOutputSteam输出数据到指定的文件中
     */
   public static void base64StringToPDF(String base64sString,String filePath){
        BufferedInputStream bin = null;
        FileOutputStream fout = null;
        BufferedOutputStream bout = null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
        //将base64编码的字符串解码成字节数组
        byte[] bytes = decoder.decodeBuffer(base64sString);
            //byte[] bytes = Base64.decodeBase64(base64sString);
            //创建一个将bytes作为其缓冲区的ByteArrayInputStream对象
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            //创建从底层输入流中读取数据的缓冲输入流对象
            bin = new BufferedInputStream(bais);
            //指定输出的文件http://m.nvzi91.cn/nxby/29355.html
            File file = new File(filePath);
            //创建到指定文件的输出流
            fout  = new FileOutputStream(file);
            //为文件输出流对接缓冲输出流对象
            bout = new BufferedOutputStream(fout);
            byte[] buffers = new byte[1024];
            int len = bin.read(buffers);
            while(len != -1){
                bout.write(buffers, 0, len);
                len = bin.read(buffers);
            }
            //刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
            bout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
            bin.close();
                fout.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String toString(Object obj) {
        return toString(obj, null);
    }
    public static String toString(Object obj, String def) {
        return obj == null ? (def == null ? "" : def) : obj.toString();
    }

    public static String getMapStringValue(Map<String, Object> map, String key) {
        return getMapStringValue(map, key, null);
    }

    public static String getMapStringValue(Map<String, Object> map, String key, String val) {

        if (map.get(key) != null) {
            return toString(map.get(key), val);
        }

        String s1 = key.toUpperCase();
        if (map.get(s1) != null) {
            return toString(map.get(s1), val);
        }

        String s2 = key.toLowerCase();
        if (map.get(s2) != null) {
            return toString(map.get(s2), val);
        }
        return val == null ? "" : val;
    }

    /**
     * 获取1-number(包括1、number在内的随机正式)
     * @param number
     * @return
     */
    public static Integer getRandom(Integer number){
        if(number==null){
            return 0;
        }
        Random ran=new Random();
        Integer month=ran.nextInt(number)+1;
        return month;
    }

    /**
     * 将多个文件打包成zip包
     * @param fileList
     * @param zipName
     * @param zipSavePath
     * @return
     */
    public static int toZipUtil(List<String> fileList,String zipName,String zipSavePath,OutputStream os) {
        //判断文件夹是否存在，不存在则创建文件夹
        File zipFile = new File(zipSavePath);
        if( !zipFile.exists()){
            zipFile.mkdirs();
        }
        int i = 0;
        ZipOutputStream zos =null;
        try {
            String save_zip = zipSavePath + File.separator + zipName + ".zip";
            if(os==null){
                os = new FileOutputStream(save_zip);
            }
            BufferedOutputStream bos = new BufferedOutputStream(os);
            zos=new ZipOutputStream(bos);
            zos.setEncoding("UTF-8");
            zos.setMethod(ZipOutputStream.DEFLATED);
            for (String filePath : fileList) {
                File file = new File(filePath);
                if(file.exists()){
                    String basePath = null;
                    if (file.isDirectory()) {
                        basePath = file.getPath();
                    } else {//直接压缩单个文件时，取父目录
                        basePath = file.getParent();
                    }
                    i = zipFile(file, basePath, zos , i);
                }else{
                    return 0;
                }
            }
            zos.closeEntry();
            zos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(zos!=null){
                    zos.flush();
                    zos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    /**
     * 功能：执行文件压缩成zip文件
     *
     * @param source
     * @param basePath 待压缩文件根目录
     * @param zos
     */

    private static int zipFile(File source, String basePath,
                               ZipOutputStream zos , int i) {
        File[] files = new File[0];
        if (source.isDirectory()) {
            files = source.listFiles();
        } else {
            files = new File[1];
            files[0] = source;
        }
        String pathName;//存相对路径(相对于待压缩的根目录)
        BufferedInputStream bis=null;
        byte[] buf = new byte[1024];
        int length = 0;
        try {
            for (File file : files) {
                if (file.isDirectory()) {
                    pathName = file.getPath().substring(basePath.length() + 1)
                            + "/";
                    zos.putNextEntry(new ZipEntry(pathName));
                    i = zipFile(file, basePath, zos , i);
                } else {
                    pathName = file.getPath().substring(basePath.length() + 1);
                    InputStream is = new FileInputStream(file);
                    bis = new BufferedInputStream(is);
                    zos.putNextEntry(new ZipEntry(pathName));
                    while ((length = bis.read(buf)) > 0) {
                        zos.write(buf, 0, length);
                    }
                    is.close();
                    i++;
                    file.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(bis!=null){
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
    //按照文件大小排序
    public static List<File> orderByLength(String fliePath) {
        List< File> files = Arrays.asList(new File(fliePath).listFiles());
        Collections.sort(files, new Comparator< File>() {
            public int compare(File f1, File f2) {
                long diff = f1.length() - f2.length();
                if (diff > 0)
                    return 1;
                else if (diff == 0)
                    return 0;
                else
                    return -1;
            }
            public boolean equals(Object obj) {
                return true;
            }
        });
        return files;
    }
    //按照文件名称排序
    public static List<File> orderByName(String fliePath) {
        List<File> files = Arrays.asList(new File(fliePath).listFiles());
        Collections.sort(files, new Comparator< File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile())
                    return -1;
                if (o1.isFile() && o2.isDirectory())
                    return 1;
                return o1.getName().compareTo(o2.getName());
            }
        });
        return files;
    }
    //按日期排序
    public static List<File> orderByDate(String fliePath) {
        File file = new File(fliePath);
        File[] fs = file.listFiles();
        Arrays.sort(fs,new Comparator< File>(){
            public int compare(File f1, File f2) {
                long diff = f1.lastModified() - f2.lastModified();
                if (diff > 0)
                    return 1;
                else if (diff == 0)
                    return 0;
                else
                    return -1;
            }
            public boolean equals(Object obj) {
                return true;
            }

        });
        return Arrays.asList(fs);
    }

    public static String replaceAllRex(String rex,String str,String mbStr){
        Pattern p = Pattern.compile("["+rex+"]");
        Matcher matcher = p.matcher(str);
        String result = matcher.replaceAll(mbStr);
        return result;
    }

    public static void copyObjectToNewObject(Object oldObj,Object newObj)throws Exception{
        Field[] fields1=oldObj.getClass().getDeclaredFields();
        Field[] fields2=newObj.getClass().getDeclaredFields();
        for(Field f1:fields1){
            for(Field f2:fields2){
                if(f1.getName().equals(f2.getName())&&f1.getType()==f2.getType()){
                    f1.setAccessible(true);
                    f2.setAccessible(true);
                    f1.set(newObj,f2.get(oldObj));
                }
            }
        }
    }

    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 实体类转Map共通方法
     *
     * @param bean 实体类
     * @return Map
     * @throws Exception
     */
    public static LinkedHashMap convertBean(Object bean) throws Exception {
        Class type = bean.getClass();
        LinkedHashMap returnMap = new LinkedHashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    public static List<LinkedHashMap> convertBeanList(List list) throws Exception {
        List<LinkedHashMap> list1 = new ArrayList<>();
        for (Object o : list) {
            list1.add(convertBean(o));
        }
        return list1;
    }
}
