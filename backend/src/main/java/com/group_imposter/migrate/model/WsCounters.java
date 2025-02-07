package com.group_imposter.migrate.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class WsCounters {

  private int wsTransactionCount;
  private int wsRejectCount;
  private BigDecimal wsTempBal;
   
  public  WsCounters(){
      wsTransactionCount = 0;
      wsRejectCount = 0;
      wsTempBal = new BigDecimal(0);
  }
}