package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UsersCreateRequest {
    @NotNull//不能为null，有key没value可以通过
    /*UsersCreateRequest(username=, password=)
    UsersCreateRequest(username=, password=null)
    */
    @NotEmpty//不能为空，需要key和value，value为空格可以通过
    @NotBlank//不能为空，且会删除前后空格，看是否为空
    private String username;
    private String password;
    @Email//验证邮箱
    private String email;
    @Max(value = 100,message = "年龄不能大于100")//最大值
    @Min(1)//最小值
    private int age;
}
