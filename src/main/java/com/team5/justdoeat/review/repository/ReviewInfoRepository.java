package com.team5.justdoeat.review.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.review.entity.ReviewInfoEntity;
import com.team5.justdoeat.review.vo.ReviewListVO;
import com.team5.justdoeat.user.entity.UserInfoEntity;




@Repository
public interface ReviewInfoRepository extends JpaRepository <ReviewInfoEntity, Long> {
  // public ReviewInfoEntity findByRiOrderNumber(String riOrderNumber);

  // public Integer countByRiSeqAndUserInfo(Long riSeq, UserInfoEntity useInfo);

  public List<ReviewInfoEntity> findByRiSiSeq(Long riSiSeq);

  public ReviewInfoEntity findByUserInfoAndRiSeq(UserInfoEntity userInfoEntity, Long userSeq);

  // @Query(value = "select ri_seq, ri_reg_dt, ri_content,ri_all_score, ri_taste_score, ri_quantity_score ,ri_delivery_score, ui_id, ui_name, ui_gen, ui_status from" 
  // +" review_info a join user_info b on ri_ui_seq = b.ui_seq where ri_si_seq  like :storeNo", nativeQuery = true)
  // public List<Map<String, Object>> getReviewList(@Param(value = "storeNo") Long storeNo);

  // public Page<ReviewInfoEntity> getReviewList(Pageable pageable, @Param(value = "storeNo") Long storeNo);

}