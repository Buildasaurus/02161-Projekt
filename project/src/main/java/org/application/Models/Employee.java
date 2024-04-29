package org.application.Models;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Employee {
    String ID;
    List<Activity> activities = new ArrayList<>();
    List<TimeBlock> timeBlocks = new ArrayList<>();

    public Employee(String ID) {
        this.ID = ID;
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

    public void createTimeBlock(ProjectActivity activity, GregorianCalendar startDate, GregorianCalendar endDate) {
        TimeBlock timeBlock = new TimeBlock(startDate, endDate, activity, this);
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

    public int getAvailabilityScore(GregorianCalendar startTime, GregorianCalendar endTime)
    {
        return 2;
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
