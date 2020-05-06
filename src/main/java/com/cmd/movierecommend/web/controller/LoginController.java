package com.cmd.movierecommend.web.controller;

import com.cmd.movierecommend.common.DBHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class LoginController {

    @ResponseBody
    @RequestMapping("login")
    public String login(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        DBHelper dbHelper = new DBHelper("jdbc:mysql://localhost:3306/movierecommend?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Hongkong", "root", "123456");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String sql = "select * from user where username='" + username + "' and password='" + password + "'";
        ResultSet resultSet = dbHelper.excuteQuery(sql, new Object[]{});
//        return "login";//跳转

        if (resultSet.next() && !username.equals("")) {
            return username;
        } else {
            return "ERROR";
        }
    }
}
