package com.demo.movie.controller.admin;

import com.demo.movie.init.InitSystem;
import com.demo.movie.model.Movie;
import com.demo.movie.service.MovieDetailService;
import com.demo.movie.service.MovieService;
import com.demo.movie.utils.DateUtils;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.Detail;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("admin/movie")
public class MovieAdminController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieDetailService movieDetailService;
    @Autowired
    private InitSystem initSystem;
    @Value("${imageFilePath}")
    private String  imageFilePath;
    //
    @PostMapping("/list")
    public Map<String,Object> queryMovie(Movie movie, @RequestParam(value = "page",required = false)Integer page,
                                         @RequestParam(value = "rows",required = false)Integer rows){
        //根据电影名查询列表
        List<Movie> movieList=movieService.list(movie,page,rows);
        //符合条件的电影数
        int total=movieService.findTotalCount(movie);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("rows",movieList);
        resultMap.put("total",total);
        return resultMap;

    }
    //上传电影到数据库
    @PostMapping("/save")
    public Map<String,Object> saveMovie(Movie movie, @RequestParam("imageFile") MultipartFile file, HttpServletRequest request)
    throws IOException {
        if(file!=null &&!file.isEmpty()){
            //获取文件名
            String fileName=file.getOriginalFilename();
            //获取文件后缀名
            if(fileName!=null){
                String suffixName=fileName.substring(fileName.indexOf("."));
                String newFileName= DateUtils.getCurrentDateStr()+suffixName;
                //保存图片到本地
                FileUtils.copyInputStreamToFile(file.getInputStream(),new File(imageFilePath+newFileName));
                //设置电影图片名
                movie.setImageName(newFileName);
            }
        }
        //设置发布日期
        movie.setPublishDate(new Date());
        //保存电影到数据库
        Boolean success=movieService.save(movie);
        //全局刷新
        initSystem.loadData(request.getServletContext());
        //数据传输
       Map<String,Object> resultMap=new HashMap<>();
       resultMap.put("success",success);
       return resultMap;
    }
    @PostMapping("/delete")
    public Map<String,Object> delete(@RequestParam("ids") String ids,HttpServletRequest request){
        String[] idStr=ids.split(",");
        //是否删除成功
        boolean idDeleteSuccess=true;
        for (String id:idStr){
            int movieId=Integer.parseInt(id);
            // 当电影被电影详情表引用时，不删除该电影
            if (movieDetailService.getByMovieId(movieId).size() > 0) {
                idDeleteSuccess = false;
            } else {
                // 删除指定id的电影
                movieService.deleteMovie(movieId);
            }
        }
        //重载全局信息
        initSystem.loadData(request.getServletContext());
        Map<String,Object> resultMap=new HashMap<>();
        if(idDeleteSuccess){
            resultMap.put("success",resultMap);
        }
        else {
            resultMap.put("success",false);
            resultMap.put("errorInfo","电影动态信息存在电影信息，不能删除");
        }
        return resultMap;
    }
@PostMapping("/findById")
    public Movie findById(Integer id){
        return movieService.findById(id);
}
@PostMapping("/comboList")
    public List<Movie> combolist(String q){
        Movie movie=new Movie();
        movie.setName(q);
        //根据分页查询电影
    return movieService.list(movie,1,20);

}
    @RequestMapping("/ckeditorUpload")
    public String ckeditorUpload(@RequestParam("upload")MultipartFile file,String CKEditorFuncNum)throws Exception{
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(Objects.requireNonNull(fileName).lastIndexOf("."));
        String newFileName = DateUtils.getCurrentDateStr()+suffixName;
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath+newFileName));

        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction("+ CKEditorFuncNum + ",'" +"/static/movieImage/"+ newFileName + "','')");
        sb.append("</script>");
        return sb.toString();
    }
}
