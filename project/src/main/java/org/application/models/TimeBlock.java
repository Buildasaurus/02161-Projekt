package org.application.Models;
import java.util.Date;
public class TimeBlock {
    Date startTime;
    Date endTime;
    Activity activity;
    Employee employee;

    public TimeBlock(Date startTime, Date endTime, Activity activity, Employee employee) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
        this.employee = employee;
    }
}
