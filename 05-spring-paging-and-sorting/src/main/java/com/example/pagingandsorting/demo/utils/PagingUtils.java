package com.example.pagingandsorting.demo.utils;

import com.example.pagingandsorting.demo.utils.model.PageReq;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

public class PagingUtils {

    public static Pageable createPageable(PageReq pageReq) {
        return createPageable(pageReq.getPage(), pageReq.getSize(), pageReq.getSortBy(), pageReq.getOrder());
    }

    public static Pageable createPageableWithCustomSort(PageReq pageReq){
        return createPageable(pageReq.getPage(), pageReq.getSize(), createSort(pageReq.getSortBy()));

    }
    private static Pageable createPageable(Integer pageNo, Integer pageSize, String sortBy, String order) {
        String sortByField = sortField(sortBy);
        Sort sort = sortOrder(sortByField, order);
        return PageRequest.of(pageNo, pageSize, sort);
    }

    private static Pageable createPageable(Integer pageNo, Integer pageSize, Sort sort) {
        return PageRequest.of(pageNo, pageSize, sort);
    }

    public static <T> Page<T> createPage(List<T> data, Pageable pageable) {
        List<T> content = new ArrayList<T>();
        if (data.size() > pageable.getOffset()) {
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), data.size());
            content = data.subList(start, end);
        }
        // NOTE: PageImpl doesn't apply sorting
        return new PageImpl<>(content, pageable, data.size());
    }

    private static String sortField(String sortBy) {
        // could add some custom mapping
        return sortBy;
    }

    public static Sort createSort(String sort) {
        // NOTE: needs additional validation
        String[] sortComponents = sort.split(",");
        Sort resultSort = null;
        for (String sortComp : sortComponents) {
            Sort currSort = Sort.by(sortComp.substring(1));
            currSort = sortComp.startsWith("+") ? currSort.ascending() : currSort.descending();
            if (resultSort == null) {
                resultSort = currSort;
            } else {
                resultSort = resultSort.and(currSort);
            }
        }
        return resultSort;
    }

    private static Sort sortOrder(String sortByField, String order) {
        return order.equals("asc") ? Sort.by(sortByField).ascending() : Sort.by(sortByField).descending();
    }
}