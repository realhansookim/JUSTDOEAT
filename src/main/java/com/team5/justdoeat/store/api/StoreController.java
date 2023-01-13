package com.team5.justdoeat.store.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.justdoeat.store.entity.StoreDetailEntity;
import com.team5.justdoeat.store.entity.StoreInfoEntity;
import com.team5.justdoeat.store.repository.StoreCategoryConnectRepository;
import com.team5.justdoeat.store.repository.StoreCategoryInfoRepository;
import com.team5.justdoeat.store.repository.StoreDetailRepository;
import com.team5.justdoeat.store.repository.StoreInfoRepository;
import com.team5.justdoeat.store.vo.StoreVO;
import com.team5.justdoeat.user.entity.UserInfoEntity;

import com.team5.justdoeat.store.service.StoreService;

@RestController
@RequestMapping("/store")
public class StoreController {
    // @Autowired com.team5.justdoeat.store.service.service service;
    @Autowired StoreService sService;
    @Autowired StoreInfoRepository siRepo;
    @Autowired StoreDetailRepository sdRepo;
    @Autowired StoreCategoryInfoRepository sciRepo;
    @Autowired StoreCategoryConnectRepository sccRepo;

    // 키워드로 검색
    // @GetMapping("/search/{type}")
    // public ResponseEntity

    // 가게 기본정보만 추가
    @PutMapping("/add/info")
    public ResponseEntity<Object> AddStore(@RequestBody StoreInfoEntity data) {
        Map<String, Object> map = sService.addStoreInfo(data);
        return new ResponseEntity<>(map, (HttpStatus) map.get("code"));
    }

    // 가게 상세정보 추가
    @PutMapping("/add/detail")
    public ResponseEntity<Object> AddStoreDetail(@RequestBody StoreDetailEntity data) {
        Map<String, Object> map = sService.addStoreDetail(data);
        return new ResponseEntity<>(map, (HttpStatus) map.get("code"));
    }

    // 가게 기본정보만 보여주기
    @GetMapping("/info")
    public ResponseEntity<Object> getInfoList(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(sService.getStoreInfoList(pageable), HttpStatus.OK);
    }

    // 가게 상세정보만 보여주기
    @GetMapping("/detail")
    public ResponseEntity<Object> getDetailList(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(sService.getStoreDetailList(pageable), HttpStatus.OK);
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    // 밑의 기능들은 포린키 연결하고부터 적용가능

    // 모든 데이터 보여주기
    @GetMapping("/list")
    public ResponseEntity<Object> listStore(StoreInfoEntity data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = sService.listStore(data);
        return new ResponseEntity<>(resultMap, (HttpStatus) resultMap.get("code"));
    }

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
                                .build())
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
                                .build())
                .build();
        siRepo.save(entity);
        return "store_info/store_Detail/user_info_data_input_success";
    }
}