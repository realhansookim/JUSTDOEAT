package com.team5.justdoeat.admin.vo;

import lombok.Data;

@Data
public class StoreUpdateVO {
private Long si_seq;
private String si_name;
private String si_min_delivery_time;
private String si_max_delivery_time;
private Integer si_min_price;
private Integer si_status;
}
