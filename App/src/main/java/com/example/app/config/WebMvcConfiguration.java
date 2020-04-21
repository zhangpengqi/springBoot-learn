package com.example.app.config;

import com.example.app.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//(6),配置文件加一个注解
//(1)实现一个接口（WebMvcConfigurer）
public class WebMvcConfiguration implements WebMvcConfigurer {

    //(3),注入刚写的拦截器
    @Autowired
    TokenInterceptor tokenInterceptor;

    //(2),重写一个方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //(4),拦截器添加到框架
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(tokenInterceptor);

        //(5),配置对那些路径生效
        interceptorRegistration.addPathPatterns("/user/whoami","/shoppingCart/***");
    }
}
