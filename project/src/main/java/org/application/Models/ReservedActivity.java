package org.application.Models;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReservedActivity extends Activity{
    public ReservedActivity(GregorianCalendar startDay, GregorianCalendar endDay, String name) {
        super(name, startDay, endDay);
    }
    public int getStartDay(){
        return getStartDate().get(Calendar.DAY_OF_MONTH);
    }

    public int getEndDay(){
        return getEndDate().get(Calendar.DAY_OF_MONTH);
    }

    public int getEndMonth(){
        return getEndDate().get(Calendar.MONTH);
    }

}
