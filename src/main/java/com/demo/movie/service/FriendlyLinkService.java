package com.demo.movie.service;

import com.demo.movie.model.FriendlyLink;

import java.util.List;

public interface FriendlyLinkService {
    //获取友情链接列表
    List<FriendlyLink> getAllFriendlyLinkList(Integer page,Integer pageSize);
    //分页查询友情链接
    List<FriendlyLink> list(FriendlyLink friendlyLink,Integer page,Integer pageSize);
    //查询符合条件的友情链接数
    int queryTotalCount(FriendlyLink friendlyLink);
    //上传友情链接
    boolean save(FriendlyLink friendlyLink);
    //根据id删除友情链接
    boolean deleteFriendlyLink(Integer friendlyLinkId);
}
