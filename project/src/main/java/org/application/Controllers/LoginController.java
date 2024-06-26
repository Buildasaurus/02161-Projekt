//Jonathan
package org.application.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import org.application.App;
import org.application.Models.SystemModel;
import org.application.Views.GeneralAlert;


public class LoginController implements IController {
    Parent view;

    public LoginController(Parent view) {
        this.view = view;
    }

    @Override
    public Parent getView() {
        return view;
    }

    public void handleLogin(ActionEvent event, String userName) {
        System.out.println(userName);
        if (SystemModel.getEmployee(userName) == null) {
            GeneralAlert.sendWarning("User with username: \"" + userName + "\" does not exist");
            return;
        }

        System.out.println("Logging in");
        EmployeeController controller = new EmployeeController(SystemModel.getEmployee(userName));

        App.setRoot(controller);
    }
}
