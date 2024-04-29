package org.application.Views;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Models.TimeBlock;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeBlockView extends VBox {
    TimeBlock block;

    public TimeBlockView(TimeBlock block) {
        this.block = block;
        initialize();
    }

    public void initialize() {
        Calendar startTime = block.getStartTime();
        Calendar endTime = block.getEndTime();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        String formattedStartTime = sdf.format(startTime.getTime());
        String formattedEndTime = sdf.format(endTime.getTime());

        Text title = new Text(String.format(block.getActivity() + " from %s to %s", formattedStartTime, formattedEndTime));

        title.setFill(Color.BLACK);
        getChildren().add(title);

        ActivityView view = ActivityView.createActivityView(block.getActivity());

        getChildren().add(view);
    }
}
