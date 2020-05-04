package com.bishe.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "BorrowInfo对象", description = "BorrowInfo对象")
public class BorrowInfo {

    private String id;
    private String bookId;
    private String bookName;
    private String userId;
    private String username;
    private Date borrowTime;
    private Date returnTime;
    private String borrowCode;
    private Integer status;//1表示借阅状态，0表示归还状态
}
