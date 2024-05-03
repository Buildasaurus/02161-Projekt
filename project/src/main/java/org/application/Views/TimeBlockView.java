package org.application.Views;

import javafx.geometry.Insets;
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
import org.application.Models.TimeBlock;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeBlockView extends StackPane {
    TimeBlock block;

    public TimeBlockView(TimeBlock block, boolean small) {
        this.block = block;
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
            HBox hbox = new HBox(title, time);
            hbox.setSpacing(5.0);
            getChildren().add(hbox);
        } else {
            VBox vbox = new VBox(title, time);
            getChildren().add(vbox);
        }

        this.setPadding(new Insets(5.0));
        Background bg = new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5.0), null));
        this.setBackground(bg);
        Border border = new Border(new BorderStroke(Color.DIMGREY, BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(2.0)));
        this.setBorder(border);
    }
}
