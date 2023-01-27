package com.team5.justdoeat.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.justdoeat.store.entity.DetailListEntity;

public interface DetailListRepository extends JpaRepository<DetailListEntity, Long> {
  public List<DetailListEntity> findBySiSeq(Long siSeq);
}
