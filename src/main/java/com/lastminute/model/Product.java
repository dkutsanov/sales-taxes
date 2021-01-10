package com.lastminute.model;

import java.math.BigDecimal;

import com.lastminute.exception.IllegalProductException;
import com.lastminute.type.Type;

/**
 * Product class represents the structure of a product.
 */
public class Product {

    private final Type type;
    private final String description;
    private final BigDecimal price;
    private final int quantity;
    private final boolean imported;

    public Product(Type type, String description, BigDecimal price, int quantity, boolean imported) {

        if (type == null) {
            throw new IllegalProductException("Type must not be null");
        }

        if (description == null
                || description.equals("")
                || description.trim().equals("")) {
            throw new IllegalProductException("Description must not be null or blank");
        }

        if (quantity <= 0) {
            throw new IllegalProductException("Quantity must be positive");
        }

        if (price == null) {
            throw new IllegalProductException("Price could not be null");
        }

        if (price.compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalProductException("Price could not be negative");
        }

        this.type = type;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.imported = imported;
    }

    public Type getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isImported() {
        return imported;
    }

    /**
     * Returns {@link String generic information of a product}.
     *
     * @return a String containing quantity, imported information and product description
     */
    public String productInfo() {
        return String.format("%d%s %s",
                getQuantity(),
                isImported() ? " imported" : "",
                getDescription());
    }
}
