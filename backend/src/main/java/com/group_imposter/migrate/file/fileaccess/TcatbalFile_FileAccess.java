package com.group_imposter.migrate.file.fileaccess;

import com.group_imposter.migrate.accessor.FdTranCatBalRecord_Accessor;
import com.group_imposter.migrate.file.FileAccessBase;
import com.group_imposter.migrate.file.FileStatusConstant;
import com.group_imposter.migrate.model.FdTranCatBalRecord;

public class TcatbalFile_FileAccess extends FileAccessBase {

   
  public  TcatbalFile_FileAccess(String filePath){
      super(filePath);
  }

  @Override
  public String readToObject(Object o){
      String line = super.getCurrentLine();
      if (isEOF()){
          return FileStatusConstant.FAILED;
      }
      if (o instanceof FdTranCatBalRecord){
          FdTranCatBalRecord fdTranCatBalRecord = (FdTranCatBalRecord)o;
          FdTranCatBalRecord_Accessor.setFdTranCatBalRecord(fdTranCatBalRecord, line);
      }
      return FileStatusConstant.SUCCESS;
  }

  @Override
  public String writeFromObject(Object o){
      if (o instanceof FdTranCatBalRecord){
          FdTranCatBalRecord fdTranCatBalRecord = (FdTranCatBalRecord)o;
          super.write(FdTranCatBalRecord_Accessor.getFdTranCatBalRecord(fdTranCatBalRecord));
      }
      return FileStatusConstant.SUCCESS;
  }
}