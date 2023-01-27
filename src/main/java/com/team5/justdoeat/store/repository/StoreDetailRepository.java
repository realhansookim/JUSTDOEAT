package com.team5.justdoeat.store.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.store.entity.StoreDetailEntity;

@Repository
public interface StoreDetailRepository extends JpaRepository<StoreDetailEntity, Long> {
    @Query(value = "select a from StoreDetailEntity a")
    Page<StoreDetailEntity> getStoreDetailList(Pageable Pageable);
}
