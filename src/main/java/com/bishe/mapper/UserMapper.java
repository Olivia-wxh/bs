package com.bishe.mapper;

import com.bishe.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *  Mapper 接口
 */
@Mapper
public interface UserMapper {

    User findByUsername(@Param("username") String username);

    User findById(@Param("userId") String userId);

    Integer save(User user);

    Integer delete(String userId);

    Integer update(User user);

    List<User> getUsers(@Param("roleId") String roleId, @Param("username") String username);
}
