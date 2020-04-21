package com.example.app.controller;

import com.example.app.entity.User;

public class UserContext {
    private static final ThreadLocal<User> currentUser=new ThreadLocal<>();

    public static void setUser(User user){
        currentUser.set(user);
    }
    public static User getUser(){
        return currentUser.get();
    }
}
