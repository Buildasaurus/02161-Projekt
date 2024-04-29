package org.application.Views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.application.Controllers.EmployeeController;
import org.application.Models.Activity;
import org.application.Models.Buttons;
import org.application.Models.TimeBlock;

/**
 * A view to see and interact with data tied to a single employee
 */
public class EmployeeView extends VBox {
    EmployeeController controller;

    //TODO implement project overview for all the projects where the employee is project leader

    public EmployeeView() {
    }

    public void setController(EmployeeController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        // Create activity button
        Button createActivity = new Button("Create Activity");
        createActivity.setOnAction(controller::handleOnCreateActivity);
        getChildren().add(createActivity);

        // Timeblocks
        Text title = new Text("!Your Timeblocks!");
        getChildren().add(title);

        for (TimeBlock block : controller.getEmployee().getTimeBlocks()) {
            TimeBlockView timeBlockView = new TimeBlockView(block);
            getChildren().add(timeBlockView);
        }

        // All (May not be assigned to timeblock)
        title = new Text("!All activites!");
        getChildren().add(title);
        
        for (Activity activity : controller.getEmployee().getActivities()) {
            ActivityView activityView = ActivityView.createActivityView(activity);
            VBox.setMargin(activityView, new Insets(5.0));
            getChildren().add(activityView);
        }
        getChildren().add(Buttons.returnButton());
    }
}
