package com.team5.justdoeat.store.entity;

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
@Entity
@Table(name = "delivery_fee_info")
public class DeliveryFeeInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dfi_seq") private Long dfiSeq;
    @Column(name = "dfi_min_fee") private Integer dfiMinFee;
    @Column(name = "dfi_max_fee") private Integer dfiMaxFee;
    @Column(name = "dfi_delivery_fee") private Integer dfiDeliveryFee;
    
    @Column(name = "dfi_si_seq") private Long dfiSiSeq;
    // @ManyToOne @JoinColumn(name = "dfi_si_seq") StoreInfoEntity storeInfo;
}