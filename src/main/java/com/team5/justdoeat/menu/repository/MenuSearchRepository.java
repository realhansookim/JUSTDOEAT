package com.team5.justdoeat.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team5.justdoeat.menu.entity.MenuSearchEntity;

public interface MenuSearchRepository extends JpaRepository <MenuSearchEntity, Long> {
  @Query(value = "select * from menu_search where mi_name like %:keyword% or si_name like %:keyword% group by si_seq" , nativeQuery=true)
  List<MenuSearchEntity> searchStoreAndMenu(@Param("keyword") String keyword);
}
