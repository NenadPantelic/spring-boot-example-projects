package com.mongodb.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {
    private String firstName;
    private String lastName;
    private String bio;
    private Boolean nobelAward;
}
