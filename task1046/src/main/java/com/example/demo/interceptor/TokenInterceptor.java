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

        if(inputToken==null){
            throw new RuntimeException("token不能为null");
        }
        //用token查id
        Token token = tokenMapper.selectByToken(inputToken);
        if(token==null){
            throw new RuntimeException("token无效");
        }
        //用id查user
        User user=userMapper.selectUserById(token.getId());

        if(user==null){
            throw new RuntimeException("token无效");
        }
        UserContext.setUser(user);

        return true;
    }
}
