package com.team5.justdoeat.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.menu.entity.MenuCateConnectEntity;


@Repository
public interface MenuCateConnectRepository extends JpaRepository<MenuCateConnectEntity, Long> {
  public List<MenuCateConnectEntity> findByMccSeq(String mccSeq);
  
}
