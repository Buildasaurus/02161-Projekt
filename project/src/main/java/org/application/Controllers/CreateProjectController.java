package org.application.Controllers;

import javafx.scene.Parent;
import org.application.Models.Project;

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
        if (projectLeader == null)        {
            Project project = new Project(name, start, end);
        }
        else {
            Project project = new Project(name, start, end, projectLeader);
        }
    }
}
