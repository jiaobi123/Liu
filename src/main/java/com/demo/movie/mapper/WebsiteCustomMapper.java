package com.demo.movie.mapper;

import com.demo.movie.model.Website;
import com.demo.movie.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WebsiteCustomMapper extends MyMapper<Website> {
    int queryTotalCount(Website website);
    List<Website> list(Website website);

}
