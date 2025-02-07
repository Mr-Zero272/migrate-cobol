package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;

public class Filler_Accessor {

   
  public static String getFiller(String db2FormatTs){
      return db2FormatTs;
  }


  public static String setFiller(String db2FormatTs, String value){
      db2FormatTs = FieldFormat.format(26, value);
      return db2FormatTs;
  }


  public static String initializeFiller(String db2FormatTs){
      db2FormatTs = FieldFormat.format(26, ValueConst.SPACE);
      return db2FormatTs;
  }


  public static String getDb2Yyyy(String db2FormatTs){
      return FieldFormat.format(4, FieldFormat.format(26, db2FormatTs).substring(0, 4));
  }


  public static String setDb2Yyyy(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(4, value));
      sb.append(s.substring(4, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String initializeDb2Yyyy(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(4, FieldFormat.format(4, ValueConst.SPACE)));
      sb.append(s.substring(4, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String getDb2Streep1(String db2FormatTs){
      return FieldFormat.format(1, FieldFormat.format(26, db2FormatTs).substring(4, 5));
  }


  public static String setDb2Streep1(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 4));
      sb.append(FieldFormat.format(1, value));
      sb.append(s.substring(5, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String initializeDb2Streep1(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 4));
      sb.append(FieldFormat.format(1, ValueConst.SPACE));
      sb.append(s.substring(5, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String getDb2Mm(String db2FormatTs){
      return FieldFormat.format(2, FieldFormat.format(26, db2FormatTs).substring(5, 7));
  }


  public static String setDb2Mm(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 5));
      sb.append(FieldFormat.format(2, value));
      sb.append(s.substring(7, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String initializeDb2Mm(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 5));
      sb.append(FieldFormat.format(2, FieldFormat.format(2, ValueConst.SPACE)));
      sb.append(s.substring(7, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String getDb2Streep2(String db2FormatTs){
      return FieldFormat.format(1, FieldFormat.format(26, db2FormatTs).substring(7, 8));
  }


  public static String setDb2Streep2(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 7));
      sb.append(FieldFormat.format(1, value));
      sb.append(s.substring(8, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String initializeDb2Streep2(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 7));
      sb.append(FieldFormat.format(1, ValueConst.SPACE));
      sb.append(s.substring(8, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String getDb2Dd(String db2FormatTs){
      return FieldFormat.format(2, FieldFormat.format(26, db2FormatTs).substring(8, 10));
  }


  public static String setDb2Dd(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 8));
      sb.append(FieldFormat.format(2, value));
      sb.append(s.substring(10, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String initializeDb2Dd(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 8));
      sb.append(FieldFormat.format(2, FieldFormat.format(2, ValueConst.SPACE)));
      sb.append(s.substring(10, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String getDb2Streep3(String db2FormatTs){
      return FieldFormat.format(1, FieldFormat.format(26, db2FormatTs).substring(10, 11));
  }


  public static String setDb2Streep3(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 10));
      sb.append(FieldFormat.format(1, value));
      sb.append(s.substring(11, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String initializeDb2Streep3(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 10));
      sb.append(FieldFormat.format(1, ValueConst.SPACE));
      sb.append(s.substring(11, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String getDb2Hh(String db2FormatTs){
      return FieldFormat.format(2, FieldFormat.format(26, db2FormatTs).substring(11, 13));
  }


  public static String setDb2Hh(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 11));
      sb.append(FieldFormat.format(2, value));
      sb.append(s.substring(13, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String initializeDb2Hh(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 11));
      sb.append(FieldFormat.format(2, FieldFormat.format(2, ValueConst.SPACE)));
      sb.append(s.substring(13, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String getDb2Dot1(String db2FormatTs){
      return FieldFormat.format(1, FieldFormat.format(26, db2FormatTs).substring(13, 14));
  }


  public static String setDb2Dot1(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 13));
      sb.append(FieldFormat.format(1, value));
      sb.append(s.substring(14, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String initializeDb2Dot1(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 13));
      sb.append(FieldFormat.format(1, ValueConst.SPACE));
      sb.append(s.substring(14, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String getDb2Min(String db2FormatTs){
      return FieldFormat.format(2, FieldFormat.format(26, db2FormatTs).substring(14, 16));
  }


  public static String setDb2Min(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 14));
      sb.append(FieldFormat.format(2, value));
      sb.append(s.substring(16, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String initializeDb2Min(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 14));
      sb.append(FieldFormat.format(2, FieldFormat.format(2, ValueConst.SPACE)));
      sb.append(s.substring(16, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String getDb2Dot2(String db2FormatTs){
      return FieldFormat.format(1, FieldFormat.format(26, db2FormatTs).substring(16, 17));
  }


  public static String setDb2Dot2(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 16));
      sb.append(FieldFormat.format(1, value));
      sb.append(s.substring(17, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String initializeDb2Dot2(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 16));
      sb.append(FieldFormat.format(1, ValueConst.SPACE));
      sb.append(s.substring(17, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String getDb2Ss(String db2FormatTs){
      return FieldFormat.format(2, FieldFormat.format(26, db2FormatTs).substring(17, 19));
  }


  public static String setDb2Ss(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 17));
      sb.append(FieldFormat.format(2, value));
      sb.append(s.substring(19, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String initializeDb2Ss(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 17));
      sb.append(FieldFormat.format(2, FieldFormat.format(2, ValueConst.SPACE)));
      sb.append(s.substring(19, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String getDb2Dot3(String db2FormatTs){
      return FieldFormat.format(1, FieldFormat.format(26, db2FormatTs).substring(19, 20));
  }


  public static String setDb2Dot3(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 19));
      sb.append(FieldFormat.format(1, value));
      sb.append(s.substring(20, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String initializeDb2Dot3(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 19));
      sb.append(FieldFormat.format(1, ValueConst.SPACE));
      sb.append(s.substring(20, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static short getDb2Mil(String db2FormatTs){
      return Short.valueOf(FieldFormat.format(26, db2FormatTs).substring(20, 22));
  }


  public static String setDb2Mil(String db2FormatTs, short value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 20));
      sb.append(FieldFormat.format(2, value));
      sb.append(s.substring(22, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String initializeDb2Mil(String db2FormatTs, short value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 20));
      sb.append(FieldFormat.format(2, (short)0));
      sb.append(s.substring(22, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String getDb2Rest(String db2FormatTs){
      return FieldFormat.format(4, FieldFormat.format(26, db2FormatTs).substring(22, 26));
  }


  public static String setDb2Rest(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 22));
      sb.append(FieldFormat.format(4, value));
      sb.append(s.substring(26, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }


  public static String initializeDb2Rest(String db2FormatTs, String value){
      String s = Filler_Accessor.getFiller(db2FormatTs);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 22));
      sb.append(FieldFormat.format(4, FieldFormat.format(4, ValueConst.SPACE)));
      sb.append(s.substring(26, s.length()));
      db2FormatTs = Filler_Accessor.setFiller(db2FormatTs, sb.toString());
      return db2FormatTs;
  }
}