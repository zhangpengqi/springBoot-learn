package com.example.demo.config;

import com.example.demo.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration//配置文件加一个注解
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired//(3)，注入刚写的拦截器
    TokenInterceptor tokenInterceptor;
    //添加拦截器
    //（2）重写一个方法(addInterceptors),添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //(4),拦截器添加到框架
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(tokenInterceptor);
        //(5),配置对哪些路径生效
        interceptorRegistration.addPathPatterns("/api/user/whoami")
                //错误页面、user注册页面、token创建页面需要排除
                .excludePathPatterns("/api/sms","/error","/api/captcha","/api/token/create");
    }
}
