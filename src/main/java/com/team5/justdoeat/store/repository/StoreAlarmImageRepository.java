package com.team5.justdoeat.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.justdoeat.store.entity.StoreAlarmImageEntity;

public interface StoreAlarmImageRepository extends JpaRepository<StoreAlarmImageEntity, Long> {
  public List<StoreAlarmImageEntity> findBySaiUri(String saiUri);
}
