package com.team5.justdoeat.user.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team5.justdoeat.user.VO.LoginVo;
import com.team5.justdoeat.user.VO.UserInfoVO;
import com.team5.justdoeat.user.entity.UserInfoEntity;
import com.team5.justdoeat.user.repository.UserInfoRepository;
import com.team5.justdoeat.user.service.UserInfoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller


public class UserInfoAPIController {

  @Autowired UserInfoService uService;
  @Autowired UserInfoRepository userRepo;

@GetMapping("/")
public String getMain(){
  return "/index";
}

@GetMapping("/login")
public String getLogin(HttpServletRequest response){
  response.setAttribute("Cache-Control", "no-cache, no-store,must-revalidate");
  response.setAttribute("Program", "no-cache");
  response.setAttribute("Expires", "0");
  return "/login";
}
@PostMapping("/login")
public String postLogin(String id,String pwd, Model model,HttpSession session){
  UserInfoEntity loginUser = userRepo.findByUiIdAndUiPwd(id, pwd);
  if(loginUser == null){
    model.addAttribute("loginStatus", "failed");
    model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
    return "/login";
  }
  session.setAttribute("loginUser",new LoginVo(loginUser));
  return "redirect:/";
}

@GetMapping("/join")
public String getUSerJoin(){
  return "/user/join";
}

@PostMapping("/join")
public String postLogin(Model model,String id, String pwd, String name){

      userRepo.save((UserInfoEntity.builder().uiId(id).uiPwd(pwd).uiName(name).build()));
   
      return "/login";
    }
  

  // @GetMapping("/list")
  // public ResponseEntity<Object> getUserList(HttpSession session) {
  //   Map<String, Object> map = uService.getUserList(session);
  //   return new ResponseEntity<>(map, (HttpStatus) map.get("code"));
  // }



@GetMapping("/loginlist")
  public String getLoginList(HttpSession session, Pageable pageable, Model model){
      UserInfoVO login = (UserInfoVO)session.getAttribute("loginUser");
      if(login == null){ //로그인 데이터 없음
        return "redirect:/login";
      }
    Page<UserInfoEntity>page =userRepo.findAll((pageable));

    // List<AdminAccountEntity> list = adminAccountRepository.findAll();
    List<UserInfoVO> accountList = new ArrayList<UserInfoVO>();
    for(UserInfoEntity a : page.getContent()){
      accountList.add(new UserInfoVO(a));
    }
    // http://localhost:9501/member/list?page=0&size=10&sort=aiSeq,desc
    
    model.addAttribute("accountList", accountList);
    model.addAttribute("totalPage", page.getTotalPages());
    model.addAttribute("totalCount", page.getTotalElements());
    model.addAttribute("getMemberJoin()",page.getNumber());
    return "/member/list";
  }
  @GetMapping("/status")
  public String getMemberStatusUpdate(@RequestParam Long seq,
  @RequestParam Integer status){
    UserInfoEntity entity = userRepo.findByUiSeq(seq); //select하고
    entity.setUiStatus(status); // ui_status 값을 바꾸고
    userRepo.save(entity); // 저장하고
    return "redirect:/member/loginlist";
  }
  @GetMapping ("/delete")
  public String getMamberDelete(@RequestParam Long seq){
    UserInfoEntity entity = userRepo.findByUiSeq(seq);
    userRepo.delete(entity);
    return "redirect:/member/loginlist";
  }
  @GetMapping("/findid")
  public String dofindLoginId(HttpServletRequest request, HttpServletResponse response){
    String name = request.getParameter("uiName");
    String email = request.getParameter("uiEmail");
      UserInfoEntity user = uService.getUserUiNameAndUiEmail(name, email);
      if( user == null){
        request.setAttribute("msg", "일치하는 회원이 존재하지 않습니다.");
        return "/";
      }
      request.setAttribute("msg", name +"의 아이디는" + user.getUiId() + "입니다.");
      return "redirect:/";
  }
  @GetMapping("/findpwd")
  public String dofindLoginPwd(HttpServletRequest request, HttpServletResponse response){
    String id = request.getParameter("id");
    String name =request.getParameter("name");
    UserInfoEntity user = uService.getUserByUiId(id);
    if(user == null){
      request.setAttribute("msg", "일치하는 회원이 없습니다.");
      return "/";
    }
    else if(user.getUiEmail().equals(name) == false){
      request.setAttribute("msg", "이메일 주소가 일치하지 않습니다.");
      return "/";
    }
    request.setAttribute("msg", user.getUiName()+"의 비밀번호는"+user.getUiPwd()+"입니다.");
    return "direct:/";
  }
}

