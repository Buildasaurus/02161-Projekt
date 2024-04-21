package org.application.Views;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Models.TimeBlock;

public class TimeBlockView extends VBox
{
    TimeBlock block;

    public TimeBlockView(TimeBlock block)
    {
        this.block = block;
        initialize();
    }

    public void initialize()
    {
        Text title = new Text(
                String.format("Activity from %s to %s",
                block.getStartTime(), block.getEndTime()));
        title.setFill(Color.BLACK);
        getChildren().add(title);

        ActivityView view = new ActivityView(block.getActivity());

        getChildren().add(view);
    }
}
