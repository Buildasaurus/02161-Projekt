package org.application.Controllers;

import javafx.scene.Parent;

import javax.swing.text.View;

public class LoginController implements IController{
    Parent view;
    public LoginController(Parent view) {
        this.view = view;
    }

    @Override
    public Parent getView() {
        return view;
    }
}
