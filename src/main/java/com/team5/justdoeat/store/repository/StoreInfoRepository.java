package com.team5.justdoeat.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.store.entity.StoreInfoEntity;

@Repository
public interface StoreInfoRepository extends JpaRepository<StoreInfoEntity,Long>{
  StoreInfoEntity findBySiSeq(Long siSeq);
  public StoreInfoEntity findBySiName(String SiName);
  
}
