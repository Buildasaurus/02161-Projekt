// written by Marinus

package org.application.Views;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.application.Models.Employee;
import org.application.Models.ReservedActivity;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


public class ReservedActivityView extends ActivityView {
    private final ReservedActivity activity;

    public ReservedActivityView(ReservedActivity activity) {
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

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        GregorianCalendar cal = activity.getStartDate();
        String startTime = sdf.format(cal.getTime());
        cal = activity.getEndDate();
        String endTime = sdf.format(cal.getTime());

        Text startText = new Text("From:");
        Text startTimeText = new Text(startTime);
        Text endText = new Text("To:");
        Text endTimeText = new Text(endTime);
        HBox timeBox = new HBox();
        timeBox.setSpacing(HBoxSpacing);
        timeBox.getChildren().addAll(startText, startTimeText, endText, endTimeText);
        mainBox.getChildren().add(timeBox);

        Employee[] employees = new Employee[activity.getAssignedEmployees().size()];
        activity.getAssignedEmployees().toArray(employees);
        String name = employees[0].getID();

        Text employeeText = new Text("Assigned employee:");
        Text employeeNameText = new Text(name);
        HBox employeeBox = new HBox();
        employeeBox.setSpacing(5.0);
        employeeBox.getChildren().addAll(employeeText, employeeNameText);
        mainBox.getChildren().add(employeeBox);

    }
}
