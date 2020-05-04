package com.bishe.service;

import com.bishe.entity.Book;
import com.bishe.entity.BorrowInfo;

import java.util.List;

public interface BorrowInfoService {
    String save(BorrowInfo borrowInfo);

    Boolean delete(String id);

    List<BorrowInfo> getBorrowInfos(String code, String status);

    Boolean update(String bookId, String userId);
}
