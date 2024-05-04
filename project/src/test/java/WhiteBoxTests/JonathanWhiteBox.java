package WhiteBoxTests;

import org.application.Models.Employee;
import org.application.Models.Project;
import org.application.Models.ProjectActivity;
import org.application.Models.ReservedActivity;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.GregorianCalendar;

public class JonathanWhiteBox {

    // Test case A: End date is before start date
    @Test
    public void testGetAvailabilityScoreA() {
        Employee employee = new Employee("empl");
        GregorianCalendar startTime = new GregorianCalendar(2024, GregorianCalendar.APRIL, 10);
        GregorianCalendar endTime = new GregorianCalendar(2024, GregorianCalendar.APRIL, 9);
        int expected = 0;
        int actual = employee.getAvailabilityScore(startTime, endTime);
        assertEquals(expected, actual);
    }

    // Test case B: Periods overlap and activity is a ReservedActivity
    @Test
    public void testGetAvailabilityScoreB() {
        Employee e = new Employee("empl");
        ReservedActivity a = new ReservedActivity(new GregorianCalendar(2024, GregorianCalendar.APRIL, 11),
                new GregorianCalendar(2024, GregorianCalendar.APRIL, 12), "test", e);
        GregorianCalendar startTime = new GregorianCalendar(2024, GregorianCalendar.APRIL, 10);
        GregorianCalendar endTime = new GregorianCalendar(2024, GregorianCalendar.APRIL, 12);
        int expected = 4999999;
        int actual = e.getAvailabilityScore(startTime, endTime);
        assertEquals(expected, actual);
    }

    // Test case C: Periods do not overlap
    @Test
    public void testGetAvailabilityScoreC() {
        Employee e = new Employee("empl");
        ReservedActivity a = new ReservedActivity(new GregorianCalendar(2024, GregorianCalendar.APRIL, 13),
                new GregorianCalendar(2024, GregorianCalendar.APRIL, 14), "test", e);
        GregorianCalendar startTime = new GregorianCalendar(2024, GregorianCalendar.APRIL, 10);
        GregorianCalendar endTime = new GregorianCalendar(2024, GregorianCalendar.APRIL, 12);
        int expected = 9999999;
        int actual = e.getAvailabilityScore(startTime, endTime);
        assertEquals(expected, actual);
    }

    // Test case D: Periods overlap and activity is a ProjectActivity
    @Test
    public void testGetAvailabilityScoreD() {
        Employee e = new Employee("empl");
        Project p = new Project("TestProject",
                new GregorianCalendar(2024, GregorianCalendar.APRIL, 7),
                new GregorianCalendar(2024, GregorianCalendar.APRIL, 14));
        ProjectActivity a = new ProjectActivity(
                new GregorianCalendar(2024, GregorianCalendar.APRIL, 11),
                new GregorianCalendar(2024, GregorianCalendar.APRIL, 12),
                5,
                "test",
                p);
        e.addActivity(a);
        GregorianCalendar startTime = new GregorianCalendar(2024, GregorianCalendar.APRIL, 10);
        GregorianCalendar endTime = new GregorianCalendar(2024, GregorianCalendar.APRIL, 12);
        int expected = 9999998;
        int actual = e.getAvailabilityScore(startTime, endTime);
        assertEquals(expected, actual);
    }

    // Test case E: No activities
    @Test
    public void testGetAvailabilityScoreE() {
        Employee employee = new Employee("empl");
        GregorianCalendar startTime = new GregorianCalendar(2024, GregorianCalendar.APRIL, 10);
        GregorianCalendar endTime = new GregorianCalendar(2024, GregorianCalendar.APRIL, 12);
        int expected = 9999999;
        int actual = employee.getAvailabilityScore(startTime, endTime);
        assertEquals(expected, actual);
    }
}
