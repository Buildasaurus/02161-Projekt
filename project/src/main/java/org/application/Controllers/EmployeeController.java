package org.application.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import org.application.Models.Employee;
import org.application.Views.EmployeeView;

public class EmployeeController implements IController {
    Parent view;
    Employee employee;

    public EmployeeController(Employee employee) {
        EmployeeView eView = new EmployeeView();
        view = eView;
        this.employee = employee;
        eView.setController(this);

    }

    @Override
    public Parent getView() {
        return view;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void handleOnCreateActivity(ActionEvent event) {

        System.out.printf("Handling event");
    }

    public void handleCompleteActivity(ActionEvent event) {

        System.out.printf("Handling complete activity");
    }
}
