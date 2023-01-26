package com.team5.justdoeat.store.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.team5.justdoeat.store.entity.StoreCategoryInfoEntity;
import com.team5.justdoeat.store.entity.StoreDetailEntity;
import com.team5.justdoeat.store.entity.StoreInfoEntity;
import com.team5.justdoeat.store.repository.StoreCategoryConnectRepository;
import com.team5.justdoeat.store.repository.StoreCategoryInfoRepository;
import com.team5.justdoeat.store.repository.StoreDetailRepository;
import com.team5.justdoeat.store.repository.StoreInfoRepository;
import com.team5.justdoeat.store.vo.StoreVO;
import com.team5.justdoeat.user.entity.UserInfoEntity;

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

    // 가게 상세정보 추가
    // http://localhost:9988/store/add/detail
    @PutMapping("/add/detail")
    public ResponseEntity<Object> putAddStoreDetail(@RequestBody StoreDetailEntity data) {
        Map<String, Object> map = sService.addStoreDetail(data);
        return new ResponseEntity<>(map, (HttpStatus) map.get("code"));
    }

    // 가게 카테고리 추가
    // http://localhost:9988/store/add/cate
    @PutMapping("/add/cate")
    public ResponseEntity<Object> putAddStoreCate(@RequestBody StoreCategoryInfoEntity data) {
        Map<String, Object> map = sService.addStoreCategory(data);
        return new ResponseEntity<>(map, (HttpStatus) map.get("code"));
    }

    // 가게 기본정보만 보여주기
    // http://localhost:9988/store/info?page=
    @GetMapping("/info")
    public ResponseEntity<Object> getInfoList(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(sService.getStoreInfoList(pageable), HttpStatus.OK);
    }

    // 가게 상세정보만 보여주기
    // http://localhost:9988/store/detail?page=
    @GetMapping("/detail")
    public ResponseEntity<Object> getDetailList(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(sService.getStoreDetailList(pageable), HttpStatus.OK);
    }

    // 가게 키워드(가게이름)로 검색 - 메뉴검색도 같이..?
    // http://localhost:9988/store/search?keyword=숯불&page=
    @GetMapping("/search")
    public ResponseEntity<Object> getStoreList(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam @Nullable String keyword) {
        return new ResponseEntity<>(sService.getStoreList(pageable, keyword), HttpStatus.OK);
    }

    @Autowired StoreCategoryConnectRepository rrepo;
    @GetMapping("/test")
    public Map<String, Object> getCate(@RequestParam @Nullable String cate) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    if(cate == null) {
        resultMap.put("list", rrepo.findAll());
    }
    else if(cate.equals("일식/돈까스")) {
        resultMap.put("keyword", cate);
        resultMap.put("list", rrepo.findByScCiSeq(1L));
    }
    else if(cate.equals("치킨")) {
        resultMap.put("keyword", cate);
        resultMap.put("list", rrepo.findByScCiSeq(2L));
    }
    else if(cate.equals("족발/보쌈")) {
        resultMap.put("keyword", cate);
        resultMap.put("list", rrepo.findByScCiSeq(3L));
    }
    else if(cate.equals("중국집")) {
        resultMap.put("keyword", cate);
        resultMap.put("list", rrepo.findByScCiSeq(4L));
    }
    else if(cate.equals("한식")) {
        resultMap.put("keyword", cate);
        resultMap.put("list", rrepo.findByScCiSeq(5L));
    }
    else if(cate.equals("피자/양식")) {
        resultMap.put("keyword", cate);
        resultMap.put("list", rrepo.findByScCiSeq(6L));
    }
    else if(cate.equals("카페/디저트")) {
        resultMap.put("keyword", cate);
        resultMap.put("list", rrepo.findByScCiSeq(7L));
    }
    else if(cate.equals("분식")) {
        resultMap.put("keyword", cate);
        resultMap.put("list", rrepo.findByScCiSeq(8L));
    }
    else if(cate.equals("야식")) {
        resultMap.put("keyword", cate);
        resultMap.put("list", rrepo.findByScCiSeq(9L));
    }
    else if(cate.equals("편의점/마트")) {
        resultMap.put("keyword", cate);
        resultMap.put("list", rrepo.findByScCiSeq(10L));
    }
    else if(cate.equals("프랜차이즈")) {
        resultMap.put("keyword", cate);
        resultMap.put("list", rrepo.findByScCiSeq(11L));
    }
    else {
        resultMap.put("message", "카테고리를 다시 확인해주세요.");
    }
    return resultMap;
}

    /////////////////////////////////////////////////////////////////////////////////////////

/* 

    // 밑의 기능들은 포린키 연결하고부터 적용가능
    // 에러나도 정상적으로 돌아가긴함 - 찜찜하면은 주석처리하기

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

 */

}