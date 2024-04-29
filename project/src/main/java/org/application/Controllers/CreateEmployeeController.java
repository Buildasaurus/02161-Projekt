package org.application.Controllers;

import javafx.scene.Parent;
import org.application.App;
import org.application.Models.Activity;
import org.application.Models.Employee;
import javafx.event.ActionEvent;
import org.application.Models.SystemModel;
import org.application.Views.MainView;


public class CreateEmployeeController implements IController{
    Parent view;
    public CreateEmployeeController(Parent view) {
        this.view = view;
    }

    @Override
    public Parent getView() {
        return view;
    }

    public void handleCompletePressed(ActionEvent event, String name) {
        Employee newEmployee = new Employee(name);
        SystemModel.addEmployee(newEmployee);
        MainView newView = new MainView();
        MainController controller = new MainController(newView);
        newView.setController(controller);
        App.setRoot(controller);
    }
}
