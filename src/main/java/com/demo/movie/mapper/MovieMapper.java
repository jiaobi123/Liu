package com.demo.movie.mapper;

import com.demo.movie.model.Movie;
import com.demo.movie.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface MovieMapper extends MyMapper<Movie> {
}
