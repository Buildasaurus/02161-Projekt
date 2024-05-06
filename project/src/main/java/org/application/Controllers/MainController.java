//Jonathan
package org.application.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import org.application.App;
import org.application.Views.CreateEmployeeView;
import org.application.Views.CreateProjectView;
import org.application.Views.DeleteEmployeeView;
import org.application.Views.LoginView;


public class MainController implements IController {
    Parent view;

    public MainController(Parent view) {
        this.view = view;
    }

    public void handleButton(ActionEvent event) {
        System.out.println("Button pressed");
        LoginView newView = new LoginView();
        LoginController controller = new LoginController(newView);
        newView.setController(controller);
        App.setRoot(controller);
    }

    public void handleCreateEmployee(ActionEvent event) {
        CreateEmployeeView newView = new CreateEmployeeView();
        CreateEmployeeController controller = new CreateEmployeeController(newView);
        newView.setController(controller);
        App.setRoot(newView);
    }

    public void handleDeleteEmployee(ActionEvent event) {
        DeleteEmployeeView newView = new DeleteEmployeeView();
        DeleteEmployeeController controller = new DeleteEmployeeController(newView);
        newView.setController(controller);
        App.setRoot(newView);
    }

    public void handleCreateProject(ActionEvent event) {
        CreateProjectView view = new CreateProjectView();
        CreateProjectController controller = new CreateProjectController(view);
        view.setController(controller);
        App.setRoot(controller);
    }

    public Parent getView() {
        return view;
    }
}
