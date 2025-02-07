package com.group_imposter.migrate.file.fileaccess;

import com.group_imposter.migrate.accessor.FdTranRecord_Accessor;
import com.group_imposter.migrate.file.FileAccessBase;
import com.group_imposter.migrate.file.FileStatusConstant;
import com.group_imposter.migrate.model.FdTranRecord;

public class DalytranFile_FileAccess extends FileAccessBase {

   
  public  DalytranFile_FileAccess(String filePath){
      super(filePath);
  }

  @Override
  public String readToObject(Object o){
      String line = super.getCurrentLine();
      if (isEOF()){
          return FileStatusConstant.FAILED;
      }
      if (o instanceof FdTranRecord){
          FdTranRecord fdTranRecord = (FdTranRecord)o;
          FdTranRecord_Accessor.setFdTranRecord(fdTranRecord, line);
      }
      return FileStatusConstant.SUCCESS;
  }

  @Override
  public String writeFromObject(Object o){
      if (o instanceof FdTranRecord){
          FdTranRecord fdTranRecord = (FdTranRecord)o;
          super.write(FdTranRecord_Accessor.getFdTranRecord(fdTranRecord));
      }
      return FileStatusConstant.SUCCESS;
  }
}