package com.lastminute.service;

import static com.lastminute.constant.Constants.GOODS_TAX;
import static com.lastminute.constant.Constants.IMPORT_TAX;
import static com.lastminute.constant.Constants.ROUNDING_MODE;
import static com.lastminute.constant.Constants.ROUNDING_VALUE;

import java.math.BigDecimal;
import java.util.List;

import com.lastminute.model.Product;
import com.lastminute.model.TaxesPair;
import com.lastminute.type.Type;
import com.lastminute.util.TaxesUtil;

public class ReceiptService {

    /**
     * Prints in console receipt information based on a list of {@link Product products}
     *
     * @param products list of products
     */
    public static void printReceipt(List<Product> products) {

        BigDecimal salesTaxes = new BigDecimal("0");
        BigDecimal total = new BigDecimal("0");

        for (Product product : products) {

            final BigDecimal taxesAmount = calculateTaxes(product);
            final BigDecimal taxedPrice = product.getPrice().add(taxesAmount);
            System.out.printf("%s: %s%n", product.productInfo(), taxedPrice);

            final BigDecimal productsTaxes = taxesAmount.multiply(BigDecimal.valueOf(product.getQuantity()));
            final BigDecimal productsTaxedAmount = taxedPrice.multiply(BigDecimal.valueOf(product.getQuantity()));

            salesTaxes = salesTaxes.add(productsTaxes);
            total = total.add(productsTaxedAmount);
        }

        System.out.println("Sales Taxes: " + salesTaxes);
        System.out.println("Total: " + total);
    }

    /**
     * Calculates {@link TaxesPair taxes} based on a product type.
     *
     * @param product product on which taxes should be applied
     * @return taxes amount
     */
    private static BigDecimal calculateTaxes(Product product) {
        BigDecimal taxes = new BigDecimal("0");

        if (product.getType() == Type.GOODS) {
            taxes = taxes.add(GOODS_TAX);
        }

        if(product.isImported()) {
            taxes = taxes.add(IMPORT_TAX);
        }

        final BigDecimal taxesAmount = product.getPrice().multiply(taxes);

        return TaxesUtil.roundingRule(taxesAmount, ROUNDING_VALUE, ROUNDING_MODE);
    }
}
