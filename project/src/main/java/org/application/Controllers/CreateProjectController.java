//Jonathan
package org.application.Controllers;

import javafx.scene.Parent;
import org.application.App;
import org.application.Models.Project;

import java.util.GregorianCalendar;

public class CreateProjectController implements IController {
    Parent view;

    public CreateProjectController(Parent view) {
        this.view = view;
    }

    @Override
    public Parent getView() {
        return view;
    }

    public void handleCreateProject(String name, String projectLeader, GregorianCalendar start, GregorianCalendar end,
                                    Project loadedProject) {

        if (loadedProject != null) {
            loadedProject.assignProjectLeader(projectLeader);
            loadedProject.setName(name);
            loadedProject.setStartWeek(start);
            loadedProject.setEndWeek(end);
            App.goToLastView();
        }
        else {
            if (projectLeader == null) {
                new Project(name, start, end);
            }
            else {
                new Project(name, start, end, projectLeader);
            }
            App.goToLastView();
        }
    }
}
