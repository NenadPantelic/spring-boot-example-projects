package com.example.pagingandsorting.demo.dao;

import com.example.pagingandsorting.demo.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findByNameContains(String name, Pageable pageable);
    List<Product> findByNameIn(List<String> names, Pageable pageable);
    List<Product> findByPriceBetween(BigDecimal lower, BigDecimal upper, Pageable pageable);
}
