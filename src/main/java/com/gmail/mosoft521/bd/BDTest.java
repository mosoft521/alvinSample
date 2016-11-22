package com.gmail.mosoft521.bd;

import java.math.BigDecimal;

/**
 * Created by Alvin on 2016/11/15 0015.
 */
public class BDTest {
    public static void main(String[] args) {
        BigDecimal marketValueTotal = new BigDecimal(162988411.60);
        BigDecimal minValue = new BigDecimal(300);

        int maxByStrategy = marketValueTotal.divide(minValue, 2, BigDecimal.ROUND_HALF_DOWN).intValue();
        System.out.println("maxByStrategy: " + maxByStrategy);
    }
}
