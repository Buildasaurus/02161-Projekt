package org.application.Views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.application.Controllers.EmployeeController;
import org.application.Models.*;

import java.util.List;

/**
 * A view to see and interact with data tied to a single employee
 */
public class EmployeeView extends ScrollPane {
    EmployeeController controller;

    //TODO implement project overview for all the projects where the employee is project leader

    public EmployeeView() {
    }

    public void setController(EmployeeController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        VBox vbox = new VBox();
        // Create activity button
        Button createActivity = new Button("Create Activity");
        createActivity.setOnAction(controller::handleOnCreateActivity);
        vbox.getChildren().add(createActivity);

        // Create reserved activity button
        Button createReservedActivity = new Button("Create Holiday Activity");
        createReservedActivity.setOnAction(controller::handleOnCreateReservedActivity);
        vbox.getChildren().add(createReservedActivity);


        // Activity Selection Section
        vbox.getChildren().add(new Label("Select Activity for editing"));
        ComboBox<String> activityComboBox = new ComboBox<>();
        List<Activity> activities = SystemModel.getActivities();
        for (Activity activity : activities) {
            activityComboBox.getItems().add(activity.getName());
        }
        vbox.getChildren().add(activityComboBox);

        // - edit activity
        Button editActivityButton = new Button("Edit activity");
        editActivityButton.setOnAction(e -> controller.handleEditActivityOverview(e,
                SystemModel.getActivity(
                        activityComboBox.getSelectionModel().getSelectedItem())));
        vbox.getChildren().add(editActivityButton);

        // Project Selection Section
        vbox.getChildren().add(new Label("Project overview"));
        ComboBox<String> comboBox = new ComboBox<>();
        List<Project> projects = SystemModel.getProjects();
        for (Project project : projects) {
            comboBox.getItems().add(project.getName());
        }
        vbox.getChildren().add(comboBox);

        // - see overview
        Button seeOverviewButton = new Button("See overview");
        seeOverviewButton.setOnAction(e -> controller.handleSeeOverview(
                SystemModel.getProjectByName(
                        comboBox.getSelectionModel().getSelectedItem())));
        vbox.getChildren().add(seeOverviewButton);

        // - generate report
        Button generateReport = new Button("Generate report");
        generateReport.setOnAction(e -> {
            Report report = SystemModel.getProjectByName(
                    comboBox.getSelectionModel().getSelectedItem()).createReport();
            report.saveToDisk("");
        });
        vbox.getChildren().add(generateReport);


        // Timeblocks
        Text title = new Text("!Your Timeblocks!");
        vbox.getChildren().add(title);

        for (TimeBlock block : controller.getEmployee().getTimeBlocks()) {
            TimeBlockView timeBlockView = new TimeBlockView(block);
            vbox.getChildren().add(timeBlockView);
        }

        // All (May not be assigned to timeblock)
        title = new Text("!All activites!");
        vbox.getChildren().add(title);


        for (Activity activity : controller.getEmployee().getActivities()) {
            ActivityView activityView = ActivityView.createActivityView(activity);
            VBox.setMargin(activityView, new Insets(5.0));
            vbox.getChildren().add(activityView); // Add the activityView to the VBox
        }


        vbox.getChildren().add(Buttons.returnButton());

        setContent(vbox);
    }
}
