package org.application.Controllers;

import javafx.scene.Parent;
import org.application.Models.Employee;

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

    public Employee getEmployee() {
        return employee;
    }
}
