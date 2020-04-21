package com.example.app.service;

import org.springframework.stereotype.Service;

/**
 * 图⽚验证码服务类
 *
 * @author 邹义良
 */
@Service
public class CaptchaService {

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