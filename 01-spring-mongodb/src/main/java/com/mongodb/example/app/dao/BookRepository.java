package com.mongodb.example.app.dao;

import com.mongodb.example.app.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findByIsbn(String isbn);
    @Query("{ 'title' : { $gt: ?0, $lt: ?1 } }")
    List<Book> findBooksByTitleBetween(String lowerBound, String upperBound);
}
