package com.team5.justdoeat.order.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "order_menu_list")
@Builder
public class OrderMenuList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="mc_seq") @JsonIgnore       private Long mcSeq;
  @Column(name="mc_oi_seq") @JsonIgnore       private Long mcOiSeq;
  @Column(name="mc_mi_seq") @JsonIgnore       private Long mcMiSeq;
  @Column(name="mi_name")       private String miName;
  @Column(name="mi_price")       private Integer miPrice;
  @ManyToMany(mappedBy = "mocMcSeq") List<OrderOptionList> optionList = new ArrayList<>();
  @Column(name="mc_menu_cnt")       private Integer mcMenuCnt;
}
