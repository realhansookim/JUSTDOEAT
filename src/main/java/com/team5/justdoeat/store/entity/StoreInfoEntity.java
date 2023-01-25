package com.team5.justdoeat.store.entity;

import java.util.ArrayList;
import java.util.List;

import com.team5.justdoeat.menu.entity.MenuInfoEntity;
import com.team5.justdoeat.review.entity.ReviewInfoEntity;
import com.team5.justdoeat.user.entity.UserInfoEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Table(name = "store_info")
@Builder
public class StoreInfoEntity {
    @ManyToMany(mappedBy = "scSiSeq") List<StoreCategoryConnectEntity> categoryInfo = new ArrayList<>();
    @OneToMany(mappedBy = "dfiSiSeq") List<DeliveryFeeInfoEntity> deliveryInfo = new ArrayList<>();
    @OneToMany(mappedBy = "diSiSeq") List<DiscountInfoEntity> discountInfo = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "si_seq")                private Long siSeq;
    @Column(name = "si_name")               private String siName;
    @Column(name = "si_min_delivery_time")  private String siMinDeliveryTime;
    @Column(name = "si_max_delivery_time")  private String siMaxDeliveryTime;
    @Column(name = "si_main_img")           private String siMainImg;
    @Column(name = "si_status")             private Integer siStatus;
    @Column(name = "si_order_cnt")          private Integer siOrderCnt;

    @Column(name = "si_ui_seq")             private Integer siUiSeq;
    // @Column(name = "si_sd_seq")             private Long siSdSeq;
    @OneToMany(mappedBy = "miSiSeq") List<MenuInfoEntity> menuInfo = new ArrayList<>();
    @OneToMany(mappedBy = "riSiSeq") List<ReviewInfoEntity> reviewInfo = new ArrayList<>();
    @OneToOne @JoinColumn(name = "si_sd_seq") StoreDetailEntity storeDetail;

    // 연쇄입력을 위해 join 키 설정
    // @OneToOne(cascade = CascadeType.PERSIST)
    // @JoinColumn(name = "si_sd_seq")
    // StoreDetailEntity storeDetailList;

    // @OneToOne(cascade = CascadeType.PERSIST)
    // @JoinColumn(name = "si_ui_seq")
    // UserInfoEntity userInfoList;

}
