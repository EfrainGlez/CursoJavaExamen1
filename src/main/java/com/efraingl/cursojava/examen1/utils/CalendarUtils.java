package com.efraingl.cursojava.examen1.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static java.time.DayOfWeek.THURSDAY;
import static java.time.temporal.TemporalAdjusters.lastInMonth;

public class CalendarUtils {

    public static Date getLastThursday(Date date) {
        LocalDate initialDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate lastThursday = initialDate.with(lastInMonth(THURSDAY));

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date finalDate = java.sql.Date.valueOf(lastThursday);

        try {
            finalDate = dt.parse(finalDate.toString());
        } catch (ParseException e) {
            System.out.println(e);
        }

        return finalDate;
    }

    public static Date getDate(String date) {
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        Date outputDate = null;

        try {
            outputDate = dt.parse(date);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return outputDate;
    }

    public static Date getNextDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);

        return c.getTime();
    }

    public static Date getNextMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 1);

        return c.getTime();
    }

    public static Date getTheNextDayToTheLastThursday(Date date) {
        Date lastThursday = getLastThursday(date);

        return getNextDay(lastThursday);
    }

}
