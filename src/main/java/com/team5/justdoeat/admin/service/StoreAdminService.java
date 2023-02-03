package com.team5.justdoeat.admin.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.team5.justdoeat.admin.entity.StoreEntity;
import com.team5.justdoeat.admin.repository.StoreRepository;
import com.team5.justdoeat.admin.vo.StoreUpdateVO;

@Service

public class StoreAdminService {
    @Autowired StoreRepository sRepo;

    
    public List<StoreEntity> getUserList(){
        return sRepo.findAll();
      }
      
    public Map<String,Object>getStoreList(String keyword, Pageable pageable){
        Page<StoreEntity> page = sRepo.findBySiNameContains(keyword,pageable);
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("list", page.getContent());
        map.put("total", page.getTotalElements());
        map.put("totalPage", page.getTotalPages());
        map.put("currentPage", page.getNumber());
    
        return map;
      }

      public void updateStoreStatus(Integer value, Long siSeq){
       StoreEntity entity = sRepo.findById(siSeq).get();
       entity.setSiStatus(value);
       sRepo.save(entity);
        }
      
        public void deleteUserStatus(Long siSeq){
        sRepo.deleteById(siSeq);
        }
      
      public StoreEntity getUserInfo(Long siSeq){
        return sRepo.findById(siSeq).get();
      }
      
      public Map<String,Object> updateStoreInfo(StoreUpdateVO data){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        StoreEntity entity = sRepo.findBySiSeq(data.getSi_seq());
       if(data.getSi_name().replaceAll(" ","").length() == 0){
        map.put("status", false);
        map.put("msg", "가게 이름에 특수문자 또는 공백문자를 입력할 수 없습니다.");
        }
        else{
          StoreEntity e = entity;
          e.setSiName(data.getSi_name());
          e.setSiMinDeliveryTime(data.getSi_min_delivery_time());
          e.setSiMaxDeliveryTime(data.getSi_max_delivery_time());
          e.setSiMinPrice(data.getSi_min_price());
          e.setSiStatus(data.getSi_status());
          sRepo.save(e);
          map.put("status", true);
        }
        return map;
      }
}

