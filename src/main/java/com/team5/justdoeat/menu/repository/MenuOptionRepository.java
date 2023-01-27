package com.team5.justdoeat.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.menu.entity.MenuOptionEntity;

@Repository
public interface MenuOptionRepository extends JpaRepository <MenuOptionEntity, Long> {
  public List<MenuOptionEntity> findByMoMiSeq(Long MoMiSeq);
}
