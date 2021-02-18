package au.com.latitudefinancial.exercise;

import au.com.latitudefinancial.exercise.model.BuySellTransaction;
import au.com.latitudefinancial.exercise.model.StockPrice;

import java.math.BigDecimal;
import java.util.List;

public class TradingProfitAnalyzer {

    private static final int TRADING_OPENS_HOUR = 10;
    private static final int TRADING_CLOSES_HOUR = 16;

    public BuySellTransaction determineMostProfitableTransaction(List<StockPrice> pricesByMinuteOffsets) {

        throwExceptionIfNotEngouhData(pricesByMinuteOffsets);
        throwExceptionIfIncorrectMinuteOffset(pricesByMinuteOffsets.get(0));

        BuySellTransaction mostProfitableTransaction = null;

        for (int indexSoldAt = 1; indexSoldAt < pricesByMinuteOffsets.size(); indexSoldAt++) {
            StockPrice soldAt = pricesByMinuteOffsets.get(indexSoldAt);
            throwExceptionIfIncorrectMinuteOffset(soldAt);

            for (int indexBoughtAt = 0; indexBoughtAt < indexSoldAt; indexBoughtAt++) {
                StockPrice boughtAt = pricesByMinuteOffsets.get(indexBoughtAt);
                BuySellTransaction transaction = new BuySellTransaction(boughtAt, soldAt);
                BigDecimal profitOrLoss = transaction.calculateAmount();
                if (mostProfitableTransaction == null || profitOrLoss.compareTo(mostProfitableTransaction.calculateAmount()) > 0) {
                    mostProfitableTransaction = transaction;
                }
            }
        }

        return mostProfitableTransaction;
    }

    private void throwExceptionIfNotEngouhData(List<StockPrice> pricesByMinuteOffsets) {
        if (pricesByMinuteOffsets == null || pricesByMinuteOffsets.size() < 2) {
            throw new IllegalStateException("There must be at least 2 entries in the price map");
        }
    }

    private void throwExceptionIfIncorrectMinuteOffset(StockPrice stockPrice) {
        if (stockPrice.getMinutesSinceOpening() < 0) {
            throw new IllegalStateException("Negative time offsets are not allowed: " + stockPrice.getMinutesSinceOpening());
        } else if (stockPrice.getMinutesSinceOpening() > calculateNumberOfTradingMinutes()) {
            throw new IllegalStateException("Negative time offsets are not allowed: " + stockPrice.getMinutesSinceOpening());
        }
    }

    public int calculateNumberOfTradingMinutes() {
        return (TRADING_CLOSES_HOUR - TRADING_OPENS_HOUR) * 60;
    }

}
