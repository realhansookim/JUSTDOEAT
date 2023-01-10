package com.team5.justdoeat.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.store.entity.StoreDetailEntity;

@Repository
public interface StoreDetailRepository extends JpaRepository<StoreDetailEntity,Long>{
  
}
