package com.team5.justdoeat.admin.vo;

import java.time.LocalDate;

import lombok.Data;
@Data
public class AddVO {
private String id;
private String pwd;
private String email;
private String name;
private String phone;
private LocalDate birth;
private Integer gen;
private Integer type;

}
