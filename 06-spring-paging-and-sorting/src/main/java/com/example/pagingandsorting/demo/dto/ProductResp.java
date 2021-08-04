package com.example.pagingandsorting.demo.dto;

import com.example.pagingandsorting.demo.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResp {
    private Long id;
    private String name;
    private BigDecimal price;
    private Instant createdAt;

    public ProductResp(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        createdAt = product.getCreatedAt();
    }
}
