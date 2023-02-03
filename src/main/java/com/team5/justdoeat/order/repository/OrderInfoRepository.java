package com.team5.justdoeat.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.justdoeat.order.entity.OrderInfoEntity;
import com.team5.justdoeat.user.entity.UserInfoEntity;

public interface OrderInfoRepository extends JpaRepository<OrderInfoEntity, Long>{

  List<OrderInfoEntity> findByUserInfoEntity(UserInfoEntity uiSeq);

  OrderInfoEntity findByOiName(String orderSeq);
  
}
