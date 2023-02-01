package com.team5.justdoeat.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team5.justdoeat.review.entity.ReviewListEntity;

public interface ReviewListRepository extends JpaRepository<ReviewListEntity, Long>{

  @Query(value = "select ri_seq, ri_reg_dt, ri_content,ri_all_score, ri_taste_score, ri_quantity_score ,ri_delivery_score,ui_id, ui_name, ui_gen, ui_status, ri_order_number from review_info a join user_info b on ri_ui_seq = b.ui_seq where ri_si_seq  like :storeNo", nativeQuery=true)
  List<ReviewListEntity> getReviewList(@Param(value = "storeNo") Long storeNo);

  
  
}
