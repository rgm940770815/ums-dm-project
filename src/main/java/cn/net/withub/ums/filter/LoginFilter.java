package cn.net.withub.ums.filter;

import cn.net.withub.ums.common.UmsConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by D.Yang on 2014/12/24 0024.
 */
@WebFilter(urlPatterns = "/*") //暂时离线过滤器,用户名：admin，密码留空，法院：高院
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String path = request.getContextPath();
//
        if (session.getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString()) != null //已登录
                || request.getRequestURI().startsWith(path + "/login")
                || request.getRequestURI().startsWith(path + "/logout")
                || request.getRequestURI().startsWith(path + "/js") || request.getRequestURI().startsWith(path + "/css") || request.getRequestURI().startsWith(path + "/image") || request.getRequestURI().startsWith(path + "/lib") //静态资源
                || request.getRequestURI().startsWith(path + "/login.jsp") || request.getRequestURI().startsWith(path + "/soft")
                || request.getRequestURI().startsWith(path + "/cxf")
                || request.getRequestURI().indexOf("nagios_test.jsp") != -1
                ) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(path + "/login.jsp");
        }

    }

    @Override
    public void destroy() {

    }
}
