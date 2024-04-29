package org.application.Views;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.application.Models.Employee;
import org.application.Models.ProjectActivity;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ProjectActivityView extends ActivityView {
    private ProjectActivity activity;

    public ProjectActivityView(ProjectActivity activity) {
        this.activity = activity;
        defaultInitialize();
        initialize();
    }

    private void initialize() {
        VBox mainBox = new VBox();
        mainBox.setSpacing(VBoxSpacing);
        getChildren().add(mainBox);

        String activityName = activity.getName();
        Text nameText = new Text(activityName);
        nameText.setFont(titleFont);
        mainBox.getChildren().add(nameText);

        GregorianCalendar cal = activity.getStartDate();
        String startTime = Integer.toString(cal.get(Calendar.WEEK_OF_YEAR));
        cal = activity.getEndDate();
        String endTime = Integer.toString(cal.get(Calendar.WEEK_OF_YEAR));

        Text startText = new Text("Start week:");
        Text startTimeText = new Text(startTime);
        Text endText = new Text("End week:");
        Text endTimeText = new Text(endTime);
        HBox timeBox = new HBox();
        timeBox.setSpacing(HBoxSpacing);
        timeBox.getChildren().addAll(startText, startTimeText, endText, endTimeText);
        mainBox.getChildren().add(timeBox);

        float duration = (float) activity.getExpectedDuration() / 2;
        String durationStr = Float.toString(duration);
        String projectName = activity.getAssignedProject().getName();

        Text durationText = new Text("Expected duration:");
        Text durationTimeText = new Text(durationStr);
        Text projectText = new Text("Project:");
        Text projectNameText = new Text(projectName);
        HBox infoBox = new HBox();
        infoBox.setSpacing(HBoxSpacing);
        infoBox.getChildren().addAll(durationText, durationTimeText, projectText, projectNameText);
        mainBox.getChildren().add(infoBox);

        HBox employeeBox = new HBox();
        employeeBox.setSpacing(HBoxSpacing);
        Text employeeText = new Text("Assigned employees:");
        employeeBox.getChildren().add(employeeText);
        Employee[] employees = new Employee[activity.getAssignedEmployees().size()];
        activity.getAssignedEmployees().toArray(employees);
        for (int i = 0; i < employees.length; i++) {
            if (i == 3) {
                Text dots = new Text("...");
                employeeBox.getChildren().add(dots);
                break;
            }
            Employee employee = employees[i];
            String name = employee.getID();
            Text employeeNameText = new Text(name);
            employeeBox.getChildren().add(employeeNameText);
        }
        mainBox.getChildren().add(employeeBox);
    }
}
