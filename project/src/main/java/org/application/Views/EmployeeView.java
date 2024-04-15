package org.application.Views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Controllers.EmployeeController;
import org.application.Controllers.LoginController;
import org.application.Models.Activity;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class EmployeeView extends StackPane {
    EmployeeController controller;

    public EmployeeView()
    {
    }

    public void setController(EmployeeController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize()
    {

        // title
        Text title = new Text("!Your Activities!");
        title.setFill(Color.BLACK);
        setAlignment(title, Pos.TOP_CENTER);
        getChildren().add(title);
        List<Activity> activities = controller.getActivities();
        System.out.println("Activities: " + activities);
    }
    public void handleLogin(ActionEvent event){
        System.out.println("Login successfull");
    }
}
