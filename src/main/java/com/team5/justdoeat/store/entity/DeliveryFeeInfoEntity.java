package com.team5.justdoeat.store.entity;

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
@Entity
@Table(name = "delivery_fee_info")
public class DeliveryFeeInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dfi_seq") @JsonIgnore private Long dfiSeq;
    @Column(name = "dfi_min_fee") private Integer dfiMinFee;
    @Column(name = "dfi_max_fee") private Integer dfiMaxFee;
    @Column(name = "dfi_delivery_fee") private Integer dfiDeliveryFee;
<<<<<<< HEAD
    @Column(name = "dfi_si_seq") @JsonIgnore private Long dfiSiSeq;
=======
    
    @Column(name = "dfi_si_seq") private Long dfiSiSeq;
>>>>>>> kdh
    // @ManyToOne @JoinColumn(name = "dfi_si_seq") StoreInfoEntity storeInfo;
}