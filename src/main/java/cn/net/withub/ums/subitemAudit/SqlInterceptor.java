package cn.net.withub.ums.subitemAudit;

import cn.net.withub.ums.webService.IOException;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import java.io.File;
import java.util.Map;

public class SqlInterceptor extends MethodFilterInterceptor {

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        Map<String, Object> parameters = invocation.getInvocationContext().getParameters();

        if (parameters.size() > 0) {
            StringBuilder sql = new StringBuilder("");
            for (String key : parameters.keySet()) {
                try {
                    Object o = parameters.get(key);
                    // 文件放过去
                    if(o instanceof  File[]){
                        continue;
                    }
                    if(o instanceof String[]){
                        String[] vx = (String[])o;
                        for (String s : vx) {
                            //GMT+ 放过
                            if(s.toUpperCase().contains("GMT+") ||  s.toLowerCase().contains("user-defined")){
                                continue;
                            }
                            sql.append("_").append(s);
                        }
                    }else{
                        //GMT+ 放过  user-defined 放过
                        if(o.toString().toUpperCase().contains("GMT+") ||  o.toString().toLowerCase().contains("user-defined")){
                            continue;
                        }

                        sql.append("_").append(o.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sqlValidate(sql.toString())) {
                System.out.println(sql.toString());
                return null;
            }
        }

        return invocation.invoke();
    }

    private static boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
        // 排除 or  ,  %
        String badStr = "and|exec|execute|insert|select|delete|update|count|drop|*|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|+|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|count|*|" +
                "chr|mid|master|truncate|char|declare|;|--|+|like|#";//过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        String[] params = str.split("_");
        for (int i = 0; i < badStrs.length; i++) {
            for (int j = 0; j < params.length; j++){
                if(badStrs[i].equals(params[j])) return  true;
            }
        }
        //or 需要存在空格
        if(str.contains("or ")){
            return true;
        }

        return false;
    }
}
