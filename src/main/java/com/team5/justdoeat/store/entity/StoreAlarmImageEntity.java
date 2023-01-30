package com.team5.justdoeat.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Entity
@Table(name = "store_alarm_image")
public class StoreAlarmImageEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sai_seq") private Long saiSeq;
  @Column(name = "sai_url") private String saiUrl;
  @Column(name = "sai_file_name") @JsonIgnore private String saiFileName;
  @Column(name = "sai_uri") @JsonIgnore private String saiUri;
  @Column(name = "sai_order") private Integer saiOrder;
  @Column(name = "sai_sdi_seq") private Long saiSdiSeq;
}
