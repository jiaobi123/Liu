package com.demo.movie.service;

import com.demo.movie.model.Movie;

import java.util.List;

//电影服务接口
public interface MovieService {
    //分页查询所有电影列表
    List<Movie> getAllMovieList(Integer page,Integer pageSize);//页面，页面大小
    //分页查询热门电影列表
    List<Movie> getHotMovieList(Integer page,Integer pageSize);
    //分页查询最新电影列表
    List<Movie> getNewMovieList(Integer page,Integer pageSize);
    //根据Id获取电影详细信息
    Movie getMovieById(Integer id);
    //根据id获取上一部电影
    Movie getLast(Integer id);
    //根据id获取下一部电影
    Movie getNext(Integer id);
    //生成随机电影列表
    List<Movie> randomList(Integer count);
    //获得记录条数
    Integer getTotalCount();
    //根据电影名查询电影
    List<Movie> findMovieByName(String movieName,Integer page,Integer pageSize);
    //查询符合条件的电影数
    int findTotalCount(Movie movie);
    //保存电影到数据库
    boolean save(Movie movie);
    //根据条件分页查询电影
    List<Movie> list(Movie movie,Integer page,Integer pageSize);
    //根据id删除电影
    boolean deleteMovie(int movieId);
    //根据id查询电影
    Movie findById(Integer movieId);
}
