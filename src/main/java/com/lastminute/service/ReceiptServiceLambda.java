package com.lastminute.service;

import static com.lastminute.constant.Constants.GOODS_TAX;
import static com.lastminute.constant.Constants.IMPORT_TAX;
import static com.lastminute.constant.Constants.ROUNDING_MODE;
import static com.lastminute.constant.Constants.ROUNDING_VALUE;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.lastminute.model.Product;
import com.lastminute.model.ProductInfo;
import com.lastminute.model.TaxesPair;
import com.lastminute.type.Type;
import com.lastminute.util.TaxesUtil;


/**
 * Functional implementation of 'Sales Taxes' problem
 */
public class ReceiptServiceLambda {

    /**
     * Prints in console receipt information based on a list of {@link Product products}
     *
     * @param products list of products
     */
    public static void printReceiptLambda(List<Product> products) {

        final TaxesPair totalTaxes = products.stream()
                .map(taxesVisitor)
                .peek(p -> System.out.printf("%s: %s%n", p.getDescription(),  p.getTaxedPrice()))
                .map(ReceiptServiceLambda::productInfoToTaxesPair)
                .reduce(new TaxesPair(new BigDecimal("0"), new BigDecimal("0")),
                        (p1, p2) -> new TaxesPair(p1.getTaxes().add(p2.getTaxes()),
                                        p1.getTaxedPrice().add(p2.getTaxedPrice())));

        System.out.println("Sales Taxes: " + totalTaxes.getTaxes());
        System.out.println("Total: " + totalTaxes.getTaxedPrice());
    }

    /**
     * Map product information to taxes pair object, that contains information about taxes and full amount paid per product.
     *
     * @param productInfo product information
     * @return taxes pair object
     */
    private static TaxesPair productInfoToTaxesPair(ProductInfo productInfo) {

        final BigDecimal taxesAmount = productInfo.getTaxes().multiply(productInfo.getQuantity());
        final BigDecimal pricesAmount = productInfo.getTaxedPrice().multiply(productInfo.getQuantity());

        return new TaxesPair(taxesAmount, pricesAmount);
    }

    /**
     * Function that represent the rule of calculating taxes for different products.
     */
    static Function<Product, ProductInfo> taxesVisitor = new LambdaVisitor<ProductInfo>()
            .on(Type.BOOK).then(ReceiptServiceLambda::exemptPairTaxes)
            .on(Type.GOODS).then(ReceiptServiceLambda::goodsPairTaxes)
            .on(Type.FOOD).then(ReceiptServiceLambda::exemptPairTaxes)
            .on(Type.MEDICAL_PRODUCT).then(ReceiptServiceLambda::exemptPairTaxes);

    /**
     * Calculates only import {@link ProductInfo taxes}.
     *
     * @param product product on which taxes should be applied
     * @return taxes amount and taxed price
     */
    private static ProductInfo exemptPairTaxes(Product product) {

        final BigDecimal taxesAmount = product.getPrice().multiply(importTaxes(product));
        final BigDecimal roundedTaxesAmount = TaxesUtil.roundingRule(
                taxesAmount,
                ROUNDING_VALUE,
                ROUNDING_MODE);

        return new ProductInfo(
                product.productInfo(),
                BigDecimal.valueOf(product.getQuantity()),
                product.getPrice(),
                roundedTaxesAmount);
    }

    /**
     * Calculates {@link ProductInfo taxes} for generic goods.
     *
     * @param product product on which taxes should be applied
     * @return taxes amount and taxed price
     */
    private static ProductInfo goodsPairTaxes(Product product) {

        final BigDecimal taxesAmount = product.getPrice()
                .multiply(GOODS_TAX.add(importTaxes(product)));

        final BigDecimal roundedTaxesAmount = TaxesUtil.roundingRule(
                taxesAmount,
                ROUNDING_VALUE,
                ROUNDING_MODE);

        return new ProductInfo(
                product.productInfo(),
                BigDecimal.valueOf(product.getQuantity()),
                product.getPrice(),
                roundedTaxesAmount);
    }

    /**
     * Helper method to provide import taxes percentage for a given product.
     *
     * @param product product on which texes should be applied
     * @return applied % of taxes
     */
    private static BigDecimal importTaxes(Product product) {
        return product.isImported() ? IMPORT_TAX : new BigDecimal("0");
    }

    /**
     * Helper class that implements the visitor pattern in functional way
     */
    public static class LambdaVisitor<A> implements Function<Product, A> {

        private final Map<Type, Function<Product, A>> fMap = new HashMap<>();

        public Acceptor<A> on(Type type) {
            return new Acceptor<>(this, type);
        }

        @Override
        public A apply(Product o) {
            return fMap.get(o.getType()).apply(o);
        }

        static class Acceptor<A> {

            private final LambdaVisitor<A> visitor;
            private final Type type;

            public Acceptor(LambdaVisitor<A> visitor, Type type) {
                this.visitor = visitor;
                this.type = type;
            }

            public LambdaVisitor<A> then(Function<Product, A> f) {
                visitor.fMap.put(type, f);
                return visitor;
            }
        }
    }
}
