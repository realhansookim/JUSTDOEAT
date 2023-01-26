package com.team5.justdoeat.store.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.store.entity.StoreCategoryConnectEntity;

@Repository
public interface StoreCategoryConnectRepository extends JpaRepository <StoreCategoryConnectEntity, Long> {
  List<StoreCategoryConnectEntity> findByScCiSeq(Long scCiSeq);
}
