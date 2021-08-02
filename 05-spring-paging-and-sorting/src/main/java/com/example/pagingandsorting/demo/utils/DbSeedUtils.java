package com.example.pagingandsorting.demo.utils;

import com.example.pagingandsorting.demo.dao.ProductRepository;
import com.example.pagingandsorting.demo.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DbSeedUtils {
    @Autowired
    private ProductRepository productRepository;

    public void populateProducts(int numOfRecords) {
        log.info("Generating random products....");
        List<Product> products = new ArrayList<Product>();
        for (int i = 0; i < numOfRecords; i++) {
            String productName = RandomUtils.generateRandomText(50, false);
            BigDecimal price = RandomUtils.getRandomDecimal();
            products.add(new Product(productName, price));
        }
        productRepository.saveAll(products);
    }
}

