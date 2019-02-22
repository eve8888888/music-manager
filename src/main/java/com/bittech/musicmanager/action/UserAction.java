package com.bittech.musicmanager.action;

import com.bittech.musicmanager.entity.User;
import com.bittech.musicmanager.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
     *
     * @param request  request
     * @param response response
     */
    @RequestMapping("/login.action")
    public void userLogin(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String userEmail = request.getParameter("user_email");
        String userPassword = request.getParameter("user_pwd");
        System.out.println(userEmail);
        System.out.println(userPassword);
        if (ius.userLogin(userEmail, userPassword)) {
            try {
                session.setAttribute("user", userEmail);
                System.out.println("这是Session===========" + session);
                response.sendRedirect("showmusic.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        response.setContentType("text/html; charset=UTF-8"); //转码
        try {
            PrintWriter out = response.getWriter();
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
    public void userRegister(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String name = request.getParameter("Name");
        String email = request.getParameter("Email");
        String password = request.getParameter("Password");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        if (ius.userRegister(user)) {
            session.setAttribute("user", email);
            response.setContentType("text/html; charset=UTF-8"); //转码
            try {
                PrintWriter out = response.getWriter();
                out.println("<script language='javascript'>\n" +
                        "alert('注册成功!');\n" +
                        "location.href='showmusic.html'\n" +
                        "</script>");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            response.setContentType("text/html; charset=UTF-8"); //转码
            try {
                PrintWriter out = response.getWriter();
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

    @RequestMapping("/quit.action")
    public void userLogout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        response.setContentType("text/html; charset=UTF-8"); //转码
        try {
            PrintWriter out = response.getWriter();
            out.println("<script language='javascript'>\n" +
                    "location.href='http://localhost:8090/index.html'\n" +
                    "</script>");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findAllUser.action")
    public @ResponseBody
    List<User> findAllUser() {
        return ius.findAllUser();
    }

    @RequestMapping("/deleteUser.action")
    public @ResponseBody
    String deleteUser(String ids) {
        ius.deleteUser(ids);
        return "删除成功";
    }

    @RequestMapping("/updateUser.action")
    public @ResponseBody
    String updateDirector(User user) {
        if(ius.checkUser(user)) {
            ius.updateUser(user);
            return "修改成功";
        }
        return "邮箱已存在";
    }

    @RequestMapping("/addUser.action")
    public @ResponseBody
    String addDirector(User user) {

        if(ius.checkUser(user)){
            ius.addUser(user);
            return "添加成功";
        }
        return "邮箱已存在";
    }
}
