package org.application.Models;
import java.util.ArrayList;
import java.util.List;

public abstract class Activity {
    String name;
    List<String> assignedEmployees = new ArrayList<>();

    public Activity(String name) {
        this.name = name;

    }

    public void assignEmployee(String employeeID) {
        if (!assignedEmployees.contains(employeeID)) {
            assignedEmployees.add(employeeID);
        }
    }

    public void assignEmployee(Employee employee) {
        if (!assignedEmployees.contains(employee.getID())) {
            assignedEmployees.add(employee.getID());
        }
    }

    public void removeEmployee(String employeeID) {
        assignedEmployees.remove(employeeID);
    }

    @Override
    public String toString() {
        return name + " " + assignedEmployees;
    }
}
