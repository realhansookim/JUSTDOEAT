package com.team5.justdoeat.store.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.team5.justdoeat.store.entity.StoreAlarmImageEntity;
import com.team5.justdoeat.store.entity.StoreCategoryConnectEntity;
import com.team5.justdoeat.store.entity.StoreCategoryInfoEntity;
import com.team5.justdoeat.store.entity.StoreDetailEntity;
import com.team5.justdoeat.store.entity.StoreImageEntity;
import com.team5.justdoeat.store.entity.StoreInfoEntity;
import com.team5.justdoeat.store.repository.StoreAlarmImageRepository;
import com.team5.justdoeat.store.repository.StoreCategoryConnectRepository;
import com.team5.justdoeat.store.repository.StoreCategoryInfoRepository;
import com.team5.justdoeat.store.repository.StoreDetailRepository;
import com.team5.justdoeat.store.repository.StoreImageRepository;
import com.team5.justdoeat.store.repository.StoreInfoRepository;

@Service
public class StoreService {
    @Autowired StoreCategoryConnectRepository storeCateConRepo;
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

    // 가게 기본정보 검색할때 정보 표시 기능
    public Map<String, Object> getStoreInfoList(Pageable pageable) {
        Page<StoreInfoEntity> page = sInfoRepo.getStoreInfoList(pageable);
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("totalPage", page.getTotalPages());
        resultMap.put("currentPage", page.getNumber() + 1);
        resultMap.put("totalCount", page.getTotalElements());
        resultMap.put("list", page.getContent());
        return resultMap;
    }

    // 가게 상세정보 검색할때 정보 표시 기능
    public Map<String, Object> getStoreDetailList(Pageable pageable) {
        Page<StoreDetailEntity> page = sDetailRepo.getStoreDetailList(pageable);
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("totalPage", page.getTotalPages());
        resultMap.put("currentPage", page.getNumber() + 1);
        resultMap.put("totalCount", page.getTotalElements());
        resultMap.put("list", page.getContent());
        return resultMap;
    }

    // 가게 기본정보 추가할때 메시지 표시 기능
    public Map<String, Object> addStoreInfo(StoreInfoEntity data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        sInfoRepo.save(data);
        map.put("status", true);
        map.put("message", "가게 기본정보가 추가 되었습니다.");
        map.put("code", HttpStatus.CREATED);
        return map;
    }

    // 가게 상세정보 추가할떄 메시지 표시 기능
    public Map<String, Object> addStoreDetail(StoreDetailEntity data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        sDetailRepo.save(data);
        map.put("status", true);
        map.put("message", "가게 상세정보가 추가 되었습니다.");
        map.put("code", HttpStatus.CREATED);
        return map;
    }

    // 가게 카테고리 추가할때 메시지 표시 기능
    public Map<String, Object> addStoreCategory(StoreCategoryInfoEntity data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        storeCateInfoRepo.save(data);
        map.put("status", true);
        map.put("message", "가게 카테고리 정보가 추가 되었습니다.");
        map.put("code", HttpStatus.CREATED);
        return map;
    }

    // 가게 카테고리 검색할때 정보 표시 기능
    public Map<String, Object> getStoreCateList(Pageable pageable) {
        Page<StoreCategoryInfoEntity> page = storeCateInfoRepo.getStoreCateList(pageable);
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("totalPage", page.getTotalPages());
        resultMap.put("currentPage", page.getNumber() + 1);
        resultMap.put("totalCount", page.getTotalElements());
        resultMap.put("list", page.getContent());
        return resultMap;
    }

    // 가게 검색 기능
    // Test1
    public Map<String, Object> getStoreList(Pageable pageable, String keyword) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (keyword == null) {
            keyword = "";
        }
        Page<StoreInfoEntity> page = sInfoRepo.findBySiName(pageable, keyword);
        resultMap.put("keyword", keyword);
        resultMap.put("totalPage", page.getTotalPages());
        resultMap.put("currentPage", page.getNumber() + 1);
        resultMap.put("totalCount", page.getTotalElements());
        resultMap.put("list", page.getContent());
        return resultMap;
    }


    // 가게 카테고리 연결 추가할때 메시지 표시 기능
    public Map<String, Object> addStoreCategoryConnect(StoreCategoryConnectEntity data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        storeCateConRepo.save(data);
        map.put("status", true);
        map.put("message", "가게 카테고리가 연결 되었습니다.");
        map.put("code", HttpStatus.CREATED);
        return map;
    }

    @Autowired StoreImageRepository imageRepo;
    public Map<String, Object> addStoreImage(StoreImageEntity data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        imageRepo.save(data);
        return map;
    }

    public String getIiFileNameByUri (String simgUri) {
        List<StoreImageEntity> data = imageRepo.findBySimgUri(simgUri);
        return data.get(0).getSimgFileName();
    }

    ////////////////////////////////////// 알람 이미지
    
    @Autowired StoreAlarmImageRepository alarmRepo;
    public Map<String, Object> addAlarmImage(StoreAlarmImageEntity data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        alarmRepo.save(data);
        return map;
    }

    public String getIiFileNameByUri1 (String saiUri) {
        List<StoreAlarmImageEntity> data = alarmRepo.findBySaiUri(saiUri);
        return data.get(0).getSaiFileName();
    }
}
