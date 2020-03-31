package com.example.task1044.controller;

import com.example.task1044.dto.BookRequest;
import com.example.task1044.entity.Book;
import com.example.task1044.mapper.BookMapper;
import com.example.task1044.util.JsonResult;
import com.example.task1044.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.sql.Timestamp;
import java.util.Date;

@RestController
@CrossOrigin
public class BookController {
    @Autowired
    BookMapper bookMapper;

    /**
     * 插入图书
     * @param name 图书名称
     * @param describe 图书详情
     * @return
     */
    @PostMapping("/books")
    public JsonResult<Book> createBook(String name,String describe){
        Book book=Book.builder()
                .name(name)
                .describe(describe)
                .createdAt(new Timestamp(new Date().getTime()))
                .build();
        bookMapper.insertBookByNameAndDescribe(book);
        System.out.println(book.getCreatedAt());
        return  new JsonResult<Book>(book);
    }

    /**
     * 查询图书详情
     * @param bookId 图书id
     * @return 图书具体信息
     */
    @GetMapping("/books/{bookId}")

    public JsonResult<Book> findBook(@PathVariable int bookId){
        Book book=bookMapper.selectBookById(bookId);
        return new JsonResult<Book>(book);
    }

    /**
     *  删除图书
     * @param bookId 删除图书的id
     * @return
     */
    @DeleteMapping("/books/{bookId}")
    public JsonResult deleteById(@PathVariable int bookId){
        int isDelete=bookMapper.deleteBookById(bookId);
        System.out.println(isDelete);
        if(isDelete==0){
            return new JsonResult("ERROR","图书ID不存在");
        }else {
            return new JsonResult("SUCCESS", "删除成功!");
        }
    }

    /**
     *  查找图书列表
     * @param name 图书名字
     * @param pagination 分页信息
     * @return
     */
    @GetMapping("/books")
    public JsonResult Books(String name,Pagination pagination){
        //获取符合条件的总记录数，用于分页
        Book book=Book.builder().name(name).build();
        pagination.setTotal(bookMapper.count(book));

        //分页查询数据
        List<Book> books=bookMapper.selectBooks(book,pagination);

        Map<String,Object> data=new HashMap<String,Object>();
        data.put("pagination",pagination);
        data.put("books",books);
        return new JsonResult(data);
    }

    /**
     * 更新图书
     * @param bookId 图书id
     * @param booksRequest 跟新内容
     * @return
     */
    @PostMapping("/books/{bookId}")
    public JsonResult  updateBooks(@PathVariable int bookId, @RequestBody BookRequest booksRequest){
        System.out.println(bookId);
        System.out.println(booksRequest);
        System.out.println("bookController");
        int isUpdate=bookMapper.updateBooks(bookId,booksRequest);

        if(isUpdate==1){
            return new JsonResult("SUCCESS","更新成功");
        }
        if(isUpdate==0){
            return new JsonResult("ERROR","id不存在");
        }
        return null;
    }
}
