package com.demo.movie.controller;

import com.demo.movie.model.MovieDetail;
import com.demo.movie.model.vo.MovieDetailVo;
import com.demo.movie.service.MovieDetailService;
import com.demo.movie.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("movieDetail")
public class MovieDetailController {
    @Autowired
    private MovieDetailService movieDetailService;
    @GetMapping("/list/{page}")
    public ModelAndView list(@PathVariable(value = "page",required = false)Integer page){
    List<MovieDetailVo>  movieDetailList=movieDetailService.getAllMovieDetailList(page,20);
    ModelAndView mv=new ModelAndView("index");
    mv.addObject("movieDetailList",movieDetailList);
    //mv.addObject("pageCode", PageUtils.generatePagination("/movieDetail/list", , page, 20));
    mv.addObject("title","电影网站动态信息列表");
    mv.addObject("mainPage","movieDetail/movie_detail_list");
    mv.addObject("fragment","movie_detail_list");
    return mv;
}
}
