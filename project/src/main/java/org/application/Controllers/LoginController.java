package org.application.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import org.application.App;
import org.application.Models.SystemModel;
import org.application.Views.EmployeeView;


public class LoginController implements IController {
    Parent view;

    public LoginController(Parent view) {
        this.view = view;
    }

    @Override
    public Parent getView() {
        return view;
    }

    public void handleLogin(ActionEvent event, String userName) {
        System.out.println(userName);
        if (SystemModel.getEmployee(userName) == null)
        {
            System.out.println("User not found");
            return;
        }

        System.out.println("Logging in");
        EmployeeView newView = new EmployeeView();
        EmployeeController controller = new EmployeeController(newView, SystemModel.getEmployee(userName));
        newView.setController(controller);

        App.setRoot(controller);
    }
}
