package org.application.Views;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.App;
import org.application.Controllers.EmployeeController;
import org.application.Controllers.LoginController;
import org.application.Controllers.MainController;
import org.application.Models.Project;
import org.application.Models.ProjectActivity;
import org.application.Models.SystemModel;
import org.application.Models.Time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateEmployeeView extends VBox {

    public CreateEmployeeView() {
        initialize();
    }

    private void initialize() {
        // title
        Text title = new Text("Choose name and other relevant data for employee");
        title.setFill(Color.BLACK);
        getChildren().add(title);

        TextField name = new TextField();
        name.setPromptText("ID");
        getChildren().add(name);


        // Create button
        Button completeButton = new Button("Complete");
            completeButton.setOnAction(e -> {MainView newView = new MainView();
            MainController controller = new M   ainController(newView);
            newView.setController(controller);
            App.setRoot(controller);});

        getChildren().add(completeButton);
    }
}
