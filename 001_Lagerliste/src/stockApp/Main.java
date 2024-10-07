package stockApp;

import stock.*;
import date.Date;

public class Main {
    public static void main(String[] args) {
        StockList stockList = new HiFoStockList();

        // Einlagern, Auslagern und Analysieren

        stockList.store(new ValuedStockMovement(
                new Date(2024, 1, 7),
                100,
                10));

        stockList.store(new ValuedStockMovement(
                new Date(2024, 1, 8),
                100,
                13));

        stockList.remove(new StockMovement(new Date(2024, 1, 9),30));

        stockList.store(new ValuedStockMovement(
                new Date(2024, 1, 11),
                100,
                12));

        stockList.remove(new StockMovement(new Date(2024, 1, 9),100));

        System.out.println("Lagerbestand:");
        System.out.println(stockList.getStockStatus());

        System.out.println("Wareneingänge:");
        System.out.println(stockList.getStockIngoings());

        System.out.println("Warenausgänge:");
        System.out.println(stockList.getStockOutgoings());
    }
}
