package com.team5.justdoeat.menu.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.menu.entity.MenuInfoEntity;

@Repository
public interface MenuInfoRepository extends JpaRepository <MenuInfoEntity, Long> {
  public MenuInfoEntity findByMiName(String miName);
  @Query(value = "select * from menu_info where mi_name like %:keyword%", nativeQuery=true)
    List<MenuInfoEntity> searchMenu(@Param("keyword") String keyword);
  public List<MenuInfoEntity> findByMiSiSeq(Long miSiSeq);
  MenuInfoEntity findByMiSeq(Long miSeq);
}
