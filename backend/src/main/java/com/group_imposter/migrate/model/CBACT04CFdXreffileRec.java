package com.group_imposter.migrate.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CBACT04CFdXreffileRec {
    private String fdXrefCardNum;
    private int fdXrefCustNum;
    private long fdXrefAcctId;
    private String filler;

    public  CBACT04CFdXreffileRec(){
        fdXrefCardNum = "";
        fdXrefCustNum = 0;
        fdXrefAcctId = 0;
        filler = "";
    }
}
