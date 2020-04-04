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

    /**
     * 给定条件图书的数量
     * @param book 查询图书的条件
     * @return 符合条件的图书数量
     */
    @Select("<script>"+
            "SELECT COUNT(*) FROM book"+
            "<where>"+
            "<if test='book.name!=null'> AND name LIKE CONCAT('%',#{book.name},'%')</if>"+
            "<if test='book.id!=0'> AND id=#{book.id}</if>" +
            "</where>"+
            "</script>")
    int count(@Param("book") Book book);

    /**
     *  查询图书列表
     * @param book 查询图书的信息
     * @param pagination 分页信息
     * @return 图书列表
     */
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

    /**
     * 更新图书
     * @param bookId 跟新图书的id
     * @param bookRequest 跟新图书的具体信息
     * @return 更新图书的条数，没有更新返回0
     */
    @Update("UPDATE book SET name=#{bookRequest.name},`describe`=#{bookRequest.describe} WHERE id=#{bookId}")
    int updateBooks(int bookId, @Param("bookRequest")BookRequest bookRequest);
}
