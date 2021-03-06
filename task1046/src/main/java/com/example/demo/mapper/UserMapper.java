package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    /**
     * 根据nickname查找用户是否存在
     * @param mobile 手机号
     * @return 用户信息，不存在返回null
     */
    @Select("SELECT * FROM user WHERE mobile=#{mobile}")
    User selectUserByMobile(String mobile);

    /**
     * 根据id查user
     * @return user信息
     */
    @Select("SELECT * FROM user WHERE id=#{id}")
    User selectUserById(int id);

    /**
     * 注册用户
     * @param user 用户个人信息
     */
    @Insert("INSERT INTO user (mobile,nickName,password) values (#{mobile},#{nickName},#{password})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertUser(User user);
}