package com.example.app.mapper;

import com.example.app.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    /**
     * 根据id查user
     * @return user信息
     */
    @Select("SELECT * FROM user WHERE id=#{id}")
    User selectUserById(int id);

    /**
     * 根据手机号查User
     * @param mobile
     * @return
     */
    @Select("SELECT * FROM user WHERE mobile=#{mobile}")
    User selectByMobile(String mobile);

    /**
     * 注册用户
     * @param user 用户个人信息
     */
    @Insert("INSERT INTO user (mobile,nickName,password) values (#{mobile},#{nickName},#{password})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertUser(User user);
}
