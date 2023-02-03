package com.team5.justdoeat.order.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team5.justdoeat.order.entity.MenuCartEntity;
import com.team5.justdoeat.order.entity.MenuOptionCartEntity;
import com.team5.justdoeat.order.entity.OrderInfoEntity;
import com.team5.justdoeat.order.repository.MenuCartRepository;
import com.team5.justdoeat.order.repository.MenuOptionCartRepository;
import com.team5.justdoeat.order.repository.OrderInfoRepository;
import com.team5.justdoeat.order.vo.MenuInfoVO;
import com.team5.justdoeat.order.vo.OptionInfo;
import com.team5.justdoeat.order.vo.OrderInfoVO;
import com.team5.justdoeat.store.repository.StoreInfoRepository;
import com.team5.justdoeat.user.entity.UserInfoEntity;
import com.team5.justdoeat.user.repository.UserInfoRepository;

@Service
public class orderService {

  @Autowired UserInfoRepository userRepo;
  @Autowired StoreInfoRepository storeRepo;
  @Autowired OrderInfoRepository orderRepo;
  @Autowired MenuOptionCartRepository menuOpCartRepo;
  @Autowired MenuCartRepository menuCartRepo;


  public Map<String, Object> addOrderInfo(OrderInfoVO data) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    if (data == null){
      resultMap.put("status",false);
      resultMap.put("message","주문이 실패하였습니다");
      return resultMap;
    }
    else{
      OrderInfoEntity orderEntity = OrderInfoEntity.builder().
                                      oiSeq(null).
                                      oiRequest(data.getRequest()).
                                      oiPayment(data.getPayment()).
                                      oiAddress(data.getAddress()).
                                      oidetailAddress(data.getDetailAddress()).
                                      oiPhone(data.getPhone()).
                                      oiRegDt(LocalDateTime.now()).
                                      oiTotalPrice(data.getTotalPrice()).
                                      oideliveryFee(data.getDeliveryFee()).
                                      oiCoupon(data.getCoupon()).
                                      oipaymentPrice(data.getPaymentPrice()).
                                      oiName(ramdomString()).
                                      userInfoEntity(userRepo.findByUiSeq(data.getUserSeq())).
                                      storeInfo(storeRepo.findBySiSeq(data.getStoreSeq())).build();
    orderRepo.save(orderEntity);
    addMenuCart(orderEntity, data.getMenuList());
    }
    resultMap.put("status",true);
    resultMap.put("message","결제 완료되었습니다");
    return resultMap;
  }



  private void addMenuCart(OrderInfoEntity orderEntity, List<MenuInfoVO> menuList) {
    MenuInfoVO menuInfoVO;
    for(int i = 0; i < menuList.size(); i++){
      menuInfoVO = menuList.get(i);
      MenuCartEntity menuCart = MenuCartEntity.builder().mcSeq(null).mcMenuCnt(menuInfoVO.getMenuCnt()).mcOiSeq(orderEntity.getOiSeq()).mcMiSeq(menuInfoVO.getMenuSeq()).build();
      menuCart = menuCartRepo.save(menuCart);
      for(int j = 0; j < menuInfoVO.getOptionList().size(); j++){
        MenuOptionCartEntity menuOptionCart =  MenuOptionCartEntity.builder().mocSeq(null).mocMcSeq(menuCart.getMcSeq()).mocMoSeq(menuInfoVO.getOptionList().get(j).getOptionSeq()).build();
        menuOpCartRepo.save(menuOptionCart);
      }
    }
  }



  public String ramdomString() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();
    String generatedString = random.ints(leftLimit, rightLimit + 1)
                                   .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                                   .limit(targetStringLength)
                                   .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                   .toString();
    return generatedString;
  }
  
}
