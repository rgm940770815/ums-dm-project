package cn.net.withub.ums.filter;

import cn.net.withub.ums.webService.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@WebFilter("/*")
public class SqlInjectionFilter implements Filter {
    public void destroy() {
        // TODO Auto-generated method stub
    }

    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }

    public void doFilter(ServletRequest args0, ServletResponse args1,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)args0;
        HttpServletResponse res=(HttpServletResponse)args1;
        //获得所有请求参数名
        Enumeration params = req.getParameterNames();
        String sql = "";
        while (params.hasMoreElements()) {
            //得到参数名
            String name = params.nextElement().toString();
            //System.out.println("name===========================" + name + "--");
            //得到参数对应值
            String[] value = req.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
                sql = sql + value[i];
            }
        }
        //System.out.println("============================SQL"+sql);
        //有sql关键字，跳转到error.html
        if (sqlValidate(sql)) {
            System.out.println(sql);
//            res.sendError(400,"系统检测到此操作存在异常，请联系系统管理员处理。");
            //String ip = req.getRemoteAddr();
            try {
                res.sendError(400,"系统检测到此操作存在异常，请联系系统管理员处理。");
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                chain.doFilter(args0,args1);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }

    //效验
    protected static boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|+|,|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
                "chr|mid|master|truncate|char|declare|or|;|--|+|,|like|//|/|%|#";//过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            str = str.replaceAll("/\\*.*\\*/"," ");
            if (str.indexOf(badStrs[i]+" ") >= 0) {
                System.out.println(badStrs[i]);
                return true;
            }
        }
        if(str.contains("<script>") || str.contains("<iframe>") || str.matches("'-\\s*[a-zA-z]+[0-9a-zA-Z]+.*-'")){
            return true;
        }
        return false;
    }
}
