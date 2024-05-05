package org.application.Views;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Controllers.CreateEmployeeController;


public class CreateEmployeeView extends VBox {

    CreateEmployeeController controller;

    public CreateEmployeeView() {
        initialize();
    }

    public void setController(CreateEmployeeController controller) {
        this.controller = controller;
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
        completeButton.setOnAction(e -> {
            controller.handleCompletePressed(e, name.getText());
        });

        getChildren().add(completeButton);

        getChildren().add(GeneralViews.returnButton());
        getChildren().add(GeneralViews.backButton());
    }
}
