package com.example.pagingandsorting.demo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;


    public Product(String name, BigDecimal price){
        this.name = name;
        this.price = price;
    }
}
