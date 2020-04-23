package com.cmd.movierecommend.dal.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Movie {

    //电影id
    private Integer movieId;

    private String movieName;

    private Date releaseTime;

    private String director;

    private String leadActors;

    private String picture;

    private Double aveRating;

    private Integer numRating;

    private String description;

    private String typeList;

//    Date currentTime = new Date();
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String dateString = formatter.format(currentTime);
}
