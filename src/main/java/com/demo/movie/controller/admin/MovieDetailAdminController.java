package com.demo.movie.controller.admin;

import com.demo.movie.init.InitSystem;
import com.demo.movie.model.Movie;
import com.demo.movie.model.MovieDetail;
import com.demo.movie.model.vo.MovieDetailVo;
import com.demo.movie.service.MovieDetailService;
import com.demo.movie.service.MovieService;
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
@RequestMapping("admin/movieDetail")
public class MovieDetailAdminController {
    @Autowired
    MovieDetailService movieDetailService;
    @Autowired
    MovieService movieService;
    @Autowired
    private InitSystem initSystem;
    @PostMapping("/list")
    public Map<String,Object> queryMovieDetail(MovieDetail movieDetail, @RequestParam(value = "page",required = false)Integer page,
                                               @RequestParam(value = "rows",required = false)Integer rows){
        //根据电影名查询电影列表
        List<MovieDetailVo> movieList=movieDetailService.list(movieDetail,page,rows);
        //根据电影名查询符合条件的电影动态数
        int total=movieDetailService.findTotalCount(movieDetail);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("rows",movieList);
        resultMap.put("total",total);
        return resultMap;
    }
    @PostMapping("/delete")
    public Map<String,Object> delete(@RequestParam("ids") String ids, HttpServletRequest request) {
        String[] idStr = ids.split(",");
        //是否删除成功
        boolean idDeleteSuccess = true;
        for (String id : idStr) {
            int movieDetailId = Integer.parseInt(id);
            //根据id删除电影动态
            boolean success=movieDetailService.deleteMovieDetail(movieDetailId);
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
    public Map<String,Object> saveMovieDetail(MovieDetail movieDetail, HttpServletRequest request){
        //更新发布日期
         movieDetail.setPublishDate(new Date());
         //保存电影动态到数据库
         boolean success=movieDetailService.save(movieDetail);
         //重载全局信息
         initSystem.loadData(request.getServletContext());

         Map<String, Object> resultMap = new HashMap<>();
         resultMap.put("success", success);
         return resultMap;

     }


}
