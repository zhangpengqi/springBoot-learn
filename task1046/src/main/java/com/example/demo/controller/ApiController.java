package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.util.CodeUtil;
import com.example.demo.util.JsonResult;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@RestController
public class ApiController {
    @Autowired
    private Producer captchaProducer = null;

    /**
     * 获取验证码图片url和key
     * @param request
     * @return
     */
    @PostMapping("/api/captcha")
    public JsonResult captcha(HttpServletRequest request){
        String key = CodeUtil.getKey(32);
        HttpSession session= request.getSession();
        session.setAttribute("key",key);
        Map<String,String> map=new HashMap<>();
        map.put("key",key);
        map.put("url","http://localhost:8080/getCaptchaImage?key="+key);
        JsonResult<Map<String,String>> result=new JsonResult("SUCCESS",null,map);
        return result;
    }

    /**
     * 获取验证码图片
     * @param request
     * @param key
     * @param response
     * @throws Exception
     */
    @GetMapping("/getCaptchaImage")
    public JsonResult getCaptchaImage(HttpServletRequest request, String key, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("key").equals(key)){
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");
            //生成验证码
            String capthcaCode = captchaProducer.createText();
            session.setAttribute(KAPTCHA_SESSION_KEY, capthcaCode);
            //向客户端写出
            BufferedImage bi = captchaProducer.createImage(capthcaCode);
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            try {
                out.flush();
            } finally {
                out.close();
            }
        }else {
            return new JsonResult("ERROR","url无效",null);
        }
        return null;
    }


    /**
     * 获取短信验证码
     * @param request
     * @return
     */
    @PostMapping("/api/sms")
    public JsonResult sms(HttpServletRequest request) {
        //验证码无效
        if (!CodeUtil.checkVerifyCodeAndKey(request)) {
            return new JsonResult<String>("INVALID_CAPTCHA","验证码⽆效",null);
        } else {
            //验证码有效，生成短信验证码
            String sms = CodeUtil.getKey(6);
            HttpSession session=request.getSession();
            //生成的短信验证码和注册的手机号，一起存入服务器session中
            session.setAttribute(request.getParameter("mobile"),sms);
            return new JsonResult<String>("SUCCESS","发送成功[本次发送内容:"+sms+"。正式上线后没有此括号中的内容]",null);
        }
    }
}
