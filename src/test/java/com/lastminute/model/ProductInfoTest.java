package com.lastminute.model;

import java.math.BigDecimal;

import com.lastminute.exception.IllegalProductInfoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductInfoTest {

    @DisplayName("Test ProductInfo structure")
    @Test
    void productStructure() {
        Assertions.assertThrows(IllegalProductInfoException.class,
                () -> new ProductInfo(
                        null,
                        new BigDecimal("3"),
                        new BigDecimal("2.99"),
                        new BigDecimal("12.99")));

        Assertions.assertThrows(IllegalProductInfoException.class,
                () -> new ProductInfo(
                        "1, imported book",
                        null,
                        new BigDecimal("2.99"),
                        new BigDecimal("12.99")));

        Assertions.assertThrows(IllegalProductInfoException.class,
                () -> new ProductInfo(
                        "1, imported book",
                        new BigDecimal("1"),
                        null,
                        new BigDecimal("2.99")));

        Assertions.assertThrows(IllegalProductInfoException.class,
                () -> new ProductInfo(
                        "1, imported book",
                        new BigDecimal("2"),
                        new BigDecimal("2.99"),
                        null));

        Assertions.assertThrows(IllegalProductInfoException.class,
                () -> new ProductInfo(
                        "  ",
                        new BigDecimal("1"),
                        new BigDecimal("2.99"),
                        new BigDecimal("12.99")));

        Assertions.assertThrows(IllegalProductInfoException.class,
                () -> new ProductInfo(
                        "1, imported book",
                        new BigDecimal("-1"),
                        new BigDecimal("2.99"),
                        new BigDecimal("12.99")));

    }
}
