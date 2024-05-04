package org.application.Models;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ProjectActivity extends Activity {
    /**
     * Expected duration of the given activity, measured in half-hours
     */
    private int expectedDuration;
    private final List<TimeBlock> timeBlocks = new ArrayList<>();
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
        if (startWeek.after(endWeek)) {
            throw new IllegalArgumentException("start week should be before end week");
        }
        this.expectedDuration = time;
        this.assignedProject = assignedProject;
        assignedProject.addActivity(this);
    }

    public ProjectActivity(GregorianCalendar startWeek, GregorianCalendar endWeek, int time, String name,
                           Project assignedProject, String[] assignedEmployeeIDs) {
        super(name, startWeek, endWeek);
        this.expectedDuration = time;
        this.assignedProject = assignedProject;
        if (startWeek.after(endWeek)) {
            throw new IllegalArgumentException("start week should be before end week");
        }
        for (String employeeID : assignedEmployeeIDs) {
            assignEmployee(employeeID);
        }
        assignedProject.addActivity(this);
    }

    public void addTimeBlock(TimeBlock timeBlock) {
        timeBlocks.add(timeBlock);
    }

    /**
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
        for (int i = assignedEmployees.size() - 1; i >= 0; i--) {
            removeEmployee(assignedEmployees.get(i));
        }
        assignedProject.removeActivity(this);
        assignedProject = null;
    }

    /**
     * Updates all values, and importantly doesn't edit the timeblocks saved in it.
     *
     * @param activity
     */
    public void updateValues(Activity activity) {
        if (!(activity instanceof ProjectActivity)) {
            throw new IllegalArgumentException("Given activity should be of instance ProjectActivity");
        }
        this.name = activity.getName();
        this.startDate = activity.getStartDate();
        this.endDate = activity.getEndDate();
        // Update all connections to match the new employees
        for (int i = assignedEmployees.size() - 1; i >= 0; i--) {
            this.removeEmployee(assignedEmployees.get(i));
        }
        for (int i = activity.getAssignedEmployees().size() - 1; i >= 0; i--) {
            this.assignEmployee(activity.getAssignedEmployees().get(i));
        }

        this.assignedProject = ((ProjectActivity) activity).getAssignedProject();
        this.expectedDuration = ((ProjectActivity) activity).getExpectedDuration();
    }

    public int getExpectedDuration() {
        return expectedDuration;
    }

    public Project getAssignedProject() {
        return assignedProject;
    }

}
