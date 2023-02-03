package com.team5.justdoeat.order.api;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team5.justdoeat.order.entity.OrderInfoEntity;
import com.team5.justdoeat.order.entity.OrderListEntity;
import com.team5.justdoeat.order.repository.OrderInfoRepository;
import com.team5.justdoeat.order.repository.OrderListRepository;
import com.team5.justdoeat.order.service.orderService;
import com.team5.justdoeat.order.vo.OrderInfoVO;
import com.team5.justdoeat.user.entity.UserInfoEntity;
import com.team5.justdoeat.user.repository.UserInfoRepository;

@RequestMapping("/order")
@RestController
public class orderController {
  
  @Autowired orderService orderService;

  @PostMapping("/add")
  public ResponseEntity<Object> postAddOrder(@RequestBody OrderInfoVO orderInfoVO){
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    resultMap = orderService.addOrderInfo(orderInfoVO);
    return new ResponseEntity<>(resultMap, HttpStatus.OK);
  }

  // @Autowired UserInfoRepository uRepo;
  // @Autowired OrderInfoRepository repo;
  // @GetMapping("/list")
  // public Map<String, Object> listOrder(@RequestParam Long userNo) {
  //   Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
  //   UserInfoEntity entity = uRepo.findByUiSeq(userNo);
  //   List<OrderInfoEntity> result = repo.findByUserInfoEntity(entity);
  //   resultMap.put("list", result);
  //   return resultMap;
  // }

  @Autowired OrderListRepository orderRepo;
  @GetMapping("/list")
  public Map<String, Object> listOrder(@RequestParam Long userNo) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    List<OrderListEntity> entity = orderRepo.findByUiSeq(userNo);
    resultMap.put("status", true);
    resultMap.put("totalCnt", entity.size());
    resultMap.put("list", entity);
    return resultMap;
  }
}
