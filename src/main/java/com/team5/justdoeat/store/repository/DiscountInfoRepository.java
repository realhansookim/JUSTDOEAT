package com.team5.justdoeat.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.store.entity.DiscountInfoEntity;

@Repository
public interface DiscountInfoRepository extends JpaRepository<DiscountInfoEntity, Long> {
    
}