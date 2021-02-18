package au.com.latitudefinancial.exercise.model;

import java.math.BigDecimal;

public class StockPrice {

    private final int minutesSinceOpening;
    private final BigDecimal price;

    public StockPrice(int minutesSinceOpening, BigDecimal price) {
        this.minutesSinceOpening = minutesSinceOpening;
        this.price = price;
    }

    public int getMinutesSinceOpening() {
        return minutesSinceOpening;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "StockPrice{" +
                "minutesSinceOpening=" + minutesSinceOpening +
                ", price=" + price +
                '}';
    }
}