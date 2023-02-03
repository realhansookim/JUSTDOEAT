package com.team5.justdoeat.order.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "order_list")
public class OrderListEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "oi_seq") @JsonIgnore private Long oiSeq;
  @Column(name = "si_seq") @JsonIgnore private Long siSeq;
  @Column(name = "ui_seq") @JsonIgnore private Long uiSeq;
  @Column(name = "si_name") private String siName;
  @Column(name = "si_main_img") private String siMainImg;
  @Column(name = "oi_reg_dt") private LocalDateTime oiRegDt;
  @Column(name = "oi_name") private String oiName;
  @ManyToMany(mappedBy = "mcOiSeq") List<OrderMenuList> menuList = new ArrayList<>();
  @Column(name = "oi_total_price") private Integer oiTotalPrice;
  @Column(name = "oi_delivery_fee") private Integer oiDeliveryFee;
  @Column(name = "oi_payment_price") private Integer oiPaymentPrice;
}
