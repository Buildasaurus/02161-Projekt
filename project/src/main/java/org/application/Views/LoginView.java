package org.application.Views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Controllers.LoginController;
import org.application.Models.Buttons;

public class LoginView extends VBox {
    LoginController controller;
    TextField userName;

    public LoginView() {
    }

    public void setController(LoginController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {

        // title
        Text title = new Text("!Username!");
        title.setFill(Color.BLACK);
        getChildren().add(title);

        // TITLE2
        Text atitle = new Text("Your username");
        atitle.setFill(Color.GRAY);
        getChildren().add(atitle);

        //Username input box
        userName = new TextField();
        userName.setPromptText("Username");
        getChildren().add(userName);

        //Login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> controller.handleLogin(e, getUsername()));
        getChildren().add(loginButton);

        //
        getChildren().add(Buttons.returnButton());

    }

    public String getUsername() {
        return userName.getText();
    }
}
