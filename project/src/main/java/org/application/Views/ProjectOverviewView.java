package org.application.Views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.application.Controllers.EmployeeController;
import org.application.Models.Buttons;
import org.application.Models.Project;
import org.application.Models.ProjectActivity;
import org.application.Models.SystemModel;
import org.application.Utils.GeneralMethods;

public class ProjectOverviewView extends VBox {
    private Project project;
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

        for (ProjectActivity activity : project.getActivities()) {
            Label activityLabel = new Label("Activity: " + activity.toString()); // Assuming ProjectActivity has a toString method
            this.getChildren().add(activityLabel);
        }

        Button OKButton = new Button("OK");
        OKButton.setOnAction(controller::handleOKButton);
        getChildren().add(OKButton);
    }
}
