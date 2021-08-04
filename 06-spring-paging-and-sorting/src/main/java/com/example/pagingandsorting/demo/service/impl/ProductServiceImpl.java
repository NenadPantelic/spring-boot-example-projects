package com.example.pagingandsorting.demo.service.impl;

import com.example.pagingandsorting.demo.dao.ProductRepository;
import com.example.pagingandsorting.demo.dto.ProductResp;
import com.example.pagingandsorting.demo.mapper.ProductMapper;
import com.example.pagingandsorting.demo.service.ProductService;
import com.example.pagingandsorting.demo.utils.PagingUtils;
import com.example.pagingandsorting.demo.utils.model.PageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductResp> getAllProducts(PageReq pageReq) {
        Pageable pageable = PagingUtils.createPageable(pageReq);
        return productMapper.mapToDtoList(productRepository.findAll(pageable));
    }

    @Override
    public List<ProductResp> getAllProductsByNameContains(String name, PageReq pageReq) {
        Pageable pageable = PagingUtils.createPageable(pageReq);
        return productMapper.mapToDtoList(productRepository.findByNameContains(name, pageable));
    }

    @Override
    public List<ProductResp> getAllProductsByNameList(List<String> names, PageReq pageReq) {
        Pageable pageable = PagingUtils.createPageable(pageReq);
        return productMapper.mapToDtoList(productRepository.findByNameIn(names, pageable));
    }

    @Override
    public List<ProductResp> getAllProductsInPriceRange(BigDecimal lower, BigDecimal upper, PageReq pageReq) {
        Pageable pageable = PagingUtils.createPageable(pageReq);
        return productMapper.mapToDtoList(productRepository.findByPriceBetween(lower, upper, pageable));
    }

    @Override
    public List<ProductResp> getProductsWithCustomSort(PageReq pageReq) {
        Pageable pageable = PagingUtils.createPageableWithCustomSort(pageReq);
        return productMapper.mapToDtoList(productRepository.findAll(pageable));
    }
}
