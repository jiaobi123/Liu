package com.demo.movie.mapper;

import com.demo.movie.model.Movie;
import com.demo.movie.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieCustomMapper extends MyMapper<Movie> {
    //生成电影随机列表
    List<Movie> randomList(Integer count);
    //查询符合条件的电影数量
    int findTotalCount(Movie movie);
    List<Movie> list(Movie movie);

}
