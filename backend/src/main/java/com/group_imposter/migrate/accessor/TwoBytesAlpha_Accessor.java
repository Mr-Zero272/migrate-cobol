package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;

public class TwoBytesAlpha_Accessor {

   
  public static String getTwoBytesAlpha(short twoBytesBinary){
      return String.valueOf(twoBytesBinary);
  }


  public static short setTwoBytesAlpha(short twoBytesBinary, String value){
      twoBytesBinary = Short.valueOf(value);
      return twoBytesBinary;
  }


  public static short initializeTwoBytesAlpha(short twoBytesBinary){
      twoBytesBinary = (short)0;
      return twoBytesBinary;
  }


  public static String getTwoBytesLeft(short twoBytesBinary){
      return FieldFormat.format(1, FieldFormat.format(4, twoBytesBinary).substring(0, 1));
  }


  public static short setTwoBytesLeft(short twoBytesBinary, String value){
      String s = TwoBytesAlpha_Accessor.getTwoBytesAlpha(twoBytesBinary);
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(1, value));
      sb.append(s.substring(1, s.length()));
      twoBytesBinary = TwoBytesAlpha_Accessor.setTwoBytesAlpha(twoBytesBinary, sb.toString());
      return twoBytesBinary;
  }


  public static short initializeTwoBytesLeft(short twoBytesBinary, String value){
      String s = TwoBytesAlpha_Accessor.getTwoBytesAlpha(twoBytesBinary);
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(1, ValueConst.SPACE));
      sb.append(s.substring(1, s.length()));
      twoBytesBinary = TwoBytesAlpha_Accessor.setTwoBytesAlpha(twoBytesBinary, sb.toString());
      return twoBytesBinary;
  }


  public static String getTwoBytesRight(short twoBytesBinary){
      return FieldFormat.format(1, FieldFormat.format(4, twoBytesBinary).substring(1, 2));
  }


  public static short setTwoBytesRight(short twoBytesBinary, String value){
      String s = TwoBytesAlpha_Accessor.getTwoBytesAlpha(twoBytesBinary);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 1));
      sb.append(FieldFormat.format(1, value));
      sb.append(s.substring(2, s.length()));
      twoBytesBinary = TwoBytesAlpha_Accessor.setTwoBytesAlpha(twoBytesBinary, sb.toString());
      return twoBytesBinary;
  }


  public static short initializeTwoBytesRight(short twoBytesBinary, String value){
      String s = TwoBytesAlpha_Accessor.getTwoBytesAlpha(twoBytesBinary);
      StringBuilder sb = new StringBuilder();
      sb.append(s.substring(0, 1));
      sb.append(FieldFormat.format(1, ValueConst.SPACE));
      sb.append(s.substring(2, s.length()));
      twoBytesBinary = TwoBytesAlpha_Accessor.setTwoBytesAlpha(twoBytesBinary, sb.toString());
      return twoBytesBinary;
  }
}