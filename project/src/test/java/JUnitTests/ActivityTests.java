package JUnitTests;

import org.application.Models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ActivityTests
{
    //reset before every test
    @Before
    public void start() {
        SystemModel.reset();
    }

    @Test
    public void ProjectActivityUpdateValuesTest() {
        Employee employee = new Employee("");
        GregorianCalendar start = new GregorianCalendar(1,1,1);
        GregorianCalendar end = new GregorianCalendar(1,2,1);
        Project project = new Project("Project", start,end);
        ProjectActivity projectActivity = new ProjectActivity(start,end,2,"",project);

        ReservedActivity reservedActivity = new ReservedActivity(start, end,"",employee);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                projectActivity.updateValues(reservedActivity));

        assertEquals("Given activity should be of instance ProjectActivity", exception.getMessage());

        new GregorianCalendar(1,1,1);
        new GregorianCalendar(1,2,1);


    }

    @Test
    public void ReservedActivityUpdateValuesTest() {
        Employee employee = new Employee("");
        GregorianCalendar start = new GregorianCalendar(1,1,1);
        GregorianCalendar end = new GregorianCalendar(1,2,1);
        Project project = new Project("Project", start,end);
        ProjectActivity projectActivity = new ProjectActivity(start,end,2,"",project);

        ReservedActivity reservedActivity = new ReservedActivity(start, end,"",employee);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                reservedActivity.updateValues(projectActivity));

        assertEquals("Given activity should be of instance ReservedActivity", exception.getMessage());

        new GregorianCalendar(1,1,1);
        new GregorianCalendar(1,2,1);
    }

    @Test
    public void ActivityConstructorTest() {
        Employee employee = new Employee("");
        GregorianCalendar start = new GregorianCalendar(1,1,1);
        GregorianCalendar end = new GregorianCalendar(1,2,1);
        Project project = new Project("Project", start,end);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new ProjectActivity(null,end,2,"",project));

        assertEquals("Activity's start date cannot be empty", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () ->
                new ProjectActivity(start,null,2,"",project));

        assertEquals("Activity's end date cannot be empty", exception.getMessage());

        ReservedActivity reservedActivity = new ReservedActivity(start, end,"",employee);

    }

}
