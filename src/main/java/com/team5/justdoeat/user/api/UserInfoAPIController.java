package com.team5.justdoeat.user.api;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.team5.justdoeat.review.vo.LoginUserVO;
import com.team5.justdoeat.user.VO.FindVO;
import com.team5.justdoeat.user.VO.LoginVo;
import com.team5.justdoeat.user.VO.UserInfoVO;
import com.team5.justdoeat.user.entity.UserInfoEntity;
import com.team5.justdoeat.user.repository.UserInfoRepository;
import com.team5.justdoeat.user.service.UserInfoService;



@RestController
@RequestMapping("/member")
public class UserInfoAPIController {

  @Autowired UserInfoRepository userRepo;
  @Autowired UserInfoService uService;

  @PutMapping("/join")
  public ResponseEntity<Object> putUserJOin(@RequestBody UserInfoEntity data){
    Map<String,Object> map = uService.addUser(data);

    return new ResponseEntity<Object>(map,(HttpStatus)map.get("code"));
  }

  @PostMapping("/login")

  public ResponseEntity<Object> userlogin(@RequestBody LoginVo data) {
    Map<String, Object> map = new LinkedHashMap<String, Object>();

    UserInfoEntity loginUser = userRepo.findByUiIdAndUiPwd(data.getId(), data.getPwd());
    System.out.println(loginUser);
    if (loginUser == null) {
      map.put("status", false);
      map.put("msg", "아이디 또는 비밀번호를 확인해주세요");
      return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
    }
    map.put("status", true);
    map.put("msg", "로그인되었습니다.");
    map.put("user", loginUser);
    return new ResponseEntity<>(map, HttpStatus.CREATED);

  }
  @GetMapping("/list")
  
  public ResponseEntity<Object> getProductList(Pageable pageable) {

    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put(("list"), userRepo.findAll(pageable));
    return new ResponseEntity<>(map, HttpStatus.CREATED);
  }

  @PutMapping("/findid")
  public ResponseEntity<Object> userId(@RequestBody UserInfoEntity data){
    Map<String,Object> map = new LinkedHashMap<String,Object>();
    UserInfoEntity findUser = userRepo.findByUiNameAndUiEmail(data.getUiName(), data.getUiEmail());
    if(findUser == null){
      map.put("status", false);
      map.put("msg", "일치하는 회원 정보가 없습니다.");
      return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
    }
    else{
      map.put("status",true);
      map.put("msg", "회원님의 아이디"+findUser.getUiId()+"는입니다.");
      // map.put("loginUser", userRepo.findById(data.getUiSeq()));

    }
    return new ResponseEntity<Object>(map,HttpStatus.OK);
  }
    



  }








  


