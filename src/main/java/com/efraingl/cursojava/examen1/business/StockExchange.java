package com.efraingl.cursojava.examen1.business;

import com.efraingl.cursojava.examen1.models.StockRegistry;
import com.efraingl.cursojava.examen1.utils.CsvUtils;

import java.math.BigDecimal;
import java.util.List;

public class StockExchange {

    private List<StockRegistry> stockRegistryList;
    private BigDecimal shares;


    public StockExchange(List<StockRegistry> stockRegistries) {

        stockRegistryList = stockRegistries;
        shares = new BigDecimal("0.0");
    }

    public BigDecimal getShares() {
        return shares;
    }

    public void setShares(BigDecimal shares) {
        this.shares = shares;
    }
}