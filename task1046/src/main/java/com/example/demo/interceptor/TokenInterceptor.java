package com.example.demo.interceptor;

import com.example.demo.controller.UserContext;
import com.example.demo.entity.Token;
import com.example.demo.entity.User;
import com.example.demo.mapper.TokenMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用作token拦截的类，实现一个接口（HandlerInterceptor），重写一个方法（preHandle）
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    TokenMapper tokenMapper;
    @Autowired
    UserMapper userMapper;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String inputToken= request.getParameter("token");
        System.out.println(inputToken);

        if(inputToken!=null){
            Token token = tokenMapper.selectByToken(inputToken);
            if(token!=null){
                //查user
                System.out.println("token");
                System.out.println(token);
                User user=userMapper.selectUserById(token.getId());
                if(user!=null){
                    UserContext.setUser(user);
                    System.out.println(user);
                }else {
                    throw new RuntimeException("token无效");
                }
            }else {
                throw new RuntimeException("token无效");
            }
        }else {
            throw new RuntimeException("token不能为null");
        }
        return true;
    }
}
