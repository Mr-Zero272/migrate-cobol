package com.group_imposter.migrate.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FdDiscgrpRec {
    private DisGroupKey fdDiscgrpKey;
    private String fdDiscgrpData;

    public FdDiscgrpRec(){
        fdDiscgrpKey = new DisGroupKey();
        fdDiscgrpData = "";
    }
}
