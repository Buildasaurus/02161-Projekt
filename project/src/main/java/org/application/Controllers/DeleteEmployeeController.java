package org.application.Controllers;

import javafx.scene.Parent;
import org.application.App;
import org.application.Models.Employee;
import org.application.Models.SystemModel;
import org.application.Views.DeleteEmployeeView;

public class DeleteEmployeeController implements IController {
    private Parent view;
    public DeleteEmployeeController(DeleteEmployeeView view)
    {
        this.view = view;
    }
    public void handleDeletePressed(Employee employee)
    {
        SystemModel.removeEmployee(employee);
        App.goToMainMenu();
    }

    @Override
    public Parent getView() {
        return view;
    }
}
