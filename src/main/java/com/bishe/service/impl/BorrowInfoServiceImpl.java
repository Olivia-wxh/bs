package com.bishe.service.impl;

import com.bishe.entity.Book;
import com.bishe.entity.BorrowInfo;
import com.bishe.entity.User;
import com.bishe.mapper.BookMapper;
import com.bishe.mapper.BorrowInfoMapper;
import com.bishe.mapper.UserMapper;
import com.bishe.service.BorrowInfoService;
import com.bishe.utils.VerificationCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BorrowInfoServiceImpl implements BorrowInfoService {

    @Resource
    private BorrowInfoMapper borrowInfoMapper;

    @Resource
    private BookMapper bookMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public String save(BorrowInfo borrowInfo) {
        String id = UUID.randomUUID().toString().replaceAll("-","");
        borrowInfo.setId(id);
        Date date = new Date();
        borrowInfo.setBorrowTime(date);
        borrowInfo.setStatus(1);//1表示借阅状态
        String code = null;
        BorrowInfo info = null;
        do {
            code = VerificationCode.get6Code();
            info = borrowInfoMapper.getByCode(code);
        } while (info != null);
        borrowInfo.setBorrowCode(code);
        Integer save = borrowInfoMapper.save(borrowInfo);
        if(save == 1) {
            //借阅成功，把书籍的状态改为借阅中
            Book book = bookMapper.getBookById(borrowInfo.getBookId());
            book.setStatus(1);
            bookMapper.update(book);
            return code;
        } else {
            return null;
        }
    }

    @Override
    public Boolean delete(String id) {
        int result = borrowInfoMapper.delete(id);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<BorrowInfo> getBorrowInfos(String code, String status) {
        List<BorrowInfo> infos = borrowInfoMapper.getBorrowInfos(code, status);
        for (int i = 0; i < infos.size(); i++) {
            User user = userMapper.findById(infos.get(i).getUserId());
            infos.get(i).setUsername(user.getUsername());
            Book book = bookMapper.getBookById(infos.get(i).getBookId());
            infos.get(i).setBookName(book.getBookName());
        }
        return infos;
    }

    @Override
    public Boolean update(String bookId, String userId) {
        BorrowInfo info = borrowInfoMapper.getByQuery(bookId, userId);
        info.setStatus(0);
        Date date = new Date();
        info.setReturnTime(date);
        int result = borrowInfoMapper.update(info);
        if (result == 1) {
            Book book = bookMapper.getBookById(info.getBookId());
            book.setStatus(0);
            Integer update = bookMapper.update(book);
            if (update == 1) {
                return true;
            }
        }
        return false;
    }
}
