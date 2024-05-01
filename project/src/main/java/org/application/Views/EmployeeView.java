package org.application.Views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.application.Controllers.EmployeeController;
import org.application.Models.Activity;
import org.application.Models.Buttons;
import org.application.Models.TimeBlock;

/**
 * A view to see and interact with data tied to a single employee
 */
public class EmployeeView extends ScrollPane {
    EmployeeController controller;

    //TODO implement project overview for all the projects where the employee is project leader

    public EmployeeView() {
    }

    public void setController(EmployeeController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        VBox vbox = new VBox();
        // Create activity button
        Button createActivity = new Button("Create Activity");
        createActivity.setOnAction(controller::handleOnCreateActivity);
        vbox.getChildren().add(createActivity);

        //Create Project button
        Button createProject = new Button("Create Project");
        createProject.setOnAction(controller::handleCreateProject);
        vbox.getChildren().add(createProject);

        // Timeblocks
        Text title = new Text("!Your Timeblocks!");
        vbox.getChildren().add(title);

        for (TimeBlock block : controller.getEmployee().getTimeBlocks()) {
            TimeBlockView timeBlockView = new TimeBlockView(block);
            vbox.getChildren().add(timeBlockView);
        }

        // All (May not be assigned to timeblock)
        title = new Text("!All activites!");
        vbox.getChildren().add(title);


        for (Activity activity : controller.getEmployee().getActivities()) {
            ActivityView activityView = ActivityView.createActivityView(activity);
            VBox.setMargin(activityView, new Insets(5.0));
            vbox.getChildren().add(activityView); // Add the activityView to the VBox
        }


        vbox.getChildren().add(Buttons.returnButton());

        setContent(vbox);
    }
}
