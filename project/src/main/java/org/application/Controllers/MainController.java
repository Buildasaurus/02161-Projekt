package org.application.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;


public class MainController implements IController{
    Parent view;
    public MainController(Parent view) {
        this.view = view;
    }
    public void handleButton(ActionEvent event)
    {
        System.out.println("Button pressed");
    }

    public Parent getView()
    {
        return view;
    }
}
