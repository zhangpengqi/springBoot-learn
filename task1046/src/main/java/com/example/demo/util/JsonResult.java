package com.example.demo.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JsonResult<T> {
    private String code;
    private String message;
    private T data;
}
