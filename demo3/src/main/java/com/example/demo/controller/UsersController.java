package com.example.demo.controller;

import com.example.demo.dto.UsersCreateRequest;
import com.example.demo.entity.User;
import com.example.demo.interceptor.UserContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UsersController {
    @PostMapping("api/users/create")
    //@Valid是验证器，验证条件在dto里面写着
   public String create(@Valid UsersCreateRequest usersCreateRequest){
        System.out.println(usersCreateRequest);
        return "api/users/create";
   }
   @GetMapping("/api/users/whoami")
   public User whoami(String token){
        //查token表，得到一个userId

       User user= UserContext.getUser();
       return user;
   }

}
