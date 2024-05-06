//Jonathan
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
        Label startWeekLabel = new Label();
        if (project.getStartWeek() == null) {
            startWeekLabel.setText("Start Week: not set");
        } else {
            startWeekLabel.setText("Start Week: " + project.getStartWeek().getTime());
        }
        Label endWeekLabel = new Label();
        if (project.getEndWeek() == null) {
            endWeekLabel.setText("End Week: not set");
        } else {
            endWeekLabel.setText("End Week: " + project.getEndWeek().getTime());
        }
        Label leaderLabel = new Label("Project Leader ID: " + project.getProjectLeaderID());

        this.getChildren().addAll(nameLabel, idLabel, startWeekLabel, endWeekLabel, leaderLabel);

        double overallProgress = project.getOverallProgress();

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
