package com.team5.justdoeat.order.vo;

import java.util.List;

import lombok.Data;

@Data
public class MenuInfoVO {
  private Long menuSeq;
  private Integer menuCount;
  private List<OptionInfo> optionList;
}
