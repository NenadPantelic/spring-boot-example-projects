package com.mongodb.example.app;

import com.mongodb.example.app.dao.BookRepository;
import com.mongodb.example.app.model.Author;
import com.mongodb.example.app.model.Book;
import com.mongodb.example.app.model.BookCover;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(BookRepository bookRepository, MongoTemplate template) {
        return args -> {
            String isbn = "978-0-446-36538-3";
            Author author = new Author("Margaret", "Mitchell", null, false);
            Book book = Book.builder()
                    .title("Gone with the Wind")
                    .isbn(isbn)
                    .author(author)
                    .bookCover(BookCover.HARDCOVER)
                    .genres(Arrays.asList("Historical", "Fiction"))
                    .price(BigDecimal.TEN)
                    .publisher("Macmillian Publishers")
                    .createdAt(Instant.now())
                    .build();

            // insertBookUsingMongoTemplate(bookRepository, template, isbn, book);
            insertBookUsingMongoRepository(bookRepository, isbn, book);
            System.out.println(findBooksByTitleRange(bookRepository, "Anabelle Li","Catcher in the Rye"));
        };

    }

    private void insertBookUsingMongoRepository(BookRepository bookRepository, String isbn, Book book) {
        Optional<Book> foundBook = bookRepository.findByIsbn(isbn);
        foundBook.ifPresentOrElse(s -> {
            System.out.println("Book already exists.");
        }, () -> {
            System.out.println("Inserting a book...");
            bookRepository.insert(book);
        });

    }

    private void insertBookUsingMongoTemplate(BookRepository bookRepository, MongoTemplate template, String isbn, Book book) {
        Query query = new Query();
        query.addCriteria(Criteria.where("isbn").is(isbn));
        List<Book> books = template.find(query, Book.class);
        // not necessary - unique index will prevent this
        if (books.size() > 1) {
            throw new IllegalStateException(
                    String.format("The book with the ISBN = %s already exists.", isbn));
        }
        if (books.isEmpty()) {
            System.out.println("Inserting a book...");
            bookRepository.insert(book);
        } else {
            System.out.println("Book already exists.");
        }
    }

    private List<Book> findBooksByTitleRange(BookRepository bookRepository, String lower, String upper){
        return bookRepository.findBooksByTitleBetween(lower, upper);
    }
}
