package com.group_imposter.migrate.file.fileaccess;

import com.group_imposter.migrate.accessor.FdRejsRecord_Accessor;
import com.group_imposter.migrate.file.FileAccessBase;
import com.group_imposter.migrate.file.FileStatusConstant;
import com.group_imposter.migrate.model.FdRejsRecord;

public class DalyrejsFile_FileAccess extends FileAccessBase {

   
  public  DalyrejsFile_FileAccess(String filePath){
      super(filePath);
  }

  @Override
  public String readToObject(Object o){
      String line = super.getCurrentLine();
      if (isEOF()){
          return FileStatusConstant.FAILED;
      }
      if (o instanceof FdRejsRecord){
          FdRejsRecord fdRejsRecord = (FdRejsRecord)o;
          FdRejsRecord_Accessor.setFdRejsRecord(fdRejsRecord, line);
      }
      return FileStatusConstant.SUCCESS;
  }

  @Override
  public String writeFromObject(Object o){
      if (o instanceof FdRejsRecord){
          FdRejsRecord fdRejsRecord = (FdRejsRecord)o;
          super.write(FdRejsRecord_Accessor.getFdRejsRecord(fdRejsRecord));
      }
      return FileStatusConstant.SUCCESS;
  }
}