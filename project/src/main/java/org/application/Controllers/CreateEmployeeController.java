//Jonathan
package org.application.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import org.application.App;
import org.application.Models.Employee;
import org.application.Models.SystemModel;
import org.application.Views.GeneralAlert;
import org.application.Views.MainView;


public class CreateEmployeeController implements IController {
    Parent view;

    public CreateEmployeeController(Parent view) {
        this.view = view;
    }

    @Override
    public Parent getView() {
        return view;
    }

    public void handleCompletePressed(ActionEvent event, String name) {
        try {
            if (name.length() > 4) {
                throw new IllegalArgumentException("ID must be at most 4 characters long");
            }
            if (SystemModel.getEmployee(name) != null) {
                throw new IllegalArgumentException("An employee with this ID already exists");
            }
            new Employee(name);
            MainView newView = new MainView();
            MainController controller = new MainController(newView);
            newView.setController(controller);
            App.setRoot(controller);
        } catch (Exception exception) {
            GeneralAlert.sendWarning(exception.getMessage());
        }
        /*
        if (name.length() <= 4) {
            if (SystemModel.getEmployee(name) != null) {

            }
            new Employee(name);
            MainView newView = new MainView();
            MainController controller = new MainController(newView);
            newView.setController(controller);
            App.setRoot(controller);
        } else {
            GeneralAlert.sendWarning("ID must be at least 4 characters long");
        }
        */
    }
}
