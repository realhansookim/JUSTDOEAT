package com.team5.justdoeat.user.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@DynamicInsert
@Table(name ="user_info")
public class UserInfoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name ="ui_seq") private Long uiSeq;
  @Column(name ="ui_id") private String uiId;
  @Column(name ="ui_pwd") private String uiPwd;
  @Column(name ="ui_name") private String uiName;
  @Column(name ="ui_email") private String uiEmail;
  @Column(name ="ui_phone") private String uiPhone;
  @Column(name ="ui_birth") private LocalDate uiBirth;
  @Column(name ="ui_gen") private Integer uiGen;
  @Column(name ="ui_grade") @ColumnDefault("1") private Integer uiGrade;
  @Column(name ="ui_status") @ColumnDefault("1") private Integer uiStatus;
}
