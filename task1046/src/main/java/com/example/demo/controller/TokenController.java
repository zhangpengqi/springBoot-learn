package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Token;
import com.example.demo.entity.User;
import com.example.demo.mapper.TokenMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.util.CodeUtil;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
@RestController
public class TokenController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    TokenMapper tokenMapper;
    @PostMapping("/api/token/create")
    public JsonResult create(@Valid LoginRequest loginRequest, HttpServletRequest request){

        System.out.println(loginRequest);
        //验证码无效
        if (!CodeUtil.checkVerifyCodeAndKey(request)) {
            return new JsonResult<String>("INVALID_CAPTCHA","图片验证码不匹配",null);
        } else {
            //用手机号查用户
            User user = userMapper.selectUserByNickname(loginRequest.getMobile());
            if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
                // 用户不存在或密码不正确，返回前端错误信息
                return new JsonResult("ERROR", "账号、密码错误", null);
            }
            UserContext.setUser(user);
            //账号密码正确，更新token
            //生成随机token值
            String token =CodeUtil.getKey(32);
            while(tokenMapper.selectByToken(token)!=null){
                token=CodeUtil.getKey(32);
            }
            //更新token
            tokenMapper.updateToken(new Token(user.getId(), token));
            return new JsonResult<Token>("SUCCESS", null, new Token(user.getId(), token));
        }
    }
}
