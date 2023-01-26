package com.team5.justdoeat.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.store.entity.StoreCategoryInfoEntity;

@Repository
public interface StoreCategoryInfoRepository extends JpaRepository <StoreCategoryInfoEntity, Long> {
    @Query(value = "select a from StoreCategoryInfoEntity a")
    Page<StoreCategoryInfoEntity> getStoreCateList(Pageable Pageable);
    
}
