package com.team5.justdoeat.order.entity;

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
@Table(name = "order_option_list")
@Builder
public class OrderOptionList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="moc_seq") @JsonIgnore       private Long mocSeq;
  @Column(name="moc_mc_seq") @JsonIgnore       private Long mocMcSeq;
  @Column(name="moc_mo_seq") @JsonIgnore       private Long mocMoSeq;
  @Column(name="mo_name")       private String moName;
  @Column(name="mo_price")       private Integer moPrice;
}
