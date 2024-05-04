package org.application.Views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.application.Controllers.EmployeeController;
import org.application.Models.ReservedActivity;
import org.application.Utils.GeneralMethods;

public class CreateReservedActivityView extends GridPane
{
    EmployeeController controller;
    public CreateReservedActivityView(EmployeeController controller)
    {
        this.controller = controller;
        initialize();
    }

    public void initialize()
    {
        setPadding(new Insets(10, 10, 10, 10));
        setVgap(5);
        setHgap(5);

        // Name field
        Label nameLabel = new Label("Navn:");
        add(nameLabel, 0, 0);
        TextField nameField = new TextField();
        add(nameField, 1, 0);

        // Start date field
        Label startDateLabel = new Label("Start-dato:");
        add(startDateLabel, 0, 1);
        DatePicker startDatePicker = new DatePicker();
        add(startDatePicker, 1, 1);

        // End date field
        Label endDateLabel = new Label("Slut-dato:");
        add(endDateLabel, 0, 2);
        DatePicker endDatePicker = new DatePicker();
        add(endDatePicker, 1, 2);

        // OK button
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            controller.handleCompleteReservedActivity(
                    new ReservedActivity(
                            GeneralMethods.convertDatePickerToCalender(startDatePicker),
                            GeneralMethods.convertDatePickerToCalender(endDatePicker),
                            nameField.getText(),
                            controller.getEmployee()
                    )
            );
        });
        add(okButton, 0, 3);

        Button nejButton = Buttons.returnButton();
        add(nejButton, 1, 3);
    }
}
