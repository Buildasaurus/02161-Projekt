package org.application.Views;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Controllers.EmployeeController;
import org.application.Models.*;
import org.application.Utils.GeneralMethods;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class CreateActivityView extends VBox {
    EmployeeController controller;
    Activity activity;

    public CreateActivityView(EmployeeController controller) {
        this.controller = controller;
        initialize();
    }

    public CreateActivityView(EmployeeController controller, Activity activity) {
        this.controller = controller;
        this.activity = activity;
        initialize();
    }

    private void initialize() {
        boolean loadingActivity = activity != null;
        // Title
        Text title = new Text("You are creating a Project Activity");
        title.setFill(Color.BLACK);
        getChildren().add(title);


        // Activity name
        getChildren().add(new Label("Activity name"));
        TextField name = new TextField();
        name.setPromptText("Activity name");
        getChildren().add(name);


        // Week Picking
        getChildren().add(new Label("Week Picking"));
        TextField startWeek = new TextField();
        startWeek.setPromptText("Start week (e.g., 5)");
        getChildren().add(startWeek);

        TextField endWeek = new TextField();
        endWeek.setPromptText("End week (e.g., 10)");
        getChildren().add(endWeek);

        // Employee Availability Section
        getChildren().add(new Label("Sorted list of most available employees"));
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


        // Assigned Employees
        getChildren().add(new Label("Assigned Employees"));
        TextField assignedEmployees = new TextField(controller.getEmployee().getID());
        assignedEmployees.setPromptText("Assigned employees, space separated");
        getChildren().add(assignedEmployees);

        // Expected Duration Section
        getChildren().add(new Label("Expected Duration"));
        TextField halfHours = new TextField();
        halfHours.setPromptText("Expected half-hours as int (e.g., 2)");
        getChildren().add(halfHours);


        // Project Selection Section
        getChildren().add(new Label("Project Selection"));
        ComboBox<String> projectSelectionCombobox = new ComboBox<>();
        List<Project> projects = SystemModel.getProjects();
        for (Project project : projects) {
            projectSelectionCombobox.getItems().add(project.getName());
        }
        getChildren().add(projectSelectionCombobox);



        // Completion Section
        Button completeButton = new Button("Complete");
        completeButton.setOnAction(e ->
        {
            if (startWeek.getText() != null || endWeek.getText() != null || halfHours.getText() != null ||
                    name.getText() != null || projectSelectionCombobox.getSelectionModel().getSelectedItem() != null ||
                    assignedEmployees.getText() != null) {
                controller.handleCompleteActivity(
                        e,
                        new ProjectActivity(
                                GeneralMethods.intToCalendar(Integer.parseInt(startWeek.getText())),
                                GeneralMethods.intToCalendar(Integer.parseInt(endWeek.getText())),
                                Integer.parseInt(halfHours.getText()),
                                name.getText(),
                                SystemModel.getProjectByName(projectSelectionCombobox.getSelectionModel().getSelectedItem())),
                        assignedEmployees.getText().split(" "));
            }
        });
        getChildren().add(completeButton);

        if (loadingActivity) {
            name.setText(activity.getName());
            startWeek.setText("" + activity.getStartDate().get(Calendar.WEEK_OF_YEAR));
            endWeek.setText("" + activity.getEndDate().get(Calendar.WEEK_OF_YEAR));
            assignedEmployees.setText(activity.getAssignedEmployees().stream()
                    .map(Employee::toString)  // assuming Employee has a toString method
                    .collect(Collectors.joining(" ")));
            halfHours.setText("" + ((ProjectActivity)activity).getExpectedDuration());
            projectSelectionCombobox.getSelectionModel().select(
                    ((ProjectActivity) activity).getAssignedProject().getName());
            activity.delete();
        }
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {controller.goToEmployeeView();});
        getChildren().add(backButton);
    }
}
