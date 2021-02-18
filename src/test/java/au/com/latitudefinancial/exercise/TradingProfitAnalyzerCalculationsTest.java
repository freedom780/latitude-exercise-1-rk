package au.com.latitudefinancial.exercise;

import au.com.latitudefinancial.exercise.model.BuySellTransaction;
import au.com.latitudefinancial.exercise.model.StockPrice;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


class TradingProfitAnalyzerCalculationsTest {

    private static final BigDecimal STOCK_PRICE_50 = new BigDecimal("50.00");
    private static final BigDecimal STOCK_PRICE_70 = new BigDecimal("70.00");
    private static final BigDecimal STOCK_PRICE_100 = new BigDecimal("100.00");
    private static final BigDecimal STOCK_PRICE_200 = new BigDecimal("200.00");

    private static final BigDecimal STOCK_PRICE_10 = new BigDecimal("10.00");
    private static final BigDecimal STOCK_PRICE_7 = new BigDecimal("7.00");
    private static final BigDecimal STOCK_PRICE_5 = new BigDecimal("5.00");
    private static final BigDecimal STOCK_PRICE_8 = new BigDecimal("8.00");
    private static final BigDecimal STOCK_PRICE_11 = new BigDecimal("11.00");
    private static final BigDecimal STOCK_PRICE_9 = new BigDecimal("9.00");

    private static final int ONE_HOUR_IN_MINUTES = 60;
    private static final int TWO_HOURS_IN_MINUTES = 60 * 2;

    @Test
    public void calculatesWithTwoPricesOnly() {

        // setup fixture
        StockPrice entry70 = new StockPrice(ONE_HOUR_IN_MINUTES, STOCK_PRICE_70);
        StockPrice entry100 = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_100);
        List<StockPrice> twoEntries = createEntries(entry70, entry100);
        TradingProfitAnalyzer tradingProfitAnalyzer = new TradingProfitAnalyzer();

        // exercise SUT
        BuySellTransaction transaction = tradingProfitAnalyzer.determineMostProfitableTransaction(twoEntries);

