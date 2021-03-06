package com.example.demo.interceptor;

import com.example.demo.entity.User;

public class UserContext {
    private static final ThreadLocal<User> currentUser=new ThreadLocal<User>();
    public static void setUser(User user){
        currentUser.set(user);
    }
    public static User getUser(){
        return currentUser.get();
    }
}
