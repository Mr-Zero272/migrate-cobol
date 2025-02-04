package com.group_imposter.migrate.util.math;

import java.math.BigDecimal;

public class BigDecimalUtil {
    /**
     * Truncate BigDecimal object
     * 
     * @param number
     * @param decimals
     *            number of decimal part to truncate
     * @return
     */
    public static BigDecimal truncate(BigDecimal number, int decimals) {
        int roundingMode = number.compareTo(new BigDecimal(0)) > 0 ? BigDecimal.ROUND_FLOOR
                : BigDecimal.ROUND_CEILING;
        return number.setScale(decimals, roundingMode);
    }
}
