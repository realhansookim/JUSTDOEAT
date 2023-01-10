package com.team5.justdoeat.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.justdoeat.store.entity.StoreInfoEntity;

public interface StoreInfoRepository extends JpaRepository<StoreInfoEntity,Long>{
  StoreInfoEntity findBySiSeq(Long siSeq);
}
