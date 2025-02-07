package com.group_imposter.migrate.file.fileaccess;

import com.group_imposter.migrate.accessor.FdAcctfileRec_Accessor;
import com.group_imposter.migrate.file.FileAccessBase;
import com.group_imposter.migrate.file.FileStatusConstant;
import com.group_imposter.migrate.model.FdAcctfileRec;

public class AccountFile_FileAccess extends FileAccessBase {

   
  public  AccountFile_FileAccess(String filePath){
      super(filePath);
  }

  @Override
  public String readToObject(Object o){
      String line = super.getCurrentLine();
      if (isEOF()){
          return FileStatusConstant.FAILED;
      }
      if (o instanceof FdAcctfileRec){
          FdAcctfileRec fdAcctfileRec = (FdAcctfileRec)o;
          FdAcctfileRec_Accessor.setFdAcctfileRec(fdAcctfileRec, line);
      }
      return FileStatusConstant.SUCCESS;
  }

  @Override
  public String writeFromObject(Object o){
      if (o instanceof FdAcctfileRec){
          FdAcctfileRec fdAcctfileRec = (FdAcctfileRec)o;
          super.write(FdAcctfileRec_Accessor.getFdAcctfileRec(fdAcctfileRec));
      }
      return FileStatusConstant.SUCCESS;
  }
}