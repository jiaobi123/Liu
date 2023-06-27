package com.demo.movie.controller.admin;

import com.demo.movie.init.InitSystem;
import com.demo.movie.model.FriendlyLink;
import com.demo.movie.model.MovieDetail;
import com.demo.movie.service.FriendlyLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/friendly_link")
public class FriendlyLinkAdminController {
    @Autowired
    FriendlyLinkService friendlyLinkService;
    @Autowired
    private InitSystem initSystem;
    @PostMapping("/list")
    public Map<String,Object> queryFriendlyLink(FriendlyLink friendlyLink,@RequestParam(value = "page",required = false)Integer page,
                                                @RequestParam(value = "rows",required = false)Integer rows){
        //根据友情链接名查询链接
        List<FriendlyLink> friendlyLinkList=friendlyLinkService.list(friendlyLink,page,rows);
        //根据友情链接名查询符合条件的友情链接数
        int total=friendlyLinkService.queryTotalCount(friendlyLink);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("rows",friendlyLinkList);
        resultMap.put("total",total);
        return resultMap;

    }
    @PostMapping("/delete")
    public Map<String,Object> delete(@RequestParam("ids") String ids, HttpServletRequest request) {
        String[] idStr = ids.split(",");
        //是否删除成功
        boolean idDeleteSuccess = true;
        for (String id : idStr) {
            int friendlyLinkId = Integer.parseInt(id);
            //根据id删除友情链接
            boolean success=friendlyLinkService.deleteFriendlyLink(friendlyLinkId);
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
    public Map<String,Object> saveMovieDetail(FriendlyLink friendlyLink, HttpServletRequest request){
        //保存友情链接到数据库
        boolean success=friendlyLinkService.save(friendlyLink);
        //重载全局信息
        initSystem.loadData(request.getServletContext());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", success);
        return resultMap;

    }

}
