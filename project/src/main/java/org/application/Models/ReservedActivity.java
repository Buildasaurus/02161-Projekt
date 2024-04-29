package org.application.Models;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReservedActivity extends Activity{
    private GregorianCalendar startDay;
    private GregorianCalendar endDay;

    public ReservedActivity(GregorianCalendar startDay, GregorianCalendar endDay, String name) {
        super(name);
        this.startDay = startDay;
        this.endDay = endDay;
    }
    public Integer getStartDay(){
        return startDay.get(Calendar.DAY_OF_MONTH);
    }

    public Integer getEndDay(){
        return endDay.get(Calendar.DAY_OF_MONTH);
    }

    public Integer getEndMonth(){
        return endDay.get(Calendar.MONTH);
    }
}
