package org.application.Views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Controllers.LoginController;

public class LoginView extends StackPane {
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
        setAlignment(title, Pos.TOP_CENTER);
        getChildren().add(title);

        VBox centerContainer = new VBox();
        centerContainer.setAlignment(Pos.CENTER);
        // TITLE2
        Text atitle = new Text("Your username");
        atitle.setFill(Color.GRAY);
        centerContainer.getChildren().add(atitle);

        //Username input boxx
        userName = new TextField();
        userName.setPromptText("Username");
        centerContainer.getChildren().add(userName);

        //Login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> controller.handleLogin(e, getUsername()));
        centerContainer.getChildren().add(loginButton);


        //Add center container to view
        getChildren().add(centerContainer);


    }

    public String getUsername() {
        return userName.getText();
    }
}
