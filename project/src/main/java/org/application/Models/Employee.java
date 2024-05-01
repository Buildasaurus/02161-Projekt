package org.application.Models;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Employee {
    String ID;
    List<Activity> activities = new ArrayList<>();
    List<TimeBlock> timeBlocks = new ArrayList<>();

    public Employee(String ID) {
        this.ID = ID;
        SystemModel.addEmployee(this);
    }

    public void addActivity(Activity activity) {
        if (!activities.contains(activity)) {
            activities.add(activity);
            // ORDER is important, to avoid infinite calling
            activity.assignEmployee(this);
        }
    }

    public void removeActivity(Activity activity) {
        if (activities.contains(activity)) {
            activities.remove(activity);
            // ORDER is important, to avoid infinite calling
            activity.removeEmployee(ID);
        }
    }

    /**
     * Creates a timeblock with the current employee assigned
     * @param activity The activity worked on in the timeblock
     * @param startHalfHour The start time of the timeblock, at a resolution down to half-hours
     * @param endHalfHour The end time of the timeblock, at a resolution down to half-hours
     */
    public void createTimeBlock(ProjectActivity activity, GregorianCalendar startHalfHour, GregorianCalendar endHalfHour) {
        TimeBlock timeBlock = new TimeBlock(startHalfHour, endHalfHour, activity, this);
        timeBlocks.add(timeBlock);
        activity.addTimeBlock(timeBlock);
    }

    public void deleteTimeBlock(TimeBlock timeBlock) {
        timeBlocks.remove(timeBlock);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public Activity getActivity(String searchActivity){
        for (Activity activity: activities){
            if (searchActivity.equals(activity.getName())){
                return activity;
            }
        }
        return null;
    }

    public List<TimeBlock> getTimeBlocks() {
        return timeBlocks;
    }

    /**
     * Higher number means more availability. 0 means not available at all.
     * @param startTime
     * @param endTime
     * @return
     */
    public int getAvailabilityScore(GregorianCalendar startTime, GregorianCalendar endTime)
    {
        long total = 9999999;
        long milliseconds1 = startTime.getTimeInMillis();
        long milliseconds2 = endTime.getTimeInMillis();
        long diff = milliseconds2 - milliseconds1;
        if (diff <= 0)
        {
            return 0;
        }
        // Use Math.ceil to round up the result
        int totalDays = (int) Math.ceil((double) diff / (24 * 60 * 60 * 1000));

        int freeDayCounter = totalDays;

        int busyActivities = 0;
        for(Activity activity : activities)
        {
            if(doPeriodsOverlap(activity.getStartDate(),activity.getEndDate(),startTime,endTime))
            {
                if (activity instanceof ReservedActivity)
                {
                    freeDayCounter--;
                }
                if (activity instanceof ProjectActivity)
                {
                    busyActivities += 1;
                }
            }
        }
        return (int)((total - busyActivities)*freeDayCounter/totalDays);
    }



    /**
     * Calculates if the two periods defined by [start1, end1] and [start2, end2] overlaps
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return
     */
    public boolean doPeriodsOverlap(GregorianCalendar start1, GregorianCalendar end1, GregorianCalendar start2, GregorianCalendar end2) {
        return !start1.after(end2) && !start2.after(end1);
    }


    public String getID() {
        return ID;
    }

    @Override
    public String toString()
    {
        return getID();
    }
}
