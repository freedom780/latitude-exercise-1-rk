package au.com.latitudefinancial.exercise;

import java.util.List;

public class TradingProfitAnalyzer {

    public void determineMostProfitableTransaction(List<StockPrice> pricesByMinuteOffsets) {
        throwExceptionIfNotEngouthData(pricesByMinuteOffsets);
        for (StockPrice stockPrice : pricesByMinuteOffsets) {
            throwExceptionIfNegativeMinuteOffset(stockPrice);
        }
    }

    private void throwExceptionIfNotEngouthData(List<StockPrice> pricesByMinuteOffsets) {
        if (pricesByMinuteOffsets == null || pricesByMinuteOffsets.size() < 2) {
            throw new IllegalStateException("There must be at least 2 entries in the price map");
        }
    }

    private void throwExceptionIfNegativeMinuteOffset(StockPrice stockPrice) {
        if (stockPrice.getMinutesSinceOpening() < 0) {
            throw new IllegalStateException("Negative time offsets are not allowed: " + stockPrice.getMinutesSinceOpening());
        }
    }

}
