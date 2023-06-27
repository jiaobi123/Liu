package com.demo.movie.service.impl;

import com.demo.movie.mapper.WebsiteCustomMapper;
import com.demo.movie.mapper.WebsiteMapper;
import com.demo.movie.model.Movie;
import com.demo.movie.model.Website;
import com.demo.movie.service.WebsiteService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
@CacheConfig(cacheNames = {"websiteServiceImpl"})
public class WebsiteServiceImpl implements WebsiteService {
    @Autowired
    private WebsiteMapper websiteMapper;
    @Autowired
    private WebsiteCustomMapper websiteCustomMapper;
    //分页查询所有的电影网站
    @Override

    public List<Website> getAllWebsiteList(Integer page, Integer pageSize) {
        if (page==null){
            page=1;
        }
        if (pageSize==null){
            pageSize=10;
        }
        PageHelper.startPage(page,pageSize);
        Example example=new Example(Movie.class);
        example.setOrderByClause("id");
        return  websiteMapper.selectByExample(example);

    }

    @Override
    @Cacheable(key = "targetClass + methodName + #p0.name + #p0.url + #p1 + #p2")
    public List<Website> list(Website website, Integer page, Integer pageSize) {
        //进行分页
        PageHelper.startPage(page,pageSize);
        return websiteCustomMapper.list(website);
    }

    @Override
    @Cacheable(key = "targetClass + methodName + #p0.name + #p0.url")
    public int queryTotalCount(Website website) {
        return websiteCustomMapper.queryTotalCount(website);
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean save(Website website) {
       //id为空时
        if(website.getId()==null){
            return websiteMapper.insertSelective(website)>=1;
        }
        return websiteMapper.updateByPrimaryKey(website)>=1;
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean deleteWebsite(Integer websiteId) {
        return websiteMapper.deleteByPrimaryKey(websiteId)>=1;
    }
}
