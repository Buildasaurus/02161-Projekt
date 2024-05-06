// 90% Jonathan 10% Marinus

package org.application.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
import java.util.ArrayList;
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
        getChildren().add(new Label("Search for available employees in selected time period"));
        Button updateSearch = new Button("Search");
        getChildren().add(updateSearch);

        ScrollPane pane = new ScrollPane();
        VBox names = new VBox();
        names.getChildren().add(new Text("Please search"));
        updateSearch.setOnAction(e -> {
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
                controller.handleUpdateSearch(e, names,
                        GeneralMethods.intToCalendar(Integer.parseInt(startWeek.getText()),
                                Integer.parseInt(startYear.getText())),
                        GeneralMethods.intToCalendar(Integer.parseInt(endWeek.getText()),
                                Integer.parseInt(endYear.getText())));
            } catch (Exception exception) {
                GeneralAlert.sendWarning(exception.getMessage());
            }                
        });
        pane.setContent(names);
        getChildren().add(pane);


        // Assigned Employees
        getChildren().add(new Label("Choose Employee to Assign"));
        ObservableList<String> employees = FXCollections.observableArrayList(SystemModel.getEmployees().stream().map(
                Employee::getID).collect(Collectors.toList()));
        ComboBox<String> employeeSelect = new ComboBox<>(employees);
        getChildren().add(employeeSelect);

        getChildren().add(new Label("Assigned Employees"));
        ScrollPane employeePane = new ScrollPane();
        getChildren().add(employeePane);

        VBox assignedEmployees = new VBox();
        employeePane.setContent(assignedEmployees);

        HBox employeeButtons = new HBox(5.0);
        getChildren().add(employeeButtons);

        Button addEmployee = new Button("Add");
        employeeButtons.getChildren().add(addEmployee);
        addEmployee.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (employeeSelect.getSelectionModel().getSelectedItem() == null) {
                    GeneralAlert.sendWarning("Please select an Employee first");
                    return;
                }
                for (Node node : assignedEmployees.getChildren()) {
                    if (employeeSelect.getSelectionModel().getSelectedItem().equals(((Text) node).getText())) {
                        GeneralAlert.sendWarning("This employee is already assigned");
                        return;
                    }
                }
                Text text = new Text(employeeSelect.getSelectionModel().getSelectedItem());
                assignedEmployees.getChildren().add(text);
            }
        });

        Button removeEmployee = new Button("Remove");
        employeeButtons.getChildren().add(removeEmployee);
        removeEmployee.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (employeeSelect.getSelectionModel().getSelectedItem() == null) {
                    GeneralAlert.sendWarning("Please select an Employee first");
                    return;
                }
                for (Node node : assignedEmployees.getChildren()) {
                    if (employeeSelect.getSelectionModel().getSelectedItem().equals(((Text) node).getText())) {
                        assignedEmployees.getChildren().remove(node);
                        return;
                    }
                }
                GeneralAlert.sendWarning("Cannot remove employee not assigned to activity");
            }
        });

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
                ArrayList<String> employeeList = new ArrayList<>();
                for (Node node : assignedEmployees.getChildren()) {
                    employeeList.add(((Text) node).getText());
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
                                employeeList.toArray(new String[0])
                        ),
                        activity

                );
            } catch (Exception exception) {
                GeneralAlert.sendWarning(exception.getMessage());
            }
        });
        getChildren().add(completeButton);

        if (loadingActivity) {
            name.setText(activity.getName());
            startWeek.setText("" + activity.getStartDate().get(Calendar.WEEK_OF_YEAR));
            endWeek.setText("" + activity.getEndDate().get(Calendar.WEEK_OF_YEAR));
            startYear.setText("" + activity.getStartDate().get(Calendar.YEAR));
            endYear.setText("" + activity.getEndDate().get(Calendar.YEAR));
            for (Employee employee : activity.getAssignedEmployees()) {
                Text text = new Text(employee.getID());
                assignedEmployees.getChildren().add(text);
            }
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
}
