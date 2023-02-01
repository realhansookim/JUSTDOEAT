package com.team5.justdoeat.review.vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.team5.justdoeat.review.entity.ReviewImgEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ReviewListVO {
private Long reviewReq;
private LocalDate reviewRegDt;
private String reviewContent;
private Double allScore;
private Double tasteScore;
private Double quantityScore;
private Double deliveryScore;
private String userId;
private String userName;
private String userGen;
private String userStatus;
private String riOrDerNumber;
private List<ReviewImgEntity> reviewImgList = new ArrayList<>();
}
