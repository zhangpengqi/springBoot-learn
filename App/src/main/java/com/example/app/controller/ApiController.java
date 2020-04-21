package com.example.app.controller;

import com.example.app.entity.Banner;
import com.example.app.entity.Nav;
import com.example.app.mapper.BannerMapper;
import com.example.app.mapper.NavMapper;
import com.example.app.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ApiController {

    @Autowired
    BannerMapper bannerMapper;
    @Autowired
    NavMapper navMapper;

    /**
     * 轮播图
     * @return
     */
    @GetMapping("/api/banner")
    public JsonResult<List<Banner>> banner(){
        List bannerList = bannerMapper.select();
        if(bannerList==null){
            return new JsonResult("ERROR","没有banner信息",null);
        }
        Map<String,Object> data=new HashMap<>();
        data.put("bannerList",bannerList);
        return new JsonResult("SUCCESS",null,data);
    }


    /**
     * 分类页的分类
     * @return
     */
    @GetMapping("/api/nav")
    public JsonResult<List<Nav>> nav(){
        List navList = navMapper.select();
        if(navList==null){
            return new JsonResult("ERROR","没有banner信息",null);
        }
        Map<String,Object> data=new HashMap<>();
        data.put("navList",navList);
        return new JsonResult("SUCCESS",null,data);
    }

    /**
     * 首页的分类
     * @return
     */
    @GetMapping("/api/navhome")
    public JsonResult<List<Nav>> navHome(){
        List navList = navMapper.selectHome();
        if(navList==null){
            return new JsonResult("ERROR","没有banner信息",null);
        }
        Map<String,Object> data=new HashMap<>();
        data.put("navList",navList);
        return new JsonResult("SUCCESS",null,data);
    }
}
