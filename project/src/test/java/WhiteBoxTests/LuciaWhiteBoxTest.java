package WhiteBoxTests;

import java.util.GregorianCalendar;

import org.application.Models.Employee;
import org.application.Models.ProjectActivity;
import org.application.Models.ReservedActivity;
import org.application.Models.SystemModel;
import org.application.Models.Project;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

public class LuciaWhiteBoxTest {
    //reset before every test
    @Before
    public void start() {
        SystemModel.reset();
    }

    // Test case A: activities does not contain the activity given
    @Test
    public void testAddActivityA(){
        //setup
        Employee employee = new Employee("424242"); // object where the method is
        GregorianCalendar startDay = new GregorianCalendar(2024,4,7);
        GregorianCalendar endDay = new GregorianCalendar(2024,4,8);
        ReservedActivity reservedActivity = new ReservedActivity(startDay, endDay, "test-employee", employee); // activity object to be used

        //test
        employee.addActivity(reservedActivity); // run method
        assertFalse(employee.getActivities().isEmpty()); //test if result is expected result by checking if an element has been added to the array
    }

    //Test case B activities does contain the activity given
    @Test
    public void testAddActivityB(){
        //setup
        Employee employee = new Employee("424242"); // object where the method is
        //create project for ProjectActivity object
        GregorianCalendar startWeekProject = new GregorianCalendar();
        startWeekProject.setWeekDate(2024, 15, 1);
        GregorianCalendar endWeekProject = new GregorianCalendar();
        endWeekProject.setWeekDate(2024, 20, 1);
        Project project = new Project("test-project", startWeekProject, endWeekProject);
        //create ProjectActivity object
        GregorianCalendar startWeekActivity = new GregorianCalendar();
        startWeekActivity.setWeekDate(2024, 17, 1);
        GregorianCalendar endWeekActivity = new GregorianCalendar();
        endWeekActivity.setWeekDate(2024, 19, 1);
        ProjectActivity projectActivity = new ProjectActivity(startWeekActivity, endWeekActivity,20, "test-activity", project);
        //Assign activity to employee without using addActivity to test the method
        projectActivity.assignEmployee(employee);

        //test
        employee.addActivity(projectActivity);
        assertTrue(employee.getActivities().size() == 1);
    }
}
