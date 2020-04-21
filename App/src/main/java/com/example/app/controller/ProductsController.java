package com.example.app.controller;

import com.example.app.entity.Product;
import com.example.app.mapper.ProductMapper;
import com.example.app.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductsController {
    @Autowired
    ProductMapper productMapper;

    /**
     * 获取商品信息id,name，price url，
     * @return
     */
    @GetMapping("/api/products/all")
    public JsonResult all(){

        List<Product> productList = productMapper.select();

        if(productList==null){
            return new JsonResult("ERROR","商品信息为null",null);
        }else{
            Map<String,List> map=new HashMap<>();
            map.put("productList",productList);
            return new JsonResult("SUCCESS",null,map);
        }
    }

    /**
     * 查找单个商品信息
     * @param productId 商品id
     * @return
     */
    @GetMapping("/api/products/{productId}")
    public JsonResult<Product> getProduct(@PathVariable int productId){
        Product product = productMapper.selectById(productId);

        if(product==null){
            return new JsonResult<Product>("ERROR","商品不存在",null);
        }else{
            return new JsonResult<Product>("SUCCESS",null,product);
        }
    }

    @GetMapping("/api/products/searchByName")
    public JsonResult searchByName(String name,HttpServletRequest request){
       //name为null
        if(name==null){
            return new JsonResult<Product>("ERROR","name不能为空",null);
        }
        //搜索结果为null
        List<Product> productList = productMapper.selectByName(name);
        if(productList==null){
            return new JsonResult<Product>("ERROR","没有以"+name+"为名的蔬菜",null);
        }
        //搜索结果不为null
        Map<String,List> data=new HashMap<>();
        data.put("productList",productList);
        return new JsonResult("SUCCESS", null,data);
    }
}
