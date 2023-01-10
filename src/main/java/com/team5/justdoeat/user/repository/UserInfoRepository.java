package com.team5.justdoeat.user.repository;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.user.entity.UserInfoEntity;
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity,Long> {
  public List<UserInfoEntity> findAllByUiSeq(Long uiSeq);
 UserInfoEntity findByUiSeq(Long uiSeq);
 UserInfoEntity findByUiId(String uiId);
 public UserInfoEntity findByUiNameAndUiEmail(String name, String email);
 public Integer countByUiId(String uiId);
 public UserInfoEntity findByUiIdAndUiPwd(String uiid, String uipwd);
=======
import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.justdoeat.user.entity.UserInfoEntity;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity,Long>{
  UserInfoEntity findByUiSeq(Long uiSeq);
>>>>>>> d316d98c7a34e68e052d44a72b0670a3dfb5958a
}
