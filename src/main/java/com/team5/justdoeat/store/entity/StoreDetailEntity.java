package com.team5.justdoeat.store.entity;

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
@Table(name ="store_detail")
public class StoreDetailEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sd_seq") private Long sdSeq;
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
