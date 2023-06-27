package com.demo.movie.init;

import com.demo.movie.model.FriendlyLink;
import com.demo.movie.model.Movie;
import com.demo.movie.model.Website;
import com.demo.movie.model.vo.MovieDetailVo;
import com.demo.movie.service.FriendlyLinkService;
import com.demo.movie.service.MovieDetailService;
import com.demo.movie.service.MovieService;
import com.demo.movie.service.WebsiteService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
@Component
//初始化加载数据
public class InitSystem implements ServletContextListener, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        loadData(sce.getServletContext());

    }

    public void loadData(ServletContext context) {
        MovieService movieService=applicationContext.getBean(MovieService.class);
        MovieDetailService movieDetailService=applicationContext.getBean(MovieDetailService.class);
        FriendlyLinkService friendlyLinkService=applicationContext.getBean(FriendlyLinkService.class);
        WebsiteService websiteService=applicationContext.getBean(WebsiteService.class);
        //首页热门电影列表
        List<Movie> hotMovieList=movieService.getHotMovieList(1,32);
        //侧栏热门
        List<Movie> HotMovieList=movieService.getHotMovieList(1,10);
        //最新动态电影列表
        List<MovieDetailVo> detailList=movieDetailService.getAllMovieDetailList(1,10);
        //最新电影列表
        List<Movie> newMovieList=movieService.getNewMovieList(1,10);
        //侧栏网站
        List<Website> websiteList=websiteService.getAllWebsiteList(1,10);
        //侧栏友情链接
        List<FriendlyLink> friendlyLinkList=friendlyLinkService.getAllFriendlyLinkList(1,10);
        //移除多余的记录
        hotMovieList=subList(hotMovieList,32);
        HotMovieList=subList(HotMovieList,10);
        detailList=subList(detailList,10);
        newMovieList=subList(newMovieList,10);
        websiteList=subList(websiteList,10);
        friendlyLinkList=subList(friendlyLinkList,10);
        context.setAttribute("newestIndexHotMovieList",hotMovieList);
        context.setAttribute("hotMovieList",HotMovieList);
        context.setAttribute("newestDetailList",detailList);
        context.setAttribute("newestMovieList",newMovieList);
        context.setAttribute("newestWebSiteList",websiteList);
        context.setAttribute("friendlyLinkList",friendlyLinkList);
    }
    private <T> List<T>  subList(List<T> list, int count) {
        if (list != null && list.size() > count) {
            return list.subList(0, count - 1);
        }
        return list;
    }

}


