package com.bishe.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类
 */
@Data
@ApiModel(value = "User对象", description = "User对象")
public class User implements Serializable {

    private String userId;
    private String username;
    private String realname;
    private Integer age;
    private String sex;
    private String phone;
    private String profession;
    private String grade;
    private String classNum;
    private String password;
    private String roleId;
    private String studentId;


}