        // verify
        BigDecimal expectedAmount = STOCK_PRICE_100.subtract(STOCK_PRICE_70);
        assertThat(transaction.calculateAmount().compareTo(expectedAmount), is(0));
    }


    @Test
    public void calculatesWithThreePricesWhenFirstAndThirdMostProfitable() {

        // setup fixture
        StockPrice entry50 = new StockPrice(ONE_HOUR_IN_MINUTES, STOCK_PRICE_50);
        StockPrice entry100 = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_100);
        StockPrice entry200 = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_200);
        List<StockPrice> prices = createEntries(entry50, entry100, entry200);
        TradingProfitAnalyzer tradingProfitAnalyzer = new TradingProfitAnalyzer();

        // exercise SUT
        BuySellTransaction transaction = tradingProfitAnalyzer.determineMostProfitableTransaction(prices);

        // verify
        BigDecimal expectedAmount = STOCK_PRICE_200.subtract(STOCK_PRICE_50);
        assertThat(transaction.calculateAmount().compareTo(expectedAmount), is(0));
    }

    @Test
    public void calculatesWithThreePricesWhenSecondAndThirdMostProfitable() {

        // setup fixture
        StockPrice entry100 = new StockPrice(ONE_HOUR_IN_MINUTES, STOCK_PRICE_100);
        StockPrice entry50 = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_50);
        StockPrice entry200 = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_200);
        List<StockPrice> prices = createEntries(entry100, entry50, entry200);
        TradingProfitAnalyzer tradingProfitAnalyzer = new TradingProfitAnalyzer();

        // exercise SUT
        BuySellTransaction transaction = tradingProfitAnalyzer.determineMostProfitableTransaction(prices);

        // verify
        BigDecimal expectedAmount = STOCK_PRICE_200.subtract(STOCK_PRICE_50);
        assertThat(transaction.calculateAmount().compareTo(expectedAmount), is(0));
    }

    @Test
    public void calculatesWithThreePricesWhenTwoMatchesProfitable() {

        // setup fixture
        StockPrice entry50FirstTime = new StockPrice(ONE_HOUR_IN_MINUTES, STOCK_PRICE_50);
        StockPrice entry100FirstTime = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_100);
        StockPrice entry50SecondTime = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_50);
        StockPrice entry100SecondTime = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_100);
        List<StockPrice> prices = createEntries(entry50FirstTime, entry100FirstTime, entry50SecondTime, entry100SecondTime);
        TradingProfitAnalyzer tradingProfitAnalyzer = new TradingProfitAnalyzer();

        // exercise SUT
        BuySellTransaction transaction = tradingProfitAnalyzer.determineMostProfitableTransaction(prices);

        // verify
        BigDecimal expectedAmount = STOCK_PRICE_100.subtract(STOCK_PRICE_50);
        assertThat(transaction.calculateAmount().compareTo(expectedAmount), is(0));
    }

    @Test
    public void calculatesWithThreeSamePrices() {

        // setup fixture
        StockPrice entry50FirstTime = new StockPrice(ONE_HOUR_IN_MINUTES, STOCK_PRICE_50);
        StockPrice entry50SecondTime = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_50);
        StockPrice entry50ThirdTime = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_50);
        List<StockPrice> prices = createEntries(entry50FirstTime, entry50SecondTime, entry50ThirdTime);
        TradingProfitAnalyzer tradingProfitAnalyzer = new TradingProfitAnalyzer();

        // exercise SUT
        BuySellTransaction transaction = tradingProfitAnalyzer.determineMostProfitableTransaction(prices);

        // verify
        BigDecimal expectedAmount = STOCK_PRICE_50.subtract(STOCK_PRICE_50);
        assertThat(transaction.calculateAmount().compareTo(expectedAmount), is(0));
    }

    @Test
    public void calculatesWithSixPricesWhenThirdAndFifthMostProfitable() {

        // setup fixture
        StockPrice entry10 = new StockPrice(ONE_HOUR_IN_MINUTES, STOCK_PRICE_10);
        StockPrice entry7 = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_7);
        StockPrice entry5 = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_5);
        StockPrice entry8 = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_8);
        StockPrice entry11 = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_11);
        StockPrice entry9 = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_9);

        List<StockPrice> prices = createEntries(entry10, entry7, entry5, entry8, entry11, entry9);
        TradingProfitAnalyzer tradingProfitAnalyzer = new TradingProfitAnalyzer();

        // exercise SUT
        BuySellTransaction transaction = tradingProfitAnalyzer.determineMostProfitableTransaction(prices);

        // verify
        BigDecimal expectedAmount = STOCK_PRICE_11.subtract(STOCK_PRICE_5);
        assertThat(transaction.calculateAmount().compareTo(expectedAmount), is(0));
    }

    @Test
    public void calculatesWithNonStopLosses() {

        // setup fixture
        StockPrice entry200 = new StockPrice(ONE_HOUR_IN_MINUTES, STOCK_PRICE_200);
        StockPrice entry100 = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_100);
        StockPrice entry50 = new StockPrice(TWO_HOURS_IN_MINUTES, STOCK_PRICE_50);
        List<StockPrice> prices = createEntries(entry200, entry100, entry50);
        TradingProfitAnalyzer tradingProfitAnalyzer = new TradingProfitAnalyzer();

        // exercise SUT
        BuySellTransaction transaction = tradingProfitAnalyzer.determineMostProfitableTransaction(prices);

        // verify
        BigDecimal expectedAmount = STOCK_PRICE_50.subtract(STOCK_PRICE_100);
        assertThat(transaction.calculateAmount().compareTo(expectedAmount), is(0));
    }

    private List<StockPrice> createEntries(StockPrice... entries) {
        List<StockPrice> result = new ArrayList<>();
        Collections.addAll(result, entries);
        return result;
    }

}