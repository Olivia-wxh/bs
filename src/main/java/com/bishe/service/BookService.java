package com.bishe.service;

import com.bishe.entity.Book;

import java.util.List;

public interface BookService {
    Boolean saveOrUpdate(Book book);

    Boolean delete(String bookId);

    List<Book> getBooks(String bookName, String category, String publisher, String status);
}
