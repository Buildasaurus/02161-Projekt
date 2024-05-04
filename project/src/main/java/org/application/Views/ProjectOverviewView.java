package org.application.Views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import org.application.Controllers.EmployeeController;
import org.application.Models.Project;
import org.application.Models.ProjectActivity;

public class ProjectOverviewView extends VBox {
    private final Project project;
    EmployeeController controller;

    public ProjectOverviewView(EmployeeController controller, Project project) {
        this.project = project;
        this.controller = controller;
        initView();
    }

    private void initView() {
        Label nameLabel = new Label("Project Name: " + project.getName());
        Label idLabel = new Label("Project ID: " + project.getProjectID());
        Label startWeekLabel = new Label("Start Week: " + project.getStartWeek().getTime());
        Label endWeekLabel = new Label("End Week: " + project.getEndWeek().getTime());
        Label leaderLabel = new Label("Project Leader ID: " + project.getProjectLeaderID());

        this.getChildren().addAll(nameLabel, idLabel, startWeekLabel, endWeekLabel, leaderLabel);

        // Calculate total spent time and expected duration for all activities
        int totalSpentTime = 0;
        int totalExpectedDuration = 0;
        for (ProjectActivity activity : project.getActivities()) {
            totalSpentTime += activity.calculateSpentTime();
            totalExpectedDuration += activity.getExpectedDuration();
        }

        // Calculate overall progress
        double overallProgress = (double) totalSpentTime / totalExpectedDuration;

        ProgressBar overallProgressBar = new ProgressBar(overallProgress);
        this.getChildren().add(new Label("Overall Progress"));
        this.getChildren().add(overallProgressBar);

        // Display each activity with its progress
        for (ProjectActivity activity : project.getActivities()) {
            ActivityProgressView activityProgressView = new ActivityProgressView(activity);
            this.getChildren().add(activityProgressView);
        }

        Button OKButton = new Button("OK");
        OKButton.setOnAction(controller::handleOKButton);
        getChildren().add(OKButton);
    }
}
