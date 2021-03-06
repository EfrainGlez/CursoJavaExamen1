package com.efraingl.cursojava.examen1.business;

import com.efraingl.cursojava.examen1.models.StockRegistry;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StockExchange {

    private List<StockRegistry> stockRegistryList;
    private BigDecimal shares, moneyInvested;


    public StockExchange(List<StockRegistry> stockRegistries) {
        stockRegistryList = stockRegistries;
        shares = new BigDecimal("0.0");
        moneyInvested = new BigDecimal("0.0");
    }

    public BigDecimal getMoneyInvested() { return moneyInvested; }

    public void setMoneyInvested(BigDecimal moneyInvested) { this.moneyInvested = moneyInvested; }

    public void addMoneyInvested(BigDecimal money) { this.moneyInvested = this.moneyInvested.add(money); }

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

        BigDecimal newShares = quantity.divide(sharePrice, 3, BigDecimal.ROUND_HALF_UP);
        addShares(newShares);
        addMoneyInvested(quantity);

        System.out.println(getInvestInfo(date));
    }

    public BigDecimal sellShares(Date date) {
        BigDecimal sharePrice = getClosePrice(date);
        if(sharePrice == null) { return new BigDecimal("0.0"); }
        BigDecimal shares = getShares();

        BigDecimal money = shares.multiply(sharePrice).setScale(3, BigDecimal.ROUND_HALF_UP);
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
        BigDecimal shares = getShares().setScale(3, BigDecimal.ROUND_HALF_UP);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return new StringBuilder()
                .append("Investment: ")
                .append(dateFormat.format(date))
                .append(" | ")
                .append("Money invested actually: ")
                .append(getMoneyInvested())
                .append("€ | ")
                .append("Shares of stock: ")
                .append(shares)
                .toString();
    }

    public StockRegistry getStockRegistryGreaterOrEqual(Date date) {
        for (StockRegistry stockRegistry : stockRegistryList) {
            if (stockRegistry.getFecha().compareTo(date) >= 0) {
                return stockRegistry;
            }
        }

        return null;
    }
}