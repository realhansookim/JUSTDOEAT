package com.team5.justdoeat.patch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.justdoeat.menu.entity.MenuInfoEntity;
import com.team5.justdoeat.menu.repository.MenuInfoRepository;
import com.team5.justdoeat.store.entity.StoreDetailEntity;
import com.team5.justdoeat.store.repository.StoreDetailRepository;
import com.team5.justdoeat.user.entity.UserInfoEntity;
import com.team5.justdoeat.user.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PatchController {
  private final StoreDetailRepository storeDetailRepo; 
  // private final MenuInfoRepository menuInfoRepo;
  private final MenuInfoRepository menuinfoRepo;
  private final UserInfoRepository userRepo;


  @GetMapping("/patch")
  public String patchLineBreak() {
    List<StoreDetailEntity> listAll = storeDetailRepo.findAll();
    for(StoreDetailEntity entity : listAll) {
      String alarm = entity.getSdAlarmContent();
      alarm = alarm.replaceAll("＼n", "\n");
      alarm = alarm.replaceAll("</br>", "\n");
      alarm = alarm.replaceAll("<br>", "\n");
      alarm = alarm.replaceAll("<br/>", "\n");
      System.out.println(alarm);
      entity.setSdAlarmContent(alarm);
      entity.setSdOriginInfo(alarm);
    }
    storeDetailRepo.saveAll(listAll);
    return "finished!";
  }
  @GetMapping("/patcher")
  public String patchLineBreaker() {
    List<MenuInfoEntity> listAll = menuinfoRepo.findAll();
    for(MenuInfoEntity entity : listAll) {
      String alarm = entity.getMiAdditionalEx();
      alarm = alarm.replaceAll("＼n", "\n");
      alarm = alarm.replaceAll("</br>", "\n");
      alarm = alarm.replaceAll("<br>", "\n");
      alarm = alarm.replaceAll("<br/>", "\n");
      System.out.println(alarm);
      entity.setMiAdditionalEx(alarm);
      
    }
    menuinfoRepo.saveAll(listAll);
    return "finished!";

}
  @GetMapping("/patcher1")
  public String patchLineBreaker1() {
    List<UserInfoEntity> listAll = userRepo.findAll();
    for(UserInfoEntity entity : listAll) {
      String alarm = entity.getUiPhone();
      alarm = alarm.replaceAll("-", "");
      alarm = alarm.replaceAll("(요기요 제공 번호)", "");
      System.out.println(alarm);
      entity.setUiPhone(alarm);
      
    }
    userRepo.saveAll(listAll);
    return "finished!";

}
}
