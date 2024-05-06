//Jonathan
package org.application.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import org.application.App;
import org.application.Models.Employee;
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
        if (name.length() <= 4) {
            Employee newEmployee = new Employee(name);
            MainView newView = new MainView();
            MainController controller = new MainController(newView);
            newView.setController(controller);
            App.setRoot(controller);
        } else {
            GeneralAlert alert = new GeneralAlert("ID must be exactly 4 characters long");
        }
    }
}
