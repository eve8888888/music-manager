package com.bittech.musicmanager.SessionFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: Eve
 * @Date: 2019/2/21 13:42
 * @Version 1.0
 */

@WebFilter(filterName = "sessionFilter", urlPatterns = {"/*"})
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        System.out.println("filter url:" + uri);
        //是否需要过滤
        //boolean needFilter = isNeedFilter(uri);

        if (uri.equals("/index.html") || uri.equals("/login.action")
                || uri.startsWith("/bootstrap") || uri.startsWith("/css") || uri.startsWith("/assets")) { //不需要过滤直接传给下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        } else { //需要过滤器
            // session中包含user对象,则是登录状态
            if (session != null && session.getAttribute("user") != null) {
                // System.out.println("user:"+session.getAttribute("user"));
                filterChain.doFilter(request, response);
            } else {
                response.setContentType("text/html; charset=UTF-8"); //转码
                try {
                    PrintWriter out = response.getWriter();
                    out.println("<script language='javascript'>\n" +
                            "alert('您还未登录!');\n" +
                            "location.href='http://localhost:8090/index.html'\n" +
                            "</script>");
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
