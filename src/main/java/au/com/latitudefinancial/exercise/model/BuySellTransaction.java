package au.com.latitudefinancial.exercise.model;


import java.math.BigDecimal;

public class BuySellTransaction {

    private StockPrice bought;
    private StockPrice sold;

    public BuySellTransaction(StockPrice bought, StockPrice sold) {
        this.bought = bought;
        this.sold = sold;
    }

    public BigDecimal calculateAmount() {
        throwExceptionIfIncomplete();
        return sold.getPrice().subtract(bought.getPrice());
    }

    private void throwExceptionIfIncomplete() {
        if (bought == null || sold == null) {
            throw new IllegalStateException("Both bought and sold parts must be completed");
        }
    }

    public StockPrice getBought() {
        return bought;
    }

    public void setBought(StockPrice bought) {
        this.bought = bought;
    }

    public StockPrice getSold() {
        return sold;
    }

    public void setSold(StockPrice sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "BuySellTransaction{" +
                "bought=" + bought +
                ", sold=" + sold +
                '}';
    }
}