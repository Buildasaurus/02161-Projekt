// 60% Jonathan 20% maritn 20% marinus

package org.application.Models;


import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public abstract class Activity {
    protected String name;
    protected List<Employee> assignedEmployees = new ArrayList<>();
    protected GregorianCalendar startDate;
    protected GregorianCalendar endDate;

    public Activity(String name, GregorianCalendar startDate, GregorianCalendar endDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("Activity's start date cannot be empty");
        }
        this.startDate = startDate;
        if (endDate == null) {
            throw new IllegalArgumentException("Activity's end date cannot be empty");
        }
        this.endDate = endDate;
        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        this.name = name;
    }

    public void assignEmployee(String employeeID) {
        Employee employee = SystemModel.getEmployee(employeeID);
        if (employee != null) {
            assignEmployee(employee);
        }
    }

    public void assignEmployee(Employee employee) {
        if (!assignedEmployees.contains(employee)) {
            assignedEmployees.add(employee);
            employee.addActivity(this);
        }
    }

    public void removeEmployee(String employeeID) {
        Employee employee = SystemModel.getEmployee(employeeID);
        if (assignedEmployees.contains(employee)) {
            assignedEmployees.remove(employee);
            employee.removeActivity(this);
        }
    }

    public void removeEmployee(Employee employee) {
        if (assignedEmployees.contains(employee)) {
            assignedEmployees.remove(employee);
            employee.removeActivity(this);
        }
    }

    public abstract void updateValues(Activity activity);

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return name + " " + assignedEmployees;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    public abstract void delete();
}
