package com.team5.justdoeat.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.justdoeat.admin.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
  public UserEntity findByUiIdAndUiPwd(String uiId,String uiPwd);
  public Integer countByUiId(String uiId);
  public Page<UserEntity>findByUiIdContains(String uiId,Pageable pageable);
  
}
