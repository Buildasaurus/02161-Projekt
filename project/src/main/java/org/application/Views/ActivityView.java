package org.application.Views;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Controllers.ActivityController;

import org.application.Models.Activity;

public class ActivityView extends StackPane {
    ActivityController controller;
    Activity activity;

    /**
     * A display for the given view.
     * @param activity The given activity to display
     */
    public ActivityView(Activity activity) {
        this.activity = activity;
        initialize();
    }

    private void initialize() {

        // title
        System.out.println("Assigned activity for activityView: " + activity);
        Text title = new Text(activity.toString());
        title.setFill(Color.BLACK);
        setAlignment(title, Pos.TOP_CENTER);
        getChildren().add(title);

    }
}
