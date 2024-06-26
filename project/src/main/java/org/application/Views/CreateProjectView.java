// Jonathan

package org.application.Views;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Controllers.CreateProjectController;
import org.application.Models.Project;
import org.application.Models.SystemModel;
import org.application.Utils.GeneralMethods;

public class CreateProjectView extends VBox {
    CreateProjectController controller;
    Project loadedProject = null;

    public CreateProjectView() {
    }

    public CreateProjectView(Project loadedProject) {
        this.loadedProject = loadedProject;
    }

    public void setController(CreateProjectController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        boolean editingProject = loadedProject != null;

        // Title
        Text title = new Text("You are creating a project");
        title.setFill(Color.BLACK);
        getChildren().add(title);

        //Activity name
        Label nameLabel = new Label("Project Name");
        getChildren().add(nameLabel);
        TextField name = new TextField();
        name.setPromptText("Project Name");
        getChildren().add(name);

        Label leaderLabel = new Label("Project Leader");
        getChildren().add(leaderLabel);
        ComboBox<String> projectLeaderComboBox = GeneralViews.chooseEmployeeIDComboBox();
        getChildren().add(projectLeaderComboBox);

        Label startLabel = new Label("Start Date");
        getChildren().add(startLabel);
        DatePicker startDate = new DatePicker();
        startDate.setEditable(false);
        startDate.setPromptText("Start Date");
        getChildren().add(startDate);

        Label endLabel = new Label("End Date");
        getChildren().add(endLabel);
        DatePicker endDate = new DatePicker();
        endDate.setEditable(false);
        endDate.setPromptText("End Date");
        getChildren().add(endDate);

        // Create button
        Button completeButton = new Button("Complete");
        completeButton.setOnAction(e -> {
            try {
                // only create project, if a project with the same name doesn't exist
                if (SystemModel.getProjectByName(name.getText()) != null && !editingProject) {
                    throw new IllegalArgumentException("Project with this name already exists");
                }
                controller.handleCreateProject(name.getText(),
                        projectLeaderComboBox.getSelectionModel().getSelectedItem(),
                        GeneralMethods.convertDatePickerToCalender(startDate),
                        GeneralMethods.convertDatePickerToCalender(endDate), loadedProject);

            } catch (Exception exception) {
                GeneralAlert.sendWarning(exception.getMessage());
            }
        });
        if (editingProject) {
            name.setText(loadedProject.getName());
            projectLeaderComboBox.setValue(loadedProject.getProjectLeaderID());

            // Set start date to the saved project's start date
            startDate.setValue(GeneralMethods.convertCalendarToLocalDate(loadedProject.getStartWeek()));

            // Set end date to the saved project's end date
            endDate.setValue(GeneralMethods.convertCalendarToLocalDate(loadedProject.getEndWeek()));
        }

        getChildren().add(completeButton);
        getChildren().add(GeneralViews.backButton());
    }
    public boolean correctDates(DatePicker start, DatePicker end)
    {
        return start.getValue() == null || end.getValue() == null || !end.getValue().isBefore(start.getValue());
    }
}
