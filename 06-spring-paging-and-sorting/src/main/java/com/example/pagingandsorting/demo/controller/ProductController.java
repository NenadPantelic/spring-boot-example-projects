package com.example.pagingandsorting.demo.controller;

import com.example.pagingandsorting.demo.dto.ProductResp;
import com.example.pagingandsorting.demo.service.ProductService;
import com.example.pagingandsorting.demo.utils.model.PageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products/")
public class ProductController {

    @Autowired
    private ProductService productService;

    /*http://localhost:8080/api/v1/products/?page=2&size=50&sort=name&order=desc*/
    @GetMapping
    public List<ProductResp> getAllProducts(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                            @RequestParam(name = "size", defaultValue = "100") Integer size,
                                            @RequestParam(name = "sort", defaultValue = "createdAt") String sortBy,
                                            @RequestParam(name = "order", defaultValue = "asc") String order) {
        return productService.getAllProducts(new PageReq(page, size, sortBy, order));
    }

    /*http://localhost:8080/api/v1/products/?page=2&size=50&sort=-price,-createdAt,%2Bname*/
    @GetMapping(params={"page", "size", "sort"})
    public List<ProductResp> getAllProducts(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                            @RequestParam(name = "size", defaultValue = "100") Integer size,
                                            @RequestParam(name = "sort", defaultValue = "createdAt") String sortBy
    ) {
        return productService.getProductsWithCustomSort(new PageReq(page, size, sortBy, null));
    }

    /*http://localhost:8080/api/v1/products/?name=de&page=2&size=50&sort=name&order=desc*/
    @GetMapping(params = "name")
    public List<ProductResp> getAllProductsByNameContains(@RequestParam(name = "name", required = false) String name,
                                                          @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                          @RequestParam(name = "size", defaultValue = "100") Integer size,
                                                          @RequestParam(name = "sort", defaultValue = "createdAt") String sortBy,
                                                          @RequestParam(name = "order", defaultValue = "asc") String order) {
        PageReq pageReq = new PageReq(page, size, sortBy, order);
        return (name != null) ?
                productService.getAllProductsByNameContains(name, pageReq)
                : productService.getAllProducts(pageReq);
    }

    /*http://localhost:8080/api/v1/products/?lower=200&upper=1000&page=2&size=50&sort=name&order=desc*/
    @GetMapping(params = {"lower", "upper"})
    public List<ProductResp> getAllProductsByPriceRange(@RequestParam(name = "lower", defaultValue = "0") Double lower,
                                                        @RequestParam(name = "upper", defaultValue = "10000") Double upper,
                                                        @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                        @RequestParam(name = "size", defaultValue = "100") Integer size,
                                                        @RequestParam(name = "sort", defaultValue = "createdAt") String sortBy,
                                                        @RequestParam(name = "order", defaultValue = "asc") String order) {
        return productService.getAllProductsInPriceRange(BigDecimal.valueOf(lower), BigDecimal.valueOf(upper), new PageReq(page, size, sortBy, order));
    }

    /*http://localhost:8080/api/v1/products/?names=de&names=pe&page=2*/
    @GetMapping(params = "names")
    public List<ProductResp> getAllProductsByNameList(@RequestParam(name = "names", required = false) List<String> names,
                                                      @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(name = "size", defaultValue = "100") Integer size,
                                                      @RequestParam(name = "sort", defaultValue = "createdAt") String sortBy,
                                                      @RequestParam(name = "order", defaultValue = "asc") String order) {
        return productService.getAllProductsByNameList(names, new PageReq(page, size, sortBy, order));
    }
}
