package com.example.task1044.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult<T> {
    private String code;
    private String message;
    private T data;

    public JsonResult(T data){
        if(data==null){
            this.code="ERROR";
            this.message="查找的信息不存在";
        }else{
            this.code="SUCCESS";
            this.data=data;
        }
    }
    public JsonResult(String code,String message){
        this.code=code;
        this.message=message;
        this.data=null;
    }
}
