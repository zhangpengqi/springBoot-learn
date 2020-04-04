package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class TokenRequest {
    @NotBlank(message = "token不能为空")
    private String token;
}
