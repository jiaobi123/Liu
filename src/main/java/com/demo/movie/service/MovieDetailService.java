package com.demo.movie.service;

import com.demo.movie.model.Movie;
import com.demo.movie.model.MovieDetail;
import com.demo.movie.model.vo.MovieDetailVo;

import java.util.List;

public interface MovieDetailService {
    //分页查询电影动态列表
    List<MovieDetailVo> getAllMovieDetailList(Integer page,Integer pageSize);
    //根据电影id获取电影详细信息
    List<MovieDetailVo> getByMovieId(Integer movieId);
    Integer getTotal();
    //分页查询电影动态信息
    List<MovieDetailVo> list(MovieDetail movieDetail,Integer page,Integer pageSize);
    int findTotalCount(MovieDetail movieDetail);
    boolean deleteMovieDetail(int movieDetailId);
    //保存电影动态详情到数据库
    boolean save(MovieDetail movieDetail);

}
