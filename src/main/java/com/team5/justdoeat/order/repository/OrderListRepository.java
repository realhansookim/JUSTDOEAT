package com.team5.justdoeat.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.justdoeat.order.entity.OrderListEntity;

public interface OrderListRepository extends JpaRepository<OrderListEntity, Long> {
  List<OrderListEntity> findByUiSeq(Long uiSeq);
}
