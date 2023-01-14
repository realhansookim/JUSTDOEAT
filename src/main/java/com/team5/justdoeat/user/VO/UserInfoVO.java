package com.team5.justdoeat.user.VO;

import java.time.LocalDate;

import com.team5.justdoeat.user.entity.UserInfoEntity;

import lombok.Data;

@Data
public class UserInfoVO {
  private Long seq;
  private String id;
  private String pwd;
  private String name;
  private String email;
  private String phone;
  private LocalDate birth;
  private Integer gen; 
  private Integer grade; 
  private Integer status;
  
  public UserInfoVO(UserInfoEntity entity){
    this.seq = entity.getUiSeq();
    this.id = entity.getUiId();
    this.pwd = entity.getUiPwd();
    this.name = entity.getUiName();
    this.email = entity.getUiEmail();
    this.phone = entity.getUiPhone();
    this.birth = entity.getUiBirth();
    this.gen = entity.getUiGen();
    this.grade = entity.getUiGrade();
    this.status = entity.getUiStatus();
  }
}
