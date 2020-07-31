package oop_and_functional.class_level;

import java.math.BigDecimal;

/**
 * Created on 31 Jul, 2020 - 03:32
 *
 */
public class CalculateNAVTest {

    public static void main(String[] args) {
        System.out.println(computeStockWorth());
    }

    public static boolean computeStockWorth(){
        final CalculateNAV calculateNAV = new CalculateNAV(ticker -> new BigDecimal("6.01"));

        BigDecimal expected = new BigDecimal("6010.00");

        return calculateNAV.computeStockWorth("GOOG", 1000).equals(expected);
    }
}
