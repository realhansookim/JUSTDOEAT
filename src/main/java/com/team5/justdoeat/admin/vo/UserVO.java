package com.team5.justdoeat.admin.vo;

import java.time.LocalDate;

import com.team5.justdoeat.admin.entity.UserEntity;

import lombok.Data;

@Data
public class UserVO {

  private Long ui_seq;
  private String ui_id;
  private String ui_pwd;
  private String ui_name;
  private String ui_email;
  private String ui_phone;
  private LocalDate ui_birth;
  private Integer ui_gen;
  private Integer ui_grade;
  private Integer ui_status;
  public UserVO(UserEntity entity){
  
    this.ui_seq = entity.getUiSeq();
    this.ui_id = entity.getUiId();
    this.ui_pwd = entity.getUiPwd();
    this.ui_name = entity.getUiName();
    this.ui_email = entity.getUiEmail();
    this.ui_phone = entity.getUiPhone();
    this.ui_birth = entity.getUiBirth();
    this.ui_gen = entity.getUiGen();
    this.ui_grade = entity.getUiGrade();
    this.ui_status = entity.getUiStatus();
    
      }
}