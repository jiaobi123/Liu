package com.demo.movie.mapper;

import com.demo.movie.model.FriendlyLink;
import com.demo.movie.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendlyLinkCustomMapper extends MyMapper<FriendlyLink> {
    //分页查询友情链接
    List<FriendlyLink> list(FriendlyLink friendlyLink);
    //查询符合条件的友情链接数
    int queryTotalCount(FriendlyLink friendlyLink);
}
