package org.application.Views;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Controllers.EmployeeController;
import org.application.Models.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateActivityView extends VBox
{
    EmployeeController controller;

    public CreateActivityView(EmployeeController controller)
    {
        this.controller = controller;
        initialize();
    }


    private void initialize()
    {
        // title
        Text title = new Text("Choose name and other relevant data for projectactivity");
        title.setFill(Color.BLACK);
        getChildren().add(title);

        TextField name = new TextField();
        name.setPromptText("Name");
        getChildren().add(name);

        DatePicker startDate = new DatePicker();
        startDate.setPromptText("Start Date");
        getChildren().add(startDate);

        DatePicker endDate = new DatePicker();
        endDate.setPromptText("End Date");
        getChildren().add(endDate);

        TextField halfHours = new TextField("expected half-hours");
        getChildren().add(halfHours);

        // Choose the relevant project
        Project chosenProject = SystemModel.projects.get(0);
        //TODO - Create input fields to enter relevant stuff for new activities.

        //Create button
        Button completeButton = new Button("Complete");
        completeButton.setOnAction(e -> controller.handleCompleteActivity(e,
                new ProjectActivity(convertDatePickerToCalender(startDate),
                        convertDatePickerToCalender(endDate),
                        new Time(Integer.parseInt(halfHours.getText())),
                        name.getText(), chosenProject)));
        getChildren().add(completeButton);
    }

    public GregorianCalendar convertDatePickerToCalender(DatePicker date)
    {
        // Assume datePicker is your DatePicker object
        LocalDate localDate = date.getValue();

        // Convert LocalDate to Instant
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay(defaultZoneId).toInstant();

        // Convert Instant to java.util.Date
        Date instantdate = Date.from(instant);

        // Convert java.util.Date to java.util.Calendar
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(instantdate);
        return calendar;

    }
}
