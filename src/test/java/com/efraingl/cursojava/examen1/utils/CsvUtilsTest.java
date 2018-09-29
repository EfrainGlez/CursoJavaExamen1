package com.efraingl.cursojava.examen1.utils;

import com.efraingl.cursojava.examen1.models.StockRegistry;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CsvUtilsTest {

    private Date fecha1, fecha2, fecha3;
    private BigDecimal cierre1, cierre2, cierre3, apertura1, apertura2, apertura3;

    @Test
    public void readStockRegistryCsvFile() throws ParseException {

        setVars();

        List<StockRegistry> stockRegistryList = CsvUtils.readStockRegistryCsvFileFromResources("stocks-ITX.csv");

        assertEquals(stockRegistryList.get(0).getFecha().toString(), fecha1.toString());
        assertEquals(stockRegistryList.get(1).getFecha().toString(), fecha2.toString());
        assertEquals(stockRegistryList.get(2).getFecha().toString(), fecha3.toString());

        System.out.println(stockRegistryList.get(0).getCierre().toString());
        System.out.println(cierre1.toString());

        assertEquals(stockRegistryList.get(0).getCierre().toString(), cierre1.toString());
        assertEquals(stockRegistryList.get(1).getCierre().toString(), cierre2.toString());
        assertEquals(stockRegistryList.get(2).getCierre().toString(), cierre3.toString());

        assertEquals(stockRegistryList.get(0).getApertura().toString(), apertura1.toString());
        assertEquals(stockRegistryList.get(1).getApertura().toString(), apertura2.toString());
        assertEquals(stockRegistryList.get(2).getApertura().toString(), apertura3.toString());
    }

    private void setVars() throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");

        fecha1 = dt.parse("28-12-2017");
        fecha2 = dt.parse("27-12-2017");
        fecha3 = dt.parse("22-12-2017");

        cierre1 = new BigDecimal("29.17");
        cierre2 = new BigDecimal("29.58");
        cierre3 = new BigDecimal("29.615");

        apertura1 = new BigDecimal("29.6");
        apertura2 = new BigDecimal("29.705");
        apertura3 = new BigDecimal("29.66");
    }
}