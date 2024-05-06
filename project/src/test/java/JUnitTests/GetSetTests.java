package JUnitTests;

import org.application.Models.Employee;
import org.application.Models.Project;
import org.application.Models.SystemModel;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GetSetTests
{
    //reset before every test
    @Before
    public void start() {
        SystemModel.reset();
    }

    @Test
    public void ProjectSetters() {
        GregorianCalendar start = new GregorianCalendar(1,1,1);
        GregorianCalendar end = new GregorianCalendar(1,2,1);
        Project project = new Project("My Project", start, end);
        String newName = "New Project";
        project.setName(newName);
        assertEquals(newName, project.getName());
        GregorianCalendar newStart = new GregorianCalendar(1,1,1);
        project.setStartWeek(newStart);
        assertEquals(newStart, project.getStartWeek());
        GregorianCalendar newEnd = new GregorianCalendar(1,2,1);
        project.setEndWeek(newEnd);
        assertEquals(newEnd, project.getEndWeek());
    }

    @Test
    public void SystemModelGetters() {

        assertNull(SystemModel.getProjectByName("doesNotExist"));

        GregorianCalendar start = new GregorianCalendar(1,1,1);
        GregorianCalendar end = new GregorianCalendar(1,2,1);
        Project project = new Project("My Project", start, end);
        assertNull(SystemModel.getProjectByName("doesNotExist"));
    }

    @Test
    public void SystemModelGetEmployee() {
        SystemModel.addEmployee(new Employee("me"));
        SystemModel.addEmployee(new Employee("you"));
        assertNull(SystemModel.getEmployee("doesNotExist"));
    }

    @Test
    public void SystemModelAddProject() {
        GregorianCalendar start = new GregorianCalendar(1,1,1);
        GregorianCalendar end = new GregorianCalendar(1,2,1);
        Project project = new Project("My Project", start, end);
        SystemModel.addProject(project);
        assertEquals(1, SystemModel.getProjects().size());
    }


}
