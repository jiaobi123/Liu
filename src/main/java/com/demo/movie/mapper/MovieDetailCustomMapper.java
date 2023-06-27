package com.demo.movie.mapper;

import com.demo.movie.model.Movie;
import com.demo.movie.model.MovieDetail;
import com.demo.movie.model.vo.MovieDetailVo;
import com.demo.movie.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieDetailCustomMapper extends MyMapper<MovieDetail> {
    /**
     * 查询所有电影动态列表
     * @return 最新电影动态列表
     */
    List<MovieDetailVo> getAllMovieDetailList();
    /**
     * 根据电影id获取电影详情信息
     * @param movieId 电影id
     * @return 电影详情信息列表
     */
    List<MovieDetailVo> getByMovieId(Integer movieId);
    List<MovieDetailVo> list(MovieDetail movieDetail);
    int findTotalCount(MovieDetail movieDetail);

}
