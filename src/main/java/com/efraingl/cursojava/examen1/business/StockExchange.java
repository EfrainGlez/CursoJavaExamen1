package com.efraingl.cursojava.examen1.business;

import com.efraingl.cursojava.examen1.models.StockRegistry;

import java.math.BigDecimal;
import java.util.Date;
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

    public void addShares(BigDecimal shares) { this.shares = this.shares.add(shares); }

    public void invest(Date date, BigDecimal quantity) {
        BigDecimal sharePrice = getOpeningPrice(date);
        if(sharePrice == null) { return; }

        BigDecimal newShares = quantity.multiply(sharePrice);
        addShares(newShares);
    }

    public BigDecimal sellShares(Date date) {
        BigDecimal sharePrice = getClosePrice(date);
        if(sharePrice == null) { return new BigDecimal("0.0"); }
        BigDecimal shares = getShares();

        BigDecimal money = shares.divide(sharePrice, 3, BigDecimal.ROUND_HALF_UP);
        setShares(new BigDecimal("0.0"));

        return money;
    }

    public BigDecimal getOpeningPrice(Date date) {
        StockRegistry stockRegistry = getStockRegistryGreaterOrEqual(date);

        return stockRegistry == null ? null : stockRegistry.getApertura();
    }

    public BigDecimal getClosePrice(Date date) {
        StockRegistry stockRegistry = getStockRegistryGreaterOrEqual(date);

        return stockRegistry == null ? null : stockRegistry.getCierre();
    }

    public String getInvestInfo(Date date) {
        BigDecimal closePrice = getClosePrice(date);
        BigDecimal shares = getShares().setScale(3, BigDecimal.ROUND_HALF_UP);
        BigDecimal money = shares.divide(closePrice, 3, BigDecimal.ROUND_HALF_UP);

        return (new StringBuilder())
                .append("Shares quantity: ")
                .append(shares)
                .append(" - If you sell now you get: ")
                .append(money)
                .append("€")
                .toString();
    }

    private StockRegistry getStockRegistryGreaterOrEqual(Date date) {
        for (StockRegistry stockRegistry : stockRegistryList) {
            if (stockRegistry.getFecha().compareTo(date) >= 0) {
                return stockRegistry;
            }
        }

        return null;
    }
}