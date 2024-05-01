package org.application.Controllers;

import javafx.scene.Parent;
import org.application.App;
import org.application.Models.Project;
import org.application.Models.SystemModel;

import java.util.GregorianCalendar;

public class CreateProjectController implements IController
{
    Parent view;
    public CreateProjectController(Parent view)
    {
        this.view = view;
    }

    @Override
    public Parent getView()
    {
        return view;
    }

    public void handleCreateProject(String name, String projectLeader, GregorianCalendar start, GregorianCalendar end)
    {
        Project project;
        if (projectLeader == null)        {
            project = new Project(name, start, end);
        }
        else {
            project = new Project(name, start, end, projectLeader);
        }
        SystemModel.addProject(project);
        App.goToMainMenu();
    }
}
