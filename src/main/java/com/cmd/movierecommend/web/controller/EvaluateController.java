package com.cmd.movierecommend.web.controller;

import com.cmd.movierecommend.common.DBHelper;
import com.cmd.movierecommend.dal.entity.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EvaluateController {

    //    @ResponseBody//返回字符串
    @RequestMapping("evaluate")
    public String toEvaluate(ModelMap modelMap, HttpServletRequest request) throws SQLException, ClassNotFoundException {

//        String keyword = request.getParameter("keyword");

//        modelMap.put("movies", movies)

        DBHelper dbHelper = new DBHelper("jdbc:mysql://localhost:3306/movierecommend?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Hongkong", "root", "0000");
        ResultSet resultSet = dbHelper.excuteQuery("select * from movieinfo", new Object[]{});

        List<Movie> movieList = new ArrayList<>();

        while (resultSet.next()) {
            Movie movie = new Movie();

//            movie.setMovieId(resultSet.getInt("movieid"));

            movie.setMovieName(resultSet.getString("moviename"));

            movie.setPicture(resultSet.getString("picture"));

            movieList.add(movie);
        }
//        modelMap.put("movies",movieList);

        //随机数生成
        int rng1=(int)(Math.random()*1000);//生成 [0,999]之间的随机整数。
        int rng2=(int)(Math.random()*1000);//生成 [0,999]之间的随机整数。
        int rng3=(int)(Math.random()*1000);//生成 [0,999]之间的随机整数。
        int rng4=(int)(Math.random()*1000);//生成 [0,999]之间的随机整数。
        int rng5=(int)(Math.random()*1000);//生成 [0,999]之间的随机整数。
        int rng6=(int)(Math.random()*1000);//生成 [0,999]之间的随机整数。
        int rng7=(int)(Math.random()*1000);//生成 [0,999]之间的随机整数。
        int rng8=(int)(Math.random()*1000);//生成 [0,999]之间的随机整数。

        List<Movie> rMovie=new ArrayList<>();

        Movie nMovie1=movieList.get(rng1);
        rMovie.add(nMovie1);
        Movie nMovie2=movieList.get(rng2);
        rMovie.add(nMovie2);
        Movie nMovie3=movieList.get(rng3);
        rMovie.add(nMovie3);
        Movie nMovie4=movieList.get(rng4);
        rMovie.add(nMovie4);
        Movie nMovie5=movieList.get(rng5);
        rMovie.add(nMovie5);
        Movie nMovie6=movieList.get(rng6);
        rMovie.add(nMovie6);
        Movie nMovie7=movieList.get(rng7);
        rMovie.add(nMovie7);
        Movie nMovie8=movieList.get(rng8);
        rMovie.add(nMovie8);



        modelMap.put("movies",rMovie);
        return "evaluate";
    }
}
