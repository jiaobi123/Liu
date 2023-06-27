package com.demo.movie.service;

import com.demo.movie.model.Website;

import java.util.List;

public interface WebsiteService {
    List<Website> getAllWebsiteList(Integer page,Integer pageSize);//获取所有网站
    List<Website> list(Website website,Integer page,Integer pageSize);//根据条件分页查询网站信息
    int queryTotalCount(Website website);//查询符合条件的电影网站数
    boolean save(Website website);//上传电影网站
    boolean deleteWebsite(Integer websiteId);//根据Id删除网站信息

}
