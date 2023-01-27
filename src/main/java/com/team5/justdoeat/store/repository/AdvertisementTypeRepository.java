package com.team5.justdoeat.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.justdoeat.store.entity.AdvertisementTypeEntity;

public interface AdvertisementTypeRepository extends JpaRepository<AdvertisementTypeEntity, Long> {
  
}
