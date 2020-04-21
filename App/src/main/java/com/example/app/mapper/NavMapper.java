package com.example.app.mapper;

import com.example.app.entity.Nav;
import org.apache.ibatis.annotations.Select;

import java.util.*;

public interface NavMapper {

    /**
     * 获取分类中的导航栏信息
     * @return
     */
    @Select("SELECT * FROM nav")
    List<Nav> select();

    /**
     * 获取首页的导航栏信息，limit=10
     * @return
     */
    @Select("SELECT * FROM nav limit 10")
    List<Nav> selectHome();
}
