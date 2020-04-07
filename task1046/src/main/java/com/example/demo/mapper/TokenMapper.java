package com.example.demo.mapper;

import com.example.demo.entity.Token;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TokenMapper {
    /**
     * 根据token查询用户id
     * @param token 用户查询的token
     * @return 用户id，不存在返回0
     */
    @Select("SELECT * FROM token WHERE token=#{token}")
    Token selectByToken(String token);


    /**
     * 向token表，插入token
     * @param token
     */
    @Insert("INSERT INTO token (id,token) values (#{id},#{token}) ")
    void insertToken( Token token);


    /**
     * 更新token值
     * @param token id和token值
     * @return 更新行数
     */
    @Update("UPDATE token SET token=#{token} WHERE id=#{id}")
    int updateToken(Token token);
}
