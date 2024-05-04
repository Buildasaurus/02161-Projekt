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
public class EmployeeView extends ScrollPane implements IRefreshable{
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

        // - delete activity
        Button deleteActivityButton = new Button("Delete activity");
        deleteActivityButton.setOnAction(e ->
                {
                    if(SystemModel.getActivity(
                            activityComboBox.getSelectionModel().getSelectedItem()) != null)
                    {
                        SystemModel.getActivity(
                                activityComboBox.getSelectionModel().getSelectedItem()).delete();

                    }
                    refreshView();
                });
        vbox.getChildren().add(deleteActivityButton);


        // Project Selection Section
        vbox.getChildren().add(new Label("Project controls"));
        ComboBox<String> projectComboBox = new ComboBox<>();
        List<Project> projects = SystemModel.getProjects();
        for (Project project : projects) {
            projectComboBox.getItems().add(project.getName());
        }
        vbox.getChildren().add(projectComboBox);

        // - see overview
        Button seeOverviewButton = new Button("See overview");
        seeOverviewButton.setOnAction(e -> controller.handleSeeOverview(
                SystemModel.getProjectByName(
                        projectComboBox.getSelectionModel().getSelectedItem())));
        vbox.getChildren().add(seeOverviewButton);

        // - generate report
        Button generateReport = new Button("Generate report");
        generateReport.setOnAction(e -> {
            Project p = SystemModel.getProjectByName(
                    projectComboBox.getSelectionModel().getSelectedItem());
            if(p != null)
            {
                Report report = p.createReport();
                report.saveToDisk("");
            }
        });
        vbox.getChildren().add(generateReport);

        // - edit project
        Button editProjectButton = new Button("Edit project");
        editProjectButton.setOnAction(e -> controller.handleEditProject(
                SystemModel.getProjectByName(
                        projectComboBox.getSelectionModel().getSelectedItem())));
        vbox.getChildren().add(editProjectButton);

        // - Delete project
        Button deleteProjectButton = new Button("Delete project");
        deleteProjectButton.setOnAction(e ->
        {
            if(SystemModel.getProjectByName(
                    projectComboBox.getSelectionModel().getSelectedItem()) != null)
            {
                SystemModel.getProjectByName(
                        projectComboBox.getSelectionModel().getSelectedItem()).delete();
            }
            refreshView();
        });
        vbox.getChildren().add(deleteProjectButton);


        // Timeblocks
        Text title = new Text("!Your Timeblocks!");
        vbox.getChildren().add(title);

        CalendarView calendarView = new CalendarView(controller.getEmployee());
        vbox.getChildren().add(calendarView);

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

    public void refreshView()
    {
        setContent(null);
        initialize();
    }
}
