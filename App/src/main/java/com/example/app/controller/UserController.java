package com.example.app.controller;

import com.example.app.dto.SignRequest;
import com.example.app.entity.Token;
import com.example.app.entity.User;
import com.example.app.mapper.TokenMapper;
import com.example.app.mapper.UserMapper;
import com.example.app.service.CaptchaService;
import com.example.app.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    TokenMapper tokenMapper;
    /**
     * 使用手机号注册
     * @param signRequest
     * @return
     */
    @PostMapping("/user/create")
    public JsonResult userCreate(@Valid SignRequest signRequest){


        //查询用户名是否注册
        if(userMapper.selectByMobile(signRequest.getMobile())==null){
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
            String token= CaptchaService.getRandomString(32);
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

    }


    /**
     * 获取个人信息
     * @param token token值
     * @return
     */
    @GetMapping("/user/whoami")
    public JsonResult whoAmI(String token){
        User user=UserContext.getUser();
        Map<String,Object> map=new HashMap<>();
        map.put("id",user.getId());
        map.put("mobile",user.getMobile());
        map.put("nickname",user.getNickName());
        map.put("avatar",user.getAvatar());
        return new JsonResult<Map<String,Object>>("SUCCESS",null,map);
    }
}
