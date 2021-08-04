package com.mongodb.example.app.controller;

import com.mongodb.example.app.model.Book;
import com.mongodb.example.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books/")
public class BookController {

    @Autowired
    private  BookService bookService;
    
    // DTO missing
    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
}
