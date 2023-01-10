package com.team5.justdoeat.store.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.justdoeat.store.entity.StoreInfoEntity;
import com.team5.justdoeat.store.entity.StoreDetailEntity;
import com.team5.justdoeat.store.repository.StoreCategoryConnectRepository;
import com.team5.justdoeat.store.repository.StoreCategoryInfoRepository;
import com.team5.justdoeat.store.repository.StoreDetailRepository;
import com.team5.justdoeat.store.repository.StoreInfoRepository;
import com.team5.justdoeat.store.vo.StoreVO;
// import com.team5.justdoeat.store.vo.StoreVO;
import com.team5.justdoeat.user.entity.UserInfoEntity;

        
@RestController
@RequestMapping("/store")
public class StoreController {
    // @Autowired Service service;
    @Autowired StoreInfoRepository siRepo;
    @Autowired StoreDetailRepository sdRepo;
    @Autowired StoreCategoryInfoRepository sciRepo;
    @Autowired StoreCategoryConnectRepository sccRepo;

    // // 모든 데이터 보여주기
    // @GetMapping("/list")
    // public ResponseEntity<Object> listStore(StoreInfoEntity data) {
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    //     resultMap = service.listStore(data);
    //     return new ResponseEntity<>(resultMap, (HttpStatus)resultMap.get("code"));
    // }

    // // // 키워드로 검색
    // // @GetMapping("/search/{type}")
    // // public ResponseEntity



    // // 가게 기본정보만 검색
    // @PutMapping("/add/info")
    // public ResponseEntity<Object> AddStore(@RequestBody StoreInfoEntity data) {
    //     Map<String, Object> map = service.addStoreInfo(data);
    //     return new ResponseEntity<>(map, (HttpStatus) map.get("code"));
    // }

    // // 가게 상세정보 검색
    // @PutMapping("/add/detail")
    // public ResponseEntity<Object> AddStoreDetail(@RequestBody StoreDetailEntity data) {
    //     Map<String, Object> map = service.addStoreDetail(data);
    //     return new ResponseEntity<>(map, (HttpStatus) map.get("code"));
    // }

    // StoreInfoEntity, UserInfoEntity, StoreDetailEntity연결완료
    // 카테고리는 별도로 넣는방식이 안전함
    @PutMapping("/add/all")
    public String addAllStoreInfo(@RequestBody StoreVO data) {
        StoreInfoEntity entity = StoreInfoEntity.builder()
        .siName(data.getSiName())
        .siMinDeliveryTime(data.getSiMinDeliveryTime())
        .siMaxDeliveryTime(data.getSiMaxDeliveryTime())
        .siMainImg(data.getSiMainImg())
        .siStatus(data.getSiStatus())
        .siOrderCnt(data.getSiOrderCnt())
        .storeDetailList(
            StoreDetailEntity.builder()
            .sdAlarmContent(data.getSdAlarmContent())
            .sdAddress(data.getSdAddress())
            .sdAdditionalInfo(data.getSdAdditionalInfo())
            .sdOpenTime(data.getSdOpenTime())
            .sdCloseTime(data.getSdCloseTime())
            .sdPhone(data.getSdPhone())
            .sdMinPrice(data.getSdMinPrice())
            .sdPayment(data.getSdPayment())
            .sdBusinessNum(data.getSdBusinessNum())
            .sdBusinessName(data.getSdBusinessName())
            .sdOriginInfo(data.getSdOriginInfo())
            .build()
        )
        .userInfoList(
            UserInfoEntity.builder()
            .uiId(data.getUiId())
            .uiPwd(data.getUiPwd())
            .uiEmail(data.getUiEmail())
            .uiPhone(data.getSdPhone())
            .uiBirth(data.getUiBirth())
            .uiGen(data.getUiGen())
            .uiGrade(data.getUiGrade())
            .uiStatus(data.getSiStatus())
            .build()
        )
        .build();
        siRepo.save(entity);
        return "store_info_all_information_input_success";
    }
}