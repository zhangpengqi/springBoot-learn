package com.example.demo.exception;
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
    @ExceptionHandler(Throwable.class)//注解，指定能处理什么样的错误，Throwable.class是异常的基类，处理所有异常
    @ResponseBody//将错误信息返回前段
    public Map<String,String> exceptionHandler(Throwable e){
        Map<String,String> result=new HashMap();
        result.put("code","ERROR");
        result.put("message",e.getMessage());
        result.put("data",null);
        e.printStackTrace();
        return result;
    }

    /**
     *  绑定异常
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)//注解，只能处理参数绑定的错误。导包：import org.springframework.validation.BindException;
    @ResponseBody//将错误信息返回前端
    public Map<String,String> exceptionHandler(BindException e){
        Map<String,String> result=new HashMap();
        result.put("code","ERROR");
        result.put("message",e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        result.put("data",null);
        e.printStackTrace();
        return result;
    }
}
