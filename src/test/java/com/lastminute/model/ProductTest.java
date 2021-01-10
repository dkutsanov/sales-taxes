package com.lastminute.model;

import java.math.BigDecimal;

import com.lastminute.exception.IllegalProductException;
import com.lastminute.type.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

    @DisplayName("Test product structure")
    @Test
    void productStructure() {
        Assertions.assertThrows(IllegalProductException.class, () -> {
            new Product(Type.BOOK, null, new BigDecimal("12.99"), 1, true);
        });

        Assertions.assertThrows(IllegalProductException.class, () -> {
            new Product(null, "DUNE", new BigDecimal("12.99"), 1, true);
        });

        Assertions.assertThrows(IllegalProductException.class, () -> {
            new Product(Type.BOOK, "DUNE", null, 1, true);
        });

        Assertions.assertThrows(IllegalProductException.class, () -> {
            new Product(Type.BOOK, "DUNE", new BigDecimal("-1"), 1, true);
        });

        Assertions.assertThrows(IllegalProductException.class, () -> {
            new Product(Type.BOOK, null, new BigDecimal("12.99"), 1, true);
        });
    }
}
