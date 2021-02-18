package au.com.latitudefinancial.exercise;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


class TradingProfitAnalyzerInvalidDataTest {

    private static final BigDecimal STOCK_PRICE_70 = new BigDecimal("70.00");
    private static final BigDecimal STOCK_PRICE_100 = new BigDecimal("100.00");
    private static final int ONE_HOUR_IN_MINUTES = 60;

    @Test
    public void throwsExceptionStockPriceMapIsNull() {

        // setup fixture
        List<StockPrice> priceList = null;
        TradingProfitAnalyzer tradingProfitAnalyzer = new TradingProfitAnalyzer();

        // exercise SUT + verify
        assertThrows(IllegalStateException.class, () -> {
            tradingProfitAnalyzer.determineMostProfitableTransaction(priceList);
        });
    }

    @Test
    public void throwsExceptionWhenEmptyStockPrices() {

        // setup fixture
        List<StockPrice> emptyPriceList = new ArrayList<>();
        TradingProfitAnalyzer tradingProfitAnalyzer = new TradingProfitAnalyzer();

        // exercise SUT + verify
        assertThrows(IllegalStateException.class, () -> {
            tradingProfitAnalyzer.determineMostProfitableTransaction(emptyPriceList);
        });
    }

    @Test
    public void throwsExceptionWhenStockPriceMapHasOnlyOnePrice() {

        // setup fixture
        List<StockPrice> priceListWithOneEntry = new ArrayList<>();
        priceListWithOneEntry.add(new StockPrice(60, STOCK_PRICE_70));
        TradingProfitAnalyzer tradingProfitAnalyzer = new TradingProfitAnalyzer();

        // exercise SUT + verify
        assertThrows(IllegalStateException.class, () -> {
            tradingProfitAnalyzer.determineMostProfitableTransaction(priceListWithOneEntry);
        });
    }

    @Test
    public void throwsExceptionWhenNegativeMinuteOffset() {

        // setup fixture
        List<StockPrice> priceListWithOneEntry = new ArrayList<>();
        priceListWithOneEntry.add(new StockPrice(-1, STOCK_PRICE_70));
        priceListWithOneEntry.add(new StockPrice(ONE_HOUR_IN_MINUTES, STOCK_PRICE_100));
        TradingProfitAnalyzer tradingProfitAnalyzer = new TradingProfitAnalyzer();

        // exercise SUT + verify
        assertThrows(IllegalStateException.class, () -> {
            tradingProfitAnalyzer.determineMostProfitableTransaction(priceListWithOneEntry);
        });
    }

}