package com.team5.justdoeat.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.review.entity.ReviewInfoEntity;
import com.team5.justdoeat.user.entity.UserInfoEntity;




@Repository
public interface ReviewInfoRepository extends JpaRepository <ReviewInfoEntity, Long> {
  // public ReviewInfoEntity findByRiOrderNumber(String riOrderNumber);

  // public Integer countByRiSeqAndUserInfo(Long riSeq, UserInfoEntity useInfo);

  public List<ReviewInfoEntity> findByRiSiSeq(Long riSiSeq);
}