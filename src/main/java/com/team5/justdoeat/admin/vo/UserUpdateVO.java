package com.team5.justdoeat.admin.vo;

import lombok.Data;

@Data
public class UserUpdateVO {
  private Long seq;
  private String pwd;
  private String name;
  private Integer status;
  private Integer grade;
}
