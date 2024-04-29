package org.application.Models;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ProjectActivity extends Activity{
    private GregorianCalendar startWeek;
    private GregorianCalendar endWeek;
    private Time expectedDuration;
    private List<TimeBlock> timeBlocks = new ArrayList<>();
    private Project assignedProject;

    /**
     * Create a new projectActivity
     * @param startWeek The week to start in, eg week 3
     * @param endWeek The end week, can be week 3 as well, or week 5
     * @param time The amount of half hours that the activity is expected to take.
     * @param name The name of the activity
     */
    public ProjectActivity(GregorianCalendar startWeek, GregorianCalendar endWeek, Time time, String name, Project assignedProject) {
        super(name);
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.expectedDuration = time;
        this.assignedProject = assignedProject;
        assignedProject.addActivity(this);
    }

    public void addTimeBlock(TimeBlock timeBlock){
        timeBlocks.add(timeBlock);
    }

    /**
     *
     * @return an integer of how many half-hours has passed for this activity
     */
    public int calculateCompletionTime(){
        int halfHours = 0;
        for(TimeBlock timeBlock : timeBlocks){
            halfHours += timeBlock.getDuration();
        }
        return halfHours;
    }

    public void removeTimeBlock(TimeBlock timeBlock){
        timeBlocks.remove(timeBlock);
    }

    public List<TimeBlock> getTimeBlocks()
    {
        return timeBlocks;
    }

    public void delete()
    {
        for (Employee employee : assignedEmployees)
        {
            employee.removeActivity(this);
        }
        assignedProject.removeActivity(this);
    }

    public GregorianCalendar getStartWeek() {
        return startWeek;
    }
    
    public GregorianCalendar getEndWeek() {
        return endWeek;
    }

    public Time getExpectedDuration() {
        return expectedDuration;
    }

    public Project getAssignedProject() {
        return assignedProject;
    }
}
