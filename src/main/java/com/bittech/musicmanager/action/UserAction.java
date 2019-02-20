package com.bittech.musicmanager.action;

import com.bittech.musicmanager.entity.User;
import com.bittech.musicmanager.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: Eve
 * @Date: 2019/2/20 11:55
 * @Version 1.0
 */

@Controller
public class UserAction {
    @Resource
    IUserService ius;

    /**
     * 实现用户登录
     * @param request request
     * @param response response
     */
    @RequestMapping("/login.action")
    public void  userLogin(HttpServletRequest request, HttpServletResponse response){
        String userEmail = request.getParameter("user_email");
        String userPassword = request.getParameter("user_pwd");
        if(ius.userLogin(userEmail,userPassword)){
            try {
                response.sendRedirect("showmusic.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        response.setContentType("text/html; charset=UTF-8"); //转码
        try {
            PrintWriter out   =   response.getWriter();
            out.println("<script language='javascript'>\n" +
                    "alert('用户名或密码不正确!');\n" +
                    "location.href='index.html'\n" +
                    "</script>");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/register.action")
    public void userRegister(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("Name");
        String email = request.getParameter("Email");
        String password = request.getParameter("Password");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        if(ius.userRegister(user)){
            response.setContentType("text/html; charset=UTF-8"); //转码
            try {
                PrintWriter out   =   response.getWriter();
                out.println("<script language='javascript'>\n" +
                        "alert('注册成功!');\n" +
                        "location.href='showmusic.html'\n" +
                        "</script>");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            response.setContentType("text/html; charset=UTF-8"); //转码
            try {
                PrintWriter out   =   response.getWriter();
                out.println("<script language='javascript'>\n" +
                        "alert('该邮箱已经存在!');\n" +
                        "location.href='index.html'\n" +
                        "</script>");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
