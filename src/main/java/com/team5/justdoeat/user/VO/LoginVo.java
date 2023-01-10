package com.team5.justdoeat.user.VO;

import com.team5.justdoeat.user.entity.UserInfoEntity;

import lombok.Data;

@Data
public class LoginVo {
  private Long seq;
  private String id;
  private String pwd;
  private String name;
  private Integer grader;
  private Integer status;

  public LoginVo(UserInfoEntity entity){
    this.seq = entity.getUiSeq();
    this.id = entity.getUiId();
    this.pwd = entity.getUiPwd();
    this.name = entity.getUiName();
    this.grader = entity.getUiGrade();
    this.status = entity.getUiStatus();
  } 
}
