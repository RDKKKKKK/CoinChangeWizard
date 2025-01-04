package com.example.coinchangewizard.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CoinChangeService {

    private static final List<BigDecimal> VALID_DENOMINATIONS = List.of(
            new BigDecimal("0.01"),
            new BigDecimal("0.05"),
            new BigDecimal("0.1"),
            new BigDecimal("0.2"),
            new BigDecimal("0.5"),
            new BigDecimal("1"),
            new BigDecimal("2"),
            new BigDecimal("5"),
            new BigDecimal("10"),
            new BigDecimal("50"),
            new BigDecimal("100"),
            new BigDecimal("1000")
    );

    /**
     * Algorithm to solve challenge 1
     * @param targetAmount within the range between 0 and 10,000.00
     * @param denominations have valid values
     * @return A list of minimum number of coins in ascending order
     */
    public List<BigDecimal> calculateMinimumCoins(BigDecimal targetAmount, List<BigDecimal> denominations) {

        if (targetAmount.compareTo(BigDecimal.ZERO) < 0 || targetAmount.compareTo(new BigDecimal("10000.00")) > 0) {
            throw new IllegalArgumentException("Target amount must be between 0 and 10000.00");
        }

        for (BigDecimal coin : denominations) {
            if (!VALID_DENOMINATIONS.contains(coin)) {
                throw new IllegalArgumentException("Invalid coin denomination: " + coin);
            }
        }

        List<BigDecimal> coinDenominations = new ArrayList<>(denominations);
        coinDenominations.sort(Comparator.reverseOrder());

        List<BigDecimal> result = new ArrayList<>();

        BigDecimal need = targetAmount;

        // Greedy Algorithm
        for (BigDecimal coin : coinDenominations) {
            while (need.compareTo(coin) >= 0) {

                result.add(coin);
                need = need.subtract(coin);

                if (need.compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }
            }

            if (need.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
        }

        Collections.sort(result);
        return result;
    }
}