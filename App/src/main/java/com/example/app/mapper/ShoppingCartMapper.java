package com.example.app.mapper;

import com.example.app.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.*;

public interface ShoppingCartMapper {

    /**
     * 根据用户id查找购物车信息
     * @param userId
     * @return
     */
    @Select("SELECT * FROM shopping_cart WHERE user_id=#{userId}")
    List<ShoppingCart> selectById(int userId);

    /**
     * 向购物车插入商品信息
     * @return
     */
    @Insert("INSERT INTO shopping_cart (`user_id`,`product_id`) values (#{userId},#{productId})")
    int add(ShoppingCart shoppingCart);

    /**
     * 更新购物车中商品数量
     * @param shoppingCart
     * @return
     */
    @Update("UPDATE shopping_cart SET number=#{number} WHERE `product_id`=#{productId} AND  `user_id`=#{userId}")
    int update(ShoppingCart shoppingCart);

    /**
     * 删除购物车中商品信息
     * @param shoppingCart
     * @return
     */
    @Delete("DELETE FROM shopping_cart WHERE  `product_id`=#{productId} AND `user_id`=#{userId}  ")
    int delete(ShoppingCart shoppingCart);
}
