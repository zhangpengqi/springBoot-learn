package com.example.demo.exception;

import org.apache.ibatis.binding.BindingException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Map<String,String> exceptionHandler(Throwable e){
        e.printStackTrace();
        Map<String,String> map=new HashMap<>();
        map.put("code","ERROR");
        map.put("message",e.getMessage());
        map.put("data",null);
        return map;
    }
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Map<String,String> exceptionHandler(BindException e){
        Map<String,String> map=new HashMap<>();
        map.put("code","ERROR");
        map.put("message",e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        map.put("data",null);
        return map;
    }
}
