package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
public class TokenController {
    @PostMapping("/api/token/create")
    public Map<String,String> create(String username,String password){

        //查user表，验证密码，得到userId
        //验证成功，生成一个随机的字符串，存到token表
        Map<String,String> result=new HashMap<>();
        result.put("code","SUCCESS");
        result.put("message",null);
        result.put("data","");
        return result;
    }
}
