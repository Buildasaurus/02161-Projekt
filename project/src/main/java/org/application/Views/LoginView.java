package org.application.Views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.application.Controllers.LoginController;
import org.application.Controllers.MainController;

import java.awt.color.ICC_ColorSpace;

public class LoginView extends StackPane {
    LoginController controller;

    public LoginView()
    {
    }

    public void setController(LoginController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize()
    {

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
    }

}
