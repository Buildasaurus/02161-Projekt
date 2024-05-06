package JUnitTests;

import org.application.Models.Employee;
import org.application.Models.Project;
import org.application.Models.ProjectActivity;
import org.application.Models.SystemModel;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class ConstructorTests
{
    //reset before every test
    @Before
    public void start() {
        SystemModel.reset();
    }

    @Test
    public void ProjectActivityConstructor() {
        //Start week before endweek
        GregorianCalendar start = new GregorianCalendar(1,1,1);
        GregorianCalendar end = new GregorianCalendar(1,2,1);
        Project project = new Project("My Project", start, end);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new ProjectActivity(end,start,10,"Test",project));

        assertEquals("Start date must be before end date", exception.getMessage());


        exception = assertThrows(IllegalArgumentException.class, () ->
                new ProjectActivity(end,start,10,"Test",null));

        assertEquals("Project activity must have an assigned project", exception.getMessage());

    }
}
