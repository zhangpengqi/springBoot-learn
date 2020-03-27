package com.example.article.controller;

import com.example.article.entity.Article;
import com.example.article.mapper.ArticleMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ArticleController {
@Autowired
ArticleMapper articleMapper;
    @GetMapping("/")
    public String home(Model model){
        List<Article> articleList = articleMapper.findArticles();
        model.addAttribute("articleList",articleList);
        return "home";
    }
    @GetMapping("/detail")
    public  String detail(Model model, HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Article article = articleMapper.findArticleById(id);
        model.addAttribute("article",article);
        return "detail";
    }
}
