package org.application.Models;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Employee {
    private final String ID;
    private final List<Activity> activities = new ArrayList<>();
    private final List<TimeBlock> timeBlocks = new ArrayList<>();

    public Employee(String ID) {
        this.ID = ID;
        SystemModel.addEmployee(this);
    }

    public void addActivity(Activity activity) {
        assert activity != null;
        assert activities != null;
        if (!activities.contains(activity)) { // 1
            activities.add(activity); // 2
            // ORDER is important, to avoid infinite calling
            activity.assignEmployee(this); // 3
        } // 4
        assert activities.contains(activity);
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
     *
     * @param activity      The activity worked on in the timeblock
     * @param startHalfHour The start time of the timeblock, at a resolution down to half-hours
     * @param endHalfHour   The end time of the timeblock, at a resolution down to half-hours
     */
    public void createTimeBlock(ProjectActivity activity, GregorianCalendar startHalfHour,
                                GregorianCalendar endHalfHour) {
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

    public List<TimeBlock> getTimeBlocks() {
        return timeBlocks;
    }

    /**
     * Higher number means more availability. 0 means not available at all.
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public int getAvailabilityScore(GregorianCalendar startTime, GregorianCalendar endTime) {
        long total = 9999999;
        long milliseconds1 = startTime.getTimeInMillis();
        long milliseconds2 = endTime.getTimeInMillis();
        long diff = milliseconds2 - milliseconds1;
        if (diff <= 0) // 1
        {
            return 0; // 2
        }
        // Use Math.ceil to round up the result
        int totalDays = (int) Math.ceil((double) diff / (24 * 60 * 60 * 1000));

        int freeDayCounter = totalDays;

        int busyActivities = 0;
        for (Activity activity : activities) { // 3
            if (doPeriodsOverlap(activity.getStartDate(), activity.getEndDate(), startTime, endTime)) { // 4
                if (activity instanceof ReservedActivity) { // 5
                    freeDayCounter--; // 6
                }
                else if (activity instanceof ProjectActivity) {// 7
                    busyActivities += 1; // 8
                }
            }
        }
        return (int) ((total - busyActivities) * freeDayCounter / totalDays); // 9
    }

    public Activity getActivity(String searchActivity){
        for (Activity activity: activities){
            if (searchActivity.equals(activity.getName())){
                return activity;
            }
        }
        return null;
    }

    /**
     * Calculates if the two periods defined by [start1, end1] and [start2, end2] overlaps
     *
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return
     */
    public boolean doPeriodsOverlap(GregorianCalendar start1, GregorianCalendar end1, GregorianCalendar start2,
                                    GregorianCalendar end2) {
        return !start1.after(end2) && !start2.after(end1);
    }


    public String getID() {
        return ID;
    }

    @Override
    public String toString() {
        return getID();
    }
}
