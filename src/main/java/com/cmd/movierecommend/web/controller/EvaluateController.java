package com.cmd.movierecommend.web.controller;

import com.cmd.movierecommend.common.DBHelper;
import com.cmd.movierecommend.dal.entity.Movie;
//import org.apache.spark.launcher.SparkAppHandle;
//import org.apache.spark.launcher.SparkLauncher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EvaluateController {

    /**
     * dbHelper的全局变量调用函数
     */
    private DBHelper dbHelper() throws SQLException, ClassNotFoundException {
        return new DBHelper("jdbc:mysql://localhost:3306/movierecommend?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Hongkong", "root", "0000");
    }

    //    @ResponseBody//返回字符串
    @RequestMapping("evaluate")
    public String toEvaluate(ModelMap modelMap, HttpServletRequest request) throws SQLException, ClassNotFoundException {

//        String keyword = request.getParameter("keyword");

//        modelMap.put("movies", movies)

        //执行查询
        ResultSet resultSet = this.dbHelper().excuteQuery("select MOVIE_ID,NAME,COVER,RELEASE_DATE,DIRECTORS," +
                "ACTORS,GENRES,STORYLINE from movie", new Object[]{});
        //关掉连接
        this.dbHelper().close();
        List<Movie> movieList = new ArrayList<>();
        while (resultSet.next()) {
            Movie movie = new Movie();

            movie.setMovieId(resultSet.getInt("MOVIE_ID"));

            movie.setMovieName(resultSet.getString("NAME"));//电影名称

            movie.setCover(resultSet.getString("COVER"));//海报

            Date releasedate = resultSet.getDate("RELEASE_DATE");//上映时间
            if (releasedate == null) {
                movie.setReleaseTime(new Date("01/01/1970"));
            } else
                movie.setReleaseTime(releasedate);

            String director = resultSet.getString("DIRECTORS");//导演
            if (director == null) {
                movie.setDirector("未知");
            } else
                movie.setDirector(director);

            String actor = resultSet.getString("ACTORS");//演员
            if (actor == null) {
                movie.setActors("未知");
            } else
                movie.setActors(actor);//主演

            String type = resultSet.getString("GENRES");//演员
            if (type == null) {
                movie.setTypeList("未知");
            } else
                movie.setTypeList(type);

            movie.setDescription(resultSet.getString("STORYLINE"));//简介

            movieList.add(movie);
        }

//        modelMap.put("movies",movieList);//调用put movieList读取

        //随机数生成
        int rng1 = (int) (Math.random() * 1000);//生成 [0,999]之间的随机整数。
        int rng2 = (int) (Math.random() * 1000);//生成 [0,999]之间的随机整数。
        int rng3 = (int) (Math.random() * 1000);//生成 [0,999]之间的随机整数。
        int rng4 = (int) (Math.random() * 1000);//生成 [0,999]之间的随机整数。
        int rng5 = (int) (Math.random() * 1000);//生成 [0,999]之间的随机整数。
        int rng6 = (int) (Math.random() * 1000);//生成 [0,999]之间的随机整数。
        int rng7 = (int) (Math.random() * 1000);//生成 [0,999]之间的随机整数。
        int rng8 = (int) (Math.random() * 1000);//生成 [0,999]之间的随机整数。

        List<Movie> rMovie = new ArrayList<>();//存储随机数据rMovie

        Movie nMovie1 = movieList.get(rng1);
        rMovie.add(nMovie1);
        Movie nMovie2 = movieList.get(rng2);
        rMovie.add(nMovie2);
        Movie nMovie3 = movieList.get(rng3);
        rMovie.add(nMovie3);
        Movie nMovie4 = movieList.get(rng4);
        rMovie.add(nMovie4);
        Movie nMovie5 = movieList.get(rng5);
        rMovie.add(nMovie5);
        Movie nMovie6 = movieList.get(rng6);
        rMovie.add(nMovie6);
        Movie nMovie7 = movieList.get(rng7);
        rMovie.add(nMovie7);
        Movie nMovie8 = movieList.get(rng8);
        rMovie.add(nMovie8);

        for (int i = 1; i <= 8; i++) {
            rMovie.get(i - 1).setNum(i);
        }

        modelMap.put("movies", rMovie);//调用put将rMovie(随机8个数据)读取
        modelMap.put("username", request.getParameter("username"));//调用put将rMovie(随机8个数据)读取
        return "evaluate";  //@controller  return返回页面
    }

    @ResponseBody
    @RequestMapping("doEvaluate")
    public String doEvaluate(ModelMap modelMap, HttpServletRequest request) throws SQLException, ClassNotFoundException, IOException {
        String username = request.getParameterMap().get("data[username]")[0];
        String[] movieId = request.getParameterMap().get("data[movieId][]");
        String[] movieScore = request.getParameterMap().get("data[movieScore][]");


        //用username反相查询到userId
        ResultSet resultSet = this.dbHelper().excuteQuery("select userid from user where username = ?", new Object[]{username});
        this.dbHelper().close();
        //用户id
        int userId = 0;
        //时间戳
        String timestamp = String.valueOf(System.currentTimeMillis()).substring(0, 10);
        while (resultSet.next()) {
            userId = resultSet.getInt("userid");
        }

        //todo 删除该用户历史评分数据，为写入本次最新评分数据做准备
        String sql = "delete from personalratings where userid= ? ";
        Object[] objects = new Object[]{userId};
        this.dbHelper().excute(sql, objects);

        //todo 把每条评分记录(userid,movieid,rating,timestamp)插入数据库
        List<Object[]> evaluateDatas = new ArrayList<>();
        for (int i = 0; i < movieId.length; i++) {
            String ms = movieScore[i];
            if (!ms.equals("")) {
                int mId = Integer.parseInt(movieId[i]);
                int msi = Integer.parseInt(ms);
                evaluateDatas.add(new Object[]{userId, mId, msi, timestamp});
            }
        }

        for (Object[] ed : evaluateDatas) {
            this.dbHelper().excute("insert into personalratings values (?,?,?,?)", ed);
        }
        this.dbHelper().close();


        //todo 调用Spark程序为用户推荐电影并把推荐结果写入数据库,把推荐结果显示到网页

//        SparkAppHandle handler = new SparkLauncher()
//                .setSparkHome("/usr/local/spark")
//                .setAppResource("/home/anahian/jar/Film_Recommend_Dataframe.jar")
//                .setMainClass("recommend.MovieLensALS")
//                .setMaster("local")
//                .setConf(SparkLauncher.DRIVER_MEMORY, "2g")
//                .addAppArgs("input_spark/recommend_data/data",String.valueOf(userId))
//                .startApplication(new SparkAppHandle.Listener(){
//                    @Override
//                    public void stateChanged(SparkAppHandle handle) {
//                        System.out.println("**********  state  changed  **********");
//                    }
//
//                    @Override
//                    public void infoChanged(SparkAppHandle handle) {
//                        System.out.println("**********  info  changed  **********");
//                    }
//                });
//        while(!"FINISHED".equalsIgnoreCase(handler.getState().toString()) && !"FAILED".equalsIgnoreCase(handler.getState().toString())){
//            System.out.println("id    "+handler.getAppId());
//            System.out.println("state "+handler.getState());
//
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }




//        return "";
        return "success";
    }


}
