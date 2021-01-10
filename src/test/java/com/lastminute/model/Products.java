package com.lastminute.model;

import java.math.BigDecimal;

import com.lastminute.type.Type;

public class Products {

    public static Product[] receiptOne = {
            new Product(Type.BOOK, "book", new BigDecimal("12.49"), 1, false),
            new Product(Type.GOODS, "music CD", new BigDecimal("14.99"), 1, false),
            new Product(Type.FOOD, "chocolate bar", new BigDecimal("0.85"), 1, false)
    };

    public static Product[] receiptTwo = {
            new Product(Type.FOOD, "box of chocolates", new BigDecimal("10.00"), 1, true),
            new Product(Type.GOODS, "bottle of perfume", new BigDecimal("47.50"), 1, true)
    };

    public static Product[] receiptThree = {
            new Product(Type.GOODS, "bottle of perfume", new BigDecimal("27.99"), 1, true),
            new Product(Type.GOODS, "bottle of perfume", new BigDecimal("18.99"), 1, false),
            new Product(Type.MEDICAL_PRODUCT, "packet of headache pills", new BigDecimal("9.75"), 1, false),
            new Product(Type.FOOD, "box of chocolates", new BigDecimal("11.25"), 1, true)
    };
}
