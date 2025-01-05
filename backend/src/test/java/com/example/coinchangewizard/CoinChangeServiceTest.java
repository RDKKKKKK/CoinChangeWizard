package com.example.coinchangewizard;

import com.example.coinchangewizard.core.CoinChangeService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CoinChangeServiceTest {
    public static CoinChangeService service = new CoinChangeService();

    @Test
    public void testValidCase1(){
        List<BigDecimal> coinDenominations = List.of(
                new BigDecimal("0.01"),
                new BigDecimal("0.5"),
                new BigDecimal("1"),
                new BigDecimal("5"),
                new BigDecimal("10")
        );

        List<BigDecimal> expected = List.of(
                new BigDecimal("0.01"),
                new BigDecimal("0.01"),
                new BigDecimal("0.01"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("5")
        );

        BigDecimal targetAmount = new BigDecimal("7.03");

        assertEquals(expected, service.calculateMinimumCoins(targetAmount, coinDenominations));

    }

    @Test
    public void testValidCase2(){
        List<BigDecimal> coinDenominations = List.of(
                new BigDecimal("1"),
                new BigDecimal("2"),
                new BigDecimal("50")
        );

        List<BigDecimal> expected = List.of(
                new BigDecimal("1"),
                new BigDecimal("2"),
                new BigDecimal("50"),
                new BigDecimal("50")
        );

        BigDecimal targetAmount = new BigDecimal("103");

        assertEquals(expected, service.calculateMinimumCoins(targetAmount, coinDenominations));
    }

    @Test
    public void testInvalidCoin(){
        List<BigDecimal> coinDenominations = List.of(
                new BigDecimal("1"),
                new BigDecimal("3"),
                new BigDecimal("50")
        );

        BigDecimal targetAmount = new BigDecimal("100");

        assertThrows(IllegalArgumentException.class, ()->{
            service.calculateMinimumCoins(targetAmount, coinDenominations);
        },"Invalid coin throws IllegalArgumentException");
    }

    @Test
    public void testInvalidTargetAmount(){
        List<BigDecimal> coinDenominations = List.of(
                new BigDecimal("1"),
                new BigDecimal("2"),
                new BigDecimal("50")
        );

        BigDecimal targetAmount = new BigDecimal("-1");

        assertThrows(IllegalArgumentException.class, ()->{
            service.calculateMinimumCoins(targetAmount, coinDenominations);
        },"Invalid target amount throws IllegalArgumentException");
    }
}
