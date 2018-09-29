package com.efraingl.cursojava.examen1;

import com.efraingl.cursojava.examen1.business.StockExchange;
import com.efraingl.cursojava.examen1.models.StockRegistry;
import com.efraingl.cursojava.examen1.utils.CalendarUtils;
import com.efraingl.cursojava.examen1.utils.CsvUtils;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<StockRegistry> stockRegistryList = CsvUtils.readStockRegistryCsvFileFromResources("stocks-ITX.csv");
        StockExchange stockExchange = new StockExchange(stockRegistryList);

        Date fechaEfra, fechaEfra2;
        for (int year = 2001; year <= 2017; year++) {
            for (int month = 1; month <= 12; month++) {
                fechaEfra = CalendarUtils.getLastThursday(month, year);
                fechaEfra2 = CalendarUtils.getTheNextDayToTheLastThursday(month, year);
                System.out.print("Mes: " + month + ", Year: " + year + ": ");
                System.out.print(fechaEfra.toString());
                System.out.print("---- Día siguiente: Mes: " + month + ", Year: " + year + ": ");
                System.out.println(fechaEfra2.toString());
            }
         }






    }


}