package com.demo.movie.service.impl;

import com.demo.movie.mapper.MovieDetailCustomMapper;
import com.demo.movie.mapper.MovieDetailMapper;
import com.demo.movie.model.MovieDetail;
import com.demo.movie.model.vo.MovieDetailVo;
import com.demo.movie.service.MovieDetailService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@CacheConfig(cacheNames = {"movieDetailImpl"})
public class MovieDetailServiceImpl implements MovieDetailService {
    @Autowired
    private MovieDetailCustomMapper movieDetailCustomMapper;
    @Autowired
    private MovieDetailMapper movieDetailMapper;
    @Override
    @Cacheable(key = "targetClass+methodName+#p0+#p1")
    public List<MovieDetailVo> getAllMovieDetailList(Integer page, Integer pageSize) {
        if(page==null){
            page=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        PageHelper.startPage(page, pageSize);
        return movieDetailCustomMapper.getAllMovieDetailList();
    }

    @Override
    public List<MovieDetailVo> getByMovieId(Integer movieId) {
        return movieDetailCustomMapper.getByMovieId(movieId);
    }

    @Override
    public Integer getTotal() {
        return null;
    }

    @Override
    @Cacheable(key = "targetClass + methodName + #p0.movieId + #p0.websiteId + #p1 + #p2")
    public List<MovieDetailVo> list(MovieDetail movieDetail, Integer page, Integer pageSize) {
       //进行分页
        PageHelper.startPage(page,pageSize);
        return movieDetailCustomMapper.list(movieDetail);

    }

    @Override
    @Cacheable(key = "targetClass + methodName + #p0.movieId + #p0.websiteId")
    public int findTotalCount(MovieDetail movieDetail) {
        return movieDetailCustomMapper.findTotalCount(movieDetail);
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean deleteMovieDetail(int movieDetailId) {
        return movieDetailMapper.deleteByPrimaryKey(movieDetailId)>=1;
    }

    @Override
    // 方法执行后清空所有缓存
    @CacheEvict(allEntries = true)
    public boolean save(MovieDetail movieDetail) {
        if(movieDetail.getId()==null){
            return movieDetailMapper.insertSelective(movieDetail)>=1;
        }
        return movieDetailMapper.updateByPrimaryKey(movieDetail)>=1;
    }


}
