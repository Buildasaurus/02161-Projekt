package org.application.Models;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Employee {
    String ID;
    List<Activity> activities = new ArrayList<>();
    List<TimeBlock> timeBlocks = new ArrayList<>();
    public Employee(String ID) {
        this.ID = ID;
    }

    public void addActivity(Activity activity){
        activities.add(activity);
    }

    public void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public void createTimeBlock(Activity activity, Date startDate, Date endDate){
        TimeBlock timeBlock = new TimeBlock(startDate,endDate,activity,this);
        timeBlocks.add(timeBlock);
    }

    public void deleteTimeBlock(TimeBlock timeBlock){
        timeBlocks.remove(timeBlock);
    }
}
