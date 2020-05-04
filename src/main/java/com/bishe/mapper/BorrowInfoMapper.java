package com.bishe.mapper;

import com.bishe.entity.BorrowInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BorrowInfoMapper {
    Integer save(BorrowInfo borrowInfo);

    BorrowInfo getByCode(String code);

    int delete(String id);

    List<BorrowInfo> getBorrowInfos(@Param("borrowCode") String borrowCode, @Param("status") String status);

    BorrowInfo getById(String id);

    int update(BorrowInfo info);

    BorrowInfo getByQuery(String bookId, String userId);
}
