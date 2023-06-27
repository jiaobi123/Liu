package com.demo.movie.controller;

import com.demo.movie.model.Movie;
import com.demo.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public  class IndexController {
    @Autowired
    private MovieService movieService;
    @GetMapping("/index")
    public ModelAndView login(){
        //查询热门电影

        ModelAndView mv=new ModelAndView("index");
        mv.addObject("title","首页");
        mv.addObject("mainPage","movie/indexMovie");
        mv.addObject("fragment","indexMovie");
        return  mv;
    }
    @GetMapping("/aboutMe")
    public ModelAndView aboutMe() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("title", "关于本站");
        mv.addObject("mainPage", "common/aboutMe");
        mv.addObject("fragment", "aboutMe");
        return mv;
    }
    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }
    @GetMapping("/admin")
    public String toAdmin() {
        return "admin/main";
    }
}
