package com.team5.justdoeat.user.VO;

import java.time.LocalDate;



import lombok.Data;


@Data
public class UserInfoVO {
  
  private String id;
  private String pwd;
  private String name;
  private String email;
  private String phone;
  private LocalDate birth;
  private Integer gen; 
  private Integer grade; 
  private Integer status;
  


}
