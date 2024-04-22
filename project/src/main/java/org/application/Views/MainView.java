package org.application.Views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.application.Controllers.MainController;

import java.awt.color.ICC_ColorSpace;

public class MainView extends VBox {
    MainController controller;

    public MainView() {
    }

    public void setController(MainController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {

        // title
        Text title = new Text("!AMaZing ApPliCation!");
        title.setFill(Color.BLACK);
        getChildren().add(title);

        // buttons
        //  - login
        Button playButton = new Button("Login");
        playButton.setOnAction(controller::handleButton);
        getChildren().add(playButton);

        // - create employee
        Button createEmployee = new Button("Create Employee");
        createEmployee.setOnAction(controller::handleCreateEmployee);
        getChildren().add(createEmployee);

    }
}
