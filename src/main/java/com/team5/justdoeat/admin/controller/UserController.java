package com.team5.justdoeat.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.team5.justdoeat.admin.service.UserService;
import com.team5.justdoeat.admin.vo.AddVO;
import com.team5.justdoeat.admin.vo.LoginVO;
import com.team5.justdoeat.admin.vo.UserUpdateVO;
import com.team5.justdoeat.admin.vo.UserVO;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")

public class UserController {
  @Value("{just.file.userImg}") String userImgPath;
@Autowired UserService uService;
  
  @GetMapping("/login")
  public String getUserLogin(){
    return "/index";
  }

  @PostMapping("/login")
  public String postUserLogin(LoginVO login, HttpSession session, Model model){
  Map<String,Object> map = uService.loginUser(login);
    if((boolean)map.get("status")){
      session.setAttribute("loginUser", map.get("login"));
      return "/main";
    }
    else{
      model.addAttribute("msg", map.get("msg"));
      return "/index";
    }
  }
  
  @GetMapping("/add")
  public String getAddUser(){
    return "/user/add";
  }
  @PostMapping("/add")
  public String postAddUser(AddVO data, Model model){
    Map<String,Object> map = uService.addUser(data);
    if((boolean)map.get("status")){
      return "redirect:/";
    }
    model.addAttribute("inputdata", data);
    model.addAttribute("msg", map.get("msg"));
    return "/user/add";
  }

  @GetMapping("/logout")
  public String getLogout(HttpSession session){
    session.invalidate();
    return "redirect:/";
  }
  @GetMapping("/list")
  public String getUerList(Model model, @RequestParam @Nullable String keyword,
  @PageableDefault(size = 10, sort="uiSeq",direction = Sort.Direction.DESC)Pageable pageable
  ,HttpSession session
){
  session.setAttribute("update_result",null);
  UserVO user = (UserVO)session.getAttribute("loginUser");
  if(user == null){
    return "redirect:/";
  }
  else if(user.getUi_grade() != 99){
    return "redirect:/main";
  }
  if(keyword ==null){
    keyword = "";  
  }
  model.addAttribute("result", uService.getUserList(keyword, pageable));
  model.addAttribute("keyword", keyword);
return "/user/list";
  }
  @GetMapping("/update/status")
  public String getUserUpdateStatus(@RequestParam Integer value, @RequestParam Long uiSeq,
  @RequestParam Integer page, @RequestParam @Nullable String keyword){
    uService.updateUserStatus(value, uiSeq);
    String returnValue = "";
    if(keyword == null || keyword.equals("")) returnValue= "redirect:/user/list?page="+page;
    else returnValue="redirect:/user/list?page="+page+"keyword="+keyword;
    return returnValue;
  }

  @GetMapping("/delete")
  public String getUserDelete(@RequestParam Long uiSeq,
  @RequestParam Integer page, @RequestParam @Nullable String keyword, HttpSession session){
    UserVO user = (UserVO)session.getAttribute("loginUser");
    if(user == null){
      return "redirect:/";
    }
    else if(user.getUi_grade() != 99){
      return "redirect:/main";
    }
    uService.deleteUserStatus(uiSeq);
    String returnValue = "";
    if(keyword == null || keyword.equals("")) returnValue ="redirect:/user/list?page="+page;
    else
      returnValue = "redirect:/user/list?page="+page+"keywoed"+keyword;
      return returnValue;
    
  }

  @GetMapping("/detail")
  public String getUserDetail(@RequestParam Long uiSeq, Model model, HttpSession session){
    UserVO user = (UserVO)session.getAttribute("loginUser");
    if(user == null){
      return "redirect:/";
    }
    else if(user.getUi_grade() != 99){
      return "redirect:/main";
    }
    model.addAttribute("user_detail", uService.getUserInfo(uiSeq));
    return "/user/detail";
  }


  @PostMapping("/update")
  public String postUserUpdate(UserUpdateVO data, HttpSession session){
    System.out.println(data);
    Map<String,Object> map = uService.updateUserInfo(data);
    if((boolean)map.get("status")){
      return "redirect:/user/list";
    }
    session.setAttribute("update_result", map);
    return "redirect:/user/detail?uiSeq="+data.getSeq();
  }
}
