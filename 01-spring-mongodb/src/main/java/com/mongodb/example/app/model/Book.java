package com.mongodb.example.app.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.Year;
import java.util.List;

@Data
@Builder
@Document
public class Book {
    @Id
    private String id;
    // ISBN can also be ID
    @Indexed(unique = true)
    private String isbn;
    private String title;
    private String publisher;
    private BookCover bookCover;
    private Author author;
    private List<String> genres;
    private BigDecimal price;
    private Year year;
    private Instant createdAt;
}
