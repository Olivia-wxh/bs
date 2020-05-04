package com.bishe.controller;

import com.alibaba.fastjson.JSONObject;
import com.bishe.entity.Book;
import com.bishe.entity.BorrowInfo;
import com.bishe.entity.User;
import com.bishe.service.BookService;
import com.bishe.service.BorrowInfoService;
import com.bishe.service.UserService;
import com.bishe.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务接口
 *
 * @param
 * @return
 */
@RestController
@RequestMapping("/stu")
@Api(tags = "业务接口", description = "")
public class ApiController {

  @Autowired private UserService userService;
  @Autowired private BookService bookService;
  @Autowired private BorrowInfoService borrowInfoService;



  /**
   * 用户管理
   */
  @PostMapping(value = "/user/submit")
  @ApiOperation(value = "添加用户", tags = "添加的时候userId传空值")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "userId", value = "用户ID", required = false),
          @ApiImplicitParam(name = "studentId", value = "学号", required = false),
          @ApiImplicitParam(name = "username", value = "用户名", required = true),
          @ApiImplicitParam(name = "password", value = "密码", required = true),
          @ApiImplicitParam(name = "age", value = "年纪", required = false),
          @ApiImplicitParam(name = "profession", value = "专业", required = false),
          @ApiImplicitParam(name = "grade", value = "年级", required = false),
          @ApiImplicitParam(name = "classNum", value = "班级", required = false),
          @ApiImplicitParam(name = "roleId", value = "角色ID", required = true),
          @ApiImplicitParam(name = "sex", value = "性别", required = false),
          @ApiImplicitParam(name = "phone", value = "联系电话", required = true)
  })
  public JSONObject submitUser(@RequestBody User user) {
    System.out.println("user参数：" + user.toString());
    JSONObject b = userService.saveOrUpdate(user);
    return b;
  }

  @GetMapping(value = "/user/delete")
  @ApiOperation(value = "删除用户", tags = "")
  @ApiImplicitParam(name = "userId", value = "用户ID", required = true)
  public JSONObject deleteUser(String userId) {
    Boolean b = userService.delete(userId);
    return Result.data(b);
  }

  @GetMapping(value = "/user/find")
  @ApiOperation(value = "查询用户", tags = "")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "roleId", value = "角色ID", required = false),
          @ApiImplicitParam(name = "username", value = "username", required = false)
  })
  public JSONObject getUsers(String roleId, String username) {
    System.out.println("参数roleId=" + roleId + ", username=" + username);
    if ("".equals(roleId)) {
      roleId = null;
    }
    if ("".equals(username)) {
      username = null;
    }
    List<User> userList = userService.getUsers(roleId, username);
    return Result.data(Result.success, "success", userList);
  }

  /**
   * 图书管理
   */
  @PostMapping(value = "/book/submit")
  @ApiOperation(value = "添加图书", tags = "添加的时候id传空值")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "id", value = "图书ID", required = true),
          @ApiImplicitParam(name = "bookName", value = "书名", required = true),
          @ApiImplicitParam(name = "author", value = "作者", required = true),
          @ApiImplicitParam(name = "category", value = "分类", required = true)
  })
  public JSONObject submitBook(@RequestBody Book book) {
    Boolean b = bookService.saveOrUpdate(book);
    return Result.data(b);
  }

  @GetMapping(value = "/book/delete")
  @ApiOperation(value = "删除图书", tags = "")
  @ApiImplicitParam(name = "bookId", value = "图书ID", required = true)
  public JSONObject deleteBook(String bookId) {
    Boolean b = bookService.delete(bookId);
    return Result.data(b);
  }

  @GetMapping(value = "/book/find")
  @ApiOperation(value = "查询图书", tags = "")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "bookName", value = "书名", required = false),
          @ApiImplicitParam(name = "category", value = "分类", required = false),
          @ApiImplicitParam(name = "publisher", value = "出版社", required = false),
          @ApiImplicitParam(name = "status", value = "书籍的借阅状态", required = false)
  })
  public JSONObject getBooks(String bookName, String category, String publisher, String status) {
    if ("".equals(bookName)) {
      bookName = null;
    }
    if ("".equals(category)) {
      category = null;
    }
    if ("".equals(publisher)) {
      publisher = null;
    }
    if ("".equals(status)) {
      status = null;
    }
    List<Book> bookList = bookService.getBooks(bookName, category, publisher, status);
    return Result.data(Result.success, "success", bookList);
  }

  /**
   * 借阅信息管理
   */
  @PostMapping(value = "/borrow/submit")
  @ApiOperation(value = "添加借阅信息", tags = "添加的时候id传空值")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "id", value = "图书ID", required = true),
          @ApiImplicitParam(name = "bookId", value = "书名", required = true),
          @ApiImplicitParam(name = "userId", value = "借阅者id", required = true)
  })
  public JSONObject submitBorrowInfo(@RequestBody BorrowInfo borrowInfo) {
    JSONObject jo = new JSONObject();
    System.out.println("借书参数：borrowInfo=" + borrowInfo.toString());
    String code = borrowInfoService.save(borrowInfo);
    jo.put("code", code);
    return Result.data(Result.success, "success", jo);
  }

  @GetMapping(value = "/borrow/back")
  @ApiOperation(value = "还书", tags = "")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "bookId", value = "书名", required = true),
          @ApiImplicitParam(name = "userId", value = "借阅者id", required = true)
  })
  public JSONObject back(String bookId, String userId) {
    System.out.println("还书参数：bookId=" + bookId + ", userId=" + userId);
    Boolean b = borrowInfoService.update(bookId, userId);
    return Result.data(b);
  }

  @GetMapping(value = "/borrow/delete")
  @ApiOperation(value = "删除借阅信息", tags = "")
  @ApiImplicitParam(name = "id", value = "借阅信息ID", required = true)
  public JSONObject deleteBorrowInfo(String id) {
    Boolean b = borrowInfoService.delete(id);
    return Result.data(b);
  }

  @GetMapping(value = "/borrow/find")
  @ApiOperation(value = "查询借阅信息", tags = "")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "code", value = "书名", required = false),
          @ApiImplicitParam(name = "status", value = "分类", required = false)
  })
  public JSONObject getBorrowInfo(String code, String status) {
    if ("".equals(code)) {
      code = null;
    }
    if ("".equals(status)) {
      status = null;
    }
    List<BorrowInfo> infos = borrowInfoService.getBorrowInfos(code, status);
    return Result.data(Result.success, "success", infos);
  }
}
