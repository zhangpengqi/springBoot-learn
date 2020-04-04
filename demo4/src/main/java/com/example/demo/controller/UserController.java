package com.example.demo.controller;

import com.example.demo.dto.UserRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @GetMapping("/api/user/whoami")
    public String whoAmI(@Valid UserRequest userRequest){

        System.out.println(userRequest);
        return "jack";
    }
}
