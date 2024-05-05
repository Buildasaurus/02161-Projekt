package org.application.Views;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import org.application.Models.ProjectActivity;

public class ActivityProgressView extends HBox {
    private final ProjectActivity activity;

    public ActivityProgressView(ProjectActivity activity) {
        this.activity = activity;
        initView();
    }

    private void initView() {
        Label activityNameLabel = new Label(activity.getName());
        ProgressBar activityProgressBar = new ProgressBar(activity.getProgress());

        this.getChildren().addAll(activityNameLabel, activityProgressBar);
    }
}
