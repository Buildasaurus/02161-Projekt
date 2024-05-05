package org.application.Views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.application.Controllers.EmployeeController;
import org.application.Models.*;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * A view to see and interact with data tied to a single employee
 */
public class EmployeeView extends ScrollPane implements IRefreshable {
    EmployeeController controller;

    public EmployeeView() {
    }

    public void setController(EmployeeController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(15));

        // Activity Controls Section
        Label activityLabel = new Label("Activity Controls");
        activityLabel.setStyle("-fx-font-weight: bold; -fx-underline: true;");
        vbox.getChildren().add(activityLabel);

        HBox activityButtons = new HBox(5);

        Button createActivity = new Button("Create Activity");
        createActivity.setOnAction(controller::handleOnCreateActivity);
        Button createReservedActivity = new Button("Create Holiday Activity");
        createReservedActivity.setOnAction(controller::handleOnCreateReservedActivity);
        activityButtons.getChildren().addAll(createActivity, createReservedActivity);
        vbox.getChildren().add(activityButtons);

        // Activity Selection Section
        Label selectActivityLabel = new Label("Select activity for modification:");
        vbox.getChildren().add(selectActivityLabel);
        ComboBox<String> activityComboBox = new ComboBox<>();
        List<Activity> activities = SystemModel.getActivities();
        for (Activity activity : activities) {
            activityComboBox.getItems().add(activity.getName());
        }
        vbox.getChildren().add(activityComboBox);

        HBox editActivityButtons = new HBox(5);
        Button editActivityButton = new Button("Edit Activity");
        editActivityButton.setOnAction(e -> controller.handleEditActivityOverview(e,
                SystemModel.getActivity(
                        activityComboBox.getSelectionModel().getSelectedItem())));
        Button deleteActivityButton = new Button("Delete Activity");
        deleteActivityButton.setOnAction(e -> {
            if (SystemModel.getActivity(
                    activityComboBox.getSelectionModel().getSelectedItem()) != null) {
                SystemModel.getActivity(
                        activityComboBox.getSelectionModel().getSelectedItem()).delete();
            }
            refreshView();
        });
        editActivityButtons.getChildren().addAll(editActivityButton, deleteActivityButton);
        vbox.getChildren().add(editActivityButtons);

        // Project Controls Section
        Label projectLabel = new Label("Project Controls");
        projectLabel.setStyle("-fx-font-weight: bold; -fx-underline: true;");
        vbox.getChildren().add(projectLabel);

        ComboBox<String> projectComboBox = new ComboBox<>();
        List<Project> projects = SystemModel.getProjects();
        for (Project project : projects) {
            projectComboBox.getItems().add(project.getName());
        }
        vbox.getChildren().add(projectComboBox);

        HBox projectButtons = new HBox(5);
        Button seeOverviewButton = new Button("See Overview");
        seeOverviewButton.setOnAction(e -> controller.handleSeeOverview(
                SystemModel.getProjectByName(
                        projectComboBox.getSelectionModel().getSelectedItem())));
        Button generateReport = new Button("Generate Report");
        generateReport.setOnAction(e -> {
            Project p = SystemModel.getProjectByName(
                    projectComboBox.getSelectionModel().getSelectedItem());
            if (p != null) {
                Report report = new Report(p);
                report.saveToDisk("");
            }
        });
        Button editProjectButton = new Button("Edit Project");
        editProjectButton.setOnAction(e -> controller.handleEditProject(
                SystemModel.getProjectByName(
                        projectComboBox.getSelectionModel().getSelectedItem())));
        Button createProject = new Button("Create Project");
        createProject.setOnAction(e -> controller.handleCreateProjet());
        Button deleteProjectButton = new Button("Delete Project");
        deleteProjectButton.setOnAction(e -> {
            if (SystemModel.getProjectByName(
                    projectComboBox.getSelectionModel().getSelectedItem()) != null) {
                SystemModel.getProjectByName(
                        projectComboBox.getSelectionModel().getSelectedItem()).delete();
            }
            refreshView();
        });
        projectButtons.getChildren().addAll(seeOverviewButton, generateReport, editProjectButton, createProject, deleteProjectButton);
        vbox.getChildren().add(projectButtons);

        // Timeblocks Section
        Text timeblocksTitle = new Text("Calendar");
        timeblocksTitle.setStyle("-fx-font-weight: bold;");
        vbox.getChildren().add(timeblocksTitle);

        // Check if there's a reserved activity for today
        boolean hasReservedActivityToday = false;
        ReservedActivity overlappingReservedActivity = null;
        GregorianCalendar today = new GregorianCalendar();
        for (Activity activity : controller.getEmployee().getActivities()) {
            if (activity instanceof ReservedActivity) {
                GregorianCalendar activityStart = activity.getStartDate();
                GregorianCalendar activityEnd = activity.getEndDate();
                if (!today.before(activityStart) && !today.after(activityEnd)) {
                    hasReservedActivityToday = true;
                    overlappingReservedActivity = (ReservedActivity) activity;
                    break;
                }
            }
        }

        // Only create the calendar view if there's no reserved activity for today
        if (!hasReservedActivityToday) {
            CalendarView calendarView = new CalendarView(controller.getEmployee(), controller);
            vbox.getChildren().add(calendarView);
        }
        else {
            Label reservedActivityLabel = new Label("You cannot work today, because of the activity: " + overlappingReservedActivity.getName());
            vbox.getChildren().add(reservedActivityLabel);
        }

        // Activities Section
        Text activitiesTitle = new Text("All Activities");
        activitiesTitle.setStyle("-fx-font-weight: bold;");
        vbox.getChildren().add(activitiesTitle);
        for (Activity activity : controller.getEmployee().getActivities()) {
            ActivityView activityView = ActivityView.createActivityView(activity);
            VBox.setMargin(activityView, new Insets(5.0));
            vbox.getChildren().add(activityView);
        }

        // Return Button
        vbox.getChildren().add(GeneralViews.returnButton());

        setContent(vbox);
    }

    public void refreshView() {
        setContent(null);
        initialize();
    }
}
