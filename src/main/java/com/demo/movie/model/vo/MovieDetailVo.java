package com.demo.movie.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MovieDetailVo implements Serializable {
    private Integer id;//电影动态id
    private Integer movieId;//电影id
    private String info;//电影信息
    private String movieName;//电影名字
    private Integer websiteId;//网站id
    private String websiteName;//网站名字
    private String websiteUrl;//网站网址
    private String url;//电影网址
    private Date publishDate;//电影发布日期

}
