package com.example.app.controller;

import com.example.app.dto.AddCartRequest;
import com.example.app.dto.UpdateCartRequest;
import com.example.app.entity.ShoppingCart;
import com.example.app.entity.Product;
import com.example.app.entity.User;
import com.example.app.mapper.ShoppingCartMapper;
import com.example.app.mapper.ProductMapper;
import com.example.app.util.JsonResult;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ShoppingCartController {

    @Autowired
    ShoppingCartMapper shoppingCartMapper;
    @Autowired
    ProductMapper productMapper;

    /**
     * 查询购物车信息
     * @param token
     * @return
     */
    @GetMapping("/shoppingCart/select")
    public JsonResult select(String token){
        User user = UserContext.getUser();
        //查询购物车列表
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.selectById(user.getId());
        //购物车信息为null
        if(shoppingCartList ==null){
            return new JsonResult("ERROR","购物车信息为null",null);
        }
        //创建购物车列表中商品列表
        List productList=new ArrayList<>();
        //根据购物车列表中商品id查询商品详情，并添加至商品列表
        for(ShoppingCart shoppingCart : shoppingCartList){
            Product product = productMapper.selectById(shoppingCart.getProductId());
            Map<String,Object> data=new HashMap();
            data.put("product",product);
            data.put("number",shoppingCart.getNumber());

            productList.add(data);
        }
        Map<String,Object> data=new HashMap();
        data.put("user_id",user.getId());
        data.put("product_list",productList);
        return new JsonResult("SUCCESS",null,data);
    }

    /**
     * 购物车添加数据
     * @param product_id 商品id
     * @return
     */
    @PostMapping("/shoppingCart/add")
    public JsonResult<String> add(int product_id){
        //获取用户信息

        User user = UserContext.getUser();
        //向购物车数据库添加数据
        int AddNum = shoppingCartMapper.add(new ShoppingCart(user.getId(), product_id));
        if(AddNum>0){
            return new JsonResult<String>("SUCCESS","购物车添加成功",null);
        }else {
            return new JsonResult<String>("ERROR","购物车插入数据失败",null);
        }
    }

    /**
     * 更新购物车中商品数量
     * @param request
     * @return
     */
    @PostMapping("/shoppingCart/update")
    public JsonResult<String> update(@Valid UpdateCartRequest request){
        //获取用户信息
        User user = UserContext.getUser();
        //更新数据库中商品数量
        int updateNum = shoppingCartMapper.update(new ShoppingCart(user.getId(), request.getProduct_id(), request.getNumber()));

        if(updateNum>0){
            return new JsonResult<String>("SUCCESS","购物车更新成功",null);
        }else {
            return new JsonResult<String>("ERROR","购物车更新数据失败",null);
        }
    }
    @DeleteMapping("/shoppingCart/delete")
    public JsonResult<String> delete(int product_id){
        //获取用户信息
        User user = UserContext.getUser();
        //向购物车数据库添加数据
        int AddNum = shoppingCartMapper.delete(new ShoppingCart(user.getId(), product_id));
        if(AddNum>0){
            return new JsonResult<String>("SUCCESS","购物车商品删除成功",null);
        }else {
            return new JsonResult<String>("ERROR","购物车商品删除失败",null);
        }
    }
}
