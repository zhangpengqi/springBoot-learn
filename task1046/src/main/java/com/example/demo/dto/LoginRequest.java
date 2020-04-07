package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Mod11Check;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "手机号不能为空")
    private String mobile;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "captcha_key不能为空")
    private String captcha_key;
    @NotBlank(message = "captcha_code不能为空")
    private String captcha_code;
}
