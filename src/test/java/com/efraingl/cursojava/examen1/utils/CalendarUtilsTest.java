package com.efraingl.cursojava.examen1.utils;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class CalendarUtilsTest {

    @Test
    public void getLastThursday() throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha1 = dt.parse("30-03-2017");
        Date fecha2 = CalendarUtils.getLastThursday(3, 2017);

        assertEquals(fecha1.toString(), fecha2.toString());
    }

    @Test
    public void getTheNextDayToTheLastThursday() throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha1 = dt.parse("31-03-2017");
        Date fecha2 = CalendarUtils.getTheNextDayToTheLastThursday(3, 2017);

        assertEquals(fecha1.toString(), fecha2.toString());
    }

    @Test
    public void getNextDay() throws  ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha1 = dt.parse("31-03-2017");
        Date fecha2 = dt.parse("01-04-2017");


        Date fecha3 = CalendarUtils.getNextDay(fecha1);
        assertEquals(fecha2.toString(), fecha3.toString());
    }
}