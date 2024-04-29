package org.application.Models;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ProjectActivity extends Activity{
    GregorianCalendar startWeek;
    GregorianCalendar endWeek;
    Time expectedDuration;
    List<TimeBlock> timeBlocks = new ArrayList<>();
    Project assignedProject;

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
    }

    public void addTimeBlock(TimeBlock timeBlock){
        timeBlocks.add(timeBlock);
    }

    /**
     *
     * @return an integer of how many half-hours has passed for this activity
     */
    public int calculateCompletionTime(){
        //Returns numbers of half hours
        //TODO: Implement this
        return 0;
    }

    public void removeTimeBlock(TimeBlock timeBlock){
        timeBlocks.remove(timeBlock);
    }

    public List<TimeBlock> getTimeBlocks()
    {
        return timeBlocks;
    }
}
