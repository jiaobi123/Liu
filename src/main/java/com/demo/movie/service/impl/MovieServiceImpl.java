package com.demo.movie.service.impl;

import com.demo.movie.mapper.MovieCustomMapper;
import com.demo.movie.mapper.MovieMapper;
import com.demo.movie.model.Movie;
import com.demo.movie.service.MovieService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"movieServiceImpl"})
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieCustomMapper movieCustomMapper;
    @Override
    @Cacheable(key = "targetClass+methodName+#p0+#p1")
    public List<Movie> getAllMovieList(Integer page, Integer pageSize) {
        if(page==null){
            page=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        //使用分页插件
        PageHelper.startPage(page,pageSize);
        //创建查询条件
        Example example=new Example(Movie.class);
        example.setOrderByClause("publish_date desc");

        return movieMapper.selectByExample(example);
    }

    @Override
    @Cacheable(key = "targetClass+methodName+#p0+#p1")
    public List<Movie> getHotMovieList(Integer page, Integer pageSize) {
        if(page==null){
            page=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        PageHelper.startPage(page,pageSize);
        Example example=new Example(Movie.class);
        example.setOrderByClause("publish_date desc");
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("hot",1);
        return movieMapper.selectByExample(example);
    }

    @Override
    @Cacheable(key = "targetClass + methodName + #p0 + #p1")
    public List<Movie> getNewMovieList(Integer page, Integer pageSize) {
        if (page==null){
            page=1;
        }
        if (pageSize==null){
            pageSize=10;
        }
        PageHelper.startPage(page,pageSize);
        Example example=new Example(Movie.class);
        example.setOrderByClause("publish_date desc");
        return movieMapper.selectByExample(example);
    }

    @Override
    @Cacheable(key = "targetClass + methodName + #p0")
    public Movie getMovieById(Integer id) {
        return movieMapper.selectByPrimaryKey(id);
    }

    @Override
    @Cacheable(key = "targetClass + methodName + #p0")
    public Movie getLast(Integer id) {
        return movieMapper.selectByPrimaryKey(id-1);
    }

    @Override
    public Movie getNext(Integer id) {
        return movieMapper.selectByPrimaryKey(id+1);
    }

    @Override
    public List<Movie> randomList(Integer count) {
        return movieCustomMapper.randomList(count);
    }

    @Override
    @Cacheable(key = "targetClass + methodName")
    public Integer getTotalCount() {
        return movieMapper.selectCount(null);
    }

    @Override
    @Cacheable(key = "targetClass + methodName + #p0 + #p1 + #p2")
    public List<Movie> findMovieByName(String movieName, Integer page, Integer pageSize) {
        if (page==null){
            page=1;
        }
        if (pageSize==null){
            pageSize=10;
        }
        //进行分页
        PageHelper.startPage(page,pageSize);
        //进行条件查询
        Example example=new Example(Movie.class);
        example.setOrderByClause("publish_date desc");
        Example.Criteria criteria=example.createCriteria();
        criteria.andLike("name","%"+movieName+"%");
        return movieMapper.selectByExample(example);
    }
    @Override
    @Cacheable(key = "targetClass + methodName")
    public int findTotalCount(Movie movie) {
        return movieCustomMapper.findTotalCount(movie);
    }

    @Override
    // 方法执行后清空所有缓存
    @CacheEvict(allEntries = true)
    public boolean save(Movie movie) {
        //id为空时，插入电影数据
        if(movie.getId()==null){
            return movieMapper.insertSelective(movie)>=1;
        }
        return movieMapper.updateByPrimaryKey(movie)>=1;
    }

    @Override
    @Cacheable(key = "targetClass + methodName + #p0.name + #p1 + #p2")
    public List<Movie> list(Movie movie, Integer page, Integer pageSize) {
        //进行分页
        PageHelper.startPage(page,pageSize);
        return movieCustomMapper.list(movie);

    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean deleteMovie(int movieId) {
        return movieMapper.deleteByPrimaryKey(movieId)>=1;
    }

    @Override
    @Cacheable(key = "targetClass + methodName + #p0")
    public Movie findById(Integer movieId) {
        return movieMapper.selectByPrimaryKey(movieId);
    }
}
