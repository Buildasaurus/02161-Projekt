package org.application.Views;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import org.application.Controllers.EmployeeController;
import org.application.Models.*;
import org.application.Utils.GeneralMethods;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarView extends GridPane {
    private final Employee employee;
    private EmployeeController controller;
    private ArrayList<Integer> freeHalfHours;
    private GregorianCalendar currentTime;
    private ComboBox<String> startSelect;
    private ComboBox<String> endSelect;
    private ComboBox<String> activityField;
    private ArrayList<TimeBlockView> timeBlocks;

    public CalendarView(Employee employee, EmployeeController controller) {
        this.employee = employee;
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        timeBlocks = new ArrayList<>();

        // create whole 24 hour day
        freeHalfHours = new ArrayList<>();
        for (int i = 0; i < 49; i++) {
            freeHalfHours.add(i);
        }

        ObservableList<String> freeHalfHourStrings = GeneralMethods.halfHourListToStringList(freeHalfHours);
        for (int i = 0; i < 49; i++) {
            Text text = new Text(freeHalfHourStrings.get(i));
            Rectangle rect = new Rectangle(50, 2);
            this.add(text, 0, i * 2);
            this.add(rect, 1, i * 2);
            GridPane.setMargin(text, new Insets(5.0));
            GridPane.setMargin(rect, new Insets(5.0));
        }

        Label text = new Label("Create new timeblock");
        text.setStyle("-fx-font-weight: bold;");
        this.add(text, 0, 97, 2,1);

        // check for any existing timeblocks
        updateTimeBlocks();
        freeHalfHourStrings = GeneralMethods.halfHourListToStringList(freeHalfHours);

        // create default comboboxes with all options
        startSelect = new ComboBox<>(freeHalfHourStrings);
        endSelect = new ComboBox<>(freeHalfHourStrings);

        startSelect.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                GeneralMethods.startSelection(startSelect, endSelect, freeHalfHours);
            }
        });
        endSelect.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                GeneralMethods.endSelection(startSelect, endSelect, freeHalfHours);
            }
        });

        activityField = GeneralViews.chooseProjectActivityComboBox();
        activityField.setPromptText("Choose activity");

        Button submitButton = new Button("Create timeblock");
        submitButton.setOnAction(this.submitTimeBlock());

        HBox selectionBox = new HBox(startSelect, endSelect, activityField, submitButton);
        this.add(selectionBox, 1, 98);

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                clearData();
            }
        });
        this.add(clearButton, 0, 98);
    }

    private void getCurrentTime() {
        currentTime = new GregorianCalendar();
    }

    private void updateTimeBlocks() {
        getCurrentTime();
        for (TimeBlock timeBlock : employee.getTimeBlocks()) {
            if (timeBlock.getStartTime().get(Calendar.DAY_OF_YEAR) == currentTime.get(Calendar.DAY_OF_YEAR)) {
                int startHalfHour = timeBlock.getStartTime().get(Calendar.HOUR_OF_DAY) * 2;
                int endHalfHour = timeBlock.getEndTime().get(Calendar.HOUR_OF_DAY) * 2;
                if (timeBlock.getEndTime().get(Calendar.DAY_OF_YEAR) != currentTime.get(Calendar.DAY_OF_YEAR)) {
                    endHalfHour = 24 * 2;
                }
                startHalfHour += timeBlock.getStartTime().get(Calendar.MINUTE) >= 30 ? 1 : 0;
                endHalfHour += timeBlock.getEndTime().get(Calendar.MINUTE) >= 30 ? 1 : 0;

                // create timeblock UI element
                ArrayList<Integer> tempList = new ArrayList<>();
                tempList.add(startHalfHour);
                tempList.add(endHalfHour);
                ObservableList<String> strings = GeneralMethods.halfHourListToStringList(tempList);
                boolean small = endHalfHour - startHalfHour < 3;
                TimeBlockView TimeBlockUI = new TimeBlockView(timeBlock, controller, small, strings.get(0), strings.get(1), new ArrayList<>(freeHalfHours));
                this.getChildren().add(TimeBlockUI);
                GridPane.setColumnIndex(TimeBlockUI, 1);
                GridPane.setRowIndex(TimeBlockUI, (startHalfHour * 2) + 1);
                GridPane.setRowSpan(TimeBlockUI, (endHalfHour - startHalfHour) * 2 - 1);
                

                // remove overlapping time
                tempList.clear();
                for (int halfHour : freeHalfHours) {
                    if (startHalfHour < halfHour && endHalfHour > halfHour) {
                        tempList.add(halfHour);
                    }
                }
                for (int i = freeHalfHours.size() - 1; i >= 0; i--) {
                    for (int halfHour : tempList) {
                        if (halfHour == freeHalfHours.get(i)) {
                            int val = freeHalfHours.get(i);
                            freeHalfHours.remove(i);
                            for (TimeBlockView block : timeBlocks) {
                                block.removeFromList(val);
                            }
                        }
                    }
                }
                timeBlocks.add(TimeBlockUI);           
            }
        }
    }

    private EventHandler<ActionEvent> submitTimeBlock() {
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (startSelect.getValue() != null && endSelect.getValue() != null) {
                    Activity activity = SystemModel.findActivityWithName(activityField.getSelectionModel().getSelectedItem());
                    if (activity instanceof ProjectActivity) {
                        GregorianCalendar[] calendars = GeneralMethods.stringsToCalendarList(startSelect.getValue(), endSelect.getValue());
                        ProjectActivity projectActivity = (ProjectActivity) activity;
                        employee.createTimeBlock(projectActivity, calendars[0], calendars[1]);
                        updateTimeBlocks();
                        pruneFreeHalfHours();
                        clearData();
                    }
                }
            }
        };
        return event;
    }

    private void clearData() {
        ObservableList<String> strList = GeneralMethods.halfHourListToStringList(freeHalfHours);
        startSelect.setValue(null);
        endSelect.setValue(null);
        startSelect.setItems(strList);
        endSelect.setItems(strList);
    }

    /**
     * Removes "dead space" time slots that cannot generate any timeblock as neither start time nor end time
     */
    private void pruneFreeHalfHours() {
        ArrayList<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < freeHalfHours.size(); i++) {
            if (i  == 0) {
                if (freeHalfHours.get(i) + 1 != freeHalfHours.get(i + 1)) {
                    tempList.add(freeHalfHours.get(i));
                }
            }
            else if (freeHalfHours.get(i) - 1 != freeHalfHours.get(i - 1)) {
                if (i == freeHalfHours.size() - 1) {
                    tempList.add(freeHalfHours.get(i));
                }
                else if (freeHalfHours.get(i) + 1 != freeHalfHours.get(i + 1)) {
                    tempList.add(freeHalfHours.get(i));
                }
            }
        }
        for (int val : tempList) {
            int index = freeHalfHours.indexOf(val);
            freeHalfHours.remove(index);
        }
    }
}
