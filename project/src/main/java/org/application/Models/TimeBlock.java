package org.application.Models;

import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class TimeBlock {
    /**
     * Start time in resolution of halfhours
     */
    private GregorianCalendar startTime;
    /**
     * End time in resolution of halfhours
     */
    private GregorianCalendar endTime;
    private ProjectActivity activity;
    private Employee employee;

    /**
     * A timeblock should never be created by itself. Instead use employee.createTimeblock()
     * Initializes a block to keep track of what activity has been worked on in a given time period
     *
     * @param startTime The start time of the working period, measured on a resolution of half-hours
     * @param endTime   The end time of the working period, measured on a resolution of half-hours
     * @param activity  The activity worked on in the period
     * @param employee  The employee working on the activity TODO: (should be several later)
     */
    public TimeBlock(GregorianCalendar startTime, GregorianCalendar endTime, ProjectActivity activity, Employee employee) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
        this.employee = employee;
    }

    public Activity getActivity() {
        return activity;
    }

    public GregorianCalendar getStartTime() {
        return startTime;
    }

    public GregorianCalendar getEndTime() {
        return endTime;
    }


    /**
     * Calculates the duration of the current timeblock in half-hours, and returns it as an int
     *
     * @return the duration of the timeblock in half-hours as int.
     */
    public int getDuration() {
        long diffInMillis = endTime.getTimeInMillis() - startTime.getTimeInMillis();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
        return (int) Math.round(minutes / 30.0);
    }

    public void delete()
    {
        employee.deleteTimeBlock(this);
        activity.removeTimeBlock(this);
    }
}
