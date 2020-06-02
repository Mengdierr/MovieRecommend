package com.cmd.movierecommend.web.controller;

import com.cmd.movierecommend.common.DBHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class RegisterController {

    @RequestMapping("register")
    public String register() {
        return "register";
    }

    @ResponseBody
    @RequestMapping("regAction")
    public String resAction(HttpServletRequest request) throws SQLException, ClassNotFoundException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");


//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(passwordConfirm);


        if (!password.equals(passwordConfirm)) {
            return "密码不一致";
//            System.out.println("密码不一致");
//            return "";
        }

        DBHelper dbHelper = new DBHelper("jdbc:mysql://localhost:3306/movierecommend?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Hongkong", "root", "0000");

        ResultSet rs = dbHelper.excuteQuery("select * from user where username = ? ", new Object[]{username});

//        System.out.println(rs);
        if (rs.next()) {
            dbHelper.close();
            return "用户名重复";
        }

        DBHelper dbHelper1 = new DBHelper("jdbc:mysql://localhost:3306/movierecommend?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Hongkong", "root", "0000");
        dbHelper1.excute("insert into user(username,password) values(?,?)", new Object[]{username, password});
        dbHelper1.close();

        return "注册成功";
    }
    @RequestMapping("index")
    public String index() {
        return "index";
    }

}
