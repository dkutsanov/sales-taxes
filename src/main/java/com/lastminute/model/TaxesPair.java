package com.lastminute.model;

import java.math.BigDecimal;

import com.lastminute.exception.IllegalTaxesPairException;

/**
 * Helper class in order to provide a pair object to the stream
 */
public class TaxesPair {
    private final BigDecimal taxes;
    private final BigDecimal taxedPrice;

    public TaxesPair(BigDecimal taxes, BigDecimal taxedPrice) {

        if(taxes == null) {
            throw new IllegalTaxesPairException("Taxes must not be null");
        }

        if(taxedPrice == null) {
            throw new IllegalTaxesPairException("TaxedPrice must not be null");
        }

        if(taxes.compareTo(new BigDecimal("0")) < 0) {
            throw new IllegalTaxesPairException("Taxes cannot be negative");
        }

        if(taxedPrice.compareTo(new BigDecimal("0")) < 0) {
            throw new IllegalTaxesPairException("TaxedPrice cannot be negative");
        }

        this.taxes = taxes;
        this.taxedPrice = taxedPrice;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public BigDecimal getTaxedPrice() {
        return taxedPrice;
    }
}
