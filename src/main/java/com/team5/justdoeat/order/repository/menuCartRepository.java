package com.team5.justdoeat.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.order.entity.MenuCartEntity;

@Repository
public interface MenuCartRepository extends JpaRepository<MenuCartEntity,Long>{
  
}
