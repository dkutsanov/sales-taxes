package com.lastminute.service;

import static com.lastminute.model.Products.receiptOne;
import static com.lastminute.model.Products.receiptThree;
import static com.lastminute.model.Products.receiptTwo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReceiptServiceTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @AfterEach
    public void restoreInitialStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @DisplayName("Test Sales Taxes 1")
    @Test
    void printReceiptOne() {
        ReceiptService.printReceipt(Arrays.asList(receiptOne.clone()));
        Assertions.assertEquals(
                "1 book: 12.49\n"
                        + "1 music CD: 16.49\n"
                        + "1 chocolate bar: 0.85\n"
                        + "Sales Taxes: 1.50\n"
                        + "Total: 29.83\n",
                out.toString());
    }

    @DisplayName("Test Sales Taxes 2")
    @Test
    void printReceiptTwo() {
        ReceiptService.printReceipt(Arrays.asList(receiptTwo.clone()));
        Assertions.assertEquals(
                "1 imported box of chocolates: 10.50\n"
                        + "1 imported bottle of perfume: 54.65\n"
                        + "Sales Taxes: 7.65\n"
                        + "Total: 65.15\n",
                out.toString());
    }

    @DisplayName("Test Sales Taxes 3")
    @Test
    void printReceiptThree() {
        ReceiptService.printReceipt(Arrays.asList(receiptThree.clone()));
        Assertions.assertEquals(
                "1 imported bottle of perfume: 32.19\n"
                        + "1 bottle of perfume: 20.89\n"
                        + "1 packet of headache pills: 9.75\n"
                        + "1 imported box of chocolates: 11.85\n"
                        + "Sales Taxes: 6.70\n"
                        + "Total: 74.68\n",
                out.toString());
    }
}
