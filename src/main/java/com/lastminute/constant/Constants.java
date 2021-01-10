package com.lastminute.constant;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Constant parameters
 */
public class Constants {
    public static final BigDecimal IMPORT_TAX = new BigDecimal("0.05");
    public static final BigDecimal GOODS_TAX = new BigDecimal("0.1");

    public static final BigDecimal ROUNDING_VALUE = new BigDecimal("0.05");
    public static final RoundingMode ROUNDING_MODE = RoundingMode.CEILING;
}
