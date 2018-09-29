package com.efraingl.cursojava.examen1.models;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class StockRegistryTest {

    @Test
    public void constructorConParametros() throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = dt.parse("05-05-1979");
        BigDecimal apertura = new BigDecimal(123.321);
        BigDecimal cierre = new BigDecimal(123.321);

        StockRegistry stockRegistry = new StockRegistry(fecha, cierre, apertura);

        assertEquals(stockRegistry.getFecha(), fecha);
        assertEquals(stockRegistry.getCierre(), cierre);
        assertEquals(stockRegistry.getApertura(), apertura);
    }

    @Test
    public void constructorSinParametros() {
        StockRegistry stockRegistry = new StockRegistry();

        assertNull(stockRegistry.getFecha());
        assertNull(stockRegistry.getCierre());
        assertNull(stockRegistry.getApertura());
    }

    @Test
    public void gettersYSetters() throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = dt.parse("05-05-1979");
        BigDecimal apertura = new BigDecimal(123.321);
        BigDecimal cierre = new BigDecimal(123.321);

        StockRegistry stockRegistry = new StockRegistry();
        stockRegistry.setFecha(fecha);
        stockRegistry.setCierre(cierre);
        stockRegistry.setApertura(apertura);

        assertEquals(stockRegistry.getFecha(), fecha);
        assertEquals(stockRegistry.getCierre(), cierre);
        assertEquals(stockRegistry.getApertura(), apertura);
    }

}