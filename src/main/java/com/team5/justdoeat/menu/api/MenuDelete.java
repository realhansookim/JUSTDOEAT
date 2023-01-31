package com.team5.justdoeat.menu.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team5.justdoeat.menu.entity.MenuInfoEntity;
import com.team5.justdoeat.menu.repository.MenuInfoRepository;

@RestController
public class MenuDelete {
  @Autowired MenuInfoRepository mRepo;
  @DeleteMapping("/delete/menu")
  public String deleteMember(@RequestParam Long storeNo) {
    MenuInfoEntity entity = mRepo.findByMiSeq(storeNo);
    if(entity == null) {
      return "번호확인";
    }
    mRepo.delete(entity);
    return "성공";
  }

  @PatchMapping("/update/menu")
  public String updateMember(@RequestParam Long storeNo, @RequestBody MenuInfoEntity data) {
    MenuInfoEntity entity = mRepo.findByMiSeq(storeNo);
    if(entity == null) {
      return "번호확인";
    }
    entity.setMiName(data.getMiName());
    entity.setMiPrice(data.getMiPrice());
    entity.setMiImg(data.getMiImg());
    mRepo.save(entity);
    return "성공";
  }
}
