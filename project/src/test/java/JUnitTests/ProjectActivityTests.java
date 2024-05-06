package JUnitTests;

import org.application.Models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ProjectActivityTests
{
    //reset before every test
    @Before
    public void start() {
        SystemModel.reset();
    }

    @Test
    public void UpdateValuesTest() {
        Employee employee = new Employee("");
        GregorianCalendar start = new GregorianCalendar(1,1,1);
        GregorianCalendar end = new GregorianCalendar(1,2,1);
        Project project = new Project("Project", start,end);
        ProjectActivity projectActivity = new ProjectActivity(start,end,2,"",project);

        ReservedActivity reservedActivity = new ReservedActivity(start, end,"",employee);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                projectActivity.updateValues(reservedActivity));

        assertEquals("Given activity should be of instance ProjectActivity", exception.getMessage());

        GregorianCalendar newStart = new GregorianCalendar(1,1,1);
        GregorianCalendar newEnd = new GregorianCalendar(1,2,1);


    }

}
