package com.team5.justdoeat.review.entity;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;

import com.team5.justdoeat.store.entity.StoreInfoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review_info")
@Entity
@DynamicInsert
public class ReviewInfoEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ri_seq") private Long riSeq;
  @Column(name = "ri_reg_dt") private LocalDate riRegDt;
  @Column(name = "ri_content") private String riContent;
  @Column(name = "ri_order_number") private String riOrderNumber;
  @Column(name = "ri_si_seq") private Long riSiSeq;
  // @ManyToOne @JoinColumn(name = "ri_si_seq") StoreInfoEntity storeInfo;
}
