package org.application.Models;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ProjectActivity extends Activity{
    GregorianCalendar startWeek;
    GregorianCalendar endWeek;
    Time expectedDuration;
    List<TimeBlock> timeBlocks = new ArrayList<>();

    public ProjectActivity(GregorianCalendar startWeek, GregorianCalendar endWeek, Time activityDuration, String name) {
        super(name);
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.expectedDuration = time;
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
