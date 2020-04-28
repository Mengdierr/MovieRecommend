package com.cmd.movierecommend.dal.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Movie {

    private Integer movieId;//电影id

    private String movieName;//电影名称

    private Date releaseTime;//上映时间

    private String director;//导演

    private String leadActors;//主演

    private String picture;//海报

    private Double aveRating;//平均评分

    private Integer numRating;//参与电影评分的人数

    private String description;//电影简介

    private String typeList;//电影类型

//    Date currentTime = new Date();
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String dateString = formatter.format(currentTime);
}
