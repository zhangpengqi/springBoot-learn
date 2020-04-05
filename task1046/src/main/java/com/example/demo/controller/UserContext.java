package com.example.demo.controller;

import com.example.demo.entity.User;

public class UserContext {
    private static User currentUser;
    public static void setUser(User user){
        currentUser=user;
    }
    public static User getUser(){
        return currentUser;
    }
}
