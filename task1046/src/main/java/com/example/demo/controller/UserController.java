package com.example.demo.controller;

import com.example.demo.dto.SignRequest;
import com.example.demo.entity.Token;
import com.example.demo.entity.User;
import com.example.demo.mapper.TokenMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.CaptchaService;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin//跨域
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    TokenMapper tokenMapper;
    @Autowired
    CaptchaService captchaService;

    /**
     * 使用手机号注册
     * @param signRequest
     * @return
     */
    @PostMapping("/api/user/create")
    public JsonResult userCreate(@Valid SignRequest signRequest){
        //检验手机号和短信验证码是否正确
        boolean verifyIsTrue = captchaService.verify(signRequest.getMobile(), signRequest.getVerify());
        //输入的短信验证码等于服务端保存的验证码
        if(verifyIsTrue){
            //查询用户名是否注册
            if(userMapper.selectUserByMobile(signRequest.getMobile())==null){
                //没查到用户，用户名没注册
                //向user表插入user信息，拿到id
                User user=User
                        .builder()
                        .mobile(signRequest.getMobile())
                        .nickName(signRequest.getNickname())
                        .password(signRequest.getPassword())
                        .build();
                userMapper.insertUser(user);
                //生成token值
                String token=CaptchaService.getRandomString(32);
                //token查重
                while (tokenMapper.selectByToken(token)!=null){
                    CaptchaService.getRandomString(32);
                }
                //存入数据库
                tokenMapper.insertToken(new Token(user.getId(),token));
                Map<String,String> map=new HashMap<>();
                map.put("token",token);
                //向客户端返回信息
                return new JsonResult<Map<String,String>>("SUCCESS",null,map);
            }else {
                //用户名已注册
                return new JsonResult("ERROR","用户名已存在",null);
            }
        }else{
            //输入的短信验证码不等于服务端保存的验证码
            //短信验证码错误
            return new JsonResult<String>("ERROR","手机号、短信验证码错误",null);
        }
    }

    /**
     * 获取个人信息
     * @param token token值
     * @return
     */
    @GetMapping("/api/user/whoami")
    public JsonResult whoAmI(String token){
        User user=UserContext.getUser();
        Map<String,String> map=new HashMap<>();
        map.put("mobile",user.getMobile());
        map.put("nickname",user.getNickName());
        map.put("avatar_url",user.getAvatarUrl());
        return new JsonResult<Map<String,String>>("SUCCESS",null,map);
    }
}
