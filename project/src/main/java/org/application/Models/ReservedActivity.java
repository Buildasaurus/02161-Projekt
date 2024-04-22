package org.application.Models;
import java.util.GregorianCalendar;
public class ReservedActivity extends Activity{
    GregorianCalendar startDay;
    GregorianCalendar endDay;
    public ReservedActivity(GregorianCalendar startDay, GregorianCalendar endDay, String name) {
        super(name);
        this.startDay = startDay;
        this.endDay = endDay;
    }

}
