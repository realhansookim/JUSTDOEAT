package com.team5.justdoeat.store.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StoreVO {

    // StoreInfoEntity
    private String siName;
    private String siMinDeliveryTime;
    private String siMaxDeliveryTime;
    private String siMainImg;
    private Integer siStatus;
    private Integer siOrderCnt;

    // StoreDetailEntity
    private String sdAlarmContent;
    private String sdAddress;
    private Integer sdAdditionalInfo;
    private String sdOpenTime;
    private String sdCloseTime;
    private String sdPhone;
    private Integer sdMinPrice;
    private String sdPayment;
    private String sdBusinessNum;
    private String sdBusinessName;
    private String sdOriginInfo;

    //StoreCategoryInfoEntity
    private String sciName;
    private String sciImg;

    // StoreCategoryConnectEntity
    private Long scSeq;

    // UserInfoEntity
    private String uiId;
    private String uiPwd;
    private String uiName;
    private String uiEmail;
    private String uiPhone;
    private LocalDate uiBirth;
    private Integer uiGen;
    private Integer uiGrade;
    private Integer uiStatus;
}