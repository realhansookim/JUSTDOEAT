package com.team5.justdoeat.review.entity;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;

import com.team5.justdoeat.store.entity.StoreInfoEntity;
import com.team5.justdoeat.user.entity.UserInfoEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review_info")
@Entity
@DynamicInsert
@Builder
public class ReviewInfoEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ri_seq") private Long riSeq;
  @Column(name = "ri_reg_dt") private LocalDate riRegDt;
  @Column(name = "ri_content") private String riContent;
  @Column(name = "ri_order_number") private String riOrderNumber;
  // @Column(name = "ri_si_seq") private Long riSiSeq;
  @ManyToOne @JoinColumn(name = "ri_si_seq" ) StoreInfoEntity storeInfo;
  @OneToOne(cascade = CascadeType.ALL) @JoinColumn(name = "ri_rsp_seq") ReviewScorePointEntity reviewScorePoint;
  @ManyToOne @JoinColumn(name = "ri_ui_seq") UserInfoEntity userInfo;

  public ReviewInfoEntity(LocalDate riRegDt,String riContent,String riOrderNumber,StoreInfoEntity storeInfo,ReviewScorePointEntity reviewScorePoint, UserInfoEntity userInfo){
    this.riRegDt = riRegDt;
    this.riContent = riContent;
    this.riOrderNumber = riOrderNumber;
    this.storeInfo = storeInfo;
    this.reviewScorePoint = reviewScorePoint;
    this.userInfo = userInfo;
  }
}
