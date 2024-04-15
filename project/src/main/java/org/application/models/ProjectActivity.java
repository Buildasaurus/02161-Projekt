package org.application.Models;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectActivity {
    Date startTime;
    Date endTime;
    Date expectedDuration;
    List<TimeBlock> timeBlocks = new ArrayList();

    public ProjectActivity(Date startTime, Date endTime, Date expectedDuration) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.expectedDuration = expectedDuration;
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
}
