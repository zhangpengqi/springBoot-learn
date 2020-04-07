package com.example.demo.service;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * 图⽚验证码服务类
 *
 * @author 邹义良
 */
@Service
public class CaptchaService {
    @Autowired
    CacheManager cacheManager;
    /**
     * 验证码⼯具
     */
    @Autowired
    DefaultKaptcha defaultKaptcha;
    public String create() {
        String random = getRandomString(4); //验证码图⽚中的随机字符
        System.out.println(random);
        String key = getRandomString(32);
        Element element = new Element(key, random, 0, 600); //过期时间600秒
        cacheManager.getCache("captcha").put(element);
        return key;
    }
    public String createSms(String mobile){
        String sms=getRandomString(6);
        Element element = new Element(mobile, sms, 0, 600); //过期时间600秒
        cacheManager.getCache("captcha").put(element);
        return sms;
    }
    public byte[] getImage(String key, String formatName) throws Exception {
        String code = getCode(key);
        if (code == null) {
            throw new RuntimeException("INVALID_KEY");
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedImage bi = defaultKaptcha.createImage(code);
        ImageIO.write(bi, formatName, out);
        byte[] captcha = out.toByteArray();
        return captcha;
    }
    public boolean verify(String key, String code) {
        return verify(key, code, true);
    }
    public boolean verify(String key, String code, boolean ignoreCase) {
        String rightCode = getCode(key);
        if (rightCode == null) {
            return false;
        }
        if (ignoreCase) {
            return rightCode.toLowerCase().equals(code.toLowerCase());
        }
        return rightCode.equals(code);
    }
    protected String getCode(String key) {
        Element element = cacheManager.getCache("captcha").get(key);
        if (element == null) {
            return null;
        }
        return element.getObjectValue().toString();
    }
    public static String getRandomString(int stringLength) {
        String string =
                "abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stringLength; i++) {
            int r = (int) (Math.random() * 10000);
            int index = r % string.length();
            sb.append(string.charAt(index));
        }
        return sb.toString();
    }
}