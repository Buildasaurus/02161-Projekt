//Jonathan
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

public class CreateReservedActivityView extends GridPane {
    EmployeeController controller;
    ReservedActivity editedReservedActivity;

    public CreateReservedActivityView(EmployeeController controller) {
        this.controller = controller;
        initialize();
    }

    public CreateReservedActivityView(EmployeeController controller, ReservedActivity oldReservedActivity) {
        this.controller = controller;
        this.editedReservedActivity = oldReservedActivity;
        initialize();
    }

    public void initialize() {
        setPadding(new Insets(10, 10, 10, 10));
        setVgap(5);
        setHgap(5);

        // Name field
        Label nameLabel = new Label("Name:");
        add(nameLabel, 0, 0);
        TextField nameField = new TextField();
        add(nameField, 1, 0);

        // Start date field
        Label startDateLabel = new Label("Start date:");
        add(startDateLabel, 0, 1);
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setEditable(false);
        add(startDatePicker, 1, 1);

        // End date field
        Label endDateLabel = new Label("End date:");
        add(endDateLabel, 0, 2);
        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setEditable(false);
        add(endDatePicker, 1, 2);

        // OK button
        Button OKButton = new Button("OK");
        OKButton.setOnAction(e -> {
            try {
                controller.handleCompleteProjectActivity(
                        new ReservedActivity(
                                GeneralMethods.convertDatePickerToCalender(startDatePicker),
                                GeneralMethods.convertDatePickerToCalender(endDatePicker),
                                nameField.getText(),
                                controller.getEmployee()
                        ),
                        editedReservedActivity
                );
            } catch (IllegalArgumentException exception) {
                GeneralAlert alert = new GeneralAlert(exception.getMessage());
            }
        });
        add(OKButton, 0, 3);

        if (editedReservedActivity != null) {
            nameField.setText(editedReservedActivity.getName());
            startDatePicker.setValue(GeneralMethods.convertCalendarToLocalDate(editedReservedActivity.getStartDate()));
            endDatePicker.setValue(GeneralMethods.convertCalendarToLocalDate(editedReservedActivity.getEndDate()));
        }

        Button returnButton = GeneralViews.returnButton();
        add(returnButton, 1, 3);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            controller.goToEmployeeView();
        });
        add(backButton, 2, 3);
    }
}
