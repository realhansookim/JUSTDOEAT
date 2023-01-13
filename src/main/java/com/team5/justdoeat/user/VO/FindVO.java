package com.team5.justdoeat.user.VO;


import com.team5.justdoeat.user.entity.UserInfoEntity;

import lombok.Data;


@Data
public class FindVO {
  private Long seq;
  private String id;
  private String pwd;
  private String email;
  private String name;

  public FindVO(UserInfoEntity entity){
this.seq = entity.getUiSeq();
this.id  = entity.getUiId();
this.pwd  = entity.getUiPwd();
this.email  = entity.getUiEmail();
this.name  = entity.getUiName();
  }
  }

