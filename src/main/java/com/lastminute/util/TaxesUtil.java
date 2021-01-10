package com.lastminute.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Util class for calculing taxes
 */
public class TaxesUtil {

    /**
     * Rounding rule by which an {@link BigDecimal amount} is rounded to the {@link BigDecimal increment value}
     * using the {@link RoundingMode rounding mode}
     *
     * @param amount amount to round
     * @param increment increment value
     * @param roundingMode rounding mode of BigDecimal
     * @return rounded value
     */
    public static BigDecimal roundingRule(
            BigDecimal amount,
            BigDecimal increment,
            RoundingMode roundingMode) {

        if (increment.signum() <= 0) {
            throw new IllegalArgumentException("Increment must be positive");
        }

        BigDecimal divided = amount.divide(increment, 0, roundingMode);
        return divided.multiply(increment);
    }
}
