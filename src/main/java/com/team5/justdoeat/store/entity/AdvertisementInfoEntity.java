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
@Table(name = "Advertisement_info")
public class AdvertisementInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_seq") private Long adSeq;
    @Column(name = "ad_status") private Integer adStatus;
    // @Column(name = "ad_si_seq") private Long adSiSeq;
    @OneToOne @JoinColumn(name = "ad_si_seq") StoreInfoEntity storeInfo;
}