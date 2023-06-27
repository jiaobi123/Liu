package com.demo.movie.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class IndexAdminController {
    //跳转到添加电影界面
    @GetMapping("/addMovie")
    public String addMovie(){
        return "admin/addMovie";
    }
    //跳转到动态管理界面
    @GetMapping("/detailManage")
    public String detailManage(){
        return "admin/detailManage";
    }
    //跳转到友链管理
    @GetMapping("/linkManage")
    public  String linkManage(){
        return "admin/linkManage";
    }
    //跳转到电影管理
    @GetMapping("/movieManage")
    public String movieManage(){
        return "admin/movieManage";
    }
    //跳转到修改电影界面
    @GetMapping("/modifyMovie")
    public String modifyMovie(){
        return "admin/modifyMovie";
    }
    //跳转到网站管理界面
    @GetMapping("/websiteManage")
    public String websiteManage(){
        return "admin/websiteManage";
    }
}
