// written by Marinus

package org.application.Controllers;

import java.util.GregorianCalendar;

import org.application.App;
import org.application.Models.Activity;
import org.application.Models.Employee;
import org.application.Models.ProjectActivity;
import org.application.Models.SystemModel;
import org.application.Models.TimeBlock;
import org.application.Utils.GeneralMethods;
import org.application.Views.EditTimeBlockView;

import javafx.scene.Parent;

public class EditTimeBlockController implements IController {
    EditTimeBlockView view;
    Employee employee;

    public EditTimeBlockController(EditTimeBlockView view, Employee employee) {
        this.view = view;
        this.employee = employee;
    }

    public Parent getView() {
        return view;
    }

    public void handleEditTimeBlock(TimeBlock timeBlock, String activityName, String startString, String endString) {
        Activity activity = SystemModel.findActivityWithName(activityName);
        if (activity instanceof ProjectActivity) {
            ProjectActivity projectActivity = (ProjectActivity) activity;
            GregorianCalendar[] calendars = GeneralMethods.stringsToCalendarList(startString, endString);

            employee.deleteTimeBlock(timeBlock);
            employee.createTimeBlock(projectActivity, calendars[0], calendars[1]);
        }

        App.goToLastView();
    }

    public void handleDeleteTimeBlock(TimeBlock timeBlock) {
        employee.deleteTimeBlock(timeBlock);

        App.goToLastView();
    }
}
