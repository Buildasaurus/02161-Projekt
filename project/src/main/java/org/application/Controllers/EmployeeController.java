//90 % Jonathan 10% Marinus
package org.application.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.application.App;
import org.application.Models.*;
import org.application.Views.*;

import java.util.ArrayList;
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
        CreateProjectActivityView view = new CreateProjectActivityView(this);
        this.view = view;
        App.setRoot(this);
    }

    public void handleOnCreateReservedActivity(ActionEvent event) {
        System.out.println("Handling event");
        CreateReservedActivityView view = new CreateReservedActivityView(this);
        this.view = view;
        App.setRoot(this);
    }

    public void handleCompleteProjectActivity(Activity activity, Activity oldActivity) {
        System.out.println("Handling complete activity. Activity made: " + activity);

        if (oldActivity != null) {
            oldActivity.updateValues(activity);
            activity.delete(); // It was simply used for reference
        }

        goToEmployeeView();
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
            if (employee.getAvailabilityScore(start, end) <= 0) {
                text.setFill(Color.RED);
            }
            searchBox.getChildren().add(text);
        }
    }

    public void handleSeeOverview(Project project) {
        if (project == null) {
            GeneralAlert alert = new GeneralAlert("Please select a project first");
            return;
        }
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

    public void handleEditProject(Project project) {
        if(project != null)
        {
            CreateProjectView view = new CreateProjectView(project);
            CreateProjectController controller = new CreateProjectController(view);
            view.setController(controller);
            App.setRoot(controller);
        } else {
            GeneralAlert alert = new GeneralAlert("Please select a project first");
        }
    }

    public void handleCreateProjet() {
        CreateProjectView view = new CreateProjectView();
        CreateProjectController controller = new CreateProjectController(view);
        view.setController(controller);
        App.setRoot(controller);
    }

    public void handleEditActivityOverview(ActionEvent event, Activity activity) {
        if (activity instanceof ProjectActivity) {
            this.view = new CreateProjectActivityView(this, (ProjectActivity) activity);
        }
        else if (activity instanceof ReservedActivity) {
            this.view = new CreateReservedActivityView(this, (ReservedActivity) activity);
        } else if (activity == null) {
            GeneralAlert alert = new GeneralAlert("Please select an activity first");
        }
        App.setRoot(this);
    }

    public void goToEmployeeView() {
        EmployeeView eView = new EmployeeView();
        view = eView;
        eView.setController(this);
        App.setRoot(this);
    }

    public void handleEditTimeBlock(ActionEvent event, TimeBlock timeBlock, ArrayList<Integer> freeHalfHours, String startString, String endString) {
        EditTimeBlockView view = new EditTimeBlockView(timeBlock, freeHalfHours, startString, endString);
        EditTimeBlockController controller = new EditTimeBlockController(view, employee);
        view.setController(controller);
        App.setRoot(controller);
    }
}
