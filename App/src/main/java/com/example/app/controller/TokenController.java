package com.example.app.controller;

import com.example.app.dto.LoginRequest;
import com.example.app.entity.Token;
import com.example.app.entity.User;
import com.example.app.mapper.TokenMapper;
import com.example.app.mapper.UserMapper;
import com.example.app.service.CaptchaService;
import com.example.app.util.JsonResult;
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

    /**
     * 登录
     * @param request
     * @return
     */
    @PostMapping("/token/create")
    public JsonResult create(@Valid LoginRequest request){
        System.out.println(request);

        //拿到mobile查password
        User user = userMapper.selectByMobile(request.getMobile());
        if(user==null){
            //密码错误
            return new JsonResult("ERROR","账号、密码错误",null);
        }
        //判断密码是否正确
        //密码正确
        if(request.getPassword().equals(user.getPassword())){
            //创建token值
            UserContext.setUser(user);
            //账号密码正确，更新token
            //生成随机token值
            String token = CaptchaService.getRandomString(32);
            //token查重

            while(tokenMapper.selectByToken(token)!=null){
                token=CaptchaService.getRandomString(32);
            }
            //更新token
            tokenMapper.updateToken(new Token(user.getId(), token));
            return new JsonResult<Token>("SUCCESS", null, new Token(user.getId(), token));
        }else {
            //密码错误
            return new JsonResult("ERROR","账号、密码错误",null);
        }
    }


}
