package com.example.demo.interceptor;

import com.example.demo.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component//(4)加一个注解
/**
 * 用作token拦截的类，（1）实现一个接口(HandlerInterceptor)，（2）重写一个方法(preHandle)
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //（3）request拿到token
        String token=request.getParameter("token");
        System.out.println(token);
        //去token表查，得到userId

        //拿着token去查userId
        //查不到，抛异常
        if(token==null){
        throw new RuntimeException("token无效");
        }
        //查到了，找地方存起来，控制器就可以直接用了。
        User user= User.builder().id(1).username("marry").build();
        UserContext.setUser(user);
        return true;
    }
}
