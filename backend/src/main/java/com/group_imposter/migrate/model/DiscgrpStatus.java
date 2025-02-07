package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscgrpStatus {
    private String discgrpStat1;
    private String discgrpStat2;

    public DiscgrpStatus(){
        discgrpStat1 = ValueConst.SPACE;
        discgrpStat2 = ValueConst.SPACE;
    }
}
