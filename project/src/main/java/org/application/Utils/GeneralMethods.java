package org.application.Utils;

import javafx.scene.control.DatePicker;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GeneralMethods
{
    public static GregorianCalendar convertDatePickerToCalender(DatePicker date)
    {
        // Assume datePicker is your DatePicker object
        LocalDate localDate = date.getValue();
        if(localDate == null)
        {
            return null;
        }

        // Convert LocalDate to Instant
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay(defaultZoneId).toInstant();

        // Convert Instant to java.util.Date
        Date instantdate = Date.from(instant);

        // Convert java.util.Date to java.util.Calendar
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(instantdate);
        return calendar;
    }

    public static GregorianCalendar intToCalendar(int weekNumber) {
        // Get the current year
        int year = Calendar.getInstance().get(Calendar.YEAR);

        // Create a new GregorianCalendar object
        GregorianCalendar calendar = new GregorianCalendar();

        // Set the year and week number
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);

        // Set the day of week to Monday (which is 2 in GregorianCalendar)
        calendar.set(Calendar.DAY_OF_WEEK, 2);

        return calendar;
    }

    public static GregorianCalendar intToCalendar(int weekNumber, int yearNumber) {
        // Create a new GregorianCalendar object
        GregorianCalendar calendar = new GregorianCalendar();

        // Set the year and week number
        calendar.set(Calendar.YEAR, yearNumber);
        calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);

        // Set the day of week to Monday (which is 2 in GregorianCalendar)
        calendar.set(Calendar.DAY_OF_WEEK, 2);

        return calendar;
    }

    public static LocalDate convertCalendarToLocalDate(GregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }
        return LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    }

}
