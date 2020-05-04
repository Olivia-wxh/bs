package com.bishe.service;

import com.alibaba.fastjson.JSONObject;
import com.bishe.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    JSONObject saveOrUpdate(User user);

    Boolean delete(String userId);

    List<User> getUsers(String roleId, String username);
}
