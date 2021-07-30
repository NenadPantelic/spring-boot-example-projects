package com.mongodb.example.app.service.impl;

import com.mongodb.example.app.dao.BookRepository;
import com.mongodb.example.app.model.Book;
import com.mongodb.example.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

}
