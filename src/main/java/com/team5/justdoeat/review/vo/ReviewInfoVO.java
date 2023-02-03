package com.team5.justdoeat.review.vo;

import java.beans.JavaBean;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewInfoVO {
    private LocalDate regDt;
    private String content;
    private Double allScore;
    private Double tasteScore;
    private Double quantityScore;
    private Double deliveryScore;
    private String orderSeq;
}