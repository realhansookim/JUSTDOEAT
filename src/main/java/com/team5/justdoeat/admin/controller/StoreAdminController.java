package com.team5.justdoeat.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team5.justdoeat.admin.service.StoreAdminService;
import com.team5.justdoeat.admin.vo.StoreUpdateVO;
import com.team5.justdoeat.admin.vo.UserVO;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shop")
public class StoreAdminController {
@Autowired StoreAdminService storeService;
    @GetMapping("/list")
    public String getUerList(Model model, @RequestParam @Nullable String keyword,
    @PageableDefault(size = 10, sort="siSeq",direction = Sort.Direction.DESC)Pageable pageable
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
    model.addAttribute("result", storeService.getStoreList(keyword, pageable));
    model.addAttribute("keyword", keyword);
 
    return "/shop/list";
    } 
    
    @GetMapping("/update/status")
    public String getUserUpdateStatus(@RequestParam Integer value, @RequestParam Long siSeq,
    @RequestParam Integer page, @RequestParam @Nullable String keyword){
      storeService.updateStoreStatus(value, siSeq);
      String returnValue = "";
      if(keyword == null || keyword.equals("")) returnValue= "redirect:/shop/list?page="+page;
      else returnValue="redirect:/shop/list?page="+page+"keyword="+keyword;
      return returnValue;
    }
  
    @GetMapping("/delete")
    public String getUserDelete(@RequestParam Long siSeq,
    @RequestParam Integer page, @RequestParam @Nullable String keyword, HttpSession session){
      UserVO user = (UserVO)session.getAttribute("loginUser");
      if(user == null){
        return "redirect:/";
      }
      else if(user.getUi_grade() != 99){
        return "redirect:/main";
      }
      storeService.deleteUserStatus(siSeq);
      String returnValue = "";
      if(keyword == null || keyword.equals("")) returnValue ="redirect:/shop/list?page="+page;
      else
        returnValue = "redirect:/shop/list?page="+page+"keywoed"+keyword;
        return returnValue;
      
    }
  
    @GetMapping("/detail")
    public String getUserDetail(@RequestParam Long siSeq, Model model, HttpSession session){
     UserVO user = (UserVO)session.getAttribute("loginUser");
      if(user == null){
        return "redirect:/";
      }
      else if(user.getUi_grade() != 99){
        return "redirect:/main";
      }
      model.addAttribute("shop_detail", storeService.getUserInfo(siSeq));
      return "/shop/detail";
    }
  
  
    @PostMapping("/update")
    public String postUserUpdate(StoreUpdateVO data, HttpSession session){
      System.out.println(data);
      Map<String,Object> map = storeService.updateStoreInfo(data);
      if((boolean)map.get("status")){
        return "redirect:/shop/list";
      }
      session.setAttribute("update_result", map);
      return "redirect:/shop/detail?siSeq="+data.getSi_seq();
    }
  }
  

