package org.application.Views;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Controllers.ActivityController;
import org.application.Controllers.EmployeeController;
import org.application.Models.Activity;

import java.awt.event.ActionEvent;
import java.util.List;

public class EmployeeView extends StackPane
{
    EmployeeController controller;

    public EmployeeView()
    {}

    public void setController(EmployeeController controller)
    {
        this.controller = controller;
        initialize();
    }

    private void initialize()
    {

        // title
        Text title = new Text("!Your Activities!");
        title.setFill(Color.BLACK);
        setAlignment(title, Pos.TOP_CENTER);
        getChildren().add(title);
        List<Activity> activities = controller.getActivities();
        System.out.println("Activities: " + activities);

        VBox activitiesVBox = new VBox();
        for (Activity activity : activities)
        {
            ActivityView view = new ActivityView(activity);
            activitiesVBox.getChildren().add(view);
        }
        activitiesVBox.setAlignment(Pos.CENTER);

        getChildren().add(activitiesVBox);
    }

    public void handleLogin(ActionEvent event)
    {
        System.out.println("Login successfull");
    }
}
