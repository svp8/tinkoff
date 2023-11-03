package edu.hw3.task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @Test
    void stockMarket() {
        StockMarket stockMarket = new StockMarketImpl();
        stockMarket.add(new Stock(5));
        stockMarket.add(new Stock(6));
        stockMarket.add(new Stock(2));
        stockMarket.add(new Stock(1));
        stockMarket.add(new Stock(4));
        Assertions.assertEquals(6, stockMarket.mostValuableStock().value());
    }

    @Test
    @DisplayName("Test after add and remove")
    void stockMarketAddRemove() {
        StockMarket stockMarket = new StockMarketImpl();
        stockMarket.add(new Stock(5));
        stockMarket.add(new Stock(2));
        Stock stock = new Stock(6);
        stockMarket.add(stock);
        stockMarket.add(new Stock(1));
        stockMarket.add(new Stock(4));
        stockMarket.remove(stock);
        Assertions.assertEquals(5, stockMarket.mostValuableStock().value());
    }
}
