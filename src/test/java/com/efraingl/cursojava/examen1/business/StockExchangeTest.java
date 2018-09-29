package com.efraingl.cursojava.examen1.business;

import com.efraingl.cursojava.examen1.models.StockRegistry;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StockExchangeTest {

    @Test
    public void getShares() {
        List<StockRegistry> stockRegistryList = new ArrayList<>();

        StockExchange stockExchange = new StockExchange(stockRegistryList);

        assertEquals(stockExchange.getShares().toString(), "0.0");
    }
}