package com.bishe.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "Book对象", description = "Book对象")
public class Book implements Serializable {

    private String id;
    private String bookName;
    private String author;
    private String category;
    private String publisher;
    private Integer status;//1表示借阅状态，0表示归还状态

}
