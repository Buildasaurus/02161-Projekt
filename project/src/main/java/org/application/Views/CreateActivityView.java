package org.application.Views;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Controllers.EmployeeController;
import org.application.Models.*;
import org.application.Utils.GeneralMethods;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateActivityView extends VBox {
    EmployeeController controller;

    public CreateActivityView(EmployeeController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        // title
        Text title = new Text("Choose name and other relevant data for projectactivity");
        title.setFill(Color.BLACK);
        getChildren().add(title);


        //Activity name
        TextField name = new TextField();
        name.setPromptText("Activity name");
        getChildren().add(name);


        //Assigned employees
        TextField assignedEmployees = new TextField(controller.getEmployee().getID());
        assignedEmployees.setPromptText("Assgined employes, space seperated");
        getChildren().add(assignedEmployees);

        DatePicker startDate = new DatePicker();
        startDate.setPromptText("Start Date");
        getChildren().add(startDate);

        DatePicker endDate = new DatePicker();
        endDate.setPromptText("End Date");
        getChildren().add(endDate);

        TextField halfHours = new TextField();
        halfHours.setPromptText("expected half-hours as int eg. (2)");
        getChildren().add(halfHours);


        // Choose the relevant project
        Project chosenProject = SystemModel.getProjects().get(0);
        //TODO - Create input fields to enter relevant stuff for new activities.


        //Search - sorted list of most available employees
        Text text = new Text("Most available employees in chosen time period");
        text.setFill(Color.BLACK);
        getChildren().add(text);

        Button updateSearch = new Button("Search");
        getChildren().add(updateSearch);

        ScrollPane pane = new ScrollPane();
        VBox names = new VBox();
        names.getChildren().add(new Text("Please search"));
        updateSearch.setOnAction(e -> {
            controller.handleUpdateSearch(e, names,
                    GeneralMethods.convertDatePickerToCalender(startDate),
                    GeneralMethods.convertDatePickerToCalender(endDate));
        });
        pane.setContent(names);
        getChildren().add(pane);

        // Create button
        Button completeButton = new Button("Complete");
        completeButton.setOnAction(e -> controller.handleCompleteActivity(e,
                new ProjectActivity(GeneralMethods.convertDatePickerToCalender(startDate),
                        GeneralMethods.convertDatePickerToCalender(endDate),
                        Integer.parseInt(halfHours.getText()),
                        name.getText(), chosenProject), assignedEmployees.getText().split(" ")));
        getChildren().add(completeButton);
    }
}
