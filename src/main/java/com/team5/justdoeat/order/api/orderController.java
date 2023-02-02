package com.team5.justdoeat.order.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.justdoeat.order.service.orderService;
import com.team5.justdoeat.order.vo.OrderInfoVO;

@RequestMapping("/order")
@RestController
public class orderController {
  
  @Autowired orderService orderService;

  @PostMapping("/add")
  public ResponseEntity<Object> postAddOrder(OrderInfoVO orderInfoVO){
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    resultMap = orderService.addOrderInfo(orderInfoVO);
    
    return new ResponseEntity<>(resultMap, (HttpStatus)resultMap.get("code"));
    
  }
}
