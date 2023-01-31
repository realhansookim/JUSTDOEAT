package com.team5.justdoeat.menu.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.team5.justdoeat.menu.entity.MenuImageInfoEntity;
import com.team5.justdoeat.menu.entity.MenuInfoEntity;
import com.team5.justdoeat.menu.repository.MenuImageInfoRepository;
import com.team5.justdoeat.menu.repository.MenuInfoRepository;

@Service
public class MenuService {
  @Autowired MenuImageInfoRepository imageRepo;

  public Map<String, Object> addmenuimageInfo(MenuImageInfoEntity data) {
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    imageRepo.save(data);
    return map;
  }

  public String getIiFileNameByUri (String mimgUri) {
    List<MenuImageInfoEntity> data = imageRepo.findByMimgUri(mimgUri);
    return data.get(0).getMimgFilename();
  }
}