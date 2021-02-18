package au.com.latitudefinancial.exercise;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


class TradingProfitAnalyzerInvalidDataTest {

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
        priceListWithOneEntry.add(new StockPrice(60, new BigDecimal("70.54")));
        TradingProfitAnalyzer tradingProfitAnalyzer = new TradingProfitAnalyzer();

        // exercise SUT + verify
        assertThrows(IllegalStateException.class, () -> {
            tradingProfitAnalyzer.determineMostProfitableTransaction(priceListWithOneEntry);
        });
    }

}