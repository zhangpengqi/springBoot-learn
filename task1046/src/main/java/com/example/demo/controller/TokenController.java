package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Token;
import com.example.demo.entity.User;
import com.example.demo.mapper.TokenMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.CaptchaService;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
@CrossOrigin//跨域
public class TokenController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    TokenMapper tokenMapper;
    @Autowired
    CaptchaService captchaService;
    /**
     * 登录
     */
    @PostMapping("/api/token/create")
    public JsonResult create(@Valid LoginRequest loginRequest){
        boolean isTrue=captchaService.verify(loginRequest.getCaptcha_key(),loginRequest.getCaptcha_code());
        //验证码无效
        if (!isTrue) {
            return new JsonResult<String>("INVALID_CAPTCHA","图片验证码不匹配",null);
        } else {
            //用手机号查用户
            User user = userMapper.selectUserByMobile(loginRequest.getMobile());
            if (user == null || !user.getPassword().equals(user.getPassword())) {
                // 用户不存在或密码不正确，返回前端错误信息
                return new JsonResult("ERROR", "账号、密码错误", null);
            }
            UserContext.setUser(user);
            //账号密码正确，更新token
            //生成随机token值
            String token =CaptchaService.getRandomString(32);
            //token查重
            while(tokenMapper.selectByToken(token)!=null){
                token=CaptchaService.getRandomString(32);
            }
            //更新token
            tokenMapper.updateToken(new Token(user.getId(), token));
            return new JsonResult<Token>("SUCCESS", null, new Token(user.getId(), token));
        }
    }
}
