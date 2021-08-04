package com.example.pagingandsorting.demo.utils.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageReq {
    private Integer page;
    private Integer size;
    private String sortBy;
    private String order;

}