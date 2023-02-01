package com.team5.justdoeat.review.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Immutable
public class ReviewListEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ri_seq") private Long reviewReq;
  @Column(name = "ri_reg_dt") private LocalDate reviewRegDt;
  @Column(name = "ri_content") private String reviewContent;
  @Column(name = "ri_all_score") private Double allScore;
  @Column(name = "ri_taste_score") private Double tasteScore;
  @Column(name = "ri_quantity_score") private Double quantityScore;
  @Column(name = "ri_delivery_score") private Double deliveryScore;
  @Column(name = "ui_id") private String userId;
  @Column(name = "ui_name") private String userName;
  @Column(name = "ui_gen") private String userGen;
  @Column(name = "ui_status") private String userStatus;
  @Column(name = "ri_order_number") private String riOrDerNumber;
  // @OneToMany(mappedBy = "rimg_seq") private List<ReviewImgEntity> ImageInfo = new ArrayList<>();
}
