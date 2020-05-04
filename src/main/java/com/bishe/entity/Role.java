package com.bishe.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类
 */
@Data
@ApiModel(value = "Role对象", description = "Role对象")
public class Role implements Serializable {

  private String roleId;
  private String roleName;


}
