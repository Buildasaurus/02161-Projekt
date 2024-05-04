package org.application.Models;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReservedActivity extends Activity{
    public ReservedActivity(GregorianCalendar startDay, GregorianCalendar endDay, String name, Employee employee) {
        super(name, startDay, endDay);
        employee.addActivity(this);
    }
    public int getStartDay(){
        return getStartDate().get(Calendar.DAY_OF_MONTH);
    }

    public int getEndDay(){
        return getEndDate().get(Calendar.DAY_OF_MONTH);
    }

    public int getEndMonth(){
        return getEndDate().get(Calendar.MONTH);
    }

    public void updateValues(Activity activity)
    {
        if(!(activity instanceof ReservedActivity))
        {
            throw new IllegalArgumentException("Given activity should be of instance ReservedActivity");
        }
        this.name = activity.getName();
        this.startDate = activity.getStartDate();
        this.endDate = activity.getEndDate();
        this.assignedEmployees.clear();
        this.assignedEmployees.addAll(activity.getAssignedEmployees());
    }

    public void delete() {
        for (int i = assignedEmployees.size()-1; i >= 0; i--)
        {
            assignedEmployees.get(i).removeActivity(this);
        }
    }
}
