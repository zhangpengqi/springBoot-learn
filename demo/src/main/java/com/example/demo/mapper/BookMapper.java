package com.example.demo.mapper;

import com.example.demo.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper {
    @Select("SELECT * FROM book")
    List<Book> findBooks();
    @Select("SELECT * FROM book WHERE id=#{id}")
    Book findBookById(@Param("id") int id);
}
