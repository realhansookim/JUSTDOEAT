package com.team5.justdoeat.store.entity;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "store_category_connect")
@Entity
@DynamicInsert
public class StoreCategoryConnectEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sc_seq") @JsonIgnore private Long scSeq;
  @Column(name = "sc_si_seq") @JsonIgnore private Long scSiSeq;
  // @ManyToOne @JoinColumn(name = "sc_si_seq") StoreInfoEntity storeInfo;
  // @Column(name = "sc_sci_seq") private Long scCiSeq;
  @ManyToOne @JoinColumn(name = "sc_ci_seq") StoreCategoryInfoEntity category;
  
}
