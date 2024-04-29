package org.application.Models;

import java.util.GregorianCalendar;

public class ReservedActivity extends Activity{
    public ReservedActivity(GregorianCalendar startDay, GregorianCalendar endDay, String name) {
        super(name, startDay, endDay);
    }

}
