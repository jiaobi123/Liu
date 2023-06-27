package com.demo.movie.mapper;

import com.demo.movie.model.MovieDetail;
import com.demo.movie.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieDetailMapper extends MyMapper<MovieDetail> {
}
