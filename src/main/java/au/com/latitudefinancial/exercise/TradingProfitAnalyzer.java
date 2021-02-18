package au.com.latitudefinancial.exercise;

import java.util.List;

public class TradingProfitAnalyzer {

    private static final int TRADING_OPENS_HOUR = 10;
    private static final int TRADING_CLOSES_HOUR = 16;

    public void determineMostProfitableTransaction(List<StockPrice> pricesByMinuteOffsets) {
        throwExceptionIfNotEngouthData(pricesByMinuteOffsets);
        for (StockPrice stockPrice : pricesByMinuteOffsets) {
            throwExceptionIfIncorrectMinuteOffset(stockPrice);
        }
    }

    private void throwExceptionIfNotEngouthData(List<StockPrice> pricesByMinuteOffsets) {
        if (pricesByMinuteOffsets == null || pricesByMinuteOffsets.size() < 2) {
            throw new IllegalStateException("There must be at least 2 entries in the price map");
        }
    }

    private void throwExceptionIfIncorrectMinuteOffset(StockPrice stockPrice) {
        if (stockPrice.getMinutesSinceOpening() < 0) {
            throw new IllegalStateException("Negative time offsets are not allowed: " + stockPrice.getMinutesSinceOpening());
        } else if (stockPrice.getMinutesSinceOpening() > calculateNumberOfTradingHours()) {
            throw new IllegalStateException("Negative time offsets are not allowed: " + stockPrice.getMinutesSinceOpening());
        }
    }

    public int calculateNumberOfTradingHours() {
        return TRADING_CLOSES_HOUR - TRADING_OPENS_HOUR;
    }

}
