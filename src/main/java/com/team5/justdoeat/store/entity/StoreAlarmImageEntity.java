package com.team5.justdoeat.store.entity;

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
  @Column(name = "sai_file_name") private String saiFileName;
  @Column(name = "sai_uri") private String saiUri;
  @Column(name = "sai_order") private Integer saiOrder;
  @Column(name = "sai_sdi_seq") private Long saiSdiSeq;
}
