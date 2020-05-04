package com.bishe.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bishe.entity.User;
import com.bishe.mapper.UserMapper;
import com.bishe.service.UserService;
import com.bishe.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        return user;
    }

    @Override
    public JSONObject saveOrUpdate(User user) {
        Integer result = null;
        if (user.getUserId() == null) {
            //添加前先查不能重名
            User byUsername = findByUsername(user.getUsername());
            if (byUsername == null) {
                String userId = UUID.randomUUID().toString().replaceAll("-","");
                user.setUserId(userId);
                result = userMapper.save(user);
            } else {
                System.out.println("username重复");
                return Result.data(Result.error, "username重复", false);
            }
        } else {
            result = userMapper.update(user);
        }
        if (result == 1) {
            return Result.data(Result.success, "success", true);
        } else {
            return Result.data(Result.error, "false", false);
        }
    }

    @Override
    public Boolean delete(String userId) {
        Integer result = userMapper.delete(userId);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<User> getUsers(String roleId, String username) {
        List<User> list = userMapper.getUsers(roleId, username);
        return list;
    }
}
