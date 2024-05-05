package org.application.Views;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Controllers.MainController;


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

        //Create Project button
        Button createProject = new Button("Create Project");
        createProject.setOnAction(controller::handleCreateProject);
        getChildren().add(createProject);

        // - create employee
        Button createEmployee = new Button("Create Employee");
        createEmployee.setOnAction(controller::handleCreateEmployee);
        getChildren().add(createEmployee);

        // - delete employee
        Button deleteEmployee = new Button("Delete Employee");
        deleteEmployee.setOnAction(controller::handleDeleteEmployee);
        getChildren().add(deleteEmployee);


    }
}
