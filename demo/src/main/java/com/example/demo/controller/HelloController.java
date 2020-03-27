package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


//告诉浏览器，这是一个控制器

/**
 * 控制器，由它决定显示哪些模板
 */
@Controller
public class HelloController {

    @Autowired//框架自动注入一个mapper实现的对象，接口里的实现类具体方法就可以直接调用
    BookMapper bookMapper;
    @GetMapping("/")
    public String home(Model model){
        List<Book> bookList=bookMapper.findBooks();
//        model.addAttribute("name","java编程思想");
//        model.addAttribute("price",100);
//        List<Book> bookList=new ArrayList();
//        bookList.add(new Book(7,"物理",1,3,"4","sdf"));
//        bookList.add(new Book(8,"物理",1,3,"4","sdf"));
        //数据装入model中，model带人模板中。
        model.addAttribute("bookList",bookList);
        return "home";
    }
    @GetMapping("/detail")
    public String detail(Model model, HttpServletRequest request){
        int id= Integer.parseInt(request.getParameter("id"));
        Book book=bookMapper.findBookById(id);
        model.addAttribute("book",book);
        return "detail";
    }
}
