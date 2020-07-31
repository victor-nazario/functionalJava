package oop_and_functional.class_level;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * Created on 31 Jul, 2020 - 03:27
 *
 */
public class CalculateNAV {
    public BigDecimal computeStockWorth(final String ticker, final int shares){
        return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
    }

    public CalculateNAV(final Function<String, BigDecimal> aPriceFinder){
        priceFinder = aPriceFinder;
    }


    private final Function<String, BigDecimal> priceFinder;
}
