package org.application.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.application.Models.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarView extends GridPane {
    private final Employee employee;
    private ArrayList<Integer> freeHalfHours;
    private GregorianCalendar currentTime;
    private ComboBox<String> startSelect;
    private ComboBox<String> endSelect;
    private TextField activityField;

    public CalendarView(Employee employee) {
        this.employee = employee;
        initialize();
    }

    private void initialize() {

        // create whole 24 hour day
        freeHalfHours = new ArrayList<>();
        for (int i = 0; i < 49; i++) {
            freeHalfHours.add(i);
        }

        ObservableList<String> freeHalfHourStrings = toStringList(freeHalfHours);
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

        // check for any existing timeblocks or reserved activities
        updateFreeHalfHours();
        updateTimeBlocks();
        freeHalfHourStrings = toStringList(freeHalfHours);

        // create default comboboxes with all options
        startSelect = new ComboBox<>(freeHalfHourStrings);
        endSelect = new ComboBox<>(freeHalfHourStrings);

        startSelect.setOnAction(this.startSelection());
        endSelect.setOnAction(this.endSelection());

        activityField = new TextField();
        activityField.setPromptText("Please enter an existing activity");

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
        for (TimeBlock timeBlock : employee.getTimeBlocks()) {
            if (timeBlock.getStartTime().get(Calendar.DAY_OF_YEAR) == currentTime.get(Calendar.DAY_OF_YEAR)) {
                int startHalfHour = timeBlock.getStartTime().get(Calendar.HOUR_OF_DAY) * 2;
                int endHalfHour = timeBlock.getEndTime().get(Calendar.HOUR_OF_DAY) * 2;
                startHalfHour += timeBlock.getStartTime().get(Calendar.MINUTE) >= 30 ? 1 : 0;
                endHalfHour += timeBlock.getEndTime().get(Calendar.MINUTE) >= 30 ? 1 : 0;
                // remove overlapping time
                ArrayList<Integer> tempList = new ArrayList<>();
                for (int halfHour : freeHalfHours) {
                    if (startHalfHour < halfHour && endHalfHour > halfHour) {
                        tempList.add(halfHour);
                    }
                }

                for (int i = freeHalfHours.size() - 1; i >= 0; i--) {
                    for (int halfHour : tempList) {
                        if (halfHour == freeHalfHours.get(i)) {
                            freeHalfHours.remove(i);
                        }
                    }
                }

                // create timeblock UI element
                boolean small = endHalfHour - startHalfHour < 3;
                TimeBlockView TimeBlockUI = new TimeBlockView(timeBlock, small);
                this.getChildren().add(TimeBlockUI);
                GridPane.setColumnIndex(TimeBlockUI, 1);
                GridPane.setRowIndex(TimeBlockUI, (startHalfHour * 2) + 1);
                GridPane.setRowSpan(TimeBlockUI, (endHalfHour - startHalfHour) * 2 - 1);
            }
        }
    }

    private void updateFreeHalfHours() {
        getCurrentTime();
        // see if there is a reserved activity today
        for (Activity activity : employee.getActivities()) {
            if (activity.getStartDate().get(Calendar.DAY_OF_YEAR) <= currentTime.get(Calendar.DAY_OF_YEAR) &&
                    activity.getEndDate().get(Calendar.DAY_OF_YEAR) >= currentTime.get(Calendar.DAY_OF_YEAR)) {
                freeHalfHours.clear();
                return;
            }
        }
    }

    private EventHandler<ActionEvent> startSelection() {
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (startSelect.getValue() != null) {
                    ArrayList<Integer> res = new ArrayList<>();
                    boolean hit = false;
                    for (int i = 0; i < freeHalfHours.size(); i++) {
                        if (hit) {
                            if (freeHalfHours.get(i) - 1 == freeHalfHours.get(i - 1)) {
                                res.add(freeHalfHours.get(i));
                            }
                            else {
                                break;
                            }
                        }
                        if (freeHalfHours.get(i) == fromString(startSelect.getValue())) {
                            hit = true;
                        }
                    }
                    endSelect.setItems(toStringList(res));
                }
            }
        };
        return event;
    }

    private EventHandler<ActionEvent> endSelection() {
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (endSelect.getValue() != null) {
                    ArrayList<Integer> res = new ArrayList<>();
                    boolean hit = false;
                    for (int i = freeHalfHours.size() - 1; i >= 0; i--) {
                        if (hit) {
                            if (freeHalfHours.get(i) + 1 == freeHalfHours.get(i + 1)) {
                                res.add(freeHalfHours.get(i));
                            }
                            else {
                                break;
                            }
                        }
                        if (freeHalfHours.get(i) == fromString(endSelect.getValue())) {
                            hit = true;
                        }
                    }
                    startSelect.setItems(toStringList(res));
                }
            }
        };
        return event;
    }

    private EventHandler<ActionEvent> submitTimeBlock() {
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (startSelect.getValue() != null && endSelect.getValue() != null) {
                    GregorianCalendar[] calendars = toCalendarList(startSelect.getValue(), endSelect.getValue());
                    ProjectActivity activity = (ProjectActivity) SystemModel.getActivity(activityField.getText());
                    if (activity != null) {
                        employee.createTimeBlock(activity, calendars[0], calendars[1]);
                        updateTimeBlocks();
                        clearData();
                    }
                }
            }
        };
        return event;
    }

    private ObservableList<String> toStringList(ArrayList<Integer> halfHourList) {
        ObservableList<String> res = FXCollections.observableArrayList(new ArrayList<>());
        for (Integer halfHour : halfHourList) {
            // format
            int hours = halfHour / 2;
            String timeStr = "";
            if (hours >= 10) {
                timeStr += hours;
            }
            else {
                timeStr += 0;
                timeStr += hours;
            }
            if (halfHour % 2 == 1) {
                timeStr += ":30";
            }
            else {
                timeStr += ":00";
            }
            res.add(timeStr);
        }
        return res;
    }

    private int fromString(String time) {
        int halfHours = 0;
        if (time.startsWith("0")) {
            halfHours += Integer.parseInt(time.substring(1, 2)) * 2;
        }
        else {
            halfHours += Integer.parseInt(time.substring(0, 2)) * 2;
        }
        if (time.charAt(3) == '3') {
            halfHours += 1;
        }
        return halfHours;
    }

    private GregorianCalendar[] toCalendarList(String startTime, String endTime) {
        GregorianCalendar[] calendars = new GregorianCalendar[2];
        int[] startTimes = toHoursAndMinutes(startTime);
        int[] endTimes = toHoursAndMinutes(endTime);
        GregorianCalendar startDate = new GregorianCalendar(currentTime.get(Calendar.YEAR),
                currentTime.get(Calendar.MONTH), currentTime.get(Calendar.DAY_OF_MONTH), startTimes[0], startTimes[1]);
        GregorianCalendar endDate = new GregorianCalendar(currentTime.get(Calendar.YEAR),
                currentTime.get(Calendar.MONTH), currentTime.get(Calendar.DAY_OF_MONTH), endTimes[0], endTimes[1]);
        calendars[0] = startDate;
        calendars[1] = endDate;
        return calendars;
    }

    private int[] toHoursAndMinutes(String time) {
        int[] res = new int[2];
        int hours = 0;
        if (time.startsWith("0")) {
            hours = Integer.parseInt(time.substring(1, 2));
        }
        else {
            hours = Integer.parseInt(time.substring(0, 2));
        }
        res[0] = hours;
        res[1] = Integer.parseInt(time.substring(3, 5));
        return res;
    }

    private void clearData() {
        ObservableList<String> strList = toStringList(freeHalfHours);
        startSelect.setValue(null);
        endSelect.setValue(null);
        startSelect.setItems(strList);
        endSelect.setItems(strList);
        activityField.setText("");
    }
}
