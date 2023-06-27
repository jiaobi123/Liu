package com.demo.movie.controller;

import com.demo.movie.model.Website;
import com.demo.movie.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("website")
public class WebsiteController {
    @Autowired
    private WebsiteService websiteService;
    @GetMapping("/list/{page}")
    public ModelAndView list(@PathVariable(value = "page",required = false)Integer page){
        //分页查询网站
        List<Website> websiteList=websiteService.getAllWebsiteList(page,20);
        ModelAndView mv=new ModelAndView("index");
        mv.addObject("websiteList",websiteList);
        mv.addObject("title","电影网站列表");
        mv.addObject("mainPage","website/website_list");
        mv.addObject("fragment","website_list");
        return mv;
    }
}
