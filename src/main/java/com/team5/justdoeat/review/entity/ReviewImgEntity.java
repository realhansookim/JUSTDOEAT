package com.team5.justdoeat.review.entity;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review_img")
@Entity
@DynamicInsert
@Builder
public class ReviewImgEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rimg_seq") private Long rimgSeq;
  @JsonIgnore
  @Column(name = "rimg_filename") private String rimgFilename;
  @Column(name = "rimg_order") private Integer rimgOrder;
  @Column(name = "rimg_uri") private String rimgUri;
  @JsonIgnore
  @Column(name = "rimg_savename") private String rimgSavename;
  // @Column(name = "rimg_ri_seq") private Long rimgRiSeq;
  @JsonBackReference
  @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "rimg_ri_seq") ReviewInfoEntity reviewInfo;
}