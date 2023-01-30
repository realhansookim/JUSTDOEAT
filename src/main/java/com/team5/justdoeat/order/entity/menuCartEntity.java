package com.team5.justdoeat.order.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "menu_cart")
@Builder
public class menuCartEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="mc_seq")       private Long meSeq;
  @Column(name="mc_menu_cnt")       private Long mcMenuCnt;
  @Column(name="mc_oi_seq")       private Long mcOiSeq;
  @Column(name="mc_mi_seq")       private Long mcMiSeq;
}
