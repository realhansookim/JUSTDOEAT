package com.team5.justdoeat.admin.vo;

import com.team5.justdoeat.admin.entity.StoreEntity;

import lombok.Data;

@Data
public class Storevo {
private Long si_seq;
private String si_name;
private String si_min_delivery_time;
private String si_max_delivery_time;
private Integer si_min_price;
private Integer si_status;

public Storevo(StoreEntity data){
this.si_seq = data.getSiSeq();
this.si_name = data.getSiName();
this.si_min_delivery_time = data.getSiMinDeliveryTime();
this.si_max_delivery_time = data.getSiMaxDeliveryTime();
this.si_min_price = data.getSiMinPrice();
this.si_status = data.getSiStatus();
}
}
