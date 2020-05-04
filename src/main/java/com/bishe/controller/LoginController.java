package com.bishe.controller;

import com.alibaba.fastjson.JSONObject;
import com.bishe.entity.User;
import com.bishe.service.UserService;
import com.bishe.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录
 *
 * @param
 * @return
 */
@RestController
@RequestMapping("/api")
@Api(tags = "登录相关接口", description = "")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    public JSONObject login(@RequestBody JSONObject params, HttpServletRequest request) {
        String username = params.getString("username");
        String password = params.getString("password");
        System.out.println("password=" + password + ", username=" + username);
        JSONObject jo = new JSONObject();
        User user = userService.findByUsername(username);
        if (user == null) {
            return Result.data(Result.error, "用户名或密码错误", null);
        } else {
            if (user.getPassword().equals(password)) {
                request.getSession().setAttribute(user.getUserId(), user.getUsername());
                return Result.data(Result.success, "success", user);
            } else {
                return Result.data(Result.error, "用户名或密码错误", null);
            }
        }
    }

    @GetMapping(value = "/logout")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true)
    public JSONObject logout(String userId, HttpServletRequest request) {
        System.out.println(request.getSession().getAttribute(userId));
        request.getSession().removeAttribute(userId);
        return Result.data(true);
    }
}
