package com.team5.justdoeat.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.store.entity.StoreInfoEntity;

@Repository
public interface StoreInfoRepository extends JpaRepository<StoreInfoEntity,Long>{
  public StoreInfoEntity findBySiName(String SiName);
}
