package com.efraingl.cursojava.examen1;

import com.efraingl.cursojava.examen1.business.StockExchange;
import com.efraingl.cursojava.examen1.models.StockRegistry;
import com.efraingl.cursojava.examen1.utils.CsvUtils;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<StockRegistry> stockRegistryList = CsvUtils.readStockRegistryCsvFileFromResources("stocks-ITX.csv");
        StockExchange stockExchange = new StockExchange(stockRegistryList);
    }
}