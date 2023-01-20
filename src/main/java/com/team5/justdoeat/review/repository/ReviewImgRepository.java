package com.team5.justdoeat.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.review.entity.ReviewImgEntity;



@Repository
public interface ReviewImgRepository extends JpaRepository <ReviewImgEntity, Long> {
  
}