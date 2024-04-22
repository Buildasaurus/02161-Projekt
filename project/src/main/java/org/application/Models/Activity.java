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
        Employee employee = SystemModel.getEmployee(employeeID);
        if (employee != null) {
            assignEmployee(employee);
        }
    }

    public void assignEmployee(Employee employee) {
        if (!assignedEmployees.contains(employee.getID())) {
            assignedEmployees.add(employee.getID());
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

    @Override
    public String toString() {
        return name + " " + assignedEmployees;
    }
}
