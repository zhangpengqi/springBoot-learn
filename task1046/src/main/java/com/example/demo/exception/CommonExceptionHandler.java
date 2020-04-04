package com.example.demo.exception;

import com.example.demo.util.JsonResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice//注解，这个类用于处理异常
public class CommonExceptionHandler {
    /**
     * 通用异常
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody//将错误信息返回前端
    public JsonResult exceptionHandler(Throwable e){
        e.printStackTrace();
        return new JsonResult("ERROR",e.getMessage(),null);
    }
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public JsonResult<String> exceptionHandler(BindException e){
        e.printStackTrace();
        return new JsonResult("ERROR",e.getBindingResult().getAllErrors().get(0).getDefaultMessage(),null);
    }
}
