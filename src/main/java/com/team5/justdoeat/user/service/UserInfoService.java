package com.team5.justdoeat.user.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.team5.justdoeat.user.entity.UserInfoEntity;
import com.team5.justdoeat.user.repository.UserInfoRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserInfoService {
@Autowired UserInfoRepository userRepo;
  public UserInfoEntity getUserUiNameAndUiEmail(String uiName,String uiEmail){
    return userRepo.findByUiNameAndUiEmail(uiName, uiEmail);
  }
  public  UserInfoEntity getUserByUiId(String uiId){
    return userRepo.findByUiId(uiId);
  }

  public Map<String, Object> getUserList(HttpSession session) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    UserInfoEntity loginUser = (UserInfoEntity) session.getAttribute("loginUser");
    if (loginUser == null) {
      resultMap.put("status", false);
      resultMap.put("msg", "로그인이 필요합니다.");
      resultMap.put("code", HttpStatus.FORBIDDEN);
    } else {
      resultMap.put("status", true);
      resultMap.put("msg", "조회되었습니다.");
      resultMap.put("code", HttpStatus.OK);
      resultMap.put("list", userRepo.findAllByUiSeq(loginUser.getUiSeq()));
    }
    return resultMap;
  }

}
