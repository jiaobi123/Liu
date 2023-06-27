package com.demo.movie.mapper;

import com.demo.movie.model.Website;
import com.demo.movie.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WebsiteMapper extends MyMapper<Website> {
}
