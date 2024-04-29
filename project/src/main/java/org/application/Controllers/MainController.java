package org.application.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import org.application.App;
import org.application.Views.CreateEmployeeView;
import org.application.Views.LoginView;


public class MainController implements IController{
    Parent view;
    public MainController(Parent view) {
        this.view = view;
    }
    public void handleButton(ActionEvent event)
    {
        System.out.println("Button pressed");
        LoginView newView = new LoginView();
        LoginController controller = new LoginController(newView);
        newView.setController(controller);
        App.setRoot(controller);
    }

    public void handleCreateEmployee(ActionEvent event)
    {
        System.out.println("CreateEmployee pressed");
        CreateEmployeeView newView = new CreateEmployeeView();
        CreateEmployeeController controller = new CreateEmployeeController(newView);
        newView.setController(controller);
        App.setRoot(newView);
    }

    public Parent getView()
    {
        return view;
    }
}
