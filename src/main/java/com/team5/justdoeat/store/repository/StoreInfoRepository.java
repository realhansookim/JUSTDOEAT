package com.team5.justdoeat.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.store.entity.StoreInfoEntity;

@Repository
public interface StoreInfoRepository extends JpaRepository<StoreInfoEntity, Long> {
    @Query(value = "select a from StoreInfoEntity a")
    Page<StoreInfoEntity> getStoreInfoList(Pageable Pageable);

    @Query(value = "select a from StoreInfoEntity a where a.siName like %:keyword%")
    Page<StoreInfoEntity> findBySiName(Pageable pageable, @Param("keyword") String keyword);

    StoreInfoEntity findBySiSeq(Long storeSeq);

}