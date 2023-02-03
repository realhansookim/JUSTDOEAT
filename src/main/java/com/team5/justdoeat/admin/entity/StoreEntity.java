package com.team5.justdoeat.admin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "store_info")
@Entity
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "si_seq")                private Long siSeq;
    @Column(name = "si_name")               private String siName;
    @Column(name = "si_min_delivery_time")  private String siMinDeliveryTime;
    @Column(name = "si_max_delivery_time")  private String siMaxDeliveryTime;
    @Column(name = "si_main_img")           private String siMainImg;
    @Column(name = "si_min_price")          private Integer siMinPrice;
    @Column(name = "si_status")             private Integer siStatus;
    @Column(name = "si_order_cnt")          private Integer siOrderCnt;
    @Column(name = "si_ui_seq")             private Integer siUiSeq;
    @Column(name = "si_sd_seq")             private Long siSdSeq;
    @Column(name = "si_at_seq")             private Long siAtSeq;
}
