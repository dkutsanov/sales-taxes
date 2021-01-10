package com.lastminute.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TaxesUtilTest {

    @DisplayName("Test correct increment value")
    @Test
    void roundingRuleException() {

        final BigDecimal valueToRound = new BigDecimal("2.49");
        final BigDecimal increment = new BigDecimal("-1");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> TaxesUtil.roundingRule(valueToRound, increment, RoundingMode.HALF_EVEN));
    }

    @DisplayName("Test different values")
    @ParameterizedTest(name = "{0} rounded to the nearest {1} is {2}")
    @CsvSource({
            "2.49,    0.05,   2.50",
            "2.63,    0.05,   2.65",
            "0.55,    0.05,   0.55"
    })
    void roundingRule(String value, String incrementValue, String expected) {

        final BigDecimal valueToRound = new BigDecimal(value);
        final BigDecimal increment = new BigDecimal(incrementValue);

        final BigDecimal result = TaxesUtil.roundingRule(valueToRound, increment, RoundingMode.CEILING);
        assertEquals(new BigDecimal(expected), result);
    }
}
