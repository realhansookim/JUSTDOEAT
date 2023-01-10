package com.team5.justdoeat.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.menu.entity.MenuImageInfoEntity;

@Repository
public interface MenuImageInfoRepository extends JpaRepository<MenuImageInfoEntity, Long> {
  public List<MenuImageInfoEntity> findByMimgUri(String mimgUri);
}
