package com.group_imposter.migrate.accessor;

public class ApplResult_Accessor {  

   
  public static boolean isApplAok(int applResult){
      int value = applResult;
      if (value == 0){
          return true;
      }
      return false;
  }


  public static boolean isApplEof(int applResult){
      int value = applResult;
      if (value == 16){
          return true;
      }
      return false;
  }


  public static int setApplAok(int applResult){
      applResult = 0;
      return applResult;
  }


  public static int setApplEof(int applResult){
      applResult = 16;
      return applResult;
  }
}