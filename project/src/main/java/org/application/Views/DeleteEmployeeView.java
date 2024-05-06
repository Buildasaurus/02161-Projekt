//Jonathan
package org.application.Views;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.Controllers.DeleteEmployeeController;
import org.application.Models.SystemModel;

public class DeleteEmployeeView extends VBox {
    DeleteEmployeeController controller;
    public void setController(DeleteEmployeeController controller)
    {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        // title
        Text title = new Text("Choose employee to delete");
        title.setFill(Color.BLACK);
        getChildren().add(title);

        ComboBox<String> employees = GeneralViews.chooseEmployeeIDComboBox();
        getChildren().add(employees);

        // Create button
        Button deleteButton = new Button("Complete");
        deleteButton.setOnAction(e ->
            controller.handleDeletePressed(SystemModel.getEmployee(employees.getSelectionModel().getSelectedItem())));

        getChildren().add(deleteButton);

        getChildren().add(GeneralViews.returnButton());
    }
}
