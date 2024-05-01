package org.application.Views;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Controllers.CreateProjectController;
import org.application.Controllers.EmployeeController;
import org.application.Models.Project;
import org.application.Models.ProjectActivity;
import org.application.Models.SystemModel;


public class CreateProjectView extends VBox
{
    CreateProjectController controller;

    public CreateProjectView(CreateProjectController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        // Title
        Text title = new Text("Choose name and other relevant data for Project");
        title.setFill(Color.BLACK);
        getChildren().add(title);

        //Activity name
        TextField name = new TextField();
        name.setPromptText("Project Name");
        getChildren().add(name);

    }
}
