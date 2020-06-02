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

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getTypeList() {
        return typeList;
    }

    public void setTypeList(String typeList) {
        this.typeList = typeList;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public String getRegions() {
        return regions;
    }

    public void setRegions(String regions) {
        this.regions = regions;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Float getRecommendnum() {
        return recommendnum;
    }

    public void setRecommendnum(Float recommendnum) {
        this.recommendnum = recommendnum;
    }

    private Integer movieId;//电影id

    private String movieName;//电影名称

    private String actors;//演员

    private String cover;//海报

    private String director;//导演

    private Double score;//豆瓣评分

    private String typeList;//电影类型

    private String language;//语言

    private Integer min;//时长

    private String regions;//制片地区

    private Date releaseTime;//上映时间

    private String description;//电影简介

    private String tags;//标签

    private Integer num;//星星序号

    private Float recommendnum;//推荐分数
//    Date currentTime = new Date();
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String dateString = formatter.format(currentTime);
}
