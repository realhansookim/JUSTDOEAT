package com.team5.justdoeat.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.team5.justdoeat.admin.entity.StoreEntity;
@Repository
public interface StoreRepository extends JpaRepository<StoreEntity,Long> {
    public Page<StoreEntity>findBySiNameContains(String siName,Pageable pageable);
   StoreEntity findBySiSeq(Long siSeq);
}
