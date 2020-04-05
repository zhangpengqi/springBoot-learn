package com.example.demo.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

public class CodeUtil {
        public static final String KEY= "KEY";//放到session中的key
    /**
     * 将获取到的前端参数转为string类型
     * @param request
     * @param key
     * @return
     */
    public static String getString(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            System.out.println("key");
            System.out.println(result);
            if(result != null) {
                result = result.trim();
            }
            if("".equals(result)) {
                result = null;
            }
            return result;
        }catch(Exception e) {
            return null;
        }
    }
    /**
     * 验证码校验
     * @param request
     * @return
     */
    public static boolean checkVerifyCodeAndKey(HttpServletRequest request) {
        //获取生成的验证码
        String verifyCodeExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //获取生成的key
        String verifyKeyExpected  = (String) request.getSession().getAttribute("key");
        //获取用户输入的验证码
        String captchaCode = CodeUtil.getString(request, "captcha_code");
        //获取用户输入的ke
        String captchaKey = CodeUtil.getString(request, "captcha_key");
        if(captchaCode == null ||!captchaCode.equals(verifyCodeExpected)) {
            return false;
        }
        if (captchaKey == null ||!captchaKey.equals(verifyKeyExpected)) {
            return false;
        }
        return true;
    }

    /**
     * 生成随机的key
     * @return
     */
    public static String getKey(int length){
        //length用户要求产生字符串的长度
        String randString=null;
        if(length>6){
            randString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";//随机产生数字与字母组合的字符串
        }
        if(length<=6){
            randString="0123456789";
        }
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(randString.length());
            sb.append(randString.charAt(number));
        }
        return sb.toString();
    }
}
