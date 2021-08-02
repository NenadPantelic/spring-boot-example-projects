package com.example.pagingandsorting.demo.mapper;

import com.example.pagingandsorting.demo.dto.ProductResp;
import com.example.pagingandsorting.demo.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    List<ProductResp> mapToDtoList(Iterable<Product> product);
}
