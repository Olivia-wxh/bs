package com.bishe.mapper;

import com.bishe.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper 接口
 */
@Mapper
public interface BookMapper {
    Integer save(Book book);

    Integer delete(String id);

    Integer update(Book book);

    List<Book> getBooks(@Param("bookName") String bookName,
                        @Param("category") String category,
                        @Param("status") String status,
                        @Param("publisher") String publisher);

    Book getBookById(String bookId);
}
