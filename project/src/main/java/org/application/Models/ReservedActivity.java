package org.application.Models;
import java.util.Date;
public class ReservedActivity extends Activity{
    Date startDay;
    Date endDay;
    public ReservedActivity(Date startDay, Date endDay, String name) {
        super(name);
        this.startDay = startDay;
        this.endDay = endDay;
    }

}
