package org.application.Views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import org.application.Controllers.EmployeeController;
import org.application.Models.TimeBlock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TimeBlockView extends StackPane {
    private EmployeeController controller;
    private TimeBlock block;
    private ArrayList<Integer> freeHalfHours;
    private String startString;
    private String endString;

    public TimeBlockView(TimeBlock block, EmployeeController controller, boolean small, String startTime, String endTime, ArrayList<Integer> freeHalfHours) {
        this.block = block;
        this.controller = controller;
        this.freeHalfHours = freeHalfHours;
        this.startString = startTime;
        this.endString = endTime;

        initialize(small);
    }

    public void initialize(boolean small) {
        Calendar startTime = block.getStartTime();
        Calendar endTime = block.getEndTime();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        String formattedStartTime = sdf.format(startTime.getTime());
        String formattedEndTime = sdf.format(endTime.getTime());

        Text title = new Text(block.getActivity().getName());
        Text time = new Text(String.format("%s to %s", formattedStartTime, formattedEndTime));

        Font titleFont = Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 11);
        Font timeFont = Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 11);

        title.setFont(titleFont);
        time.setFont(timeFont);

        if (small) {
            HBox leftBox = new HBox(title, time);
            leftBox.setSpacing(5.0);
            getChildren().add(leftBox);
        } else {
            VBox leftBox = new VBox(title, time);
            getChildren().add(leftBox);
        }

        HBox editBox = new HBox();
        editBox.setAlignment(Pos.TOP_RIGHT);
        getChildren().add(editBox);

        Button editButton = new Button("edit");
        editBox.getChildren().add(editButton);
        
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                controller.handleEditTimeBlock(e, block, freeHalfHours, startString, endString);
            }
        });

        this.setPadding(new Insets(5.0));
        Background bg = new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5.0), null));
        this.setBackground(bg);
        Border border = new Border(
                new BorderStroke(Color.DIMGREY, BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(2.0)));
        this.setBorder(border);
    }

    public void removeFromList(int value) {
        int i = freeHalfHours.indexOf(value);
        freeHalfHours.remove(i);
    }
}
