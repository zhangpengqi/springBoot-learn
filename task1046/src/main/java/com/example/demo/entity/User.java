package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * 用户信息
 */
public class User {
    private int id;
    private String mobile;
    private String nickName;
    private String password;
    private String avatarUrl;
}
