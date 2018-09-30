package com.efraingl.cursojava.examen1;

import com.efraingl.cursojava.examen1.business.StockExchange;
import com.efraingl.cursojava.examen1.models.StockRegistry;
import com.efraingl.cursojava.examen1.utils.CalendarUtils;
import com.efraingl.cursojava.examen1.utils.CsvUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<StockRegistry> stockRegistryList = CsvUtils.readStockRegistryCsvFileFromResources("stocks-ITX.csv");
        StockExchange stockExchange = new StockExchange(stockRegistryList);

        BigDecimal moneyToInvest = (new BigDecimal("50").multiply(new BigDecimal("0.98")));
        Date actualDate = CalendarUtils.getDate("01-05-2001");
        Date investMaxDate = CalendarUtils.getDate("01-12-2017");

        while (actualDate.compareTo(investMaxDate) != 0) {
            Date nextDayToTheLastThursday = CalendarUtils.getTheNextDayToTheLastThursday(actualDate);
            stockExchange.invest(nextDayToTheLastThursday, moneyToInvest);

            actualDate = CalendarUtils.getNextMonth(actualDate);
        }

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date sellDate = CalendarUtils.getDate("28-12-2017");
        BigDecimal finalShares = stockExchange.getShares();
        BigDecimal finalMoney = stockExchange.sellShares(sellDate);
        StockRegistry stockRegistry = stockExchange.getStockRegistryGreaterOrEqual(sellDate);

        String sellString = new StringBuilder()
                .append(dateFormat.format(sellDate)).append(" --> sell ").append(finalShares)
                .append(" stock shares, with a value of ").append(stockRegistry.getCierre())
                .append(" €/share.\nYou has won ").append(finalMoney).append("€ on the stock market.").toString();

        System.out.println(sellString);
    }
}