package org.application.Views;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Controllers.CreateProjectController;
import org.application.Utils.GeneralMethods;

public class CreateProjectView extends VBox
{
    CreateProjectController controller;

    public CreateProjectView() {
    }

    public void setController(CreateProjectController controller)
    {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        // Title
        Text title = new Text("Choose name and other relevant data for Project");
        title.setFill(Color.BLACK);
        getChildren().add(title);

        //Activity name
        TextField name = new TextField();
        name.setPromptText("Project Name");
        getChildren().add(name);

        TextField projectLeader = new TextField();
        projectLeader.setPromptText("(optional) Project leaderID");
        getChildren().add(projectLeader);

        DatePicker startDate = new DatePicker();
        startDate.setPromptText("Start Date");
        getChildren().add(startDate);

        DatePicker endDate = new DatePicker();
        endDate.setPromptText("End Date");
        getChildren().add(endDate);

        // Create button
        Button completeButton = new Button("Complete");
        completeButton.setOnAction(e -> controller.handleCreateProject(name.getText(),
                projectLeader.getText(), GeneralMethods.convertDatePickerToCalender(startDate),
                GeneralMethods.convertDatePickerToCalender(endDate)));
        getChildren().add(completeButton);
    }
}
