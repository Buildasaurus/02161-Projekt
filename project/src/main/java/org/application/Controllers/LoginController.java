package org.application.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import org.application.App;
import org.application.Models.MainModel;
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

    public void handleLogin(ActionEvent event)
    {
        System.out.println("Logging in");
        EmployeeView newView = new EmployeeView();
        EmployeeController controller = new EmployeeController(newView, MainModel.getEmployee("404040"));
        newView.setController(controller);

        App.setRoot(controller);
    }
}
