package com.team5.justdoeat.store.api;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team5.justdoeat.store.entity.StoreCategoryConnectEntity;
import com.team5.justdoeat.store.entity.StoreCategoryInfoEntity;
import com.team5.justdoeat.store.entity.StoreDetailEntity;
import com.team5.justdoeat.store.entity.StoreInfoEntity;
import com.team5.justdoeat.store.repository.StoreCategoryConnectRepository;
import com.team5.justdoeat.store.repository.StoreCategoryInfoRepository;
import com.team5.justdoeat.store.repository.StoreDetailRepository;
import com.team5.justdoeat.store.repository.StoreInfoRepository;

import com.team5.justdoeat.store.service.StoreService;

import io.micrometer.common.lang.Nullable;

@RestController
@RequestMapping("/store")
public class StoreController {
    // @Autowired com.team5.justdoeat.store.service.service service;
    @Autowired StoreService sService;
    @Autowired StoreInfoRepository siRepo;
    @Autowired StoreDetailRepository sdRepo;
    @Autowired StoreCategoryInfoRepository sciRepo;
    @Autowired StoreCategoryConnectRepository sccRepo;

    // 가게 기본정보만 추가
    // http://localhost:9988/store/add/info
    @PutMapping("/add/info")
    public ResponseEntity<Object> putAddStore(@RequestBody StoreInfoEntity data) {
        Map<String, Object> map = sService.addStoreInfo(data);
        return new ResponseEntity<>(map, (HttpStatus) map.get("code"));
    }
    
    // 가게 기본정보만 검색
    // http://localhost:9988/store/info?page=
    @GetMapping("/info")
    public ResponseEntity<Object> getInfoList(@PageableDefault(size = 10) Pageable pageable) {
        return new ResponseEntity<>(sService.getStoreInfoList(pageable), HttpStatus.OK);
    }

    // 가게 상세정보 추가
    // http://localhost:9988/store/add/detail
    @PutMapping("/add/detail")
    public ResponseEntity<Object> putAddStoreDetail(@RequestBody StoreDetailEntity data) {
        Map<String, Object> map = sService.addStoreDetail(data);
        return new ResponseEntity<>(map, (HttpStatus) map.get("code"));
    }

    // 가게 상세정보만 검색
    // http://localhost:9988/store/detail?page=
    @GetMapping("/detail")
    public ResponseEntity<Object> getDetailList(@PageableDefault(size = 10) Pageable pageable) {
        return new ResponseEntity<>(sService.getStoreDetailList(pageable), HttpStatus.OK);
    }
    // 가게 카테고리 추가
    // http://localhost:9988/store/add/cate
    @PutMapping("/add/cate")
    public ResponseEntity<Object> putAddStoreCate(@RequestBody StoreCategoryInfoEntity data) {
        Map<String, Object> map = sService.addStoreCategory(data);
        return new ResponseEntity<>(map, (HttpStatus) map.get("code"));
    }

    // 가게 카테고리 연결 추가
    // http://localhost:9988/store/add/cate/connect
    @PutMapping("/add/cate/connect")
    public ResponseEntity<Object> putAddStoreCateConnect(@RequestBody StoreCategoryConnectEntity data) {
        Map<String, Object> map = sService.addStoreCategoryConnect(data);
        return new ResponseEntity<>(map, (HttpStatus) map.get("code"));
    }

    // 가게 키워드(가게이름)로 검색 - 메뉴검색도 같이..?
    // http://localhost:9988/store/search?keyword=숯불&page=
    @GetMapping("/search")
    public ResponseEntity<Object> getStoreList(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam @Nullable String keyword) {
        return new ResponseEntity<>(sService.getStoreList(pageable, keyword), HttpStatus.OK);
    }


    // 가게 카테고리 검색
    // http://localhost:9988/store/cate?page=
    @GetMapping("/cate")
    public ResponseEntity<Object> getCateList(@PageableDefault(size = 10) Pageable pageable) {
        return new ResponseEntity<>(sService.getStoreCateList(pageable), HttpStatus.OK);
    }

    









    /////////////////////////////////////////////////////////////////////////////////////////


    // 밑의 기능들은 포린키 연결하고부터 적용가능
    // 에러나도 정상적으로 돌아가긴함 - 찜찜하면은 주석처리하기


/* 

    // 모든 데이터 보여주기
    @GetMapping("/list")
    public ResponseEntity<Object> listStore(StoreInfoEntity data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = sService.listStore(data);
        return new ResponseEntity<>(resultMap, (HttpStatus) resultMap.get("code"));
    }

    // StoreInfoEntity, UserInfoEntity, StoreDetailEntity연결완료
    // 카테고리는 별도로 넣는방식이 안전함

    // http://localhost:9988/store/add/all
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
                                .uiName(data.getUiName())
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

 */

}