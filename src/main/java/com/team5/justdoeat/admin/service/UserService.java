package com.team5.justdoeat.admin.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.team5.justdoeat.admin.entity.UserEntity;
import com.team5.justdoeat.admin.repository.UserRepository;
import com.team5.justdoeat.admin.vo.AddVO;
import com.team5.justdoeat.admin.vo.LoginVO;
import com.team5.justdoeat.admin.vo.UserUpdateVO;
import com.team5.justdoeat.admin.vo.UserVO;

@Service
public class UserService {
  @Autowired UserRepository uRepo;

  public Map<String,Object>loginUser(LoginVO login){
    Map<String,Object> map = new LinkedHashMap<String,Object>();
    UserEntity entity = uRepo.findByUiIdAndUiPwd(login.getUi_id(), login.getUi_pwd());
    if(entity == null){
      map.put("status", false);
      map.put("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
    }
    else if(entity.getUiStatus() == 2){
      map.put("status", false);
      map.put("msg", "이용이 정지된 계정입니다.");
    }
  else if(entity.getUiStatus() == 3){
      map.put("status", false);
      map.put("msg", "이미 탈퇴한 계정입니다..");
    }
    else{
      map.put("status", true);
      map.put("msg", "정상 로그인 되었습니다.");
      map.put("login", new UserVO(entity));
    }
    return map;
  }
  public Map<String,Object>addUser(AddVO data){
    Map<String,Object> map = new LinkedHashMap<String,Object>();

    if(data.getId() == null || data.getId().equals("")){
      map.put("status", false);
      map.put("msg", "아이디를 입력해주세요");
    }
    else if(uRepo.countByUiId(data.getId()) != 0){
      map.put("status", false);
      map.put("msg", data.getId()+"은/는 이미 사용중인 아이디 입니다.");
    }
    else if(data.getPwd() == null || data.getPwd().equals("")){
      map.put("status", false);
      map.put("msg", "비밀번호를 입력해주세요");
    }
    else if(data.getName() == null || data.getName().equals("")){
      map.put("status", false);
      map.put("msg", "이름을 입력해주세요");
    }
    else{
      UserEntity entity = UserEntity.builder()
      .uiId(data.getId()).uiPwd(data.getPwd()).uiEmail(data.getEmail())
      .uiName(data.getName()).uiBirth(data.getBirth()).uiGen(data.getGen())
      .uiPhone(data.getPhone()).uiGrade(data.getType()).build();
      uRepo.save(entity);
      map.put("status", true);
      map.put("msg", "계정 등록이 완료되었습니다.");
    }
    return map;
  }

  public List<UserEntity> getUserList(){
    return uRepo.findAll();
  }

  public Map<String,Object>getUserList(String keyword, Pageable pageable){
    Page<UserEntity> page = uRepo.findByUiIdContains(keyword,pageable);
    Map<String,Object> map = new LinkedHashMap<String,Object>();
    map.put("list", page.getContent());
    map.put("total", page.getTotalElements());
    map.put("totalPage", page.getTotalPages());
    map.put("currentPage", page.getNumber());

    return map;
  }
  public void updateUserStatus(Integer value, Long uiSeq){
  UserEntity entity = uRepo.findById(uiSeq).get();
  entity.setUiStatus(value);
  uRepo.save(entity);
  }

  public void deleteUserStatus(Long uiSeq){
  uRepo.deleteById(uiSeq);
  }

public UserEntity getUserInfo(Long uiSeq){
  return uRepo.findById(uiSeq).get();
}

public Map<String,Object> updateUserInfo(UserUpdateVO data){
  Map<String,Object> map = new LinkedHashMap<String,Object>();
  Optional<UserEntity> entity = uRepo.findById(data.getSeq());
  String pattern = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$";
  if(entity.isEmpty()){
    map.put("status", false);
    map.put("msg", "잘못된 관리자 정보가 입력되었습니다.");
  }
  else if(data.getPwd().length() > 16 || data.getPwd().length()<8){
    map.put("status", false);
    map.put("msg", "비밀번호는 8-16자로 입력해주세요");
  }
  else if(data.getPwd().replaceAll(" ","").length() == 0 || !Pattern.matches(pattern,data.getPwd())){
  map.put("status", false);
  map.put("msg", "비밀번호에 특수문자 또는 공백문자를 입력할 수 없습니다.");
  }
 else if(data.getName().replaceAll(" ","").length() == 0 || !Pattern.matches(pattern,data.getName())){
  map.put("status", false);
  map.put("msg", "이름에 특수문자 또는 공백문자를 입력할 수 없습니다.");
  }
  else{
    UserEntity e = entity.get();
    e.setUiPwd(data.getPwd());
    e.setUiName(data.getName());
    e.setUiStatus(data.getStatus());
    e.setUiGrade(data.getGrade());
    uRepo.save(e);
    map.put("status", true);
  }
  return map;
}
}
