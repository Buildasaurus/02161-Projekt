package org.application.Controllers;

import javafx.scene.Parent;
import org.application.Models.Activity;
import org.application.Models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController implements IController {
    Parent view;
    Employee employee;
    public EmployeeController(Parent view, Employee employee) {
        this.view = view;
        this.employee = employee;
    }

    @Override
    public Parent getView() {
        return view;
    }

    public List<Activity> getActivities() {
        return employee.getActivities();
    }
}
