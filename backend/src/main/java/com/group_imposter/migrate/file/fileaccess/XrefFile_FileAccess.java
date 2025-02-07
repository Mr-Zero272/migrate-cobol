package com.group_imposter.migrate.file.fileaccess;

import com.group_imposter.migrate.accessor.FdXreffileRec_Accessor;
import com.group_imposter.migrate.file.FileAccessBase;
import com.group_imposter.migrate.file.FileStatusConstant;
import com.group_imposter.migrate.model.FdXreffileRec;

public class XrefFile_FileAccess extends FileAccessBase {

   
  public  XrefFile_FileAccess(String filePath){
      super(filePath);
  }

  @Override
  public String readToObject(Object o){
      String line = super.getCurrentLine();
      if (isEOF()){
          return FileStatusConstant.FAILED;
      }
      if (o instanceof FdXreffileRec){
          FdXreffileRec fdXreffileRec = (FdXreffileRec)o;
          FdXreffileRec_Accessor.setFdXreffileRec(fdXreffileRec, line);
      }
      return FileStatusConstant.SUCCESS;
  }

  @Override
  public String writeFromObject(Object o){
      if (o instanceof FdXreffileRec){
          FdXreffileRec fdXreffileRec = (FdXreffileRec)o;
          super.write(FdXreffileRec_Accessor.getFdXreffileRec(fdXreffileRec));
      }
      return FileStatusConstant.SUCCESS;
  }
}