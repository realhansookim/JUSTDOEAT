package com.team5.justdoeat.review.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.review.entity.ReviewImgEntity;



@Repository
public interface ReviewImgRepository extends JpaRepository <ReviewImgEntity, Long> {

  ReviewImgEntity findByRimgSavename(String rimgUri);

  // @Query(value="select a from ReviewImgEntity a where :riSeq")
  // List<ReviewImgEntity> getReviewImageList(@Param(value="riSeq")Long riSeq);

  // @Query(value="select a from ReviewImgEntity AS a where a.reviewInfo =:riSeq")
  // List<Object> getReviewImageList(@Param(value="riSeq")Long riSeq);

  @Query(value="select rimg_uri, rimg_order  from review_img a join review_info b on a.rimg_ri_seq = b.ri_seq where ri_seq like :riSeq order by rimg_order ASC", nativeQuery = true)
  public List<Map<String, Object>> getReviewImageList(@Param(value="riSeq")Object riSeq);

  @Query(value = "select * from review_img  where rimg_ri_seq = :riSeq", nativeQuery = true)
  List<ReviewImgEntity> findByRimgRiSeq(@Param(value="riSeq")Object riSeq);
  
}