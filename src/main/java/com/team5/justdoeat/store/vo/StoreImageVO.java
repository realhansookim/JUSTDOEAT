package com.team5.justdoeat.store.vo;

import com.team5.justdoeat.store.entity.StoreImageEntity;

import lombok.Data;

@Data
public class StoreImageVO {
  private Long no;
  private String url;
  private Integer order;
  private Integer storeInfo;

  public StoreImageVO(StoreImageEntity data){
    this.no = data.getSimgSeq();
    this.url = data.getSimgUri();
    // this.order = data.getSimgOrder();
  }
}
