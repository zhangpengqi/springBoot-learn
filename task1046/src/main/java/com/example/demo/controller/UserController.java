package com.example.demo.controller;

import com.example.demo.dto.SignRequest;
import com.example.demo.entity.Token;
import com.example.demo.entity.User;
import com.example.demo.mapper.TokenMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.util.CodeUtil;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
     * @param request
     * @return
     */
    @PostMapping("/api/user/create")
    public JsonResult userCreate(@Valid SignRequest signRequest, HttpServletRequest request){
        //客户端输入的手机号
        String inputMobile= signRequest.getMobile();

        //客户端输入的短信验证码
        String inputSms=signRequest.getVerify();

        HttpSession session=request.getSession();
        //输入的短信验证码等于服务端保存的验证码
        if(inputSms.equals(session.getAttribute(inputMobile))){
            //查询用户名是否注册
            if(userMapper.selectUserByNickname(request.getParameter("nickname"))==null){
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
                String token=CodeUtil.getKey(32);
                //将token存入数据库
                //查询token是否存在
                //存在，重新生成token
                while (tokenMapper.selectByToken(token)!=null){
                    token=CodeUtil.getKey(32);
                }
                //不存在存入数据库
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

    @GetMapping("/api/user/whoami")
    public JsonResult whoAmI(String token){
        User user=UserContext.getUser();
        return new JsonResult<User>("SUCCESS",null,user);
    }
}
