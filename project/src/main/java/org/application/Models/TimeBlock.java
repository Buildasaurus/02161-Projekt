package org.application.Models;
import java.util.GregorianCalendar;
public class TimeBlock {
    GregorianCalendar startTime;
    GregorianCalendar endTime;
    Activity activity;
    Employee employee;

    public TimeBlock(GregorianCalendar startTime, GregorianCalendar endTime, Activity activity, Employee employee) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
        this.employee = employee;
    }

    public Activity getActivity()
    {
        return activity;
    }

    public GregorianCalendar getStartTime()
    {
        return startTime;
    }

    public GregorianCalendar getEndTime()
    {
        return endTime;
    }

    /**
     * Calculates the duration of the current timeblock, and returns it as an int
     * @return the duration of the timeblock as int.
     */
    public int getDuration()
    {

    }
}
