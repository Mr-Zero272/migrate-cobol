package com.group_imposter.migrate.file.fileaccess;

import com.group_imposter.migrate.accessor.FdTranfileRec_Accessor;
import com.group_imposter.migrate.file.FileAccessBase;
import com.group_imposter.migrate.file.FileStatusConstant;
import com.group_imposter.migrate.model.FdTranfileRec;

public class TransactFile_FileAccess extends FileAccessBase {

   
  public  TransactFile_FileAccess(String filePath){
      super(filePath);
  }

  @Override
  public String readToObject(Object o){
      String line = super.getCurrentLine();
      if (isEOF()){
          return FileStatusConstant.FAILED;
      }
      if (o instanceof FdTranfileRec){
          FdTranfileRec fdTranfileRec = (FdTranfileRec)o;
          FdTranfileRec_Accessor.setFdTranfileRec(fdTranfileRec, line);
      }
      return FileStatusConstant.SUCCESS;
  }

  @Override
  public String writeFromObject(Object o){
      if (o instanceof FdTranfileRec){
          FdTranfileRec fdTranfileRec = (FdTranfileRec)o;
          super.write(FdTranfileRec_Accessor.getFdTranfileRec(fdTranfileRec));
      }
      return FileStatusConstant.SUCCESS;
  }
}