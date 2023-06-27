package com.demo.movie.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class MovieDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//电影动态id
    private String info;//电影信息
    @Column(name = "movie_id")
    private Integer movieId;//电影id
    @Column(name = "website_id")
    private Integer websiteId;//网站id
    private String url;//电影网站地址
    @Column(name = "publish_date")
    private Date publishDate;//电影发布日期


}
