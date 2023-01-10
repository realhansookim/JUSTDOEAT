package com.team5.justdoeat.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.justdoeat.user.entity.UserInfoEntity;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity,Long>{
  UserInfoEntity findByUiSeq(Long uiSeq);
}
