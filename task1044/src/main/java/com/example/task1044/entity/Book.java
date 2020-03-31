package com.example.task1044.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
@Data//get ,set,toString,hashCode,equals
@Builder//可以随意赋值，不是全参的时候，不用一个一个去set
@NoArgsConstructor//无参构造
@AllArgsConstructor//全参构造
public class Book {
    private int id;//图书id
    private String name;//图书名称
//    private BigDecimal price;//图书单价
    private String describe;//图书描述
//    private String status;//状态
    private Timestamp createdAt;//创建时间
}
