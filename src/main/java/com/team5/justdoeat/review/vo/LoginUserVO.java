package com.team5.justdoeat.review.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVO {
private Long userSeq; 
private String userId;
private String userPwd;
private Long storeSeq;
private Long menuOptionSeq;
private String storeOrder;

}
