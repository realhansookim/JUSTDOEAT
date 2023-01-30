package com.team5.justdoeat.store.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "detail_list")
public class DetailListEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "si_seq") private Long siSeq;
  @Column(name = "sd_seq") private Long sdSeq;
  @ManyToMany(mappedBy = "saiSdiSeq") List<StoreAlarmImageEntity> ImageInfo = new ArrayList<>();
  @Column(name = "sd_alarm_content") private String sdAlarmContent;
  @Column(name = "sd_address") private String sdAddress;
  @Column(name = "sd_additional_info") private Integer sdAdditionalInfo;
  @Column(name = "sd_open_time") private String sdOpenTime;
  @Column(name = "sd_close_time") private String sdCloseTime;
  @Column(name = "sd_phone") private String sdPhone;
  @Column(name = "sd_min_price") private Integer sdMinPrice;
  @Column(name = "sd_payment") private String sdPayment;
  @Column(name = "sd_business_num") private String sdBusinessNum;
  @Column(name = "sd_business_name") private String sdBusinessName;
  @Column(name = "sd_origin_info") private String sdOriginInfo;

}
