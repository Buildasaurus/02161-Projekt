package org.application.Views;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.application.App;
import org.application.Models.Employee;
import org.application.Models.ProjectActivity;
import org.application.Models.SystemModel;

import java.util.List;

/**
 * Static class for commonly used buttons, to avoid code redundancy
 */
public class GeneralViews {
    /**
     * A button that returns to the main menu.
     *
     * @return An instance of a button that when clicked returns to the main menu
     */
    public static Button returnButton() {
        Button returnButton = new Button("Main menu");
        returnButton.setOnAction(e -> App.goToMainMenu());
        return returnButton;
    }

    public static Button backButton() {
        Button returnButton = new Button("Back");
        returnButton.setOnAction(e -> App.goToLastView());
        return returnButton;
    }

    public static ComboBox<String> chooseProjectActivityComboBox()
    {
        ComboBox<String> chooseActivityComboBox = new ComboBox<>();
        List<ProjectActivity> projects = SystemModel.getProjectActivities();
        for (ProjectActivity project : projects) {
            chooseActivityComboBox.getItems().add(project.getName());
        }
        return  chooseActivityComboBox;
    }

    public static ComboBox<String> chooseEmployeeIDComboBox()
    {
        ComboBox<String> chooseActivityComboBox = new ComboBox<>();
        List<Employee> projects = SystemModel.getEmployees();
        for (Employee employee : projects) {
            chooseActivityComboBox.getItems().add(employee.getID());
        }
        return  chooseActivityComboBox;
    }
}
