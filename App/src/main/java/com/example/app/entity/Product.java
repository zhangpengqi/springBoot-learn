package com.example.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Product {
    private int id;//商品Id
    private String name;//商品名
    private String describe;//商品描述
    private BigDecimal price;//商品价格
    private int navId;//所属类别id
    private int detailId;//详情id
    private String url;//商品图片
    private String status;//商品状态，1为上架，0位下架
    private int stock;//库存
    private String weight;//商品重量
    private String condition;//保存条件
    private String expiryDate;//保质期

    public Product(int id, String name, BigDecimal price, String url) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.url = url;
    }
}
