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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team5.justdoeat.review.vo.LoginUserVO;
import com.team5.justdoeat.user.VO.FindVO;
import com.team5.justdoeat.user.VO.LoginVo;
import com.team5.justdoeat.user.VO.UserInfoVO;
import com.team5.justdoeat.user.entity.UserInfoEntity;
import com.team5.justdoeat.user.repository.UserInfoRepository;
import com.team5.justdoeat.user.service.UserInfoService;

import io.micrometer.common.lang.Nullable;



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
    if (loginUser == null) {
      map.put("status", false);
      map.put("msg", "아이디 또는 비밀번호를 확인해주세요");
      return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
    }
    map.put("status", true);
    map.put("msg", "로그인되었습니다.");
    return new ResponseEntity<>(map, HttpStatus.CREATED);

  }
  // @GetMapping("/list")
  
  // public ResponseEntity<Object> getProductList(Pageable pageable) {

  //   Map<String, Object> map = new LinkedHashMap<String, Object>();
  //   map.put(("list"), userRepo.findAll(pageable));
  //   return new ResponseEntity<>(map, HttpStatus.CREATED);
  // }
@GetMapping("list")
public Map<String,Object> getUserList(Pageable pageable, @RequestParam @Nullable String keyword, @RequestParam @Nullable Integer page){
  Map<String,Object> map = new LinkedHashMap<String,Object>();

  if(keyword == null){
    keyword = "";
  }
  if(page == null){
    page = 1;
  }
  map.put("list", userRepo.findAll(pageable));
  map.put("currentPage",page);
  map.put("totalCount",userRepo.count());
return map;
}
  @PutMapping("/findid")
  public ResponseEntity<Object> userId(@RequestBody UserInfoEntity data){
    Map<String,Object> map = new LinkedHashMap<String,Object>();
    UserInfoEntity findUser = userRepo.findByUiNameAndUiEmail(data.getUiName(), data.getUiEmail());
    System.out.println(findUser);
    if(findUser == null){
      map.put("status", false);
      map.put("msg", "일치하는 회원 정보가 없습니다.");
      return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
    }
    else{
      map.put("status",true);
      map.put("msg", "회원님의 아이디는 "+findUser.getUiId()+" 입니다.");
      // map.put("loginUser", userRepo.findById(data.getUiSeq()));

    }
    return new ResponseEntity<Object>(map,HttpStatus.OK);

    }
    @PutMapping("/findpw")
    public ResponseEntity<Object> userPw(@RequestBody UserInfoEntity data){
      Map<String,Object> map = new LinkedHashMap<String,Object>();
      UserInfoEntity findUser = userRepo.findByUiIdAndUiNameAndUiEmail(data.getUiId(),data.getUiName(), data.getUiEmail());
      System.out.println(findUser);
      if(findUser == null){
        map.put("status", false);
        map.put("msg", "일치하는 회원 정보가 없습니다.");
        return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
      }
      else{
        map.put("status",true);
        map.put("msg", "회원님의 비밀번호는"+ findUser.getUiPwd() +"입니다.");
      }
      return new ResponseEntity<Object>(map,HttpStatus.OK);
    }
    
  @PutMapping("/idCheck")
    public ResponseEntity<Object> idcheck(@RequestBody UserInfoEntity data){
      Map<String,Object> map = new LinkedHashMap<String,Object>();
      UserInfoEntity findUser = userRepo.findByUiId(data.getUiId());
      if(findUser != null) {
        map.put("status", false);
        map.put("msg", "이미 가입된 아이디입니다.");
      }
      else {
        map.put("status", true);
        map.put("msg", "가입 가능한 아이디입니다.");
      }
      return new ResponseEntity<Object>(map,HttpStatus.OK); 
    }

    @PutMapping("/emailCheck")
    public ResponseEntity<Object> emailCheck(@RequestBody UserInfoEntity data){
      Map<String,Object> map = new LinkedHashMap<String,Object>();
      UserInfoEntity findUser = userRepo.findByUiEmail(data.getUiEmail());
      if(findUser != null) {
        map.put("status", false);
        map.put("msg", "이미 가입된 이메일입니다.");
      }
      else {
        map.put("status", true);
        map.put("msg", "가입 가능한 이메일입니다.");
      }
      return new ResponseEntity<Object>(map,HttpStatus.OK); 
    }

    @PutMapping("/phoneCheck")
    public ResponseEntity<Object> phoneCheck(@RequestBody UserInfoEntity data){
      Map<String,Object> map = new LinkedHashMap<String,Object>();
      UserInfoEntity findUser = userRepo.findByUiPhone(data.getUiPhone());
      if(findUser != null) {
        map.put("status", false);
        map.put("msg", "이미 가입된 번호입니다.");
      }
      else {
        map.put("status", true);
        map.put("msg", "가입 가능한 번호입니다.");
      }
      return new ResponseEntity<Object>(map,HttpStatus.OK); 
    }

    @DeleteMapping("/delete")
    public Map<String, Object> deleteUser(@RequestParam Long userNo) {
      Map<String,Object> map = uService.deleteUser(userNo);
      return map;
    }

    @PatchMapping("/update")
    public Map<String, Object> updateUser(@RequestParam Long userNo, @RequestBody UserInfoEntity data) {
      Map<String,Object> map = uService.updateUser(userNo, data);
      return map;
    }
}








  


