package com.cmd.movierecommend.dal.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Movie {

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLeadActors() {
        return leadActors;
    }

    public void setLeadActors(String leadActors) {
        this.leadActors = leadActors;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getAveRating() {
        return aveRating;
    }

    public void setAveRating(Double aveRating) {
        this.aveRating = aveRating;
    }

    public Integer getNumRating() {
        return numRating;
    }

    public void setNumRating(Integer numRating) {
        this.numRating = numRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeList() {
        return typeList;
    }

    public void setTypeList(String typeList) {
        this.typeList = typeList;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

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

    private Integer num;//星星序号

//    Date currentTime = new Date();
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String dateString = formatter.format(currentTime);
}
