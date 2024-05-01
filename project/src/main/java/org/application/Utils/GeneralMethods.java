package org.application.Utils;

import javafx.scene.control.DatePicker;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
}
