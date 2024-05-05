package org.application.Utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

public class GeneralMethods {
    public static GregorianCalendar convertDatePickerToCalender(DatePicker date) {
        // Assume datePicker is your DatePicker object
        LocalDate localDate = date.getValue();
        if (localDate == null) {
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
        return LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static void startSelection(ComboBox<String> start, ComboBox<String> end, ArrayList<Integer> freeHalfHours) {       
        if (start.getValue() != null) {
            ArrayList<Integer> res = new ArrayList<>();
            boolean hit = false;
            for (int i = 0; i < freeHalfHours.size(); i++) {
                if (hit) {
                    if (freeHalfHours.get(i) - 1 == freeHalfHours.get(i - 1)) {
                        res.add(freeHalfHours.get(i));
                    } else {
                        break;
                    }
                }
                if (freeHalfHours.get(i) == timeStringToHalfHours(start.getValue())) {
                    hit = true;
                }
            }
            end.setItems(halfHourListToStringList(res));
        }
    }

    public static void endSelection(ComboBox<String> start, ComboBox<String> end, ArrayList<Integer> freeHalfHours) {
        if (end.getValue() != null) {
            ArrayList<Integer> res = new ArrayList<>();
            boolean hit = false;
            for (int i = freeHalfHours.size() - 1; i >= 0; i--) {
                if (hit) {
                    if (freeHalfHours.get(i) + 1 == freeHalfHours.get(i + 1)) {
                        res.add(freeHalfHours.get(i));
                    } else {
                        break;
                    }
                }
                if (freeHalfHours.get(i) == timeStringToHalfHours(end.getValue())) {
                    hit = true;
                }
            }
            Collections.reverse(res);
            start.setItems(halfHourListToStringList(res));
        }
    }

    private static int timeStringToHalfHours(String time) {
        int halfHours = 0;
        if (time.startsWith("0")) {
            halfHours += Integer.parseInt(time.substring(1, 2)) * 2;
        }
        else {
            halfHours += Integer.parseInt(time.substring(0, 2)) * 2;
        }
        if (time.charAt(3) == '3') {
            halfHours += 1;
        }
        return halfHours;
    }

    public static ObservableList<String> halfHourListToStringList(ArrayList<Integer> halfHourList) {
        ObservableList<String> res = FXCollections.observableArrayList(new ArrayList<>());
        for (Integer halfHour : halfHourList) {
            // format
            int hours = halfHour / 2;
            String timeStr = "";
            if (hours >= 10) {
                timeStr += hours;
            }
            else {
                timeStr += 0;
                timeStr += hours;
            }
            if (halfHour % 2 == 1) {
                timeStr += ":30";
            }
            else {
                timeStr += ":00";
            }
            res.add(timeStr);
        }
        return res;
    }

    public static GregorianCalendar[] stringsToCalendarList(String startTime, String endTime) {
        GregorianCalendar currentTime = new GregorianCalendar();
        GregorianCalendar[] calendars = new GregorianCalendar[2];
        int[] startTimes = stringToHoursAndMinutes(startTime);
        int[] endTimes = stringToHoursAndMinutes(endTime);
        GregorianCalendar startDate = new GregorianCalendar(currentTime.get(Calendar.YEAR),
                currentTime.get(Calendar.MONTH), currentTime.get(Calendar.DAY_OF_MONTH), startTimes[0], startTimes[1]);
        GregorianCalendar endDate = new GregorianCalendar(currentTime.get(Calendar.YEAR),
                currentTime.get(Calendar.MONTH), currentTime.get(Calendar.DAY_OF_MONTH), endTimes[0], endTimes[1]);
        calendars[0] = startDate;
        calendars[1] = endDate;
        return calendars;
    }

    private static int[] stringToHoursAndMinutes(String time) {
        int[] res = new int[2];
        int hours = 0;
        if (time.startsWith("0")) {
            hours = Integer.parseInt(time.substring(1, 2));
        }
        else {
            hours = Integer.parseInt(time.substring(0, 2));
        }
        res[0] = hours;
        res[1] = Integer.parseInt(time.substring(3, 5));
        return res;
    }
}
