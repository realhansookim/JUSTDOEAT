package com.team5.justdoeat.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.justdoeat.order.entity.OrderInfoEntity;

public interface OrderInfoRepository extends JpaRepository<OrderInfoEntity, Long>{
  
}
