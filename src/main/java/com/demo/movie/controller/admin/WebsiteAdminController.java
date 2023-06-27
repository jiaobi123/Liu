package com.demo.movie.controller.admin;

import com.demo.movie.init.InitSystem;
import com.demo.movie.model.Website;
import com.demo.movie.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/website")
public class WebsiteAdminController {
    @Autowired
    WebsiteService websiteService;
    @Autowired
    private InitSystem initSystem;
    @PostMapping("/list")
    public Map<String,Object> queryWebsite(Website website,@RequestParam(value = "page",required = false)Integer page,
                                           @RequestParam(value = "rows",required = false)Integer rows){
        //查询网站列表
        List<Website> websiteList=websiteService.list(website,page,rows);
        //查询符合条件的网站数
        int total=websiteService.queryTotalCount(website);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("rows",websiteList);
        resultMap.put("total",total);
        return resultMap;
    }
    @PostMapping("/comboList")
    public List<Website> combolist(String q){
        Website website=new Website();
        website.setName(q);
        //根据分页查询电影网站
        return websiteService.list(website,1,20);
    }
    @PostMapping("/delete")
    public Map<String,Object> delete(@RequestParam("ids") String ids, HttpServletRequest request){
        String[] idStr = ids.split(",");
        //是否删除成功
        boolean idDeleteSuccess = true;
        for (String id : idStr) {
            int websiteId = Integer.parseInt(id);
            //根据id删除电影动态
            boolean success=websiteService.deleteWebsite(websiteId);
            if(!success){
                idDeleteSuccess=false;
            }
        }
        //重载全局信息
        initSystem.loadData(request.getServletContext());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", idDeleteSuccess);
        return resultMap;
    }
    @PostMapping("/save")
    public Map<String,Object> saveWebsite(Website website,HttpServletRequest request){
        //保存电影网站
        boolean success=websiteService.save(website);
        //重载全局信息
        initSystem.loadData(request.getServletContext());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", success);
        return resultMap;
    }
}
