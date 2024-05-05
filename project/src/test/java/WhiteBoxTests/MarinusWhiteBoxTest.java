package WhiteBoxTests;

import org.application.Models.Employee;
import org.application.Models.Project;
import org.application.Models.ProjectActivity;
import org.application.Utils.GeneralMethods;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.GregorianCalendar;

public class MarinusWhiteBoxTest {
    
    // Test case A: expectedDuration less than or equal to 0
    @Test
    public void testGetProgressA() {
        GregorianCalendar testStartTime = GeneralMethods.intToCalendar(5);
        GregorianCalendar testEndTime = GeneralMethods.intToCalendar(7);
        Project testProject = new Project("test", testStartTime, testEndTime);
        ProjectActivity testProjectActivity = new ProjectActivity(testStartTime, testEndTime, 0, "test", testProject);
        Employee testEmployee = new Employee("test");
        String testStartString = "05:00";
        String testEndString = "10:00";
        GregorianCalendar[] testCalendars = GeneralMethods.stringsToCalendarList(testStartString, testEndString);
        testEmployee.createTimeBlock(testProjectActivity, testCalendars[0], testCalendars[1]);
        double expected = 0.0;
        double actual = testProjectActivity.getProgress();
        assertEquals(expected, actual, 0.001);
    }

    // Test case B: expectedDuration is greater than 0 and spentTime is less than or equal to expectedDuration
    @Test
    public void testGetProgressB() {
        GregorianCalendar testStartTime = GeneralMethods.intToCalendar(5);
        GregorianCalendar testEndTime = GeneralMethods.intToCalendar(7);
        Project testProject = new Project("test", testStartTime, testEndTime);
        ProjectActivity testProjectActivity = new ProjectActivity(testStartTime, testEndTime, 20, "test", testProject);
        Employee testEmployee = new Employee("test");
        String testStartString = "05:00";
        String testEndString = "10:00";
        GregorianCalendar[] testCalendars = GeneralMethods.stringsToCalendarList(testStartString, testEndString);
        testEmployee.createTimeBlock(testProjectActivity, testCalendars[0], testCalendars[1]);
        double expected = 0.5;
        double actual = testProjectActivity.getProgress();
        assertEquals(expected, actual, 0.001);
    }

    // Test case C: expectedDuration is greater than 0 and spentTime is greater than expectedDuration
    public void testGetProgressC() {
        GregorianCalendar testStartTime = GeneralMethods.intToCalendar(5);
        GregorianCalendar testEndTime = GeneralMethods.intToCalendar(7);
        Project testProject = new Project("test", testStartTime, testEndTime);
        ProjectActivity testProjectActivity = new ProjectActivity(testStartTime, testEndTime, 5, "test", testProject);
        Employee testEmployee = new Employee("test");
        String testStartString = "05:00";
        String testEndString = "10:00";
        GregorianCalendar[] testCalendars = GeneralMethods.stringsToCalendarList(testStartString, testEndString);
        testEmployee.createTimeBlock(testProjectActivity, testCalendars[0], testCalendars[1]);
        double expected = 1.0;
        double actual = testProjectActivity.getProgress();
        assertEquals(expected, actual, 0.001);
    }
}
