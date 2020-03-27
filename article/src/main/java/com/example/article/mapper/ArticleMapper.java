package com.example.article.mapper;

import com.example.article.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleMapper {
    @Select("SELECT * FROM article")
    List<Article> findArticles();

    @Select("SELECT * FROM article where id=#{id}")
    Article findArticleById(@Param("id") int id);
}
