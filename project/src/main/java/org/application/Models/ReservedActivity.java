package org.application.Models;

import java.util.GregorianCalendar;

public class ReservedActivity extends Activity{
    private GregorianCalendar startDay;
    private GregorianCalendar endDay;

    public ReservedActivity(GregorianCalendar startDay, GregorianCalendar endDay, String name) {
        super(name);
        this.startDay = startDay;
        this.endDay = endDay;
    }
    public GregorianCalendar getEndDay() {
        return endDay;
    }
    public GregorianCalendar getStartDay() {
        return startDay;
    }
}
