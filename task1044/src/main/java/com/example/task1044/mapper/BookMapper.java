package com.example.task1044.mapper;

import com.example.task1044.dto.BookRequest;
import com.example.task1044.entity.Book;
import com.example.task1044.util.Pagination;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {

    /**
     * 插入图书
     * @param book 图书信息
     */
    @Insert("INSERT INTO book (name,`describe`) values (#{name},#{describe})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertBookByNameAndDescribe(Book book);

    /**
     * 查询一本书的信息
     * @param id 书本id
     * @return 书本信息
     */
    @Select("SELECT * FROM book WHERE id=#{id}")
    Book selectBookById(@Param("id") int id);

    @Delete("DELETE  FROM book WHERE id=#{id}")
    int deleteBookById(@Param("id") int id);

    @Select("<script>"+
            "SELECT COUNT(*) FROM book"+
            "<where>"+
            "<if test='book.name!=null'> AND name LIKE CONCAT('%',#{book.name},'%')</if>"+
            "<if test='book.id!=0'> AND id=#{book.id}</if>" +
            "</where>"+
            "</script>")
    int count(@Param("book") Book book);

    @Select("<script>"+
            "SELECT * FROM book"+
            "<where>"+
            "<if test='book.name!=null'> AND name LIKE CONCAT('%',#{book.name},'%')</if>"+
            "</where>"+
            "ORDER BY id DESC"+
            " LIMIT #{pagination.offset},#{pagination.limit}"+
            "</script>"
    )
    List<Book> selectBooks(@Param("book") Book book, @Param("pagination") Pagination pagination);

    @Update("UPDATE book SET name=#{bookRequest.name},`describe`=#{bookRequest.describe} WHERE id=#{bookId}")
    int updateBooks(int bookId, @Param("bookRequest")BookRequest bookRequest);

}
