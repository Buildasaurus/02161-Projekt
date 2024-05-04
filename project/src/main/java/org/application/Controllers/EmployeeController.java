package org.application.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.App;
import org.application.Models.Activity;
import org.application.Models.Employee;
import org.application.Models.Project;
import org.application.Models.SystemModel;
import org.application.Views.*;

import java.util.GregorianCalendar;
import java.util.List;

public class EmployeeController implements IController {
    Parent view;
    Employee employee;

    public EmployeeController(Employee employee) {
        EmployeeView eView = new EmployeeView();
        view = eView;
        this.employee = employee;
        eView.setController(this);
    }



    @Override
    public Parent getView() {
        return view;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void handleOnCreateActivity(ActionEvent event) {
        System.out.println("Handling event");
        CreateActivityView view = new CreateActivityView(this);
        this.view = view;
        App.setRoot(this);
    }

    public void handleOnCreateReservedActivity(ActionEvent event) {
        System.out.println("Handling event");
        CreateReservedActivityView view = new CreateReservedActivityView(this);
        this.view = view;
        App.setRoot(this);
    }

    public void handleCompleteActivity(ActionEvent event, Activity activity, String[] assignedEmployeeIDs) {
        System.out.println("Handling complete activity. Activity made: " + activity);

        for(String employeeID : assignedEmployeeIDs) {
            Employee emp = SystemModel.getEmployee(employeeID);
            if (emp != null) {
                emp.addActivity(activity);
            }
        }
        EmployeeView eView = new EmployeeView();
        view = eView;
        eView.setController(this);
        App.setRoot(this);
    }

    public void handleCompleteReservedActivity(Activity activity) {
        employee.addActivity(activity);
        EmployeeView eView = new EmployeeView();
        view = eView;
        eView.setController(this);
        App.setRoot(this);
    }

    public void handleUpdateSearch(ActionEvent event, VBox searchBox, GregorianCalendar start, GregorianCalendar end) {
        searchBox.getChildren().clear();
        if (start == null || end == null) {
            searchBox.getChildren().add(new Text("start or end date missing"));
            return;
        }
        List<Employee> sortedEmployees = SystemModel.findAvailableEmployees(start, end);
        for (Employee employee : sortedEmployees) {
            Text text = new Text(employee.getID());
            if(employee.getAvailabilityScore(start,end) <= 0)
            {
                text.setFill(Color.RED);
            }
            searchBox.getChildren().add(text);
        }
    }

    public void handleSeeOverview(Project project)
    {
        ProjectOverviewView view = new ProjectOverviewView(this, project);
        this.view = view;
        App.setRoot(this);
    }

    public void handleOKButton(ActionEvent event) {
        EmployeeView eView = new EmployeeView();
        view = eView;
        eView.setController(this);
        App.setRoot(this);
    }

    public void handleEditProject(Project project)
    {
        CreateProjectView view = new CreateProjectView();
        CreateProjectController controller = new CreateProjectController(view);
        view.setController(controller);
        App.setRoot(controller);
    }

    public void handleEditActivityOverview(ActionEvent event, Activity activity)
    {
        CreateActivityView view = new CreateActivityView(this, activity);
        this.view = view;
        App.setRoot(this);
    }


}
