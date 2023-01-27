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
@Table(name = "advertisement_type")
public class AdvertisementTypeEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "at_seq") private Long atSeq;
  @Column(name = "at_type") private String atType;
}
