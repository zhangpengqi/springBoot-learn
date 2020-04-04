package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserRequest {
    @NotNull(message = "username不能为空")
    private String username;
    @NotNull
    private String password;
    @Email(message = "邮箱格式不对")
    private String email;
}
