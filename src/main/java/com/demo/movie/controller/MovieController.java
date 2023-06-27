package com.demo.movie.controller;

import com.demo.movie.model.Movie;
import com.demo.movie.model.vo.MovieDetailVo;
import com.demo.movie.service.MovieDetailService;
import com.demo.movie.service.MovieService;
import com.demo.movie.utils.PageUtils;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieDetailService movieDetailService;
    @RequestMapping("/search")
    public ModelAndView search(@Valid Movie movie, BindingResult bindingResult){
        ModelAndView mv=new ModelAndView("index");
        if(bindingResult.hasErrors()){
            // 发生错误则跳转到首页
            mv.addObject("error", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            mv.addObject("title", "首页");
            mv.addObject("mainPage", "movie/indexMovie");
            mv.addObject("fragment", "indexMovie");
            mv.addObject("movie", movie);
        }
        else {
            //跳转到搜索页面
            String movieName= movie.getName();
            //根据电影名查询电影列表
            List<Movie> movieList=movieService.findMovieByName(movieName,1,32);
            mv.addObject("title",movieName);
            mv.addObject("movieList",movieList);
            mv.addObject("mainPage","movie/search_result");
            mv.addObject("fragment","search_result");
            mv.addObject("movie",movie);
            mv.addObject("total",movieList.size());
        }
        return mv;

    }
    @GetMapping("/list/{page}")
    public ModelAndView list(@PathVariable(value = "page",required = false)Integer page){
        int total=movieService.getTotalCount();
        //分页查询电影
        List<Movie> movieList=movieService.getAllMovieList(page,20);
        ModelAndView mv=new ModelAndView("index");
        mv.addObject("movieList",movieList);
        mv.addObject("pageCode", PageUtils.generatePagination("/movie/list",total, page, 20));
        mv.addObject("title","电影列表");
        mv.addObject("mainPage","movie/movie_list");
        mv.addObject("fragment","movie_list");
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView now(@PathVariable("id")Integer id){
        Movie movie=movieService.getMovieById(id);
        ModelAndView mv=new ModelAndView("index");
        mv.addObject("movie",movie);
        mv.addObject("title",movie.getTitle());
        mv.addObject("pageCode",this.UpAndDownPage(movieService.getLast(id),movieService.getNext(id)));
        mv.addObject("movieDetailList",movieDetailService.getByMovieId(id));
        mv.addObject("mainPage", "movie/detail");
        mv.addObject("fragment", "detail");
        return mv;
    }
    private String UpAndDownPage(Movie lastMovie,Movie nextMovie){
        StringBuilder pageCode=new StringBuilder();
        if(lastMovie==null||lastMovie.getId()==null){
            pageCode.append("<p>上一篇：没有了</p>");
        }else
        {
            pageCode.append(String.format("<p>上一篇：<a href='/movie/%s'>%s</a></p>", lastMovie.getId(), lastMovie.getTitle()));
        }
        if(nextMovie==null||nextMovie.getId()==null){
            pageCode.append("<p>下一篇：没有了</p>");
        }else {
            pageCode.append(String.format("<p>上一篇：<a href='/movie/%s'>%s</a></p>",nextMovie.getId(), nextMovie.getTitle()));
        }

        return pageCode.toString();
    }

}
