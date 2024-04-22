package org.application.Views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Controllers.ActivityController;
import org.application.Controllers.EmployeeController;
import org.application.Models.Activity;

public class CreateActivityView extends VBox
{
    EmployeeController controller;

    public CreateActivityView(EmployeeController controller)
    {
        this.controller = controller;
        initialize();
    }


    private void initialize()
    {
        // title
        Text title = new Text("Choose name and other relevant data");
        title.setFill(Color.BLACK);
        getChildren().add(title);

        //TODO - Create input fields to enter relevant stuff for new activities.

        //Create button
        Button completeButton = new Button("Complete");
        completeButton.setOnAction(controller::handleCompleteActivity);
        getChildren().add(completeButton);
    }
}
