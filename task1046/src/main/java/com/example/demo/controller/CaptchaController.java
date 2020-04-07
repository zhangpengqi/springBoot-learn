package com.example.demo.controller;

import com.example.demo.service.CaptchaService;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 图⽚验证码
 */
@CrossOrigin//跨域
@RestController
public class CaptchaController {
    @Autowired
    CaptchaService captchaService;

    /**
     * 获取key和url
     * @param request
     * @return
     */
    @PostMapping("/api/captcha")
    public JsonResult create(HttpServletRequest request){
        String key = captchaService.create();
        StringBuffer url = new StringBuffer();
        url.append(request.getScheme());
        url.append("://");
        url.append(request.getServerName());
        url.append(":");
        url.append(request.getServerPort());
        url.append("/captcha/show/");
        url.append(key);
        Map<String, String> result = new HashMap<String, String>();
        result.put("key", key);
        result.put("url", url.toString());
        return new JsonResult<Map<String,String>>("SUCCESS",null,result);
    }

    /**
     * 获取验证码图片
     * @param key
     * @param response
     * @throws Exception
     */
    @GetMapping("/captcha/show/{key}")
    public void show(@PathVariable String key, HttpServletResponse response)
            throws Exception {
        byte[] captcha = captchaService.getImage(key, "png");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        ServletOutputStream sout = response.getOutputStream();
        sout.write(captcha);
        sout.flush();
        sout.close();
    }

    /**
     * 获取短信验证码
     * @param mobile  手机号
     * @param request
     * @return
     */
    @PostMapping("/api/sms")
    public JsonResult sms(String mobile,HttpServletRequest request){
        System.out.println(mobile);
        String key=request.getParameter("captcha_key");
        String code=request.getParameter("captcha_code");
        //检验验证码是否正确
        boolean isTrue=captchaService.verify(key,code);
        if(isTrue){
            String sms = captchaService.createSms(mobile);
            return new JsonResult("SUCCESS","发送成功[本次发送内容:"+sms+"。正式上线后没有此括号中的内容]",null);
        }else{
            //验证码错误
            return new JsonResult<String>("INVALID_CAPTCHA","验证码⽆效",null);
        }
    }
}