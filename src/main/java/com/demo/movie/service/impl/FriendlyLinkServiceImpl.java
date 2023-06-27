package com.demo.movie.service.impl;

import com.demo.movie.mapper.FriendlyLinkCustomMapper;
import com.demo.movie.mapper.FriendlyLinkMapper;
import com.demo.movie.model.FriendlyLink;
import com.demo.movie.service.FriendlyLinkService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
@CacheConfig(cacheNames = {"friendlyLinkServiceImpl"})
public class FriendlyLinkServiceImpl implements FriendlyLinkService {
    @Autowired
    private FriendlyLinkMapper friendlyLinkMapper;
    @Autowired
    private FriendlyLinkCustomMapper friendlyLinkCustomMapper;
    @Override
    @Cacheable(key = "targetClass+methodName+#p0+#p1")
    public List<FriendlyLink> getAllFriendlyLinkList(Integer page, Integer pageSize) {
        if(page==null){
            page=1;
        }
        if (pageSize==null){
            pageSize=10;
        }
        PageHelper.startPage(page,pageSize);
        Example example=new Example(FriendlyLink.class);
        example.setOrderByClause("sort");
        return friendlyLinkMapper.selectByExample(example);
    }

    @Override
    @Cacheable(key = "targetClass + methodName + #p0.name + #p0.url + #p1 + #p2")
    public List<FriendlyLink> list(FriendlyLink friendlyLink, Integer page, Integer pageSize) {
        //进行分页
        PageHelper.startPage(page,pageSize);
        return friendlyLinkCustomMapper.list(friendlyLink);
    }

    @Override
    @Cacheable(key = "targetClass + methodName + #p0.name + #p0.url")
    public int queryTotalCount(FriendlyLink friendlyLink) {
        return friendlyLinkCustomMapper.queryTotalCount(friendlyLink);
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean save(FriendlyLink friendlyLink) {
        //id为空时,插入友情链接
        if(friendlyLink.getId()==null){
            return friendlyLinkMapper.insertSelective(friendlyLink)>=1;
        }


        return friendlyLinkMapper.updateByPrimaryKey(friendlyLink)>=1;
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean deleteFriendlyLink(Integer friendlyLinkId) {
        return friendlyLinkMapper.deleteByPrimaryKey(friendlyLinkId)>=1;
    }
}
