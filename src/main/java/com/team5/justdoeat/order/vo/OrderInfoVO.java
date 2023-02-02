package com.team5.justdoeat.order.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class OrderInfoVO {
private String request;
private Integer payment;
private String address;
private String detailAddress;
private String phone;
private Long userSeq;
private Integer totalPrice;
private Long storeSeq;
private Integer deliveryFee;
private String coupon;
private Integer paymentPrice;
private List<MenuInfoVO> menuList;
}
