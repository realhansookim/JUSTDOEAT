package com.team5.justdoeat.order.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.team5.justdoeat.store.entity.StoreInfoEntity;
import com.team5.justdoeat.user.entity.UserInfoEntity;

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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_info")
@Builder
public class OrderInfoEntity {
  @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name ="oi_seq") private Long oiSeq;
@Column(name ="oi_request") private String oiRequest;
@Column(name ="oi_payment") private Integer oiPayment;
@Column(name ="oi_address") private String oiAddress;
@Column(name ="oi_detail_address") private String oidetailAddress;
@Column(name ="oi_phone") private String oiPhone;
@Column(name ="oi_reg_dt") private LocalDateTime oiRegDt;
@Column(name ="oi_total_price") private Integer oiTotalPrice;
@Column(name ="oi_delivery_fee") private Integer oideliveryFee;
@Column(name ="oi_coupon") private String oiCoupon;
@Column(name ="oi_payment_price") private Integer oipaymentPrice;
@Column(name ="oi_name") private String oiName;
@ManyToOne @JoinColumn(name = "oi_ui_seq") UserInfoEntity userInfoEntity;
@ManyToOne @JoinColumn(name = "oi_si_seq") StoreInfoEntity storeInfo;
// @Column(name ="oi_ui_seq") private Integer oiuiSeq;
// @Column(name ="oi_si_seq") private Integer oisiSeq;

}
