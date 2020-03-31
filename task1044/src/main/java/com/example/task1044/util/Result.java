package com.example.task1044.util;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Result<T> {
    private String code;
    private String message;
    private T  data;
}
