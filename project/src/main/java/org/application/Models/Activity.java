package org.application.Models;
import java.util.List;

public abstract class Activity {
    String name;
    List<String> assignedEmployees;

    public Activity(String name) {
        this.name = name;
    }

    public void assignEmployee(String employeeID) {
        if (!assignedEmployees.contains(employeeID)) {
            assignedEmployees.add(employeeID);
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
