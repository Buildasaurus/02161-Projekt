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

        // input text
        // TODO make field for login - username
        Text atitle = new Text("Your username");
        atitle.setFill(Color.GRAY);
        setAlignment(atitle, Pos.CENTER);
        getChildren().add(atitle);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> controller.handleLogin(e, getUsername()));
        VBox vbox = new VBox(0.05);
        vbox.getChildren().addAll(loginButton);
        vbox.setStyle("-fx-alignment: center");
        getChildren().add(vbox);

        //Textbox
        userName = new TextField();
        userName.setPromptText("Username");
        vbox.getChildren().add(userName);
    }

    public String getUsername() {
        return userName.getText();
    }
}
