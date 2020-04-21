package com.example.app.mapper;

import com.example.app.entity.Banner;
import org.apache.ibatis.annotations.Select;

import java.util.*;

public interface BannerMapper {

    /**
     * 查询轮播图（id,theme，url）
     * @return
     */
    @Select("SELECT * FROM banner")
    List<Banner> select();
}
