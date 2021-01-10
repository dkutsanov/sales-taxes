package com.lastminute.model;

import java.math.BigDecimal;

import com.lastminute.exception.IllegalTaxesPairException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TaxesPairTest {

    @DisplayName("Test TaxesPair structure")
    @Test
    void productStructure() {
        Assertions.assertThrows(IllegalTaxesPairException.class, () -> {
            new TaxesPair(new BigDecimal("12.99"), null);
        });

        Assertions.assertThrows(IllegalTaxesPairException.class, () -> {
            new TaxesPair(null, new BigDecimal("12.99"));
        });

        Assertions.assertThrows(IllegalTaxesPairException.class, () -> {
            new TaxesPair(new BigDecimal("-1"), new BigDecimal("12.99"));
        });

        Assertions.assertThrows(IllegalTaxesPairException.class, () -> {
            new TaxesPair(new BigDecimal("12.99"), new BigDecimal("-1"));
        });
    }
}
