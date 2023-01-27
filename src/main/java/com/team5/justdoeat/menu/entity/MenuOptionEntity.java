package com.team5.justdoeat.menu.entity;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu_option")
@Entity
@DynamicInsert
@Builder
public class MenuOptionEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "mo_seq") private Long moSeq;
  @Column(name = "mo_name") private String moName;
  @Column(name = "mo_price") private Integer moPrice;
  @Column(name = "mo_mi_seq") @JsonIgnore private Long moMiSeq;
  // @ManyToOne @JoinColumn(name = "mo_mi_seq") MenuInfoEntity menuInfo;

}
