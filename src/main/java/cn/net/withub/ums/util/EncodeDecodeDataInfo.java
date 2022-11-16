package cn.net.withub.ums.util;

import cn.net.withub.PassUtil;
import cn.net.withub.ums.entity.UmsRmpsyJbxx;
import cn.net.withub.ums.entity.UmsUserInfo;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

//对身份证证和手机号进行加密 和 解码
public class EncodeDecodeDataInfo {

    //是否启用编码解码
    protected static boolean enableEncode = false;

    static {
        String propertiesPath = "pass.properties";
        init(propertiesPath);
    }

    protected static void init(String propertiesPath) {
        InputStream resourceAsStream = null;
        Properties properties = null;
        try {
            ClassLoader classLoader = EncodeDecodeDataInfo.class.getClassLoader();
            resourceAsStream = classLoader.getResourceAsStream(propertiesPath);
            properties = new Properties();
            properties.load(resourceAsStream);
            String property = properties.getProperty("enable.encode");
            if (StringUtils.hasText(property)) {
                enableEncode = Boolean.parseBoolean(property);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //给字段编码
    public static void enCodeDataForUms(UmsUserInfo userInfo) {
        if (!enableEncode) {
            //不启用编码解码
            return;
        }
        List<String> fields = Arrays.asList("idcard", "phoneNumber");
        try {
            for (String field : fields) {
                Field declaredField = userInfo.getClass().getDeclaredField(field);
                declaredField.setAccessible(true);
                Object o = declaredField.get(userInfo);
                if (o != null) {
                    //只支持 String 类型
                    String sourceF = (String) o;
                    String toEncodeStr = EncodeDecodeDataInfo.encodeData(sourceF);
                    declaredField.set(userInfo, toEncodeStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deCodeDataForUms(UmsUserInfo userInfo) {
        if (!enableEncode) {
            //不启用编码解码
            return;
        }
        List<String> fields = Arrays.asList("idcard", "phoneNumber");
        try {
            for (String field : fields) {
                Field declaredField = userInfo.getClass().getDeclaredField(field);
                declaredField.setAccessible(true);
                Object o = declaredField.get(userInfo);
                if (o != null) {
                    //只支持 String 类型
                    String sourceF = (String) o;
                    String toEncodeStr = EncodeDecodeDataInfo.decodeData(sourceF);
                    declaredField.set(userInfo, toEncodeStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enCodeDataForUmsRmpsy(UmsRmpsyJbxx userInfo) {
        if (!enableEncode) {
            //不启用编码解码
            return;
        }
        List<String> fields = Arrays.asList("idcard", "phonenum");
        try {
            for (String field : fields) {
                Field declaredField = userInfo.getClass().getDeclaredField(field);
                declaredField.setAccessible(true);
                Object o = declaredField.get(userInfo);
                if (o != null) {
                    //只支持 String 类型
                    String sourceF = (String) o;
                    String toEncodeStr = EncodeDecodeDataInfo.encodeData(sourceF);
                    declaredField.set(userInfo, toEncodeStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deCodeDataForUmsRmpsy(UmsRmpsyJbxx userInfo) {
        if (!enableEncode) {
            //不启用编码解码
            return;
        }
        List<String> fields = Arrays.asList("idcard", "phonenum");
        try {
            for (String field : fields) {
                Field declaredField = userInfo.getClass().getDeclaredField(field);
                declaredField.setAccessible(true);
                Object o = declaredField.get(userInfo);
                if (o != null) {
                    //只支持 String 类型
                    String sourceF = (String) o;
                    String toEncodeStr = EncodeDecodeDataInfo.decodeData(sourceF);
                    declaredField.set(userInfo, toEncodeStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encodeData(String info) {
return info;
 /*       if (info != null) {
            return PassUtil.passSm4(info);
        }
        return null; */
//        if(info != null){
//            info +="_";
//        }
//        return info;
    }

    public static String decodeData(String info) {
return info;
      /*  if (info != null) {
            return PassUtil.decryptSm4(info);
        }
        return null;*/
//        if(info != null && info.lastIndexOf("_") == (info.length() - 1) ){
//            return info.substring(0,info.length() -1);
//        }
//        return info;
    }


}
