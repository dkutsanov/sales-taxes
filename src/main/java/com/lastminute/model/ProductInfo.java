package com.lastminute.model;

import java.math.BigDecimal;

import com.lastminute.exception.IllegalProductInfoException;

/**
 * Helper class for storing mapped product information
 */
public class ProductInfo {
    private final String description;
    private final BigDecimal quantity;
    private final BigDecimal taxedPrice;
    private final BigDecimal taxes;

    public ProductInfo(String description, BigDecimal quantity, BigDecimal taxedPrice, BigDecimal taxes) {

        if (description == null
                || description.equals("")
                || description.trim().equals("")) {
            throw new IllegalProductInfoException("Description must not be null or blank");
        }

        if (quantity == null) {
            throw new IllegalProductInfoException("Quantity could not be null");
        }

        if (quantity.compareTo(new BigDecimal("0")) <= 0) {
            throw new IllegalProductInfoException("Quantity must be positive");
        }

        if (taxedPrice == null) {
            throw new IllegalProductInfoException("TaxedPrice could not be null");
        }

        if (taxes == null) {
            throw new IllegalProductInfoException("Taxes could not be null");
        }

        this.description = description;
        this.quantity = quantity;
        this.taxedPrice = taxedPrice;
        this.taxes = taxes;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getTaxedPrice() {
        return taxedPrice;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }
}
