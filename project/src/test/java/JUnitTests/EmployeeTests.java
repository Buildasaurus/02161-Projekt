package JUnitTests;

import org.application.Models.Employee;
import org.application.Models.Project;
import org.application.Models.ProjectActivity;
import org.application.Models.SystemModel;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class EmployeeTests
{
    //reset before every test
    @Before
    public void start() {
        SystemModel.reset();
    }

    @Test
    public void AddActivityBranchesTest() {
        Employee employee = new Employee("");
        assertThrows(AssertionError.class, () -> {
            employee.addActivity(null); // should give assertion error
        });
    }

    @Test
    public void getAvailabilityScoreBranchesTest() {
        Employee employee = new Employee("");
        GregorianCalendar start = new GregorianCalendar(1,1,1);
        GregorianCalendar end = new GregorianCalendar(1,2,1);
        //ProjectActivity a = new ProjectActivity(null,null,2,"",null);
        assertThrows(AssertionError.class, () -> {
            employee.getAvailabilityScore(start,null); // should give assertion error
        });
        assertThrows(AssertionError.class, () -> {
            employee.getAvailabilityScore(null,end); // should give assertion error
        });
        assertThrows(AssertionError.class, () -> {
            employee.getAvailabilityScore(null,null); // should give assertion error
        });
    }

}
