package com.example.demo.util;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
@MapperScan("com.example.demo.mapper")
public class Text {

    public static void main(String[] args) {
        String code1=CodeUtil.getKey(6);
        System.out.println(code1);
        String code2=CodeUtil.getKey(32);
        System.out.println(code2);

    }
}
