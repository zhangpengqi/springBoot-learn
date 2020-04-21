package com.example.app.mapper;

import com.example.app.entity.Product;
import org.apache.ibatis.annotations.Select;

import java.util.*;

public interface ProductMapper {

    /**
     * 菜品信息(id,name,price,url)
     * @return
     */
    @Select("SELECT `id`,`name`,`price`,`url` FROM product")
    List<Product> select();

    /**
     * 按id搜索商品信息
     * @param id 商品id
     * @return
     */
    @Select("SELECT * FROM product WHERE id=#{id}")
    Product selectById(int id);

    /**
     * 按照名字模糊搜索product
     * @param name 查询的名字
     * @return
     */
    @Select("SELECT * FROM product WHERE name LIKE CONCAT('%',#{name},'%')")
    List<Product> selectByName(String name);
}
