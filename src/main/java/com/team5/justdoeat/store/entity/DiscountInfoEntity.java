package com.team5.justdoeat.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "discount_info")
public class DiscountInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "di_seq") private Long diSeq;
    @Column(name = "di_min_price") private Integer diMinPrice;
    @Column(name = "di_discount") private Integer diDiscount;
    
    @Column(name = "di_si_seq") private Long diSiSeq;
    // @OneToOne @JoinColumn(name = "di_si_seq") StoreInfoEntity storeInfo;
}