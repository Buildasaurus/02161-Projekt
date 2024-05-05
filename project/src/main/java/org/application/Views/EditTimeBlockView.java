package org.application.Views;

import java.util.ArrayList;

import org.application.Controllers.EditTimeBlockController;
import org.application.Models.TimeBlock;
import org.application.Utils.GeneralMethods;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class EditTimeBlockView extends StackPane {
    private TimeBlock timeBlock;
    private ArrayList<Integer> freeHalfHours;
    private EditTimeBlockController controller;

    public EditTimeBlockView(TimeBlock timeBlock, ArrayList<Integer> freeHalfHours, String startTime, String endTime) {
        this.timeBlock = timeBlock;
        this.freeHalfHours = freeHalfHours;

        initialize(startTime, endTime);
    }

    public void initialize(String startTime, String endTime) {
        VBox mainBox = new VBox();
        getChildren().add(mainBox);

        mainBox.setSpacing(5.0);

        ComboBox<String> activity = GeneralViews.chooseProjectActivityComboBox();
        mainBox.getChildren().add(activity);

        activity.setValue(timeBlock.getActivity().getName());

        HBox timeBox = new HBox();
        mainBox.getChildren().add(timeBox);

        timeBox.setSpacing(5.0);

        ComboBox<String> startSelect = new ComboBox<>();
        ComboBox<String> endSelect = new ComboBox<>();
        timeBox.getChildren().addAll(startSelect, endSelect);

        // give initial values to make later use of startSelection and endSelection work
        startSelect.setValue(startTime);
        endSelect.setValue(endTime);

        // use startSelection and endSelection to generate options
        GeneralMethods.startSelection(startSelect, endSelect, freeHalfHours);
        GeneralMethods.endSelection(startSelect, endSelect, freeHalfHours);

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

        HBox buttonBox = new HBox();
        mainBox.getChildren().add(buttonBox);

        buttonBox.setSpacing(5.0);

        Button finish = new Button("Apply changes");
        buttonBox.getChildren().add(finish);

        finish.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                controller.handleEditTimeBlock(timeBlock, activity.getValue(), startSelect.getValue(), endSelect.getValue());
            }
        });

        Button delete = new Button("Delete timeblock");
        buttonBox.getChildren().add(delete);

        delete.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                controller.handleDeleteTimeBlock(timeBlock);
            }
        });

        Button back = GeneralViews.backButton();
        buttonBox.getChildren().add(back);
    }

    public void setController(EditTimeBlockController controller) {
        this.controller = controller;
    }
}
