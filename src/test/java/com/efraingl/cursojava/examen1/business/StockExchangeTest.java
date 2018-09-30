package com.efraingl.cursojava.examen1.business;

import com.efraingl.cursojava.examen1.models.StockRegistry;
import com.efraingl.cursojava.examen1.utils.CsvUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class StockExchangeTest {

    private List<StockRegistry> stockRegistryListEmpty, stockRegistryListWithData;

    @Before
    public void setUp() {
        stockRegistryListEmpty = new ArrayList<>();
        stockRegistryListWithData = CsvUtils.readStockRegistryCsvFileFromResources("stocks-ITX.csv");
    }

    @Test
    public void getShares() {
        StockExchange stockExchange = new StockExchange(stockRegistryListEmpty);
        assertEquals(stockExchange.getShares().toString(), "0.0");
    }

    @Test
    public void setShares() {
        StockExchange stockExchange = new StockExchange(stockRegistryListEmpty);
        stockExchange.setShares(new BigDecimal("5.678"));
        assertEquals(stockExchange.getShares().toString(), "5.678");
    }

    @Test
    public void addShares() {
        StockExchange stockExchange = new StockExchange(stockRegistryListEmpty);
        stockExchange.addShares(new BigDecimal("2.1"));
        stockExchange.addShares(new BigDecimal("1.211"));
        assertEquals(stockExchange.getShares().toString(), "3.311");
    }

    @Test
    public void invest() throws ParseException {
        StockExchange stockExchange = new StockExchange(stockRegistryListWithData);
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = dt.parse("27-12-2017");

        stockExchange.invest(fecha, new BigDecimal("1000.0"));

        BigDecimal shares = stockExchange.getShares();
        BigDecimal expectedShares = (new BigDecimal("29.705")).multiply(new BigDecimal("1000.0"));

        assertEquals(shares.compareTo(expectedShares), 0);
    }

    @Test
    public void sellShares() throws ParseException {
        StockExchange stockExchange = new StockExchange(stockRegistryListWithData);
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = dt.parse("27-12-2017");

        stockExchange.invest(fecha, new BigDecimal("1000.0"));

        BigDecimal money = stockExchange.sellShares(fecha);
        BigDecimal expectedMoney = (new BigDecimal("29705.0"))
                .divide(new BigDecimal("29.58"), 3, BigDecimal.ROUND_HALF_UP);

        System.out.println(money.toString());
        System.out.println(expectedMoney.toString());

        assertEquals(money.compareTo(expectedMoney), 0);
    }

    @Test
    public void getOpeningPrice() throws ParseException {
        StockExchange stockExchange = new StockExchange(stockRegistryListWithData);

        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = dt.parse("23-12-2017");

        BigDecimal openingPrice = stockExchange.getOpeningPrice(fecha);

        assertEquals(openingPrice.compareTo(new BigDecimal("29.705")), 0);
    }

    @Test
    public void getClosePrice() throws ParseException {
        StockExchange stockExchange = new StockExchange(stockRegistryListWithData);

        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = dt.parse("27-12-2017");

        BigDecimal closePrice = stockExchange.getClosePrice(fecha);

        assertEquals(closePrice.compareTo(new BigDecimal("29.58")), 0);
    }

    @Test
    public void getOpeningPriceWithNullResult() throws ParseException {
        StockExchange stockExchange = new StockExchange(stockRegistryListWithData);

        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = dt.parse("30-12-2017");

        BigDecimal openingPrice = stockExchange.getOpeningPrice(fecha);

        assertNull(openingPrice);
    }

    @Test
    public void getClosePriceWithNullResult() throws ParseException {
        StockExchange stockExchange = new StockExchange(stockRegistryListWithData);

        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = dt.parse("05-05-2018");

        BigDecimal closePrice = stockExchange.getClosePrice(fecha);

        assertNull(closePrice);
    }

    @Test
    public void getInvestInfo() throws ParseException {
        StockExchange stockExchange = new StockExchange(stockRegistryListWithData);
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = dt.parse("27-12-2017");

        stockExchange.invest(fecha, new BigDecimal("1000.0"));

        String output = stockExchange.getInvestInfo(fecha);
        String expectedOutput = "Shares quantity: 29705.000 - If you sell now you get: 1004.226€";

        assertEquals(output.compareTo(expectedOutput), 0);
    }
}