package com.example.pagingandsorting.demo.service;

import com.example.pagingandsorting.demo.dto.ProductResp;
import com.example.pagingandsorting.demo.utils.model.PageReq;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductResp> getAllProducts(PageReq pageReq);
    List<ProductResp> getAllProductsByNameContains(String name, PageReq pageReq);
    List<ProductResp> getAllProductsByNameList(List<String> names, PageReq pageReq);
    List<ProductResp> getAllProductsInPriceRange(BigDecimal lower, BigDecimal upper, PageReq pageReq);
    List<ProductResp> getProductsWithCustomSort(PageReq pageReq);


}
