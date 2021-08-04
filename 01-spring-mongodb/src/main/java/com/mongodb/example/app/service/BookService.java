package com.mongodb.example.app.service;

import com.mongodb.example.app.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
}
