package org.application.Models;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ProjectActivity extends Activity {
    /**
     * Expected duration of the given activity, measured in half-hours
     */
    private int expectedDuration;
    private List<TimeBlock> timeBlocks = new ArrayList<>();
    private Project assignedProject;

    /**
     * Creates a new projectActivity. Also adds the project activity to the given
     * project, when constructed
     * 
     * @param startWeek The week to start in, eg week 3
     * @param endWeek   The end week, can be week 3 as well, or week 5
     * @param time      The amount of half hours that the activity is expected to
     *                  take.
     * @param name      The name of the activity
     */
    public ProjectActivity(GregorianCalendar startWeek, GregorianCalendar endWeek, int time, String name,
            Project assignedProject) {
        super(name, startWeek, endWeek);
        this.expectedDuration = time;
        this.assignedProject = assignedProject;
        assignedProject.addActivity(this);
    }

    public void addTimeBlock(TimeBlock timeBlock) {
        timeBlocks.add(timeBlock);
    }

    /**
     *
     * @return an integer of how many half-hours has been spent on this activity
     */
    public int calculateSpentTime() {
        int halfHours = 0;
        for (TimeBlock timeBlock : timeBlocks) {
            halfHours += timeBlock.getDuration();
        }
        return halfHours;
    }

    public void removeTimeBlock(TimeBlock timeBlock) {
        timeBlocks.remove(timeBlock);
    }

    public List<TimeBlock> getTimeBlocks() {
        return timeBlocks;
    }

    /**
     * Deletes all references to assigned employees and the assigned project.
     */
    public void delete() {
        for (int i = assignedEmployees.size()-1; i >= 0; i--) {
            removeEmployee(assignedEmployees.get(i));
        }
        assignedProject.removeActivity(this);
        assignedProject = null;
    }

    public int getExpectedDuration() {
        return expectedDuration;
    }

    public Project getAssignedProject() {
        return assignedProject;
    }

}
