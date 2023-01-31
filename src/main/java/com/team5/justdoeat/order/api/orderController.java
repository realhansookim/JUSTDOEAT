package com.team5.justdoeat.order.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order")
@RestController
public class orderController {
  
  @PostMapping("/add")
  public ResponseEntity<Object> postAddOrder(){
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    return new ResponseEntity<>(resultMap, (HttpStatus)resultMap.get("code"));
    
  }
}
