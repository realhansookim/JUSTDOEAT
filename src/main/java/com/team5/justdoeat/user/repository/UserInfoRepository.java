package com.team5.justdoeat.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.justdoeat.user.entity.UserInfoEntity;
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity,Long> {
  public List<UserInfoEntity> findAllByUiSeq(Long uiSeq);
UserInfoEntity findByUiSeq(Long uiSeq);
UserInfoEntity findByUiId(String uiId);
UserInfoEntity findByUiPhone(String uiPhone);
public UserInfoEntity findByUiNameAndUiEmail(String uiName, String uiEmail);
public UserInfoEntity findByUiIdAndUiNameAndUiEmail(String uiId,String uiName, String uiEmail);
public Integer countByUiId(String uiId);
UserInfoEntity findByUiEmail(String uiEmail);
public UserInfoEntity findByUiIdAndUiPwd(String uiId, String uiPwd);
}
