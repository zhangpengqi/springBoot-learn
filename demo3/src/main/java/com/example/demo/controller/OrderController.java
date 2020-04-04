package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.interceptor.UserContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @GetMapping("/api/order/find")
    public String orders(){
        User user = UserContext.getUser();


    }
}
