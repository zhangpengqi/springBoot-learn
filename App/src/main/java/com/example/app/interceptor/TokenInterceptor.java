package com.example.app.interceptor;

import com.example.app.controller.UserContext;
import com.example.app.entity.Token;
import com.example.app.entity.User;
import com.example.app.mapper.TokenMapper;
import com.example.app.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    TokenMapper tokenMapper;

    @Autowired
    UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //拿到token
        String inputToken=request.getParameter("token");
        //去token表查，得到userId
        Token tokenId = tokenMapper.selectByToken(inputToken);
        //查不到Token
        if(tokenId==null){
            throw new RuntimeException("token无效");
        }
        //用id查user
        User user=userMapper.selectUserById(tokenId.getId());

        if(user==null){
            throw new RuntimeException("token无效");
        }
        UserContext.setUser(user);

        return true;
    }
}
