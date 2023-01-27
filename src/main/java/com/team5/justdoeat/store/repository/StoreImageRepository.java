package com.team5.justdoeat.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.store.entity.StoreImageEntity;

@Repository
public interface StoreImageRepository extends JpaRepository<StoreImageEntity,Long>{
    // List<BookImageEntity> findByBimgBiSeq(Long bimgBiSeq);
    List<StoreImageEntity> findBySimgSeq(Long simgBiSeq);

    // List<StoreImageEntity> findTop1BySimgUriOrderBySimgSeqDesc(String uri);
    public List<StoreImageEntity> findBySimgUri(String simgUri);
  
}
