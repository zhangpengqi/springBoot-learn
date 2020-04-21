package com.example.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ShoppingCart {

    private int id;
    private int userId;
    private int productId;
    private int number;
    private Timestamp createTime;

    public ShoppingCart(int userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public ShoppingCart(int userId, int productId, int number) {
        this.userId = userId;
        this.productId = productId;
        this.number = number;
    }
}
