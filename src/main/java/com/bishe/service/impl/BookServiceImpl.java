package com.bishe.service.impl;

import com.bishe.entity.Book;
import com.bishe.mapper.BookMapper;
import com.bishe.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Override
    public Boolean saveOrUpdate(Book book) {
        Integer result = null;
        if (book.getId() == null) {
            String id = UUID.randomUUID().toString().replaceAll("-","");
            book.setId(id);
            result = bookMapper.save(book);
        } else {
            result = bookMapper.update(book);
        }
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(String bookId) {
        Integer result = bookMapper.delete(bookId);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Book> getBooks(String bookName, String category, String publisher, String status) {
        List<Book> books = bookMapper.getBooks(bookName, category, publisher, status);
        return books;
    }
}
