// 90% Jonathan 10% Marinus

package org.application.Views;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Controllers.EmployeeController;
import org.application.Models.Employee;
import org.application.Models.Project;
import org.application.Models.ProjectActivity;
import org.application.Models.SystemModel;
import org.application.Utils.GeneralMethods;

import java.lang.IllegalArgumentException;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class CreateProjectActivityView extends VBox {
    EmployeeController controller;
    ProjectActivity activity;

    public CreateProjectActivityView(EmployeeController controller) {
        this.controller = controller;
        initialize();
    }

    public CreateProjectActivityView(EmployeeController controller, ProjectActivity activity) {
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


        // Week and Year Picking Section
        Label weekYearLabel = new Label("Week and Year Picking");
        getChildren().add(weekYearLabel);

        HBox weekBox = new HBox(10); // HBox with spacing of 10
        weekBox.getChildren().add(new Label("Start Week:"));
        TextField startWeek = new TextField();
        startWeek.setPromptText("e.g., 5");
        weekBox.getChildren().add(startWeek);

        weekBox.getChildren().add(new Label("End Week:"));
        TextField endWeek = new TextField();
        endWeek.setPromptText("e.g., 10");
        weekBox.getChildren().add(endWeek);
        getChildren().add(weekBox);

        HBox yearBox = new HBox(10); // HBox with spacing of 10
        yearBox.getChildren().add(new Label("Start Year:"));
        TextField startYear = new TextField();
        startYear.setPromptText("e.g., 2024");
        yearBox.getChildren().add(startYear);

        yearBox.getChildren().add(new Label("End Year:"));
        TextField endYear = new TextField();
        endYear.setPromptText("e.g., 2024");
        yearBox.getChildren().add(endYear);
        getChildren().add(yearBox);


        // Employee Availability Section
        getChildren().add(new Label("Sorted list of most available employees"));
        Button updateSearch = new Button("Search");
        getChildren().add(updateSearch);

        ScrollPane pane = new ScrollPane();
        VBox names = new VBox();
        names.getChildren().add(new Text("Please search"));
        updateSearch.setOnAction(e -> {
            if (!(startWeek.getText().isEmpty() || endWeek.getText().isEmpty() || startYear.getText().isEmpty() || endYear.getText().isEmpty()))
                controller.handleUpdateSearch(e, names,
                        GeneralMethods.intToCalendar(Integer.parseInt(startWeek.getText()),
                                Integer.parseInt(startYear.getText())),
                        GeneralMethods.intToCalendar(Integer.parseInt(endWeek.getText()),
                                Integer.parseInt(endYear.getText())));
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
            try {
                if (startWeek.getText() == "") {
                    throw new IllegalArgumentException("Field \"Start Week\" cannot be empty");
                }
                if (endWeek.getText() == "") {
                    throw new IllegalArgumentException("Field \"End Week\" cannot be empty");
                }
                if (startYear.getText() == "") {
                    throw new IllegalArgumentException("Field \"Start Year\" cannot be empty");
                }
                if (endYear.getText() == "") {
                    throw new IllegalArgumentException("Field \"End Year\" cannot be empty");
                }
                if (halfHours.getText() == "") {
                    throw new IllegalArgumentException("Field \"Expected Duration\" cannot be empty");
                }
                controller.handleCompleteProjectActivity(
                        new ProjectActivity(
                                GeneralMethods.intToCalendar(Integer.parseInt(startWeek.getText()), 
                                        Integer.parseInt(startYear.getText())),
                                GeneralMethods.intToCalendar(Integer.parseInt(endWeek.getText()), 
                                        Integer.parseInt(endYear.getText())),
                                Integer.parseInt(halfHours.getText()),
                                name.getText(),
                                SystemModel.getProjectByName(
                                        projectSelectionCombobox.getSelectionModel().getSelectedItem()),
                                assignedEmployees.getText().split(" ")
                        ),
                        activity

                );
            } catch (Exception exception) {
                GeneralAlert alert = new GeneralAlert(exception.getMessage());
            }
        });
        getChildren().add(completeButton);

        if (loadingActivity) {
            name.setText(activity.getName());
            startWeek.setText("" + activity.getStartDate().get(Calendar.WEEK_OF_YEAR));
            endWeek.setText("" + activity.getEndDate().get(Calendar.WEEK_OF_YEAR));
            startYear.setText("" + activity.getStartDate().get(Calendar.YEAR));
            endYear.setText("" + activity.getEndDate().get(Calendar.YEAR));
            assignedEmployees.setText(activity.getAssignedEmployees().stream()
                    .map(Employee::toString)  // assuming Employee has a toString method
                    .collect(Collectors.joining(" ")));
            halfHours.setText("" + activity.getExpectedDuration());
            projectSelectionCombobox.getSelectionModel().select(
                    activity.getAssignedProject().getName());
        }
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            controller.goToEmployeeView();
        });
        getChildren().add(backButton);
    }

    private boolean isValidData(TextField startWeek, TextField endWeek, TextField halfHours, TextField name,
                                ComboBox<String> projectSelectionCombobox, TextField assignedEmployees,
                                TextField startYear, TextField endYear) {
        try {
            boolean value = !(startWeek.getText().isEmpty() || endWeek.getText().isEmpty() || halfHours.getText().isEmpty() ||
                    name.getText().isEmpty() || projectSelectionCombobox.getSelectionModel().getSelectedItem() == null ||
                    assignedEmployees.getText() == null || endYear.getText().isEmpty() || startYear.getText().isEmpty()) &&
                    Integer.parseInt(startWeek.getText()) <= Integer.parseInt(endWeek.getText()) &&
                    Integer.parseInt(startYear.getText()) <= Integer.parseInt(endYear.getText()) &&
                    Integer.parseInt(halfHours.getText()) >= 0;
            return  value;
        }
        catch (Exception e)
        {
            return false;
        }

    }
}
