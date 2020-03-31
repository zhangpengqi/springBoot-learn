package com.example.task1044.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@MapperScan("com.example.task1044.mapper")
public class Task1044Controller {
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("name","孙权");
        return "home";
    }
}


