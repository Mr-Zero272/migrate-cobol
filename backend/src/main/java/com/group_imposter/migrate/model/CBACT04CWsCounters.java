package com.group_imposter.migrate.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CBACT04CWsCounters {
    private int wsRecordCount;
    private int wsTranIdSuffix;

    public CBACT04CWsCounters(){
        wsRecordCount = 0;
        wsTranIdSuffix = 0;
    }
}
