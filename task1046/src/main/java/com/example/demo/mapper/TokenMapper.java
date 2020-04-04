package com.example.demo.mapper;


import com.example.demo.entity.Token;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TokenMapper {
    /**
     * 根据token查询用户id
     * @param token 用户查询的token
     * @return 用户id，不存在返回0
     */
    @Select("SELECT * FROM token WHERE token=#{token}")
    Token selectByToken(@Param("token") String token);


    /**
     * 向token表，插入token
     * @param token
     */
    @Insert("INSERT INTO token (id,token) values (#{token.id},#{token.token}) ")
    void insertToken(@Param("token") Token token);


}
