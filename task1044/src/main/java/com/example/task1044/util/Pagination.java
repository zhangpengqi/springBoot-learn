package com.example.task1044.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@JsonIgnoreProperties(value = {"offset"})
public class Pagination {
    private int page;//当前页码
    private int limit;//每页显示条数
    private int total;//总条数

    public Pagination(){
        limit=20;
        page=1;
    }
    public Pagination(int page,int limit){
        setPage(page);
        setLimit(limit);
    }
    public Pagination(int page,int limit,int total){
        setPage(page);
        setLimit(limit);
        setTotal(total);
    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page>0?page:1;
    }

    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        if(limit>0){
            this.limit = limit;
        }
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getOffset(){
        return (page-1)*limit;
    }
}
