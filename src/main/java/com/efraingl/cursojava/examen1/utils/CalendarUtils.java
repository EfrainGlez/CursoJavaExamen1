package com.efraingl.cursojava.examen1.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static java.time.DayOfWeek.THURSDAY;
import static java.time.temporal.TemporalAdjusters.lastInMonth;

public class CalendarUtils {

    public static Date getLastThursday(int month, int year) {
        LocalDate lastThursday = LocalDate.of(year, month, 1).with(lastInMonth(THURSDAY));

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = java.sql.Date.valueOf(lastThursday);

        try {
            date = dt.parse(date.toString());
        } catch (ParseException e) {
            System.out.println(e);
        }


        return date;
    }

    public static Date getNextDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);

        return c.getTime();
    }

    public static Date getTheNextDayToTheLastThursday(int month, int year) {
        Date lastThursday = getLastThursday(month, year);

        return getNextDay(lastThursday);
    }
}
