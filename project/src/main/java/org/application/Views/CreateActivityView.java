package org.application.Views;

import javafx.scene.control.*;
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
import java.util.List;

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

        //Week picking

        TextField startWeek = new TextField();
        startWeek.setPromptText("Start week (eg. 5)");
        getChildren().add(startWeek);

        TextField endWeek = new TextField();
        endWeek.setPromptText("End week (eg. 10)");
        getChildren().add(endWeek);

        // Day variation
        /*
        DatePicker startDate = new DatePicker();
        startDate.setPromptText("Start Date");
        getChildren().add(startDate);

        DatePicker endDate = new DatePicker();
        endDate.setPromptText("End Date");
        getChildren().add(endDate);
        */

        //Expected duration
        TextField halfHours = new TextField();
        halfHours.setPromptText("expected half-hours as int eg. (2)");
        getChildren().add(halfHours);


        // Choose the relevant project
        ComboBox<String> comboBox = new ComboBox<>();

        List<Project> projects = SystemModel.getProjects();
        for (Project project : projects) {
            comboBox.getItems().add(project.getName());
        }
        getChildren().add(comboBox);

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
                    GeneralMethods.intToCalendar(Integer.parseInt(startWeek.getText())),
                    GeneralMethods.intToCalendar(Integer.parseInt(endWeek.getText())));
        });
        pane.setContent(names);
        getChildren().add(pane);

        // Create button
        Button completeButton = new Button("Complete");
        completeButton.setOnAction(e -> controller.handleCompleteActivity(e,
                new ProjectActivity(
                        GeneralMethods.intToCalendar(Integer.parseInt(startWeek.getText())),
                        GeneralMethods.intToCalendar(Integer.parseInt(endWeek.getText())),
                        Integer.parseInt(halfHours.getText()),
                        name.getText(), SystemModel.getProjectByName(comboBox.getSelectionModel().getSelectedItem())), assignedEmployees.getText().split(" ")));
        getChildren().add(completeButton);
    }
}
