package com.team5.justdoeat.review.entity;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review_img")
@Entity
@DynamicInsert
public class ReviewImgEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rimg_seq") private Long rimgSeq;
  @Column(name = "rimg_filename") private String rimgFilename;
  @Column(name = "rimg_order") private Integer rimgOrder;
  @Column(name = "rimg_ri_seq") private Long rimgRiSeq;
}
