package com.team5.justdoeat.user.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
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
    // List<UserInfoEntity> list = userRepo.findAll();
    if (loginUser == null) {
      resultMap.put("status", false);
      resultMap.put("msg", "로그인이 필요합니다.");
      resultMap.put("code", HttpStatus.FORBIDDEN);
    } else {
      resultMap.put("status", true);
      resultMap.put("msg", "조회되었습니다.");
      // resultMap.put("totalPage", list.size());
      resultMap.put("code", HttpStatus.OK);
      resultMap.put("list", userRepo.findAllByUiSeq(loginUser.getUiSeq()));
    }
    return resultMap;
  }

  public Map<String,Object> addUser(UserInfoEntity data){
    Map<String,Object> map = new LinkedHashMap<String,Object>();
    if (userRepo.countByUiId(data.getUiId()) == 1) {
      map.put("status", false);
      map.put("msg", data.getUiId() + "은/는 이미 등록된 사용자 입니다.");
      map.put("msg", data.getUiEmail() + "은/는 이미 등록된 이메일 입니다.");
      map.put("code", HttpStatus.BAD_REQUEST);
    } else {
    userRepo.save(data);
    map.put("status", true);
    map.put("msg", "등록되었습니다.");
    map.put("code", HttpStatus.CREATED);
  }
  return map;
}

  public Map<String, Object> deleteUser(Long userNo) {
    Map<String,Object> map = new LinkedHashMap<String,Object>();
    UserInfoEntity entity = userRepo.findByUiSeq(userNo);
    if(entity == null) {
      map.put("status", false);
      map.put("message", "잘못된 정보입니다.");
    }
    else {
      map.put("status", true);
      map.put("message", "탈퇴되었습니다.");
      userRepo.delete(entity);
    }
    return map;
  }

  public Map<String, Object> updateUser(Long userNo, UserInfoEntity data) {
    Map<String,Object> map = new LinkedHashMap<String,Object>();
    UserInfoEntity entity = userRepo.findByUiSeq(userNo);
    if(entity == null) {
      map.put("status", false);
      map.put("message", "잘못된 정보입니다.");
    }
    else {
      map.put("status", true);
      map.put("message", "수정되었습니다.");
      if(data.getUiName() != null) entity.setUiName(data.getUiName());
      if(data.getUiPwd() != null) entity.setUiPwd(data.getUiPwd());
      if(data.getUiBirth() != null) entity.setUiBirth(data.getUiBirth());
      if(data.getUiPhone() != null) entity.setUiPhone(data.getUiPhone());
      if(data.getUiEmail() != null) entity.setUiEmail(data.getUiEmail());
      userRepo.save(entity);
    }
    return map;
  }


// public Map<String, Object> getLoginList(HttpSession session) {
//   Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
//   UserInfoEntity loginUser = (UserInfoEntity) session.getAttribute("loginUser");
//   if (loginUser == null) {
//     resultMap.put("status", false);
//     resultMap.put("msg", "로그인이 필요합니다.");
//     resultMap.put("code", HttpStatus.FORBIDDEN);

//   } else {
//     resultMap.put("status", true);
//     resultMap.put("msg", "조회되었습니다.");
//     resultMap.put("code", HttpStatus.OK);
//     resultMap.put("list", userRepo.findAllByUiSeq(loginUser.getUiSeq()));
//   }
//   return resultMap;
// }


}