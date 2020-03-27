package com.example.demo.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//来自lombok的注解，生成get、set访问器和构造方法
@NoArgsConstructor//无参构造方法
@AllArgsConstructor//全参构造方法
public class Book {
    private int id;
    private String name;
    private int categoryId;
    private int quantity;
    private String price;
    private String isbn;
}
