package com.team5.justdoeat.store.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.team5.justdoeat.store.entity.StoreDetailEntity;
import com.team5.justdoeat.store.entity.StoreInfoEntity;
import com.team5.justdoeat.store.repository.StoreCategoryConnectRepository;
import com.team5.justdoeat.store.repository.StoreCategoryInfoRepository;
import com.team5.justdoeat.store.repository.StoreDetailRepository;
import com.team5.justdoeat.store.repository.StoreImageRepository;
import com.team5.justdoeat.store.repository.StoreInfoRepository;

@Service
public class service {
   
    @Autowired StoreCategoryConnectRepository storeCateRepo;
    @Autowired StoreCategoryInfoRepository storeCateInfoRepo;
    @Autowired StoreDetailRepository sDetailRepo;
    // @Autowired StoreImageRepository sImgRepo;
    @Autowired StoreInfoRepository sInfoRepo;

    public Map<String, Object> listStore(StoreInfoEntity data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("list", sInfoRepo.findAll());
        resultMap.put("code", HttpStatus.OK);
        return resultMap;
    }

    public Map<String, Object> addStoreInfo(StoreInfoEntity data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        sInfoRepo.save(data);
        map.put("status", true);
        map.put("code", HttpStatus.CREATED);
        return map;
    }
    public Map<String, Object> addStoreDetail(StoreDetailEntity data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        sDetailRepo.save(data);
        map.put("status", true);
        map.put("code", HttpStatus.CREATED);
        return map;
      }
}
