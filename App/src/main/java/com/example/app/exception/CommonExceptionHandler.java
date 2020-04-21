package com.example.app.exception;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice//注解，这个类用于处理异常
public class CommonExceptionHandler {

    /**
     * 通用异常
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody//将错误信息返回前端
    public Map<String,String> exceptionHandler(Throwable e){
        Map<String,String> result=new HashMap<>();
        result.put("code","ERROR");
        result.put("message",e.getMessage());
        result.put("data",null);
        return result;
    }
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Map<String,String> exceptionHandler(BindException e){
        Map<String,String> result=new HashMap<>();
        result.put("code","ERROR");
        result.put("message",e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        result.put("data",null);
        e.printStackTrace();
        return result;
    }
}
