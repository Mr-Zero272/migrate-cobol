package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import lombok.Getter;
import lombok.Setter;

public class WsDateTime {
  @Getter
  @Setter
  private short wsCurdateYear;
  @Getter
  @Setter
  private short wsCurdateMonth;
  @Getter
  @Setter
  private short wsCurdateDay;
  @Getter
  @Setter
  private short wsCurtimeHours;
  @Getter
  @Setter
  private short wsCurtimeMinute;
  @Getter
  @Setter
  private short wsCurtimeSecond;
  @Getter
  @Setter
  private short wsCurtimeMilsec;
  @Getter
  @Setter
  private short wsCurdateMm;
  @Getter
  @Setter
  private String filler1;
  @Getter
  @Setter
  private short wsCurdateDd;
  @Getter
  @Setter
  private String filler2;
  @Getter
  @Setter
  private short wsCurdateYy;
  @Getter
  @Setter
  private short wsCurtimeHh;
  @Getter
  @Setter
  private String filler1OfWsCurtimeHhMmSs;
  @Getter
  @Setter
  private short wsCurtimeMm;
  @Getter
  @Setter
  private String filler2OfWsCurtimeHhMmSs;
  @Getter
  @Setter
  private short wsCurtimeSs;
  @Getter
  @Setter
  private short wsTimestampDtYyyy;
  @Getter
  @Setter
  private String filler1OfWsTimestamp;
  @Getter
  @Setter
  private short wsTimestampDtMm;
  @Getter
  @Setter
  private String filler2OfWsTimestamp;
  @Getter
  @Setter
  private short wsTimestampDtDd;
  @Getter
  @Setter
  private String filler3;
  @Getter
  @Setter
  private short wsTimestampTmHh;
  @Getter
  @Setter
  private String filler4;
  @Getter
  @Setter
  private short wsTimestampTmMm;
  @Getter
  @Setter
  private String filler5;
  @Getter
  @Setter
  private short wsTimestampTmSs;
  @Getter
  @Setter
  private String filler6;
  @Getter
  @Setter
  private int wsTimestampTmMs6;

  public  WsDateTime(){
    wsCurdateYear = (short)0;
    wsCurdateMonth = (short)0;
    wsCurdateDay = (short)0;
    wsCurtimeHours = (short)0;
    wsCurtimeMinute = (short)0;
    wsCurtimeSecond = (short)0;
    wsCurtimeMilsec = (short)0;
    wsCurdateMm = (short)0;
    filler1 = ValueConst.SPACE;
    wsCurdateDd = (short)0;
    filler2 = ValueConst.SPACE;
    wsCurdateYy = (short)0;
    wsCurtimeHh = (short)0;
    filler1OfWsCurtimeHhMmSs = ValueConst.SPACE;
    wsCurtimeMm = (short)0;
    filler2OfWsCurtimeHhMmSs = ValueConst.SPACE;
    wsCurtimeSs = (short)0;
    wsTimestampDtYyyy = (short)0;
    filler1OfWsTimestamp = ValueConst.SPACE;
    wsTimestampDtMm = (short)0;
    filler2OfWsTimestamp = ValueConst.SPACE;
    wsTimestampDtDd = (short)0;
    filler3 = ValueConst.SPACE;
    wsTimestampTmHh = (short)0;
    filler4 = ValueConst.SPACE;
    wsTimestampTmMm = (short)0;
    filler5 = ValueConst.SPACE;
    wsTimestampTmSs = (short)0;
    filler6 = ValueConst.SPACE;
    wsTimestampTmMs6 = 0;
  }
}
