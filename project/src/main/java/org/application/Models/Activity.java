package org.application.Models;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public abstract class Activity {
    protected String name;
    protected List<Employee> assignedEmployees = new ArrayList<>();
    protected GregorianCalendar startDate;
    protected GregorianCalendar endDate;

    public Activity(String name, GregorianCalendar startDate, GregorianCalendar endDate)
    {
        this.startDate = startDate;
        this.endDate = endDate;
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
        if (assignedEmployees.contains(employeeID)) {
            assignedEmployees.remove(employeeID);
            Employee employee = SystemModel.getEmployee(employeeID);
            employee.removeActivity(this);
        }
    }

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
}
